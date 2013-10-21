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
import org.eclipse.swt.graphics.Color;

/**
 * A transaction command to set foreground color of EditParts.
 */
public class ChangeForegroundColorEditPartsCommand extends AbstractCommand {

    /**
     * the map of new foreground color.
     */
    private Map<DcaseDelegateEditPart, Color> changeForegroundColorEditPartMap = null;

    /**
     * the map of original foreground color.
     */
    private Map<DcaseDelegateEditPart, Color> restoreForegroundColorEditPartMap = null;

    /**
     * Creates a ChangeForegroundColorEditPartsCommand object and initializes it.
     * 
     * @param label the command label.
     * @param changeMap the map of new foreground color.
     */
    public ChangeForegroundColorEditPartsCommand(String label,
            Map<DcaseDelegateEditPart, Color> changeMap) {
        super(label);
        changeForegroundColorEditPartMap = changeMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.gmf.runtime.emf.commands.core.command.
     * AbstractTransactionalCommand
     * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        doProcess();

        return CommandResult.newOKCommandResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doRedoWithResult
     * (org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {
        doProcess();
        return CommandResult.newOKCommandResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doUndoWithResult
     * (org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {

        if (restoreForegroundColorEditPartMap == null) {
            return CommandResult.newOKCommandResult();
        }
        Set<DcaseDelegateEditPart> changeSet = restoreForegroundColorEditPartMap
                .keySet();
        for (DcaseDelegateEditPart editPart : changeSet) {
            editPart.setForegroundColorEx(restoreForegroundColorEditPartMap
                    .get(editPart));
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Saves original foreground colors and sets new foreground color.
     */
    private void doProcess() {
        restoreForegroundColorEditPartMap = new HashMap<DcaseDelegateEditPart, Color>();
        Set<DcaseDelegateEditPart> changeSet = changeForegroundColorEditPartMap
                .keySet();
        for (DcaseDelegateEditPart editPart : changeSet) {
            restoreForegroundColorEditPartMap.put(editPart, editPart
                    .getForegroundColorEx());
            editPart.setForegroundColorEx(changeForegroundColorEditPartMap
                    .get(editPart));
        }
    }
}
