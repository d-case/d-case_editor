/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.copyimg;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.PDF_FILE_EXTENSION;

import java.io.File;
import java.io.IOException;
import java.util.List;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * A class that outputs a D-Case diagram to an EPS image.
 */
public class CopyToEpsRunnable implements IRunnableWithProgress {
    /**
     * the name of the EPS file.
     */
    private String epsFileName;
    /**
     * the command to convert to EPS.
     */
    private String batFileName;
    /**
     * the prefix of a PDF file.
     */
    private static final String TEMP_FILE_PREFIX = "temp_"; //$NON-NLS-1$
    /**
     * the count of the job.
     */
    private static final int CREATE_EPS_TOTAL_WORK = 10;

    /**
     * Creates an instance and initializes it.
     * 
     * @param epsFileName the name of the EPS file.
     * @param batFileName the command to convert to EPS.
     */
    public CopyToEpsRunnable(String epsFileName, String batFileName) {
        this.epsFileName = epsFileName;
        this.batFileName = batFileName;
    }

    /**
     * Outputs a D-Case diagram to a PDF file and converts it to a EPS file.
     * 
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     * @param monitor the progress monitor to use to display progress and receive requests for cancellation. 
     */
    public void run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.CopyToEpsRunnable_4,
                    CREATE_EPS_TOTAL_WORK);
            monitor.setTaskName(epsFileName);
            // Outputs a D-Case diagram to a PDF file
            String pdfFileName = createPDFFilename();
            monitor.worked(1);

            outputImageFile(pdfFileName, monitor);
            monitor.worked(1);

            // converts the PDF file to the EPS.
            runBatch(pdfFileName);
            monitor.worked(1);

            File pdfFile = new File(pdfFileName);
            if (!pdfFile.delete()) {
                MessageWriter.writeMessageToConsole(
                        Messages.CopyToEpsRunnable_5,
                        MessageTypeImpl.COPY_TO_EPS_FAILED);
            }
            monitor.worked(1);
        } catch (DcaseSystemException e) {
            MessageWriter.writeMessageToErrorLog(e);
            MessageWriter.showMessageBoxSeeErroLog();
        } finally {
            monitor.done();
        }
    }

    /**
     * Creates a PDF file name.
     * 
     * @return a PDF file name.
     */
    private String createPDFFilename() {
         String parentPath = new File(epsFileName).getParentFile().getPath();
        String fileName = getNewFileName(new Path(parentPath));
        while (new File(fileName).exists()) {
            fileName = getNewFileName(new Path(parentPath));
        }
        return fileName;
    }

    /**
     * Returns a new file name.
     * 
     * @param parentPath the path to the folder.
     * @return a new file name.
     */
    private String getNewFileName(IPath parentPath) {
        long timeStampL = System.currentTimeMillis();
        parentPath = parentPath.append(TEMP_FILE_PREFIX.concat(
                String.valueOf(timeStampL)).concat(PDF_FILE_EXTENSION));
        return parentPath.toOSString();
    }

    /**
     * Outputs a D-Case diagram to a PDF file.
     * 
     * @param pdfFileName a PDF file name.
     * @param monitor the progress monitor to use to display progress and receive requests for cancellation. 
     */
    @SuppressWarnings("unchecked")
    private void outputImageFile(String pdfFileName, IProgressMonitor monitor) {
        try {
            List<?> editparts = getSelectedObjects();
            CopyToImageUtil copyToImageUtil = new CopyToImageUtil();
            if (editparts.size() == 1
                    && editparts.get(0) instanceof DiagramEditPart) {
                copyToImageUtil.copyToImage((DiagramEditPart) editparts.get(0),
                        new Path(pdfFileName), ImageFileFormat.PDF, monitor);
            } else {
                DiagramImageUtils.zOrderSort(
                        (List<GraphicalEditPart>) editparts,
                        LayerManager.Helper.find(getDiagramEditPart())
                                .getLayer(LayerConstants.PRINTABLE_LAYERS));
                copyToImageUtil.copyToImage(getDiagramEditPart(), editparts,
                        new Path(pdfFileName), ImageFileFormat.PDF, monitor);
            }
        } catch (CoreException e) {
            throw new DcaseSystemException(Messages.CopyToEpsRunnable_1, e,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        }
    }

    /**
     * Returns the selected objects.
     * 
     * @return the selected objects.
     */
    protected List<?> getSelectedObjects() {
        ISelection selection = null;
        ISelectionService selectionService = null;
        if (getWorkbenchWindow() != null) {
            selectionService = getWorkbenchWindow().getSelectionService();
        }
        if (selectionService != null) {
            selection = selectionService.getSelection();
        }
        if (selection == null || !(selection instanceof StructuredSelection)) {
            selection = StructuredSelection.EMPTY;
        }
        return ((StructuredSelection) selection).toList();
    }

    /**
     * Returns the current diagram edit part.
     * 
     * @return the current diagram edit part.
     */
    protected DiagramEditPart getDiagramEditPart() {
        IDiagramWorkbenchPart part = DcaseEditorUtil.getCurrentDcaseEditor();
        if (part != null) {
            return part.getDiagramEditPart();
        }
        return null;
    }

    /**
     * Returns the current workbench window.
     * 
     * @return the current workbench window.
     */
    protected final IWorkbenchWindow getWorkbenchWindow() {
        IWorkbenchPart part = DcaseEditorUtil.getCurrentDcaseEditor();
        if (part == null) {
            return null;
        }
        return part.getSite().getWorkbenchWindow();
    }

    /**
     * Executes the command to convert a PDF file to a EPS file.
     * 
     * @param pdfFileName the name of PDF file.
     */
    private void runBatch(String pdfFileName) {
        try {
            Process pro = Runtime.getRuntime().exec(
                    new String[] { batFileName, epsFileName, pdfFileName });
            if (pro.waitFor() != 0) {
                throw new DcaseSystemException(Messages.CopyToEpsRunnable_2,
                        null, MessageTypeImpl.COPY_TO_EPS_FAILED);
            }
        } catch (IOException e) {
            throw new DcaseSystemException(Messages.CopyToEpsRunnable_3, e,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        } catch (InterruptedException e) {
            throw new DcaseSystemException(Messages.CopyToEpsRunnable_3, e,
                    MessageTypeImpl.COPY_TO_EPS_FAILED);
        }
    }
}
