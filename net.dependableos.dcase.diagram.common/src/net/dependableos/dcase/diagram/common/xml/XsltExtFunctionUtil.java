/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.xml;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_NAME;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_TYPE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_VALUE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.impl.ParameterItem;

/**
 * 
 * The Xsl transform extended functions class.
 *
 */
public final class XsltExtFunctionUtil {
	
    /**
     * The XML element names.
     */
    private static final String XML_NAMESPACE = "http://www.dependable-os.net/2013/11/dcase"; //$NON-NLS-1$
    private static final String XML_ELEM_PARAMETERS = "parameters"; //$NON-NLS-1$
    private static final String XML_ELEM_PARAMETER = "parameter"; //$NON-NLS-1$


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
    
    /**
     * Return parameter elements from definitions and values.
     * @param parameterVals The parameter values.
     * @param parameterDefs The parameter definitions.
     * @return parameter elements.
     * @throws ParserConfigurationException The parser configuration exception.
     */
    public static Node deparameterize(String parameterVals, String parameterDefs) 
    		throws ParserConfigurationException {
    	DocumentBuilderFactory  documentBuilderFactory = DocumentBuilderFactory.newInstance();
       documentBuilderFactory.setNamespaceAware(true);
    	DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
    	Document document = builder.newDocument();
    	Element elem = document.createElementNS(XML_NAMESPACE, XML_ELEM_PARAMETERS);

    	// create parameter elements
    	if (parameterVals != null && parameterVals.length() > 0 &&
    			parameterDefs != null && parameterDefs.length() > 0) {
    		// parse values
    		List<ParameterItem> paramList = ParameterItem.getPatameterList(parameterVals);
    		// parse definitions
    		String[] defsArray = parameterDefs.split(";"); //$NON-NLS-1$
    		for (int i = 1; i < defsArray.length; i++) {
    			// parse definition of index i
        		List<ParameterItem> defList = createParameterDefinitionList(defsArray[i], paramList);
        		// create and add the parameter
    			Element childElem = document.createElementNS(XML_NAMESPACE, XML_ELEM_PARAMETER);
    			for (ParameterItem item : defList) {
    				childElem.setAttribute(item.getParameterId(), item.getParameterValue());
    				// notice: attributes are sorted in dictionary order.
    			}
    			elem.appendChild(childElem);
    		}
    	}
    	return elem;
    }
    
    /**
     * Return the list of parameter definitions.
     * @param parameterDefs The parameter definitions.
     * @param paramList The parameter values.
     * @return the map of parameter definitions.
     */
    private static List<ParameterItem> createParameterDefinitionList(
    		String parameterDefs, List<ParameterItem> paramList) {
    	List<ParameterItem> retList = new ArrayList<ParameterItem>();
    	String name = null;
		String[] defArray = parameterDefs.split(ParameterItem.SEPARATOR);
		// convert the string array to the list
		for (int j = 0; j < defArray.length; j++) {
			ParameterItem item = new ParameterItem(defArray[j]);
			String id = item.getParameterId();
			if (DATA_ATTRIBUTE_NAME.equals(id)) {
				name = item.getParameterValue();
				retList.add(item);
			} else if (DATA_ATTRIBUTE_TYPE.equals(id)) {
				retList.add(item);
				// if enum, parse items only
				if (DATA_TYPE_ENUM.equals(item.getParameterValue())) {
					int itemIndex = 0;
					for (int k = 0; k < j+1; k++, itemIndex++) {
						itemIndex = parameterDefs.indexOf(ParameterItem.SEPARATOR, itemIndex);
						if (itemIndex < 0) {
							break;
						}
					}
					if (itemIndex > 0) {
						item = new ParameterItem(parameterDefs.substring(itemIndex));
						retList.add(item);
					}
					break;
				}
			} else {
				retList.add(item);
			}
		}
		/* add the value */
		for (ParameterItem item : paramList) {
			if (item.getParameterId().equals(name)) {
				item.setParameterId(DATA_ATTRIBUTE_VALUE);
				retList.add(item);
				break;
			}
		}
    	return retList;
    }

