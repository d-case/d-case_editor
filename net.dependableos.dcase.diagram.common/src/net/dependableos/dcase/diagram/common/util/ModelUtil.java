/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.LinkInfo;
import net.dependableos.dcase.diagram.common.model.LinkType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.Context;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A utility class with methods to handle the GMF model.
 */
public final class ModelUtil {

    /**
     * The empty string.
     */
    public static final String STRING_EMPTY = ""; //$NON-NLS-1$

    /**
     * The integer that represents 1.
     */
    public static final Integer INTEGER_ONE = Integer.valueOf(1);

    /**
     * the string that represents the root of the workspace.
     */
    private static final String PLATFORM = "platform:/resource"; //$NON-NLS-1$

    /**
     * the xpath to elements.
     */
    private static final String EXPRESSION = "//element"; //$NON-NLS-1$

    /**
     * the XML attribute of reference to a model file.
     */
    private static final String ATTRIBUTE_HREF = "href"; //$NON-NLS-1$

    /**
     * the encoding output property.
     */
    private static final String ENCODING = "encoding"; //$NON-NLS-1$

    /**
     * the separator between a model file and an element .
     */
    private static final String SHARP_STRING = "#"; //$NON-NLS-1$

    /**
     * A constractor.
     */
    private ModelUtil() {
    }

    /**
     * Returns the duplicated object.
     * This throws an exception if the type of the object is unsupported.
     * 
     * @param <T> the type of the object.
     * @param originalObject the object.
     * @return the duplicated object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T duplicateBasicObject(T originalObject) {

        if (originalObject == null) {
            return null;
        }

        // String
        if (originalObject instanceof String) {
            // substituting string value means copying.
            return originalObject;
        }

        // Integer
        if (originalObject instanceof Integer) {
            return (T) Integer.valueOf(((Integer) originalObject).intValue());
        }

        // BigDecimal
        if (originalObject instanceof BigDecimal) {
            return (T) new BigDecimal(((BigDecimal) originalObject).toString());
        }

        // Boolean
        if (originalObject instanceof Boolean) {
            return (T) Boolean.valueOf(((Boolean) originalObject)
                    .booleanValue());
        }

        // Unsupported
        throw new DcaseSystemException(Messages.ModelUtil_0, null,
                MessageTypeImpl.UNDEFINED);
    }

    /**
     * Returns the duplicated map.
     * This throws an exception if the type of the map is unsupported.
     * 
     * @param <K> the type of the key.
     * @param <V> the type of the value.
     * @param originalMap the map.
     * @return the duplicated map.
     */
    public static <K, V> Map<K, V> duplicateMap(Map<K, V> originalMap) {

        if (originalMap == null) {
            return null;
        }

        // HashMap
        if (originalMap.getClass().equals(HashMap.class)) {
            HashMap<K, V> duplicateMap = new HashMap<K, V>();
            for (Map.Entry<K, V> attribute : originalMap.entrySet()) {
                V value = attribute.getValue();
                V newValue = duplicateBasicObject(value);
                duplicateMap.put(attribute.getKey(), newValue);
            }
            return duplicateMap;
        }

        // Unsupported
        throw new DcaseSystemException(Messages.ModelUtil_1, null,
                MessageTypeImpl.UNDEFINED);
    }

    /**
     * Returns the duplicated list.
     * This throws an exception if the type of the list is unsupported.
     * 
     * @param <T> the type of the elements.
     * @param originalList the list.
     * @return the duplicated list.
     */
    public static <T> List<T> duplicateList(List<T> originalList) {

        if (originalList == null) {
            return null;
        }

        // ArrayList
        if (originalList.getClass().equals(ArrayList.class)) {
            List<T> duplicateList = new ArrayList<T>();
            for (T element : originalList) {
                T newValue = duplicateBasicObject(element);
                duplicateList.add(newValue);
            }
            return duplicateList;
        }

        // Unsupported
        throw new DcaseSystemException(Messages.ModelUtil_2, null,
                MessageTypeImpl.UNDEFINED);
    }

