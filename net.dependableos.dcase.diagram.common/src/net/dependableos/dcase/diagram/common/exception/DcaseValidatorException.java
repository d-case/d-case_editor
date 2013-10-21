/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.exception;


import net.dependableos.dcase.diagram.common.util.IMessageType;

import org.eclipse.core.resources.IFile;

/**
 * An exception that can be thrown during validating.
 */
public class DcaseValidatorException extends DcaseRuntimeException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Allocates a DcaseValidatorException object and initialize it to represents
     *  the message,cause,resource,line number,and the message type.
     * 
     * @param message the message.
     * @param cause the cause
     * @param resource the resource which the exception occurred.
     * @param lineNumber the line number which the exception occurred.
     * @param messageType the message type
     */
    public DcaseValidatorException(String message, Throwable cause,
            IFile resource, int lineNumber, IMessageType messageType) {
        super(message, cause, resource, lineNumber, messageType);
    }

    /**
     * Allocates a DcaseValidatorException object and initialize it to represents
     *  the message and the message type.
     * 
     * @param message the message.
     * @param messageType the message type
     */
    public DcaseValidatorException(String message, IMessageType messageType) {
        this(message, null, null, 0, messageType);
    }
}
