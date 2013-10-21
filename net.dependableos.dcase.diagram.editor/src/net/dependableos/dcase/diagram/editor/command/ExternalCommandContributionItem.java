/*
 * Copyright (C) 2013  The University of Electro-Communications All rights reserved.
 * Copyright (C) 2013  AXE,Inc.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.EXTERNAL_COMMAND_PROJECT_NAME;
import static org.eclipse.swt.SWT.PUSH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class ExternalCommandContributionItem extends ContributionItem {

	/**
	 * the name of the template project.
	 */
	private static final String CONST_EXTERNAL_COMMAND_PROJECT_NAME = PropertyUtil
			.getSystemProperty(EXTERNAL_COMMAND_PROJECT_NAME);

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem.
	 */
	public ExternalCommandContributionItem() {
		super();
	}

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem and
	 * initialize it.
	 * 
	 * @param id
	 *            the contribution item identifier, or null.
	 */
	public ExternalCommandContributionItem(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Menu menu, int index) {
		IProject commandProject;
		try {
			commandProject = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(CONST_EXTERNAL_COMMAND_PROJECT_NAME);

			// tests whether the project is accessible.
			if (!commandProject.isAccessible()) {
				return;
			}
		} catch (DcaseSystemException dse) {
			dse.printStackTrace();
			return;
		}

		// get templates.
		ArrayList<IFile> list = new ArrayList<IFile>();
		try {
			getCommandFiles(commandProject, list);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		// add to menus.
		for (IFile file : list) {
			MenuItem item = new MenuItem(menu, PUSH);
			String fname = file.getFullPath().removeFileExtension()
					.toOSString();
			String separator = System.getProperty("file.separator"); //$NON-NLS-1$
			item.setText(fname.substring(fname.indexOf(separator,
					fname.indexOf(separator) + 1) + 1));
			AddPatternSelectionAdapter adapter = new AddPatternSelectionAdapter();
			adapter.setEntry(file);
			item.addSelectionListener(adapter);
		}
	}

	/**
	 * Returns the pattern file list
	 * 
	 * @param topResource
	 * @param list
	 * @throws CoreException
	 */
	private void getCommandFiles(IResource topResource, ArrayList<IFile> list)
			throws CoreException {
		IResource[] resources;
		if (topResource.getType() == IResource.PROJECT) {
			resources = ((IProject) topResource).members();
		} else if (topResource.getType() == IResource.FOLDER) {
			resources = ((IFolder) topResource).members();
		} else {
			return;
		}
		for (IResource res : resources) {
			switch (res.getType()) {
			case IResource.FILE:
				if (!res.getName().startsWith(".")) { //$NON-NLS-1$
					list.add((IFile) res);
				}
				break;
			case IResource.FOLDER:
				getCommandFiles((IFolder) res, list);
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDynamic() {
		return true;
	}

	/**
	 * Adapter class for Selecting from Modules.
	 */
	private class AddPatternSelectionAdapter extends SelectionAdapter {
		/**
		 * The template file.
		 */
		private IFile entry;

		/**
		 * Sets the selected template of public node.
		 * 
		 * @param entry
		 */
		public void setEntry(IFile entry) {
			this.entry = entry;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {
			ArgumentEditPart argumentEditPart = DcaseEditorUtil
					.getCurrentArgumentEditPart();
			IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
			String cmd = entry.getLocation().toOSString() + " " + //$NON-NLS-1$
					modelFile.getLocation().toOSString();
			Runtime r = Runtime.getRuntime();
			try {
				Process proc = r.exec(cmd);
				// close stdin,tdout
				proc.getOutputStream().close();
				proc.getInputStream().close();
				// output stderr to console
				InputStream est = proc.getErrorStream();
				BufferedReader breader = new BufferedReader(
						new InputStreamReader(est));
				while (true) {
					String mess = breader.readLine();
					if (mess != null) {
						MessageWriter.writeMessageToConsole(mess,
								MessageTypeImpl.DIAGNOSIS);
					} else {
						break;
					}
				}
				breader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
