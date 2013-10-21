/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A handler class to handle the parameter dialog on the attribute dialog.
 */
public class AdjustReferencesHandler extends AbstractEditPartHandler {

	private final static String SET_ATTR_CMD_LABEL = "command for setting attributes"; //$NON-NLS-1$

	/**
	 * Adjusts the refecences between Attachment and Userdef011.
	 * 
	 * @param event
	 *            An event
	 * @return the result of the execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Argument currentArgument = (Argument) DcaseEditorUtil
				.getElement(currentArgumentEditPart);
		URI uri = currentArgument.eResource().getURI();
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(uri.toPlatformString(false)));

		Map<String, String> moduleMap = null;
		HashSet<String> savingModuleSet = new HashSet<String>();
		IResource[] resources = null;
		try {
			// get current map
			moduleMap = ModuleUtil.getModulesAndNodes(file, true);
			resources = file.getParent().members();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		// check links for all Arguments and Goals
		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				IFile resFile = (IFile) resource;
				if (!ModuleUtil.isModelFile(resFile)) {
					continue;
				}
				EObject eobj = ModelUtil.getModel(resFile);
				if (eobj instanceof Argument) {
					Argument argument = (Argument) eobj;
					String moduleStr = ModuleUtil.getModuleName(resFile);

					// check Goal and Module Nodes
					for (BasicNode node : argument.getRootBasicNode()) {
						if (!(node instanceof Goal)
								&& !(node instanceof Userdef005)) {
							continue;
						}
						String refStr = ModuleUtil.createNodeReference(
								moduleStr, node.getName());
						String attachmentStr = node.getAttachment();
						if (attachmentStr != null
								&& attachmentStr.length() > 0
								&& !ModuleUtil
										.isWorkspaceReference(attachmentStr)
								&& !ModuleUtil.isUrl(attachmentStr)) {
							if (moduleMap.containsKey(attachmentStr)) {
								// Attachment exists.
								String userdef011Str = moduleMap
										.get(attachmentStr);
								String newStr = ""; //$NON-NLS-1$
								if (userdef011Str == null) {
									userdef011Str = ""; //$NON-NLS-1$
								}
								if (userdef011Str.length() > 0) {
									newStr = ModuleUtil.removeModuleReference(
											userdef011Str, refStr);
								}
								if (userdef011Str.equals(newStr)) {
									addUserdef011(attachmentStr, refStr);
									savingModuleSet.add(ModuleUtil
											.getModuleName(attachmentStr));
								} else {
									moduleMap.put(attachmentStr, newStr);
								}
								// Sync Responsibility
								String respRefStr = getUserdef012(attachmentStr);
								String respMyStr = (node.getUserdef012() != null) ? node
										.getUserdef012() : ""; //$NON-NLS-1$
								if (!respMyStr.equals(respRefStr)) {
									setUserdef012(refStr, respRefStr);
									savingModuleSet.add(moduleStr);
								}
							} else {
								// Attachment does not exist.
								setAttachment(refStr, ""); //$NON-NLS-1$
								savingModuleSet.add(moduleStr);
							}
						}
					}
				}
			}
		}

		// remove unreferenced...
		for (Map.Entry<String, String> entry : moduleMap.entrySet()) {
			String userdef011Str = entry.getValue();
			if (userdef011Str != null && userdef011Str.length() > 0) {
				String nodeName = entry.getKey();
				removeUserdef011(nodeName, userdef011Str);
				savingModuleSet.add(ModuleUtil.getModuleName(nodeName));
			}
		}

		// save module files
		for (Object mobj : savingModuleSet.toArray()) {
			if (mobj instanceof String) {
				saveModuleFile((String) mobj);
			}
		}

		// switch to current diagram
		Resource diagramResource = currentDiagram.eResource();
		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Sets the attachment attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the attachment value.
	 */
	private void setAttachment(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.ATTACHMENT, str);
	}

	/**
	 * Sets the userdef012 attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the userdef012 value.
	 */
	private void setUserdef012(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.USERDEF012, str);
	}

	/**
	 * Appends to the userdef011 attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param refStr
	 *            the value to append.
	 */
	private void addUserdef011(String nodeName, String refStr) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return;
		}
		String userdef011Str = node.getUserdef011();
		for (String ref : refStr.split(ModuleUtil.getReferenceSeparator())) {
			userdef011Str = ModuleUtil
					.appendModuleReference(userdef011Str, ref);
		}
		setAttribute(node, AttributeType.USERDEF011, userdef011Str);
	}

	/**
	 * Removes from the userdef011 attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param refStr
	 *            the value to remove.
	 */
	private void removeUserdef011(String nodeName, String refStr) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return;
		}
		String userdef011Str = node.getUserdef011();
		for (String ref : refStr.split(ModuleUtil.getReferenceSeparator())) {
			userdef011Str = ModuleUtil
					.removeModuleReference(userdef011Str, ref);
		}
		setAttribute(node, AttributeType.USERDEF011, userdef011Str);
	}

	/**
	 * Returns the BasicNode and open Editor.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the node.
	 */
	private BasicNode nodeNameToNode(String nodeName) {
		String moduleName = ModuleUtil.getModuleName(nodeName);
		ModuleUtil.openModuleEditor(moduleName);
		ArgumentEditPart argumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Object eobj = DcaseEditorUtil.getElement(argumentEditPart);

		if (eobj != null && eobj instanceof Argument) {
			String nodePartName = ModuleUtil.getNodeName(nodeName);
			if (nodePartName.equals(nodeName)) {
				return (BasicNode) eobj;
			}
			for (BasicNode node : ((Argument) eobj).getRootBasicNode()) {
				if (nodePartName.equals(node.getName())) {
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * Saves the module file.
	 * 
	 * @param moduleName
	 *            the module name.
	 */
	private void saveModuleFile(String moduleName) {
		IEditorPart editorPart = null;
		IPath diagramPath = ModuleUtil.getDiagramPath(moduleName);
		if (diagramPath == null) {
			return;
		}
		IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath
				.toOSString());
		if (diagramFile == null) {
			return;
		}
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			editorPart = workbenchPage.findEditor(new FileEditorInput(
					diagramFile));
			if (editorPart == null) {
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry()
						.getDefaultEditor(diagramFile.getName());
				if (desc != null) {
					editorPart = workbenchPage.openEditor(new FileEditorInput(
							diagramFile), desc.getId());
				}
			}
		} catch (Exception e) {
			MessageWriter.writeMessageToConsole(Messages.OpenModuleFile_0,
					MessageTypeImpl.OPEN_MODULE_FILE_FAILED);
		}

		if (editorPart != null) {
			workbenchPage.saveEditor(editorPart, false);
		}
	}

	/**
	 * Returns the userdef012 attribute value.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the userdef012 attribute value.
	 */
	private String getUserdef012(String nodeName) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return null;
		}
		return node.getUserdef012();
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param type
	 *            the attribute type.
	 * @param value
	 *            the attribute value.
	 */
	private void setAttribute(String nodeName, AttributeType type, String value) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return;
		}
		setAttribute(node, type, value);
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param node
	 *            the node.
	 * @param type
	 *            the attribute type.
	 * @param value
	 *            the attribute value.
	 */
	private void setAttribute(BasicNode node, AttributeType type, String value) {
		ArgumentEditPart argumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
		attrMap.put(type, value);
		TransactionalEditingDomain currentDomain = DcaseEditorUtil
				.getCurrentArgumentEditPart().getEditingDomain();
		ICommand changeCommand = new ChangeBasicNodePropertyTransactionCommand(
				currentDomain, SET_ATTR_CMD_LABEL, null, node, attrMap);
		if (node instanceof Argument) {
			argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(new ICommandProxy(changeCommand));
		} else {
			for (Object obj : argumentEditPart.getChildren()) {
				if (obj instanceof GraphicalEditPart) {
					if (node == (BasicNode) DcaseEditorUtil
							.getElement((GraphicalEditPart) obj)) {
						((GraphicalEditPart) obj).getDiagramEditDomain()
								.getDiagramCommandStack()
								.execute(new ICommandProxy(changeCommand));
						return;
					}
				}
			}
		}
	}

}
