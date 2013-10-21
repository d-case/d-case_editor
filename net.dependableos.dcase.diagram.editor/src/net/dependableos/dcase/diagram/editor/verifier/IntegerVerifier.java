/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_INC;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MAX;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;

import java.util.Map;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;

import org.eclipse.osgi.util.NLS;

/**
 * A verifier for an integer parameter.
 */
public class IntegerVerifier extends DataTypeVerifier {

    /**
     * the minimum value.
     */
    private int min;

    /**
     * the maximum value.
     */
    private int max;
    
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
            // gets the minimum value.
            String minStr = attributeMap.get(DATA_ATTRIBUTE_MIN);
            if (minStr != null && minStr.length() > 0) {
                min = Integer.parseInt(minStr);
            } else {
                min = 0;
            }
            // gets the maximum value.
            String maxStr = attributeMap.get(DATA_ATTRIBUTE_MAX);
            if (maxStr != null && maxStr.length() > 0) {
                max = Integer.parseInt(maxStr);
            } else {
                max = Integer.MAX_VALUE;
            }

            // throws an exception if the minimum value is lager than the maximum value.
            if (min >= max) {
                if (load) {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.IntegerVerifier_1, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED);
                } else {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.IntegerVerifier_2, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED); 
                }
            }

            // gets the incremental.
            String incStr = attributeMap.get(DATA_ATTRIBUTE_INC);
            int inc = 1;
            if (incStr != null && incStr.length() > 0) {
                inc = Integer.parseInt(incStr);
            }
            
            long maxL = max;
            long minL = min;
            // throws an exception if the incremental is lager than the maximum value minus the minimum value.
            if (inc < 1 || inc > (maxL - minL)) {
                throw new DcaseSystemException(NLS.bind(
                        Messages.IntegerVerifier_0, DATA_ATTRIBUTE_MAX), null,
                        MessageTypeImpl.UNDEFINED);
            }
        } catch (NumberFormatException e) {
            throw new DcaseSystemException(NLS.bind(Messages.IntegerVerifier_0,
                    getParamName()), e, MessageTypeImpl.UNDEFINED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String value) {
        boolean result = false;
        try {
            int intValue = Integer.parseInt(value);

            if (min <= intValue && intValue <= max) {
                result = true;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
