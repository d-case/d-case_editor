/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.ArrayList;
import java.util.List;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef005EditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

/**
 * A handler to expand the Module..
 */
public class OpenModuleHandler extends AbstractEditPartHandler {

	/**
	 * the Labels for executing commands.
	 */
	private static final String SET_BOUNDS_CMD_LABEL = "command for opening the Module"; //$NON-NLS-1$

	/**
	 * Expands the Module..
	 * 
	 * @param event
	 *            ExecutionEvent
	 * @return the result of the execution.
	 * @throws ExecutionException
	 *             if an exception occurred during execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// gets the selected node.
		GraphicalEditPart editPart = getSelectedElement(event);
		DcaseLinkEditPart linkEditPart = null;
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Resource diagramResource = currentDiagram.eResource();
		Dimension dimension = null;

		if (editPart != null) {
			String attrStr = makeReferenceString(editPart);
			if (attrStr == null || attrStr.length() == 0) {
				MessageWriter.writeMessageToConsole(
						Messages.OpenModuleHandler_0,
						MessageTypeImpl.SHOW_MODULE_FAILED);
				return null;
			}

			if (editPart instanceof GoalEditPart) {
				String moduleName = ModuleUtil.getModuleName(attrStr);
				String nodeName = ModuleUtil.getNodeName(attrStr);
				if (moduleName != null && moduleName.length() > 0) {
					ModuleUtil.openModuleEditor(moduleName);
					ArgumentEditPart moduleArgumentEditPart = DcaseEditorUtil
							.getCurrentArgumentEditPart();
					GraphicalEditPart nodeEditPart = getNodeEditPart(
							moduleArgumentEditPart, nodeName);
					if (nodeEditPart != null) {
						List<Object> editPartList = DcaseEditorUtil
								.selectSubtree(nodeEditPart, true);
						List<IFigure> figureList = new ArrayList<IFigure>();
						for (Object part : editPartList) {
							if (part instanceof GraphicalEditPart) {
								figureList.add(((GraphicalEditPart) part)
										.getFigure());
							} else if (part instanceof ConnectionNodeEditPart) {
								figureList.add(((ConnectionNodeEditPart) part)
										.getFigure());
							}
						}
						dimension = ((GoalEditPart) editPart).setModuleValue(
								moduleName, figureList);
					}
				} else {
					MessageWriter.writeMessageToConsole(
							Messages.OpenModuleHandler_0,
							MessageTypeImpl.SHOW_MODULE_FAILED);
					return null;
				}
			} else if (editPart instanceof Userdef005EditPart) {
				String moduleName = ModuleUtil.getModuleName(attrStr);
				if (moduleName != null && moduleName.length() > 0) {
					dimension = ((Userdef005EditPart) editPart)
							.setModuleValue(moduleName);
				}
			} else {
				MessageWriter.writeMessageToConsole(
						Messages.OpenModuleHandler_0,
						MessageTypeImpl.SHOW_MODULE_FAILED);
				return null;
			}
		}

		// set size
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

		// restore diagram.
		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (PartInitException e) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_3,
					MessageTypeImpl.SHOW_MODULE_FAILED);
		}

		return null;
	}

	/**
	 * Makes string of nodes.
	 * 
	 * @param editPart
	 *            the goal edit part.
	 * @return the string of nodes.
	 */
	private String makeReferenceString(GraphicalEditPart editPart) {
		EObject eobj = DcaseEditorUtil.getElement(editPart);
		return makeReferenceString(eobj);
	}

	/**
	 * Makes string of nodes.
	 * 
	 * @param eobj
	 *            the node or link.
	 * @return the string of nodes.
	 */
	private String makeReferenceString(EObject eobj) {
		String refStr = null;
		String attachName = null;
		if (eobj instanceof BasicNode) {
			attachName = ((BasicNode) eobj).getAttachment();
		} else if (eobj instanceof BasicLink) {
			attachName = ((BasicLink) eobj).getAttachment();
		}
		if (attachName != null && attachName.length() > 0) {
			String moduleName = ModuleUtil.getModuleName(attachName);
			// open module.
			IEditorPart editorPart = ModuleUtil.openModuleEditor(moduleName);
			if (editorPart != null) {
				ArgumentEditPart moduleArgumentEditPart = DcaseEditorUtil
						.getCurrentArgumentEditPart();
				EObject aobj = DcaseEditorUtil
						.getElement(moduleArgumentEditPart);
				if (aobj instanceof Argument) {
					refStr = getElements((Argument) aobj);
				}
			} else {
				MessageWriter.writeMessageToConsole(Messages.OpenModuleFile_0,
						MessageTypeImpl.SHOW_MODULE_FAILED);
			}
			return attachName;
		}
		return refStr;
	}

	/**
	 * Makes string of nodes.
	 * 
	 * @param argument
	 *            the module argument.
	 * @return the string of nodes.
	 */
	private String getElements(Argument argument) {
		StringBuffer buffer = new StringBuffer();
		String retStr = System.getProperty("line.separator"); //$NON-NLS-1$
		EList<BasicNode> list = argument.getRootBasicNode();
		for (BasicNode node : list) {
			buffer.append(node.getName() + " : " + node.getDesc() + retStr); //$NON-NLS-1$
		}
		return buffer.toString();
	}

	/**
	 * Returns the node edit part. .... copy from
	 * SelectModuleContributionItem.java ....
	 * 
	 * @param editPart
	 *            the Argument edit part.
	 * @param name
	 *            the node name.
	 * @return the node edit part.
	 */
	private GraphicalEditPart getNodeEditPart(ArgumentEditPart editPart,
			String nodeName) {
		for (Object obj : editPart.getChildren()) {
			if (obj instanceof DcaseNodeEditPart) {
				DcaseNodeEditPart nodeEditPart = (DcaseNodeEditPart) obj;
				if (nodeName.equals(ModuleUtil.getAttributeValue(nodeEditPart,
						AttributeType.NAME))) {
					return nodeEditPart;
				}
			}
		}
		return null;
	}

}