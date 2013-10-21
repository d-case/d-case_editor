/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.diagram.edit.parts.custom.DcaseDelegateNodeEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.swt.graphics.Color;

/**
 * A transaction command to set background color of EditParts.
 */
public class ChangeBackgroundColorEditPartsCommand extends AbstractCommand {

    /**
     * the command label.
     */
    public static final String CONST_CHANGE_BACKGROUNDCOLOR_COMMAND_LABEL =
        "Change BackgroundColor command"; //$NON-NLS-1$

    /**
     * the map of new background color.
     */
    private Map<DcaseDelegateNodeEditPart, Color> changeBackgroundColorEditPartMap = null;

    /**
     * the map of original background color.
     */
    private Map<DcaseDelegateNodeEditPart, Color> restoreBackgroundColorEditPartMap = null;

    /**
     * Creates a ChangeBackgroundColorEditPartsCommand object and initializes it.
     * 
     * @param label the command label.
     * @param changeMap the map of new background color.
     */
    public ChangeBackgroundColorEditPartsCommand(String label,
            Map<DcaseDelegateNodeEditPart, Color> changeMap) {
        super(label, null);
        changeBackgroundColorEditPartMap = changeMap;

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

        if (restoreBackgroundColorEditPartMap == null) {
            return CommandResult.newOKCommandResult();
        }
        Set<DcaseDelegateNodeEditPart> changeSet = restoreBackgroundColorEditPartMap
                .keySet();
        for (DcaseDelegateNodeEditPart editPart : changeSet) {
            editPart.setBackgroundColorEx(restoreBackgroundColorEditPartMap
                    .get(editPart));
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Saves original background colors and sets new background color.
     */
    private void doProcess() {
        restoreBackgroundColorEditPartMap = new HashMap<DcaseDelegateNodeEditPart, Color>();
        Set<DcaseDelegateNodeEditPart> changeSet = changeBackgroundColorEditPartMap
                .keySet();
        for (DcaseDelegateNodeEditPart editPart : changeSet) {
            restoreBackgroundColorEditPartMap.put(editPart, editPart
                    .getBackgroundColorEx());
            editPart.setBackgroundColorEx(changeBackgroundColorEditPartMap
                    .get(editPart));
        }
    }
}
