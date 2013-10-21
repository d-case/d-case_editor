/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.Set;


import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

/**
 * A command to select edit parts excluding specified edit parts.
 */
public class SelectExcludesCommand extends AbstractCommand {

    /**
     * the argument.
     */
    private ArgumentEditPart argumentEditPart;

    /**
     * the set of IDs those will be excluded to select.
     */
    private Set<String> excludeIdSet;


    /**
     * Creates the command and initializes it.
     * 
     * @param label the command label.
     * @param argumentEditPart the argument.
     * @param excludeIdSet the set of IDs those will be excluded to select.
     */
    public SelectExcludesCommand(String label,
            ArgumentEditPart argumentEditPart, Set<String> excludeIdSet) {
        super(label);
        this.argumentEditPart = argumentEditPart;
        this.excludeIdSet = excludeIdSet;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand
     *  #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(
            IProgressMonitor progressMonitor, IAdaptable info)
            throws ExecutionException {

        doProcess();
        return CommandResult.newOKCommandResult();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand
     *  #doRedoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {

        doProcess();
        return CommandResult.newOKCommandResult();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand
     *  #doUndoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
            IAdaptable info) throws ExecutionException {
        return CommandResult.newOKCommandResult();
    }

    /**
     * Selects edit parts excluding specified edit parts.
     */
    private void doProcess() {

        XMLResource resource = DcaseEditorUtil.getXMLResource(argumentEditPart);
        int counter = 0;
        for (Object obj : argumentEditPart.getChildren()) {

            if (!(obj instanceof ShapeNodeEditPart)) {
                continue;
            }
            
            ShapeNodeEditPart nodeEditPart = (ShapeNodeEditPart) obj;

            String id = DcaseEditorUtil.getUUIDs(nodeEditPart, resource);
            if (excludeIdSet.contains(id)) {
                continue;
            }

            if (counter == 0) {
                argumentEditPart.getViewer().select(nodeEditPart);
            } else {
                argumentEditPart.getViewer().appendSelection(nodeEditPart);
            }
            counter++;
        }
    }

}
