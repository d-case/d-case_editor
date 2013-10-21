/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.editor.ui;

import java.util.List;

import net.dependableos.dcase.diagram.editor.logic.xmlconv.XSLConverterItem;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Enter the new converter name dialog. 
 *
 */
public class NewConverterDialog extends Dialog {

    /**
     * The invalid characters.
     */
    private static final String[] INVALID_CHARACTERS =
        {
        ",", //$NON-NLS-1$
        ";", //$NON-NLS-1$
        "{", //$NON-NLS-1$
        "}", //$NON-NLS-1$
        "=", //$NON-NLS-1$
        };
    /**
     * A dialog grid column.
     */
    private static final int GRID_COLUMN = 2;
    /**
     * A text field width.
     */
    private static final int TEXT_FIELD_WIDTH = 200;
    /**
     * A converter name value.
     */
    private String name = null;
    /**
     * A list of converter item.
     */
    private List<XSLConverterItem> converters = null;
    /**
     * A label control.
     */
    private Label nameLabel;
    /**
     * A text control.
     */
    private Text nameText;

    /**
     * Constructor for the class.
     * Create new instance.
     * 
     * @param parentShell A parent shell.
     * @param converters A list of converter item.
     */
    public NewConverterDialog(Shell parentShell, List<XSLConverterItem> converters) {
        super(parentShell);
        this.setConverters(converters);
    }
    /**
     * Returns a converter name.
     * 
     * @return A converter name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets a converter name.
     * 
     * @param name A converter name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns list of converter item. 
     * @return A list of converter item.
     */
    public List<XSLConverterItem> getConverters() {
        return converters;
    }
    /**
     * Sets list of converter item.
     * @param converters A list of converter item.
     */
    public void setConverters(List<XSLConverterItem> converters) {
        this.converters = converters;
    }
    /**
     * Create dialog area.
     * @param parent A composite object.
     * @return A control object.
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite panel = (Composite) super.createDialogArea(parent);
        
        // creates a grid layout.
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = GRID_COLUMN;
        panel.setLayout(layout);
        
        //
        getShell().setText(Messages.NewConverterDialogTitle);

        createDialogControl(panel);
        
        return panel;
    }
    /**
     * Create a dialog controls.
     * @param panel A composite object.
     */
    private void createDialogControl(Composite panel) {
        nameLabel = new Label(panel, SWT.LEFT);
        nameLabel.setText(Messages.NewConverterDialogNameLabel);
        GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        nameLabel.setLayoutData(labelGridData);

        nameText = new Text(panel, SWT.LEFT | SWT.SINGLE | SWT.BORDER);
        GridData textGridData = new GridData(GridData.FILL_HORIZONTAL);
        textGridData.minimumWidth = TEXT_FIELD_WIDTH;
        nameText.setLayoutData(textGridData);
    }

    /**
     * Pressed OK button.
     */
    @Override
    protected void okPressed() {
        setName(nameText.getText());

        if (isExistsName(getName())) {
            MessageDialog.openError(getShell(),
                    Messages.MessageWriter_1,
                    Messages.XSLConverterNameExists);
            return;
        }
        if (hasInvalidCharacter(getName())) {
            MessageDialog.openError(getShell(),
                    Messages.MessageWriter_1,
                    Messages.XSLConverterIllegalCharacters);
            return;
        }

        super.okPressed();
    }
    /**
     * Checked exists converter name.
     * 
     * @param name A target value.
     * @return True is exists.
     */
    private boolean isExistsName(String name) {
        for (XSLConverterItem item : getConverters()) {
            if (item.getName().equals(getName())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checked has a invalid character.
     * 
     * @param name A target value.
     * @return True is has a invalid character.
     */
    private boolean hasInvalidCharacter(String name) {
        for (String c : INVALID_CHARACTERS) {
            if (name.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
