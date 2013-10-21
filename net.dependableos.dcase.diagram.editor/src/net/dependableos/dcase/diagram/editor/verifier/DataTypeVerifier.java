/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.osgi.util.NLS;

import net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.message.Messages;

/**
 * An abstract class that provides methods for verification.
 */
public abstract class DataTypeVerifier {
    
    /**
     * the data type.
     */
    private String dataType;
    
    /**
     * the parameter name.
     */
    private String paramName;

    /**
     * the map of attributes.
     */
    private Map<String , String> attributeMap = new LinkedHashMap<String , String>();
    
    /**
     * the list of enumeration items. 
     */
    private List<String> enumerationItems = new ArrayList<String>();

    /**
     * Adds an attribute.
     * 
     * @param key the key.
     * @param value the value.
     */
    public void addAttribute(String key , String value) {
        if (value != null && value.length() > 0) {
            attributeMap.put(key, value);
        }
    }
    
    /**
     * Adds an enumeration item.
     * 
     * @param value an enumeration item.
     */
    public void addItem(String value) {
        enumerationItems.add(value);
    }
    
    /**
     * Returns the map of attributes.
     * 
     * @return the map of attributes.
     */
    public Map<String , String> getAttributeMap() {
        return attributeMap;
    }
    
    /**
     * Returns the list of enumeration items.
     * 
     * @return the list of enumeration items.
     */
    public List<String> getAttributeList() {
        return enumerationItems;
    }
    
    /**
     * Returns the data type.
     * 
     * @return the the data type.
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the data type.
     * 
     * @param dataType the data type.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    /**
     * Returns the parameter name.
     * 
     * @return the parameter name.
     */
    public String getParamName() {
        return paramName;
    }
    
    /**
     * Sets the parameter name.
     * 
     * @param paramName the parameter name.
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    
    /**
     * Returns the list of enumeration items.
     * 
     * @return the list of enumeration items.
     */
    public List<String> getItems() {
        return null;
    }
    
    /**
     * Initializes.
     */
    public abstract void init();
    
    /**
     * Verifies the value.
     * 
     * @param value the value.
     * @return true if and only if the value is verified;false otherwise.
     */
    public abstract boolean verify(String value);
    
    /**
     * Validates name attribute values.
     * 
     * @param load the load flag. if if this instance is initialized from the parameter definition file:true.
     */
    protected void validName(boolean load) {
            validString(
                    load,
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    attributeMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_NAME));
    }
    
    /**
     * validates the a attribute sting value.
     * 
     * @param load the load flag. if if this instance is initialized from the parameter definition file:true.
     * @param attributeName the attribute name
     * @param value the attribute string value
     */
    public static void validString(boolean load, String attributeName, String value) {
        if (value == null || value.length() == 0) {
            return;
        }
        validString(
                attributeName,
                value,
                new String[]{
                        ",", ";", "{", "}", "="}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        if (!load) {
            checkReservedWord(value, new String[]{"id", "Requirements", "n"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }
    
    /**
     * Checks if the reserved word is contained in the given value.
     * @param value the value
     * @param reserved the reserved words
     */
    protected static void checkReservedWord(String value, String[] reserved) {
        if (value == null || value.length() == 0) {
            return;
        }
        for (String str : reserved) {
            if (value.equals(str)) {
                throw new DcaseSystemException(
                        NLS.bind(
                                Messages.DataTypeVerifier_ReservedWordErrMessage,
                                str),
                                null,
                                MessageTypeImpl.UNDEFINED);
            }
        }
    }
    
    /**
     * validates the a attribute sting value.
     * 
     * @param attributeName the attribute name
     * @param value the attribute string value
     * @param forbidden the forbidden strings
     */
    protected static void validString(String attributeName, String value, String[] forbidden) {
        if (value == null || value.length() == 0) {
            return;
        }
        for (String str : forbidden) {
            if (value.contains(str)) {
                throw new DcaseSystemException(
                        NLS.bind(
                                Messages.DataTypeVerifier_ForbiddenCharacterErrMessage,
                                attributeName,
                                getArrayString(forbidden)),
                        null,
                        MessageTypeImpl.UNDEFINED);
            }
        }
    }
    
    /**
     * Returns the string that represents the string array. 
     * @param array the array
     * @return the string that represents the string array
     */
    private static String getArrayString(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
            sb.append(" "); //$NON-NLS-1$
            }
        }
        return sb.toString();
    }
}
