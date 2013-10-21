/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.List;
import java.util.Set;

import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to add a model to a diagram.
 */
public class NotifyParametersCommand extends AbstractTransactionalCommand {

	/**
	 * The argument editPart.
	 */
	private ArgumentEditPart argumentEditPart;

	/**
	 * The set of IDs those will be excluded to notify.
	 */
	private Set<String> excludeIdSet;

	/**
	 * Creates transaction command to add a model to a diagram and initializes
	 * it.
	 * 
	 * @param domain
	 *            the editing domain.
	 * @param label
	 *            the user-readable label, should never be null.
	 * @param affectedFiles
	 *            the list of affected IFiles; may be null.
	 */
	public NotifyParametersCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles,
			ArgumentEditPart argumentEditPart, Set<String> excludeIdSet) {
		super(domain, label, affectedFiles);
		this.argumentEditPart = argumentEditPart;
		this.excludeIdSet = excludeIdSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.
	 * AbstractTransactionalCommand
	 * #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
	 * org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		XMLResource resource = DcaseEditorUtil.getXMLResource(argumentEditPart);
		for (Object obj : argumentEditPart.getChildren()) {
			if (obj instanceof DcaseNodeEditPart) {
				DcaseNodeEditPart editPart = (DcaseNodeEditPart) obj;
				String id = DcaseEditorUtil.getUUIDs(editPart, resource);
				if (!excludeIdSet.contains(id)) {
					editPart.notifyParameters();
				}
			}
		}
		return CommandResult.newOKCommandResult();
	}

}
