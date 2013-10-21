/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * A wizard page to select an input file and an output file to convert a D-Case file to a GMF model file.
 */
public class XslTransformFromGmfWizardPage extends
        AbstractXmlConversionWizardPage {

    /**
     * The file extension of a GMF model file.
     */
    private static String gmfModelFileExtension = PropertyUtil
            .getSystemProperty(MODEL_GMF_FILE_EXTENSION);

    /**
     * Creates an instance and initializes it.
     * 
     * @param selection the selected object.
     */
    public XslTransformFromGmfWizardPage(IStructuredSelection selection) {
        super(Messages.XmlConversionToDcaseWizardPage_0, gmfModelFileExtension,
                null, selection);
    }

    /* (non-Javadoc)
     * @see net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage#initDisplayText()
     */
    @Override
    protected void initDisplayText() {
        setInputModelFileName(GMF_MODEL_FILE_NAME);
        setOutputModelFileName(""); // $NON-NLS-1$
        setInputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
        setOutputModelFileDialogTitle(Messages.XslTransformOutputFileDialogTitle);
    }

}
