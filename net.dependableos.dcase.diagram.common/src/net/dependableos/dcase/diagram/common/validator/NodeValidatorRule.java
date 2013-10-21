/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

import java.util.List;
import java.util.Map;

import net.dependableos.dcase.diagram.common.model.NodeType;


/**
 * A class that represents a rule for node connections.
 */
public class NodeValidatorRule {

    /**
     * the multiplicities for children.
     * The key is a node type.The value is a multiplicity.
     */
    private Map<NodeType, NodeMultiplicity> childMultiplicity;

    /**
     * the multiplicities for parents.
     * The key is a node type.The value is a multiplicity.
     */
    private Map<NodeType, NodeMultiplicity> parentMultiplicity;

    /**
     * the list of connection rules for children. 
     */
    private List<NodeConnectionRule> childRule;

    /**
    * the list of connection rules for parents. 
     */
    private List<NodeConnectionRule> parentRule;

    /**
     * Returns the multiplicities for children.
     * 
     * @return the multiplicities for children.the key is a node type.the value is a multiplicity.
     */
    public Map<NodeType, NodeMultiplicity> getChildMultiplicity() {
        return childMultiplicity;
    }

    /**
     * Sets the multiplicities for children.
     * 
     * @param childMultiplicity the multiplicities for children.the key is a node type.the value is a multiplicity.
     */
    public void setChildMultiplicity(
            Map<NodeType, NodeMultiplicity> childMultiplicity) {
        this.childMultiplicity = childMultiplicity;
    }

    /**
     * Returns the multiplicities for parents.
     * 
     * @return the multiplicities for parents.the key is a node type.the value is a multiplicity.
     */
    public Map<NodeType, NodeMultiplicity> getParentMultiplicity() {
        return parentMultiplicity;
    }

    /**
     * Sets the multiplicities for parents.
     * 
     * @param parentMultiplicity the multiplicities for parents.the key is a node type.the value is a multiplicity.
     */
    public void setParentMultiplicity(
            Map<NodeType, NodeMultiplicity> parentMultiplicity) {
        this.parentMultiplicity = parentMultiplicity;
    }

    /**
     * Returns the list of connection rules for children.
     * 
     * @return the list of connection rules for children.
     */
    public List<NodeConnectionRule> getChildRule() {
        return childRule;
    }

    /**
     * Sets the list of connection rules for children.
     * 
     * @param childRule the list of connection rules for children.
     */
    public void setChildRule(List<NodeConnectionRule> childRule) {
        this.childRule = childRule;
    }

    /**
     * Returns the list of connection rules for parents.
     * 
     * @return the list of connection rules for parents.
     */
    public List<NodeConnectionRule> getParentRule() {
        return parentRule;
    }

    /**
     * Sets the list of connection rules for parents.
     * 
     * @param parentRule the list of connection rules for parents.
     */
    public void setParentRule(List<NodeConnectionRule> parentRule) {
        this.parentRule = parentRule;
    }

}
