/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.LinkInfo;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.impl.RequirementItem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * A class that manages nodes and links.
 */
public class LinkManager {
    
    /**
     * A XMLResource.
     */
    private XMLResource xmlResource;

    /**
     * the map that manages suites of a node ID and its source node IDs.
     */
    private Map<String, List<String>> sourceHashMap;

    /**
     * the map that manages suites of a node ID and its target node IDs.
     */
    private Map<String, List<String>> targetHashMap;

    /**
     * the map that manages suites of a node ID and its BasicNode object.
     */
    private Map<String, BasicNode> nodeHashMap;

    /**
     * the map that manages suites of a node ID and its BasicNode object.
     */
    private Map<String, NodeInfo> nodeMap;

    /**
     * the map that manages suites of a link ID and its LinkInfo object.
     */
    private Map<String, LinkInfo> linkMap;
    
    /**
     * the requirements.
     */
    private List<RequirementItem> requirements;
    
    /**
     * Loads the D-Case XMLResource and initializes this object.
     * 
     * @param xmlResource the D-Case XMLResource.
     */
    public void load(XMLResource xmlResource) {

        this.xmlResource = xmlResource;

        //clears the maps.
        sourceHashMap = new HashMap<String, List<String>>(
                COLLECTION_INITIAL_CAPACITY);
        targetHashMap = new HashMap<String, List<String>>(
                COLLECTION_INITIAL_CAPACITY);
        nodeHashMap = new HashMap<String, BasicNode>(
                COLLECTION_INITIAL_CAPACITY);
        nodeMap = new HashMap<String, NodeInfo>(COLLECTION_INITIAL_CAPACITY);
        linkMap = new HashMap<String, LinkInfo>(COLLECTION_INITIAL_CAPACITY);

        // gets the argument.
        Argument argument = null;
        EList<EObject> contentList = xmlResource.getContents();
        for (EObject content : contentList) {
            if (content instanceof Argument) {
                argument = (Argument) content;
                break;
            }
        }

        // failed to get the argument.
        if (argument == null) {
            throw new DcaseSystemException(Messages.LinkManager_0, null,
                    MessageTypeImpl.DATA_STRUCTURE_ERROR);
        }
        
        // puts the argument to the maps.
        String argumentId = getId(argument);
        nodeHashMap.put(argumentId, argument);
        nodeMap.put(argumentId, ModelUtil.createNodeInfo(argument));

        // puts the nodes to the maps.
        EList<BasicNode> nodeList = argument.getRootBasicNode();
        List<NodeInfo> goals = new ArrayList<NodeInfo>();
        requirements = new ArrayList<RequirementItem>();
        for (BasicNode node : nodeList) {
            String id = getId(node);
            nodeHashMap.put(id, node);
            NodeInfo nodeInfo = ModelUtil.createNodeInfo(node);
            nodeMap.put(id, nodeInfo);
            if (nodeInfo.getNodeType() == NodeType.CONTEXT) {
                Object contextRequriements = nodeInfo
                        .getAttribute(AttributeType.USERDEF003);
                if (contextRequriements != null
                        && contextRequriements instanceof List<?>) {
                    requirements
                            .addAll((List<RequirementItem>) contextRequriements);
                }
            } else if (nodeInfo.getNodeType() == NodeType.GOAL) {
                Object goalRequriement = nodeInfo
                        .getAttribute(AttributeType.USERDEF003);
                if (goalRequriement != null
                        && goalRequriement.toString().length() > 0) {
                    goals.add(nodeInfo);
                }
            }
        }

        for (RequirementItem requirement : requirements) {
            for (NodeInfo goal : goals) {
                if (requirement.getFullId().equals(
                        goal.getAttribute(AttributeType.USERDEF003).toString())) {
                    goal.setAttribute(AttributeType.USERDEF003, requirement);
                }
            }
        }

        // puts the links to the maps.
        EList<BasicLink> linkList = argument.getRootBasicLink();
        for (BasicLink link : linkList) {
            String id = getId(link);
            String sourceId = getId(link.getSource());
            String targetId = getId(link.getTarget());

            // puts the links to the maps.
            linkMap.put(id, ModelUtil.createLinkInfo(link));

            // puts the targets to the maps.
            List<String> sourceList = sourceHashMap.get(targetId);
            if (sourceList == null) {
                sourceList = new ArrayList<String>();
            }
            sourceList.add(sourceId);
            sourceHashMap.put(targetId, sourceList);

            // puts the sources to the maps.
            List<String> targetList = targetHashMap.get(sourceId);
            if (targetList == null) {
                targetList = new ArrayList<String>();
            }
            targetList.add(targetId);
            targetHashMap.put(sourceId, targetList);
        }
    }

    /**
     * @return the requirements
     */
    public List<RequirementItem> getRequirements() {
        return requirements;
    }

    /**
     * Gets the list of the node IDs those link to the node that is represented the specified ID.
     * 
     * @param id the target node ID.
     * @return the list of the source node IDs.
     */
    public List<String> getSource(String id) {
       return sourceHashMap.get(id);
    }

    /**
     * Gets the list of the node IDs those link from the node that is represented the specified ID.
     * 
     * @param id the source node ID.
     * @return the list of the target node IDs.
     */
    public List<String> getTarget(String id) {
        return targetHashMap.get(id);
    }

    /**
     * Returns the list of IDs of all nodes.
     * 
     * @return the list of IDs of all nodes.
     */
    public Set<String> getAllNodes() {
        return nodeHashMap.keySet();
    }
    
    /**
     * Returns the list of IDs of all links.
     * 
     * @return the list of IDs of all links.
     */
    public Set<String> getAllLinks() {
        return linkMap.keySet();
    }

    /**
     * Returns the ID of the specified node.
     * 
     * @param node the node.
     * @return the ID.
     */
    public String getId(EObject node) {
        return xmlResource.getID(node);
    }

    /**
     * Returns the BasicNode object that is represented the specified ID.
     * 
     * @param id the ID.
     * @return the node.
     */
    public BasicNode getBasicNode(String id) {
        return nodeHashMap.get(id);
    }

    /**
     * Returns the NodeInfo object that is represented the specified ID.
     * 
     * @param id the ID.
     * @return the node.
     */
    public NodeInfo getNodeInfo(String id) {
        return nodeMap.get(id);
    }

    /**
     * Returns the LinkInfo object that is represented the specified ID.
     * 
     * @param id the ID.
     * @return the link.
     */
    public LinkInfo getLinkInfo(String id) {
        return linkMap.get(id);
    }
    
    /**
     * Returns the URI of this resource. 
     * 
     * @return the URI of this resource. 
     */
    public URI getUri() {
        return xmlResource.getURI();
    }
}
