/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */

package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Userdef001;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
	 * Adjusts the refecences between Attachment and RefSource.
	 * 
	 * @param event
	 *            An event
	 * @return the result of the execution.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile file = DcaseEditorUtil.getModelFile(currentArgumentEditPart);

		Map<String, String> moduleMap = null;
		HashSet<String> savingModuleSet = new HashSet<String>();
		IResource[] resources = null;
		try {
			// get current map
			moduleMap = ModuleUtil.getModulesAndNodes(file, true);
			resources = ModuleUtil.getMembers(file.getProject());
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
				EObject eobj = ModelUtil.getModel(resFile, true);
				if (eobj instanceof Argument) {
					Argument argument = (Argument) eobj;
					String moduleStr = ModuleUtil.getModuleName(resFile);

					// check Goal and Module Nodes
					for (BasicNode node : argument.getRootBasicNode()) {
						if (!(node instanceof Goal)
								&& !(node instanceof Userdef005) && !(node instanceof Userdef001)) {
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
								String refSourceStr = moduleMap
										.get(attachmentStr);
								String newStr = ""; //$NON-NLS-1$
								if (refSourceStr == null) {
									refSourceStr = ""; //$NON-NLS-1$
								}
								if (refSourceStr.length() > 0) {
									newStr = ModuleUtil.removeModuleReference(
											refSourceStr, refStr);
								}
								if (refSourceStr.equals(newStr)) {
									addRefSource(attachmentStr, refStr);
									savingModuleSet.add(ModuleUtil
											.getModuleName(attachmentStr));
								} else {
									moduleMap.put(attachmentStr, newStr);
								}
								// Sync Responsibility
								String respRefName = getRespName(attachmentStr);
								String respRefAddr = getRespAddress(attachmentStr);
								String respRefIcon = getRespIcon(attachmentStr);
								String respRefTime = getRespTime(attachmentStr);
								String respMyName = (node.getRespName() != null) ? node.getRespName():""; //$NON-NLS-1$
								String respMyAddr = (node.getRespAddress() != null) ? node.getRespAddress():""; //$NON-NLS-1$
								String respMyIcon = (node.getRespIcon() != null) ? node.getRespIcon():""; //$NON-NLS-1$
								String respMyTime = (node.getRespTime() != null) ? node.getRespTime():""; //$NON-NLS-1$
								if (!respMyName.equals(respRefName)) {
									setRespName(refStr, respRefName);
									savingModuleSet.add(moduleStr);
								}
								if (!respMyAddr.equals(respRefAddr)) {
									setRespAddress(refStr, respRefAddr);
									savingModuleSet.add(moduleStr);
								}
								if (!respMyIcon.equals(respRefIcon)) {
									setRespIcon(refStr, respRefIcon);
									savingModuleSet.add(moduleStr);
								}
								if (!respMyTime.equals(respRefTime)) {
									setRespTime(refStr, respRefTime);
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
			String refSourceStr = entry.getValue();
			if (refSourceStr != null && refSourceStr.length() > 0) {
				String nodeName = entry.getKey();
				removeRefSource(nodeName, refSourceStr);
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
	 * Sets the respName attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the respName value.
	 */
	private void setRespName(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.RESPNAME, str);
	}

	/**
	 * Sets the respAddress attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the respAddress value.
	 */
	private void setRespAddress(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.RESPADDRESS, str);
	}

	/**
	 * Sets the respIcon attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the respIcon value.
	 */
	private void setRespIcon(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.RESPICON, str);
	}

	/**
	 * Sets the respTime attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param str
	 *            the respTime value.
	 */
	private void setRespTime(String nodeName, String str) {
		setAttribute(nodeName, AttributeType.RESPTIME, str);
	}

	/**
	 * Appends to the refSource attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param refStr
	 *            the value to append.
	 */
	private void addRefSource(String nodeName, String refStr) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return;
		}
		String refSourceStr = node.getRefSource();
		for (String ref : refStr.split(ModuleUtil.getReferenceSeparator())) {
			refSourceStr = ModuleUtil
					.appendModuleReference(refSourceStr, ref);
		}
		setAttribute(node, AttributeType.REFSOURCE, refSourceStr);
	}

	/**
	 * Removes from the refSource attribute.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @param refStr
	 *            the value to remove.
	 */
	private void removeRefSource(String nodeName, String refStr) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return;
		}
		String refSourceStr = node.getRefSource();
		for (String ref : refStr.split(ModuleUtil.getReferenceSeparator())) {
			refSourceStr = ModuleUtil
					.removeModuleReference(refSourceStr, ref);
		}
		setAttribute(node, AttributeType.REFSOURCE, refSourceStr);
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
			if (nodePartName == null) {
				return null;
			}
			// If nodeName is module name, return Argument
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
	 * Returns the respName attribute value.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the urespName attribute value.
	 */
	private String getRespName(String nodeName) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return null;
		}
		String ret = node.getRespName();
		return (ret == null) ? "":ret;  //$NON-NLS-1$
	}

	/**
	 * Returns the respAddress attribute value.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the urespAddress attribute value.
	 */
	private String getRespAddress(String nodeName) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return null;
		}
		String ret = node.getRespAddress();
		return (ret == null) ? "":ret;  //$NON-NLS-1$
	}

	/**
	 * Returns the respIcon attribute value.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the urespName attribute value.
	 */
	private String getRespIcon(String nodeName) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return null;
		}
		String ret = node.getRespIcon();
		return (ret == null) ? "":ret;  //$NON-NLS-1$
	}

	/**
	 * Returns the respTime attribute value.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the urespTime attribute value.
	 */
	private String getRespTime(String nodeName) {
		BasicNode node = nodeNameToNode(nodeName);
		if (node == null) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.AdjustReference_0, nodeName),
					MessageTypeImpl.ADJUST_REFERENCE_FAILED);
			return null;
		}
		String ret = node.getRespTime();
		return (ret == null) ? "":ret;  //$NON-NLS-1$
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
