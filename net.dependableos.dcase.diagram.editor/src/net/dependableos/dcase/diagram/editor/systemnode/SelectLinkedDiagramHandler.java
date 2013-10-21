/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.systemnode;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_FILE_EXTENSION;

import java.util.HashMap;
import java.util.Map;


import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.common.util.TermsMessages;
import net.dependableos.dcase.diagram.common.util.UserInterfaceUtil;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.editor.command.FileSelectionStatusValidator;
import net.dependableos.dcase.diagram.providers.FileExtensionRestrictTreeContentProvider;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A handler to show a dialog to select the linked diagram of a system node.
 */
public class SelectLinkedDiagramHandler extends AbstractSystemNodeHandler {

    /**
     * the file extension.
     */
    private static final String SELECT_FILE_EXTENSION = PropertyUtil
            .getSystemProperty(DIAGRAM_FILE_EXTENSION);

    /**
     * Shows a dialog to select the linked diagram of a system node.
     * 
     * @param event the event.
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     * @see org.eclipse.core.commands.AbstractHandler
     *      #execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the selected edit part.
        SystemEditPart systemEditPart = getSelectedEditPart(event);
        if (systemEditPart != null) {
            net.dependableos.dcase.System systemObj = getSystem(systemEditPart);
            if (systemObj != null) {

                IWorkbenchWindow window = HandlerUtil
                        .getActiveWorkbenchWindowChecked(event);

                // creates a dialog to select elements out of a tree structure. 
                ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                        window.getShell(), new DecoratingLabelProvider(
                                new WorkbenchLabelProvider(), PlatformUI
                                        .getWorkbench().getDecoratorManager()
                                        .getLabelDecorator()),
                        new FileExtensionRestrictTreeContentProvider(
                                SELECT_FILE_EXTENSION));

                //  sets projects of the workspace.
                fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot()
                        .getProjects());

                fileDialog.setAllowMultiple(false);
                fileDialog.setBlockOnOpen(true);
                fileDialog.setTitle(TermsMessages.SelectLinkedDiagramHandler_0);
                // sets the validator. 
                fileDialog.setValidator(new FileSelectionStatusValidator());
                // sets the selected diagram file.
                NodeInfo goalNodeInfo = ModelUtil.createNodeInfo(systemObj);
                String nodeLinktStr = (String) goalNodeInfo
                        .getAttribute(AttributeType.NODE_LINK);
                TreePath treePath = UserInterfaceUtil
                        .convertFilePathToTreePath(nodeLinktStr,
                                ResourcesPlugin.getWorkspace().getRoot());
                if (treePath != null) {
                    fileDialog.setInitialSelection(treePath);
                }

                // opens the dialog.
                fileDialog.open();

                // applies.
                Object[] results = fileDialog.getResult();
                if (results != null && results.length == 1
                        && results[0] instanceof IResource) {

                    // gets the path to the selected diagram file.
                    String diagramPath = ((IResource) results[0]).getFullPath()
                            .toString();

                    // creates a map of the attributes to update.
                    Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
                    attrMap.put(AttributeType.NODE_LINK, diagramPath);

                    // creates a command to update the attributes.
                    TransactionalEditingDomain currentDomain = systemEditPart
                            .getEditingDomain();
                    ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
                            currentDomain, Menus.SelectLinkedDiagramHandler_0,
                            null, systemObj, attrMap);

                    // executes the command.
                    systemEditPart.getDiagramEditDomain()
                            .getDiagramCommandStack().execute(
                                    new ICommandProxy(changeCommand));
                }
            }
        }

        return null;
    }

}
