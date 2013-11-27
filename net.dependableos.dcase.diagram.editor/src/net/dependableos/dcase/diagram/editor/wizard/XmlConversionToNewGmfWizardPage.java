/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * A wizard page to select an input file and an output file to convert a Old GMF model file to a New GMF file.
 */
public class XmlConversionToNewGmfWizardPage extends AbstractXmlConversionWizardPage {

    /**
     * the file extension of a GMF model file.
     */
    private static String gmfModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_GMF_FILE_EXTENSION);

    /**
     * Creates an instance and initializes it.
     * 
     * @param selection the selected object.
     */
    public XmlConversionToNewGmfWizardPage(IStructuredSelection selection) {
        super(Messages.XmlConversionToNewGmfWizardPage_0, gmfModelFileExtension,
                gmfModelFileExtension, selection);
    }

    /* (non-Javadoc)
     * @see net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage#initDisplayText()
     */
    @Override
    protected void initDisplayText() {
        setInputModelFileName(GMF_MODEL_FILE_NAME);
        setOutputModelFileName(GMF_MODEL_FILE_NAME);
        setInputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
        setOutputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
    }
    
}
