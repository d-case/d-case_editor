/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.HashMap;
import java.util.Map;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.ui.SelectFromWebDialog;
import net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler class to select the attachment from web.
 */
public class SelectAttachmentFromWebHandler extends AbstractEditPartHandler implements IAttachmentSelector {
    
    /**
     * the attachment selector name.
     */
    private String name;
    
    /**
     * Shows a dialog to select the attachment from web.
     * 
     * @param event An event
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the GraphicalEditPart of selected node.
        GraphicalEditPart selectedElement = getSelectedElement(event);

        if (selectedElement != null) {
            // gets the BasicNode object from the GraphicalEditPart.
            BasicNode basicNode = getBasicNode(selectedElement);
            if (basicNode != null) {

                IWorkbenchWindow window = HandlerUtil
                        .getActiveWorkbenchWindowChecked(event);

                SelectFromWebDialog dialog = new SelectFromWebDialog(window.getShell());
                dialog.setUrl(basicNode.getAttachment());
                // displays the dialog.
                if (dialog.open() == Dialog.OK) {

                    String selectedUrl = dialog.getUrl();
                    
                    // create the attribute map.
                    Map<AttributeType, Object> attributeMap = new HashMap<AttributeType, Object>();
                    // sets the attachment to attribute map.
                    attributeMap.put(AttributeType.ATTACHMENT, selectedUrl);
    
                    // creates the command to change the attribute.
                    TransactionalEditingDomain currentDomain = DcaseEditorUtil
                    .getCurrentArgumentEditPart().getEditingDomain();
                    ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                            currentDomain, Menus.SelectAttachmentHandler_0,
                            null, basicNode, attributeMap);
    
                    // executes the command to change the attribute.
                    selectedElement.getDiagramEditDomain()
                    .getDiagramCommandStack().execute(
                            new ICommandProxy(changeCommand));
                }
            }
        }
        return null;
    }
    
    /**
     * Selects the attachment.
     * 
     * @param parent the parent shell.
     * @param currentAttachment the original attachment.
     * @param basicNode the node.
     * @return the selected attachment.
     * 
     * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#selectAttachment(java.lang.String)
     */
    public String selectAttachment(Shell parent, String currentAttachment,
            BasicNode basicNode) {

        SelectFromWebDialog dialog = new SelectFromWebDialog(parent);
        dialog.setUrl(basicNode.getAttachment());
        // displays the dialog.
        if (dialog.open() == Dialog.OK) {

            return dialog.getUrl();
        }
        return null;
    }

    /**
     * Returns the attachment selector name.
     * @return the attachment selector name.
     * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#getName()
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the attachment selector name.
     * @param name the attachment selector name.
     * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#setName(java.lang.String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Processes after care.
     * @param node the node.
     * @param attachment the new attachment value.
     */
    public void postProcess(BasicNode node, String attachment) {
    	SelectModuleContributionItem.doPostProcess(node, null);
    }
    
    public String getRespName(String attachment) {
    	return null;
    }
    public String getRespAddress(String attachment) {
    	return null;
    }
    public String getRespIcon(String attachment) {
    	return null;
    }
    public String getRespTime(String attachment) {
    	return null;
    }
}
