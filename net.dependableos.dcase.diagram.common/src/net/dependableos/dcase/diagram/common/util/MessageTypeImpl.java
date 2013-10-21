/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

/**
 * An enumeration that defines message types.
 */
public enum MessageTypeImpl implements IMessageType {

    /**
     * Diagnosis.
     */
    DIAGNOSIS(MessageLevel.VERBOSE, FunctionTypeImpl.NON_FUNCTION),
    /**
     * Undefined.
     */
    UNDEFINED(MessageLevel.CRITICAL, FunctionTypeImpl.NON_FUNCTION),
    /**
     * Failed to calculate score.
     */
    CALCULATE_SCORE_FAILD(MessageLevel.ERROR, FunctionTypeImpl.CALCULATE_SCORE),
    /**
     * Failed to initialize templates.
     */
    TEMPLATE_INIT_FAILED(MessageLevel.CRITICAL, FunctionTypeImpl.TEMPLATE),
    /**
     * Failed to add elements from templates.
     */
    TEMPLATE_INSERT_OPERATION_FAILED(MessageLevel.ERROR, FunctionTypeImpl.TEMPLATE),
    /**
     * An internal error while adding elements from templates.
     */
    TEMPLATE_INSERT_INTERNAL_ERROR(MessageLevel.CRITICAL, FunctionTypeImpl.TEMPLATE),
    /**
     * Failed to open a URL.
     */
    OPEN_URL_INVALID_URL(MessageLevel.ERROR, FunctionTypeImpl.OPEN_URL),
    /**
     * A critical error while opening a URL.
     */
    OPEN_URL_CRITICAL_FAILED(MessageLevel.CRITICAL, FunctionTypeImpl.OPEN_URL),
    /**
     * Failed to open an attachment.
     */
    OPEN_ATTACHMENT_INVALID_FILE(MessageLevel.ERROR, FunctionTypeImpl.OPEN_ATTACHMENT),
    /**
     * A critical error while opening an attachment.
     */
    OPEN_ATTACHMENT_CRITICAL_FAILED(MessageLevel.CRITICAL, FunctionTypeImpl.OPEN_ATTACHMENT),
    /**
     * A critical error while opening an attachment.
     */
    OPEN_PARAMETER_DATA_FAILED(MessageLevel.CRITICAL, FunctionTypeImpl.OPEN_PARAMETER_DATA),
    /**
     * A critical error while opening an attachment.
     */
    OPEN_BOOKMARK_INVALID_FAILED(MessageLevel.ERROR, FunctionTypeImpl.OPEN_BOOKMARK_DATA),
    /**
     * Failed to hide children.
     */
    HIDE_CHILDREN_HIDE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.HIDE_CHILDREN),
    /**
     * Failed to convert to D-Case model.
     */
    CONVERT_TO_DCASE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CONVERT_TO_DCASE),
    /**
     * Failed to convert to GMF model.
     */
    CONVERT_TO_GMF_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CONVERT_TO_GMF),
    /**
     * Failed to convert to ARM.
     */
    CONVERT_TO_ARM_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CONVERT_TO_ARM),
    /**
     * Failed to convert to Text.
     */
    CONVERT_TO_TEXT_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CONVERT_TO_TEXT),
    /**
     * Invalid data structure is detected.
     */
    DATA_STRUCTURE_ERROR(MessageLevel.ERROR, FunctionTypeImpl.NON_FUNCTION),
    /**
     * Invalid data is detected.
     */
    VALIDATION_ERROR(MessageLevel.ERROR, FunctionTypeImpl.VALIDATION),
    /**
     * Failed to initialize complement.
     */
    COMPLEMENT_INIT_FAILED(MessageLevel.CRITICAL, FunctionTypeImpl.COMPLEMENT),
    /**
     * Failed to copy to EPS.
     */
    COPY_TO_EPS_FAILED(MessageLevel.ERROR, FunctionTypeImpl.COPY_TO_EPS),
    /**
     * Failed to update reference to the model file.
     */
    UPDATE_MODEL_FILE_REFERENCE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.UPDATE_MODEL_FILE_REFERENCE),
    /**
     * Failed to rename a file.
     */
    RENAME_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.RENAME_FILE),
    /**
     * Failed to copy a file.
     */
    COPY_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.COPY_FILE),
    /**
     * Failed to copy a file.
     */
    EDIT_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.EDIT_FILE),
    /**
     * Failed to get a model.
     */
    GET_MODEL_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.GET_MODEL_FILE),
    /**
     * Failed to compare models.
     */
    COMPARE_MODEL_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.COMPARE_TO),
    
    /**
     * A result of comparing diagrams.
     */
    WRITE_COMPARE_INFO(MessageLevel.INFORMATION, FunctionTypeImpl.COMPARE_TO),
    
    /**
     * Parameter data type file not found.
     */
    PARAMETER_DATA_TYPE_NOT_FOUND(MessageLevel.WARNING,
            FunctionTypeImpl.PARAMETER_CONFIG),
    /**
     * Xsl transform failed.
     */
    TRANSFORM_FROM_GMF_FAILED(MessageLevel.ERROR, FunctionTypeImpl.TRANSFORM_FROM_GMF),
    
    /**
     * Failed to create the module.
     */
    MODULE_FILE_CREATE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CREATE_MODULE),
    
    /**
     * Failed to get module informations.
     */
    MODULE_INFO_GET_FAILED(MessageLevel.ERROR, FunctionTypeImpl.MODULE_INFO),

    /**
     * Failed to open module file.
     */
    OPEN_MODULE_FILE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.OPEN_DIAGRAM),

    /**
     * Failed to restore module.
     */
    RESTORE_MODULE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.RESTORE_MODULE),

    /**
     * Failed to select module.
     */
    SELECT_MODULE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.SELECT_MODULE),

    /**
     * Failed to show/hide module.
     */
    SHOW_MODULE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.SHOW_MODULE),

    /**
     * Failed to delete module.
     */
    DELETE_MODULE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.DELETE_MODULE),

    /**
     * Failed to delete module.
     */
    CREATE_PATTERN_FAILED(MessageLevel.ERROR, FunctionTypeImpl.CREATE_PATTERN),
    
    /**
     * Failed to adjust reference.
     */
    ADJUST_REFERENCE_FAILED(MessageLevel.ERROR, FunctionTypeImpl.ADJUST_REFERENCE);

    /**
     * the message level.
     */
    private MessageLevel messageLevel = MessageLevel.INFORMATION;

    /**
     * the function type.
     */
    private IFunctionType functionType = FunctionTypeImpl.NON_FUNCTION;

    
    /**
     * Allocates a MessageTypeImple object and initializes it to represent
     *  the specified message level and function type.
     * 
     * @param messageLevel the message level.
     * @param functionType the message type.
     */
    private MessageTypeImpl(MessageLevel messageLevel, IFunctionType functionType) {
        this.messageLevel = messageLevel;
        this.functionType = functionType;
    }

    /**
     * Returns the message level.
     * 
     * @return the message level.
     */
    public MessageLevel getMessageLevel() {
        return messageLevel;
    }

    /**
     * Returns the function type.
     * 
     * @return the function type.
     */
    public IFunctionType getFunctionType() {
        return functionType;
    }
}
