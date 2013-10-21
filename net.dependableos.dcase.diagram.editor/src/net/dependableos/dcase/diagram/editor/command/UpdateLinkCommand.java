/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_SOURCE;
import static org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_TARGET;

import java.util.List;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This class updates the source or the target of links when node type are changed.
 */
public class UpdateLinkCommand extends AbstractTransactionalCommand {

    /**
     * the source argument.
     */
    private Argument sourceArgument;
    /**
     * the original node.
     */
    private BasicNode oldNode;
    /**
     * current argument.
     */
    private ArgumentEditPart argumentEditPart;
    /**
     * the request for creating new node.
     */
    private CreateViewAndElementRequest createNodeRequest;

    /**
     * Creates a ConvertBasicLinkCommand object and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param sourceArgument the source argument.
     * @param oldNode the original node.
     * @param argumentEditPart current argument
     * @param createNodeRequest the request for creating new node.
     */
    public UpdateLinkCommand(TransactionalEditingDomain domain,
            String label, Argument sourceArgument, BasicNode oldNode,
            ArgumentEditPart argumentEditPart,
            CreateViewAndElementRequest createNodeRequest) {
        super(domain, label, null);
        this.sourceArgument = sourceArgument;
        this.oldNode = oldNode;
        this.argumentEditPart = argumentEditPart;
        this.createNodeRequest = createNodeRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.gmf.runtime.emf.commands.core.command.
     * AbstractTransactionalCommand
     * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        EList<BasicLink> linkList = sourceArgument.getRootBasicLink();
        EList<BasicNode> nodeList = sourceArgument.getRootBasicNode();

        BasicNode sourceNode = null;
        BasicNode targetNode = null;

        List elemList = argumentEditPart.getChildren();
        if (elemList == null || elemList.isEmpty()) {
            return null;
        }

        // gets new node from the CreateViewAndElementRequest object
        IAdaptable viewAdapter = (IAdaptable) ((List) createNodeRequest
                .getNewObject()).get(0);
        EditPartViewer viewer = argumentEditPart.getViewer();
        EditPart elementPart = (EditPart) viewer.getEditPartRegistry().get(
                viewAdapter.getAdapter(View.class));

        EObject eObj = DcaseEditorUtil
                .getElement((GraphicalEditPart) elementPart);
        BasicNode addNode = (BasicNode) eObj;

        // updates links.
        for (BasicLink modifyLink : linkList) {

            sourceNode = modifyLink.getSource();
            targetNode = modifyLink.getTarget();

            if (sourceNode == oldNode) {
                updateLink(modifyLink, addNode, REORIENT_SOURCE);
            }
            if (targetNode == oldNode) {
                updateLink(modifyLink, addNode, REORIENT_TARGET);
            }
       }
        // remove original node.
        for (BasicNode convertNode : nodeList) {
            if (convertNode == oldNode) {
                nodeList.remove(oldNode);
                break;
            }
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Updates target or source of a link.
     * 
     * @param link a link
     * @param newNode new node.
     * @param direction specifies which will be changed,target or source.
     */
    private void updateLink(BasicLink link, BasicNode newNode,
            int direction) {

        switch (direction) {
            case REORIENT_SOURCE:
                link.setSource(newNode);
                break;
            case REORIENT_TARGET:
                link.setTarget(newNode);
                break;
            default:
        }
        return;
    }
}
