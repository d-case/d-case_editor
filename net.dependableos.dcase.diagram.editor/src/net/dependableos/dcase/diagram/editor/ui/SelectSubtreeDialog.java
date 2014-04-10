/*
 * Copyright (C) 2014  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2014  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.util.ArrayList;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.System;
import net.dependableos.dcase.diagram.part.PatternUtil;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog to configure parameters.
 */
public class SelectSubtreeDialog extends Dialog {
    /**
     * the nodes.
     */
    private java.util.List<BasicNode> nodeList = null;
    private List buttonList;
    private ArrayList<BasicNode> unselectedList = null;

    /**
     * the Pattern node.
     */
    private System sNode;
    private Argument argument;
    
    /**
     * the width of the dialog.
     */
    private static final int INIT_WIDTH = 400;

    /**
     * the title of the dialog.
     */
    private static final String DIALOG_TITLE = "Choice Subtrees"; //$NON-NLS-1$

    /**
     * Creates an instance and initializes it.
     * 
     * @param parentShell the parent shell.
     */
    public SelectSubtreeDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Creates an instance and initializes it.
     * 
     * @param parentShell the parent shell.
     * @param min the minimum number of the range.
     * @param max the maximum number of the range.
     */
    public SelectSubtreeDialog(Shell parentShell, java.util.List<BasicNode> list,
    		System sNode, Argument argument) {
        super(parentShell);
        setNodes(list);
        this.sNode = sNode;
        this.argument = argument;
    }

    /**
     * Sets the node.
     * 
     * @param nodeInfo the node.
     */
    public void setNodes(java.util.List<BasicNode> list) {
        this.nodeList = list;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite panel = (Composite) super.createDialogArea(parent);

        GridLayout layout = new GridLayout();
        panel.setLayout(layout);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        getShell().setText(DIALOG_TITLE);
        
        Label label = new Label(panel, SWT.WRAP);
        label.setText(
        		PatternUtil.getNodeLabel(PatternUtil.getParent(sNode, argument, false),
        				argument));
        label.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        buttonList = new List(panel, SWT.MULTI|SWT.BORDER|SWT.V_SCROLL);
        buttonList.addSelectionListener(new SelectionListener() {
        	public void widgetSelected(SelectionEvent arg) {
        		validateList();
        	}
        	public void widgetDefaultSelected(SelectionEvent arg){
        	}
        });
        for (BasicNode node : nodeList) {
        	buttonList.add(node.getName());
        }
        buttonList.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        return panel;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
    	Button button = super.createButton(parent, id, label, defaultButton);
    	if (id == IDialogConstants.OK_ID) {
    		validateList();
    	}
    	return button;
    }
    
    /**
     * Validates the selection count.
     */
    private void validateList() {
    	int count = buttonList.getSelectionCount();
    	if (count < sNode.getI() || count > sNode.getJ()) {
    		getButton(IDialogConstants.OK_ID).setEnabled(false);
    	} else {
    		getButton(IDialogConstants.OK_ID).setEnabled(true);
    	}
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
    	// create the unselected list.
    	unselectedList = new ArrayList<BasicNode>();
    	for (int i = 0; i < nodeList.size(); i++) {
    		if (!buttonList.isSelected(i)) {
    			unselectedList.add(nodeList.get(i));
    		}
    	}
        // closes the dialog.
        super.okPressed();
    }

    /**
     * Disposes of this content provider.
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        nodeList = null;
    }
    
    /**
     * Returns the unselected nodes.
     * @return the unselected nodes.
     */
    public java.util.List<BasicNode> getUnselectedNodes() {
    	return unselectedList;
    }

    /**
     * Notifies this content provider that the given viewer's input has been switched to a different element. 
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     * 
     * @param viewer the viewer
     * @param oldInput the old input element, or null if the viewer did not previously have an input.
     * @param newInput the new input element, or null if the viewer does not have an input.
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
    
}
