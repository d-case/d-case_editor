/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.impl.ArgumentImpl;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

/**
 * A handler to remove module (prototype)
 */
public class RestoreModuleHandler extends AbstractEditPartHandler {

	/**
	 * the Labels for executing commands.
	 */
	private final String RESTORE_MODULE_CMD_LABEL = "command for restoring module"; //$NON-NLS-1$
	private final String RESTORE_SUBTREE_CMD_LABEL = "command for restoring sub-tree"; //$NON-NLS-1$
	private final String SELECT_SUBTREE_CMD_LABEL = "command for selecting sub-tree"; //$NON-NLS-1$
	private final String ARRANGE_SUBTREE_CMD_LABEL = "command for arranging sub-tree"; //$NON-NLS-1$
	private final String PERSIST_SUBTREE_CMD_LABEL = "command for persisting sub-tree"; //$NON-NLS-1$
	private final String MOVE_MODULE_CMD_LABEL = "command for moving module node"; //$NON-NLS-1$
	private final String POSITION_MODULE_CMD_LABEL = "command for adjusting module node"; //$NON-NLS-1$
	private final String SET_ATTRIBUTE_CMD_LABEL = "command for setting attribute"; //$NON-NLS-1$

	/**
	 * mapping for position of nodes. module : moduleEditPart -- moduleBasicNode
	 * current : currentEditPart -- currentBasicNode
	 * moduleMap<moduleBasicNode->currentBasicNode, moduleEditPart>
	 */
	Map<DcaseNodeEditPart, BasicNode> moduleMap;

