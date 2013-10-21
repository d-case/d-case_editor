/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.commands;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcaseLink004;
import net.dependableos.dcase.diagram.edit.policies.DcaseBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class DcaseLink004ReorientCommand extends EditElementCommand {

    /**
     * @generated
     */
    private final int reorientDirection;

    /**
     * @generated
     */
    private final EObject oldEnd;

    /**
     * @generated
     */
    private final EObject newEnd;

    /**
     * @generated
     */
    public DcaseLink004ReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), request);
        reorientDirection = request.getDirection();
        oldEnd = request.getOldRelationshipEnd();
        newEnd = request.getNewRelationshipEnd();
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        if (false == getElementToEdit() instanceof DcaseLink004) {
            return false;
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return canReorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return canReorientTarget();
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean canReorientSource() {
        if (!(oldEnd instanceof BasicNode && newEnd instanceof BasicNode)) {
            return false;
        }
        BasicNode target = getLink().getTarget();
        if (!(getLink().eContainer() instanceof Argument)) {
            return false;
        }
        Argument container = (Argument) getLink().eContainer();
        return DcaseBaseItemSemanticEditPolicy.LinkConstraints
                .canExistDcaseLink004_3004(container, getNewSource(), target);
    }

    /**
     * @generated
     */
    protected boolean canReorientTarget() {
        if (!(oldEnd instanceof BasicNode && newEnd instanceof BasicNode)) {
            return false;
        }
        BasicNode source = getLink().getSource();
        if (!(getLink().eContainer() instanceof Argument)) {
            return false;
        }
        Argument container = (Argument) getLink().eContainer();
        return DcaseBaseItemSemanticEditPolicy.LinkConstraints
                .canExistDcaseLink004_3004(container, source, getNewTarget());
    }

    /**
     * @generated
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException(
                    "Invalid arguments in reorient link command"); //$NON-NLS-1$
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
            return reorientSource();
        }
        if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
            return reorientTarget();
        }
        throw new IllegalStateException();
    }

    /**
     * @generated
     */
    protected CommandResult reorientSource() throws ExecutionException {
        getLink().setSource(getNewSource());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected CommandResult reorientTarget() throws ExecutionException {
        getLink().setTarget(getNewTarget());
        return CommandResult.newOKCommandResult(getLink());
    }

    /**
     * @generated
     */
    protected DcaseLink004 getLink() {
        return (DcaseLink004) getElementToEdit();
    }

    /**
     * @generated
     */
    protected BasicNode getOldSource() {
        return (BasicNode) oldEnd;
    }

    /**
     * @generated
     */
    protected BasicNode getNewSource() {
        return (BasicNode) newEnd;
    }

    /**
     * @generated
     */
    protected BasicNode getOldTarget() {
        return (BasicNode) oldEnd;
    }

    /**
     * @generated
     */
    protected BasicNode getNewTarget() {
        return (BasicNode) newEnd;
    }
}
