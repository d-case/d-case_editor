/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst;

/**
 * A enumeration of the functions.
 */
public enum FunctionTypeImpl implements IFunctionType {

    /**
     * No function.
     */
    NON_FUNCTION(null),
    /**
     * Convert to D-Case XML.
     */
    CONVERT_TO_DCASE(null),
    /**
     * Convert to GMF XML.
     */
    CONVERT_TO_GMF(null),
    /**
     * Convert to ARM XML.
     */
    CONVERT_TO_ARM(null),
    /**
     * Convert to Text XML.
     */
    CONVERT_TO_TEXT(null),
    /**
     * Calculate the score.
     */
    CALCULATE_SCORE(SystemDefinitionConst.CALCULATE_SCORE_MARKER_ID),
    /**
     * Open the URL.
     */
    OPEN_URL(null),
    /**
     * Open the Attachment file.
     */
    OPEN_ATTACHMENT(null),
    /**
     * Open the Parameter file.
     */
    OPEN_PARAMETER_DATA(null),
    /**
     * Open the Bookmark data.
     */
    OPEN_BOOKMARK_DATA(null),
    /**
     * Select from template.
     */
    TEMPLATE(null),
    /**
     * Hide the children.
     */
    HIDE_CHILDREN(null),
    /**
     * Validation.
     */
    VALIDATION(null),

    /**
     * Complement.
     */
    COMPLEMENT(null),
    
    /**
     * Copy to the EPS.
     */
    COPY_TO_EPS(null),
    
    /**
     * Change the reference mode file.
     */
    UPDATE_MODEL_FILE_REFERENCE(null),
    
    /**
     * Rename the file.
     */
    RENAME_FILE(null),
    
    /**
     * Copy the file.
     */
    COPY_FILE(null),
    
    /**
     * Edit the file.
     */
    EDIT_FILE(null),
    
    /**
     * Retrieving the model file.
     */
    GET_MODEL_FILE(null),
    
    /**
     * Compare to.
     */
    COMPARE_TO(null),
    
    /**
     * Configure the parameters.
     */
    PARAMETER_CONFIG(null),

    /**
     * Xsl transform from Gmf.
     */
    TRANSFORM_FROM_GMF(null),
    
    /**
     * Create the module.
     */
    CREATE_MODULE(null),
    
    /**
     * Get module informations.
     */
    MODULE_INFO(null),
    
    /**
     * Open diagram file.
     */
    OPEN_DIAGRAM(null),

    /**
     * Restore the module.
     */
    RESTORE_MODULE(null),
    
    /**
     * Select the module.
     */
    SELECT_MODULE(null),

    /**
     * Show/Hide the module.
     */
    SHOW_MODULE(null),

    /**
     * Delete the module.
     */
    DELETE_MODULE(null),

    /**
     * Delete the module.
     */
    CREATE_PATTERN(null),
    
    /**
     * Adjust the reference.
     */
    ADJUST_REFERENCE(null);

    /**
     * the marker ID.
     */
    private String markerId;

    /**
     * Allocates a FunctionTypeImpl object and initializes it to represent the specified marker ID.
     * 
     * @param markerId the marker ID.
     */
    FunctionTypeImpl(String markerId) {
        this.markerId = markerId;
    }

    /**
     * Returns the marker ID.
     * 
     * @return the marker ID.
     */
    public String getMarkerId() {
        return markerId;
    }
}
