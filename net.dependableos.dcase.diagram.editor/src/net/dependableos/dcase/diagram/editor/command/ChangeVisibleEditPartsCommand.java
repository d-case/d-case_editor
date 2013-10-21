/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;

/**
 * A transaction command to set visibility of EditParts.
 */
public class ChangeVisibleEditPartsCommand extends AbstractCommand {

    /**
     * the command label.
     */
    public static final String CONST_CHANGE_VISIBLE_COMMAND_LABEL = "Change Visible command"; //$NON-NLS-1$

    /**
     * the set of EditParts to set visibility.
     */
    private Set<GraphicalEditPart> changeVisibleEditPartSet;

    /**
     * new value of visibility.
     */
    private boolean visible;

    /**
     * the map of original value of visibility.
     */
    private Map<GraphicalEditPart, Boolean> restoreVisibleEditPartMap;

    /**
     * Creates a ChangeVisibleEditPartsCommand object and initializes it.
     * 
     * @param label the command label.
     * @param changeSet the set of EditParts to set visibility.
     * @param changeVisible new value of visibility.
     */
    public ChangeVisibleEditPartsCommand(String label,
            Set<GraphicalEditPart> changeSet, boolean changeVisible) {
        super(label, null);
        changeVisibleEditPartSet = changeSet;
        visible = changeVisible;
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
        if (restoreVisibleEditPartMap == null) {
            return CommandResult.newOKCommandResult();
        }
        Set<GraphicalEditPart> changeSet = restoreVisibleEditPartMap.keySet();
        for (GraphicalEditPart editPart : changeSet) {
            editPart.getFigure().setVisible(
                    restoreVisibleEditPartMap.get(editPart));
        }
        return CommandResult.newOKCommandResult();
    }

    /**
     * Saves original values of visibility and sets new values of visibility.
     */
    private void doProcess() {
        restoreVisibleEditPartMap = new HashMap<GraphicalEditPart, Boolean>(
                COLLECTION_INITIAL_CAPACITY);

        for (GraphicalEditPart editPart : changeVisibleEditPartSet) {
            restoreVisibleEditPartMap.put(editPart, editPart.getFigure()
                    .isVisible());
            editPart.getFigure().setVisible(visible);
        }
    }
}
