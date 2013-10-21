/*
 * Copyright (C) 2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import org.eclipse.swt.widgets.Menu;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class CopyModuleContributionItem extends SelectModuleContributionItem {
	/**
	 * Constructor for the class. Creates a CopyModuleContributionItem.
	 */
	public CopyModuleContributionItem() {
		super();
	}

	/**
	 * Constructor for the class. Creates a CopyModuleContributionItem and
	 * initialize it.
	 * 
	 * @param id
	 *            the contribution item identifier, or null.
	 */
	public CopyModuleContributionItem(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Menu menu, int index) {
		super.fill(menu, index, true);
	}

}
