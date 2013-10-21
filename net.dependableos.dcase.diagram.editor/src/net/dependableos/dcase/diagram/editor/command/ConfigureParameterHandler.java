/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.command.SetAttributeHandler;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.ui.ParameterConfigDialog;
import net.dependableos.dcase.diagram.ui.AttributeDialog;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler class to handle the parameter configuration dialog on the attribute dialog.
 */
public class ConfigureParameterHandler extends SetAttributeHandler {

    /**
     * the dialog.
     */
    private ParameterConfigDialog dialog = null;
    
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
            dialog = new ParameterConfigDialog(HandlerUtil.getActiveShell(event));
            basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();
            nodeInfo = ModelUtil.createNodeInfo(basicNode);
            dialog.setNodeInfo(nodeInfo);
        }
        // gets the attribute dialog.
        AttributeDialog attributeDialog = getAttributeDialog(event);
        // sets the desc format string
        nodeInfo.setAttribute(AttributeType.USERDEF005, attributeDialog.getDescFormat());
        // sets the script.
        nodeInfo.setAttribute(AttributeType.USERDEF006, attributeDialog.getScript());
        // sets the parameters.
        nodeInfo.setAttribute(AttributeType.USERDEF007, attributeDialog.getParameters());
        return dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object pressOk(ExecutionEvent event) {
        // gets the attribute dialog.
        AttributeDialog attributeDialog = getAttributeDialog(event);
        // sets the desc format.
        attributeDialog.setDescFormat(
                (String) nodeInfo.getAttribute(AttributeType.USERDEF005));
        // sets the script.
        attributeDialog.setScript(
                (String) nodeInfo.getAttribute(AttributeType.USERDEF006));
        // sets the parameters.
        attributeDialog.setParameters(
                (String) nodeInfo.getAttribute(AttributeType.USERDEF007));
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