    /**
     * Creates a NodeInfo object that represents the specified BasicNode object.
     * 
     * @param basicNode a BasicNode object.
     * @return a NodeInfo object.
     */
    public static NodeInfo createNodeInfo(BasicNode basicNode) {

        if (basicNode == null) {
            return null;
        }

        XMLResource xmlResource = (XMLResource) basicNode.eResource();
        String id = xmlResource.getID(basicNode);

        NodeInfo nodeInfo = new NodeInfo(NodeType.getNodeType(basicNode));

        // sets the common attributes.
        nodeInfo.setAttribute(AttributeType.ID, id);
        nodeInfo.setAttribute(AttributeType.NAME, basicNode.getName());
        nodeInfo.setAttribute(AttributeType.DESC, basicNode.getDesc());
        nodeInfo.setAttribute(AttributeType.ATTACHMENT, basicNode
                .getAttachment());
        nodeInfo.setAttribute(AttributeType.STATUS, basicNode.getStatus());
        nodeInfo.setAttribute(AttributeType.USERDEF001, basicNode
                .getUserdef001());
        nodeInfo.setAttribute(AttributeType.USERDEF002, basicNode
                .getUserdef002());
        nodeInfo.setAttribute(AttributeType.USERDEF003, basicNode
                .getUserdef003());
        nodeInfo.setAttribute(AttributeType.USERDEF004, basicNode
                .getUserdef004());
        nodeInfo.setAttribute(AttributeType.USERDEF005, basicNode
                .getUserdef005());
        nodeInfo.setAttribute(AttributeType.USERDEF006, basicNode
                .getUserdef006());
        nodeInfo.setAttribute(AttributeType.USERDEF007, basicNode
                .getUserdef007());
        nodeInfo.setAttribute(AttributeType.USERDEF008, basicNode
                .getUserdef008());
        nodeInfo.setAttribute(AttributeType.USERDEF009, basicNode
                .getUserdef009());
        nodeInfo.setAttribute(AttributeType.USERDEF010, basicNode
                .getUserdef010());
        nodeInfo.setAttribute(AttributeType.USERDEF011, basicNode
                .getUserdef011());
        nodeInfo.setAttribute(AttributeType.USERDEF012, basicNode
                .getUserdef012());
        nodeInfo.setAttribute(AttributeType.USERDEF013, basicNode
                .getUserdef013());
        nodeInfo.setAttribute(AttributeType.USERDEF014, basicNode
                .getUserdef014());
        nodeInfo.setAttribute(AttributeType.USERDEF015, basicNode
                .getUserdef015());
        nodeInfo.setAttribute(AttributeType.USERDEF016, basicNode
                .getUserdef016());

        // sets the proprietary attributes.
        if (basicNode instanceof Goal) {
            Goal goalNode = (Goal) basicNode;
            nodeInfo.setAttribute(AttributeType.SCORE, goalNode.getScore());
            nodeInfo.setAttribute(AttributeType.WEIGHT, goalNode.getWeight());
        } else if (basicNode instanceof Monitor) {
            Monitor monitorNode = (Monitor) basicNode;
            nodeInfo.setAttribute(AttributeType.IS_NORMAL, monitorNode
                    .isIsNormal());
        } else if (basicNode instanceof Justification) {
            Justification justificationNode = (Justification) basicNode;
            nodeInfo.setAttribute(AttributeType.STAKEHOLDER, justificationNode
                    .getStakeholder());
            nodeInfo.setAttribute(AttributeType.RISK_ANALYSIS,
                    justificationNode.getRiskAnalysis());
        } else if (basicNode instanceof net.dependableos.dcase.System) {
            net.dependableos.dcase.System systemNode = (net.dependableos.dcase.System) basicNode;
            nodeInfo.setAttribute(AttributeType.SCORE, systemNode.getScore());
            nodeInfo.setAttribute(AttributeType.WEIGHT, systemNode.getWeight());
            nodeInfo.setAttribute(AttributeType.NODE_LINK, systemNode
                    .getNodeLink());
        } else if (basicNode instanceof net.dependableos.dcase.Context) {
            nodeInfo.setAttribute(AttributeType.USERDEF003,
                    ((Context) basicNode).getRequirements());
        }

        return nodeInfo;
    }

