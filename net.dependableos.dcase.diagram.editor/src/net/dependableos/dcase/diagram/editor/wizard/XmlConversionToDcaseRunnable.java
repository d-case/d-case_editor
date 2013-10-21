/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import java.lang.reflect.InvocationTargetException;


import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.xmlconv.XmlConversionToDcaseLogic;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Converts a GMF model file to a D-Case file.
 */
public class XmlConversionToDcaseRunnable implements IRunnableWithProgress {

    /**
     * the input file.
     */
    private IFile inputFile;
    /**
     * the output file.
     */
    private IFile outputFile;
    /**
     * determines whether the output file is allowed to override.
     */
    private boolean overwriteOption;

    /**
     * Creates an instance and initializes it.
     * 
     * @param inputFile the input file.
     * @param outputFile the output file.
     * @param overwriteOption true if and only if the output file is allowed to override;false otherwise.
     */
    public XmlConversionToDcaseRunnable(IFile inputFile, IFile outputFile,
            boolean overwriteOption) {
        super();
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts a GMF model file to a D-Case file.
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
            XmlConversionToDcaseLogic conversionLogic = new XmlConversionToDcaseLogic(
                    inputFile, outputFile, overwriteOption);
            conversionLogic.convert();

        } catch (DcaseRuntimeException e) {
            e.setMessageType(MessageTypeImpl.CONVERT_TO_DCASE_FAILED);
            MessageWriter.writeMessageToProblemsView(e);
            MessageWriter.showMessageBoxSeeProblems();

        } catch (DcaseSystemException e) {
            e.setMessageType(MessageTypeImpl.CONVERT_TO_DCASE_FAILED);
            MessageWriter.writeMessageToErrorLog(e);
            MessageWriter.showMessageBoxSeeErroLog();
        } finally {
            monitor.done();
        }
    }

}
