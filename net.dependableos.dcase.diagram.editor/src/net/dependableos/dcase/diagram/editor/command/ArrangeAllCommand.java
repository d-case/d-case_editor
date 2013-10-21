/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.List;

import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.Animation;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command that arranges layout all elements.
 */
public class ArrangeAllCommand extends AbstractTransactionalCommand {

    /**
     * the argument.
     */
    private ArgumentEditPart argumentEditPart;

    /**
     * Creates an ArrangeAllCommand object and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param affectedFiles the list of affected IFiles.
     * @param argumentEditPart 
     */
    public ArrangeAllCommand(TransactionalEditingDomain domain, String label,
            List<IFile> affectedFiles, ArgumentEditPart argumentEditPart) {
        super(domain, label, affectedFiles);

        this.argumentEditPart = argumentEditPart;
    }

    /**
     * The layout type name.
     */
    public static final String LAYOUT_TYPE = "Dcase";
    /**
     * The animation time.
     */
    private static final int CONST_ANIMATION_TIME = 2000;

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        if (this.argumentEditPart == null) {
            return CommandResult.newErrorCommandResult("");
        }

        ArrangeRequest request = new ArrangeRequest(
                ActionIds.ACTION_ARRANGE_ALL, LAYOUT_TYPE);

        Command command = this.argumentEditPart.getCommand(request);
        
        Animation.markBegin();
        
        command.execute();
        
        Animation.run(CONST_ANIMATION_TIME);
        
        return CommandResult.newOKCommandResult();
    }

}
