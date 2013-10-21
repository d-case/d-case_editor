/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.policies;

import java.util.Iterator;


import net.dependableos.dcase.diagram.edit.commands.DcaseLink001CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink001ReorientCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink002CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink002ReorientCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink003CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink003ReorientCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink004CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.DcaseLink004ReorientCommand;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink001EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink002EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink003EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink004EditPart;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Userdef003ItemSemanticEditPolicy extends
        DcaseBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public Userdef003ItemSemanticEditPolicy() {
        super(DcaseElementTypes.Userdef003_1012);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        View view = (View) getHost().getModel();
        CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
                getEditingDomain(), null);
        cmd.setTransactionNestingEnabled(false);
        for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
            Edge incomingLink = (Edge) it.next();
            if (DcaseVisualIDRegistry.getVisualID(incomingLink) == DcaseLink001EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(incomingLink) == DcaseLink002EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(incomingLink) == DcaseLink003EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(incomingLink) == DcaseLink004EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        incomingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
                continue;
            }
        }
        for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
            Edge outgoingLink = (Edge) it.next();
            if (DcaseVisualIDRegistry.getVisualID(outgoingLink) == DcaseLink001EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(outgoingLink) == DcaseLink002EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(outgoingLink) == DcaseLink003EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
            if (DcaseVisualIDRegistry.getVisualID(outgoingLink) == DcaseLink004EditPart.VISUAL_ID) {
                DestroyElementRequest r = new DestroyElementRequest(
                        outgoingLink.getElement(), false);
                cmd.add(new DestroyElementCommand(r));
                cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
                continue;
            }
        }
        EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
        if (annotation == null) {
            // there are indirectly referenced children, need extra commands: false
            addDestroyShortcutsCommand(cmd, view);
            // delete host element
            cmd.add(new DestroyElementCommand(req));
        } else {
            cmd.add(new DeleteCommand(getEditingDomain(), view));
        }
        return getGEFWrapper(cmd.reduce());
    }

    /**
     * @generated
     */
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
                : getCompleteCreateRelationshipCommand(req);
        return command != null ? command : super
                .getCreateRelationshipCommand(req);
    }

    /**
     * @generated
     */
    protected Command getStartCreateRelationshipCommand(
            CreateRelationshipRequest req) {
        if (DcaseElementTypes.DcaseLink001_3001 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink001CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink002_3002 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink002CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink003_3003 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink003CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink004_3004 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink004CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * @generated
     */
    protected Command getCompleteCreateRelationshipCommand(
            CreateRelationshipRequest req) {
        if (DcaseElementTypes.DcaseLink001_3001 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink001CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink002_3002 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink002CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink003_3003 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink003CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        if (DcaseElementTypes.DcaseLink004_3004 == req.getElementType()) {
            return getGEFWrapper(new DcaseLink004CreateCommand(req, req
                    .getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * Returns command to reorient EClass based link. New link target or source
     * should be the domain model element associated with this node.
     * 
     * @generated
     */
    protected Command getReorientRelationshipCommand(
            ReorientRelationshipRequest req) {
        switch (getVisualID(req)) {
            case DcaseLink001EditPart.VISUAL_ID:
                return getGEFWrapper(new DcaseLink001ReorientCommand(req));
            case DcaseLink002EditPart.VISUAL_ID:
                return getGEFWrapper(new DcaseLink002ReorientCommand(req));
            case DcaseLink003EditPart.VISUAL_ID:
                return getGEFWrapper(new DcaseLink003ReorientCommand(req));
            case DcaseLink004EditPart.VISUAL_ID:
                return getGEFWrapper(new DcaseLink004ReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
    }

}
