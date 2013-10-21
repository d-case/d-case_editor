/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;


import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;

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
public class RenameDcaseFileDialog extends Dialog {

    /**
     * the initial width.
     */
    private static final int INIT_WIDTH = 500;

    /**
     * the filename.
     */
    private String filename = null;

    /**
     * the new filename.
     */
    private String inputedFilename = null;

    /**
     * the text box to input the new filename.
     */
    private Text textControl = null;

    /**
     * Creates a dialog and initializes it.
     * 
     * @param parentShell the parent.
     */
    public RenameDcaseFileDialog(Shell parentShell) {
        super(parentShell);
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
     * @param filename the filename.
     */
    public void setFilename(String filename) {
        this.filename = filename;
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
        getShell().setText(Messages.RenameDcaseFileDialog_1);

        // create a label and initializes it.
        Label label = new Label(panel, SWT.WRAP);
        label.setText(Messages.RenameDcaseFileDialog_0);
        label.setLayoutData(new GridData());

        // creates a text box and initializes it.
        textControl = new Text(panel, SWT.SINGLE | SWT.BORDER);
        textControl.setText(inputedFilename);
        textControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        textControl.setSelection(0, inputedFilename.length());

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

        // invalid if the new filename equals old filename.
        if (filename.equals(inputedFilename)) {
            return false;
        }

        IStatus status = ResourcesPlugin.getWorkspace().validateName(
                inputedFilename, IFile.FILE);
        if (!status.isOK()) {
            MessageWriter.showErrorMessageBox(status.getMessage());
            return false;
        }
        return true;
    }
}
