/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.common.util.UserInterfaceUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.ui.RenameDcaseFileDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class that command handler of Rename to D-Case files.
 */
public class RenameDcaseFileHandler extends AbstractHandler {

    /**
     * Execute command.
     * 
     * @param event the event argument.
     * @throws ExecutionException the ExecutionException.
     * @return null.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        try {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (!(selection instanceof StructuredSelection)) {
                return null;
            }

            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.isEmpty()) {
                return null;
            }

            // gets the diagram file.
            IFile selectedDiagramFile = (IFile) structuredSelection.getFirstElement();

            // gets the model file.
            IFile selectedModelFile = ModelUtil.
                    getModelFileFromDiagramFile(selectedDiagramFile);

            if (UserInterfaceUtil.isFileOpenedWithEditor(selectedDiagramFile)) {
                MessageWriter
                    .showErrorMessageBox(Messages.RenameDcaseFileHandler_0);
                return null;
            }

            if (!selectedModelFile.exists()) {
                MessageWriter
                        .showErrorMessageBox(Messages.RenameDcaseFileHandler_1);
                return null;
            } else {
                // shows error message and terminates if the files are being edited.
                if (UserInterfaceUtil
                        .isFileOpenedWithEditor(selectedModelFile)) {
                    MessageWriter
                            .showErrorMessageBox(Messages.RenameDcaseFileHandler_2);
                    return null;
                }
            }

            // shows the dialog to input new name.
            RenameDcaseFileDialog dialog = new RenameDcaseFileDialog(
                    DcaseEditorUtil.getActiveWindowShell());
            dialog.setFilename(selectedDiagramFile.getFullPath().removeFileExtension().lastSegment());
            if (Dialog.OK != dialog.open()) {
                return null;
            }

            // gets new name.
            String newFilename = dialog.getInputedFilename();
            // renames the diagram file.
            IPath basePath = selectedDiagramFile.getParent().getFullPath();
            IFile newDiagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
                    basePath.append(newFilename).addFileExtension(selectedDiagramFile.getFileExtension()));
            if (newDiagramFile == null) {
                MessageWriter
                        .showErrorMessageBox(Messages.RenameDcaseFileHandler_3);
                return null;
            }
            // shows error message and terminates if the specified name is already used.
            if (newDiagramFile.exists()) {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.RenameDcaseFileHandler_4,
                        newDiagramFile.getFullPath().toString()));
                return null;
            }
            // renames the model file.
            IFile newModelFile = FileUtil.renameFile(selectedModelFile,
                    newFilename);
            if (newModelFile == null) {
                MessageWriter
                        .showErrorMessageBox(Messages.RenameDcaseFileHandler_5);
                return null;
            }
            // shows error message and terminates if the specified name is already used.
            if (selectedModelFile.exists()) {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.RenameDcaseFileHandler_4,
                        newModelFile.getFullPath().toString()));
                return null;
            }
            // update reference to the model file in the diagram file.
            ModelUtil.updateModelFileReference(selectedDiagramFile,
                    newModelFile, newDiagramFile, true);

        } catch (DcaseSystemException de) {
            de.setMessageType(MessageTypeImpl.RENAME_FILE_FAILED);
            MessageWriter.writeMessageToErrorLog(de);
            MessageWriter.showMessageBoxSeeErroLog();
        }

        return null;
    }

}
