/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.ui.ModuleViewPart;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.util.FileUtil;
import org.eclipse.osgi.util.NLS;

/**
 * An action to delete a module file.
 */
public class ModuleDeleteAction extends Action {

	/**
	 * the module view part.
	 */
	private ModuleViewPart viewPart;

	/**
	 * Creates the action and initializes it.
	 * 
	 * @param viewPart
	 *            the module view part.
	 */
	public ModuleDeleteAction(ModuleViewPart viewPart) {
		super();
		this.viewPart = viewPart;
	}

	/**
	 * Deletes the module file.
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		String moduleName = viewPart.getSelectedModuleFile();
		if (ModuleUtil.isPublicNodeName(moduleName)) {
			return;
		}
		IPath diagramPath = ModuleUtil.getDiagramPath(moduleName);
		IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(diagramPath);
		IPath modelPath = ModuleUtil.getModelPath(moduleName);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(modelPath);
		EObject eobj = ModelUtil.getModel(modelFile, true);
		// check reference number
		if (eobj instanceof Argument) {
			String refStr = ((Argument) eobj).getRefSource();
			if (ModuleUtil.getReferenceNumber(refStr) > 0) {
				MessageWriter.writeMessageToConsole(
						NLS.bind(Messages.ModuleDeleteAction_2, moduleName),
						MessageTypeImpl.DELETE_MODULE_FAILED);
				return;
			}
			String publicFlagStr = ModuleUtil.getPublicFlagString();
			for (BasicNode node : ((Argument) eobj).getRootBasicNode()) {
				String flagStr = node.getFlag();
				refStr = node.getRefSource();
				if (flagStr == null || flagStr.length() == 0) {
					continue;
				}
				if (flagStr.indexOf(publicFlagStr) >= 0 && refStr != null
						&& ModuleUtil.getReferenceNumber(refStr) > 0) {
					MessageWriter.writeMessageToConsole(NLS.bind(
							Messages.ModuleDeleteAction_2,
							ModuleUtil.createNodeReference(moduleName,
									node.getName())),
							MessageTypeImpl.DELETE_MODULE_FAILED);
					return;
				}
			}
		}
		try {
			FileUtil.deleteFile(diagramFile, new NullProgressMonitor());
		} catch (CoreException e) {
			MessageWriter.writeMessageToConsole(
					NLS.bind(Messages.ModuleDeleteAction_0,
							diagramFile.getName()),
					MessageTypeImpl.DELETE_MODULE_FAILED);
			return;
		}
		try {
			FileUtil.deleteFile(modelFile, new NullProgressMonitor());
		} catch (CoreException e) {
			MessageWriter.writeMessageToConsole(NLS.bind(
					Messages.ModuleDeleteAction_1, modelFile.getName()),
					MessageTypeImpl.DELETE_MODULE_FAILED);
			return;
		}
	}

}
