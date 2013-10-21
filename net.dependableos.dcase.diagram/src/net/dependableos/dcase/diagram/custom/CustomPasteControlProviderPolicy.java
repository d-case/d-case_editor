/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.custom;

import java.util.Iterator;

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderPolicy;
import org.eclipse.gmf.runtime.common.ui.services.action.global.GlobalActionHandlerOperation;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A policy for the custom paste control.
 */
public class CustomPasteControlProviderPolicy implements IProviderPolicy {

    /**
     * Indicates whether this provider provides the specified operation. 
     * This provider does not provide the paste operation if any link is selected. 
     * 
     * @param operation the operation in question. 
     * @return true if and only if this provider provides the operation; false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean provides(IOperation operation) {

        if (operation instanceof GlobalActionHandlerOperation) {

            GlobalActionHandlerOperation globalOperation = (GlobalActionHandlerOperation) operation;
            IGlobalActionHandlerContext context = globalOperation.getContext();

            // tests whether the action id is "paste".
            if ("paste".equals(context.getActionId())) { //$NON-NLS-1$

                // gets selected objects
                IWorkbench workbench = PlatformUI.getWorkbench();
                IWorkbenchWindow activeWindow = workbench
                        .getActiveWorkbenchWindow();
                ISelection sel = activeWindow.getSelectionService()
                        .getSelection();
                if (sel instanceof IStructuredSelection) {
                    IStructuredSelection structuredSel = (IStructuredSelection) sel;
                    Iterator it = structuredSel.iterator();
                    while (it.hasNext()) {
                        Object obj = it.next();

                        // returns false if any link is selected. 
                        if (obj instanceof ConnectionNodeEditPart) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
