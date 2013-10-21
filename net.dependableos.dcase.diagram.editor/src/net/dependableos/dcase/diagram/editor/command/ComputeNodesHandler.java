/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * The class that command handler of Compute nodes.
 */
public class ComputeNodesHandler extends AbstractEditPartHandler {
   
    /**
     * The ID of the view as specified by the extension.
     */
    private static final String COMPUTE_NODES_VIEW_ID =
            "net.dependableos.dcase.diagram.editor.views.computenodes";

    /**
     * Execute command.
     * 
     * @param arg0 the event argument.
     * @throws ExecutionException the ExecutionException.
     * @return null.
     */
    public Object execute(ExecutionEvent arg0) throws ExecutionException {
        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
        
        try {
            workbenchPage.showView(COMPUTE_NODES_VIEW_ID);
        } catch (Exception e) {
            MessageWriter.writeMessageToConsole(e.getMessage(), MessageTypeImpl.DIAGNOSIS);
        }
        
        return null;
    }

}
