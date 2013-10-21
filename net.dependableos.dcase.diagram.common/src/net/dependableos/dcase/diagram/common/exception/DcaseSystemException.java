/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.exception;

import net.dependableos.dcase.diagram.common.util.IMessageType;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;

/**
 * A system exception.
 */
public class DcaseSystemException extends RuntimeException {

    /**
     * serialVersion.
     */
    private static final long serialVersionUID = 3108302042846782127L;
 
    /**
     * the message type.
     */
    private IMessageType messageType;

    /**
     * Allocates a DcaseSystemException object and initialized it to represents the undefined exception.
     */
    public DcaseSystemException() {
        this(null, null, MessageTypeImpl.UNDEFINED);
    }

    /**
     * Allocates a DcaseSystemException object and initialize it to represents
     *  the message,cause,and the message type.
     * 
     * @param message the message.
     * @param cause the cause.
     * @param messageType the message type.
     */
    public DcaseSystemException(String message, Throwable cause,
            IMessageType messageType) {

        super(message, cause);
        setMessageType(messageType);
    }

    /**
     * Returns the message type.
     * 
     * @return the message type.
     */
    public IMessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the message type.
     * 
     * @param messageType the message type.
     */
    public void setMessageType(IMessageType messageType) {
        this.messageType = messageType;
    }
}
