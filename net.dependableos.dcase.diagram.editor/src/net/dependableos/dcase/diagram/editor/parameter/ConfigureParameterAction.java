/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.parameter;

import java.util.HashMap;
import java.util.Map;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Menus;
import net.dependableos.dcase.diagram.editor.ui.ParameterConfigDialog;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to show a dialog to configure parameters.
 */
public class ConfigureParameterAction implements IObjectActionDelegate {

    /**
     * the workbench.
     */
    private IWorkbenchPart targetPart;

    /**
     * Sets the active workbench part.
     * 
     * @param action the action.
     * @param targetPart the active workbench part.
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    /**
     * Notifies this action delegate that the selection in the workbench has changed.
     * 
     * @param action the action proxy that handles presentation portion of 
     *      the action
     * @param selection the current selection, or null if there
     *      is no selection.
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
     * @param action the action.
     */
    public void run(IAction action) {
        // gets the selected node.        
        BasicNode basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();

        NodeInfo nodeInfo = ModelUtil.createNodeInfo(basicNode);
        if (nodeInfo != null) {
            ParameterConfigDialog dialog = new ParameterConfigDialog(getShell());
            dialog.setNodeInfo(nodeInfo);
            if (Dialog.OK == dialog.open()) {
                // gets the argument.
                ArgumentEditPart argumentEditPart = DcaseEditorUtil
                        .getCurrentArgumentEditPart();
                // creates a map of attributes to update.
                Map<AttributeType, Object> attributeMap = new HashMap<AttributeType, Object>();
                // sets values to the map.
                attributeMap.put(AttributeType.PARAMETERIZEDDESC, nodeInfo
                        .getAttribute(AttributeType.PARAMETERIZEDDESC));
                attributeMap.put(AttributeType.USERDEF006, nodeInfo
                        .getAttribute(AttributeType.USERDEF006));
                attributeMap.put(AttributeType.PARAMETERVALS, nodeInfo
                        .getAttribute(AttributeType.PARAMETERVALS));
                // creates a command.
                ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                        argumentEditPart.getEditingDomain(),
                        Menus.ConfigureParameterAction_0, null, basicNode,
                        attributeMap);
                // executes the command.
                argumentEditPart.getDiagramEditDomain()
                        .getDiagramCommandStack().execute(
                                new ICommandProxy(changeCommand));

            }
        }
    }
}
