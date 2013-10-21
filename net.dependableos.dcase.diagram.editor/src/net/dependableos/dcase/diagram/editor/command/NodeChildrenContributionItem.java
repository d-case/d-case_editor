/*
 * Copyright (C) 2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static org.eclipse.swt.SWT.PUSH;

import java.util.List;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Menus;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class NodeChildrenContributionItem extends ContributionItem {

	/**
	 * Constructor for the class. Creates a NodeChildrenContributionItem.
	 */
	public NodeChildrenContributionItem() {
		super();
	}

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem and
	 * initialize it.
	 * 
	 * @param id
	 *            the contribution item identifier, or null.
	 */
	public NodeChildrenContributionItem(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Menu menu, int index) {
		DcaseNodeEditPart selectedEditPart = DcaseEditorUtil
				.getFirstCurrentSelectedPart();
		if (selectedEditPart == null) {
			return;
		}
		List list = selectedEditPart.getSourceConnections();
		if (list == null || list.size() == 0) {
			return;
		}

		for (int i = 0; i < 2; i++) {
			MenuItem item = new MenuItem(menu, PUSH);
			MenuSelectionAdapter adapter;
			if (i == 0) {
				item.setText(Menus.ShowChildren);
				adapter = new MenuSelectionAdapter(true);
			} else {
				item.setText(Menus.HideChildren);
				adapter = new MenuSelectionAdapter(false);
			}
			item.addSelectionListener(adapter);
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
	 * Adapter class for Show/Hide children.
	 */
	private class MenuSelectionAdapter extends SelectionAdapter {
		/**
		 * the flag of whether show or hide.
		 */
		private boolean isShow = false;

		/**
		 * Constructor for the class.
		 */
		public MenuSelectionAdapter(boolean isShow) {
			super();
			this.isShow = isShow;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {
			IHandler handler;
			if (isShow) {
				handler = new NodeChildrenShowHandler();
			} else {
				handler = new NodeChildrenHideHandler();
			}
			try {
				handler.execute(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
