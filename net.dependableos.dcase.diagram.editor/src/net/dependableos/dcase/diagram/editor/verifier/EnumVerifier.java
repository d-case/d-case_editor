/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;

import java.util.List;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;

import org.eclipse.osgi.util.NLS;

/**
 * A verifier for an enumeration parameter.
 */
public class EnumVerifier extends DataTypeVerifier {

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        init(true);
    }
    
    /**
     * Initialize the verifier.
     * @param load when the resource is loaded actually: true.
     */
    public void init(boolean load) {
        // Checks if forbidden characters exist in name attribute value. 
        validName(load);
        
        if (load) {
            if (getAttributeList().size() == 0) {
                throw new DcaseSystemException(NLS.bind(Messages.EnumVerifier_0,
                        DATA_TYPE_ENUM), null, MessageTypeImpl.UNDEFINED);
            }
        } else {
            if (getAttributeList().size() == 0) {
                throw new DcaseSystemException(
                        net.dependableos.dcase.diagram.editor.message.Messages.EnumVerifier_EnumEmptyErrMessage,
                        null,
                        MessageTypeImpl.UNDEFINED);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String value) {
        boolean result = false;
        for (String str : getAttributeList()) {
            if (str.equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getItems() {
        return getAttributeList();
    }
    
    /**
     * Checks if item value is valid or not.
     * @param item the item value 
     */
    public static void validItemString(String item) {
        if (item == null || item.length() == 0) {
            return;
        }
        validString(item, item, new String[]{",", ";", "{", "}"});
    }
    
    /**
     * Validates all attribute string values.
     * 
     * @param load the load flag. if if this instance is initialized from the parameter definition file:true.
     */
    @Override
    protected void validName(boolean load) {
        super.validName(load);
        for (String item : getItems()) {
            validItemString(item);
        }
    }
}
