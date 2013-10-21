/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import java.util.ArrayList;
import java.util.List;

import net.dependableos.dcase.Context;
import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ContextImpl extends BasicNodeImpl implements Context {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ContextImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.CONTEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserdef003(String newUserdef003) {
        super.setUserdef003(newUserdef003);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<RequirementItem> getRequirements(){
        List<RequirementItem> requirementList = new ArrayList<RequirementItem>();

        String id = ((XMLResource)eResource()).getID(this);
        String requirementsString = getUserdef003();
        String parameters = ""; //$NON-NLS-1$;
        try {
            parameters = getParameters();
        } catch (Exception e) {
        }
        if (requirementsString != null && requirementsString.length() > 0) {
            for (String requirement : requirementsString.split(RequirementItem.REQUIREMENT_DELIMITER)) {
                String[] requirementStrings = requirement.split(RequirementItem.REQUIREMENT_ID_DELIMITER);
                if (requirementStrings.length < 2){
                    return null;
                }
                RequirementItem item = new RequirementItem(id,requirementStrings[0], requirementStrings[1], parameters);
                requirementList.add(item);
            }
        }
        return requirementList;        
   }
    
    /**
     * {@inheritDoc}
     */
    public void setRequirements(List<RequirementItem> requirements){
        if(requirements == null){
            return;
        }
        StringBuilder requirementsStringBuilder = new StringBuilder();
        for(RequirementItem requirement:requirements){
            requirementsStringBuilder.append(requirement.toString());
            requirementsStringBuilder.append(RequirementItem.REQUIREMENT_DELIMITER);
        }
        setUserdef003(requirementsStringBuilder.toString());
    }
    
    /**
     * Sets formatted string to description.
     * 
     * @param basicNode the node.
     * @param parameters the parameters.
     */
    private void setContextDesc(Context basicNode , String parameters) {
        String userdef005 = basicNode.getUserdef005();
        // tests whether the formatter is valid.
        if (userdef005 != null && userdef005.trim().length() != 0) {
            basicNode.setDesc(getFormattedDesc(basicNode, parameters,
                    userdef005));
        }
    }
    
    /**
     * Returns the formatted string for description.
     * 
     * @param basicNode the node.
     * @param parameters the parameters.
     * @param formatter the format string.
     * @return the formatted string for description.
     */
    public static String getFormattedDesc(Context basicNode , String parameters, String formatter) {
        // tests whether the parameters are valid.
        String requirements = getRequirementString(basicNode);
        if (requirements != null && requirements.length() > 0) {
            formatter = formatter.replaceAll(
                    String.format(
                            ParameterItem.PARAM_ITEM_FORMAT, "Requirements"), requirements); //$NON-NLS-1$
        }
        return ParameterItem.getFormattedDesc(parameters , formatter);
    }
    
    /**
     *  Returns the string that represents requirements formatted with parameters.
     * 
     * @param context the context.
     * @return the string that represents requirements.
     */
    private static String getRequirementString(Context context) {
        List<RequirementItem> requirements = context.getRequirements();
        StringBuilder requirementsStringBuilder = new StringBuilder();
        for(RequirementItem requirement:requirements){
            requirementsStringBuilder.append(requirement.getRequirement());
            requirementsStringBuilder.append(System.getProperty("line.separator"));
        }
        return requirementsStringBuilder.toString();
    }

} //ContextImpl