    /**
     * Returns a LinkInfo object that represents the specified BasicLink.
     * 
     * @param basicLink a BasicLink object.
     * @return a LinkInfo object.
     */
    public static LinkInfo createLinkInfo(BasicLink basicLink) {

        if (basicLink == null) {
            return null;
        }

        XMLResource xmlResource = (XMLResource) basicLink.eResource();
        String id = xmlResource.getID(basicLink);
        String sourceId = xmlResource.getID(basicLink.getSource());
        String targetId = xmlResource.getID(basicLink.getTarget());

        LinkInfo linkInfo = new LinkInfo(LinkType.BASIC_LINK);

        // sets the common attributes.
        linkInfo.setAttribute(AttributeType.ID, id);
        linkInfo.setAttribute(AttributeType.DESC, basicLink.getDesc());
        linkInfo.setAttribute(AttributeType.ATTACHMENT, basicLink
                .getAttachment());
        linkInfo.setAttribute(AttributeType.SOURCE, sourceId);
        linkInfo.setAttribute(AttributeType.TARGET, targetId);
        linkInfo.setAttribute(AttributeType.NAME, basicLink.getName());
        linkInfo.setAttribute(AttributeType.STATUS, basicLink.getStatus());
        linkInfo.setAttribute(AttributeType.USERDEF001, basicLink
                .getUserdef001());
        linkInfo.setAttribute(AttributeType.USERDEF002, basicLink
                .getUserdef002());
        linkInfo.setAttribute(AttributeType.USERDEF003, basicLink
                .getUserdef003());
        linkInfo.setAttribute(AttributeType.USERDEF004, basicLink
                .getUserdef004());
        linkInfo.setAttribute(AttributeType.USERDEF005, basicLink
                .getUserdef005());
        linkInfo.setAttribute(AttributeType.USERDEF006, basicLink
                .getUserdef006());
        linkInfo.setAttribute(AttributeType.USERDEF007, basicLink
                .getUserdef007());
        linkInfo.setAttribute(AttributeType.USERDEF008, basicLink
                .getUserdef008());
        linkInfo.setAttribute(AttributeType.USERDEF009, basicLink
                .getUserdef009());
        linkInfo.setAttribute(AttributeType.USERDEF010, basicLink
                .getUserdef010());
        linkInfo.setAttribute(AttributeType.USERDEF011, basicLink
                .getUserdef011());
        linkInfo.setAttribute(AttributeType.USERDEF012, basicLink
                .getUserdef012());
        linkInfo.setAttribute(AttributeType.USERDEF013, basicLink
                .getUserdef013());
        linkInfo.setAttribute(AttributeType.USERDEF014, basicLink
                .getUserdef014());
        linkInfo.setAttribute(AttributeType.USERDEF015, basicLink
                .getUserdef015());
        linkInfo.setAttribute(AttributeType.USERDEF016, basicLink
                .getUserdef016());

        return linkInfo;
    }

    /**
     * Returns the EObject from the model file.
     * 
     * @param modelFile the model file.
     * @return the EObject
     */
    public static EObject getModel(IFile modelFile) {

        // creates URI.
        URI modelURI = URI.createPlatformResourceURI(modelFile.getFullPath()
                .toString(), true);

        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                .createEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        EObject diagramRoot = null;
        try {
            // gets the model.
            Resource resource = resourceSet.getResource(modelURI, true);
            diagramRoot = (EObject) resource.getContents().get(0);
        } catch (WrappedException we) {
            throw new DcaseSystemException(
                    Messages.TemplateModelAdditionAction_4, we,
                    MessageTypeImpl.TEMPLATE_INSERT_INTERNAL_ERROR);
        }

        return diagramRoot;
    }

    /**
     * Returns the file in the relative path.
     * 
     * @param base the base path.
     * @param modelPath relative path to the file.
     * @return the file.
     */
    private static IFile getFileInRelativePath(IPath base, String modelPath) {
        IFile result = null;
        // in the case of different project.
        if (modelPath.startsWith(PLATFORM)) {
            modelPath = modelPath.replaceAll(PLATFORM, ""); //$NON-NLS-1$
            result = ResourcesPlugin.getWorkspace().getRoot().getFile(
                    new Path(modelPath));
            return result;
        }
        // in the case of same project.
        IPath path = base.append(modelPath);
        result = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

        return result;
    }

    /**
     * Returns the GMF model file that is referred from the GMF diagram file.
     * 
     * @param diagramFile the GMF diagram file.
     * @return the GMF model file.
     */
    public static IFile getModelFileFromDiagramFile(IFile diagramFile) {
        if (diagramFile == null) {
            return null;
        }
        IFile result = null;
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder;

            builder = domFactory.newDocumentBuilder();
            // parses the XML.
            Document doc = builder.parse(diagramFile.getLocation().toFile());

            // creates a XPathFactory.
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(EXPRESSION);

            Object nodeListObj = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) nodeListObj;

