/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.ui;

import java.util.List;

import net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The dialog class for selecting the method to specify a attachment.
 */
public class AttachmentSelectorDialog extends Dialog {

    /**
     * the dialog width.
     */
    private static final int INIT_WIDTH = 350;
    
    /**
     * column number.
     */
    private static final int GRID_COLUMNS = 1;
    
    /**
     * the list of attachment selector.
     */
    private final List<IAttachmentSelector> selectorList;
    
    /**
     * the list of attachment selector.
     */
    private IAttachmentSelector selectedSelector;
   
    /**
     * constructor.
     * 
     * @param parentShell shell
     * @param selectorList the list of attachment selector
     */
    public AttachmentSelectorDialog(Shell parentShell, List<IAttachmentSelector> selectorList) {
        super(parentShell);
        this.selectorList = selectorList;
    }
    
    /**
     * get dialog size.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     * @return Dialog size.
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
     * create dialog area.
     * @param parent parent
     * @return Control
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite panel = (Composite) super.createDialogArea(parent);
        
        // creates a grid layout.
        ((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;
        
        getShell().setText("Select the method to specify the attachment"); 
        
        createDialogControl(panel);
        
        return panel;
    }
    
    /**
     * create dialog control.
     * 
     * @param parent parent
     */
    private void createDialogControl(Composite parent) {
        
        for (final IAttachmentSelector selector : selectorList) {
            Button button = new Button(parent, SWT.RADIO);
            button.setText(selector.getName());
            button.addSelectionListener(new SelectionAdapter() {
                /* (non-Javadoc)
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected(SelectionEvent e) {
                    Button b = (Button) e.widget;
                    if (b.getSelection()) {
                        AttachmentSelectorDialog.this.selectedSelector = selector;
                    }
                }
            });
        }
        selectedSelector = selectorList.get(0);
    }
    
    /**
     * Return the selected attachment selector.
     * @return the selected attachment selector 
     */
    public IAttachmentSelector getAttachmentSelector() {
        return selectedSelector;
    }
}
