/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
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
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.message.Menus;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.verifier.ParameterDialog;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * An action to show a dialog to set parameters.
 */
public class SetParameterAction implements IObjectActionDelegate {

	/**
	 * the key of Parameter subtype.
	 */
	private static final String SUBTYPE_PARAMETER = "_UI_System_subType_param"; //$NON-NLS-1$
	
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
     * Shows a dialog to set parameters..
     * 
     * @param action  IAction.
     */
    public void run(IAction action) {
        // gets the selected node.
        BasicNode basicNode = (BasicNode) DcaseEditorUtil.getSelectedObject();
        
        // check d*
        if (basicNode instanceof Argument) {
        	if (! ModuleUtil.isDstarFile(DcaseEditorUtil.getCurrentDiagramFile())) {
        		return;
        	}
        }

        NodeInfo nodeInfo = ModelUtil.createNodeInfo(basicNode);
        if (nodeInfo != null) {
            ParameterDialog dialog = new ParameterDialog(getShell());
            dialog.setNodeInfo(nodeInfo);
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
                return;
            }
            if (Dialog.OK == dialog.open()) {
                // gets the argument.
                ArgumentEditPart argumentEditPart = DcaseEditorUtil
                        .getCurrentArgumentEditPart();
                // creates a map of the attributes to update and initializes it.
                Map<AttributeType, Object> attributeMap = new HashMap<AttributeType, Object>();
                // sets attribute values.
                attributeMap.put(AttributeType.PARAMETERVALS, nodeInfo
                        .getAttribute(AttributeType.PARAMETERVALS));
                attributeMap.put(AttributeType.SUBTYPE,
						DcaseEditPlugin.getPlugin().getString(SUBTYPE_PARAMETER));
                // creates a command to update attribute values and initializes it.
                ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                        argumentEditPart.getEditingDomain(),
                        Menus.SetParameterAction_0, null, basicNode,
                        attributeMap);
                // executes the command.
                argumentEditPart.getDiagramEditDomain()
                        .getDiagramCommandStack().execute(
                                new ICommandProxy(changeCommand));
                // notify to all editors
                IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getActivePage();
                IEditorReference[] editorRefs = workbenchPage.getEditorReferences();
                for (IEditorReference ref : editorRefs) {
                	DcaseDiagramEditor editor = (DcaseDiagramEditor)ref.getEditor(false);
                	if (editor != null) {
                		editor.setNotifyFlag(true);
                	}
                }
            }
        }
    }

}
