/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.editor.logic.xmlconv.XSLConverterItem;
import net.dependableos.dcase.diagram.editor.wizard.XslTransformFromGmfWizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class XslConverterSelectionAdapter extends SelectionAdapter {
    /**
     * The converter definition.
     */
    private XSLConverterItem converter = null;

    /** 
     * Returns a converter definition.
     * 
     * @return A converter definition.
     */
    public XSLConverterItem getConverter() {
        return converter;
    }

    /**
     * Sets a converter definition.
     * 
     * @param converter A converter definition.
     */
    public void setConverter(XSLConverterItem converter) {
        this.converter = converter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        openWizard(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        openWizard(e);
    }
    /**
     * Show the scenario view.
     * 
     * @param e The selection event object.
     */
    private void openWizard(SelectionEvent e) {

        IWorkbenchWindow window = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow();
        XslTransformFromGmfWizard wizard = new XslTransformFromGmfWizard();
        ISelection sel = window.getSelectionService().getSelection();
        wizard.setConverter(converter);
        if (sel instanceof IStructuredSelection) {
            wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) sel);
        } else {
            wizard.init(PlatformUI.getWorkbench(), null);
        }

        WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
        dialog.open();
    }
}
