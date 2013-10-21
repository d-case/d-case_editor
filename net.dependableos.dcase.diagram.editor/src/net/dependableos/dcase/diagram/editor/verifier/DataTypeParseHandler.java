/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_NAME;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_TYPE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_VALUE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_DOUBLE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_INT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_RAW;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_STRING;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_TAG_ITEM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NS_PARAMETER_DATA_TYPE;

import java.util.LinkedHashMap;
import java.util.Map;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A handler to parse a XML file which defines parameters.
 */
public class DataTypeParseHandler extends DefaultHandler {

    /**
     * the current tag name.
     */
    private String currentTagName = null;

    /**
     * the current verifier.
     */
    private DataTypeVerifier currentVerifier = null;

    /**
     * the map of parameters and theirs verifiers.
     */
    private Map<String, DataTypeVerifier> verifierMap = new LinkedHashMap<String, DataTypeVerifier>();

    /**
     * Creates an instance and initializes it.
     */
    public DataTypeParseHandler() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startElement(String namespaceURI, String localName,
            String tagName, Attributes attrs) {
        if (DATA_TYPE_TAG_PARAMETER.equals(localName)
                && NS_PARAMETER_DATA_TYPE.equals(namespaceURI)) {
            if (attrs.getLength() != 0) {
                // gets the parameter name.
                String paramName = attrs.getValue(DATA_ATTRIBUTE_NAME);
                // terminate if the parameter name is null or empty.
                if (paramName == null || paramName.trim().length() == 0) {
                    return;
                }

                // gets the type.
                String type = attrs.getValue(DATA_ATTRIBUTE_TYPE);

                // trims.
                if (type != null) {
                    type = type.trim();
                }

                // in case of integer type.
                if (DATA_TYPE_INT.equals(type)) {
                    currentVerifier = new IntegerVerifier();
                }
                // in case of double type.
                if (DATA_TYPE_DOUBLE.equals(type)) {
                    currentVerifier = new DoubleVerifier();
                }
                // in case of string type.
                if (DATA_TYPE_STRING.equals(type)) {
                    currentVerifier = new StringVerifier();
                }
                // in case of enumeration type.
                if (DATA_TYPE_ENUM.equals(type)) {
                    currentVerifier = new EnumVerifier();
                }
                // in case of raw type.
                if (DATA_TYPE_RAW.equals(type)) {
                    currentVerifier = new StringVerifier();
                }

                if (currentVerifier != null) {
                    // sets the data type.
                    currentVerifier.setDataType(type);
                    // sets the parameter name.
                    currentVerifier.setParamName(paramName);
                    // puts the verifier to the map.
                    verifierMap.put(paramName, currentVerifier);
                    // sets the current tag name.
                    currentTagName = localName;
                }
            }
        }

        // tests whether the current tag is a parameter.
        if (DATA_TYPE_TAG_PARAMETER.equals(currentTagName)) {
            // gets the data type.
            String type = currentVerifier.getDataType();
            // gets enumeration items.
            if (DATA_TYPE_ENUM.equals(type)) {
                if (DATA_TYPE_TAG_ITEM.equals(localName)) {
                    currentVerifier.addItem(attrs
                            .getValue(DATA_ATTRIBUTE_VALUE));
                }
            } else {
                // gets attributes.
                if (!DATA_TYPE_TAG_PARAMETER.equals(localName)) {
                    for (int i = 0; i < attrs.getLength(); i++) {
                        currentVerifier.addAttribute(attrs.getQName(i), attrs
                                .getValue(i));
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String namespaceURI, String localName, String tagName) {
        // tests whether the local name is a parameter.
        if (DATA_TYPE_TAG_PARAMETER.equals(localName)) {
            if (currentVerifier != null) {
                try {
                    // initializes the current verifier.
                    currentVerifier.init();
                } catch (DcaseSystemException e) {
                    // outputs the error to the log.
                    MessageWriter.writeMessageToErrorLog(e);
                    // remove the current verifier from the map.
                    verifierMap.remove(currentVerifier.getParamName());
                }
            }
            currentTagName = null;
            currentVerifier = null;
        }
    }

    /**
     * the map of parameters and theirs verifiers.
     * 
     * @return the validatorMap
     */
    public Map<String, DataTypeVerifier> getVerifierMap() {
        return verifierMap;
    }
}
