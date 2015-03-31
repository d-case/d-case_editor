/*
 * Copyright (C) 2015  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2015  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.editor.wizard.XmlConversionToExtSacmWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class XmlConversionToExtSacmWizardHandler extends AbstractHandler {

    /**
     * Converts GMF model file to Extended SACM file.
     * 
     * @param event the event.
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        
        XmlConversionToExtSacmWizard wizard = new XmlConversionToExtSacmWizard();
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
