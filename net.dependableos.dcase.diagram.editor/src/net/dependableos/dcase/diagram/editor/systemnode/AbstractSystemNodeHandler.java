/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.systemnode;


import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * An abstract class that handles events for a System node.
 */
public abstract class AbstractSystemNodeHandler extends AbstractHandler {

    /**
     * Returns the selected edit part that represents a System node.
     * 
     * @param event the event.
     * @return the selected edit part that represents a System node.
     */
    protected SystemEditPart getSelectedEditPart(ExecutionEvent event) {

        SystemEditPart editPart = null;

        ISelection selection = HandlerUtil.getActiveMenuSelection(event);
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof SystemEditPart) {
                editPart = (SystemEditPart) firstElement;
            }
        }

        return editPart;
    }

    /**
     * Returns the model of the System node.
     * 
     * @param editPart the System node.
     * @return the model of the System node.
     */
    protected net.dependableos.dcase.System getSystem(SystemEditPart editPart) {

        if (editPart == null) {
            return null;
        }

        net.dependableos.dcase.System systemObj = null;

        Object model = editPart.getModel();
        if (model instanceof View) {
            View view = (View) model;
            EObject eObj = view.getElement();
            if (eObj instanceof net.dependableos.dcase.System) {
                systemObj = (net.dependableos.dcase.System) eObj;
            }
        }

        return systemObj;
    }
}
