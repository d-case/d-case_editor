/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.systemnode;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A handler to open the linked diagram of a system node.
 */
public class OpenLinkedDiagramHandler extends AbstractSystemNodeHandler {

    /**
     * Opens the linked diagram of a system node.
     * 
     * @param event the event.
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler
     *      #execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the selected edit part.
        SystemEditPart selectEditPart = getSelectedEditPart(event);
        if (selectEditPart != null) {
            // gets the node link.
            net.dependableos.dcase.System systemNode = getSystem(selectEditPart);
            String nodeLink = systemNode.getNodeLink();

            if (nodeLink != null && !nodeLink.equals("")) {
                IFile file = FileUtil.getWorksapceFileFromPath(nodeLink);
                if (file != null && file.exists()) {
                    // opens the diagram.
                    IEditorInput input = new FileEditorInput(file);
                    IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getActivePage();
                    try {
                        workbenchPage.openEditor(input, DcaseDiagramEditor.ID);
                    } catch (PartInitException pie) {
                        DcaseSystemException dse = new DcaseSystemException(
                                Messages.COMMON_EXCEPTION_partInit, 
                                pie,
                                MessageTypeImpl.UNDEFINED);
                        // handles the exception.
                        MessageWriter.writeMessageToErrorLog(dse);
                        MessageWriter.showMessageBoxSeeErroLog();
                    }
                } else {
                    showErrorMessageBox();
                }
            } else {
                showErrorMessageBox();
            }
        }
        return null;
    }
    /**
     * Shows the error message.
     */
    private void showErrorMessageBox() {
        MessageWriter.showErrorMessageBox(Messages.OpenLinkedDiagramHandler_0);
    }
}
