/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.part;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_GMF_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DSTAR_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MODEL_DSTAR_FILE_EXTENSION;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcaseLink002;
import net.dependableos.dcase.System;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.NumberUtil;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A utility class that handles patterns.
 */
public final class PatternUtil {

	/**
     * the key for the text of the Pattern SubType.
     */
	private static final String SUBTYPE_KEYS[] = {
        "_UI_System_subType_param", //$NON-NLS-1$
        "_UI_System_subType_loop", //$NON-NLS-1$
        "_UI_System_subType_choice", //$NON-NLS-1$
        "_UI_System_subType_multi", //$NON-NLS-1$
	};
	
	/**
	 * the index of Subtype.
	 */
	private static final int SUBTYPE_KEY_PARAM = 0;
	private static final int SUBTYPE_KEY_LOOP = 1;
	private static final int SUBTYPE_KEY_CHOICE = 2;
	private static final int SUBTYPE_KEY_MULTI = 3;
	private static final int SUBTYPE_NR = 4;
	
	/**
	 * the subtype names.
	 */
    private static String subTypeNames[] = new String[SUBTYPE_NR];
    
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
	 * the separator string of responsibility.
	 */
	private static final String RESPONSIBILITY_SEPARATOR_NAME = ";"; //$NON-NLS-1$

