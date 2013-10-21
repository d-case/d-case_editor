/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog to configure parameters.
 */
public class ParameterDefineDialog extends Dialog implements
		IStructuredContentProvider {
	/**
	 * the width of the dialog.
	 */
	private static final int INIT_WIDTH = 500;

	/**
	 * the width of the button.
	 */
	private static final int BUTTON_WIDTH = 80;

	/**
	 * the number of columns.
	 */
	private static final int COLUMN_NUMBER = 2;

	/**
	 * the height of dialog.
	 */
	private static final int HEIGHT_HINT = 200;

	/**
	 * the vertical span. (Buttons x 3 + Label)
	 */
	private static final int VERTICAL_SPAN = 4;

	/**
	 * the title of the dialog.
	 */
	private static final String DIALOG_TITLE = "Define Parameters"; //$NON-NLS-1$

	/**
	 * the node.
	 */
	private NodeInfo nodeInfo = null;

	/**
	 * a list control for parameters.
	 */
	private org.eclipse.swt.widgets.List parameterDataList;

	/**
	 * parameters.
	 */
	private List<ParameterDataItem> parameterDataItems;

	/**
	 * the selected parameter.
	 */
	private ParameterDataItem selectedParameter = null;

	/**
	 * the add button.
	 */
	private Button addButton;

	/**
	 * the edit button.
	 */
	private Button editButton;

	/**
	 * the delete button.
	 */
	private Button delButton;

	/**
	 * Creates an instance and initializes it.
	 * 
	 * @param parentShell
	 *            the parent shell.
	 */
	public ParameterDefineDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Sets the node.
	 * 
	 * @param nodeInfo
	 *            the node.
	 */
	public void setNodeInfo(NodeInfo nodeInfo) {
		this.nodeInfo = nodeInfo;
		String userdef009 = (String) nodeInfo
				.getAttribute(AttributeType.USERDEF009);
		parameterDataItems = ParameterDataItem
				.getParamDatatypeListFromString(userdef009);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite panel = (Composite) super.createDialogArea(parent);

		GridLayout layout = new GridLayout();
		layout.numColumns = COLUMN_NUMBER;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		panel.setLayout(layout);
		panel.setLayoutData(new GridData(GridData.FILL_BOTH));
		panel.setFont(panel.getFont());
		getShell().setText(DIALOG_TITLE);

		if (nodeInfo != null) {
			setParameterTypeListSelect(panel);
			createOperationButton(panel);
		}

		return panel;
	}

	/**
	 * Initializes a list for parameters.
	 * 
	 * @param panel
	 *            parent composite.
	 */
	private void setParameterTypeListSelect(Composite panel) {
		if (parameterDataList == null) {
			parameterDataList = new org.eclipse.swt.widgets.List(panel,
					SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.grabExcessVerticalSpace = true;
			gridData.verticalSpan = VERTICAL_SPAN;
			gridData.heightHint = HEIGHT_HINT;
			parameterDataList.setLayoutData(gridData);
		}
		parameterDataList.removeAll();

		String[] items = new String[parameterDataItems.size()];
		for (int i = 0; i < parameterDataItems.size(); i++) {
			items[i] = parameterDataItems.get(i).getName();
		}
		parameterDataList.setItems(items);

		// If a parameter type is selected, sets it selected status in the
		// select list.
		if (selectedParameter != null) {
			for (int i = 0; i < parameterDataItems.size(); i++) {
				if (selectedParameter.equals(parameterDataItems.get(i))) {
					parameterDataList.select(i);
					break;
				}
			}
		}

		for (ParameterDataItem data : parameterDataItems) {
			data.validate();
		}
	}

	/**
	 * Creates buttons.
	 * 
	 * @param panel
	 *            parent composite.
	 */
	private void createOperationButton(final Composite panel) {

		// creates the add button.
		addButton = new Button(panel, SWT.PUSH | SWT.CENTER);
		addButton.setText(Messages.ParameterDatatypePreferencePage_1);
		GridData createGridData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		createGridData.widthHint = BUTTON_WIDTH;
		addButton.setLayoutData(createGridData);
		addButton.addSelectionListener(new SelectionAdapter() {
			// The event method executed when the creation button is pushed.
			public void widgetSelected(SelectionEvent e) {
				ParameterDefinitionWizard wizard = new ParameterDefinitionWizard(
						parameterDataItems);
				wizard.setWindowTitle(Messages.ParameterDatatypeDialog_CreationWizardTile);
				DcaseDiagramEditorUtil.runWizard(getShell(), wizard,
						"CreateParameterDatatype"); //$NON-NLS-1$
				selectedParameter = wizard.getResultedParameterdata();
				setParameterTypeListSelect(panel);
			}
		});
		// creates the edit button.
		editButton = new Button(panel, SWT.PUSH | SWT.CENTER);
		editButton.setText(Messages.ParameterDatatypePreferencePage_2);
		GridData editGridData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		editGridData.widthHint = BUTTON_WIDTH;
		editButton.setLayoutData(editGridData);
		editButton.addSelectionListener(new SelectionAdapter() {
			// The event method executed when the edit button is pushed.
			public void widgetSelected(SelectionEvent e) {
				int index = parameterDataList.getSelectionIndex();
				if (index >= 0) {
					ParameterDefinitionWizard wizard = new ParameterDefinitionWizard(
							index, parameterDataItems);
					wizard.setWindowTitle(Messages.ParameterDatatypeDialog_EditWizardTitle);
					DcaseDiagramEditorUtil.runWizard(getShell(), wizard,
							"EditParameterDatatype"); //$NON-NLS-1$
					selectedParameter = wizard.getResultedParameterdata();
					setParameterTypeListSelect(panel);
				}
			}
		});

		// creates the deletion button.
		delButton = new Button(panel, SWT.PUSH | SWT.CENTER);
		delButton.setText(Messages.ParameterDatatypePreferencePage_3);
		GridData delGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		delGridData.widthHint = BUTTON_WIDTH;
		delButton.setLayoutData(delGridData);
		delButton.addSelectionListener(new SelectionAdapter() {
			// // The event method executed when the deletion button is pushed.
			public void widgetSelected(SelectionEvent e) {
				int index = parameterDataList.getSelectionIndex();
				if (index >= 0) {
					parameterDataList.remove(index);
					parameterDataItems.remove(index);
					setParameterTypeListSelect(panel);
				}
			}
		});

		// sets layout data.
		Label scriptStrLabel = new Label(panel, SWT.WRAP);
		GridData spaceGridData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		spaceGridData.widthHint = BUTTON_WIDTH;
		scriptStrLabel.setLayoutData(spaceGridData);
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
		String dataStr = ParameterDataItem.getSavedString(parameterDataItems);
		nodeInfo.setAttribute(AttributeType.USERDEF009, dataStr);
		super.okPressed();
		parameterDataList = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
		parameterDataList = null;
	}

	/**
	 * Returns the elements to display in the viewer when its input is set to
	 * the given element. These elements can be presented as rows in a table,
	 * items in a list, etc. The result is not modified by the viewer.
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 * 
	 * @param inputElement
	 *            the input element.
	 * @return the array of elements to display in the viewer.
	 */
	public Object[] getElements(Object inputElement) {
		return (Object[]) inputElement;
	}

	/**
	 * Disposes of this content provider.
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		parameterDataItems = null;
		parameterDataList = null;
	}

	/**
	 * Notifies this content provider that the given viewer's input has been
	 * switched to a different element.
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 * 
	 * @param viewer
	 *            the viewer
	 * @param oldInput
	 *            the old input element, or null if the viewer did not
	 *            previously have an input.
	 * @param newInput
	 *            the new input element, or null if the viewer does not have an
	 *            input.
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
