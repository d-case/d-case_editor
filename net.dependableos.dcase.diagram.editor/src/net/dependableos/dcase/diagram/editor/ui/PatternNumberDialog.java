/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

/**
 * A dialog to input number for Pattern nodes.
 */
public class PatternNumberDialog extends Dialog {

    /**
     * the initial width.
     */
    private static final int INIT_WIDTH = 500;

    /**
     * the strings of title and label.
     */
    private String titleStr = null;
    private String labelStr = null;
    
    /**
     * the number.
     */
    private int nr;
    private int min;
    private int max;

    /**
     * the spinner to input the number.
     */
    private Spinner nrSpinner = null;

    /**
     * Creates a dialog and initializes it.
     * 
     * @param parentShell the parent.
     * @param min the minimum number.
     * @param max the maximum number.
     */
    public PatternNumberDialog(Shell parentShell, String titleStr, String labelStr, int min, int max) {
        super(parentShell);
        this.titleStr = titleStr;
        this.labelStr = labelStr;
        this.min = this.nr = min;
        this.max = max;
    }

    /**
     * Returns the current number.
     * @return the current number.
     */
    public int getNumber() {
    	return nr;
    }

    /**
     * Sets the number.
     * @param nr the number.
     */
    public void setNumber(int nr) {
    	this.nr = nr;
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
        getShell().setText(titleStr);

        // create a label and initializes it.
        Label label = new Label(panel, SWT.WRAP);
        label.setText(labelStr);
        label.setLayoutData(new GridData());

        // creates a spinner.
        nrSpinner = new Spinner(parent, SWT.BORDER);
        nrSpinner.setMaximum(max);
        nrSpinner.setMinimum(min);
        nrSpinner.setSelection(nr - min);
        GridData nrGrid = new GridData();
        nrGrid.horizontalAlignment = GridData.FILL;
        nrGrid.grabExcessHorizontalSpace = true;
        nrSpinner.setLayoutData(nrGrid);
        
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
    	nr = Integer.valueOf(nrSpinner.getText());
        super.okPressed();
    }
}
