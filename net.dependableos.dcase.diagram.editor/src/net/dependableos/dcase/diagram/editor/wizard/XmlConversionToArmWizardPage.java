/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_ARM_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Creates the wizard page to create the ARM file from the GMF model file.
 */
public class XmlConversionToArmWizardPage extends AbstractXmlConversionWizardPage {

    /**
     * The file extension of the GMF model file.
     */
    private static String gmfModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_GMF_FILE_EXTENSION);
    /**
     * The file extension of the ARM file.
     */
    private static String armModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_ARM_FILE_EXTENSION);

    /**
     * Allocates a XmlConversionToArmWizardPage object and 
     *  initializes it to represent the IStructuredSelection object.
     * 
     * @param selection The IStructuredSelection object.
     */
    public XmlConversionToArmWizardPage(IStructuredSelection selection) {
        super(Messages.XmlConversionToArmWizardPage_0, gmfModelFileExtension,
                armModelFileExtension, selection);
    }

    /* (non-Javadoc)
     * @see net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage#initDisplayText()
     */
    @Override
    protected void initDisplayText() {
       setInputModelFileName(GMF_MODEL_FILE_NAME);
       setOutputModelFileName(Messages.XmlConversionToArmWizardPage_1);
       setInputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
       setOutputModelFileDialogTitle(Messages.XmlConversionToArmWizardPage_2);
    }
}
