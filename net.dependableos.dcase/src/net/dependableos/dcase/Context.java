/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

import java.util.List;

import net.dependableos.dcase.impl.RequirementItem;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see net.dependableos.dcase.DcasePackage#getContext()
 * @model
 * @generated
 */
public interface Context extends BasicNode {
    
    /**
     * Returns the requirements;
     * 
     * @return the requirements;
     */
   List<RequirementItem> getRequirements();
    
    /**
     * Sets the requirements;
     * 
     * @param requirements the requirements;
     */
    void setRequirements(List<RequirementItem> requirements);
   // Context
}