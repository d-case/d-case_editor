/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.common.util;

import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.System;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;
import net.dependableos.dcase.diagram.editor.ui.PatternNumberDialog;
import net.dependableos.dcase.diagram.part.PatternUtil;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Tool;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * A utility class that handles modules.
 */
public final class ModuleUtil {

	/**
	 * the file extension of a diagram.
	 */
	private static final String DIAGRAM_FILE_EXTENSION_NAME = PatternUtil.getDiagramFileExtension();

	/**
	 * the file extension of a model.
	 */
	private static final String MODEL_FILE_EXTENSION_NAME = PatternUtil.getModelFileExtension();

	/**
	 * the file extension of a d* diagram.
	 */
	private static final String DSTAR_FILE_EXTENSION_NAME = PatternUtil.getDstarDiagramFileExtension();

	/**
	 * the file extension of a model.
	 */
	private static final String DSTAR_MODEL_FILE_EXTENSION_NAME = PatternUtil.getDstarModelFileExtension();

	/**
	 * the main module name.
	 */
	private static final String MAIN_MODULE_NAME = PatternUtil.getMainModuleName();

	/**
	 * the separator string of references.
	 */
	private static final String REFERENCE_SEPARATOR_NAME = ";"; //$NON-NLS-1$

	/**
	 * the separator string of modules and nodes.
	 */
	private static final String MODULE_SEPARATOR_NAME = "/"; //$NON-NLS-1$

	/**
	 * the Public flag name.
	 */
	private static final String PUBLIC_FLAG_NAME = "P"; //$NON-NLS-1$

	/**
	 * the separator string of responsibility.
	 */
	private static final String RESPONSIBILITY_SEPARATOR_NAME = PatternUtil.getResponsibilitySeparatorName(); //$NON-NLS-1$

	/**
	 * the Contract flag name.
	 */
	private static final String CONTRACT_FLAG_NAME = "C"; //$NON-NLS-1$

	/**
	 * the Responsibility Contract flag name.
	 */
	private static final String RESPONSIBILITY_CONTRACT_FLAG_NAME = "R"; //$NON-NLS-1$

	/**
	 * A constructor.
	 */
	private ModuleUtil() {
	}

	/**
	 * Returns the reference separator.
	 * 
	 * @return the reference separator.
	 */
	public static String getReferenceSeparator() {
		return REFERENCE_SEPARATOR_NAME;
	}

	/**
	 * Returns the module separator.
	 * 
	 * @return the reference separator.
	 */
	public static String getModuleSeparator() {
		return MODULE_SEPARATOR_NAME;
	}

	/**
	 * Returns the diagram file extension.
	 * 
	 * @return the diagram file extension.
	 */
	public static String getDiagramFileExtension() {
		return DIAGRAM_FILE_EXTENSION_NAME;
	}

	/**
	 * Returns the model file extension.
	 * 
	 * @return the model file extension.
	 */
	public static String getModelFileExtension() {
		return MODEL_FILE_EXTENSION_NAME;
	}

	/**
	 * Returns the d* diagram file extension.
	 * 
	 * @return the d* diagram file extension.
	 */
	public static String getDstarDiagramFileExtension() {
		return DSTAR_FILE_EXTENSION_NAME;
	}

	/**
	 * Returns the d* model file extension.
	 * 
	 * @return the d* model file extension.
	 */
	public static String getDstarModelFileExtension() {
		return DSTAR_MODEL_FILE_EXTENSION_NAME;
	}

	/**
	 * Returns the public flag string.
	 * 
	 * @return the public flag string.
	 */
	public static String getPublicFlagString() {
		return PUBLIC_FLAG_NAME;
	}

	/**
	 * Returns the main module name.
	 * 
	 * @return the main module name.
	 */
	public static String getMainModuleName() {
		return MAIN_MODULE_NAME;
	}

	/**
	 * Returns whether the name is workspace reference.
	 * 
	 * @param name
	 *            the attachment name.
	 * @return whether the name is workspace reference.
	 */
	public static boolean isWorkspaceReference(String name) {
		return PatternUtil.isWorkspaceReference(name);
	}

	/**
	 * Returns whether the node name or not.
	 * 
	 * @param name
	 *            the node name.
	 * @return whether the node name or not.
	 */
	public static boolean isPublicNodeName(String name) {
		return PatternUtil.isPublicNodeName(name);
	}

