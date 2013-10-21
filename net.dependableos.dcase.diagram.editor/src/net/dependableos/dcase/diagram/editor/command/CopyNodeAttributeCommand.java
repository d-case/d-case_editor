/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * A transaction command to copy attributes of original node to new node.
 */
public class CopyNodeAttributeCommand extends AbstractTransactionalCommand {

    /**
     * the command label.
     */
    private static final String CHANGE_BASIC_NODE_COMMAND_LABEL = "change basic node command"; //$NON-NLS-1$

    /**
     * separator.
     */
    private static final String COMMA = ","; //$NON-NLS-1$
    /**
     * the format string for incompatible attribute that is pushed to userdef008.
     */
    private static final String INCOMPATIBLE_ATTRIBUTE_FORMAT = "%s=%s"; //$NON-NLS-1$

    /**
     * the original node.
     */
    private BasicNode oldNode;
    /**
     * current argument.
     */
    private ArgumentEditPart argumentEditPart;
    /**
     * current editing domain.
     */
    private TransactionalEditingDomain currentDomain;
    /**
     * the request for creating new node.
     */
    private CreateViewAndElementRequest createNodeRequest;
    
    /**
     * the new name.
     */
    private String newName = null;

    /**
     * Creates a CopyNodeAttributeCommand object and initializes it.
     * 
     * @param domain current editing domain.
     * @param label the command label.
     * @param oldNode the original node.
     * @param argumentEditPart current argument.
     * @param createNodeRequest the request for creating new node.
     */
    public CopyNodeAttributeCommand(TransactionalEditingDomain domain,
            String label, BasicNode oldNode, ArgumentEditPart argumentEditPart,
            CreateViewAndElementRequest createNodeRequest) {
        super(domain, label, null);
        this.oldNode = oldNode;
        this.argumentEditPart = argumentEditPart;
        this.currentDomain = domain;
        this.createNodeRequest = createNodeRequest;
    }
    
    /**
     * Creates a CopyNodeAttributeCommand object and initializes it.
     * 
     * @param domain current editing domain.
     * @param label the command label.
     * @param oldNode the original node.
     * @param argumentEditPart current argument.
     * @param createNodeRequest the request for creating new node.
     * @param isCopyName the flag for copying name.
     */
    public CopyNodeAttributeCommand(TransactionalEditingDomain domain,
            String label, BasicNode oldNode, ArgumentEditPart argumentEditPart,
            CreateViewAndElementRequest createNodeRequest, String newName) {
    	this(domain, label, oldNode, argumentEditPart, createNodeRequest);
    	this.newName = newName;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.gmf.runtime.emf.commands.core.command.
     * AbstractTransactionalCommand
     * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    //@SuppressWarnings("unchecked")
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        // terminates if no node is found.
        List elemList = argumentEditPart.getChildren();
        if (elemList == null || elemList.isEmpty()) {
            return null;
        }

        // gets the node from the CreateViewAndElementRequest object.
        IAdaptable viewAdapter = (IAdaptable) ((List) createNodeRequest
                .getNewObject()).get(0);
        EditPartViewer viewer = argumentEditPart.getViewer();
        EditPart elementPart = (EditPart) viewer.getEditPartRegistry().get(
                viewAdapter.getAdapter(View.class));

        EObject eObj = DcaseEditorUtil
                .getElement((GraphicalEditPart) elementPart);
        BasicNode addNode = (BasicNode) eObj;

        // creates a map of attributes to copy.
        Map<AttributeType, Object> attrMap = createAttributeMap(addNode);

        // creates a command to set attributes.
        ICommand changeAttributeCommand = new ChangeBasicNodePropertyTransactionCommand(
                currentDomain, CHANGE_BASIC_NODE_COMMAND_LABEL, null, addNode,
                attrMap);

        ICommandProxy proxy = new ICommandProxy(changeAttributeCommand);

        // executes the command.
        proxy.execute();

        return CommandResult.newOKCommandResult();
    }

