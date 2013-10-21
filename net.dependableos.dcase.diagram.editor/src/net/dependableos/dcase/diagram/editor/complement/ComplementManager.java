/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;

import static javax.xml.xpath.XPathConstants.NODESET;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.TEMPLATE_RESOURCE_PROJECT_NAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;

import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A class that manages the candidates for complement.
 */
public class ComplementManager {

    /**
     * the XPath for the candidate for complement.
     */
    private static final String XPATH_TEMPLATE =
        "/ComplementChoices/Template[(@type='%1$s' or @type='All') and (count(tags/tag)=0%2$s)]"; //$NON-NLS-1$

    /**
     * format string for the predicate to filter by the tag.
     */
    private static final String XPATH_MATCH_TAG = " or tags/tag='%1$s'";        //$NON-NLS-1$

    /**
     * the path to the template project.
     */
    private static final String TEMPLATE_PROJECT_PATH = PropertyUtil
            .getSystemProperty(TEMPLATE_RESOURCE_PROJECT_NAME)
            + "/%1$s";                                                          //$NON-NLS-1$

    /**
     * the name of the complement definition file.
     */
    private static final String COMPLEMENT_CONFIG_FILENAME = "Complement.xml";  //$NON-NLS-1$

    /**
     * the attribute name of "name".
     */
    private static final String XML_ATTRIBUTE_NAME = "name";                    //$NON-NLS-1$

    /**
     * the attribute name of "path".
     */
    private static final String XML_ATTRIBUTE_PATH = "path";                    //$NON-NLS-1$

    /**
     * the separators of tags.
     */
    private static final String TAG_SEPARATOR = "\r|\n|\\,|\\.| ";              //$NON-NLS-1$

    /**
     * the instance.
     */
    private static ComplementManager instance = new ComplementManager();

    /**
     * the xml document.
     */
    private Document xmlDocument;

    /**
     * true if and only if the complement definition file is loaded.
     */
    private boolean isLoaded = false;

    /**
     * Returns the instance.
     * 
     * @return the instance.
     */
    public static ComplementManager getInstance() {
        if (!instance.isLoaded()) {
            URI uri = URI.createPlatformResourceURI(String.format(TEMPLATE_PROJECT_PATH,
                    COMPLEMENT_CONFIG_FILENAME), true);

            instance.load(uri.toString());
        }

        return instance;
    }

    /**
     * Returns the candidates for the specified node.
     * 
     * @param node the node.
     * @return the candidates.
     */
    public List<ComplementItem> getItems(NodeInfo node) {
        // builds the predicate to filter by the tags.
        Object desc = node.getAttribute(AttributeType.DESC);
        StringBuffer tagFinder = new StringBuffer();
        if (desc != null) {
            String[] tags = desc.toString().split(TAG_SEPARATOR);
            for (String tag : tags) {
                if (tag.length() > 0) {

                    tagFinder.append(String.format(XPATH_MATCH_TAG, tag));
                }
            }
        }

        List<ComplementItem> items = new ArrayList<ComplementItem>();
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        try {
            // filters by the tags.
            XPathExpression expr = xpath.compile(String.format(XPATH_TEMPLATE,
                    node.getNodeType().getName(), tagFinder.toString()));

            NodeList list = (NodeList) expr.evaluate(xmlDocument, NODESET);

            // builds the list of the candidates.
            int size = list.getLength();
            for (int i = 0; i < size; i++) {
                Node xmlNode = list.item(i);
                ComplementItem item = new ComplementItem(String.format(
                        TEMPLATE_PROJECT_PATH, xmlNode.getAttributes()
                                .getNamedItem(XML_ATTRIBUTE_PATH)
                                .getNodeValue()), xmlNode.getAttributes()
                        .getNamedItem(XML_ATTRIBUTE_NAME).getNodeValue());

                items.add(item);
            }
        } catch (Exception e) {
            handleException(e);
        }
        return items;
    }

    /**
     * Loads the complement definition file.
     * 
     * @param path the path to the complement definition file.
     */
    protected void load(String path) {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory
                .newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            xmlDocument = builder.parse(path);
            isLoaded = true;
        } catch (ParserConfigurationException e) {
            handleException(e);
        } catch (IOException e) {
            handleException(e);
        } catch (SAXException e) {
            handleException(e);
        }
    }

    /**
     * Tests whether complement definition file is loaded.
     * 
     * @return true if and only if the complement definition file is loaded;false otherwise.
     */
    protected boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Handles the exception.
     * 
     * @param e the exception.
     */
    private void handleException(Exception e) {
        throw new DcaseSystemException(e.getMessage(), e,
                MessageTypeImpl.COMPLEMENT_INIT_FAILED);
    }

}
