/*
 * Copyright (C) 2012,2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.ui.SetResponsibilityDialog;

import java.util.Map;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to calculate the score.
 */
public class SetResponsibilityAction implements IObjectActionDelegate {

	private final String CHANGE_ATTR_CMD_LABEL = "change responsibilities attribute"; //$NON-NLS-1$
	private final String SET_RESP_CMD_LABEL = "set responsibilities"; //$NON-NLS-1$

	/**
	 * the action ID.
	 */
	public static final String ID = "net.dependableos.dcase.diagram.editor.SetResponsibilityActionID"; //$NON-NLS-1$ 

	/**
	 * the workbenck.
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
	 * Set responsibility to argument.
	 * 
	 * @param action
	 *            IAction.
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		ArgumentEditPart argumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		EObject aobj = DcaseEditorUtil.getElement(argumentEditPart);
		if (!(aobj instanceof Argument)) {
			return;
		}
		Argument argument = (Argument) aobj;
		SetResponsibilityDialog dialog = new SetResponsibilityDialog(targetPart
				.getSite().getShell());
		String oldName = ModuleUtil.getResponsibilityName(argument);
		dialog.setName(oldName);
		String oldAddr = ModuleUtil.getResponsibilityAddress(argument);
		dialog.setAddress(oldAddr);
		String oldIcon = ModuleUtil.getResponsibilityIconPath(argument);
		dialog.setIconPath(oldIcon);
		String oldTime = ModuleUtil.getResponsibilityTime(argument);
		dialog.setTime(oldTime);
		if (dialog.open() == Dialog.OK) {
			String newName = dialog.getName();
			String newAddr = dialog.getAddress();
			String newIcon = dialog.getIconPath();
			String newTime = dialog.getTime();
			String newStr = newName + newAddr + newIcon + newTime; // new* != null
			if (newStr.equals(oldName + oldAddr + oldIcon + oldTime)) {
				return;
			}

			Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
			TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
					.getEditingDomain(currentDiagram.eResource()
							.getResourceSet());
			Map<AttributeType, Object> attrNewMap = new HashMap<AttributeType, Object>();
			if (! newName.equals(oldName)) {
				attrNewMap.put(AttributeType.RESPNAME, newName);
			}
			if (! newAddr.equals(oldName)) {
				attrNewMap.put(AttributeType.RESPADDRESS, newAddr);
			}
			if (! newIcon.equals(oldIcon)) {
				attrNewMap.put(AttributeType.RESPICON, newIcon);
			}
			if (! newTime.equals(oldTime)) {
				attrNewMap.put(AttributeType.RESPTIME, newTime);
			}
			CompoundCommand addCmd = new CompoundCommand(CHANGE_ATTR_CMD_LABEL);
			ICommand setUserdef012Command = new ChangeBasicNodePropertyTransactionCommand(
					currentDomain, SET_RESP_CMD_LABEL, null, argument, attrNewMap);
			addCmd.add(new ICommandProxy(setUserdef012Command));
			argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(addCmd);
		}
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

}
