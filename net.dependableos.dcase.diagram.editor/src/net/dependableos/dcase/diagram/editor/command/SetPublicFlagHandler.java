/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;

import java.util.Map;
import java.util.HashMap;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

/**
 * A handler class to handle the parameter dialog on the attribute dialog.
 */
public class SetPublicFlagHandler extends AbstractEditPartHandler {

	private final static String SET_FLAG_CMD_LABEL = "command for setting public flag"; //$NON-NLS-1$

	/**
	 * Sets the "P" flag.
	 * 
	 * @param event
	 *            An event
	 * @return the result of the execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		GraphicalEditPart selectedElement = getSelectedElement(event);
		if (selectedElement == null) {
			selectedElement = DcaseEditorUtil.getCurrentArgumentEditPart();
		}
		if (selectedElement != null) {
			BasicNode basicNode = getBasicNode(selectedElement);
			if (basicNode != null) {
				// get flag info
				NodeInfo nodeInfo = ModelUtil.createNodeInfo(basicNode);
				String flags = (String) nodeInfo
						.getAttribute(AttributeType.USERDEF015);
				String publicFlag = ModuleUtil.getPublicFlagString();
				if (flags == null || flags.length() == 0) {
					flags = publicFlag;
				} else if (flags.indexOf(publicFlag) < 0) {
					flags += publicFlag;
				} else {
					return null;
				}

				// set flag info
				Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
				attrMap.put(AttributeType.USERDEF015, flags);
				TransactionalEditingDomain currentDomain = DcaseEditorUtil
						.getCurrentArgumentEditPart().getEditingDomain();
				ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
						currentDomain, SET_FLAG_CMD_LABEL, null, basicNode,
						attrMap);
				selectedElement.getDiagramEditDomain().getDiagramCommandStack()
						.execute(new ICommandProxy(changeCommand));
			}
		}
		return null;
	}

}
