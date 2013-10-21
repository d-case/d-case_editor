/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.xmlconv.XslTransformFromGmfLogic;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Xsl transform a GMF model file.
 */
public class XslTransformFromGmfRunnable implements IRunnableWithProgress {

    /**
     * The input file.
     */
    private IFile inputFile;
    /**
     * The output file.
     */
    private IFile outputFile;
    /**
     * The converter file.
     */
    private File converterFile;
    /**
     * A determines whether the output file is allowed to override.
     */
    private boolean overwriteOption;

    /**
     * Creates an instance and initializes it.
     * 
     * @param inputFile the input file.
     * @param outputFile the output file.
     * @param converterFile the converter xsl file.
     * @param overwriteOption true if and only if the output file is allowed to override;false otherwise.
     */
    public XslTransformFromGmfRunnable(IFile inputFile, IFile outputFile, File converterFile,
            boolean overwriteOption) {
        super();
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.converterFile = converterFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts a D-Case file to a GMF model file.
     * 
     * @param monitor the progress monitor to use to display progress and
     *            receive requests for cancellation.
     * @throws InvocationTargetException if the run method must propagate a
     *             checked exception, it should wrap it inside an
     *             InvocationTargetException; runtime exceptions are
     *             automatically wrapped in an InvocationTargetException by the
     *             calling context.
     * @throws InterruptedException if the operation detects a request to
     *             cancel, using IProgressMonitor.isCanceled(), it should exit
     *             by throwing InterruptedException.
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        try {
            monitor.beginTask(Messages.COMMON_COMMAND_processing,
                    IProgressMonitor.UNKNOWN);

            // converts
            XslTransformFromGmfLogic conversionLogic = new XslTransformFromGmfLogic(
                    inputFile, outputFile, converterFile, overwriteOption);
            conversionLogic.convert();

        } catch (DcaseRuntimeException e) {
            e.setMessageType(MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
            MessageWriter.writeMessageToProblemsView(e);
            MessageWriter.showMessageBoxSeeProblems();

        } catch (DcaseSystemException e) {
            e.setMessageType(MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
            MessageWriter.writeMessageToErrorLog(e);
            MessageWriter.showMessageBoxSeeErroLog();
        } finally {
            monitor.done();
        }
    }
}
