/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.commands;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.DcaseLink003;
import net.dependableos.dcase.diagram.common.util.NamePropertyAutoCreator;
import net.dependableos.dcase.diagram.edit.policies.DcaseBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 * @generated
 */
public class DcaseLink003CreateCommand extends EditElementCommand {

    /**
     * @generated
     */
    private final EObject source;

    /**
     * @generated
     */
    private final EObject target;

    /**
     * @generated
     */
    private final Argument container;

    /**
     * @generated
     */
    public DcaseLink003CreateCommand(CreateRelationshipRequest request,
            EObject source, EObject target) {
        super(request.getLabel(), null, request);
        this.source = source;
        this.target = target;
        container = deduceContainer(source, target);
    }

    /**
     * @generated
     */
    public boolean canExecute() {
        if (source == null && target == null) {
            return false;
        }
        if (source != null && false == source instanceof BasicNode) {
            return false;
        }
        if (target != null && false == target instanceof BasicNode) {
            return false;
        }
        if (getSource() == null) {
            return true; // link creation is in progress; source is not defined yet
        }
        // target may be null here but it's possible to check constraint
        if (getContainer() == null) {
            return false;
        }
        return DcaseBaseItemSemanticEditPolicy.LinkConstraints
                .canCreateDcaseLink003_3003(getContainer(), getSource(),
                        getTarget());
    }

    /**
     * @generated NOT
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        if (!canExecute()) {
            throw new ExecutionException(
                    "Invalid arguments in create link command"); //$NON-NLS-1$
        }

        DcaseLink003 newElement = DcaseFactory.eINSTANCE.createDcaseLink003();

        // AUTO_GENERATED:END

        // initializes the name attribute.
        NamePropertyAutoCreator nameCreator = new NamePropertyAutoCreator();
        nameCreator.loadDiagram(getContainer());
        newElement.setName(nameCreator.getInitialName(newElement));

        // AUTO_GENERATED:START

        getContainer().getRootBasicLink().add(newElement);
        newElement.setSource(getSource());
        newElement.setTarget(getTarget());
        doConfigure(newElement, monitor, info);
        ((CreateElementRequest) getRequest()).setNewElement(newElement);
        return CommandResult.newOKCommandResult(newElement);

    }

    /**
     * @generated
     */
    protected void doConfigure(DcaseLink003 newElement,
            IProgressMonitor monitor, IAdaptable info)
            throws ExecutionException {
        IElementType elementType = ((CreateElementRequest) getRequest())
                .getElementType();
        ConfigureRequest configureRequest = new ConfigureRequest(
                getEditingDomain(), newElement, elementType);
        configureRequest.setClientContext(((CreateElementRequest) getRequest())
                .getClientContext());
        configureRequest.addParameters(getRequest().getParameters());
        configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
                getSource());
        configureRequest.setParameter(CreateRelationshipRequest.TARGET,
                getTarget());
        ICommand configureCommand = elementType
                .getEditCommand(configureRequest);
        if (configureCommand != null && configureCommand.canExecute()) {
            configureCommand.execute(monitor, info);
        }
    }

    /**
     * @generated
     */
    protected void setElementToEdit(EObject element) {
        throw new UnsupportedOperationException();
    }

    /**
     * @generated
     */
    protected BasicNode getSource() {
        return (BasicNode) source;
    }

    /**
     * @generated
     */
    protected BasicNode getTarget() {
        return (BasicNode) target;
    }

    /**
     * @generated
     */
    public Argument getContainer() {
        return container;
    }

    /**
     * Default approach is to traverse ancestors of the source to find instance of container.
     * Modify with appropriate logic.
     * @generated
     */
    private static Argument deduceContainer(EObject source, EObject target) {
        // Find container element for the new link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null; element = element
                .eContainer()) {
            if (element instanceof Argument) {
                return (Argument) element;
            }
        }
        return null;
    }

}
