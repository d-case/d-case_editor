/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command that arranges layout excludes elements those are represented specified ID.
 */
public class ArrangeExcludesCommand extends AbstractTransactionalCommand {

    /**
     * the argument.
     */
    private ArgumentEditPart argumentEditPart;

    /**
     * the set of IDs those will be excluded to arrange layout.
     */
    private Set<String> excludeIdSet;

    /**
     * Creates an ArrangeExcludesCommand object and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param affectedFiles the list of affected IFiles.
     * @param argumentEditPart the argument.
     * @param excludeIdSet the set of IDs those will be excluded to arrange layout.
     */
    public ArrangeExcludesCommand(TransactionalEditingDomain domain,
            String label, List<IFile> affectedFiles,
            ArgumentEditPart argumentEditPart, Set<String> excludeIdSet) {
        super(domain, label, affectedFiles);
        this.argumentEditPart = argumentEditPart;
        this.excludeIdSet = excludeIdSet;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
     *  #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        // gets the EditParts to arrange layout.
        XMLResource resource = DcaseEditorUtil.getXMLResource(argumentEditPart);
        List<ShapeNodeEditPart> selectedEditPartList = new ArrayList<ShapeNodeEditPart>(
                COLLECTION_INITIAL_CAPACITY);
        for (Object obj : argumentEditPart.getChildren()) {

            if (!(obj instanceof ShapeNodeEditPart)) {
                continue;
            }

            ShapeNodeEditPart nodeEditPart = (ShapeNodeEditPart) obj;

            // tests whether the EditPart is to exclude.
            String id = DcaseEditorUtil.getUUIDs(nodeEditPart, resource);
            if (excludeIdSet.contains(id)) {
                continue;
            }

            selectedEditPartList.add(nodeEditPart);
        }

        // creates a command.
        ArrangeRequest request = new ArrangeRequest(
                RequestConstants.REQ_ARRANGE_DEFERRED);
        request.setViewAdaptersToArrange(selectedEditPartList);
        Command arrangeCommand = argumentEditPart.getCommand(request);

        // executes the command.
        arrangeCommand.execute();

        return CommandResult.newOKCommandResult();
    }

}
