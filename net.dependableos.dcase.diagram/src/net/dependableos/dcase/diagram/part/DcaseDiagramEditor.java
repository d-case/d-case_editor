/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;


import java.io.IOException;
import java.util.Collections;

import net.dependableos.dcase.diagram.navigator.DcaseNavigatorItem;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;

/**
 * @generated
 */
public class DcaseDiagramEditor extends DiagramDocumentEditor implements
        IGotoMarker {

    /**
     * @generated
     */
    public static final String ID = "net.dependableos.dcase.diagram.part.DcaseDiagramEditorID"; //$NON-NLS-1$
    /**
     * @generated
     */
    public static final String CONTEXT_ID = "net.dependableos.dcase.diagram.ui.diagramContext"; //$NON-NLS-1$
    
    /**
     * the target file to compare.
     */
    private IFile compareTargetFile = null;

    /**
     * the flag for changing parameters
     */
    private boolean notifyFlag = false;
    
    /**
     * @generated
     */
    public DcaseDiagramEditor() {
        super(true);
    }

    /**
     * @generated
     */
    protected String getContextID() {
        return CONTEXT_ID;
    }

    /**
     * @generated
     */
    protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
        PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
        new DcasePaletteFactory().fillPalette(root);
        return root;
    }

    /**
     * @generated
     */
    protected PreferencesHint getPreferencesHint() {
        return DcaseDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
    }

    /**
     * @generated
     */
    public String getContributorId() {
        return DcaseDiagramEditorPlugin.ID;
    }

    /**
     * @generated
     */
    public Object getAdapter(Class type) {
        if (type == IShowInTargetList.class) {
            return new IShowInTargetList() {
                public String[] getShowInTargetIds() {
                    return new String[] { ProjectExplorer.VIEW_ID };
                }
            };
        }
        return super.getAdapter(type);
    }

    /**
     * @generated
     */
    protected IDocumentProvider getDocumentProvider(IEditorInput input) {
        if (input instanceof IFileEditorInput
                || input instanceof URIEditorInput) {
            return DcaseDiagramEditorPlugin.getInstance().getDocumentProvider();
        }
        return super.getDocumentProvider(input);
    }

    /**
     * @generated
     */
    public TransactionalEditingDomain getEditingDomain() {
        IDocument document = getEditorInput() != null ? getDocumentProvider()
                .getDocument(getEditorInput()) : null;
        if (document instanceof IDiagramDocument) {
            return ((IDiagramDocument) document).getEditingDomain();
        }
        return super.getEditingDomain();
    }

    /**
     * @generated
     */
    protected void setDocumentProvider(IEditorInput input) {
        if (input instanceof IFileEditorInput
                || input instanceof URIEditorInput) {
            setDocumentProvider(DcaseDiagramEditorPlugin.getInstance()
                    .getDocumentProvider());
        } else {
            super.setDocumentProvider(input);
        }
    }

    /**
     * @generated
     */
    public void gotoMarker(IMarker marker) {
        MarkerNavigationService.getInstance().gotoMarker(this, marker);
    }

    /**
     * @generated
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * @generated
     */
    public void doSaveAs() {
        performSaveAs(new NullProgressMonitor());
    }

    /**
     * @generated NOT
     */
    protected void performSaveAs(IProgressMonitor progressMonitor) {
        Shell shell = getSite().getShell();
        IEditorInput input = getEditorInput();
        SaveAsDialog dialog = new SaveAsDialog(shell);
        IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
                .getFile()
                : null;
        if (original != null) {
            dialog.setOriginalFile(original);
        }
        dialog.create();
        IDocumentProvider provider = getDocumentProvider();
        if (provider == null) {
            // editor has been programmatically closed while the dialog was open
            return;
        }
        if (provider.isDeleted(input) && original != null) {
            String message = NLS.bind(
                    Messages.DcaseDiagramEditor_SavingDeletedFile, original
                            .getName());
            dialog.setErrorMessage(null);
            dialog.setMessage(message, IMessageProvider.WARNING);
        }
        if (dialog.open() == Window.CANCEL) {
            if (progressMonitor != null) {
                progressMonitor.setCanceled(true);
            }
            return;
        }
        IPath filePath = dialog.getResult();
        if (filePath == null) {
            if (progressMonitor != null) {
                progressMonitor.setCanceled(true);
            }
            return;
        }
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IFile file = workspaceRoot.getFile(filePath);
        final IEditorInput newInput = new FileEditorInput(file);
        // Check if the editor is already open
        IEditorMatchingStrategy matchingStrategy = getEditorDescriptor()
                .getEditorMatchingStrategy();
        IEditorReference[] editorRefs = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .getEditorReferences();
        for (int i = 0; i < editorRefs.length; i++) {
            if (matchingStrategy.matches(editorRefs[i], newInput)) {
                MessageDialog.openWarning(shell,
                        Messages.DcaseDiagramEditor_SaveAsErrorTitle,
                        Messages.DcaseDiagramEditor_SaveAsErrorMessage);
                return;
            }
        }
        boolean success = false;
        try {
            try {
                IPath modelPath = file.getFullPath().
                        removeFileExtension().addFileExtension("dcase_model");   //$NON-NLS-1$
                IFile modelFile = workspaceRoot.getFile(modelPath);
                final EObject model = getDiagram().getElement();
                ResourceSet resourceSet = model.eResource().getResourceSet();
                final Resource resource = resourceSet.createResource(URI.
                        createPlatformResourceURI(modelFile.getFullPath()
                                .toString(), true));
                
                new AbstractTransactionalCommand(getEditingDomain(), "Save Model", null) {   //$NON-NLS-1$
                    
                    @Override
                    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
                            IAdaptable info) throws ExecutionException {
                        resource.getContents().add(model);
                        try {
                            resource.save(Collections.emptyMap());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        getDiagram().setElement(model);
                        return CommandResult.newOKCommandResult();
                    }
                }.execute(progressMonitor, null);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            provider.aboutToChange(newInput);
            getDocumentProvider(newInput).saveDocument(progressMonitor,
                    newInput,
                    getDocumentProvider().getDocument(getEditorInput()), true);
            success = true;
        } catch (CoreException x) {
            IStatus status = x.getStatus();
            if (status == null || status.getSeverity() != IStatus.CANCEL) {
                ErrorDialog.openError(shell,
                        Messages.DcaseDiagramEditor_SaveErrorTitle,
                        Messages.DcaseDiagramEditor_SaveErrorMessage, x
                                .getStatus());
            }
        } finally {
            provider.changed(newInput);
            if (success) {
                setInput(newInput);
            }
        }
        if (progressMonitor != null) {
            progressMonitor.setCanceled(!success);
        }
    }

    /**
     * @generated
     */
    public ShowInContext getShowInContext() {
        return new ShowInContext(getEditorInput(), getNavigatorSelection());
    }

    /**
     * @generated
     */
    private ISelection getNavigatorSelection() {
        IDiagramDocument document = getDiagramDocument();
        if (document == null) {
            return StructuredSelection.EMPTY;
        }
        Diagram diagram = document.getDiagram();
        IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
        if (file != null) {
            DcaseNavigatorItem item = new DcaseNavigatorItem(diagram, file,
                    false);
            return new StructuredSelection(item);
        }
        return StructuredSelection.EMPTY;
    }

    /**
     * @generated NOT
     */
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(
                this, getDiagramGraphicalViewer());
        getDiagramGraphicalViewer().setContextMenu(provider);
        getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
                provider, getDiagramGraphicalViewer());

        // sets a key handler.
        IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
        KeyHandler dcaseKeyHandler = new DcaseDiagramKeyHandler(viewer);
        dcaseKeyHandler.setParent(viewer.getKeyHandler());
        viewer.setKeyHandler(dcaseKeyHandler);
    }

    /**
     * Returns the target file to compare.
     * 
     * @return the target file to compare.
     */
    public IFile getCompareTargetFile() {
        return compareTargetFile;
    }

    /**
     * Sets the target file to compare.
     * 
     * @param compareTargetFile the target file to compare.
     */
    public void setCompareTargetFile(IFile compareTargetFile) {
        this.compareTargetFile = compareTargetFile;
    }
    
    /**
     * Returns the notify flag.
     * @return the notify flag.
     */
    public boolean getNotifyFlag() {
    	return notifyFlag;
    }
    
    /**
     * Sets the notify flag.
     * @param flag the notify flag.
     */
    public void setNotifyFlag(boolean flag) {
    	notifyFlag = flag;
    }
    
    /**
     * Applies parameters when changing ancestor's parameters.
     */
    protected void handleSelectionChanged() {
    	super.handleSelectionChanged();
    	if(notifyFlag) {
    		final DiagramEditPart editPart = this.getDiagramEditPart();
    		if(editPart instanceof ArgumentEditPart) {
    			TransactionalEditingDomain domain =
    					GMFEditingDomainFactory.INSTANCE.getEditingDomain(getDiagram().eResource().getResourceSet());
    			domain.getCommandStack().execute(new RecordingCommand(domain) {
    				public void doExecute() {
    					((ArgumentEditPart)editPart).notifyRootNodes();
    			   }
    			});
    		}
    		notifyFlag = false;
    	}
    }

}
