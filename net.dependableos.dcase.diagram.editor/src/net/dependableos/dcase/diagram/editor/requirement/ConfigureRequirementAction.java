/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.requirement;

import java.util.HashMap;
import java.util.Map;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;


import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * The action class to open the Requirement dialog related to Context. 
 */
public class ConfigureRequirementAction implements IObjectActionDelegate {

    /**
     * workbench part.
     */
    private IWorkbenchPart targetPart;

    /**
     * Sets the active part for the delegate.
     * 
     * @param action
     *            the action proxy that handles presentation portion of the
     *            action; must not be <code>null</code>.
     * @param targetPart
     *            the new part target; must not be <code>null</code>.
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    /**
     * Notifies this action delegate that the selection in the workbench has changed.
     * 
     * @param action the action proxy that handles presentation portion of 
     *      the action
     * @param selection the current selection, or <code>null</code> if there
     *      is no selection.
     * 
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * Returns the shell.
     * 
     * @return shell
     */
    private Shell getShell() {
        return targetPart.getSite().getShell();
    }

    /**
     * The function that execute when menu item is clicked.
     * 
     * @param action action
     */
    public void run(IAction action) {
        // get the selected node
    	ContextEditPart contextEditPart = (ContextEditPart) DcaseEditorUtil.getFirstCurrentSelectedPart();
        BasicNode basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();

        if (basicNode != null) {
            RequirementDialog dialog = new RequirementDialog(getShell(), contextEditPart);
            if (Dialog.OK == dialog.open()) {
                // gets the Argument edit part
                ArgumentEditPart argumentEditPart = DcaseEditorUtil
                        .getCurrentArgumentEditPart();

                // creates a map for updating the property.
                Map<AttributeType, Object> attributeMap = new HashMap<AttributeType, Object>();
                // sets the updating value to the map.
                attributeMap.put(AttributeType.REQUIREMENTS,
                        dialog.getRequirements());
                // creates a command to updating the property.
                ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                        argumentEditPart.getEditingDomain(),
                        Messages.ConfigureRequirementAction_CommandLabel, null,
                        basicNode, attributeMap);
                // executes the command.
                argumentEditPart.getDiagramEditDomain()
                        .getDiagramCommandStack()
                        .execute(new ICommandProxy(changeCommand));
            }
        }
    }
}