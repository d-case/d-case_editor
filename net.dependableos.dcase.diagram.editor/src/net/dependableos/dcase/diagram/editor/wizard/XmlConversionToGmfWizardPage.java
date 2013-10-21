/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_DCASE_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * A wizard page to select an input file and an output file to convert a D-Case file to a GMF model file.
 */
public class XmlConversionToGmfWizardPage extends AbstractXmlConversionWizardPage {

    /**
     * the file extension of a GMF model file.
     */
    private static String gmfModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_GMF_FILE_EXTENSION);
    /**
     * the file extension of a D-Case model file.
     */
    private static String dcaseModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_DCASE_FILE_EXTENSION);

    /**
     * Creates an instance and initializes it.
     * 
     * @param selection the selected object.
     */
    public XmlConversionToGmfWizardPage(IStructuredSelection selection) {
        super(Messages.XmlConversionToGmfWizardPage_0, dcaseModelFileExtension,
                gmfModelFileExtension, selection);
    }

    /* (non-Javadoc)
     * @see net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage#initDisplayText()
     */
    @Override
    protected void initDisplayText() {
        setInputModelFileName(DCASE_MODEL_FILE_NAME);
        setOutputModelFileName(GMF_MODEL_FILE_NAME);
        setInputModelFileDialogTitle(DIALOG_TITLE_DCASE_MODEL_FILE);
        setOutputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
    }

    
}
