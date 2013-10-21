/*
 * Copyright (C) 2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.ui.editpolicies;

import net.dependableos.dcase.BasicLink;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * An open edit policy for a basic node.
 */
public class BasicLinkOpenEditPolicy extends OpenEditPolicy {

	/**
	 * the command name.
	 */
	//private static final String COMMAND_NAME = "Set Attribute"; //$NON-NLS-1$

	/**
	 * Returns a command to open diagram.
	 * 
	 * @param request
	 *            the request
	 * @return command to open diagram
	 */
	@Override
	protected Command getOpenCommand(Request request) {
		// get link
		EditPart editPart = getTargetEditPart(request);
		if (!(editPart instanceof ConnectionNodeEditPart)) {
			editPart = editPart.getParent();
			/* for DcaseLink003Userdef001Desc */
			if (!(editPart instanceof ConnectionNodeEditPart)) {
				return null;
			}
		}
		Object model = editPart.getModel();
		if (!(model instanceof View)) {
			return null;
		}
		BasicLink link = (BasicLink) ((View) model).getElement();
		String moduleName = link.getAttachment();
		// truncate icon string...
		if (moduleName != null && moduleName.length() > 0) {
			String moduleArray[] = moduleName.split(";"); //$NON-NLS-1$
			if (moduleArray.length > 1) {
				moduleName = moduleArray[0];
			}
		}
		if (moduleName == null || moduleName.length() == 0) {
			return null;
		}

		// get module file
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		XMLResource resource = (XMLResource) link.eResource();
		IFile modelFile = WorkspaceSynchronizer.getFile(resource);
		IPath diagramPath = modelFile.getParent().getFullPath()
				.append(moduleName).addFileExtension("dcase_diagram"); //$NON-NLS-1$
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile diagramFile = root.getFile(new Path(diagramPath.toOSString()));

		// open diagram
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(diagramFile.getName());
		if (desc != null) {
			try {
				workbenchPage.openEditor(new FileEditorInput(diagramFile),
						desc.getId());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return null;
	}

}