	/**
	 * Returns whether the file is model file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is model file.
	 */
	public static boolean isModelFile(IFile file) {
		return PatternUtil.isModelFile(file);
	}

	/**
	 * Returns whether the file is diagram file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is diagram file.
	 */
	public static boolean isDiagramFile(IFile file) {
		return PatternUtil.isDiagramFile(file);
	}

	/**
	 * Returns whether the file is d* model file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is d* model file.
	 */
	public static boolean isDstarModelFile(IFile file) {
		return PatternUtil.isDstarModelFile(file);
	}

	/**
	 * Returns whether the file is d* diagram file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is d* diagram file.
	 */
	public static boolean isDstarFile(IFile file) {
		return PatternUtil.isDstarFile(file);
	}

	/**
	 * Returns the module name.
	 * 
	 * @param file
	 *            the diagram file or the model file.
	 * @return the module name.
	 */
	public static String getModuleName(IFile file) {
		return PatternUtil.getModuleName(file);
	}

	/**
	 * Returns the module name.
	 * 
	 * @param name
	 *            the module name or node name.
	 * @return the module name.
	 */
	public static String getModuleName(String name) {
		return PatternUtil.getModuleName(name);
	}
	
	/**
	 * Returns the node name.
	 * 
	 * @param nodeName
	 *            the node name (with module name).
	 * @return the node name.
	 */
	public static String getNodeName(String nodeName) {
		return PatternUtil.getNodeName(nodeName);
	}

	/**
	 * Returns the Diagram file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the diagram file path.
	 */
	public static IPath getDiagramPath(String name) {
		return PatternUtil.getDiagramPath(name);
	}

	/**
	 * Returns the Model file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the model file path.
	 */
	public static IPath getModelPath(String name) {
		return PatternUtil.getModelPath(name);
	}

	/**
	 * Returns the d* Diagram file path.
	 * 
	 * @return the d* diagram file path.
	 */
	public static IPath getDstarPath() {
		return PatternUtil.getDstarPath();
	}

	/**
	 * Returns the d* Model file path.
	 * 
	 * @return the d* model file path.
	 */
	public static IPath getDstarModelPath() {
		return PatternUtil.getDstarModelPath();
	}

	/**
	 * Returns the public node reference.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @param nodeName
	 *            the node name.
	 * @return the public node reference string.
	 */
	public static String createNodeReference(String moduleName, String nodeName) {
		return PatternUtil.createNodeReference(moduleName, nodeName);
	}

	/**
	 * Returns the public node reference.
	 * 
	 * @param modelFile
	 *            the model file.
	 * @param nodeName
	 *            the node name.
	 * @return the public node reference string.
	 */
	public static String createNodeReference(IFile modelFile, String nodeName) {
		return PatternUtil.createNodeReference(getModuleName(modelFile), nodeName);
	}

	/**
	 * Returns the reference number.
	 * 
	 * @param reference
	 *            RefSource value.
	 * @return the reference number.
	 */
	public static int getReferenceNumber(String reference) {
		if (reference == null || reference.length() == 0) {
			return 0;
		} else {
			return reference.split(REFERENCE_SEPARATOR_NAME).length;
		}
	}

	/**
	 * Returns the attribute of node.
	 * 
	 * @param node
	 *            the node.
	 * @param type
	 *            the attribute type.
	 * @return the attribute string.
	 */
	public static String getAttributeValue(BasicNode node, AttributeType type) {
		NodeInfo nodeInfo = ModelUtil.createNodeInfo(node);
		if (nodeInfo != null) {
			return (String) nodeInfo.getAttribute(type);
		} else {
			return null;
		}
	}

	/**
	 * Returns the attribute of editpart.
	 * 
	 * @param editPart
	 *            the editpart.
	 * @param type
	 *            the attribute type.
	 * @return the attribute string.
	 */
	public static String getAttributeValue(GraphicalEditPart editPart,
			AttributeType type) {
		EObject eobj = DcaseEditorUtil.getElement(editPart);
		if (eobj instanceof BasicNode) {
			return getAttributeValue((BasicNode) eobj, type);
		} else {
			return null;
		}
	}

