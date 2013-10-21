/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.impl.ArgumentImpl;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcaseFactory;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.ui.NewModuleInputDialog;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A handler to add module (prototype)
 */
public class CreateModuleHandler extends AbstractEditPartHandler {

	/**
	 * the Labels for executing commands.
	 */
	private final String ADD_DIAGRAM_CMD_LABEL = "command for creating diagram"; //$NON-NLS-1$
	private final String ADD_MODULE_CMD_LABEL = "command for adding module"; //$NON-NLS-1$
	private final String ADD_SUBTREE_CMD_LABEL = "command for adding sub-tree"; //$NON-NLS-1$
	private final String SET_USERDEF011_CMD_LABEL = "command for setting userdef011"; //$NON-NLS-1$
	private final String SELECT_SUBTREE_CMD_LABEL = "command for selecting sub-tree"; //$NON-NLS-1$
	private final String ARRANGE_SUBTREE_CMD_LABEL = "command for arranging sub-tree"; //$NON-NLS-1$
	private final String PERSIST_SUBTREE_CMD_LABEL = "command for persisting sub-tree"; //$NON-NLS-1$
	private final String REPLACE_MODULE_CMD_LABEL = "command for replacing sub-tree to node"; //$NON-NLS-1$
	private final String ADD_MODULE_NODE_CMD_LABEL = "command for adding node"; //$NON-NLS-1$
	private final String SET_ATTACHMENT_CMD_LABEL = "command for setting attachment"; //$NON-NLS-1$

	/**
	 * the List of the checked nodes.
	 */
	private Set<String> checkedNodeList;

	/**
	 * the Set of the EditParts to modularization.
	 */
	private Set<GraphicalEditPart> nodeEditPartSet;
	private Set<ConnectionNodeEditPart> linkEditPartSet;

	/**
	 * The constructor.
	 */
	public void initDatas() {
		// initialize
		checkedNodeList = new HashSet<String>(COLLECTION_INITIAL_CAPACITY);
		nodeEditPartSet = new HashSet<GraphicalEditPart>(
				COLLECTION_INITIAL_CAPACITY);
		linkEditPartSet = new HashSet<ConnectionNodeEditPart>(
				COLLECTION_INITIAL_CAPACITY);
	}

	/**
	 * Returns the selected node editPart set.
	 * 
	 * @return the selected node editPart set.
	 */
	public Set<GraphicalEditPart> getSelectedNodeEditParts() {
		return nodeEditPartSet;
	}

	/**
	 * Returns the selected link editPart set.
	 * 
	 * @return the selected link editPart set.
	 */
	public Set<ConnectionNodeEditPart> getSelectedLinkEditParts() {
		return linkEditPartSet;
	}

