/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.impl;

import java.util.ArrayList;
import java.util.List;

import net.dependableos.dcase.BasicNode;


/**
 * A parameter item.
 */
public class ParameterItem {
    /**
     * the format string of the parameter item.
     */
    public static final String PARAM_ITEM_FORMAT = "\\{\\s*%1$s\\s*\\}"; //$NON-NLS-1$
    /**
     * The character string of the new line.
     */
    private static final String NEW_LINE_CHAR = "n"; //$NON-NLS-1$
    /**
     * The key name to get value of the line feed from the system property.
     */
    private static final String LINE_SEPRATOR = "line.separator"; //$NON-NLS-1$
    /**
     * the separator of parameters.
     */
    public static final String SEPARATOR = ","; //$NON-NLS-1$
    /**
     * the separator of the id and the value.
     */
    private static final String EQUAL = "="; //$NON-NLS-1$
    /**
     * the escape equal value.
     */
    private static final String ESCAPE_EQUAL = "&#3d;"; //$NON-NLS-1$
    /**
     * the parameter ID.
     */
    private String parameterId;
    /**
     * the parameter value.
     */
    private String parameterValue;
    /**
     * true if and only if the parameter is set to use.
     */
    private boolean selected;
    
    /**
     * Allocates a ParameterItem object.
     */
    public ParameterItem() {
    }
    
    /**
     * Allocates a ParameterItem object and initializes it to represent the specified string.
     * 
     * @param paramStr a string representation of the parameter item.
     */
    public ParameterItem(String paramStr) {
        String[] paramSet = paramStr.split(EQUAL);
        if (paramSet[0].trim().length() != 0) {
            parameterId = paramSet[0];
            if (paramSet.length > 1) {
                parameterValue = paramSet[1];
            }
        }
    }
    
    /**
     * Tests whether the parameter is set to use.
     * 
     * @return true if and only if the parameter is set to use; false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }
    /**
     * Sets whether the parameter is set to use.
     * 
     * @param selected true if and only if the parameter is set to use; false otherwise.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /**
     * Returns parameter ID.
     * 
     * @return the parameter ID
     */
    public String getParameterId() {
        return parameterId;
    }
    /**
     * Sets the parameter ID.
     * 
     * @param parameterId the parameter ID to set
     */
    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }
    /**
     * Returns the parameter value.
     * 
     * @return the parameter value.
     */
    public String getParameterValue() {
        if (parameterValue == null) {
            return ""; //$NON-NLS-1$
        }
        return parameterValue;
    }
    /**
     * Sets the parameter value.
     * 
     * @param parameterValue the parameter value to set
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
    
    /**
     * Tests whether the parameter is valid.
     * 
     * @param parameterStr parameter string to test.
     * @return true if and only if the parameter is valid; false otherwise.
     */
    public static boolean isValidParameter(String parameterStr) {
        boolean result = true;
        String[] params = parameterStr.split(SEPARATOR);
        for (String str : params) {
            //ignore the empty.
            if ("".equals(str)) { //$NON-NLS-1$
                continue;
            }
            //invalid if the id is empty.
            if (str.trim().indexOf(EQUAL) <= 0) {
                result = false;
                break;
            }
            String[] paramSet = str.split(EQUAL);
            //invalid if more than one "=" are detected.
            if (paramSet.length > 2) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    /**
     * Creates the list of parameter items from the parameter string.
     * 
     * @param parameters parameter string.
     * @return the list of parameter items.
     */
    public static List<ParameterItem> getPatameterList(String parameters) {
        List<ParameterItem> list = new ArrayList<ParameterItem>();
        if (parameters != null && isValidParameter(parameters)) {
            String[] params = parameters.split(SEPARATOR);
            for (String str : params) {
                String[] paramSet = str.split(EQUAL);
                ParameterItem item = new ParameterItem();
                if (paramSet[0].trim().length() == 0) {
                    continue;
                }
                item.parameterId = paramSet[0];
                if (paramSet.length > 1) {
                    item.parameterValue = paramSet[1].replaceAll(ESCAPE_EQUAL, EQUAL);
                }
                list.add(item);
            }
        }
        return list;
    }
    
    /**
     * Returns the string formatted with the parameters and the formatter.
     * 
     * @param parameters a string representation of parameters.
     * @param formatter a formatter.
     * @return the string formatted with the parameters and the formatter.
     */
    public static String getFormattedDesc(String parameters , String formatter) {
        List<ParameterItem> list = getPatameterList(parameters);
        for (ParameterItem item : list) {
            formatter = formatter.replaceAll(String.format(PARAM_ITEM_FORMAT, item
                    .getParameterId()), item.getParameterValue());
        }
        // Sets the line feed for the Desc attribute.
        formatter = formatter.replaceAll(String.format(PARAM_ITEM_FORMAT, NEW_LINE_CHAR), 
                System.getProperty(LINE_SEPRATOR));
        return formatter;
    }
    
    /**
     * Returns the formatted desc string derived from the parameters, the formatter
     * and the other node attribute. This method is prepared for the Attribute dialog
     * originally.
     * 
     * @param node the target node.
     * @param parameters a value of parameters attribute.
     * @param formatter a formatter.
     * @return the formatted desc string adjusted to the target node.
     */
    public static String getFormattedDesc(BasicNode node, String parameters , String formatter) {
	if(parameters != null && parameters.length() > 0) {
		formatter = getFormattedDesc(parameters, formatter);
	}
	return formatter;
    }
    
    /**
     * Sets the formated string to the Desc attribute if the parameters and the formatter are valid.
     * 
     * @param basicNode a basic node.
     * @param parameters a new value of the parameters.
     */
    public static void setDesc(BasicNode basicNode, String parameters) {
        String userdef005 = basicNode.getUserdef005();
        // tests whether the formatter is valid.
        if (userdef005 != null && userdef005.trim().length() != 0) {
            basicNode.setDesc(getFormattedDesc(parameters, userdef005));
        }
    }
    
    /**
     * Returns a string representation of the parameter item.
     * 
     * @return String a string representation of the parameter item.
     */
    public String getParamString() {
        String result = parameterId.concat(EQUAL);
        if (parameterValue != null) {
            result = result.concat(parameterValue.replaceAll(EQUAL, ESCAPE_EQUAL));
        }
        return result;
    }
    
    /**
     * Returns the string that represents the parameters.
     * @param parameters the parameters.
     * @return the string that represents the parameters.
     */
    public static String getSavedString(List<ParameterItem> parameters) {
    	StringBuffer buf = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            buf.append(parameters.get(i).getParamString());
            if (i < parameters.size() - 1) {
                buf.append(SEPARATOR);
            }
        }
        return buf.toString();
    }
}
