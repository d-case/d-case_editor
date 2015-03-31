/*
 * Copyright (C) 2015  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2015  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_SACM_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Creates the wizard page to create the Extended SACM file from the GMF model file.
 */
public class XmlConversionToExtSacmWizardPage extends AbstractXmlConversionWizardPage {

    /**
     * The file extension of the GMF model file.
     */
    private static String gmfModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_GMF_FILE_EXTENSION);
    /**
     * The file extension of the SACM file.
     */
    private static String sacmModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_SACM_FILE_EXTENSION);

    /**
     * Allocates a XmlConversionToSacmWizardPage object and 
     *  initializes it to represent the IStructuredSelection object.
     * 
     * @param selection The IStructuredSelection object.
     */
    public XmlConversionToExtSacmWizardPage(IStructuredSelection selection) {
        super(Messages.XmlConversionToExtSacmWizardPage_0, gmfModelFileExtension,
                sacmModelFileExtension, selection);
    }

    /* (non-Javadoc)
     * @see net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage#initDisplayText()
     */
    @Override
    protected void initDisplayText() {
       setInputModelFileName(GMF_MODEL_FILE_NAME);
       setOutputModelFileName(Messages.XmlConversionToExtSacmWizardPage_1);
       setInputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
       setOutputModelFileDialogTitle(Messages.XmlConversionToExtSacmWizardPage_2);
    }
}