	/**
	 * Removes selected Module node and restores the Module to current diagram.
	 * 
	 * @param event
	 *            ExecutionEvent
	 * @return the result of the execution.
	 * @throws ExecutionException
	 *             if an exception occurred during execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ArgumentEditPart argumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Argument currentArgument = null;
		EObject aobj = DcaseEditorUtil.getElement(argumentEditPart);
		if (aobj instanceof Argument) {
			currentArgument = (Argument) aobj;
		} else {
			MessageWriter.writeMessageToConsole(
					Messages.RestoreModuleHandler_0,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			return null;
		}
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(currentDiagram.eResource().getResourceSet());

		// *** Detect selected Module node
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		ShapeNodeEditPart selectedEditPart = null;
		Point selectedPoint = null;
		if (selection instanceof IStructuredSelection) {
			Object firstObject = ((IStructuredSelection) selection)
					.getFirstElement();
			if (firstObject instanceof ShapeNodeEditPart) {
				selectedEditPart = (ShapeNodeEditPart) firstObject;
				selectedPoint = selectedEditPart.getLocation();
			}
		}
		if (selectedEditPart == null || selectedPoint == null) {
			MessageWriter.writeMessageToConsole(
					Messages.RestoreModuleHandler_1,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			return null;
		}
		EObject sobj = DcaseEditorUtil.getElement(selectedEditPart);
		BasicNode selectedElement = null;
		if (sobj instanceof Userdef005 || sobj instanceof Userdef001) {
			selectedElement = (BasicNode) sobj;
		} else {
			MessageWriter.writeMessageToConsole(
					Messages.RestoreModuleHandler_2,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			return null;
		}
		NodeInfo nodeInfo = ModelUtil.createNodeInfo(selectedElement);
		String moduleName = (String) nodeInfo
				.getAttribute(AttributeType.ATTACHMENT);
		if (moduleName == null || moduleName.length() == 0) {
			MessageWriter.writeMessageToConsole(
					Messages.RestoreModuleHandler_3,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			return null;
		}

		// *** Get Module ***
		// open and select module diagram
		Resource diagramResource = currentDiagram.eResource();
		ModuleUtil.openModuleEditor(moduleName);
		Diagram moduleDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain moduleDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(moduleDiagram.eResource().getResourceSet());
		ArgumentEditPart moduleArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		EObject moduleModel = DcaseEditorUtil
				.getElement(moduleArgumentEditPart);
		Argument moduleArgument = null;
		if (moduleModel instanceof Argument) {
			moduleArgument = (Argument) moduleModel;
		} else {
			MessageWriter.writeMessageToConsole(
					Messages.RestoreModuleHandler_4,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			return null;
		}
		EList<BasicLink> linkList = moduleArgument.getRootBasicLink();
		DcaseNodeEditPart topModuleEditPart = makeMap(moduleArgumentEditPart);
		openDiagram(diagramResource);

		// *** Delete Module node and Add sub-tree to current diagram ***
		ArgumentImpl addedArgument = (ArgumentImpl) DcaseFactory.eINSTANCE
				.createArgument();
		// add links to argument
		for (BasicLink link : linkList) {
			EObject cpLink = EcoreUtil.copy(link);
			addedArgument.getRootBasicLink().add((BasicLink) cpLink);
		}
		// add nodes to argument
		BasicNode topNode = null;
		for (Map.Entry<DcaseNodeEditPart, BasicNode> mapEntry : moduleMap
				.entrySet()) {
			DcaseNodeEditPart oldEditPart = mapEntry.getKey();
			BasicNode oldNode = mapEntry.getValue();
			BasicNode newNode = EcoreUtil.copy(oldNode);
			addedArgument.getRootBasicNode().add(newNode);
			moduleMap.put(oldEditPart, newNode);
			for (BasicLink addLink : addedArgument.getRootBasicLink()) {
				if (addLink.getSource() == oldNode) {
					addLink.setSource(newNode);
				}
				if (addLink.getTarget() == oldNode) {
					addLink.setTarget(newNode);
				}
			}

			if (oldEditPart == topModuleEditPart) {
				topNode = newNode;
			}
		}

		// add links of top node
		if (topNode != null) {
			for (Object link : selectedEditPart.getTargetConnections()) {
				EObject pLink = DcaseEditorUtil
						.getElement((ConnectionNodeEditPart) link);
				if (pLink instanceof BasicLink) {
					BasicLink cpLink = EcoreUtil.copy((BasicLink) pLink);
					cpLink.setTarget(topNode);
					addedArgument.getRootBasicLink().add(cpLink);
				}
			}
		}

		// execute
		CompoundCommand addCmd = new CompoundCommand(RESTORE_MODULE_CMD_LABEL);
		GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
		deleteReq.setEditParts(selectedEditPart);
		addCmd.add(selectedEditPart.getCommand(deleteReq));
		ICommand additionCommand = new ModelAdditionCommand(currentDomain,
				RESTORE_SUBTREE_CMD_LABEL, null, addedArgument,
				currentArgument, false);
		addCmd.add(new ICommandProxy(additionCommand));
		Set<String> idSet = DcaseEditorUtil.getChildUUIDs(argumentEditPart);
		// select, arrange and persistence command
		ICommand selectCommand = new SelectExcludesCommand(
				SELECT_SUBTREE_CMD_LABEL, argumentEditPart, idSet);
		addCmd.add(new ICommandProxy(selectCommand));
		ICommand arrangeCommand = new ArrangeExcludesCommand(currentDomain,
				ARRANGE_SUBTREE_CMD_LABEL, null, argumentEditPart, idSet);
		addCmd.add(new ICommandProxy(arrangeCommand));
		argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(addCmd);

		// *** Move
		CompoundCommand moveCmd = new CompoundCommand(MOVE_MODULE_CMD_LABEL);
		DcaseNodeEditPart topEditPart = DcaseEditorUtil
				.getTopCurrentSelectedPart();
		if (topEditPart == null) {
			return null;
		}
		// move
		Dimension diffDimension = selectedPoint.getDifference(topModuleEditPart
				.getLocation());
		int i = 0;
		for (Map.Entry<DcaseNodeEditPart, BasicNode> mapEntry2 : moduleMap
				.entrySet()) {
			DcaseNodeEditPart oldEditPart2 = mapEntry2.getKey();
			DcaseNodeEditPart newEditPart2 = null;
			BasicNode newNode2 = mapEntry2.getValue();
			// search edit part
			for (DcaseNodeEditPart canEditPart : DcaseEditorUtil
					.getSelectedPart()) {
				EObject eobj2 = DcaseEditorUtil.getElement(canEditPart);
				if (eobj2 instanceof BasicNode
						&& ((BasicNode) eobj2) == newNode2) {
					newEditPart2 = canEditPart;
					break;
				}
			}
			if (newEditPart2 != null) {
				Point eachPoint = oldEditPart2.getLocation().translate(
						diffDimension);
				ICommand positionCommand = new SetBoundsCommand(currentDomain,
						POSITION_MODULE_CMD_LABEL + i, new EObjectAdapter(
								(EObject) newEditPart2.getModel()), eachPoint);
				moveCmd.add(new ICommandProxy(positionCommand));
				i++;
			}
		}
		ICommand persistanceCommand = new NotationPersistanceCommand(
				currentDomain, PERSIST_SUBTREE_CMD_LABEL, null,
				argumentEditPart);
		moveCmd.add(new ICommandProxy(persistanceCommand));
		argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(moveCmd);

		// update RefSource of Module
		IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
		String refStr = ModuleUtil.createNodeReference(modelFile, moduleName);
		String newAttrStr = ModuleUtil.removeModuleReference(
				moduleArgumentEditPart, refStr);
		Map<AttributeType, Object> attrNewMap = new HashMap<AttributeType, Object>();
		attrNewMap.put(AttributeType.REFSOURCE, newAttrStr);
		ICommand setUserdef011Command = new ChangeBasicNodePropertyTransactionCommand(
				moduleDomain, SET_ATTRIBUTE_CMD_LABEL, null, moduleArgument,
				attrNewMap);
		moduleArgumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(new ICommandProxy(setUserdef011Command));
		ModuleUtil.saveModuleEditor(moduleName);
		openDiagram(diagramResource);

		return null;
	}

	/**
	 * Makes mapping of module edit parts and basic nodes
	 * 
	 * @param moduleName
	 *            the module name
	 * @return the top edit part
	 */
	private DcaseNodeEditPart makeMap(ArgumentEditPart argumentEditPart) {
		DcaseNodeEditPart topEditPart = null;

		// select all nodes
		ICommand selectCommand = new SelectExcludesCommand(
				SELECT_SUBTREE_CMD_LABEL, argumentEditPart,
				new HashSet<String>());
		argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(new ICommandProxy(selectCommand));
		topEditPart = DcaseEditorUtil.getTopCurrentSelectedPart();

		// make mappings
		moduleMap = new HashMap<DcaseNodeEditPart, BasicNode>();
		for (DcaseNodeEditPart editPart : DcaseEditorUtil.getSelectedPart()) {
			EObject eobj = DcaseEditorUtil.getElement(editPart);
			if (eobj instanceof BasicNode) {
				moduleMap.put(editPart, (BasicNode) eobj);
			}
		}

		return topEditPart;
	}

	/**
	 * Opens diagram
	 * 
	 * @param diagramResource
	 *            the diagram resource
	 */
	private void openDiagram(Resource diagramResource) {
		// open current diagram
		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (Exception e) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_3,
					MessageTypeImpl.RESTORE_MODULE_FAILED);
			throw new DcaseRuntimeException(Messages.CreateModuleHandler_3,
					null, null, 0, MessageTypeImpl.RESTORE_MODULE_FAILED);
		}
	}

}