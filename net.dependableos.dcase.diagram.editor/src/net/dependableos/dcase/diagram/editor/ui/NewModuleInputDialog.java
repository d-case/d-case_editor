/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to input new filename.
 */
public class NewModuleInputDialog extends Dialog {

	/**
	 * the initial width.
	 */
	private static final int INIT_WIDTH = 500;

	/**
	 * the module filename.
	 */
	private String inputedFilename = null;

	/**
	 * the text box to input the new filename.
	 */
	private Text textControl = null;

	/**
	 * the message.
	 */
	private String message = ""; //$NON-NLS-1$

	/**
	 * Creates a dialog and initializes it.
	 * 
	 * @param parentShell
	 *            the parent.
	 * @param message
	 *            the message string.
	 */
	public NewModuleInputDialog(Shell parentShell, String message) {
		super(parentShell);
		this.message = message;
	}

	/**
	 * Returns the new filename.
	 * 
	 * @return the inputedFilename
	 */
	public String getInputedFilename() {
		return inputedFilename;
	}

	/**
	 * Sets the filename.
	 * 
	 * @param filename
	 *            the filename.
	 */
	public void setFilename(String filename) {
		inputedFilename = filename;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		// sets grids.
		Composite panel = (Composite) super.createDialogArea(parent);

		panel.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		panel.setLayout(layout);
		// sets the title.
		getShell().setText(Messages.NewModuleInputDialog_Title);

		// create a message.
		Label messageLabel = new Label(panel, SWT.NORMAL);
		messageLabel.setText(message);
		GridData messageGrid = new GridData();
		messageGrid.horizontalSpan = 2;
		messageLabel.setLayoutData(messageGrid);

		// create a label and initializes it.
		Label label = new Label(panel, SWT.WRAP);
		label.setText(Messages.NewModuleInputDialog_Label);
		label.setLayoutData(new GridData());

		// creates a text box and initializes it.
		textControl = new Text(panel, SWT.SINGLE | SWT.BORDER);
		if (inputedFilename != null) {
			textControl.setText(inputedFilename);
			textControl.setSelection(0, inputedFilename.length());
		}
		textControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return panel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Point getInitialSize() {
		Point size = super.getInitialSize();
		if (size.x < INIT_WIDTH) {
			size.x = INIT_WIDTH;
		}
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void okPressed() {
		inputedFilename = textControl.getText();
		// tests whether the new filename is valid.
		if (!isValidFilename()) {
			return;
		}
		super.okPressed();
	}

	/**
	 * Tests whether the new filename is valid.
	 * 
	 * @return true if and only if the filename is valid;false otherwise.
	 */
	private boolean isValidFilename() {
		// invalid if the new filename is null or empty.
		if (inputedFilename == null || inputedFilename.trim().length() == 0) {
			return false;
		}
		if (inputedFilename.indexOf(";") >= 0 || //$NON-NLS-1$
				inputedFilename.indexOf(":") >= 0) { //$NON-NLS-1$
			MessageWriter.showErrorMessageBox(inputedFilename
					+ " contains invalid char."); //$NON-NLS-1$
			return false;
		}

		IStatus status = ResourcesPlugin.getWorkspace().validateName( // empty,
																		// /, \,
																		// :, .,
																		// ..
				inputedFilename, IFile.FILE);
		if (!status.isOK()) {
			MessageWriter.showErrorMessageBox(status.getMessage());
			return false;
		}
		return true;
	}
}
