/*
 * Copyright (C) 2012,2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.DcaseLink003;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.impl.ArgumentImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A handler to add module (prototype)
 */
public class CreateDstarHandler extends AbstractEditPartHandler {

	/**
	 * the Labels for executing commands.
	 */
	private final String ADD_DIAGRAM_CMD_LABEL = "command for creating diagram"; //$NON-NLS-1$
	private final String CREATE_DSTAR_CMD_LABEL = "command for adding dstar"; //$NON-NLS-1$
	private final String SELECT_OLD_CMD_LABEL = "command for selecting argument"; //$NON-NLS-1$
	private final String DELETE_OLD_CMD_LABEL = "command for deleting argument"; //$NON-NLS-1$
	private final String ADD_DSTAR_CMD_LABEL = "command for adding argument"; //$NON-NLS-1$
	private final String SELECT_DSTAR_CMD_LABEL = "command for selecting argument"; //$NON-NLS-1$
	private final String ARRANGE_DSTAR_CMD_LABEL = "command for arranging argument"; //$NON-NLS-1$
	private final String PERSIST_DSTAR_CMD_LABEL = "command for persisting argument"; //$NON-NLS-1$
	private final String DELETE_SELECTED_CMD_LABEL = "command for deleting selected editParts"; //$NON-NLS-1$

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

		// *** Get d* file names ***
		String moduleName = ModuleUtil.getMainModuleName();
		IFile newDiagramFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(ModuleUtil.getDstarPath(moduleName));
		IFile newModelFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(ModuleUtil.getDstarModelPath(moduleName));
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
		if (!newModelFile
				.equals(DcaseEditorUtil.getModelFile(argumentEditPart))) {
			OperationHistoryFactory.getOperationHistory().execute(createCmd,
					new NullProgressMonitor(), null);
		}

		// *** get new Argument
		final ArgumentEditPart newArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		EObject naobj = DcaseEditorUtil.getElement(newArgumentEditPart);
		if (!(naobj instanceof Argument)) {
			return null;
		}
		Argument newArgument = (Argument) naobj;
		ArgumentImpl addedArgument = (ArgumentImpl) DcaseFactory.eINSTANCE
				.createArgument();
		Diagram newDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain newDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(newDiagram.eResource().getResourceSet());

		// *** Save previous responsibilities and links by hand ***
		HashMap<String, String> oldLinkMap = new HashMap<String, String>();
		HashSet<String> handLinkSet = new HashSet<String>();
		for (BasicLink link : newArgument.getRootBasicLink()) {
			if (link.getSource() == null || link.getTarget() == null) {
				continue;
			}
			String linkName = createLinkName(link.getSource().getName(), link
					.getTarget().getName());
			String attachmentStr = ModuleUtil.removeContractIconString(link
					.getAttachment());
			// save the responsibility
			if (attachmentStr != null && attachmentStr.length() > 0) {
				oldLinkMap.put(linkName, attachmentStr);
			}
			// save the link
			if (link.getUserdef012() == null
					|| link.getUserdef012().length() == 0) {
				handLinkSet.add(linkName);
			}
		}

