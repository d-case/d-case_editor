/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

/**
 * An interface that represents a message type.
 */
public interface IMessageType {
    /**
     * Returns the message level.
     * 
     * @return the message level.
     */
    MessageLevel getMessageLevel();

    /**
     * Returns the function type.
     * 
     * @return the function type.
     */
    IFunctionType getFunctionType();


}
