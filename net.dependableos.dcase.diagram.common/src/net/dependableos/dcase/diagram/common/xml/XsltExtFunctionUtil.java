/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;

/**
 * 
 * The Xsl transform extended functions class.
 *
 */
public final class XsltExtFunctionUtil {

    /**
     * The UTF-8 encoding name.
     */
    private static final String UTF8_ENCODING = "UTF-8";

    /**
     * Default contractor.
     */
    private XsltExtFunctionUtil() {
        
    }

    /**
     * Get serialized text from XML elements.
     * @param nodes The DOM node list,
     * @return The serialized text.
     */
    public static String serialize(NodeList nodes) {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer;
        StringBuilder sb = new StringBuilder();
        try {
            serializer = tf.newTransformer();
            StringWriter sw = new StringWriter();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                serializer.transform(new DOMSource(node), new StreamResult(sw));
                sb.append(sw.getBuffer().toString());
            }
        } catch (TransformerException e) {
            throw new DcaseSystemException(e.getMessage(),
                    null, MessageTypeImpl.UNDEFINED);
        }

        return sb.toString();
    }

    /**
     * Get XML elements from serialized text.
     * @param xml The serialized text.
     * @return Parsed XML element.
     * @throws ParserConfigurationException The parser configuration exception.
     * @throws SAXException The SAX exception.
     * @throws IOException The I/O exception.
     */
    public static Node deserialize(String xml)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory  documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder builder;

        builder = documentBuilderFactory.newDocumentBuilder();
        InputStream in = new ByteArrayInputStream(xml.getBytes(UTF8_ENCODING));

        Document document = builder.parse(in);

        return document.getDocumentElement();
    }
}
