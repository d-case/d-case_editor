/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

public class MoveSelectedCommand extends AbstractTransactionalCommand {

	private static final String MOVE_NODE_CMD_LABEL = "command for moving node-"; //$NON-NLS-1$

	private Point point;

	public MoveSelectedCommand(TransactionalEditingDomain domain, String label,
			Point point) {
		super(domain, label, null);
		this.point = point;
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
		TransactionalEditingDomain domain = this.getEditingDomain();
		DcaseNodeEditPart topEditPart = DcaseEditorUtil
				.getTopCurrentSelectedPart();
		if (topEditPart == null) {
			return null;
		}
		CompoundCommand cc = new CompoundCommand(this.getLabel());
		// move
		Dimension diffDimension = point
				.getDifference(topEditPart.getLocation());
		int i = 0;
		for (DcaseNodeEditPart editPart : DcaseEditorUtil.getSelectedPart()) {
			Point eachPoint = editPart.getLocation().translate(diffDimension);
			ICommand positionCommand = new SetBoundsCommand(domain,
					MOVE_NODE_CMD_LABEL + i, new EObjectAdapter(
							(EObject) editPart.getModel()), eachPoint);
			cc.add(new ICommandProxy(positionCommand));
			i++;
		}
		// execute
		if (i > 0) {
			ArgumentEditPart argumentEditPart = DcaseEditorUtil
					.getCurrentArgumentEditPart();
			argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(cc);
		}
		return CommandResult.newOKCommandResult();
	}

}
