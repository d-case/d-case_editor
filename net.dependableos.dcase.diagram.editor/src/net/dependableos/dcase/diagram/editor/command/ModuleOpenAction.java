/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.editor.ui.ModuleViewPart;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;

import org.eclipse.jface.action.Action;

/**
 * An action to open a module file.
 */
public class ModuleOpenAction extends Action {

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
	public ModuleOpenAction(ModuleViewPart viewPart) {
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
		ModuleUtil.openModuleEditor(moduleName);
	}

}
