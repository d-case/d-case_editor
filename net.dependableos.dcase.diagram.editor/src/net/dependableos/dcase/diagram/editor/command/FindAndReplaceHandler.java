/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.ui.FindAndReplaceDialog;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler to perform find-and-replace.
 */
public class FindAndReplaceHandler extends AbstractEditPartHandler {

    /**
     * Show the find-and-replace dialog and perform it.
     * 
     * @param event ExecutionEvent
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        final DcaseDiagramEditor dcaseDiagramEditor = DcaseEditorUtil
                .getCurrentDcaseEditor();
        if (dcaseDiagramEditor != null) {
            IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
            FindAndReplaceDialog dialog = new FindAndReplaceDialog(
                    window.getShell(), 
                    dcaseDiagramEditor);
            dialog.open();
        }
        
        return null;
    }

}
