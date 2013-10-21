/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.List;


import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to persist view.
 */
public class NotationPersistanceCommand extends AbstractTransactionalCommand {

    /**
     * the argument edit part.
     */
    private ArgumentEditPart argumentEditPart;

    /**
     * Creates the command and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the user-readable label, should never be null.
     * @param affectedFiles the list of affected IFiles; may be null.
     * @param argumentEditPart the argument to persist.
     */
    @SuppressWarnings("unchecked")
    public NotationPersistanceCommand(TransactionalEditingDomain domain,
            String label, List affectedFiles, ArgumentEditPart argumentEditPart) {
        super(domain, label, affectedFiles);
        this.argumentEditPart = argumentEditPart;
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

        // persists all views.
        argumentEditPart.getNotationView().persistChildren();

        return CommandResult.newOKCommandResult();
    }

}
