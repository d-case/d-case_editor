/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.requirement;

import java.util.List;

import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * the Requirement editing dialog class.
 */
public class RequirementEditDialog extends Dialog {
    
    /**
     * the dialog title if mode is create.
     */
    private static final String CREATE_TITLE = Messages.RequirementEditDialog_CreateDialogTitle;
    
    /**
     * the dialog title if mode is edit.
     */
    private static final String EDIT_TITLE = Messages.RequirementEditDialog_EditDialogTitle;

    /**
     * the dialog width.
     */
    private static final int INIT_WIDTH = 300;
    
    /**
     * column number.
     */
    private static final int GRID_COLUMNS = 2;
    
    /**
     * the text width.
     */
    private static final int TEXT_WIDTH = 250;
    
    /**
     * Returns the id.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the requirement.
     * 
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * the create mode. 
     */
    public static final String CREATE = "create"; //$NON-NLS-1$
    
    /**
     * the edit mode.
     */
    public static final String EDIT = "edit"; //$NON-NLS-1$
    
    /**
     * a current mode.
     */
    private final String mode;
    
    /**
     * the text control for requirement id.
     */
    private Text requirementIdText;
    
    /**
     * the text control for requirement content.
     */
    private Text requiremetText;
    
    /**
     * the requirement id.
     */
    private String id;
    
    /**
     * the requirement.
     */
    private String requirement;
    
    /**
     * the existing requirement ids.
     */
    private List<String> existingIds;
   
    

    /**
     * Creates a dialog to edit a requirement and initializes it.
     * 
     * @param parentShell the parent.
     * @param id the id.
     * @param requirement the requirement.
     * @param existingIds the existing requirement ids.
     * @param mode create or edit.
     */
    public RequirementEditDialog(Shell parentShell, String id,
            String requirement, List<String> existingIds, String mode) {
        super(parentShell);
        this.id = id;
        this.requirement = requirement;
        this.existingIds = existingIds;
        this.mode = mode;
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
    protected Control createDialogArea(Composite parent) {
        
        Composite panel = (Composite) super.createDialogArea(parent);
        
        // creates a grid layout.
        ((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;
        
        //
        if (CREATE.equals(mode)) {
            getShell().setText(CREATE_TITLE);
            
        } else if (EDIT.equals(mode)) {
            getShell().setText(EDIT_TITLE);
            
        }
        createDialogControl(panel);
        
        return panel;
    }
    
    /**
     * Creates dialog control.
     * @param parent the parent composite
     */
    private void createDialogControl(Composite parent) {
     
        //Name
        Label label = new Label(parent, SWT.WRAP);
        label.setText(Messages.RequirementEditDialog_NameLabelText);
        
        requirementIdText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        if (EDIT.equals(mode)) {
            requirementIdText.setText(id);
        }
        GridData requirementIdData = new GridData();
        requirementIdData.horizontalAlignment = GridData.FILL;
        requirementIdData.widthHint = TEXT_WIDTH;
        requirementIdText.setLayoutData(requirementIdData);


        //Requirement
        Label labe2 = new Label(parent, SWT.WRAP);
        labe2.setText(Messages.RequirementEditDialog_RequirementLabelText);
        
        requiremetText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        if (EDIT.equals(mode)) {
            requiremetText.setText(requirement);
        }
        GridData requirementTextdata = new GridData();
        requirementTextdata.horizontalAlignment = GridData.FILL;
        requirementTextdata.widthHint = TEXT_WIDTH;
        requiremetText.setLayoutData(requirementTextdata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void okPressed() {
        id = requirementIdText.getText();
        requirement = requiremetText.getText();
        
        if (existingIds.contains(id)) {
            MessageBox box = new MessageBox(getShell(), SWT.OK
                    | SWT.ICON_ERROR);
            box.setText(Messages.RequirementEditDialog_0);
            box.setMessage(Messages.RequirementEditDialog_1);
            box.open();
            return;
        }
        
        super.okPressed();
        
    }

}
