/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class that command handler of Copy to D-Case files.
 */
public class CopyDcaseFileHandler extends AbstractHandler {

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

            // gets model.
            IFile selectedModelFile = ModelUtil
                    .getModelFileFromDiagramFile(selectedDiagramFile);
            if (!selectedModelFile.exists()) {
                MessageWriter
                        .showErrorMessageBox(Messages.CopyDcaseFileSelectionAdapter_1);
                return null;
            }
            // shows a dialog to select target.
            SaveAsDialog dialog = new SaveAsDialog(DcaseEditorUtil
                    .getActiveWindowShell());
            dialog.setOriginalFile(selectedDiagramFile);
            dialog.create();
            dialog.setTitle(Messages.CopyDcaseFileSelectionAdapter_2);
            dialog.setMessage(Messages.CopyDcaseFileSelectionAdapter_3);
            dialog.getShell().setText(Messages.CopyDcaseFileSelectionAdapter_2);

            // terminates if canceled.
            if (dialog.open() == Window.CANCEL) {
                return null;
            }
            // gets target path.
            IPath destDiagramPath = dialog.getResult();
            if (destDiagramPath == null) {
                return null;
            }
            // removes file extension.
            
            IPath destBase = destDiagramPath.removeFileExtension();

            // copies diagram.
            IFile destDiagramFile = ResourcesPlugin.getWorkspace().getRoot()
                    .getFile(
                            destBase.addFileExtension(selectedDiagramFile
                                    .getFileExtension()));
            // copies model.
            IFile destModelPath = FileUtil.copyFileTo(selectedModelFile,
                    destBase.addFileExtension(selectedModelFile.getFileExtension()));

            // updates the reference to the GMF model file of the GMF diagram file.
            ModelUtil.updateModelFileReference(selectedDiagramFile,
                    destModelPath, destDiagramFile, false);
        } catch (DcaseSystemException de) {
            MessageWriter.writeMessageToErrorLog(de);
            MessageWriter.showMessageBoxSeeErroLog();
        }
        return null;
    }

}
