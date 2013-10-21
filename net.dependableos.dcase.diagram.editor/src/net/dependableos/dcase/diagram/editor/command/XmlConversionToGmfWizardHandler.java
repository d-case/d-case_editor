/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;


import net.dependableos.dcase.diagram.editor.wizard.XmlConversionToGmfWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler to convert D-Case model file to GMF model file.
 */
public class XmlConversionToGmfWizardHandler extends AbstractHandler {

    /**
     * Converts GMF model file to D-Case model file.
     * 
     * @param event the event.
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        IWorkbenchWindow window = HandlerUtil
                .getActiveWorkbenchWindowChecked(event);

        XmlConversionToGmfWizard wizard = new XmlConversionToGmfWizard();
        ISelection sel = window.getSelectionService().getSelection();
        if (sel instanceof IStructuredSelection) {
            wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) sel);
        } else {
            wizard.init(PlatformUI.getWorkbench(), null);
        }

        WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
        dialog.open();

        return null;
    }

}
