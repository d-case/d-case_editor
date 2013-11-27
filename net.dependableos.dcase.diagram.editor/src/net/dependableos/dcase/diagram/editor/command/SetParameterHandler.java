/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.verifier.ParameterDialog;
import net.dependableos.dcase.diagram.ui.AttributeDialog;
import net.dependableos.dcase.diagram.command.SetAttributeHandler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler class to handle the parameter dialog on the attribute dialog.
 */
public class SetParameterHandler extends SetAttributeHandler {
    
    /**
     * The dialog.
     */
    private ParameterDialog dialog;
    
    /**
     * The selected node.
     */
    private BasicNode basicNode;
    
    /**
     * The node information.
     */
    private NodeInfo nodeInfo;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        dialog = getDialog(event);
        
        if (dialog.getParameters().length == 0) {
            String userdef009 = (String) nodeInfo.getAttribute(AttributeType.PARAMETERDEFS);
            String attributeName = ""; //$NON-NLS-1$
            if (basicNode instanceof Argument) {
                attributeName = "Global Parameters"; //$NON-NLS-1$
            } else {
                attributeName = "Parameters"; //$NON-NLS-1$
            }
            if (userdef009 == null || userdef009.trim().length() == 0) {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.SetParameterHandler_ParametersEmptyMessage, attributeName));
            } else {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.SetParameterHandler_ParametersInvalidMessage, attributeName));
            }
            return null;
        } else if (Dialog.OK == dialog.open()) {
            
            return pressOk(event);
        } else {
            return dialog;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ParameterDialog getDialog(ExecutionEvent event) {

        if (dialog == null) {
            dialog = new ParameterDialog(HandlerUtil.getActiveShell(event));
            basicNode = ((BasicNode) DcaseEditorUtil.getSelectedObject());
            nodeInfo = (ModelUtil.createNodeInfo(basicNode));
        }
        AttributeDialog attributeDialog = getAttributeDialog(event);
        nodeInfo.setAttribute(AttributeType.PARAMETERIZEDDESC, attributeDialog.getDescFormatText());
        nodeInfo.setAttribute(AttributeType.PARAMETERVALS, attributeDialog.getParameters());
        nodeInfo.setAttribute(AttributeType.PARAMETERDEFS, attributeDialog.getParameterDefinitions());
        dialog.setNodeInfo(nodeInfo);
        return dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object pressOk(ExecutionEvent event) {
        // gets the attribute dialog.
        AttributeDialog attributeDialog = getAttributeDialog(event);
        attributeDialog.setParameters(
                (String) nodeInfo.getAttribute(AttributeType.PARAMETERVALS));
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