	/**
	 * Returns the RefSource attribute value.
	 * 
	 * @param list
	 *            the reference list.
	 * @return the RefSource attribute value.
	 */
	private static String makeModuleReference(ArrayList<String> list) {
		StringBuffer buffer = new StringBuffer();
		for (String attr : list) {
			if (buffer.length() > 0) {
				buffer.append(REFERENCE_SEPARATOR_NAME);
			}
			buffer.append(attr);
		}
		return buffer.toString();
	}

	/**
	 * Appends reference to RefSource and returns the new references.
	 * 
	 * @param allStr
	 *            the RefSource.
	 * @param refStr
	 *            the adding reference.
	 * @return the new references.
	 */
	public static String appendModuleReference(String allStr, String refStr) {
		ArrayList<String> orgList = null;
		if (allStr != null) {
			orgList = new ArrayList<String>(Arrays.asList(allStr
					.split(REFERENCE_SEPARATOR_NAME)));
		} else {
			orgList = new ArrayList<String>();
		}
		if (!orgList.contains(refStr)) {
			orgList.add(refStr);
		}
		return makeModuleReference(orgList);
	}

	/**
	 * Appends reference to RefSource and returns the new references.
	 * 
	 * @param node
	 *            the node.
	 * @param refStr
	 *            the adding reference.
	 * @return the new references.
	 */
	public static String appendModuleReference(BasicNode node, String refStr) {
		return appendModuleReference(node.getRefSource(), refStr);
	}

	/**
	 * Appends reference to RefSource and returns the new references.
	 * 
	 * @param editPart
	 *            the editpart.
	 * @param refStr
	 *            the adding reference.
	 * @return the new references.
	 */
	public static String appendModuleReference(GraphicalEditPart editPart,
			String refStr) {
		EObject eobj = DcaseEditorUtil.getElement(editPart);
		if (eobj instanceof BasicNode) {
			return appendModuleReference((BasicNode) eobj, refStr);
		} else {
			return null;
		}
	}

	/**
	 * Removes reference from RefSource and returns the new references.
	 * 
	 * @param allStr
	 *            the RefSource.
	 * @param refStr
	 *            the removing reference.
	 * @return the new references.
	 */
	public static String removeModuleReference(String allStr, String refStr) {
		ArrayList<String> orgList = null;
		if (allStr != null) {
			orgList = new ArrayList<String>(Arrays.asList(allStr
					.split(REFERENCE_SEPARATOR_NAME)));
		} else {
			orgList = new ArrayList<String>();
		}
		if (orgList.contains(refStr)) {
			orgList.remove(refStr);
		}
		return makeModuleReference(orgList);
	}

	/**
	 * Removes reference from RefSource and returns the new references.
	 * 
	 * @param node
	 *            the node.
	 * @param refStr
	 *            the removing reference.
	 * @return the new references.
	 */
	public static String removeModuleReference(BasicNode node, String refStr) {
		return removeModuleReference(node.getRefSource(), refStr);
	}

	/**
	 * Removes reference from RefSource and returns the new references.
	 * 
	 * @param editPart
	 *            the editpart.
	 * @param refStr
	 *            the removing reference.
	 * @return the new references.
	 */
	public static String removeModuleReference(GraphicalEditPart editPart,
			String refStr) {
		EObject eobj = DcaseEditorUtil.getElement(editPart);
		if (eobj instanceof BasicNode) {
			return removeModuleReference((BasicNode) eobj, refStr);
		} else {
			return null;
		}
	}

	/**
	 * Returns the name list of modules and public nodes.
	 * 
	 * @param editPart
	 *            the argument editpart.
	 * @param needNode
	 *            whether public node is needed.
	 * @return the map(name, reference) of modules and public nodes.
	 */
	public static Map<String, String> getModulesAndNodes(
			GraphicalEditPart editPart, boolean needNode) throws CoreException {
		IFile file = DcaseEditorUtil.getModelFile(editPart);
		return getModulesAndNodes(file, needNode);
	}

