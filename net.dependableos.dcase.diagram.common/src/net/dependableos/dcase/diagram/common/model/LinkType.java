/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.model;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.BASIC_LINK_NAME;
import static net.dependableos.dcase.diagram.common.util.ModelUtil.STRING_EMPTY;

import java.util.HashMap;
import java.util.Map;

/**
 * A enumeration that represents a type of the link.
 */
public enum LinkType {

    /** The BasicLink. */
    BASIC_LINK(BASIC_LINK_NAME);

    /**
     * the type name.
     */
    private String name = null;

    /**
     * the map of attributes with default value.
     */
    private Map<AttributeType, Object> attributeInitValueMap = null;

    /**
     * Creates the map of attributes with default value.
     * 
     * @param name the string that represents type name.
     * @return the map of attributes with default value.
     */
    private static Map<AttributeType, Object> createAttributeInitValueMap(
            String name) {

        Map<AttributeType, Object> initValueMap = null;

        // in the case of the BasicLink.
        if (BASIC_LINK_NAME.equals(name)) {
            initValueMap = new HashMap<AttributeType, Object>();
            initValueMap.put(AttributeType.ID, null);
            initValueMap.put(AttributeType.DESC, STRING_EMPTY);
            initValueMap.put(AttributeType.ATTACHMENT, STRING_EMPTY);
            initValueMap.put(AttributeType.SOURCE, null);
            initValueMap.put(AttributeType.TARGET, null);
            initValueMap.put(AttributeType.NAME, STRING_EMPTY);
            initValueMap.put(AttributeType.STATUS, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF001, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF002, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF003, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF004, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF005, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF006, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF007, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF008, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF009, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF010, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF011, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF012, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF013, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF014, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF015, STRING_EMPTY);
            initValueMap.put(AttributeType.USERDEF016, STRING_EMPTY);
        }

        return initValueMap;
    }

    /**
     * Allocates a LinkType object and initialized it to represent to the type name.
     * 
     * @param name the string that represents type name.
     */
    private LinkType(String name) {
        this.name = name;
        this.attributeInitValueMap = createAttributeInitValueMap(name);
    }

    /**
     * Returns the type name.
     * 
     * @return  the string that represents type name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the map of attributes with default value.
     * 
     * @return the map of attributes with default value.
     */
    public Map<AttributeType, Object> getAttributeInitValueMap() {
        return attributeInitValueMap;
    }
}
