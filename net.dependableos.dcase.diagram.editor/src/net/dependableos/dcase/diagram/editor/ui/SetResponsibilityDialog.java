/*
 * Copyright (C) 2012,2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.providers.FileExtensionRestrictTreeContentProvider;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * The dialog class to edit responsibilities.
 */
public class SetResponsibilityDialog extends Dialog {

	/**
	 * the dialog width.
	 */
	private static final int INIT_WIDTH = 300;

	/**
	 * column number.
	 */
	private static final int GRID_COLUMNS = 3;

	/**
	 * the text width.
	 */
	private static final int TEXT_WIDTH = 250;

	/**
	 * the key for the label text of the Responsibility attribute.
	 */
	private static final String RESPDIALOG_1 = "_UI_BasicNode_respName_feature"; //$NON-NLS-1$
	private static final String RESPDIALOG_2 = "_UI_BasicNode_respAddress_feature"; //$NON-NLS-1$
	private static final String RESPDIALOG_3 = "_UI_BasicNode_respIcon_feature"; //$NON-NLS-1$
	private static final String RESPDIALOG_4 = "_UI_BasicNode_respIcon_label"; //$NON-NLS-1$
	private static final String RESPDIALOG_5 = "_UI_BasicNode_respTime_feature"; //$NON-NLS-1$

	/**
	 * the format string for a label text.
	 */
	private static final String LABEL_FORMAT = "%s:"; //$NON-NLS-1$

	/**
	 * the responsibility attributes. (name, address and icon)
	 */
	private Text nameText;
	private Text addrText;
	private Text iconText;
	private Text timeText;
	private String nameStr;
	private String addrStr;
	private String iconStr;
	private String timeStr;

	/**
	 * constructor.
	 * 
	 * @param parentShell
	 *            shell.
	 * @param editPart
	 *            the node.
	 */
	public SetResponsibilityDialog(Shell parentShell) {
		super(parentShell);

	}

	/**
	 * Returns dialog size.
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
	 * Creates dialog area.
	 * 
	 * @param parent
	 *            parent
	 * @return Control
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite panel = (Composite) super.createDialogArea(parent);
		((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;
		getShell().setText(Messages.SetResponsibilityDialog_Title);
		createDialogControl(panel);

		return panel;
	}

	/**
	 * Creates dialog control.
	 * 
	 * @param parent
	 *            parent
	 */
	private void createDialogControl(Composite parent) {

		// Name
		createLabel(parent, getAttributeName(RESPDIALOG_1, true));
		nameText = createText(parent, nameStr);
		createLabel(parent);

		// Address
		createLabel(parent, getAttributeName(RESPDIALOG_2, true));
		addrText = createText(parent, addrStr);
		createLabel(parent);

		// Icon
		createLabel(parent, getAttributeName(RESPDIALOG_3, true));
		iconText = createText(parent, iconStr);
		Button iconButton = new Button(parent, SWT.PUSH);
		iconButton.setText(getAttributeName(RESPDIALOG_4, false));
		iconButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
						getShell(), new DecoratingLabelProvider(
								new WorkbenchLabelProvider(), PlatformUI
										.getWorkbench().getDecoratorManager()
										.getLabelDecorator()),
						new FileExtensionRestrictTreeContentProvider(null));
				// set projects from workspace.
				fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot()
						.getProjects());
				fileDialog.setAllowMultiple(false);
				fileDialog.setBlockOnOpen(true);
				fileDialog.setTitle("Select Icon Image"); //$NON-NLS-1$
				fileDialog.open();
				Object[] results = fileDialog.getResult();
				if (results != null && results.length == 1
						&& results[0] instanceof IResource) {
					iconText.setText(((IResource) results[0]).getFullPath()
							.toString());
				}
			}
		});

		// Time
		createLabel(parent, getAttributeName(RESPDIALOG_5, true));
		timeText = createText(parent, timeStr);
		createLabel(parent);

	}

	/**
	 * Sets the responsibility name.
	 * 
	 * @param name
	 *            the responsibility name.
	 */
	public void setName(String name) {
		this.nameStr = name;
	}

	/**
	 * Sets the responsibility address.
	 * 
	 * @param name
	 *            the responsibility address.
	 */
	public void setAddress(String addr) {
		this.addrStr = addr;
	}

	/**
	 * Sets the responsibility icon.
	 * 
	 * @param name
	 *            the responsibility icon.
	 */
	public void setIconPath(String icon) {
		this.iconStr = icon;
	}

	/**
	 * Sets the responsibility time.
	 * 
	 * @param name
	 *            the responsibility time.
	 */
	public void setTime(String value) {
		this.timeStr = value;
	}

	/**
	 * Returns the responsibility name.
	 * 
	 * @return the responsibility name.
	 */
	public String getName() {
		return nameStr;
	}

	/**
	 * Returns the responsibility address.
	 * 
	 * @return the responsibility address.
	 */
	public String getAddress() {
		return addrStr;
	}

	/**
	 * Returns the responsibility icon.
	 * 
	 * @return the responsibility icon.
	 */
	public String getIconPath() {
		return iconStr;
	}

	/**
	 * Returns the responsibility time.
	 * 
	 * @return the responsibility time.
	 */
	public String getTime() {
		return timeStr;
	}

	/**
	 * Sets the responsibility.
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		nameStr = nameText.getText();
		addrStr = addrText.getText();
		iconStr = iconText.getText();
		timeStr = timeText.getText();
		super.okPressed();
	}

	/**
	 * Creates a label and initializes it to represent the empty text.
	 * 
	 * @param parent
	 *            the parent.
	 * @return a label.
	 */
	private Label createLabel(Composite parent) {
		return createLabel(parent, ""); //$NON-NLS-1$
	}

	/**
	 * Creates a label and initializes it to represent the specified text.
	 * 
	 * @param parent
	 *            the parent.
	 * @param text
	 *            the text.
	 * @return a label.
	 */
	private Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.WRAP);
		label.setText((text != null) ? text : ""); //$NON-NLS-1$
		label.setAlignment(SWT.LEFT);
		return label;
	}

	/**
	 * Creates a text.
	 * 
	 * @param parent
	 *            the parent.
	 * @return a text.
	 */
	private Text createText(Composite parent, String value) {
		Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setText((value != null) ? value : ""); //$NON-NLS-1$
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.widthHint = TEXT_WIDTH;
		text.setLayoutData(gridData);
		return text;
	}

	/**
	 * Returns the name of the attribute.
	 * 
	 * @param key
	 *            the key for the name of the attribute.
	 * @return the name of the attribute.
	 */
	private String getAttributeName(String key, boolean append) {
		if (append) {
			return String.format(LABEL_FORMAT, DcaseEditPlugin.getPlugin()
					.getString(key));
		} else {
			return DcaseEditPlugin.getPlugin().getString(key);
		}
	}
}