		// *** add nodes and links
		Map<String, String> moduleList = null;
		HashMap<String, Userdef005> nodeMap = new HashMap<String, Userdef005>();
		HashSet<String> linkSet = new HashSet<String>();
		try {
			moduleList = ModuleUtil.getModulesAndNodes(argumentEditPart, true);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		// create nodes
		for (String name : moduleList.keySet()) {
			if (!ModuleUtil.isPublicNodeName(name)
					&& !oldLinkMap.containsValue(name)) {
				Userdef005 newNode = DcaseFactory.eINSTANCE.createUserdef005();
				IFile moduleModelFile = ResourcesPlugin.getWorkspace()
						.getRoot().getFile(ModuleUtil.getModelPath(name));
				EObject aobj = ModelUtil.getModel(moduleModelFile);
				if (aobj instanceof Argument) {
					newNode.setUserdef012(((Argument) aobj).getUserdef012());
					newNode.setDesc(ModuleUtil
							.getResponsibilityName((Argument) aobj));
				}
				nodeMap.put(name, newNode);
				newNode.setName(name);
				newNode.setAttachment(name);
				addedArgument.getRootBasicNode().add(newNode);
			}
		}
		// create links by hand
		String separator = ModuleUtil.getReferenceSeparator();
		for (String linkName : handLinkSet) {
			String srcArray[] = linkName.split(separator);
			DcaseLink003 newLink = createLink(linkName,
					nodeMap.get(srcArray[0]), nodeMap.get(srcArray[1]),
					oldLinkMap);
			addedArgument.getRootBasicLink().add(newLink);
		}
		// create links automatically
		for (String name : moduleList.keySet()) {
			String tgtName = ModuleUtil.getModuleName(name);
			String refName = moduleList.get(name);
			if (refName == null || refName.length() == 0) {
				continue;
			}
			String srcArray[] = refName.split(separator);
			for (String srcKey : srcArray) {
				String srcName = ModuleUtil.getModuleName(srcKey);
				String newLinkName = createLinkName(srcName, tgtName);
				if (linkSet.contains(newLinkName)) {
					continue;
				}
				linkSet.add(newLinkName);
				if (!nodeMap.containsKey(srcName)) {
					continue;
				}
				if (!nodeMap.containsKey(tgtName)) {
					continue;
				}
				if (handLinkSet.contains(newLinkName)) {
					continue;
				}
				DcaseLink003 newLink = createLink(newLinkName,
						nodeMap.get(srcName), nodeMap.get(tgtName), oldLinkMap);
				newLink.setUserdef012("auto"); //$NON-NLS-1$
				addedArgument.getRootBasicLink().add(newLink);
			}
		}
		Set<String> idSet = new HashSet<String>();
		CompoundCommand addCmd = new CompoundCommand(CREATE_DSTAR_CMD_LABEL);
		// select old nodes
		ICommand selectOldCommand = new SelectExcludesCommand(
				SELECT_OLD_CMD_LABEL, newArgumentEditPart, idSet);
		addCmd.add(new ICommandProxy(selectOldCommand));
		// delete old nodes
		AbstractTransactionalCommand deleteOldCommand = new AbstractTransactionalCommand(
				newDomain, DELETE_OLD_CMD_LABEL, Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				List<DcaseNodeEditPart> selectedList = DcaseEditorUtil
						.getSelectedPart();
				if (selectedList.size() == 0) {
					return CommandResult.newOKCommandResult();
				}
				CompoundCommand deleteCmd = new CompoundCommand(
						DELETE_SELECTED_CMD_LABEL);
				for (DcaseNodeEditPart editPart : selectedList) {
					GroupRequest deleteReq = new GroupRequest(
							RequestConstants.REQ_DELETE);
					deleteReq.setEditParts(editPart);
					deleteCmd.add(editPart.getCommand(deleteReq));
				}
				newArgumentEditPart.getDiagramEditDomain()
						.getDiagramCommandStack().execute(deleteCmd);
				return CommandResult.newOKCommandResult();
			}
		};
		addCmd.add(new ICommandProxy(deleteOldCommand));
		ICommand additionCommand = new ModelAdditionCommand(newDomain,
				ADD_DSTAR_CMD_LABEL, null, addedArgument, newArgument, false);
		addCmd.add(new ICommandProxy(additionCommand));
		ICommand selectCommand = new SelectExcludesCommand(
				SELECT_DSTAR_CMD_LABEL, newArgumentEditPart, idSet);
		addCmd.add(new ICommandProxy(selectCommand));
		ICommand arrangeCommand = new ArrangeExcludesCommand(newDomain,
				ARRANGE_DSTAR_CMD_LABEL, null, newArgumentEditPart, idSet);
		addCmd.add(new ICommandProxy(arrangeCommand));
		ICommand persistanceCommand = new NotationPersistanceCommand(newDomain,
				PERSIST_DSTAR_CMD_LABEL, null, newArgumentEditPart);
		addCmd.add(new ICommandProxy(persistanceCommand));
		// execute
		newArgumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(addCmd);

		// *** save diagram
		ModuleUtil.saveModuleEditor(moduleName, false);

		return null;
	}

	private DcaseLink003 createLink(String newLinkName, Userdef005 srcNode,
			Userdef005 tgtNode, HashMap<String, String> oldLinkMap) {
		DcaseLink003 newLink = DcaseFactory.eINSTANCE.createDcaseLink003();
		newLink.setName(newLinkName);
		newLink.setSource(srcNode);
		newLink.setTarget(tgtNode);
		String addedStr = ModuleUtil.getContractIconString(srcNode, tgtNode);
		// restore responsibility
		if (oldLinkMap.containsKey(newLinkName)) {
			newLink.setAttachment(oldLinkMap.get(newLinkName) + addedStr);
		} else {
			newLink.setAttachment(addedStr);
		}
		return newLink;
	}

	private String createLinkName(String src, String tgt) {
		if (src == null || tgt == null || src.length() == 0
				|| tgt.length() == 0) {
			return ""; //$NON-NLS-1$
		}
		return src + ModuleUtil.getReferenceSeparator() + tgt;
	}
}