    /**
     * Creates a map of attributes to copy.
     * 
     * @param newNode new node.
     * @return a map of attributes to copy.
     */
    private Map<AttributeType, Object> createAttributeMap(BasicNode newNode) {

        // creates a map.
        Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();

        if(newName != null && newName.length() > 0) {
            attrMap.put(AttributeType.NAME, newName);
        }
        attrMap.put(AttributeType.ATTACHMENT, oldNode.getAttachment());
        attrMap.put(AttributeType.DESC, oldNode.getDesc());
        attrMap.put(AttributeType.STATUS, oldNode.getStatus());
        attrMap.put(AttributeType.USERDEF001, oldNode.getUserdef001());
        attrMap.put(AttributeType.USERDEF002, oldNode.getUserdef002());
        attrMap.put(AttributeType.USERDEF003, oldNode.getUserdef003());
        attrMap.put(AttributeType.USERDEF004, oldNode.getUserdef004());
        attrMap.put(AttributeType.USERDEF005, oldNode.getUserdef005());
        attrMap.put(AttributeType.USERDEF006, oldNode.getUserdef006());
        attrMap.put(AttributeType.USERDEF007, oldNode.getUserdef007());
        attrMap.put(AttributeType.USERDEF008, oldNode.getUserdef008());
        attrMap.put(AttributeType.USERDEF009, oldNode.getUserdef009());
        attrMap.put(AttributeType.USERDEF010, oldNode.getUserdef010());
        attrMap.put(AttributeType.USERDEF011, oldNode.getUserdef011());
        attrMap.put(AttributeType.USERDEF012, oldNode.getUserdef012());
        attrMap.put(AttributeType.USERDEF013, oldNode.getUserdef013());
        attrMap.put(AttributeType.USERDEF014, oldNode.getUserdef014());
        attrMap.put(AttributeType.USERDEF015, oldNode.getUserdef015());
        attrMap.put(AttributeType.USERDEF016, oldNode.getUserdef016());

        // pushes incompatible attributes to userdef007.
        String uniqueAttribute = getIncompatibleAttributeString(newNode);
        if (uniqueAttribute != null) {
            if (oldNode.getUserdef008() == null
                    || oldNode.getUserdef008().length() == 0) {
                attrMap.put(AttributeType.USERDEF008, uniqueAttribute);
            } else {
                attrMap.put(AttributeType.USERDEF008, oldNode.getUserdef008()
                        + COMMA + uniqueAttribute);
            }
        }
        return attrMap;
    }

    /**
     * Gets a string that represents incompatible attributes.
     * 
     * @param newNode new node.
     * @return a string that represents incompatible attributes.
     */
    private String getIncompatibleAttributeString(BasicNode newNode) {

        StringBuilder sb = new StringBuilder();
        NodeType oldNodeType = NodeType.getNodeType(oldNode);


        switch (oldNodeType) {
            case GOAL:
                Goal goal = (Goal) oldNode;
                append(sb, "Score", goal.getScore().toString()); //$NON-NLS-1$
                append(sb, "Weight", Integer.toString(goal.getWeight())); //$NON-NLS-1$
                break;
            case JUSTIFICATION:
                Justification justification = (Justification) oldNode;
                append(sb, "Stakeholder", justification.getStakeholder()); //$NON-NLS-1$
                append(sb, "Risk Analysis", justification.getRiskAnalysis()); //$NON-NLS-1$
                break;
            case MONITOR:
                Monitor monitor = (Monitor) oldNode;
                append(sb, "Is Normal", Boolean.toString(monitor.isIsNormal())); //$NON-NLS-1$
                break;
            default:
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    /**
     * Appends the string that represents a attribute.
     * 
     * @param builder a string builder.
     * @param key the key.
     * @param value the value.
     */
    private void append(StringBuilder builder, String key, String value) {
        if (value == null) {
            return;
        }
        if (builder.length() > 0) {
            builder.append(COMMA);
        }
        builder.append(String.format(INCOMPATIBLE_ATTRIBUTE_FORMAT, key, value));
    }

}
