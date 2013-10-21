/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;
import static net.dependableos.dcase.diagram.common.util.ModelUtil.STRING_EMPTY;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseValidatorException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.LinkInfo;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;

import org.eclipse.osgi.util.NLS;

/**
 * A validator for the D-Case.
 */
public class DcaseValidator {

    /**
     * the regular expression pattern for URL.
     */
    private static final Pattern URL_PATTERN = Pattern.compile(
            "((http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+)|"        //$NON-NLS-1$
            + "(file://{1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+\\"                   //$NON-NLS-1$
                + System.getProperties().getProperty("file.separator") + "]+)",     //$NON-NLS-1$ //$NON-NLS-2$
            Pattern.CASE_INSENSITIVE);

    /**
     * the link manager.
     */
    private LinkManager linkManager = null;

    /**
     * Allocates a DcaseValidator object and initializes to validate
     *  a D-Case that represents the specified link manager.
     * 
     * @param linkManager LinkManager
     */
    public DcaseValidator(LinkManager linkManager) {
        this.linkManager = linkManager;
    }

    /**
     * Validates the attributes of a node.
     * It throws a DcaseValidatorException if invalid attribute is detected.
     * 
     * @param node a node.
     */
    public void validateNodeAttribute(BasicNode node) {
        NodeInfo nodeInfo = ModelUtil.createNodeInfo(node);
        validateAttribute(nodeInfo.getAttributeMap(), Messages.DcaseValidator_0);
    }

    /**
     * Validates the attributes of a link.
     * It throws a DcaseValidatorException if invalid attribute is detected.
     * 
     * @param link a link.
     */
    public void validateLinkAttribute(BasicLink link) {
        LinkInfo linkInfo = ModelUtil.createLinkInfo(link);
        validateAttribute(linkInfo.getAttributeMap(), Messages.DcaseValidator_1);
    }

    /**
     * Validates the attributes of a node or a link.
     * It throws a DcaseValidatorException if invalid attribute is detected.
     * 
     * @param attributeMap the attributes
     * @param objectType the string that represents the object type.
     */
    private void validateAttribute(Map<AttributeType, Object> attributeMap,
            String objectType) {

        String objectName = (String) attributeMap.get(AttributeType.NAME);

        for (Map.Entry<AttributeType, Object> attribute : attributeMap
                .entrySet()) {

            AttributeType attributeType = attribute.getKey();
            String attributeName = attributeType.toString().toLowerCase();

            switch (attributeType.getRuleType()) {
                case STRING_NOT_NULL:
                    String strNotNullValue = (String) attribute.getValue();
                    if (strNotNullValue == null) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_4,
                                new Object[] { attributeName, objectType,
                                        objectName }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case STRING_NOT_EMPTY:
                    String strNotEmptyValue = (String) attribute.getValue();
                    if (strNotEmptyValue == null
                            || strNotEmptyValue.equals(STRING_EMPTY)) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_5,
                                new Object[] { attributeName, objectType,
                                        objectName }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case STRING_URL:
                    String strUrlValue = (String) attribute.getValue();
                    if (strUrlValue != null
                            && !strUrlValue.equals(STRING_EMPTY)) {
                        Matcher matcher = URL_PATTERN.matcher(strUrlValue);
                        if (!matcher.matches()) {
                            throw new DcaseValidatorException(NLS.bind(
                                        Messages.DcaseValidator_6,
                                        new Object[] {
                                                    attributeName,
                                                    objectType,
                                                    objectName }),
                                        MessageTypeImpl.VALIDATION_ERROR);
                        }
                    }
                    break;
                case INTEGER_ONE_OR_MORE:
                    Integer intOneOrMoreValue = (Integer) attribute.getValue();
                    if (intOneOrMoreValue.intValue() < 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_7,
                                new Object[] { attributeName,
                                        objectType, objectName }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case NO_RULE:
                default:
                    break;
            }
        }
    }

    /**
     * Validates whether the tree that start from specified node has any loop.
     * It throws a DcaseValidatorException if the tree has any loop.
     * 
     * @param topNode the top node.
     * @return the list of the node IDs those has been tested.
     */
    public Set<String> validateCyclicStatePart(NodeInfo topNode) {

        if (topNode == null) {
            return null;
        }

        // initializes the list of the node IDs those has been tested.
        Set<String> checkedIdSet = new HashSet<String>(
                COLLECTION_INITIAL_CAPACITY);
        // initializes the list of the node IDs those are current subject of validation.
        LinkedList<String> searchingIdList = new LinkedList<String>();
        // initializes the list of the node IDs those are unchecked.
        Map<String, List<String>> uncheckedLinkInfoMap = new HashMap<String, List<String>>(
                COLLECTION_INITIAL_CAPACITY);

        // initializes the start point to trace.
        String topNodeId = (String) topNode.getAttribute(AttributeType.ID);
        List<String> topNodetargetIdList = linkManager.getTarget(topNodeId);
        if (topNodetargetIdList != null && !topNodetargetIdList.isEmpty()) {
            uncheckedLinkInfoMap.put(topNodeId, ModelUtil
                    .duplicateList(topNodetargetIdList));
            searchingIdList.addLast(topNodeId);
        } else {
            checkedIdSet.add(topNodeId);
        }

        // validates.
        while (!searchingIdList.isEmpty()) {

            String lastId = searchingIdList.getLast();
            List<String> nextTargetIdList = uncheckedLinkInfoMap.get(lastId);
            boolean existsNewTarget = false;

            if (nextTargetIdList != null) {
                for (String targetId : nextTargetIdList) {

                    // detects cyclic.
                    if (searchingIdList.contains(targetId)) {
                        searchingIdList.add(targetId);
                        // ignore the ID that is not a part of a cycle.
                        while (!searchingIdList.isEmpty()) {
                            String notLoopId = searchingIdList.getFirst();
                            if (notLoopId.equals(targetId)) {
                                break;
                            } else {
                                searchingIdList.removeFirst();
                            }
                        }
                        String cyclicInfo = getNodeListString(searchingIdList);
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_8,
                                cyclicInfo), MessageTypeImpl.VALIDATION_ERROR);
                    }

                    // traces the node.
                    List<String> newTargetIdList = linkManager
                            .getTarget(targetId);
                    if (newTargetIdList != null && !newTargetIdList.isEmpty()) {
                        uncheckedLinkInfoMap.put(targetId, ModelUtil
                                .duplicateList(newTargetIdList));
                        if (!existsNewTarget) {
                            searchingIdList.addLast(targetId);
                            existsNewTarget = true;
                        }
                    } else {
                        checkedIdSet.add(targetId);
                    }
                }
            }

            // traces the next tree.
            if (!existsNewTarget) {
                String checkedId = searchingIdList.removeLast();
                checkedIdSet.add(checkedId);
                if (!searchingIdList.isEmpty()) {
                    String recheckId = searchingIdList.getLast();
                    List<String> oldIdList = uncheckedLinkInfoMap
                            .get(recheckId);
                    oldIdList.remove(checkedId);
                    if (oldIdList.isEmpty()) {
                        uncheckedLinkInfoMap.remove(recheckId);
                    } else {
                        uncheckedLinkInfoMap.put(recheckId, oldIdList);
                    }
                }
            }

        }

