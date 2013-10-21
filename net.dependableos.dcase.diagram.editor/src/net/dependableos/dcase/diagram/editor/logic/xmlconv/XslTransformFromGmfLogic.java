/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.xmlconv;

import java.io.File;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.xml.XslTransformer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;

/**
 * A class that Xsl transform a D-Case model file.
 *
 */
public class XslTransformFromGmfLogic {

    /**
     * the input file.
     */
    private IFile inputFile;
    /**
     * the output file.
     */
    private IFile outputFile;
    /**
     * the converter xsl file.
     */
    private File converterFile;
    /**
     * the flag that whether the output file is allowed to override.
     */
    private boolean overwriteOption;

    /**
     * Creates the instance and initializes it.
     * 
     * @param inputFile the input file.
     * @param outputFile the output file.
     * @param converterFile the converter xsl file.
     * @param overwriteOption the flag that whether the output file is allowed to override.
     */
    public XslTransformFromGmfLogic(IFile inputFile, IFile outputFile, File converterFile,
            boolean overwriteOption) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.converterFile = converterFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts.
     */
    public void convert() {
        // the input file must not be null.
        if (inputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XslTransformFromGmfLogic_InputFileFailed, null, null, 0,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
        }
        // the output file must not be null.
        if (outputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XslTransformFromGmfLogic_OutputFileFailed, null, null, 0,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
        }

        File target = new File(outputFile.getLocation().toOSString());

        // tests whether the output file exists.
        if (target.exists() && !overwriteOption) {
            throw new DcaseRuntimeException(
                    Messages.XslTransformFromGmfLogic_OutputFileExsists, null, null, 0,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
        }
        
        // tests whether the converter file exists.
        if (!converterFile.exists()) {
            throw new DcaseRuntimeException(
                    Messages.XslTransformFromGmfLogic_XslFileNotExsists, null, null, 0,
                    MessageTypeImpl.TRANSFORM_FROM_GMF_FAILED);
        }
        
        File input = new File(inputFile.getLocation().toOSString());

        XslTransformer.transform(
                new StreamResult(target),
                new StreamSource(input),
                new StreamSource(converterFile));

        try {
            outputFile.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            throw new DcaseSystemException(NLS.bind(
                    Messages.XslTransformFromGmfLogic_CoreException, outputFile
                            .getFullPath().toString()), e,
                    MessageTypeImpl.UNDEFINED);
        }

    }
}
