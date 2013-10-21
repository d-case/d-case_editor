/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.command.SetAttributeHandler;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;
import net.dependableos.dcase.diagram.editor.ui.ParameterDefineDialog;
import net.dependableos.dcase.diagram.ui.AttributeDialog;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler class to handle the parameter configuration dialog on the attribute
 * dialog.
 */
public class DefineParameterHandler extends SetAttributeHandler {

	/**
	 * the dialog.
	 */
	private ParameterDefineDialog dialog = null;

	/**
	 * the node information.
	 */
	private NodeInfo nodeInfo;

	/**
	 * the selected node.
	 */
	private BasicNode basicNode;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Dialog getDialog(ExecutionEvent event) {
		if (dialog == null) {
			dialog = new ParameterDefineDialog(
					HandlerUtil.getActiveShell(event));
			basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();
			nodeInfo = ModelUtil.createNodeInfo(basicNode);
		}
		// gets the attribute dialog.
		AttributeDialog attributeDialog = getAttributeDialog(event);
		nodeInfo.setAttribute(AttributeType.USERDEF007,
				attributeDialog.getParameters());
		nodeInfo.setAttribute(AttributeType.USERDEF009,
				attributeDialog.getParameterDefinitions());
		dialog.setNodeInfo(nodeInfo);
		return dialog;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object pressOk(ExecutionEvent event) {
		// gets the attribute dialog.
		AttributeDialog attributeDialog = getAttributeDialog(event);
		String userdef009 = (String) nodeInfo
				.getAttribute(AttributeType.USERDEF009);
		String userdef007 = (String) nodeInfo
				.getAttribute(AttributeType.USERDEF007);
		attributeDialog.setParameterDefinitions(userdef009);
		attributeDialog.setParameters(ParameterUtil.updateParameters(
				userdef007, userdef009));
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		dialog = null;
		nodeInfo = null;
		basicNode = null;
		super.dispose();
	}
}
