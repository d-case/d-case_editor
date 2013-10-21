/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * A handler to open attachment with the default editor.
 */
public class OpenDefaultEditorHandler extends AbstractEditPartHandler {

    /**
     * Opens attachment with the default editor.
     * 
     * @param event ExecutionEvent
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the selected node.
        GraphicalEditPart selectedElement = getSelectedElement(event);

        if (selectedElement != null) {
            try {
                // gets the BasicNode object.
                BasicNode basicNode = getBasicNode(selectedElement);

                // gets the IFile object that represents attachment.
                IFile file = getIFile(basicNode);

                // opens the attachment with default editor.
                openFileEditor(file);

            } catch (DcaseRuntimeException dre) {
                // handles the runtime exception.
                MessageWriter.showErrorMessageBox(dre.getMessage());
            } catch (DcaseSystemException dse) {
                // handles the d-case system exception.
                MessageWriter.writeMessageToErrorLog(dse);
                MessageWriter.showMessageBoxSeeErroLog();
            }
        }
        return null;
    }

    /**
     * Returns the IFile object that represents attachment.
     * 
     * @param basicNode the BasicNode object.
     * @return the IFile object that represents attachment.
     */
    private IFile getIFile(BasicNode basicNode) {

        // gets the file path.
        String filePath = basicNode.getAttachment();

        // throws exception if the path is null or empty.
        if (filePath == null || filePath.trim().length() == 0) {
            throw new DcaseRuntimeException(
                    Messages.OpenDefaultEditorHandler_0, null, null, 0,
                    MessageTypeImpl.OPEN_ATTACHMENT_INVALID_FILE);
        }
        // gets the IFile object.
        IFile file = FileUtil.getWorksapceFileFromPath(filePath);

        // throws exception if the file doesn't exist.
        if (file == null || !file.exists()) {
            throw new DcaseRuntimeException(
                    Messages.OpenDefaultEditorHandler_1, null, null, 0,
                    MessageTypeImpl.OPEN_ATTACHMENT_INVALID_FILE);
        }

         return file;
    }

    /**
     * Opens the file with the default editor.
     * 
     * @param file the file to open.
     */
    private void openFileEditor(IFile file) {

        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
        try {
            IDE.openEditor(workbenchPage, file);
        } catch (PartInitException e) {
            throw new DcaseSystemException(Messages.COMMON_EXCEPTION_partInit,
                    e, MessageTypeImpl.OPEN_ATTACHMENT_CRITICAL_FAILED);
        }
    }
}