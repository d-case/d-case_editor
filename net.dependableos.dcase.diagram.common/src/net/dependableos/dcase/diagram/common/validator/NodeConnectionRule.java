/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

import java.util.List;

import net.dependableos.dcase.diagram.common.model.NodeType;


/**
 * A class that represents a connection rule.
 */
public class NodeConnectionRule {

    /**
     * the operator.
     */
    private RuleOperator ruleOperator;
    
    /**
     * the list of node types.
     */
    private List<NodeType> nodeList;

    /**
     * Allocates a NodeConnectionRule object and initializes it to the specified operator and the list of node types.
     * 
     * @param ruleOperator the operator.
     * @param nodeList the list of node types.
     */
    public NodeConnectionRule(RuleOperator ruleOperator, List<NodeType> nodeList) {
        this.ruleOperator = ruleOperator;
        this.nodeList = nodeList;
    }

    /**
     * Returns the operator.
     * 
     * @return the operator.
     */
    public RuleOperator getRuleOperator() {
        return ruleOperator;
    }

    /**
     * Returns the list of node types.
     * 
     * @return the list of node types.
     */
    public List<NodeType> getNodeList() {
        return nodeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (ruleOperator == null || nodeList == null) {
            return false;
        }

        if (obj instanceof NodeConnectionRule) {
            NodeConnectionRule anotherConnRule = (NodeConnectionRule) obj;
            RuleOperator anotherRuleOperator = anotherConnRule.getRuleOperator();
            List<NodeType> anotherNodeList = anotherConnRule.getNodeList();
            if (ruleOperator.equals(anotherRuleOperator)) {
                if (nodeList.equals(anotherNodeList)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        final int multiplier = 31;
        int result = 1;

        result = result * multiplier;
        if (ruleOperator != null) {
            result += ruleOperator.hashCode();
        }

        result = result * multiplier;
        if (nodeList != null) {
            result += nodeList.hashCode();
        }

        return result;
    }

}
