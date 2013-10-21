/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.parameter;

import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.ui.ShowParameterDialog;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to show a dialog to set parameters.
 */
public class ShowParameterAction implements IObjectActionDelegate {

	/**
	 * the workbench.
	 */
	private IWorkbenchPart targetPart;

	/**
	 * Sets the active part for the delegate.
	 * 
	 * @param action
	 *            the action proxy that handles presentation portion of the
	 *            action; must not be null.
	 * @param targetPart
	 *            the new part target; must not be null.
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * Notifies this action delegate that the selection in the workbench has
	 * changed.
	 * 
	 * @param action
	 *            the action proxy that handles presentation portion of the
	 *            action
	 * @param selection
	 *            the current selection, or null if there is no selection.
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * Returns the shell.
	 * 
	 * @return the shell.
	 */
	private Shell getShell() {
		return targetPart.getSite().getShell();
	}

	/**
	 * Shows a dialog to set parameters..
	 * 
	 * @param action
	 *            IAction.
	 */
	public void run(IAction action) {
		ShowParameterDialog dialog = new ShowParameterDialog(getShell(),
				DcaseEditorUtil.getTopCurrentSelectedPart());
		dialog.open();
	}
}