        return checkedIdSet;
    }

    /**
     * Returns the string that is the list of the node IDs joined with comma.
     *
     * @param idList the list of the node IDs
     * @return the string that is the list of the node IDs joined with comma.
     */
    private String getNodeListString(List<String> idList) {

        StringBuilder cyclicNodeInfo = new StringBuilder();
        int count = 0;

        for (String id : idList) {
            NodeInfo nodeInfo = linkManager.getNodeInfo(id);
            String name = (String) nodeInfo.getAttribute(AttributeType.NAME);
            if (count != 0) {
                cyclicNodeInfo.append(",");  //$NON-NLS-1$
            }
            cyclicNodeInfo.append(name);
            count++;
        }
        
        return cyclicNodeInfo.toString();
    }
    
    /**
    * Validates whether the argument node has any loop.
    * It throws a DcaseValidatorException if the argument has any loop.
    */
    public void validateCyclicStateAll() {

        Set<String> allIdList = linkManager.getAllNodes();
        
        while (!allIdList.isEmpty()) {
            String topNodeId = null;

            // searches top nodes.
            for (String id : allIdList) {
                List<String> sourceIdList = linkManager.getSource(id);
                if (sourceIdList == null || sourceIdList.isEmpty()) {
                    topNodeId = id;
                    break;
                }
            }

            // sets a node as a top if all nodes has a parent.
            if (topNodeId == null) {
                for (String notTopNodeId : allIdList) {
                    topNodeId = notTopNodeId;
                    break;
                }
            }
            
            // checks the tree that start from a top node.
            NodeInfo topNode = linkManager.getNodeInfo(topNodeId);
            Set<String> checkedIdSet = validateCyclicStatePart(topNode);
            allIdList.removeAll(checkedIdSet);
        }
    }

    /**
     * Validates links of the node.
     * It throws a DcaseValidatorException if invalid connection is detected.
     * 
     * @param node the node to validate.
     */
    public void validateLink(BasicNode node) {

        // creates the NodeInfo object.
        NodeInfo nodeInfo = ModelUtil.createNodeInfo(node);

        // gets the basic attributes.
        String nodeId = (String) nodeInfo.getAttribute(AttributeType.ID);
        String nodeName = (String) nodeInfo.getAttribute(AttributeType.NAME);

        // gets the links.
        List<String> targetIdList = linkManager.getTarget(nodeId);
        List<String> sourceIdList = linkManager.getSource(nodeId);

        // gets the connection rules.
        NodeValidatorRule nodeValidatorRule = nodeInfo.getNodeType()
                .getNodeValidatorRule();

        // checks the connections(the multiplicity for children).
        Map<NodeType, NodeMultiplicity> childMultiplicityMap = nodeValidatorRule
                .getChildMultiplicity();
        if (childMultiplicityMap != null) {
            validateMultiplicity(childMultiplicityMap, targetIdList, nodeName,
                    Messages.DcaseValidator_2);
        }

        // checks the connections(the multiplicity for parents).
        Map<NodeType, NodeMultiplicity> parentMultiplicityMap = nodeValidatorRule
                .getParentMultiplicity();
        if (parentMultiplicityMap != null) {
            validateMultiplicity(parentMultiplicityMap, sourceIdList, nodeName,
                    Messages.DcaseValidator_3);
        }

        // checks the connections to children.
        List<NodeConnectionRule> childConnectionRuleList = nodeValidatorRule
                .getChildRule();
        if (childConnectionRuleList != null) {
            validateLinkRule(childConnectionRuleList, targetIdList, nodeName,
                    Messages.DcaseValidator_2);
        }

        // checks the connections to parents.
        List<NodeConnectionRule> parentConnectionRuleList = nodeValidatorRule
                .getParentRule();
        if (parentConnectionRuleList != null) {
            validateLinkRule(parentConnectionRuleList, sourceIdList, nodeName,
                    Messages.DcaseValidator_3);
        }
    }

    /**
     * Validates the connections (multiplicity).
     * It throws a DcaseValidatorException if invalid connection is detected.
     *  
     * @param multiplicityMap the multiplicity.
     * @param idList a list of node IDs that connect with the node to validate.
     * @param nodeName a node to validate.
     * @param relationType the relation type (child or parent).
     */
    private void validateMultiplicity(
            Map<NodeType, NodeMultiplicity> multiplicityMap,
            List<String> idList, String nodeName, String relationType) {

        for (Map.Entry<NodeType, NodeMultiplicity> multiplicity : multiplicityMap
                .entrySet()) {

            NodeType nodeType = multiplicity.getKey();
            NodeMultiplicity nodeMultiplicity = multiplicity.getValue();

            // counts the nodes of specified type.
            int nodeNum = countNode(idList, nodeType);

            switch (nodeMultiplicity) {
                case ZERO:
                    if (nodeNum != 0) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_9,
                                new Object[] { nodeName, nodeType.getName(), relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case ONE:
                    if (nodeNum != 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_10,
                                new Object[] { nodeName, nodeType.getName(), relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case ONE_OR_MORE:
                    if (nodeNum < 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_11,
                                new Object[] { nodeName, nodeType.getName(), relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case ZERO_OR_ONE:
                    if (nodeNum > 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_12,
                                new Object[] { nodeName, nodeType.getName(), relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case ZERO_OR_MORE:
                default:
                    break;
            }
        }
    }

    /**
     * Validates the connections.
     * It throws a DcaseValidatorException if invalid connection is detected.

     * @param connectionRuleList a list of rules
     * @param idList a list of node IDs that connect with the node to validate.
     * @param nodeName a node to validate.
     * @param relationType the relation type (child or parent).
     */
    private void validateLinkRule(
            List<NodeConnectionRule> connectionRuleList,
            List<String> idList, String nodeName, String relationType) {

        for (NodeConnectionRule connectionRule : connectionRuleList) {

            RuleOperator ruleOperator = connectionRule.getRuleOperator();
            List<NodeType> nodeTypeList = connectionRule.getNodeList();

            switch (ruleOperator) {
                case AND:
                    for (NodeType nodeType : nodeTypeList) {
                        if (countNode(idList, nodeType) == 0) {
                            throw new DcaseValidatorException(NLS.bind(
                                    Messages.DcaseValidator_13,
                                    new Object[] { nodeName, relationType }),
                                    MessageTypeImpl.VALIDATION_ERROR);
                        }
                    }
                    break;
                case OR:
                    boolean isValidForOrRule = false;
                    for (NodeType nodeType : nodeTypeList) {
                        if (countNode(idList, nodeType) > 0) {
                            isValidForOrRule = true;
                            break;
                        }
                    }
                    if (!isValidForOrRule) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_14,
                                new Object[] { nodeName, relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case NAND:
                    int nandNodeTypeCounter = 0;
                    for (NodeType nodeType : nodeTypeList) {
                        if (countNode(idList, nodeType) > 0) {
                            nandNodeTypeCounter++;
                        }
                    }
                    if (nandNodeTypeCounter > 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_15,
                                new Object[] { nodeName, relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                case XOR:
                    int xorNodeTypeCounter = 0;
                    for (NodeType nodeType : nodeTypeList) {
                        if (countNode(idList, nodeType) > 0) {
                            xorNodeTypeCounter++;
                        }
                    }
                    if (xorNodeTypeCounter != 1) {
                        throw new DcaseValidatorException(NLS.bind(
                                Messages.DcaseValidator_16,
                                new Object[] { nodeName, relationType }),
                                MessageTypeImpl.VALIDATION_ERROR);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Returns the number of nodes of the specified type.
     * 
     * @param idList the list of node IDs.
     * @param nodeType the node type.
     * @return the number of nodes.
     */
    private int countNode(List<String> idList, NodeType nodeType) {

        int count = 0;
        if (idList == null) {
            return count;
        }

        for (String id : idList) {
            NodeInfo nodeInfo = linkManager.getNodeInfo(id);
            if (nodeType.equals(nodeInfo.getNodeType())) {
                count++;
            }
        }
        
        return count;
    }
}