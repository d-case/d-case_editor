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
public class LinkInfo {

    /**
     * the type.
     */
    private LinkType linkType;
    
    /**
     * the map of attributes.
     */
    private Map<AttributeType, Object> attributeMap = null;
    
    /**
     * Allocates a LinkInfo object and initializes it to represent the link type and the attributes with default values.
     * 
     * @param linkType the link type.
     */
    public LinkInfo(LinkType linkType) {
        this.linkType = linkType;

        // initialized
        if (linkType != null) {
            attributeMap = ModelUtil.duplicateMap(linkType
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
    public LinkType getLinkType() {
        return linkType;
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
