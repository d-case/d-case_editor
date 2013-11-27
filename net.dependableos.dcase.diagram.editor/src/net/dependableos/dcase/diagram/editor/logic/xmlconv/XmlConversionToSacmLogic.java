/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.logic.xmlconv;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.XSL_TO_SACM_PATH;

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
 * Converts the GMF model file to the SACM file.
 */
public class XmlConversionToSacmLogic {

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
     * Allocates a XmlConversionToSacmLogic object and initializes it to represent
     *  the input file, the output file and the overwrite option.
     * 
     * @param inputFile The input file.
     * @param outputFile The output file.
     * @param overwriteOption The flag of overwrite save for output file.
     */
    public XmlConversionToSacmLogic(IFile inputFile, IFile outputFile,
            boolean overwriteOption) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts the GMF model file to the SACM file. 
     */
    public void convert() {

        // Checks the input file.
        if (inputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToSacmLogic_0, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_SACM_FAILED);
        }
        // Checks the output file.
        if (outputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToSacmLogic_1, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_SACM_FAILED);
        }

        File target = new File(outputFile.getLocation().toOSString());

        // Check the existence of the file and check an overwrite option more.
        if (target.exists() && !overwriteOption) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToSacmLogic_2, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_SACM_FAILED);
        }

        XslTransformer.transform(new StreamResult(target), new StreamSource(
                inputFile.getLocation().toOSString()), new StreamSource(
                getClass().getResourceAsStream(XSL_TO_SACM_PATH)));

        try {
            outputFile.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            throw new DcaseSystemException(NLS.bind(
                    Messages.XmlConversionToSacmLogic_3, outputFile
                            .getFullPath().toString()), e,
                    MessageTypeImpl.UNDEFINED);
        }

    }

}
