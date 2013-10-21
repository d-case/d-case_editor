/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.assessment;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;


/**
 * A class that assess a D-Case diagram.
 */
public class Assessment {

    /**
     * the link manager.
     */
    private LinkManager linkManager;

    /**
     * the scores.
     */
    private Map<String, BigDecimal> changeList;

    /**
     * Create an instance of Assessment and initializes it.
     * 
     * @param linkManager the link manager.
     */
    public Assessment(LinkManager linkManager) {
        this.linkManager = linkManager;
        changeList = new HashMap<String, BigDecimal>(
                COLLECTION_INITIAL_CAPACITY);
    }

    /**
     * Calculates the scores.
     */
    public void calculateScore() {

        changeList.clear();

        Set<String> nodeIdList = linkManager.getAllNodes();

        for (String id : nodeIdList) {
            NodeInfo node = linkManager.getNodeInfo(id);
            NodeType type = node.getNodeType();
            if (type == NodeType.GOAL || type == NodeType.SYSTEM) {
                changeList.put(id, null);
            }
        }

        nodeIdList = changeList.keySet();
        for (String id : nodeIdList) {
            calculate(id);
        }
    }

    /**
     * Returns the map of the new scores.
     * 
     * @return the map of the new scores.
     */
    public Map<String, BigDecimal> getChangeList() {
        return changeList;
    }

    /**
     * Calculates the score of a node.
     * 
     * @param id the ID of the node.
     */
    private void calculate(String id) {

        if (calculateGoal(id)) {

            // calculates the score of ancestor nodes.
            List<String> sourceList = linkManager.getSource(id);
            if (sourceList != null) {
                for (String strategyId : sourceList) {
                    NodeInfo node = linkManager.getNodeInfo(strategyId);
                    if (node.getNodeType() == NodeType.STRATEGY) {
                        calculateParentGoal(strategyId);
                    }
                }
            }
        }
    }

    /**
     * Calculates the score of a parents.
     * 
     * @param strategyId the ID of the Strategy node.
     */
    private void calculateParentGoal(String strategyId) {
        List<String> parentList = linkManager.getSource(strategyId);
        if (parentList != null) {
            for (String parentId : parentList) {
                NodeInfo parentNode = linkManager.getNodeInfo(parentId);
                NodeType type = parentNode.getNodeType();
                if (type == NodeType.GOAL || type == NodeType.SYSTEM) {
                    calculate(parentId);
                }
            }
        }
    }

    /**
     * Calculates the score of a Goal node.
     * 
     * @param id the ID of the Goal node.
     * @return true if and only if the calculation is done;false otherwise.
     */
    private boolean calculateGoal(String id) {

        // tests whether the calculation has been already done.
        if (changeList.get(id) != null) {
            return false;
        }

        // calculates the score.
        List<String> targetList = linkManager.getTarget(id);
        if (targetList == null) {
            changeList.put(id, BigDecimal.ZERO);
            return true;
        }

        boolean hasEvidence = false;
        boolean hasStrategy = false;
        boolean hasUndeveloped = false;
        String strategyId = null;

        for (String targetId : targetList) {
            NodeInfo node = linkManager.getNodeInfo(targetId);
            switch (node.getNodeType()) {
                case STRATEGY:
                    // throws an exception if the node has 2 or more Strategy nodes.
                    if (hasStrategy) {
                        throw new DcaseRuntimeException(Messages.Assessment_1,
                                null, null, 0,
                                MessageTypeImpl.CALCULATE_SCORE_FAILD);
                    }
                    hasStrategy = true;
                    strategyId = targetId;
                    break;
                case MONITOR:
                case EVIDENCE:
                    hasEvidence = true;
                    break;
                case UNDEVELOPED:
                    hasUndeveloped = true;
                    break;
                default:
                    break;

            }
        }

        if (hasStrategy) {
            if (hasUndeveloped) {
                throw new DcaseRuntimeException(Messages.Assessment_2, null,
                        null, 0, MessageTypeImpl.CALCULATE_SCORE_FAILD);
            }
            if (hasEvidence) {
                throw new DcaseRuntimeException(Messages.Assessment_3, null,
                        null, 0, MessageTypeImpl.CALCULATE_SCORE_FAILD);
            }

            return calculateSubgoal(id, strategyId);
        }

        if (hasEvidence) {
            changeList.put(id, BigDecimal.ONE);
        } else {
            changeList.put(id, BigDecimal.ZERO);
        }
        return true;
    }

    /**
     * Calculates the score by the scores of the Subgoals.
     * Does not calculate the score if a Subgoal which does not have new score exists.
     * 
     * @param goalId the ID of the Goal node.
     * @param strategyId the ID of the Strategy node.
     * @return true if and only if the calculation is done;false otherwise.
     */
    private boolean calculateSubgoal(String goalId, String strategyId) {

        List<String> targetList = linkManager.getTarget(strategyId);
        if (targetList == null) {
            changeList.put(goalId, BigDecimal.ZERO);
            return true;
        }
        BigDecimal score = BigDecimal.ZERO;
        BigDecimal denominator = BigDecimal.ZERO;

        for (String targetId : targetList) {
            NodeInfo node = linkManager.getNodeInfo(targetId);
            NodeType type = node.getNodeType();
            if (type == NodeType.GOAL || type == NodeType.SYSTEM) {

                // returns false if a Subgoal which does not have new score exists.
                if (changeList.get(targetId) == null) {
                    return false;
                }
                BigDecimal weight = new BigDecimal(getWeight(node));
                score = score.add(changeList.get(targetId).multiply(weight));
                denominator = denominator.add(weight);

            }
        }

        if (denominator.equals(BigDecimal.ZERO)) {
            changeList.put(goalId, BigDecimal.ZERO);
        } else {
            changeList.put(goalId, score.divide(denominator,
                    MathContext.DECIMAL128));
        }

        return true;
    }

    /**
     * Returns the weight.
     * 
     * @param goal the Goal node.
     * @return weight the weight.
     */
    private int getWeight(NodeInfo goal) {
        int weight = 0;
        Object weightObj = goal.getAttribute(AttributeType.WEIGHT);

        if (weightObj == null) {
            throw new DcaseRuntimeException(Messages.Assessment_4, null, null,
                    0, MessageTypeImpl.CALCULATE_SCORE_FAILD);
        }

        if (weightObj instanceof Integer) {
            weight = ((Integer) weightObj).intValue();
        } else {
            throw new DcaseRuntimeException(Messages.Assessment_4, null, null,
                    0, MessageTypeImpl.CALCULATE_SCORE_FAILD);
        }
        if (weight <= 0) {
            throw new DcaseRuntimeException(Messages.Assessment_4, null, null,
                    0, MessageTypeImpl.CALCULATE_SCORE_FAILD);
        }
        return weight;
    }

}
