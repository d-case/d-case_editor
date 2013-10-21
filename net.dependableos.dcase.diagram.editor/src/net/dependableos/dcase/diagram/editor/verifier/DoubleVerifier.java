/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_DIGIT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_INC;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MAX;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;

import java.util.Map;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;

import org.eclipse.osgi.util.NLS;

/**
 * A verifier for a Double parameter.
 */
public class DoubleVerifier extends DataTypeVerifier {

    /**
     * minimum.
     */
    private double min;

    /**
     * maximum.
     */
    private double max;

    /**
     * decimal places.
     */
    private int digit;
    
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
            if (minStr != null) {
                min = Double.parseDouble(minStr);
            } else {
                min = 0;
            }
            // gets the maximum value.
            String maxStr = attributeMap.get(DATA_ATTRIBUTE_MAX);
            if (maxStr != null && maxStr.length() > 0) {
                max = Double.parseDouble(maxStr);
            } else {
                max = Double.POSITIVE_INFINITY;
            }
            // gets the decimal places.
            digit = 2;
            String digitStr = attributeMap.get(DATA_ATTRIBUTE_DIGIT);
            if (digitStr != null && digitStr.length() > 0) {
                digit = Integer.parseInt(digitStr);
                if (digit < 0) {
                    digit = 2;
                }
            }
            attributeMap.put(DATA_ATTRIBUTE_DIGIT, Integer.valueOf(digit).toString());
            // throws an exception if minimum value is invalid.
            if (min >= max) {
                if (load) {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.DoubleVerifier_1, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED);
                } else {
                    throw new DcaseSystemException(NLS.bind(
                            Messages.DoubleVerifier_3, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN), null,
                            MessageTypeImpl.UNDEFINED);
                }
            }

            // gets the incremental.
            String incStr = attributeMap.get(DATA_ATTRIBUTE_INC);
            double inc = 1;
            if (incStr != null && incStr.length() > 0) {
                inc = Double.parseDouble(incStr);
            }
            // throws an exception if the incremental is invalid.
            if (inc <= 0 || inc > (max - min)) {
                if (load) {
                    throw new DcaseSystemException(
                            NLS.bind(
                                Messages.DoubleVerifier_2,
                                new Object[]{DATA_ATTRIBUTE_INC, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN}),
                            null,
                            MessageTypeImpl.UNDEFINED);
                } else {
                    throw new DcaseSystemException(
                            NLS.bind(
                                Messages.DoubleVerifier_4,
                                new Object[]{DATA_ATTRIBUTE_INC, DATA_ATTRIBUTE_MAX, DATA_ATTRIBUTE_MIN}),
                            null,
                            MessageTypeImpl.UNDEFINED); 
                }
            }
        } catch (NumberFormatException e) {
            throw new DcaseSystemException(NLS.bind(Messages.DoubleVerifier_0,
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
            double doubleValue = Double.parseDouble(value);
            if (min <= doubleValue && doubleValue <= max) {
                result = true;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
