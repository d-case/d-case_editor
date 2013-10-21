/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.model;

import java.util.HashMap;
import java.util.Map;

import net.dependableos.dcase.diagram.common.util.ModelUtil;



/**
 * A Class that represents the link.
 */
public class NodeInfo {

    /**
     * the type.
     */
    private NodeType nodeType;
    
    /**
     * Allocates a NodeInfo object and initializes it to represent the link type and the attributes with default values.
     */
    private Map<AttributeType, Object> attributeMap = null;

    /**
     * Allocates a NodeInfo objects and initializes it to represents the specified node type.
     * 
     * @param nodeType the node type.
     */
    public NodeInfo(NodeType nodeType) {
        this.nodeType = nodeType;

        // initializes the attributes.
        if (nodeType != null) {
            attributeMap = ModelUtil.duplicateMap(nodeType
                    .getAttributeInitValueMap());
        }
        if (attributeMap == null) {
            attributeMap = new HashMap<AttributeType, Object>();
        }
    }

    /**
     * Returns the value of the specified attribute type.
     * 
     * @param attributeType the attribute type.
     * @return the value of the specified attribute type.
     */
    public Object getAttribute(AttributeType attributeType) {

        return attributeMap.get(attributeType);
    }

    /**
     * Sets the value of the specified attribute type.
     * 
     * @param attributeType the attribute type.
     * @param object the value of the specified attribute type.
     */
    public void setAttribute(AttributeType attributeType, Object object) {
        attributeMap.put(attributeType, object);
    }

    /**
     * Returns the type.
     * 
     * @return the type.
     */
    public NodeType getNodeType() {
        return nodeType;
    }

    /**
     * Returns the map of attributes.
     * 
     * @return the map of attributes.
     */
    public Map<AttributeType, Object> getAttributeMap() {
        return attributeMap;
    }

}
