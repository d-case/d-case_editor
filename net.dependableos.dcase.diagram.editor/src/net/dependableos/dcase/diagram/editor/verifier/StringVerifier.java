/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MAX;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;

import java.util.Map;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;

import org.eclipse.osgi.util.NLS;

/**
 * A verifier for a string.
 */
public class StringVerifier extends DataTypeVerifier {

    /**
     * empty.
     */
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * the minimum length.
     */
    private int minLength;

    /**
     * the maximum length.
     */
    private int maxLength;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        init(true);
    }

    /**
     * Initializes.
     * 
     * @param load true if this instance is initialized from the parameter definition file.
     */
    public void init(boolean load) {
        try {
            
            // Checks if forbidden characters exist in name attribute value. 
            validName(load);
            
            Map<String, String> attributeMap = getAttributeMap();
            // gets the minimum length.
            String minLengthStr = attributeMap.get(DATA_ATTRIBUTE_MIN);
            if (minLengthStr != null) {
                minLength = Integer.parseInt(minLengthStr);
            } else {
                minLength = 0;
            }
            // gets the maximum length.
            String maxLengthStr = attributeMap.get(DATA_ATTRIBUTE_MAX);
            if (maxLengthStr != null) {
                maxLength = Integer.parseInt(maxLengthStr);
            } else {
                maxLength = Integer.MAX_VALUE;
            }
            // throw a exception if the minimum length is lager than maximum length.
            if (minLength > maxLength) {
                if (load) {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.StringVerifier_1, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED);
                } else {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.StringVerifier_2, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED); 
                }
            }
        } catch (NumberFormatException e) {
            throw new DcaseSystemException(NLS.bind(Messages.StringVerifier_0,
                    getParamName()), e, MessageTypeImpl.UNDEFINED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String value) {
        boolean result = false;
        if (value == null) {
            value = EMPTY_STRING;
        }
        if (minLength <= value.length() && value.length() <= maxLength) {
            result = true;
        }
        return result;
    }
}
