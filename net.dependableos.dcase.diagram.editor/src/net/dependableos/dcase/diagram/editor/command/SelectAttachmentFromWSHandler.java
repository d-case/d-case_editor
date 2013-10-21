/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.HashMap;
import java.util.Map;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.common.util.TermsMessages;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A handler class to select attachment file path from the workspace.
 */
public class SelectAttachmentFromWSHandler extends AbstractEditPartHandler
    implements IAttachmentSelector {
    
    /**
     * the attachment selector name.
     */
    private String name;

    /**
     * Shows a dialog to select the attachment from workspace.
     * 
     * @param event An event
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the EditPart that represents selected node.
        GraphicalEditPart selectedElement = getSelectedElement(event);

        if (selectedElement != null) {
            // gets the BasicNode  that represents selected node.
            BasicNode basicNode = getBasicNode(selectedElement);
            if (basicNode != null) {

                IWorkbenchWindow window = HandlerUtil
                        .getActiveWorkbenchWindowChecked(event);
                
                // selects the attached file.
                NodeInfo goalNodeInfo = ModelUtil.createNodeInfo(basicNode);
                String attachmentStr = (String) goalNodeInfo
                        .getAttribute(AttributeType.ATTACHMENT);

                // creates tree and initializes it to show files in the workspace.
                
                String selectedPath = selectAttachmentFromWS(window.getShell(), attachmentStr);

                // sets the value of the attachment attribute.
                if (selectedPath != null && selectedPath.length() > 0) {

                    Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
                    attrMap.put(AttributeType.ATTACHMENT, selectedPath);

                    TransactionalEditingDomain currentDomain = DcaseEditorUtil
                            .getCurrentArgumentEditPart().getEditingDomain();
                    ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                            currentDomain, Menus.SelectAttachmentHandler_0,
                            null, basicNode, attrMap);

                    selectedElement.getDiagramEditDomain()
                            .getDiagramCommandStack().execute(
                                    new ICommandProxy(changeCommand));
                }
            }
        }
        return null;
    }
    
    /**
     * Select the attachment from the workspace.
     * 
     * @param parent the parent.
     * @param currentAttachment the original attachment.
     * @return the selected attachment.
     */
    private String selectAttachmentFromWS(Shell parent, String currentAttachment) {
                
                IFile selectedFile = null;
                if (currentAttachment != null && currentAttachment.length() > 0) {
                    selectedFile = FileUtil.getWorksapceFileFromPath(currentAttachment);
                }

                // creates tree and initializes it to show files in the workspace.
                
                ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                        parent, new DecoratingLabelProvider(
                                new WorkbenchLabelProvider(), PlatformUI
                                        .getWorkbench().getDecoratorManager()
                                        .getLabelDecorator()),
                        //new FileExtensionRestrictTreeContentProvider(null)
                        new WorkbenchContentProvider());

                // sets projects to the tree selection dialog.
                fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
                fileDialog.setAllowMultiple(false);
                fileDialog.setBlockOnOpen(true);
                // sets the validator.
                fileDialog.setValidator(new FileSelectionStatusValidator());
                fileDialog.setTitle(TermsMessages.SelectAttachmentHandler_0);

                if (selectedFile != null) {
                    fileDialog.setInitialSelection(selectedFile);
                }
                fileDialog.open();

                Object[] results = fileDialog.getResult();

                // sets the value of the attachment attribute.
                if (results != null && results.length == 1
                        && results[0] instanceof IResource) {
                    String diagramPath = ((IResource) results[0]).getFullPath()
                            .toString();

                    return diagramPath;
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
        return selectAttachmentFromWS(parent, currentAttachment);
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

    public String getResponsibility(String attachment) {
    	return null;
    }
    
}
