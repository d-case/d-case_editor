/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.common.util;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DSTAR_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_DSTAR_FILE_EXTENSION;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;

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
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A utility class that handles modules.
 */
public final class ModuleUtil {

	/**
	 * the file extension of a diagram.
	 */
	private static final String DIAGRAM_FILE_EXTENSION_NAME = PropertyUtil
			.getSystemProperty(DIAGRAM_FILE_EXTENSION);

	/**
	 * the file extension of a model.
	 */
	private static final String MODEL_FILE_EXTENSION_NAME = PropertyUtil
			.getSystemProperty(MODEL_GMF_FILE_EXTENSION);

	/**
	 * the file extension of a d* diagram.
	 */
	private static final String DSTAR_FILE_EXTENSION_NAME = PropertyUtil
			.getSystemProperty(DSTAR_FILE_EXTENSION);

	/**
	 * the file extension of a model.
	 */
	private static final String DSTAR_MODEL_FILE_EXTENSION_NAME = PropertyUtil
			.getSystemProperty(MODEL_DSTAR_FILE_EXTENSION);

	/**
	 * the main module name.
	 */
	private static final String MAIN_MODULE_NAME = "main"; //$NON-NLS-1$

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
	private static final String RESPONSIBILITY_SEPARATOR_NAME = ";"; //$NON-NLS-1$

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
		try {
			if (name.substring(0, 1).equals(MODULE_SEPARATOR_NAME)) {
				return true;
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Returns whether the node is public.
	 * 
	 * @param name
	 *            the node name.
	 * @return whether the node is public.
	 */
	public static boolean isPublicNodeName(String name) {
		if (name != null) {
			return (name.indexOf(MODULE_SEPARATOR_NAME) >= 0);
		} else {
			return false;
		}
	}

	/**
	 * Returns whether the file is model file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is model file.
	 */
	public static boolean isModelFile(IFile file) {
		if (file != null) {
			String extensionStr = file.getFileExtension();
			if (extensionStr != null) {
				return extensionStr.equals(MODEL_FILE_EXTENSION_NAME);
			}
		}
		return false;
	}

	/**
	 * Returns whether the file is diagram file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is diagram file.
	 */
	public static boolean isDiagramFile(IFile file) {
		if (file != null) {
			String extensionStr = file.getFileExtension();
			if (extensionStr != null) {
				return extensionStr.equals(DIAGRAM_FILE_EXTENSION_NAME);
			}
		}
		return false;
	}

	/**
	 * Returns whether the file is d* model file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is d* model file.
	 */
	public static boolean isDstarModelFile(IFile file) {
		if (file != null) {
			String extensionStr = file.getFileExtension();
			if (extensionStr != null) {
				return extensionStr.equals(DSTAR_MODEL_FILE_EXTENSION_NAME);
			}
		}
		return false;
	}

	/**
	 * Returns whether the file is d* diagram file.
	 * 
	 * @param file
	 *            the file.
	 * @return whether the file is d* diagram file.
	 */
	public static boolean isDstarFile(IFile file) {
		if (file != null) {
			String extensionStr = file.getFileExtension();
			if (extensionStr != null) {
				return extensionStr.equals(DSTAR_FILE_EXTENSION_NAME);
			}
		}
		return false;
	}

	/**
	 * Returns the module name.
	 * 
	 * @param file
	 *            the diagram file or the model file.
	 * @return the module name.
	 */
	public static String getModuleName(IFile file) {
		if (file != null) {
			return file.getFullPath().removeFileExtension().lastSegment();
		} else {
			return null;
		}
	}

	/**
	 * Returns the module name.
	 * 
	 * @param nodeName
	 *            the node name.
	 * @return the module name.
	 */
	public static String getModuleName(String nodeName) {
		if (nodeName == null) {
			return null;
		}
		int index = nodeName.indexOf(MODULE_SEPARATOR_NAME);
		if (index >= 0) {
			return nodeName.substring(0, index);
		} else {
			return nodeName;
		}
	}

	/**
	 * Returns the node name.
	 * 
	 * @param nodeName
	 *            the node name (with module name).
	 * @return the node name.
	 */
	public static String getNodeName(String nodeName) {
		if (nodeName == null) {
			return null;
		}
		int index = nodeName.indexOf(MODULE_SEPARATOR_NAME);
		if (index >= 0) {
			return nodeName.substring(index + 1);
		} else {
			return nodeName;
		}
	}

	/**
	 * Returns the Diagram file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the diagram file path.
	 */
	public static IPath getDiagramPath(String name) {
		ArgumentEditPart editPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile modelFile = DcaseEditorUtil.getModelFile(editPart);
		IPath basePath = modelFile.getParent().getFullPath().append(name);
		return basePath.addFileExtension(DIAGRAM_FILE_EXTENSION_NAME);
	}

	/**
	 * Returns the Model file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the model file path.
	 */
	public static IPath getModelPath(String name) {
		ArgumentEditPart editPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile modelFile = DcaseEditorUtil.getModelFile(editPart);
		IPath basePath = modelFile.getParent().getFullPath().append(name);
		return basePath.addFileExtension(MODEL_FILE_EXTENSION_NAME);
	}

	/**
	 * Returns the d* Diagram file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the d* diagram file path.
	 */
	public static IPath getDstarPath(String name) {
		ArgumentEditPart editPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile modelFile = DcaseEditorUtil.getModelFile(editPart);
		IPath basePath = modelFile.getParent().getFullPath().append(name);
		return basePath.addFileExtension(DSTAR_FILE_EXTENSION_NAME);
	}

	/**
	 * Returns the d* Model file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the d* model file path.
	 */
	public static IPath getDstarModelPath(String name) {
		ArgumentEditPart editPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile modelFile = DcaseEditorUtil.getModelFile(editPart);
		IPath basePath = modelFile.getParent().getFullPath().append(name);
		return basePath.addFileExtension(DSTAR_MODEL_FILE_EXTENSION_NAME);
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
		return moduleName + MODULE_SEPARATOR_NAME + nodeName;
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
		return createNodeReference(getModuleName(modelFile), nodeName);
	}

	/**
	 * Returns the reference number.
	 * 
	 * @param reference
	 *            Userdef011 value.
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
	 * Returns the Userdef011 attribute value.
	 * 
	 * @param list
	 *            the reference list.
	 * @return the Userdef011 attribute value.
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
	 * Appends reference to Userdef011 and returns the new references.
	 * 
	 * @param allStr
	 *            the Userdef011.
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
	 * Appends reference to Userdef011 and returns the new references.
	 * 
	 * @param node
	 *            the node.
	 * @param refStr
	 *            the adding reference.
	 * @return the new references.
	 */
	public static String appendModuleReference(BasicNode node, String refStr) {
		return appendModuleReference(node.getUserdef011(), refStr);
	}

	/**
	 * Appends reference to Userdef011 and returns the new references.
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
	 * Removes reference from Userdef011 and returns the new references.
	 * 
	 * @param allStr
	 *            the Userdef011.
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
	 * Removes reference from Userdef011 and returns the new references.
	 * 
	 * @param node
	 *            the node.
	 * @param refStr
	 *            the removing reference.
	 * @return the new references.
	 */
	public static String removeModuleReference(BasicNode node, String refStr) {
		return removeModuleReference(node.getUserdef011(), refStr);
	}

	/**
	 * Removes reference from Userdef011 and returns the new references.
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
		IResource[] resources = file.getParent().members();
		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				IFile resFile = (IFile) resource;
				if (!isModelFile(resFile)) {
					continue;
				}
				// add the module to list
				String moduleName = getModuleName(resFile);
				// if(moduleName.equals(MAIN_MODULE_NAME)) {
				// continue;
				// }
				EObject eobj = ModelUtil.getModel(resFile);
				if (eobj instanceof Argument) {
					Argument argument = (Argument) eobj;
					String userdef011Str = argument.getUserdef011();
					// check whether module is public or not.
					boolean addModule = true;
					if (currentModuleName != null) {
						String parent = argument.getUserdef013();
						if (parent == null || !parent.equals(currentModuleName)) {
							String flags = argument.getUserdef015();
							if (flags == null || flags.length() == 0
									|| flags.indexOf(PUBLIC_FLAG_NAME) < 0) {
								addModule = false;
							}
						}
					}
					if (addModule) {
						moduleMap.put(getModuleName(resFile),
								(userdef011Str != null) ? userdef011Str : ""); //$NON-NLS-1$
					}
					// add public node to list
					if (needNode) {
						EList<BasicNode> nodes = argument.getRootBasicNode();
						for (BasicNode node : nodes) {
							String flags = node.getUserdef015();
							if (flags == null || flags.length() == 0) {
								continue;
							}
							if (flags.indexOf(PUBLIC_FLAG_NAME) >= 0) {
								userdef011Str = node.getUserdef011();
								moduleMap.put(
										createNodeReference(moduleName,
												node.getName()),
										(userdef011Str != null) ? userdef011Str
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
	 * Opens the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @param isDiagram
	 *            whether normal diagram.
	 * @return the opened module editor.
	 */
	public static IEditorPart openModuleEditor(String moduleName,
			boolean isDiagram) {
		IPath diagramPath;
		if (isDiagram) {
			diagramPath = ModuleUtil.getDiagramPath(moduleName);
		} else {
			diagramPath = ModuleUtil.getDstarPath(moduleName);
		}
		if (diagramPath == null) {
			return null;
		}
		IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath
				.toOSString());
		if (diagramFile == null) {
			return null;
		}
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(diagramFile.getName());
		if (desc != null) {
			try {
				return workbenchPage.openEditor(
						new FileEditorInput(diagramFile), desc.getId());
			} catch (Exception e) {
				MessageWriter.writeMessageToConsole(Messages.OpenModuleFile_0,
						MessageTypeImpl.OPEN_MODULE_FILE_FAILED);
				return null;
			}
		} else {
			MessageWriter.writeMessageToConsole(Messages.OpenModuleFile_1,
					MessageTypeImpl.OPEN_MODULE_FILE_FAILED);
			return null;
		}
	}

	/**
	 * Opens the module diagram.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @return the opened module editor.
	 */
	public static IEditorPart openModuleEditor(String moduleName) {
		return openModuleEditor(moduleName, true);
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
		IEditorPart editorPart = openModuleEditor(moduleName, isDiagram);
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		workbenchPage.saveEditor(editorPart, false);
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
				EObject eobj = ModelUtil.getModel(file);
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
		EObject templateModel = ModelUtil.getModel(file);
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
		String respStr = node.getUserdef012();
		if (respStr != null && respStr.length() > 0) {
			String respArray[] = respStr.split(RESPONSIBILITY_SEPARATOR_NAME);
			if (respArray.length > index) {
				return respArray[index];
			}
		}
		return ""; //$NON-NLS-1$
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
	 * Returns the responsibility value.
	 * 
	 * @param argument
	 *            the argument.
	 * @return the responsibility value.
	 */
	public static String getResponsibilityValue(Argument argument, int index) {
		String respStr = argument.getUserdef012();
		if (respStr != null && respStr.length() > 0) {
			String respArray[] = respStr.split(RESPONSIBILITY_SEPARATOR_NAME);
			if (respArray.length > index) {
				return respArray[index];
			}
		}
		return ""; //$NON-NLS-1$
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
			String str1 = node1.getUserdef012();
			String str2 = node2.getUserdef012();
			if (str1 != null && str1.length() > 0 && str2 != null
					&& str2.length() > 0) {
				ret = RESPONSIBILITY_SEPARATOR_NAME
						+ (str1.equals(str2) ? CONTRACT_FLAG_NAME
								: RESPONSIBILITY_CONTRACT_FLAG_NAME);
			}
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

}
