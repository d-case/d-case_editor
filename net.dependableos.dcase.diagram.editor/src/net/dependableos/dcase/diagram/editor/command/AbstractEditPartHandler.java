/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;


import net.dependableos.dcase.BasicNode;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This is an AbstructHandler for GraphicalEditParts.
 */
public abstract class AbstractEditPartHandler extends AbstractHandler {

    
    /**
     * Returns the selected element.
     * 
     * @param event an execution event
     * @return the selected element.
     */
    protected GraphicalEditPart getSelectedElement(ExecutionEvent event) {

        GraphicalEditPart editPart = null;

        // gets active menu selection.
        ISelection selection = HandlerUtil.getActiveMenuSelection(event);

        // gets first element if the selection is an IStructuredSelection.
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();
            // casts to GraphicalEditPart.
            if (firstElement instanceof GraphicalEditPart) {
                editPart = (GraphicalEditPart) firstElement;
            }
        }
        return editPart;
    }

    /**
     * Gets the BasicNode object from a GraphicalEditPart object.
     * 
     * @param element a GraphicalEditPart object.
     * @return the BasicNode object
     */
    protected BasicNode getBasicNode(GraphicalEditPart element) {

        if (element == null) {
            return null;
        }
        BasicNode basicNode = null;

        // gets the View object.
        Object model = element.getModel();
        if (model instanceof View) {
            View view = (View) model;
            // gets the EObject object.
            EObject eObj = view.getElement();
            // casts to BasicNode.
            if (eObj instanceof BasicNode) {
                basicNode = (BasicNode) eObj;
            }
        }
        return basicNode;
    }
}
