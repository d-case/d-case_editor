/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @generated
 */
public class DcaseDiagramActionBarContributor extends
        DiagramActionBarContributor {

    /**
     * @generated
     */
    protected Class getEditorClass() {
        return DcaseDiagramEditor.class;
    }

    /**
     * @generated
     */
    protected String getEditorId() {
        return DcaseDiagramEditor.ID;
    }

    /**
     * @generated
     */
    public void init(IActionBars bars, IWorkbenchPage page) {
        super.init(bars, page);
        // print preview
        IMenuManager fileMenu = bars.getMenuManager().findMenuUsingPath(
                IWorkbenchActionConstants.M_FILE);
        assert fileMenu != null;
        IAction printPreviewAction = new RenderedPrintPreviewAction(
                new EnhancedPrintActionHelper());
        fileMenu.insertBefore("print", printPreviewAction); //$NON-NLS-1$
        IMenuManager editMenu = bars.getMenuManager().findMenuUsingPath(
                IWorkbenchActionConstants.M_EDIT);
        assert editMenu != null;
        if (editMenu.find("validationGroup") == null) { //$NON-NLS-1$
            editMenu.add(new GroupMarker("validationGroup")); //$NON-NLS-1$
        }
        IAction validateAction = new ValidateAction(page);
        editMenu.appendToGroup("validationGroup", validateAction); //$NON-NLS-1$
    }
}
