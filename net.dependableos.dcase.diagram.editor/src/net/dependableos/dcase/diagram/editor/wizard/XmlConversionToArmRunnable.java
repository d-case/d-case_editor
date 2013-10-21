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
import net.dependableos.dcase.diagram.editor.logic.xmlconv.XmlConversionToArmLogic;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Creates the ARM file from the GMF model file.
 */
public class XmlConversionToArmRunnable implements IRunnableWithProgress {

    /**
     * The input file.
     */
    private IFile inputFile;
    /**
     * The output file.
     */
    private IFile outputFile;
    /**
     * The flag of overwrite save for output file.
     */
    private boolean overwriteOption;

    /**
     * Allocates a XmlConversionToArmRunnable object and initializes it
     *  to represent the input file, the output file and the flag of overwrite save.
     * 
     * @param inputFile The input file.
     * @param outputFile The output file.
     * @param overwriteOption The flag of overwrite save for the output file.
     */
    public XmlConversionToArmRunnable(IFile inputFile, IFile outputFile,
            boolean overwriteOption) {
        super();
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts the GMF model file to the ARM file. 
     * 
     * @param monitor The progress monitor.
     * @throws InvocationTargetException The InvocationTargetException
     * @throws InterruptedException The InterruptedException
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {

        try {
            monitor.beginTask(Messages.COMMON_COMMAND_processing, IProgressMonitor.UNKNOWN);
            
            // converts the ARM file from the GMF model file.
            XmlConversionToArmLogic conversionLogic = new XmlConversionToArmLogic(
                    inputFile, outputFile, overwriteOption);
            conversionLogic.convert();
        } catch (DcaseRuntimeException e) {
            e.setMessageType(MessageTypeImpl.CONVERT_TO_ARM_FAILED);
            MessageWriter.writeMessageToProblemsView(e);
            MessageWriter.showMessageBoxSeeProblems();
        } catch (DcaseSystemException e) {
            e.setMessageType(MessageTypeImpl.CONVERT_TO_ARM_FAILED);
            MessageWriter.writeMessageToErrorLog(e);
            MessageWriter.showMessageBoxSeeErroLog();
        } finally {
            monitor.done();
        }
    }

}