            //gets the name of the model file.
            String modelFilename = null;
            for (int i = 0; i < nodeList.getLength(); i++) {
                NamedNodeMap maps = nodeList.item(i).getAttributes();
                Node node = maps.getNamedItem(ATTRIBUTE_HREF);
                if (node != null) {
                    modelFilename = node.getNodeValue();
                    break;
                }
            }
            if (modelFilename != null) {
                int index = modelFilename.indexOf(SHARP_STRING);
                modelFilename = modelFilename.substring(0, index);

                //gets the model file.
                result = getFileInRelativePath(diagramFile.getFullPath()
                        .removeLastSegments(1), modelFilename);
            }
        } catch (ParserConfigurationException e) {
            throw new DcaseSystemException(Messages.ModelUtil_4, e,
                    MessageTypeImpl.GET_MODEL_FILE_FAILED);
        } catch (SAXException e) {
            throw new DcaseSystemException(Messages.ModelUtil_4, e,
                    MessageTypeImpl.GET_MODEL_FILE_FAILED);
        } catch (IOException e) {
            throw new DcaseSystemException(Messages.ModelUtil_4, e,
                    MessageTypeImpl.GET_MODEL_FILE_FAILED);
        } catch (XPathExpressionException e) {
            throw new DcaseSystemException(Messages.ModelUtil_4, e,
                    MessageTypeImpl.GET_MODEL_FILE_FAILED);
        }
        return result;
    }

    /**
     * Updates the reference to the GMF model file of the GMF diagram file.
     * 
     * @param diagramFile the source GMF diagram file.
     * @param modelFile the GMF model file
     * @param destFile the destination GMF diagram file.
     * @param deleteSourceDiagram true if delete the source; false otherwise.
     */
    public static void updateModelFileReference(IFile diagramFile,
            IFile modelFile, IFile destFile, boolean deleteSourceDiagram) {

        if (diagramFile == null || modelFile == null || destFile == null) {
            return;
        }
        // gets the relative path from the diagram file to the model file.
        IPath base = destFile.getFullPath().removeLastSegments(1);
        String modelFilename = getRelativePath(base, modelFile);
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            // parses XML.
            Document doc = builder.parse(diagramFile.getLocation().toFile());

            // creates a XPathFactory.
            XPathFactory factory = XPathFactory.newInstance();
            XPathExpression expr = factory.newXPath().compile(EXPRESSION);

            Object nodeListObj = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) nodeListObj;

            String baseAttribute = modelFilename.concat(SHARP_STRING);
            String attributeValue = null;

            // updates the reference to the model file.
            for (int i = 0; i < nodeList.getLength(); i++) {
                NamedNodeMap maps = nodeList.item(i).getAttributes();
                Node hrefAttribute = maps.getNamedItem(ATTRIBUTE_HREF);
                if (hrefAttribute == null) {
                    continue;
                }
                attributeValue = hrefAttribute.getNodeValue();
                int objIdIndex = attributeValue.indexOf(SHARP_STRING);
                // updates the value of the attribute.
                hrefAttribute.setNodeValue(baseAttribute.concat(attributeValue
                        .substring(objIdIndex + 1)));
            }

            // overrides the diagram file.
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf;
            tf = tff.newTransformer();
            tf.setOutputProperty(ENCODING, "UTF-8"); //$NON-NLS-1$

            tf.transform(new DOMSource(doc), new StreamResult(destFile
                    .getLocation().toOSString()));

            // deletes the original file.
            if (deleteSourceDiagram) {
                diagramFile.delete(true, null);
            }

            // refreshes.
            destFile.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (ParserConfigurationException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        } catch (SAXException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        } catch (IOException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        } catch (XPathExpressionException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        } catch (TransformerException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        } catch (CoreException e) {
            throw new DcaseSystemException(Messages.ModelUtil_3, e,
                    MessageTypeImpl.UPDATE_MODEL_FILE_REFERENCE_FAILED);
        }
    }

    /**
     * Returns the string that represents the relative path from the base to the file.
     * 
     * @param base the base.
     * @param file the file.
     * @return the string that represents the relative path.
     */
    private static String getRelativePath(IPath base, IFile file) {
        String result = null;
        String[] segs = base.segments();
        String[] companionSegs = file.getFullPath().segments();

        if (!segs[0].equals(companionSegs[0])) {
            // in the case of different project.
            result = PLATFORM.concat(file.getFullPath().toString());
        } else {
           IPath companionPath = file.getFullPath();
            // in the case of same project.
            result = companionPath.makeRelativeTo(base).toString();
        }
        return result;
    }
}
