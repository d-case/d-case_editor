/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Context;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.impl.RequirementItem;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to change the value of attributes of a node.
 */
public class ChangeBasicNodePropertyTransactionCommand extends
        AbstractTransactionalCommand {

    /**
     * the node.
     */
    private BasicNode basicNode;

    /**
     * the map of attributes.
     */
    private Map<AttributeType, Object> attributeMap;

    /**
     * Allocates a ChangeBasicNodePropertyTransactionCommand object and initialize it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param affectedFiles the list of affected IFiles.
     * @param basicNode the node.
     * @param attributeMap the map of attributes.
     */
    @SuppressWarnings("unchecked")
    public ChangeBasicNodePropertyTransactionCommand(
            TransactionalEditingDomain domain, String label,
            List affectedFiles, BasicNode basicNode,
            Map<AttributeType, Object> attributeMap) {
        super(domain, label, affectedFiles);
        this.basicNode = basicNode;
        this.attributeMap = attributeMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.gmf.runtime.emf.commands.core.command.
     * AbstractTransactionalCommand
     * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(
            IProgressMonitor progressMonitor, IAdaptable info)
            throws ExecutionException {

        // sets new values of common attributes.
        setCommonAttribute();

        Set<AttributeType> attrributeTypeSet = attributeMap.keySet();

        // sets the value of proprietary attributes.
        NodeType nodeType = NodeType.getNodeType(basicNode);
        switch (nodeType) {
            case GOAL:
                Goal goal = (Goal) basicNode;
                if (attrributeTypeSet.contains(AttributeType.SCORE)) {
                    goal.setScore((BigDecimal) attributeMap
                            .get(AttributeType.SCORE));
                }
                if (attrributeTypeSet.contains(AttributeType.WEIGHT)) {
                    goal.setWeight((Integer) attributeMap
                            .get(AttributeType.WEIGHT));
                }
                break;
            case SYSTEM:
                net.dependableos.dcase.System system = (net.dependableos.dcase.System) basicNode;
                if (attrributeTypeSet.contains(AttributeType.SUBTYPE)) {
                    system.setSubType((String) attributeMap.get(AttributeType.SUBTYPE));
                }
                if (attrributeTypeSet.contains(AttributeType.LEAFNODE)) {
                    system.setLeafNode((String) attributeMap.get(AttributeType.LEAFNODE));
                }
                if (attrributeTypeSet.contains(AttributeType.I)) {
                    system.setI((Integer) attributeMap.get(AttributeType.I));
                }
                if (attrributeTypeSet.contains(AttributeType.J)) {
                    system.setJ((Integer) attributeMap.get(AttributeType.J));
                }
                break;
            case MONITOR:
                if (attrributeTypeSet.contains(AttributeType.IS_NORMAL)) {
                    Monitor monitor = (Monitor) basicNode;
                    monitor.setIsNormal((Boolean) attributeMap
                            .get(AttributeType.IS_NORMAL));
                }
                break;
            case JUSTIFICATION:
                Justification justification = (Justification) basicNode;
                if (attrributeTypeSet.contains(AttributeType.STAKEHOLDER)) {
                    justification.setStakeholder((String) attributeMap
                            .get(AttributeType.STAKEHOLDER));
                }
                if (attrributeTypeSet.contains(AttributeType.RISK_ANALYSIS)) {
                    justification.setRiskAnalysis((String) attributeMap
                            .get(AttributeType.RISK_ANALYSIS));
                }
                break;
            case CONTEXT:
                if (attrributeTypeSet.contains(AttributeType.REQUIREMENTS)) {
                    Context context = (Context) basicNode;
                    context.setRequirements((List<RequirementItem>) attributeMap
                            .get(AttributeType.REQUIREMENTS));
                }
                break;
            default:
                break;
        }

        return CommandResult.newOKCommandResult();
    }

    /**
     * Sets the values of common attributes.
     */
    private void setCommonAttribute() {

        for (Map.Entry<AttributeType, Object> attribute : attributeMap
                .entrySet()) {
            Object value = attribute.getValue();
            switch (attribute.getKey()) {
                case NAME:
                    basicNode.setName((String) value);
                    break;
                case DESC:
                    basicNode.setDesc((String) value);
                    break;
                case ATTACHMENT:
                    basicNode.setAttachment((String) value);
                    break;
                case STATUS:
                    basicNode.setStatus((String) value);
                    break;
                case FLAG:
                    basicNode.setFlag((String) value);
                    break;
                case RESPNAME:
                    basicNode.setRespName((String) value);
                    break;
                case RESPADDRESS:
                    basicNode.setRespAddress((String) value);
                    break;
                case RESPICON:
                    basicNode.setRespIcon((String) value);
                    break;
                case RESPTIME:
                    basicNode.setRespTime((String) value);
                    break;
                case MESSAGE:
                    basicNode.setMessage((String) value);
                    break;
                case REQUIREMENT:
                    basicNode.setRequirement((String) value);
                    break;
                case PARENT:
                    basicNode.setParent((String) value);
                    break;
                case REFSOURCE:
                    basicNode.setRefSource((String) value);
                    break;
                case PARAMETERDEFS:
                    basicNode.setParameterDefs((String) value);
                    break;
                case PARAMETERVALS:
                    basicNode.setParameterVals((String) value);
                    break;
                case PARAMETERIZEDDESC:
                    basicNode.setParameterizedDesc((String) value);
                    break;
                case USERDEF001:
                    basicNode.setUserdef001((String) value);
                    break;
                case USERDEF002:
                    basicNode.setUserdef002((String) value);
                    break;
                case USERDEF003:
                    basicNode.setUserdef003((String) value);
                    break;
                case USERDEF004:
                    basicNode.setUserdef004((String) value);
                    break;
                case USERDEF005:
                    basicNode.setUserdef005((String) value);
                    break;
                case USERDEF006:
                    basicNode.setUserdef006((String) value);
                    break;
                case USERDEF008:
                    basicNode.setUserdef008((String) value);
                    break;
                case USERDEF009:
                    basicNode.setUserdef009((String) value);
                    break;
                case USERDEF010:
                    basicNode.setUserdef010((String) value);
                    break;
                case USERDEF011:
                    basicNode.setUserdef011((String) value);
                    break;
                case USERDEF012:
                    basicNode.setUserdef012((String) value);
                    break;
                case USERDEF013:
                    basicNode.setUserdef013((String) value);
                    break;
                case USERDEF014:
                    basicNode.setUserdef014((String) value);
                    break;
                case USERDEF015:
                    basicNode.setUserdef015((String) value);
                    break;
                case USERDEF016:
                    basicNode.setUserdef016((String) value);
                    break;
                default:
                    break;
            }
        }

        // sets the value of the Parameters attribute last.
        if (attributeMap.get(AttributeType.USERDEF007) != null) {
            basicNode.setUserdef007((String) attributeMap
                    .get(AttributeType.USERDEF007));
        }

    }
}
