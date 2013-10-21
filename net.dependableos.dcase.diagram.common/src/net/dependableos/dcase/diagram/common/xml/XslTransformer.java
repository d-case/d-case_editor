/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;


/**
 * A class that transform a XML using a XSL.
 */
public final class XslTransformer {

    /**
     * A constructor.
     */
    private XslTransformer() {
    }

    /**
     * Transforms a XML using a XSL.
     * 
     * @param target the source
     * @param source the target
     * @param xsl a XSL file.
     */
    public static void transform(StreamResult target, StreamSource source,
            StreamSource xsl) {

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(xsl);

            transformer.transform(source, target);
        } catch (TransformerConfigurationException configurationException) {
            throw new DcaseSystemException(configurationException.getMessage(),
                    null, MessageTypeImpl.UNDEFINED);
        } catch (TransformerException transformerException) {
            throw new DcaseRuntimeException(transformerException.getMessage(),
                    transformerException, null, 0, MessageTypeImpl.UNDEFINED);
        }
    }
}
