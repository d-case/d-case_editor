/*
 * Copyright (C) 2015  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2015  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import java.lang.reflect.InvocationTargetException;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Creates a wizard of "GMF Model To Extended SACM Wizard".
 */
public class XmlConversionToExtSacmWizard extends Wizard implements INewWizard {

    /**
     * Conversion file definition page.
     */
    private XmlConversionToExtSacmWizardPage convertDefinitionPage;

    /**
     * The IStructuredSelection object which is chosen at the time of Wizard start in a work space.
     */
    private IStructuredSelection selection;

    /**
     * Initializes the Wizard.
     * 
     * @param workbench The workbench
     * @param selection The selection object from workspace
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
        setWindowTitle(Messages.XmlConversionToExtSacmWizard_3);
        
        // Validates the progress monitor.
        setNeedsProgressMonitor(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        convertDefinitionPage = new XmlConversionToExtSacmWizardPage(selection);
        addPage(convertDefinitionPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public boolean performFinish() {
        IRunnableWithProgress operation = new XmlConversionToExtSacmRunnable(
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage.getInputFilePath().getText()),
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage.getOutputFilePath().getText()),
                convertDefinitionPage.isOverwriteOption());
        
        IWizardContainer container = getContainer();
        try {
            // Creates the output file.
            container.run(true, true, operation);
        } catch (InvocationTargetException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToExtSacmWizard_1, null,
                    MessageTypeImpl.CONVERT_TO_SACM_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        } catch (InterruptedException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToExtSacmWizard_2, null,
                    MessageTypeImpl.CONVERT_TO_SACM_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        }

        return true;
    }

}