	/**
	 * Returns the name list of modules and public nodes.
	 * 
	 * @param editPart
	 *            the argument editpart.
	 * @param needNode
	 *            whether public node is needed.
	 * @param currentModuleName
	 *            the current module name (need to check public or parent).
	 * @return the map(name, reference) of modules and public nodes.
	 */
	public static Map<String, String> getModulesAndNodes(
			GraphicalEditPart editPart, boolean needNode,
			String currentModuleName) throws CoreException {
		IFile file = DcaseEditorUtil.getModelFile(editPart);
		return getModulesAndNodes(file, needNode, currentModuleName);
	}

	/**
	 * Returns the name list of modules and public nodes.
	 * 
	 * @param file
	 *            the model file.
	 * @param needNode
	 *            whether public node is needed.
	 * @return the map(name, reference) of modules and public nodes.
	 */
	public static Map<String, String> getModulesAndNodes(IFile file,
			boolean needNode) throws CoreException {
		return getModulesAndNodes(file, needNode, null);
	}

	/**
	 * Returns the name list of modules and public nodes.
	 * 
	 * @param file
	 *            the model file.
	 * @param needNode
	 *            whether public node is needed.
	 * @param currentModuleName
	 *            the current module name (need to check public or parent).
	 * @return the map(name, reference) of modules and public nodes.
	 */
	public static Map<String, String> getModulesAndNodes(IFile file,
			boolean needNode, String currentModuleName) throws CoreException {
		Map<String, String> moduleMap = new LinkedHashMap<String, String>();
		if (file == null) {
			return moduleMap;
		}
		IResource[] resources = getMembers(file.getProject());
		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				IFile resFile = (IFile) resource;
				if (!isModelFile(resFile)) {
					continue;
				}
				// add the module to list
				String moduleName = getModuleName(resFile);
				EObject eobj = ModelUtil.getModel(resFile, true);
				if (eobj instanceof Argument) {
					Argument argument = (Argument) eobj;
					String refSourceStr = argument.getRefSource();
					// check whether module is public or not.
					boolean addModule = true;
					if (currentModuleName != null) {
						String parent = argument.getParent();
						if (parent == null || !parent.equals(currentModuleName)) {
							String flags = argument.getFlag();
							if (flags == null || flags.length() == 0
									|| flags.indexOf(PUBLIC_FLAG_NAME) < 0) {
								addModule = false;
							}
						}
					}
					if (addModule) {
						moduleMap.put(getModuleName(resFile),
								(refSourceStr != null) ? refSourceStr : ""); //$NON-NLS-1$
					}
					// add public node to list
					if (needNode) {
						EList<BasicNode> nodes = argument.getRootBasicNode();
						for (BasicNode node : nodes) {
							String flags = node.getFlag();
							if (flags == null || flags.length() == 0) {
								continue;
							}
							if (flags.indexOf(PUBLIC_FLAG_NAME) >= 0) {
								refSourceStr = node.getRefSource();
								moduleMap.put(
										createNodeReference(moduleName,
												node.getName()),
										(refSourceStr != null) ? refSourceStr
												: ""); //$NON-NLS-1$
							}
						}
					}
				}
			}
		}
		return moduleMap;
	}
	
	/**
	 * Returns the files belong to the project.
	 * @param project the project.
	 * @return the files belong to the project.
	 */
	public static IResource[] getMembers(IProject project) throws CoreException {
		ArrayList<IResource>ret = new ArrayList<IResource>();
		for (IResource res : project.members()) {
			if (res instanceof IFolder) {
				ret.addAll(getMembers((IFolder)res));
			} else {
				ret.add(res);
			}
		}
		return ret.toArray(new IResource[ret.size()]);
	}
	
	/**
	 * Returns the files belong to the folder (recursively).
	 * @param folder the folder.
	 * @return the files belong to the folder.
	 */
	private static List<IResource> getMembers(IFolder folder) throws CoreException {
		ArrayList<IResource>ret = new ArrayList<IResource>();
		for (IResource res : folder.members()) {
			if (res instanceof IFolder) {
				ret.addAll(getMembers((IFolder)res));
			} else {
				ret.add(res);
			}
		}
		return ret;
	}

	/**
	 * Opens the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @param isDiagram
	 *            whether normal diagram.
	 * @return the opened module editor.
	 */
	public static IEditorPart openModuleEditor(String moduleName, boolean isDiagram) {
		return PatternUtil.openModuleEditor(moduleName, isDiagram);
	}

	/**
	 * Opens the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @return the opened module editor.
	 */
	public static IEditorPart openModuleEditor(String moduleName) {
		return PatternUtil.openModuleEditor(moduleName);
	}

	/**
	 * Saves the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @param isDiagram
	 *            whether normal diagram.
	 */
	public static void saveModuleEditor(String moduleName, boolean isDiagram) {
		PatternUtil.saveModuleEditor(moduleName, isDiagram);
	}
	
	/**
	 * Saves the module diagram.
	 * 
	 * @param diagramFile the diagram file.
	 */
	public static void saveModuleEditor(IFile diagramFile) {
		PatternUtil.saveModuleEditor(diagramFile);
	}

	/**
	 * Saves the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 */
	public static void saveModuleEditor(String moduleName) {
		saveModuleEditor(moduleName, true);
	}

	/**
	 * Closes the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @param isDiagram
	 *            whether normal diagram.
	 */
	public static void closeModuleEditor(String moduleName, boolean isDiagram) {
		IEditorPart editorPart = openModuleEditor(moduleName, isDiagram);
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		workbenchPage.closeEditor(editorPart, true);
	}

	/**
	 * Closes the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 */
	public static void closeModuleEditor(String moduleName) {
		closeModuleEditor(moduleName, true);
	}

	/**
	 * Checks if the attachment is URL or not.
	 * 
	 * @param attachment
	 *            the attachment.
	 * @return whether the attachment is URL or not.
	 */
	public static boolean isUrl(String attachment) {
		try {
			URL url = new URL(attachment);
			if (!DcaseEditorUtil.checkDcaseReferenceProtocol(url.getProtocol())) {
				return false;
			}

		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the node number.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @return the node number.
	 */
	public static int countNodes(String moduleName) {
		if (moduleName == null || moduleName.length() == 0
				|| isPublicNodeName(moduleName)) {
			return 0;
		}
		IPath path = getModelPath(moduleName);
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				EObject eobj = ModelUtil.getModel(file, true);
				if (eobj instanceof Argument) {
					EList<BasicNode> nodes = ((Argument) eobj)
							.getRootBasicNode();
					return (nodes != null) ? nodes.size() : 0;
				}
			}
		}
		return 0;
	}

	/***
	 * Returns the root node of argument.
	 * 
	 * @param argument
	 *            the argument.
	 * @return the root node of argument.
	 */
	public static BasicNode getRootElement(Argument argument) {
		HashSet<BasicNode> nodeSet = new HashSet<BasicNode>(
				argument.getRootBasicNode());
		for (BasicLink link : argument.getRootBasicLink()) {
			nodeSet.remove(link.getTarget());
		}
		return (nodeSet.size() > 0) ? (BasicNode) nodeSet.toArray()[0] : null;
	}

	/**
	 * Returns the root node.
	 * 
	 * @param file
	 *            the module file.
	 * @return the root node.
	 */
	public static BasicNode getRootNode(IFile file) {
		EObject templateModel = ModelUtil.getModel(file, true);
		return getRootNode((Argument) templateModel);
	}

	/**
	 * Returns the root node.
	 * 
	 * @param argument
	 *            the argument.
	 * @return the root node.
	 */
	public static BasicNode getRootNode(Argument argument) {
		List<BasicNode> nodeList = argument.getRootBasicNode();
		HashSet<BasicNode> nodeSet = new HashSet<BasicNode>(nodeList);
		for (BasicLink link : argument.getRootBasicLink()) {
			nodeSet.remove(link.getTarget());
		}
		if (nodeSet.size() == 1) {
			return (BasicNode) nodeSet.toArray()[0];
		} else {
			return null;
		}
	}

	/**
	 * Returns the responsibility value.
	 * 
	 * @param node
	 *            the node.
	 * @return the responsibility value.
	 */
	public static String getResponsibilityValue(BasicNode node, int index) {
		switch (index) {
		case 0: // RespName
			return node.getRespName();
		case 1: // RespAddress
			return node.getRespAddress();
		case 2: // RespIcon
			return node.getRespIcon();
		case 3: // RespTime
			return node.getRespTime();
		}
		return null;
	}

	/**
	 * Returns the responsibility name.
	 * 
	 * @param node
	 *            the node.
	 * @return the responsibility name.
	 */
	public static String getResponsibilityName(BasicNode node) {
		return getResponsibilityValue(node, 0);
	}

	/**
	 * Returns the responsibility address.
	 * 
	 * @param node
	 *            the node.
	 * @return the responsibility address.
	 */
	public static String getResponsibilityAddress(BasicNode node) {
		return getResponsibilityValue(node, 1);
	}

	/**
	 * Returns the responsibility icon path.
	 * 
	 * @param node
	 *            the node.
	 * @return the responsibility icon path.
	 */
	public static String getResponsibilityIconPath(BasicNode node) {
		return getResponsibilityValue(node, 2);
	}

	/**
	 * Returns the responsibility time.
	 * 
	 * @param node
	 *            the node.
	 * @return the responsibility time.
	 */
	public static String getResponsibilityTime(BasicNode node) {
		return getResponsibilityValue(node, 3);
	}

	/**
	 * Returns the responsibility value.
	 * 
	 * @param argument
	 *            the argument.
	 * @return the responsibility value.
	 */
	public static String getResponsibilityValue(Argument argument, int index) {
		return getResponsibilityValue((BasicNode)argument, index);
	}

	/**
	 * Returns the responsibility name.
	 * 
	 * @param node
	 *            the argument.
	 * @return the responsibility name.
	 */
	public static String getResponsibilityName(Argument node) {
		return getResponsibilityValue(node, 0);
	}

	/**
	 * Returns the responsibility address.
	 * 
	 * @param node
	 *            the argument.
	 * @return the responsibility address.
	 */
	public static String getResponsibilityAddress(Argument node) {
		return getResponsibilityValue(node, 1);
	}

	/**
	 * Returns the responsibility icon path.
	 * 
	 * @param node
	 *            the argument.
	 * @return the responsibility icon path.
	 */
	public static String getResponsibilityIconPath(Argument node) {
		return getResponsibilityValue(node, 2);
	}

	/**
	 * Returns the current cursor location for Diagram.
	 * 
	 * @param argumentEditPart
	 *            the argument edit part.
	 * @return the current point.
	 */
	public static Point getCurrentLocation(ArgumentEditPart argumentEditPart) {
		IDiagramGraphicalViewer viewer = DcaseEditorUtil
				.getCurrentDcaseEditor().getDiagramGraphicalViewer();
		FigureCanvas canvas = (FigureCanvas) viewer.getControl();
		// the position of scrollable diagram.
		Point viewPoint = canvas.getViewport().getViewLocation();
		Tool tool = argumentEditPart.getViewer().getEditDomain()
				.getActiveTool();
		AbstractTool aTool = (AbstractTool) tool;
		Point toolLocation = null;
		try {
			Method method = AbstractTool.class.getDeclaredMethod("getLocation"); //$NON-NLS-1$
			method.setAccessible(true);
			toolLocation = ((org.eclipse.draw2d.geometry.Point) method
					.invoke(aTool)).getCopy();
		} catch (Exception e) {
			MessageWriter.writeMessageToConsole(
					Messages.AddPatternContributionItem_0,
					MessageTypeImpl.CREATE_PATTERN_FAILED);
			return new Point(0, 0);
		}
		return new Point(viewPoint.x + toolLocation.x, viewPoint.y
				+ toolLocation.y);
	}

	/**
	 * Returns the contract icon string.
	 * 
	 * @param node1
	 *            the one node.
	 * @param node2
	 *            the another node.
	 * @return the contract icon string.
	 */
	public static String getContractIconString(BasicNode node1, BasicNode node2) {
		String ret = ""; //$NON-NLS-1$
		if (node1 != null && node2 != null) {
			String[] resp1 = new String[] {
					getResponsibilityName(node1),
					getResponsibilityAddress(node1),
					getResponsibilityIconPath(node1),
			};
			String[] resp2 = new String[] {
					getResponsibilityName(node2),
					getResponsibilityAddress(node2),
					getResponsibilityIconPath(node2),
			};
			boolean isCheck = false;
			for (String s : resp1) {
				if (s != null && s.length() > 0) {
					isCheck = true;
					break;
				}
			}
			for (String s : resp2) {
				if (s != null && s.length() > 0) {
					isCheck = true;
					break;
				}
			}
			ret = RESPONSIBILITY_SEPARATOR_NAME
					+ (isCheck && Arrays.equals(resp1, resp2) ? CONTRACT_FLAG_NAME
							: RESPONSIBILITY_CONTRACT_FLAG_NAME);
		}
		return ret;
	}

	/**
	 * Returns the extracted attachment string.
	 * 
	 * @param attachment
	 *            the attachment string.
	 * @return the extracted attachment string.
	 */
	public static String removeContractIconString(String attachment) {
		if (attachment != null) {
			int index = attachment.indexOf(RESPONSIBILITY_SEPARATOR_NAME);
			if (index >= 0) {
				return attachment.substring(0, index);
			}
		}
		return attachment;
	}

	/**
	 * Processes the Patterns.
	 * @param copyArgument the copied argument.
	 * @return false if invalid or cancelled.
	 */
	public static boolean processPatterns(Argument copyArgument) {
		// get root node
		BasicNode rootNode = ModuleUtil.getRootNode(copyArgument);
		if (rootNode == null) {
			return false;
		}

		// process loop
		while (true) {
			// search Patterns
			BasicNode cNode = searchFirstPattern(rootNode, copyArgument, new HashSet<BasicNode>());
			if (cNode == null) {
				break;
			}
			if (! PatternUtil.isValid(cNode, copyArgument)) {
				MessageWriter.writeMessageToConsole(
						NLS.bind(Messages.AddPatternContributionItem_2, cNode.getName()),
						MessageTypeImpl.CREATE_PATTERN_FAILED);
				return false;
			}
			
			// process Pattern nodes.
			System scNode = (System)cNode;
			String subType = scNode.getSubType();
			int k = getPatternNumber(scNode);
			if (k <= 0) {
				return false;
			}
			// Loop
			if (PatternUtil.isLoop(subType)) {
				// add k-1 times
				BasicNode parent = PatternUtil.getParent(cNode, copyArgument);
				ArrayList<BasicNode>pnodeList = new ArrayList<BasicNode>();
				ArrayList<BasicLink>plinkList = new ArrayList<BasicLink>();
				PatternUtil.getSubtree(scNode, copyArgument, pnodeList, plinkList);
				String leafName = scNode.getLeafNode();
				BasicNode leafNode = PatternUtil.getNode(leafName, pnodeList);
				for (int i = 1; i < k; i++) {
					if (leafNode == null) {
						MessageWriter.writeMessageToConsole(
								NLS.bind(Messages.AddPatternContributionItem_2, leafName),
								MessageTypeImpl.CREATE_PATTERN_FAILED);
						return false;
					}
					ArrayList<BasicNode>newNodeList = new ArrayList<BasicNode>();
					ArrayList<BasicLink>newLinkList = new ArrayList<BasicLink>();
					BasicNode newpNode = PatternUtil.copySubtree(parent, pnodeList, plinkList, newNodeList, newLinkList);
					copyArgument.getRootBasicNode().addAll(newNodeList);
					copyArgument.getRootBasicLink().addAll(newLinkList);
					BasicLink newLink = DcaseFactory.eINSTANCE.createDcaseLink001();
					newLink.setSource(leafNode);
					newLink.setTarget(newpNode);
					copyArgument.getRootBasicLink().add(newLink);
					leafNode = PatternUtil.getNode(leafName, newNodeList);
				}
			}
			// Choice
			if (PatternUtil.isChoice(subType)) {
				// remove from k+1 to n
				List<BasicNode> childList = PatternUtil.getChildren(cNode, copyArgument);
				for (int i = k; i < childList.size(); i++) {
					ArrayList<BasicNode>pnodeList = new ArrayList<BasicNode>();
					ArrayList<BasicLink>plinkList = new ArrayList<BasicLink>();
					HashSet<BasicNode>checkedSet = new HashSet<BasicNode>();
					BasicNode pnode = childList.get(i);
					PatternUtil.getSubtree(pnode, copyArgument, pnodeList, plinkList, checkedSet);
					copyArgument.getRootBasicNode().removeAll(pnodeList);
					copyArgument.getRootBasicLink().removeAll(plinkList);
					// remove links of deleted nodes.
					for (BasicNode dnode : pnodeList) {
						PatternUtil.removeLinks(dnode, copyArgument.getRootBasicLink());
					}
				}
			}
			// Multiplicity
			if (PatternUtil.isMultiplicity(subType)) {
				// add k-1 times
				BasicNode parent = PatternUtil.getParent(cNode, copyArgument);
				ArrayList<BasicNode>pnodeList = new ArrayList<BasicNode>();
				ArrayList<BasicLink>plinkList = new ArrayList<BasicLink>();
				HashSet<BasicNode>checkedSet = new HashSet<BasicNode>();
				BasicNode pnode = PatternUtil.getChild(cNode, copyArgument, 1);
				PatternUtil.getSubtree(pnode, copyArgument, pnodeList, plinkList, checkedSet);
				for (BasicLink link : copyArgument.getRootBasicLink()) {
					if (link.getSource() == parent) {
						link.setSiblingOrder(Integer.toString(1));
					}
				}
				for (int i = 1; i < k; i++) {
					ArrayList<BasicNode>newNodeList = new ArrayList<BasicNode>();
					ArrayList<BasicLink>newLinkList = new ArrayList<BasicLink>();
					BasicNode newpNode = PatternUtil.copySubtree(pnode, pnodeList, plinkList, newNodeList, newLinkList);
					copyArgument.getRootBasicNode().addAll(newNodeList);
					copyArgument.getRootBasicLink().addAll(newLinkList);
					BasicLink newLink = DcaseFactory.eINSTANCE.createDcaseLink001();
					newLink.setSource(parent);
					newLink.setTarget(newpNode);
					newLink.setSiblingOrder(Integer.toString(i+1));
					copyArgument.getRootBasicLink().add(newLink);
				}
			}
			// remove the current Pattern node.
			PatternUtil.removeLinks(cNode, copyArgument.getRootBasicLink());
			copyArgument.getRootBasicNode().remove(cNode);
		}
		
		// process all Parameter nodes.
		for (BasicNode cNode : copyArgument.getRootBasicNode()) {
			if (! (cNode instanceof System)) {
        		continue;
        	}
			if (PatternUtil.isParameter(((System)cNode).getSubType())) {
				if (!ParameterUtil.processParameter(cNode)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns the first Pattern node.
	 * @param rootNode the root node.
	 * @param argument the argument.
	 * @param nodeSet the checked node set.
	 * @return the first Pattern node.
	 */
	private static BasicNode searchFirstPattern(BasicNode rootNode, Argument argument,
			HashSet<BasicNode> nodeSet) {
		if (! nodeSet.add(rootNode)) {
			return null;
		}
		List<BasicNode>childList = PatternUtil.getChildren(rootNode, argument, false);
		if (childList == null) {
			return null;
		}
		// check all children
		while (childList.size() > 0) {
			// must be Array (for siblingOrder)
			ArrayList<BasicNode>nextList = new ArrayList<BasicNode>();
			for (BasicNode node : childList) {
				if (node instanceof System) {
					String subType = ((System)node).getSubType();
					if (PatternUtil.isLoop(subType) ||
							PatternUtil.isChoice(subType) ||
							PatternUtil.isMultiplicity(subType)) {
						return node;
					}
				}
				// gather grandchildren
				List<BasicNode>gcList = PatternUtil.getChildren(node, argument, false);
				if (gcList != null) {
					for (BasicNode gcnode : gcList) {
						if (nodeSet.add(gcnode)) {
							nextList.add(gcnode);
						}
					}
				}
			}
			// try grandchildren
			childList = nextList;
		}
		return null;
	}

	/**
	 * Gets the number of k.
	 * @param scNode the System node.
	 * @return the number of k.
	 */
	private static int getPatternNumber(System scNode) {
		int i = scNode.getI();
		int j = scNode.getJ();
		if (PatternUtil.isLoop(scNode.getSubType())) {
			i = 1;
			j = 100; // SPINNER_MAX at AttributeDialog.
		}
		if (i == j) {
			return i;
		}
		PatternNumberDialog dialog = new PatternNumberDialog(
				DcaseEditorUtil.getActiveWindowShell(),
				NLS.bind(Menus.AddPattern_0, scNode.getSubType()),
				NLS.bind(Menus.AddPattern_1, scNode.getName()), i, j);
		if (dialog.open() != Dialog.OK) {
			return -1;
		}
		return dialog.getNumber();
	}

}
