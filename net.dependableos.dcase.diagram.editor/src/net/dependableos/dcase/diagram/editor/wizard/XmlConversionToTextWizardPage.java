/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * A wizard page to select an input file and an output file to convert a GMF
 * model file to a Text file.
 */
public class XmlConversionToTextWizardPage extends
		AbstractXmlConversionWizardPage {

	/**
	 * the file extension of a GMF model file.
	 */
	private static String gmfModelFileExtension = PropertyUtil
			.getSystemProperty(MODEL_GMF_FILE_EXTENSION);

	/**
	 * Creates an instance and initializes it.
	 * 
	 * @param selection
	 *            the selected object.
	 */
	public XmlConversionToTextWizardPage(IStructuredSelection selection) {
		super(Messages.XmlConversionToTextWizardPage_0, gmfModelFileExtension,
				"txt", selection); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.dependableos.diagram.editor.wizard.AbstractXmlConversionWizardPage
	 * #initDisplayText()
	 */
	@Override
	protected void initDisplayText() {
		setInputModelFileName(GMF_MODEL_FILE_NAME);
		setOutputModelFileName(Messages.XmlConversionToTextWizardPage_1);
		setInputModelFileDialogTitle(DIALOG_TITLE_GMF_MODEL_FILE);
		setOutputModelFileDialogTitle(Messages.XmlConversionToTextWizardPage_2);
	}

}
