/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 */
public class DcaseCreationWizard extends Wizard implements INewWizard {

    /**
     * @generated
     */
    private IWorkbench workbench;

    /**
     * @generated
     */
    protected IStructuredSelection selection;

    /**
     * @generated
     */
    protected DcaseCreationWizardPage diagramModelFilePage;

    /**
     * @generated
     */
    protected DcaseCreationWizardPage domainModelFilePage;

    /**
     * @generated
     */
    protected Resource diagram;

    /**
     * @generated
     */
    private boolean openNewlyCreatedDiagramEditor = true;

    /**
     * @generated
     */
    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * @generated
     */
    public IStructuredSelection getSelection() {
        return selection;
    }

    /**
     * @generated
     */
    public final Resource getDiagram() {
        return diagram;
    }

    /**
     * @generated
     */
    public final boolean isOpenNewlyCreatedDiagramEditor() {
        return openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void setOpenNewlyCreatedDiagramEditor(
            boolean openNewlyCreatedDiagramEditor) {
        this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
    }

    /**
     * @generated
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(Messages.DcaseCreationWizardTitle);
        setDefaultPageImageDescriptor(DcaseDiagramEditorPlugin
                .getBundledImageDescriptor("icons/wizban/NewDcaseWizard.gif")); //$NON-NLS-1$
        setNeedsProgressMonitor(true);
    }

    /**
     * @generated NOT
     */
    public void addPages() {
        diagramModelFilePage = new DcaseCreationWizardPage(
                "DiagramModelFile", getSelection(), "dcase_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
        diagramModelFilePage
                .setTitle(Messages.DcaseCreationWizard_DiagramModelFilePageTitle);
        diagramModelFilePage
                .setDescription(Messages.DcaseCreationWizard_DiagramModelFilePageDescription);
        addPage(diagramModelFilePage);

        domainModelFilePage = new DcaseCreationWizardPage(
                "DomainModelFile", getSelection(), "dcase_model") { //$NON-NLS-1$ //$NON-NLS-2$

            // indicates whether the page has shown.
            private boolean nextFlg = false;

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length()
                            - ".dcase_diagram".length()); //$NON-NLS-1$
                    setFileName(DcaseDiagramEditorUtil.getUniqueFileName(
                            getContainerFullPath(), fileName, "dcase_model")); //$NON-NLS-1$
                }
                super.setVisible(visible);

                nextFlg = true;
            }

            // AUTO_GENERATED:END
            @Override
            public boolean isPageComplete() {

                boolean result = super.isPageComplete();

                // returns false if the page has not shown.
                if (!nextFlg) {
                    result = false;
                }

                return result;
            }
            // AUTO_GENERATED:START
        };
        domainModelFilePage
                .setTitle(Messages.DcaseCreationWizard_DomainModelFilePageTitle);
        domainModelFilePage
                .setDescription(Messages.DcaseCreationWizard_DomainModelFilePageDescription);
        addPage(domainModelFilePage);
    }

    /**
     * @generated
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            protected void execute(IProgressMonitor monitor)
                    throws CoreException, InterruptedException {
                diagram = DcaseDiagramEditorUtil.createDiagram(
                        diagramModelFilePage.getURI(), domainModelFilePage
                                .getURI(), monitor);
                if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
                    try {
                        DcaseDiagramEditorUtil.openDiagram(diagram);
                    } catch (PartInitException e) {
                        ErrorDialog.openError(getContainer().getShell(),
                                Messages.DcaseCreationWizardOpenEditorError,
                                null, e.getStatus());
                    }
                }
            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(),
                        Messages.DcaseCreationWizardCreationError, null,
                        ((CoreException) e.getTargetException()).getStatus());
            } else {
                DcaseDiagramEditorPlugin.getInstance().logError(
                        "Error creating diagram", e.getTargetException()); //$NON-NLS-1$
            }
            return false;
        }
        return diagram != null;
    }
}