	/**
	 * Creates Module file and adds Module node to current diagram.
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
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(currentDiagram.eResource().getResourceSet());
		String myName = ModuleUtil.getModuleName(DcaseEditorUtil
				.getCurrentDiagramFile());

		// *** Select sub-tree ***
		// detect selected node
		DcaseNodeEditPart selectedEditPart = (DcaseNodeEditPart) getSelectedElement(event);
		Point selectedPoint = selectedEditPart.getLocation();
		if (selectedEditPart == null || selectedPoint == null) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_1,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			return null;
		}
		// create sub-tree
		initDatas();
		if (!selectSubtree(selectedEditPart, selectedEditPart)) {
			return null;
		}

		// *** Input Module name ***
		IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
		IFile newDiagramFile = null;
		IFile newModelFile = null;
		String moduleName = null;
		do {
			NewModuleInputDialog dialog = new NewModuleInputDialog(
					DcaseEditorUtil.getActiveWindowShell(),
					(moduleName == null) ? net.dependableos.dcase.diagram.editor.message.Messages.CreateModuleDialog_1
							: net.dependableos.dcase.diagram.editor.message.Messages.CreateModuleDialog_2);
			if (dialog.open() != Dialog.OK) {
				return null;
			}
			moduleName = dialog.getInputedFilename();
			newDiagramFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(ModuleUtil.getDiagramPath(moduleName));
			newModelFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(ModuleUtil.getModelPath(moduleName));
		} while (newDiagramFile.exists() || newModelFile.exists());
		final IPath newDiagramPath = newDiagramFile.getFullPath();
		final IPath newModelPath = newModelFile.getFullPath();

		// *** Create new Module diagram ***
		// create diagram and model file
		AbstractTransactionalCommand createCmd = new AbstractTransactionalCommand(
				currentDomain, ADD_DIAGRAM_CMD_LABEL, Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				URI diagramURI = URI.createPlatformResourceURI(
						newDiagramPath.toOSString(), false);
				URI modelURI = URI.createPlatformResourceURI(
						newModelPath.toOSString(), false);
				Resource diagram = DcaseDiagramEditorUtil.createDiagram(
						diagramURI, modelURI, monitor);
				try {
					DcaseDiagramEditorUtil.openDiagram(diagram);
				} catch (Exception e) {
					MessageWriter.writeMessageToConsole(
							Messages.CreateModuleHandler_2,
							MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
					throw new DcaseRuntimeException(
							Messages.CreateModuleHandler_2, null, null, 0,
							MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
				}
				return CommandResult.newOKCommandResult();
			}
		};
		OperationHistoryFactory.getOperationHistory().execute(createCmd,
				new NullProgressMonitor(), null);

		// *** Add sub-tree to Module diagram ***
		ArgumentEditPart newArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Diagram newDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain newDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(newDiagram.eResource().getResourceSet());
		Argument newArgument = null;
		EObject naobj = DcaseEditorUtil.getElement(newArgumentEditPart);
		if (naobj instanceof Argument) {
			newArgument = (Argument) naobj;
		} else {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_0,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			return null;
		}
		ArgumentImpl addedArgument = (ArgumentImpl) DcaseFactory.eINSTANCE
				.createArgument();
		// add links to argument
		for (Iterator<ConnectionNodeEditPart> it = linkEditPartSet.iterator(); it
				.hasNext();) {
			ConnectionNodeEditPart link = it.next();
			Object modelObj = DcaseEditorUtil.getElement(link);
			if (modelObj instanceof BasicLink) {
				EObject cpLink = EcoreUtil.copy((EObject) modelObj);
				addedArgument.getRootBasicLink().add((BasicLink) cpLink);
			}
		}
		// add nodes to argument
		for (Iterator<GraphicalEditPart> it = nodeEditPartSet.iterator(); it
				.hasNext();) {
			GraphicalEditPart node = it.next();
			Object modelObj = DcaseEditorUtil.getElement(node);
			// rewrite link source and target
			if (modelObj instanceof BasicNode) {
				EObject cpModel = EcoreUtil.copy((EObject) modelObj);
				((BasicNode) cpModel).setName(((BasicNode) modelObj).getName());
				addedArgument.getRootBasicNode().add((BasicNode) cpModel);
				for (BasicLink addLink : addedArgument.getRootBasicLink()) {
					if (addLink.getSource() == (BasicNode) modelObj) {
						addLink.setSource((BasicNode) cpModel);
					}
					if (addLink.getTarget() == (BasicNode) modelObj) {
						addLink.setTarget((BasicNode) cpModel);
					}
				}
				boolean isRef = false;
				if (node != selectedEditPart) {
					for (Object lobj : node.getTargetConnections()) {
						if (!linkEditPartSet.contains(lobj)) {
							isRef = true;
							break;
						}
					}
				}
				if (isRef) {
					// set public flag.
					String flag = ((BasicNode) cpModel).getUserdef015();
					String publicFlag = ModuleUtil.getPublicFlagString();
					if (flag == null || flag.length() == 0) {
						flag = publicFlag;
					} else if (flag.indexOf(publicFlag) < 0) {
						flag += publicFlag;
					}
					// append reference.
					((BasicNode) cpModel).setUserdef015(flag);
					String refStr = ModuleUtil.createNodeReference(modelFile,
							((BasicNode) cpModel).getName());
					refStr = ModuleUtil.appendModuleReference(node, refStr);
					((BasicNode) cpModel).setUserdef011(refStr);
				}
			}
		}
		CompoundCommand addCmd = new CompoundCommand(ADD_MODULE_CMD_LABEL);
		ICommand additionCommand = new ModelAdditionCommand(newDomain,
				ADD_SUBTREE_CMD_LABEL, null, addedArgument, newArgument, false);
		addCmd.add(new ICommandProxy(additionCommand));
		Set<String> idSet = new HashSet<String>();
		// set UserDef011 command
		Map<AttributeType, Object> attrNewMap = new HashMap<AttributeType, Object>();
		attrNewMap.put(AttributeType.USERDEF011,
				ModuleUtil.createNodeReference(modelFile, moduleName));
		// set Name
		attrNewMap.put(AttributeType.NAME, moduleName);
		// set Userdef012
		Argument curArgument = null;
		naobj = DcaseEditorUtil.getElement(argumentEditPart);
		if (naobj instanceof Argument) {
			curArgument = (Argument) naobj;
		} else {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_0,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			return null;
		}
		attrNewMap.put(AttributeType.USERDEF012, curArgument.getUserdef012());
		// set Userdef013
		attrNewMap.put(AttributeType.USERDEF013, myName);
		ICommand setUserdef011Command = new ChangeBasicNodePropertyTransactionCommand(
				newDomain, SET_USERDEF011_CMD_LABEL, null, newArgument,
				attrNewMap);
		addCmd.add(new ICommandProxy(setUserdef011Command));
		// select, arrange and persistence command
		ICommand selectCommand = new SelectExcludesCommand(
				SELECT_SUBTREE_CMD_LABEL, newArgumentEditPart, idSet);
		addCmd.add(new ICommandProxy(selectCommand));
		ICommand arrangeCommand = new ArrangeExcludesCommand(newDomain,
				ARRANGE_SUBTREE_CMD_LABEL, null, newArgumentEditPart, idSet);
		addCmd.add(new ICommandProxy(arrangeCommand));
		ICommand persistanceCommand = new NotationPersistanceCommand(newDomain,
				PERSIST_SUBTREE_CMD_LABEL, null, newArgumentEditPart);
		addCmd.add(new ICommandProxy(persistanceCommand));
		// execute
		newArgumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(addCmd);

		// *** Delete sub-tree and Add Module node to current diagram ***
		CompoundCommand moduleCmd = new CompoundCommand(
				REPLACE_MODULE_CMD_LABEL);
		// delete command
		nodeEditPartSet.remove(selectedEditPart);
		int awayNr = 0;
		for (Iterator<GraphicalEditPart> it = nodeEditPartSet.iterator(); it
				.hasNext();) {
			GraphicalEditPart node = it.next();
			boolean isAway = false;
			for (Object lobj : node.getTargetConnections()) {
				if (!linkEditPartSet.contains(lobj)) {
					isAway = true;
					break;
				}
			}
			if (isAway) {
				// convert to Away Goal
				Object nodeObj = DcaseEditorUtil.getElement(node);
				Map<AttributeType, Object> lattrMap = new HashMap<AttributeType, Object>();
				String nodeName = ((BasicNode) nodeObj).getName();
				lattrMap.put(AttributeType.NAME, nodeName);
				lattrMap.put(AttributeType.ATTACHMENT,
						ModuleUtil.createNodeReference(moduleName, nodeName));
				ICommand lAttachmentCommand = new ChangeBasicNodePropertyTransactionCommand(
						currentDomain, SET_ATTACHMENT_CMD_LABEL + awayNr, null,
						(BasicNode) nodeObj, lattrMap);
				moduleCmd.add(new ICommandProxy(lAttachmentCommand));
				ConvertNodeTypeUtil lutil = new ConvertNodeTypeUtil(
						currentDomain, ADD_MODULE_NODE_CMD_LABEL + awayNr,
						currentDiagram, argumentEditPart,
						(ShapeNodeEditPart) node, NodeType.GOAL,
						(BasicNode) nodeObj, ((BasicNode) nodeObj).getName());
				moduleCmd.add(lutil.createCommand());
				awayNr++;
			} else {
				// remove Node
				GroupRequest deleteReq = new GroupRequest(
						RequestConstants.REQ_DELETE);
				deleteReq.setEditParts(node);
				moduleCmd.add(node.getCommand(deleteReq));
			}
		}
		// set Attachment command
		BasicNode selectedNode = (BasicNode) DcaseEditorUtil
				.getElement(selectedEditPart);
		Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
		attrMap.put(AttributeType.ATTACHMENT, moduleName);
		ICommand setAttachmentCommand = new ChangeBasicNodePropertyTransactionCommand(
				currentDomain, SET_ATTACHMENT_CMD_LABEL, null, selectedNode,
				attrMap);
		moduleCmd.add(new ICommandProxy(setAttachmentCommand));
		// convert the root node of sub-tree command
		ConvertNodeTypeUtil util = new ConvertNodeTypeUtil(currentDomain,
				ADD_MODULE_NODE_CMD_LABEL, currentDiagram, argumentEditPart,
				selectedEditPart, NodeType.USERDEF005, selectedNode, moduleName);
		moduleCmd.add(util.createCommand());
		// execute
		argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(moduleCmd);

		// *** save module file
		ModuleUtil.saveModuleEditor(moduleName);

		// *** change diagram
		Resource diagramResource = currentDiagram.eResource();
		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (Exception e) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_3,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			throw new DcaseRuntimeException(Messages.CreateModuleHandler_3,
					null, null, 0, MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
		}

		return null;
	}

	public boolean selectSubtree(GraphicalEditPart editPart,
			GraphicalEditPart rootEditPart) {
		String uuid = DcaseEditorUtil.getUUIDs(editPart);
		// do not check junction link
		if (!checkedNodeList.contains(uuid)) {
			checkedNodeList.add(uuid);
		} else if (editPart != rootEditPart) {
			return true;
		} else {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_4,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			return false;
		}
		for (Object link : editPart.getSourceConnections()) {
			if (link instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart dLink = (ConnectionNodeEditPart) link;
				if (!linkEditPartSet.contains(dLink)) {
					linkEditPartSet.add(dLink);
				} else {
					MessageWriter.writeMessageToConsole(
							Messages.CreateModuleHandler_5,
							MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
					return false;
				}
				if (!selectSubtree((ShapeNodeEditPart) dLink.getTarget(),
						rootEditPart)) {
					return false;
				}

			} else {
				MessageWriter.writeMessageToConsole(
						Messages.CreateModuleHandler_6,
						MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
				return false;
			}
		}
		if (!nodeEditPartSet.contains(editPart)) {
			nodeEditPartSet.add(editPart);
		} else {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_7,
					MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
			return false;
		}

		return true;
	}

}
