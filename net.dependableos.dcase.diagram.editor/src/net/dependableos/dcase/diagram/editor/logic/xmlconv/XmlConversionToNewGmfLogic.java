/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.logic.xmlconv;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.XSL_TO_NEWGMF_PATH;

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
 * A class that converts a Old GMF model file to a New GMF model file.
 */
public class XmlConversionToNewGmfLogic {

    /**
     * the input file.
     */
    private IFile inputFile;
    /**
     * the output file.
     */
    private IFile outputFile;

    /**
     * the flag that whether the output file is allowed to override.
     */
    private boolean overwriteOption;

    /**
     * Creates the instance and initializes it.
     * 
     * @param inputFile the input file.
     * @param outputFile the output file.
     * @param overwriteOption the flag that whether the output file is allowed to override.
     */
    public XmlConversionToNewGmfLogic(IFile inputFile, IFile outputFile,
            boolean overwriteOption) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.overwriteOption = overwriteOption;
    }

    /**
     * Converts.
     */
    public void convert() {

        // the input file must not be null.
        if (inputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToNewGmfLogic_00, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_NEWGMF_FAILED);
        }
        // the output file must not be null.
        if (outputFile == null) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToNewGmfLogic_01, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_NEWGMF_FAILED);
        }

        File target = new File(outputFile.getLocation().toOSString());

        // tests whether the output file exists.
        if (target.exists() && !overwriteOption) {
            throw new DcaseRuntimeException(
                    Messages.XmlConversionToNewGmfLogic_02, null, null, 0,
                    MessageTypeImpl.CONVERT_TO_NEWGMF_FAILED);
        }

        XslTransformer.transform(new StreamResult(target), new StreamSource(
                inputFile.getLocation().toOSString()), new StreamSource(
                getClass().getResourceAsStream(XSL_TO_NEWGMF_PATH)));
        
        try {
            outputFile.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            throw new DcaseSystemException(NLS.bind(
                    Messages.XmlConversionToNewGmfLogic_03, outputFile
                            .getFullPath().toString()), e,
                    MessageTypeImpl.UNDEFINED);
        }

    }

}