    /**
     * Return the parameter values.
     * @param nodes the node of parameters.
     * @return the parameter values.
     */
    public static String parameterizeVals(NodeList nodes) {
    	// check...
    	if (nodes.getLength() <= 0) {
    		return null;
    	}
    	Node parentNode = nodes.item(0); // parameters
    	NodeList childNodes = parentNode.getChildNodes(); // parameter
    	if (childNodes == null || childNodes.getLength() <= 0) {
    		return null;
    	}

    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < childNodes.getLength(); i++) {
    		Node node = childNodes.item(i);
    		if (! XML_ELEM_PARAMETER.equals(node.getNodeName())) {
    			continue;
    		}
    		// parse attributes
    		NamedNodeMap attrMap = node.getAttributes();
    		String name = null;
    		String value = null;
    		for (int j = 0; j < attrMap.getLength(); j++) {
    			Node attr = attrMap.item(j);
    			if (DATA_ATTRIBUTE_NAME.equals(attr.getNodeName())) {
    				name = attr.getNodeValue();
    			}
    			if (DATA_ATTRIBUTE_VALUE.equals(attr.getNodeName())) {
    				value = attr.getNodeValue();
    			}
    		}
    		if (name != null && value != null) {
    			sb.append(ParameterItem.SEPARATOR + name + "=" + value); //$NON-NLS-1$
    		}
    	}
    	if (sb.length() > 0) {
    		return sb.toString().substring(1);
    	} else {
    		return sb.toString();
    	}
    }

    /**
     * Return the parameter definitions.
     * @param nodes the node of parameters.
     * @return the parameter definitions.
     */
    public static String parameterizeDefs(NodeList nodes) {
    	// check...
    	if (nodes.getLength() <= 0) {
    		return null;
    	}
    	Node parentNode = nodes.item(0); // parameters
    	NodeList childNodes = parentNode.getChildNodes(); // parameter
    	if (childNodes == null || childNodes.getLength() <= 0) {
    		return null;
    	}

    	// create name lists
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < childNodes.getLength(); i++) {
    		Node node = childNodes.item(i);
    		if (! XML_ELEM_PARAMETER.equals(node.getNodeName())) {
    			continue;
    		}
    		NamedNodeMap attrMap = node.getAttributes();
    		for (int j = 0; j < attrMap.getLength(); j++) {
    			Node attr = attrMap.item(j);
    			if (DATA_ATTRIBUTE_NAME.equals(attr.getNodeName())) {
    				sb.append(ParameterItem.SEPARATOR + attr.getNodeValue());
    				break;
    			}
    		}
    	}
    	
    	// for each parameters
    	for (int i = 0; i < childNodes.getLength(); i++) {
    		Node node = childNodes.item(i);
    		if (! XML_ELEM_PARAMETER.equals(node.getNodeName())) {
    			continue;
    		}
    		
    		// get name
        	ArrayList<Node>attrList = new ArrayList<Node>();
    		NamedNodeMap attrMap = node.getAttributes();
    		for (int j = 0; j < attrMap.getLength(); j++) {
    			Node attr = attrMap.item(j);
    			if (DATA_ATTRIBUTE_NAME.equals(attr.getNodeName())) {
    				attrList.add(attr);
    				break;
    			}
    		}
    		// get type
    		for (int j = 0; j < attrMap.getLength(); j++) {
    			Node attr = attrMap.item(j);
    			if (DATA_ATTRIBUTE_TYPE.equals(attr.getNodeName())) {
    				attrList.add(attr);
    				break;
    			}
    		}
    		// get others
    		for (int j = 0; j < attrMap.getLength(); j++) {
    			Node attr = attrMap.item(j);
    			if (! DATA_ATTRIBUTE_NAME.equals(attr.getNodeName()) &&
    					! DATA_ATTRIBUTE_TYPE.equals(attr.getNodeName()) &&
    					! DATA_ATTRIBUTE_VALUE.equals(attr.getNodeName())) {
    				attrList.add(attr);
    			}
    		}
    		// convert to String
    		sb.append(";"); //$NON-NLS-1$
    		String sep = ""; //$NON-NLS-1$
    		for (Node attr : attrList) {
    			sb.append(sep + attr.getNodeName() + "=" + attr.getNodeValue()); //$NON-NLS-1$
    			sep = ParameterItem.SEPARATOR;
    		}
    	}
    	if (sb.length() > 0) {
    		return sb.toString().substring(1);
    	} else {
    		return sb.toString();
    	}
    }
}
