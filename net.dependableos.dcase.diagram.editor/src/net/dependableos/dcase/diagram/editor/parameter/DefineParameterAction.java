/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.parameter;

import java.util.HashMap;
import java.util.Map;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.message.Menus;
import net.dependableos.dcase.diagram.editor.ui.ParameterDefineDialog;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * An action to show a dialog to configure parameters.
 */
public class DefineParameterAction implements IObjectActionDelegate {

	/**
	 * the key of Parameter subtype.
	 */
	private static final String SUBTYPE_PARAMETER = "_UI_System_subType_param"; //$NON-NLS-1$
	
	/**
	 * the workbench.
	 */
	private IWorkbenchPart targetPart;

	/**
	 * Sets the active workbench part.
	 * 
	 * @param action
	 *            the action.
	 * @param targetPart
	 *            the active workbench part.
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
	 * Shows a dialog to configure parameters.
	 * 
	 * @param action
	 *            the action.
	 */
	public void run(IAction action) {
		// gets the selected node.
		BasicNode basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();

		// check d*
		if (basicNode instanceof Argument) {
			if (!ModuleUtil
					.isDstarFile(DcaseEditorUtil.getCurrentDiagramFile())) {
				return;
			}
		}

		NodeInfo nodeInfo = ModelUtil.createNodeInfo(basicNode);
		if (nodeInfo != null) {
			ParameterDefineDialog dialog = new ParameterDefineDialog(getShell());
			dialog.setNodeInfo(nodeInfo);
			if (Dialog.OK == dialog.open()) {
				// gets the argument.
				ArgumentEditPart argumentEditPart = DcaseEditorUtil
						.getCurrentArgumentEditPart();
				// creates a map of attributes to update.
				Map<AttributeType, Object> attributeMap = new HashMap<AttributeType, Object>();
				// sets values to the map.
				String userdef009 = (String) nodeInfo
						.getAttribute(AttributeType.PARAMETERDEFS);
				attributeMap.put(AttributeType.PARAMETERDEFS, userdef009);
				String userdef007 = (String) nodeInfo
						.getAttribute(AttributeType.PARAMETERVALS);
				attributeMap.put(AttributeType.PARAMETERVALS,
						ParameterUtil.updateParameters(userdef007, userdef009));
				attributeMap.put(AttributeType.SUBTYPE,
						DcaseEditPlugin.getPlugin().getString(SUBTYPE_PARAMETER));

				// creates a command.
				ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
						argumentEditPart.getEditingDomain(),
						Menus.ConfigureParameterAction_0, null, basicNode,
						attributeMap);
				// executes the command.
				argumentEditPart.getDiagramEditDomain()
						.getDiagramCommandStack()
						.execute(new ICommandProxy(changeCommand));
				// notify to all editors
				IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorReference[] editorRefs = workbenchPage
						.getEditorReferences();
				for (IEditorReference ref : editorRefs) {
					DcaseDiagramEditor editor = (DcaseDiagramEditor) ref
							.getEditor(false);
					if (editor != null) {
						editor.setNotifyFlag(true);
					}
				}
			}
		}
	}

}
