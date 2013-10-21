/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.impl;


/**
 * A Requirement Item bean class.
 */
public class RequirementItem {
    
    /**
     * the delimiter to separate requirements.
     */
    public static final String REQUIREMENT_DELIMITER = ";";
    /**
     * the delimiter to separate the id and the content of a requirement.
     */
    public static final String REQUIREMENT_ID_DELIMITER = ":";
    /**
     * the Context id. 
     */
    private String contextId = null;
    /**
     * the requirement id.
     */
    private String requirementId = null;
    /**
     * the requirement.
     */
    private String requirement = null;
    /**
     * the description.
     */
    private String description = null;

    /**
     * Creates a requirement and initializes it.
     * 
     * @param contextId the context id.
     * @param id the id.
     * @param requirement the requirement.
     * @param parameters the parameters.
     */
    public RequirementItem(String contextId, String id, String requirement,
            String parameters) {
        this.contextId = contextId;
        this.requirementId = id;
        this.requirement = requirement;
        this.description = ParameterItem.getFormattedDesc(parameters,
                requirement);
    }
    
    /**
     * Returns the description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the context id.
     * @return the context id.
     */
    public String getContextId() {
        return contextId;
    }

    /**
     * Returns the requirement.
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }
    
    /**
     * Returns the id.
     * @return the id.
     */
    public String getRequirementId() {
        return requirementId;
    }
    
    /**
     * Returns the full id of the requirement.
     * @return the full id.
     */
    public String getFullId() {
        return getContextId() + REQUIREMENT_ID_DELIMITER + getRequirementId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getRequirementId() + REQUIREMENT_ID_DELIMITER + getRequirement();
    }
}
