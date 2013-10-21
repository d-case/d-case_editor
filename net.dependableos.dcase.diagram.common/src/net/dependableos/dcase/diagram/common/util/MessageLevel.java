/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

/**
 * A enumeration of the message level.
 */
public enum MessageLevel {

    /**
     * Debug.
     */
    VERBOSE("VERBOSE"),

    /**
     * Information.
     */
    INFORMATION("INFORMATION"),

    /**
     * Warning.
     */
    WARNING("WARNING"),

    /**
     * Error.
     */
    ERROR("ERROR"),

    /**
     * Critical.
     */
    CRITICAL("CRITICAL");

    /**
     * the name of the message type.
     */
    private String name = "";

    /**
     * Allocates the MessageLevel objects and initializes it to represents the specified name of the message type..
     * 
     * @param name the name of the message type..
     */
    private MessageLevel(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the message type.
     * 
     * @return the name of the message type.
     */
    public String getName() {
        return name;
    }
    
}
