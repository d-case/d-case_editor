/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
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
 * Creates a wizard of "GMF Model To ARM Wizard".
 */
public class XmlConversionToArmWizard extends Wizard implements INewWizard {

    /**
     * Conversion file definition page.
     */
    private XmlConversionToArmWizardPage convertDefinitionPage;

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
        setWindowTitle(Messages.XmlConversionToArmWizard_3);
        
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
        convertDefinitionPage = new XmlConversionToArmWizardPage(selection);
        addPage(convertDefinitionPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public boolean performFinish() {
        IRunnableWithProgress operation = new XmlConversionToArmRunnable(
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage.getInputFilePath().getText()),
                FileUtil.getWorksapceFileFromPath(convertDefinitionPage.getOutputFilePath().getText()),
                convertDefinitionPage.isOverwriteOption());
        
        IWizardContainer container = getContainer();
        try {
            // Creates the output file.
            container.run(true, true, operation);
        } catch (InvocationTargetException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToArmWizard_1, null,
                    MessageTypeImpl.CONVERT_TO_ARM_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        } catch (InterruptedException e) {
            DcaseSystemException dcaseSystemException = new DcaseSystemException(
                    Messages.XmlConversionToArmWizard_2, null,
                    MessageTypeImpl.CONVERT_TO_ARM_FAILED);
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
            return false;
        }

        return true;
    }

}