	/**
	 * A constructor.
	 */
	private PatternUtil() {
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
	 * Returns the main module name.
	 * 
	 * @return the main module name.
	 */
	public static String getMainModuleName() {
		return MAIN_MODULE_NAME;
	}

	/**
	 * Returns the responsibility separator name.
	 * 
	 * @return the responsibility separator name.
	 */
	public static String getResponsibilitySeparatorName() {
		return RESPONSIBILITY_SEPARATOR_NAME;
	}

	/**
	 * Returns the subtype name of Parameter.
	 * @return the subtype name of Parameter.
	 */
	public static String getParameterName() {
		return getSubtypeName(SUBTYPE_KEY_PARAM);
	}
	
	/**
	 * Returns the subtype name of Loop.
	 * @return the subtype name of Loop.
	 */
	public static String getLoopName() {
		return getSubtypeName(SUBTYPE_KEY_LOOP);
	}
	
	/**
	 * Returns the subtype name of Choice.
	 * @return the subtype name of Choice.
	 */
	public static String getChoiceName() {
		return getSubtypeName(SUBTYPE_KEY_CHOICE);
	}
	
	/**
	 * Returns the subtype name of Multiplicity.
	 * @return the subtype name of Multiplicity.
	 */
	public static String getMultiplicityName() {
		return getSubtypeName(SUBTYPE_KEY_MULTI);
	}
	
	/**
	 * Returns the subtype name.
	 * @param index the index of key array.
	 * @return the subtype name.
	 */
	private static String getSubtypeName(int index) {
		if (index < 0 || index >= SUBTYPE_NR) {
			return null;
		}
		if (subTypeNames[index] == null) {
			subTypeNames[index] = DcaseEditPlugin.getPlugin().getString(SUBTYPE_KEYS[index]);
		}
		return subTypeNames[index];
	}
	
	/**
	 * Returns the Pattern subtype names.
	 * @return the Pattern subtype names.
	 */
	public static String[] getSubtypeNames() {
		for (int i = 0; i < subTypeNames.length; i++) {
			getSubtypeName(i);
		}
		return subTypeNames;
	}
	
	/**
	 * Whether the subtype name is Parameter.
	 * @param subtype the subtype name.
	 * @return whether the subtype name is Parameter or not.
	 */
	public static boolean isParameter(String subtype) {
		if (subtype == null) {
			return false;
		}
		return subtype.equals(getParameterName());
	}
	
	/**
	 * Whether the subtype name is Loop.
	 * @param subtype the subtype name.
	 * @return whether the subtype name is Loop or not.
	 */
	public static boolean isLoop(String subtype) {
		if (subtype == null) {
			return false;
		}
		return subtype.equals(getLoopName());
	}
	
	/**
	 * Whether the subtype name is Choice.
	 * @param subtype the subtype name.
	 * @return whether the subtype name is Choice or not.
	 */
	public static boolean isChoice(String subtype) {
		if (subtype == null) {
			return false;
		}
		return subtype.equals(getChoiceName());
	}
	
	/**
	 * Whether the subtype name is Multiplicity.
	 * @param subtype the subtype name.
	 * @return whether the subtype name is Multiplicity or not.
	 */
	public static boolean isMultiplicity(String subtype) {
		if (subtype == null) {
			return false;
		}
		return subtype.equals(getMultiplicityName());
	}
	
	/**
	 * Whether Pattern node is valid or not.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @return whether Pattern node is valid or not.
	 */
	public static boolean isValid(BasicNode node, Argument argument) {
		// check node type.
		if (! (node instanceof System)) {
			return false;
		}
		System snode = (System)node;
		String subtype = snode.getSubType();
		
		// check parent is only one.
		BasicNode parent = getParent(node, argument);
		if (parent == null) {
			return false;
		}
		// OK if Parameter.
		if (isParameter(subtype)) {
			return true;
		}

		// check any other Pattern nodes at children of parent.
		List<BasicNode>childList = getChildren(parent, argument, false);
		if (childList != null) {
			for (BasicNode subnode : childList) {
				if (node == subnode) {
					continue;
				}
				if (subnode instanceof System) {
					String sstype = ((System)subnode).getSubType();
					if (isLoop(sstype) || isChoice(sstype) || isMultiplicity(sstype)) {
						return false;
					}
				}
			}
		}
		
		// check if Loop.
		if (isLoop(subtype)) {
			String leafNode = snode.getLeafNode();
			if (leafNode == null) {
				return false;
			}
			List<BasicNode>leafList = getLeafNodes(node, argument);
			boolean isFound = false;
			for (BasicNode lnode : leafList) {
				if (leafNode.equals(lnode.getName())) {
					isFound = true;
					break;
				}
			}
			if (! isFound) {
				return false;
			}
		}
		// check if Choice.
		if (isChoice(subtype)) {
			int n = getChildNr(node, argument);
			if (snode.getI() <= 0 || snode.getI() > snode.getJ() ||
					snode.getI() > n || snode.getJ() > n) {
				return false;
			}
		}
		// check if Multiplicity
		if (isMultiplicity(subtype)) {
			if (getChildNr(node, argument) != 1) {
				return false;
			}
			if (snode.getI() <= 0 || snode.getI() > snode.getJ()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns the parent node of the Pattern node.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @return the parent node of the Pattern node.
	 */
	public static BasicNode getParent(BasicNode node, Argument argument) {
		BasicNode parent = null;
		int nr = 0;
		for (BasicLink link : argument.getRootBasicLink()) {
			if (link instanceof DcaseLink002) {
				if (link.getTarget() == node) {
					parent = link.getSource();
					nr++;
				}
			}
		}
		return (nr == 1) ? parent:null;
	}
	
	/**
	 * Returns the child node number.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @return the child node number.
	 */
	public static int getChildNr(BasicNode node, Argument argument) {
		BasicNode parent = getParent(node, argument);
		int nr = 0;
		if (parent != null) {
			for (BasicLink link : argument.getRootBasicLink()) {
				if ((! (link instanceof DcaseLink002)) &&
						link.getSource() == parent) {
					nr++;
				}
			}
		}
		return nr;
	}
	
	/**
	 * Returns the INDEXth child nodex.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @param index the index. (1..*)
	 * @return the INDEXth child node.
	 */
	public static BasicNode getChild(BasicNode node, Argument argument, int index) {
		BasicNode parent = getParent(node, argument);
		if (parent == null) {
			return null;
		}
		// check siblingOrder
		for (BasicLink link : argument.getRootBasicLink()) {
			if ((! (link instanceof DcaseLink002)) && link.getSource() == parent) {
				String orderStr = link.getSiblingOrder();
				if (orderStr != null && NumberUtil.parseIntWithDefault(orderStr, 0) == index) {
					return link.getTarget();
				}
			}
		}
		int i = 0;
		for (BasicLink link : argument.getRootBasicLink()) {
			if ((! (link instanceof DcaseLink002)) && link.getSource() == parent) {
				if (++i == index) {
					return link.getTarget();
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the ordered list of children.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 */
	public static List<BasicNode> getChildren(BasicNode node, Argument argument) {
		return getChildren(node, argument, true);
	}
	
	/**
	 * Returns the ordered list of children.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @param isPattern whether node is Pattern or not.
	 */
	public static List<BasicNode> getChildren(BasicNode node, Argument argument,
			boolean isPattern) {
		BasicNode parent;
		if (isPattern) {
			parent = getParent(node, argument);
		} else {
			parent = node;
		}
		if (parent == null) {
			return null;
		}
		LinkedList<Integer> orderList = new LinkedList<Integer>();
		LinkedList<BasicNode> retList = new LinkedList<BasicNode>();
		for (BasicLink link : argument.getRootBasicLink()) {
			if (link.getSource() == parent) {
				if (isPattern && (link instanceof DcaseLink002)) {
					continue;
				}
				// check siblingOrder
				String orderStr = link.getSiblingOrder();
				int n = NumberUtil.parseIntWithDefault(orderStr, Integer.MAX_VALUE);
				int i;
				for (i = 0; i < orderList.size(); i++) {
					if (orderList.get(i) > n) {
						orderList.add(i, n);
						retList.add(i, link.getTarget());
						break;
					}
				}
				if (i == orderList.size()) {
					orderList.add(n);
					retList.add(link.getTarget());
				}
			}
		}
		return retList;
	}
	
	/**
	 * Gets the subtree.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @param nodeList the node list of subtree.
	 * @param linkList the link list of subtree.
	 */
	public static void getSubtree(BasicNode node, Argument argument,
			List<BasicNode>nodeList, List<BasicLink>linkList) {
		BasicNode parent = getParent(node, argument);
		if (parent == null) {
			return;
		}
		HashSet<BasicNode> checkedSet = new HashSet<BasicNode>();
		checkedSet.add(parent);
		nodeList.add(parent);
		for (BasicLink link : argument.getRootBasicLink()) {
			if ((! (link instanceof DcaseLink002)) && link.getSource() == parent) {
				if (linkList != null && ! linkList.contains(link)) {
					linkList.add(link);
				}
				getSubtree(link.getTarget(), argument, nodeList, linkList, checkedSet);
			}
		}
	}
	
	/**
	 * Gets the subtree.(called by getSubtree.)
	 * @param node the current node.
	 * @param argument the argument.
	 * @param nodeList the node list of subtree.
	 * @param linkList the link list of subtree.
	 * @param checkedSet the checked node set.
	 */
	public static void getSubtree(BasicNode node, Argument argument,
			List<BasicNode>nodeList, List<BasicLink>linkList, Set<BasicNode>checkedSet) {
		if (node == null || ! checkedSet.add(node)) {
			return;
		}
		if (! nodeList.contains(node)) {
			nodeList.add(node);
		}
		for (BasicLink link : argument.getRootBasicLink()) {
			if (link.getSource() == node) {
				if (linkList != null && ! linkList.contains(link)) {
					linkList.add(link);
				}
				getSubtree(link.getTarget(), argument, nodeList, linkList, checkedSet);
			}
		}
	}
	
	/**
	 * Return the leaf nodes.
	 * @param node the Pattern node.
	 * @param argument the argument.
	 * @return the leaf nodes list.
	 */
	public static List<BasicNode> getLeafNodes(BasicNode node, Argument argument) {
		ArrayList<BasicNode> nodeList = new ArrayList<BasicNode>();
		ArrayList<BasicLink> linkList = new ArrayList<BasicLink>();
		ArrayList<BasicNode> leafNodeList = new ArrayList<BasicNode>();
		getSubtree(node, argument, nodeList, linkList);
		for (BasicNode lnode : nodeList) {
			boolean isLeaf = true;
			for (BasicLink link : linkList) {
				if ((! (link instanceof DcaseLink002)) && link.getSource() == lnode) {
					isLeaf = false;
					break;
				}
				if (link instanceof DcaseLink002 && link.getTarget() == lnode &&
						lnode instanceof System) {
					isLeaf = false;
					break;
				}
			}
			if (isLeaf) {
				leafNodeList.add(lnode);
			}
		}
		return leafNodeList;
	}
	
	/**
	 * Returns the leaf node.
	 * @param leafName the leaf node name.
	 * @param node the Loop node.
	 * @param argument the argument.
	 * @return the leaf node.
	 */
	public static BasicNode getLeafNode(String leafName, BasicNode node, Argument argument) {
		if (leafName == null) {
			return null;
		}
		List<BasicNode>leafNodeList = getLeafNodes(node, argument);
		for (BasicNode lnode : leafNodeList) {
			if (leafName.equals(lnode.getName())) {
				return lnode;
			}
		}
		return null;
	}
	
	/**
	 * Returns the node of name.
	 * @param name the node name.
	 * @param nodeList the node list.
	 * @return the node of name.
	 */
	public static BasicNode getNode(String name, List<BasicNode>nodeList) {
		if (name == null) {
			return null;
		}
		for (BasicNode node : nodeList) {
			if (name.equals(node.getName())) {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Copy subtree.
	 * @param rootNode the original root node.
	 * @param nodeList the original node list.
	 * @param linkList the original link list.
	 * @param newNodeList the copied node list.
	 * @param newLinkList the copied link list.
	 * @return the new root node.
	 */
	public static BasicNode copySubtree(BasicNode rootNode,
			List<BasicNode> nodeList, List<BasicLink> linkList,
			List<BasicNode> newNodeList, List<BasicLink> newLinkList) {
		HashMap<BasicNode, BasicNode> matchMap = new HashMap<BasicNode, BasicNode>();
		BasicNode ret = null;
		// copy nodes.
		for (BasicNode node : nodeList) {
			BasicNode newNode = EcoreUtil.copy(node);
			newNodeList.add(newNode);
			matchMap.put(node, newNode);
			if (node == rootNode) {
				ret = newNode;
			}
		}
		// copy links.
		for (BasicLink link : linkList) {
			BasicLink newLink = EcoreUtil.copy(link);
			BasicNode srcNode = matchMap.get(link.getSource());
			BasicNode tgtNode = matchMap.get(link.getTarget());
			newLink.setSource(srcNode);
			newLink.setTarget(tgtNode);
			newLinkList.add(newLink);
		}
		return ret;
	}
	
	/**
	 * Removes all links connecting the node.
	 * @param node the node.
	 * @param linkList the link list.
	 */
	public static void removeLinks(BasicNode node, List<BasicLink> linkList) {
		ArrayList<BasicLink> delList = new ArrayList<BasicLink>();
		for (BasicLink link : linkList) {
			if (link.getSource() == node || link.getTarget() == node) {
				delList.add(link);
			}
		}
		linkList.removeAll(delList);
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
     * Checks whether the Attachment is module name or not.
     * @param name the attachment value.
     * @return the Attachment is module name or not.
     */
    public static boolean isModuleReference(String name) {
    	// null check
    	if(name == null || name.length() == 0) {
    		return false;
    	}
    	// check Workspace
    	if(isWorkspaceReference(name)) {
    		return false;
    	}
    	// check URL
    	try {
    		/*URL url = */new URL(name);
    	} catch(MalformedURLException e) {
    		return true;
    	}
    	return false;
    }

	/**
	 * Returns whether the node name or not.
	 * 
	 * @param name
	 *            the node name.
	 * @return whether the node name or not.
	 */
	public static boolean isPublicNodeName(String name) {
		if (name != null) {
			int len = name.length();
			if (name.lastIndexOf(MODULE_SEPARATOR_NAME) < len-1) {
				return true;
			}
		}
		return false;
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
	 * @param path
	 *            the diagram file or the model file.
	 * @return the module name.
	 */
	public static String getModuleName(IPath path) {
		if (path != null) {
			String absolutePath = path.removeFileExtension() + MODULE_SEPARATOR_NAME;
			return trimProjectName(absolutePath);
		}
		return null;
	}

	/**
	 * Returns the module name.
	 * 
	 * @param file
	 *            the diagram file or the model file.
	 * @return the module name.
	 */
	public static String getModuleName(IFile file) {
		return getModuleName(file.getFullPath());
	}

	/**
	 * Returns the module name.
	 * 
	 * @param name
	 *            the module name or node name.
	 * @return the module name.
	 */
	public static String getModuleName(String name) {
		if (name == null || name.length() == 0) {
			return null;
		}
		name = trimProjectName(name);
		int lindex = name.lastIndexOf(MODULE_SEPARATOR_NAME);
		if (lindex >= 0) {
			if (lindex < name.length()-1) {
				return name.substring(0, lindex+1); // include the last MODULE_SEPARATOR_NAME
			} else {
				return name; // maybe module name
			}
		}
		return name + MODULE_SEPARATOR_NAME; // for compatibility
	}
	
	/**
	 * Trims the project name.
	 * @param name the absolute path name.
	 * @return the trimming name.
	 */
	public static String trimProjectName(String name) {
		if (name.charAt(0) == IPath.SEPARATOR) {
			int index = name.indexOf(IPath.SEPARATOR, 1);
			if (index > 0) {
				name = name.substring(index+1);
			}
		}
		return name;
	}
	
	/**
	 * Returns the base name of the module name.
	 * @param name the module name.
	 * @return the base name of the module name.
	 */
	public static String getModuleBaseName(String name) {
		name = trimProjectName(name);
		if (name.lastIndexOf(MODULE_SEPARATOR_NAME) == name.length()-1) {
			name = name.substring(0, name.length()-1);
		}
		int lindex = name.lastIndexOf(MODULE_SEPARATOR_NAME);
		if (lindex >= 0) {
			return name.substring(lindex+1);
		} else {
			return name;
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
		int index = nodeName.lastIndexOf(MODULE_SEPARATOR_NAME);
		if (index >= 0) {
			if (index < nodeName.length()-1) {
				return nodeName.substring(index + 1);
			} else {
				// If module name only, return nodeName
			}
		}
		return nodeName;
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
		if (moduleName == null || moduleName.length() == 0) {
			return null;
		}
		if (MODULE_SEPARATOR_NAME.equals(moduleName.substring(moduleName.length()-1))) {
			return moduleName + nodeName;
		} else {
			return moduleName + MODULE_SEPARATOR_NAME + nodeName;
		}
	}

	/**
     * Returns the current diagram editor.
     * 
     * @return the current diagram editor.
     */
    public static DcaseDiagramEditor getCurrentDcaseEditor() {
        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
    	if(workbenchPage == null) {
    		return null;
    	}
        IEditorPart editorPart = workbenchPage.getActiveEditor();

        // returns null if there is no active D-Case diagram editor.
        if (editorPart == null || !(editorPart instanceof DcaseDiagramEditor)) {
            return null;
        }

        return (DcaseDiagramEditor) editorPart;
    }

    /**
     * Returns the current argument edit part.
     * 
     * @return the current argument edit part.
     */
    public static ArgumentEditPart getCurrentArgumentEditPart() {

        DcaseDiagramEditor dcaseEditor = getCurrentDcaseEditor();

        return getCurrentArgumentEditPart(dcaseEditor);
    }

    /**
     * Returns the argument edit part from the specified editor.
     * 
     * @param dcaseEditor the editor.
     * @return the argument edit part.
     */
    public static ArgumentEditPart getCurrentArgumentEditPart(
            DcaseDiagramEditor dcaseEditor) {

        if (dcaseEditor == null) {
            return null;
        }

        DiagramEditPart editPart = dcaseEditor.getDiagramEditPart();
        if (!(editPart instanceof ArgumentEditPart)) {
            return null;
        }

        return (ArgumentEditPart) editPart;
    }

    /**
     * Returns the EObject.
     * 
     * @param editPart the edit part.
     * @return the EObject.
     */
    public static EObject getElement(GraphicalEditPart editPart) {

        if (editPart == null) {
            return null;
        }

        Object model = editPart.getModel();
        if (!(model instanceof View)) {
            return null;
        }

        View view = (View) model;

        return view.getElement();
    }

    /**
     * Returns the containing resource, or null.
     * 
     * @param editPart the edit part.
     * @return the containing resource, or null.
     */
    public static XMLResource getXMLResource(GraphicalEditPart editPart) {

        EObject eObj = getElement(editPart);
        if (eObj == null) {
            return null;
        }

        return (XMLResource) eObj.eResource();
    }

    /**
     * Returns the IFile that represents model file that contains the specified edit part.
     * 
     * @param editPart the edit part.
     * @return the IFile that represents model file.
     */
    public static IFile getModelFile(GraphicalEditPart editPart) {

        XMLResource resource = getXMLResource(editPart);
        if (resource == null) {
            return null;
        }

        return getModelFile(resource);
    }

    /**
     * Returns the IFile that represents model file.
     * 
     * @param resource the resource
     * @return the IFile that represents model file.
     */
    public static IFile getModelFile(Resource resource) {

        return WorkspaceSynchronizer.getFile(resource);
    }
     
    /**
     * Returns the IPath that represents model/diagram file.
     * @param baseFile the base reference file.
     * @param moduleName the module name.
     * @param extension the extension of file.
     * @return the IPath that represents model/diagram file.
     */
    public static IPath getModulePath(IFile baseFile, String moduleName, String extension) {
    	if (moduleName == null || moduleName.length() == 0 ||
    			extension == null || extension.length() == 0) {
    		return null;
    	}
		IProject project;
		if (baseFile != null) {
			project = baseFile.getProject();
		} else {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject();
		}
		if (project != null) {
			// trim the last separater character.
			String baseName = MODULE_SEPARATOR_NAME.equals(moduleName.substring(moduleName.length()-1))
					? moduleName.substring(0, moduleName.length()-1) : moduleName;
			IPath basePath = project.getFullPath().addTrailingSeparator().append(baseName);
			return basePath.addFileExtension(extension);
		}
		return null;
    }

    /**
	 * Returns the Model file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the diagram file path.
	 */
    public static IPath getModelPath(String name) {
		ArgumentEditPart editPart = getCurrentArgumentEditPart();
		IFile modelFile = getModelFile(editPart);
		return getModulePath(modelFile, name, MODEL_FILE_EXTENSION_NAME);
    }
    
    /**
	 * Returns the Diagram file path.
	 * 
	 * @param name
	 *            the module name.
	 * @return the diagram file path.
	 */
	public static IPath getDiagramPath(String name) {
		ArgumentEditPart editPart = getCurrentArgumentEditPart();
		IFile modelFile = getModelFile(editPart);
		return getModulePath(modelFile, name, DIAGRAM_FILE_EXTENSION_NAME);
	}
	
	/**
	 * Returns the Diagram file path.
	 * @param baseFile the base reference file.
	 * @param name the module name.
	 * @return the Diagram file path.
	 */
	public static IPath getDiagramPath(IFile baseFile, String name) {
		return getModulePath(baseFile, name, DIAGRAM_FILE_EXTENSION_NAME);
	}

	/**
	 * Returns the d* Diagram file path.
	 * 
	 * @param file the diagram file or the model file.
	 * @return the d* diagram file path.
	 */
	public static IPath getDstarPath(IFile file) {
		IProject project;
		if (file != null) {
			project = file.getProject();
		} else {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject();
		}
		if (project != null) {
			IPath basePath = project.getFullPath().addTrailingSeparator().append(getMainModuleName());
			return basePath.addFileExtension(DSTAR_FILE_EXTENSION_NAME);
		}
		return null;
	}
	
	/**
	 * Returns the d* Diagram file path.
	 * 
	 * @return the d* diagram file path.
	 */
	public static IPath getDstarPath() {
		return getDstarPath(null);
	}

	/**
	 * Returns the d* Model file path.
	 * 
	 * @param file the diagram file or the model file.
	 * @return the d* model file path.
	 */
	public static IPath getDstarModelPath(IFile file) {
		IProject project;
		if (file != null) {
			project = file.getProject();
		} else {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject();
		}
		if (project != null) {
			IPath basePath = project.getFullPath().addTrailingSeparator().append(getMainModuleName());
			return basePath.addFileExtension(DSTAR_MODEL_FILE_EXTENSION_NAME);
		}
		return null;
	}

	/**
	 * Returns the d* Model file path.
	 * 
	 * @return the d* model file path.
	 */
	public static IPath getDstarModelPath() {
		return getDstarModelPath(null);
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
			diagramPath = getDiagramPath(moduleName);
		} else {
			diagramPath = getDstarPath();
		}
		if (diagramPath == null) {
			return null;
		}
		IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath
				.toOSString());
		return openModuleEditor(diagramFile);
	}
	
	/**
	 * Opens the module diagram.
	 * 
	 * @param diagramFile the diagram file.
	 * @return the opened module editor.
	 */
	public static IEditorPart openModuleEditor(IFile diagramFile) {
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
				e.printStackTrace();
				return null;
			}
		} else {
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
		if (editorPart != null) {
			IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			workbenchPage.saveEditor(editorPart, false);
		}
	}
	
	/**
	 * Saves the module diagram.
	 * 
	 * @param diagramFile the diagram file.
	 */
	public static void saveModuleEditor(IFile diagramFile) {
		IEditorPart editorPart = PatternUtil.openModuleEditor(diagramFile);
		if (editorPart != null) {
			IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			workbenchPage.saveEditor(editorPart, false);		}
	}

}
