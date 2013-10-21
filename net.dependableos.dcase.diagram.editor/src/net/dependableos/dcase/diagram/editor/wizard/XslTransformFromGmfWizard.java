/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.xmlconv.XSLConverterItem;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.wizard.Wizard;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Creates a wizard of "Xsl transform".
 */
public class XslTransformFromGmfWizard extends Wizard implements INewWizard {

    /**
     * The wizard page to select an input file and an output file.
     */
    private XslTransformFromGmfWizardPage convertDefinitionPage = null;

    /**
     * The selected object.
     */
    private IStructuredSelection selection;
    /**
     * The converter definition.
     */
    private XSLConverterItem converter = null;

    /**
     * Initializes.
     * 
     * @param workbench the workbench.
     * @param selection the selected object.
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
        String converterName = ""; // $NON-NLS-1$
        if (converter != null) {
            converterName = converter.getName();
        }
        setWindowTitle(NLS.bind(
                Messages.XslTransformFromGmfWizardTitle, converterName));

        // sets whether this wizard needs a progress monitor. 
        setNeedsProgressMonitor(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        convertDefinitionPage = new XslTransformFromGmfWizardPage(selection);
        addPage(convertDefinitionPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        IRunnableWithProgress operation = new XslTransformFromGmfRunnable(
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage
                        .getInputFilePath().getText()),
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage
                        .getOutputFilePath().getText()),
                new File(converter.getPath()),
                convertDefinitionPage.isOverwriteOption());

        IWizardContainer container = getContainer();
        try {
            container.run(true, true, operation);
        } catch (InvocationTargetException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XslTransformCantStartThread, null,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        } catch (InterruptedException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XslTransformThreadInterrupted, null,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        }

        return true;
    }

    /**
     * Returns converter definition.
     * @return A converter object.
     */
    public XSLConverterItem getConverter() {
        return converter;
    }

    /**
     * Sets converter definition.
     * @param converter A converter object.
     */
    public void setConverter(XSLConverterItem converter) {
        this.converter = converter;
    }
}
