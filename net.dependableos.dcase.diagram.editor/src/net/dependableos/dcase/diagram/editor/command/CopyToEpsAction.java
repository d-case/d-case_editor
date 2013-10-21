/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DCASE_TOOL_FOLDER;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.EPS_CONVERT_TOOL_NAME;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.EPS_FILE_EXTENSION;

import java.io.File;
import java.lang.reflect.InvocationTargetException;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.copyimg.CopyToEpsRunnable;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to copy current editing D-Case diagram to EPS.
 */
public class CopyToEpsAction implements IObjectActionDelegate {
    /**
     * a wildcard character.
     */
    private static final String ASTERISK = "*"; //$NON-NLS-1$
    /**
     * the workbenck.
     */
    private IWorkbenchPart targetPart;

    /**
     * Sets the active part for the delegate.
     * 
     * @param action
     *            the action proxy that handles presentation portion of the
     *            action; must not be null.
     * @param targetPart
     *            the new part target; must not be null.
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    /**
     * Notifies this action delegate that the selection in the workbench has changed.
     * 
     * @param action the action proxy that handles presentation portion of 
     *      the action
     * @param selection the current selection, or null if there
     *      is no selection.
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * Returns the shell.
     * 
     * @return the shell.
     */
    private Shell getShell() {
        return targetPart.getSite().getShell();
    }

    /**
     * Copies current editing D-Case diagram to EPS.
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     * @param action the action proxy that handles the presentation portion of the action.
     */
    public void run(IAction action) {
        try {
            // gets the full path of the batch file to convert to EPS from PDF.
            String batFileName = getBatchFileName();
            // creates a dialog to select target file.
            FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
            dialog.setFilterExtensions(new String[] { ASTERISK
                    .concat(EPS_FILE_EXTENSION) });
            dialog.setText(Messages.CopyToEpsAction_3);
            String epsFileName = dialog.open();
            // terminates if canceled.
            if (epsFileName == null) {
                return;
            }
            // completes file extension.
            if (!epsFileName.toLowerCase().endsWith(EPS_FILE_EXTENSION)) {
                epsFileName = epsFileName.concat(EPS_FILE_EXTENSION);
            }
            File epsFile = new File(epsFileName);
            if (epsFile.exists()) {
                // confirms overridation.
                boolean selectOk = MessageDialog.open(MessageDialog.QUESTION,
                        this.getShell(), Messages.CopyToEpsAction_4,
                        Messages.CopyToEpsAction_5, SWT.NONE);
                if (!selectOk) {
                    return;
                }
            }
            // creates an EPS file.
            createEpsFile(epsFileName, batFileName);
        } catch (DcaseSystemException e) {
            MessageWriter.writeMessageToErrorLog(e);
            MessageWriter.showMessageBoxSeeErroLog();
        }
    }

    /**
     * Creates an EPS file.
     * 
     * @param epsFileName target file name.
     * @param batchFileName the full path of the batch file to convert to EPS from PDF.
     */
    private void createEpsFile(String epsFileName, String batchFileName) {
        // creates a thread.
        IRunnableWithProgress runnable = new CopyToEpsRunnable(epsFileName,
                batchFileName);
        // creates a monitor dialog.
        ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(
                getShell());
        try {
            progressMonitorDialog.run(false, true, runnable);
        } catch (InvocationTargetException e) {
            throw new DcaseSystemException(Messages.CopyToEpsAction_1, e,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        } catch (InterruptedException e) {
            throw new DcaseSystemException(Messages.CopyToEpsAction_1, e,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        }
    }

    /**
     * Returns the full path of the batch file to convert to EPS from PDF.
     * 
     * @return the batch filename
     */
    private String getBatchFileName() {
        String batchFile = null;
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        // gets the full path of the batch file to convert to EPS from PDF.
        String rootFullPath = root.getLocation().toOSString();
        IPath basePath = new Path(rootFullPath);
        basePath = basePath.append(DCASE_TOOL_FOLDER);
        basePath = basePath.append(EPS_CONVERT_TOOL_NAME);

        File file = new File(basePath.toOSString());
        // terminates if the file does not exist.
        if (!file.exists()) {
            throw new DcaseSystemException(Messages.CopyToEpsAction_2, null,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        } else {
            batchFile = file.getPath();
        }
        return batchFile;
    }
}
