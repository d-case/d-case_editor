/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

/**
 * The Select subtree command handler.
 *
 */
public class SelectSubtreeHandler extends AbstractEditPartHandler {

    /**
     * The list of descendant and current id.
     */
    private static List<String> subTreeList = new ArrayList<String>();

    /**
     * Execute command.
     * 
     * @param arg0 the event argument.
     * @throws ExecutionException the ExecutionException.
     * @return null.
     */
    public Object execute(ExecutionEvent arg0) throws ExecutionException {

        // current edit part.
        DcaseNodeEditPart currentEditPart = DcaseEditorUtil.getFirstCurrentSelectedPart();
        // get argument edit part.
        ArgumentEditPart argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart();
        
        // List up selection nodes.
        subTreeList.clear();
        LinkManager linkManager = new LinkManager();
        linkManager.load(
                DcaseEditorUtil.getXMLResource(argumentEditPart));
        String currentNodeId = DcaseEditorUtil.getUUIDs(currentEditPart);
        findChildlen(
                linkManager,
                currentNodeId);

        addConnectionsId(argumentEditPart, subTreeList);

        Set<String> subSelectIdSet = new HashSet<String>();
        subSelectIdSet.addAll(subTreeList);

        // command execute.
        // select nodes
        ICommand selectCommand = new SelectGraphicCommand(Messages.SelectSubtreeHandler_1,
                argumentEditPart, subSelectIdSet);

        selectCommand.execute(null, null);
        
        return null;
    }

    /**
     * Find child nodes.
     * 
     * @param linkManager the link manager.
     * @param uuid the uuid.
     */
    private void findChildlen(
            LinkManager linkManager,
            String uuid) {

        if (linkManager == null
         || StringUtil.isNullOrEmpty(uuid)) {
            return;
        }

        if (subTreeList.contains(uuid)) {
            return;
        }
        subTreeList.add(uuid);

        List<String> targets = linkManager.getTarget(uuid);
        if (targets != null) {
            for (String target : targets) {
                findChildlen(linkManager, target);
            }
        }
        return;
    }
    
    /**
     * Add Connections ID.
     * 
     * @param argumentEditPart the argument edit part
     * @param children the child nodes
     */
    @SuppressWarnings("rawtypes")
    private void addConnectionsId(
            ArgumentEditPart argumentEditPart,
            List<String> children) {
        for (Object node : argumentEditPart.getChildren()) {
            if (!(node instanceof GraphicalEditPart)) {
                continue;
            }
            GraphicalEditPart editPart = (GraphicalEditPart) node;
            
            String uuid = DcaseEditorUtil.getUUIDs(editPart);
            if (children.contains(uuid)) {
                List connections = editPart.getSourceConnections();
                for (Object connection : connections) {
                    if (!(connection instanceof ConnectionNodeEditPart)) {
                        continue;
                    }
                    ConnectionNodeEditPart linkEditPart =
                            (ConnectionNodeEditPart) connection;
                    if (DcaseEditorUtil.getUUIDs(linkEditPart) != null) {
                        children.add(DcaseEditorUtil.getUUIDs(linkEditPart));
                    }
                }
            }
        }
    }
}
