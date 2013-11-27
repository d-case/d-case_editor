/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef001EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef005EditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * A handler to contract the module.
 */
public class ModuleHideHandler extends AbstractEditPartHandler {

	/**
	 * the Labels for executing commands.
	 */
	private static final String SET_BOUNDS_CMD_LABEL = "command for opening the Module"; //$NON-NLS-1$

	/**
	 * Contracts the module.
	 * 
	 * @param event
	 *            ExecutionEvent
	 * @return the result of the execution.
	 * @throws ExecutionException
	 *             if an exception occurred during execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		DcaseNodeEditPart editPart = DcaseEditorUtil
				.getFirstCurrentSelectedPart();
		DcaseLinkEditPart linkEditPart = null;
		Dimension dimension = null;
		if (editPart instanceof GoalEditPart) {
			dimension = ((GoalEditPart) editPart).setModuleValue("", null); //$NON-NLS-1$
		} else if (editPart instanceof Userdef005EditPart) {
			dimension = ((Userdef005EditPart) editPart).setModuleValue(""); //$NON-NLS-1$
		} else if (editPart instanceof Userdef001EditPart) {
			dimension = ((Userdef001EditPart) editPart).setModuleValue(""); //$NON-NLS-1$
		}
		if (dimension != null && !dimension.isEmpty()) {
			TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
					.getEditingDomain(currentDiagram.eResource()
							.getResourceSet());
			ICommand command = new SetBoundsCommand(currentDomain,
					SET_BOUNDS_CMD_LABEL, (editPart != null) ? editPart
							: linkEditPart, dimension);
			currentArgumentEditPart.getDiagramEditDomain()
					.getDiagramCommandStack()
					.execute(new ICommandProxy(command));
		}
		return null;
	}

}
