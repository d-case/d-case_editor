/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseDelegateEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;

/**
 * A transaction command to set line width EditParts.
 */
public class ChangeLineWidthCommand extends AbstractCommand {

    /**
     * the map of new foreground color.
     */
    private Map<DcaseDelegateEditPart, Integer> changeLineWidthEditPartMap = null;

    /**
     * the map of original foreground color.
     */
    private Map<DcaseDelegateEditPart, Integer> restoreLineWidthEditPartMap = null;

    /**
     * Creates a ChangeForegroundColorEditPartsCommand object and initializes it.
     * 
     * @param label the command label.
     * @param changeMap the map of new foreground color.
     */
    public ChangeLineWidthCommand(String label,
            Map<DcaseDelegateEditPart, Integer> changeMap) {
        super(label);
        changeLineWidthEditPartMap = changeMap;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecuteWithResult(
     * org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(
            IProgressMonitor progressMonitor, IAdaptable info)
            throws ExecutionException {

        doProcess();
        
        return CommandResult.newOKCommandResult();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doRedoWithResult(
     * org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {
        doProcess();
        return CommandResult.newOKCommandResult();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doUndoWithResult(
     * org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {

        if (restoreLineWidthEditPartMap == null) {
            return CommandResult.newOKCommandResult();
        }
        Set<DcaseDelegateEditPart> changeSet = restoreLineWidthEditPartMap
                .keySet();
        for (DcaseDelegateEditPart editPart : changeSet) {
            editPart.setLineWidthEx(restoreLineWidthEditPartMap
                    .get(editPart));
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Saves original line width and sets new line width.
     */
    private void doProcess() {
        restoreLineWidthEditPartMap = new HashMap<DcaseDelegateEditPart, Integer>();
        Set<DcaseDelegateEditPart> changeSet = changeLineWidthEditPartMap
                .keySet();
        for (DcaseDelegateEditPart editPart : changeSet) {
            restoreLineWidthEditPartMap.put(editPart, editPart
                    .getLineWidthEx());
            editPart.setLineWidthEx(changeLineWidthEditPartMap
                    .get(editPart));
        }
    }
}
