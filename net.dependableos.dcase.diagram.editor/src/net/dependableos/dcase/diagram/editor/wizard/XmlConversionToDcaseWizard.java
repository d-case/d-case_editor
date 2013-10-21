/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import java.lang.reflect.InvocationTargetException;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard to convert a GMF model file to a D-Case file.
 */
public class XmlConversionToDcaseWizard extends Wizard implements INewWizard {

    /**
     * the wizard page to select an input file and an output file.
     */
    private XmlConversionToDcaseWizardPage convertDefinitionPage;

    /**
     * the selected object.
     */
    private IStructuredSelection selection;

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
        setWindowTitle(Messages.XmlConversionToDcaseWizard_0);

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
        convertDefinitionPage = new XmlConversionToDcaseWizardPage(selection);
        addPage(convertDefinitionPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        IRunnableWithProgress operation = new XmlConversionToDcaseRunnable(
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage
                        .getInputFilePath().getText()), FileUtil
                        .getWorksapceFileFromPath(convertDefinitionPage
                                .getOutputFilePath().getText()),
                convertDefinitionPage.isOverwriteOption());

        IWizardContainer container = getContainer();
        try {
            container.run(true, true, operation);
        } catch (InvocationTargetException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToDcaseWizard_1, null,
                    MessageTypeImpl.CONVERT_TO_DCASE_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        } catch (InterruptedException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToDcaseWizard_2, null,
                    MessageTypeImpl.CONVERT_TO_DCASE_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        }

        return true;
    }

}
