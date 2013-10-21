/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.exception;


import net.dependableos.dcase.diagram.common.util.IMessageType;

import org.eclipse.core.resources.IFile;

/**
 * An exception that can be thrown during normal operation of D-Case Editor.
 */
public class DcaseRuntimeException extends RuntimeException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1282935660160582865L;

    /**
     * the resource which the exception occurred.
     */
    private IFile resource;

    /**
     * the line number which the exception occurred.0 if N/A.
     */
    private int lineNumber;

    /**
     * the message type.
     */
    private IMessageType messageType;

    /**
     * Allocates a DcaseRuntimeException object and initialize it to represents
     *  the message,cause,resource,line number,and the message type.
     * 
     * @param message the message.
     * @param cause the cause
     * @param resource the resource which the exception occurred.this can be null.
     * @param lineNumber the line number which the exception occurred.
     * @param messageType the message type
     */
    public DcaseRuntimeException(String message, Throwable cause,
            IFile resource, int lineNumber, IMessageType messageType) {

        super(message, cause);
        setResource(resource);
        setLineNumber(lineNumber);
        setMessageType(messageType);
    }

    /**
     * Returns the resource which the exception occurred.
     * 
     * @return the resource which the exception occurred.
     */
    public IFile getResource() {
        return resource;
    }

    /**
     * Sets the resource which the exception occurred.
     * 
     * @param resource the resource which the exception occurred.
     */
    public void setResource(IFile resource) {
        this.resource = resource;
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

    /**
     * Returns the line number which the exception occurred.
     * 
     * @return the line number which the exception occurred.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the line number which the exception occurred.
     * 
     * @param lineNumber the line number which the exception occurred.
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
