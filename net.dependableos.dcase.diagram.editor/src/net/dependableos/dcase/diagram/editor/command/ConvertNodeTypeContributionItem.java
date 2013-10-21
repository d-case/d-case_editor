/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.model.NodeType.ARGUMENT;
import static org.eclipse.swt.SWT.PUSH;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A contribution item that provides menus to convert node type.
 */
public class ConvertNodeTypeContributionItem extends ContributionItem {

    /**
     * Fills the parent menu with items those provide method to convert different node types.
     * 
     * @param menu the parent menu.
     * @param index the index where the controls are inserted, or -1 to insert at the end.
     */
    @Override
    public void fill(Menu menu, int index) {

        // gets selected node.
        EObject eSelectedObj = DcaseEditorUtil.getSelectedObject();

        if (eSelectedObj != null) {
            if (eSelectedObj instanceof BasicNode) {
                // gets type of selected node.
                NodeType selectedType = NodeType
                        .getNodeType((BasicNode) eSelectedObj);

                // creates menu items for all node types except the type of selected node.
                for (NodeType dispNodeType : NodeType.values()) {
                    if (!selectedType.getName().equals(dispNodeType.getName())
                            && !dispNodeType.getName().equals(
                                    ARGUMENT.getName())) {

                        MenuItem item = new MenuItem(menu, PUSH);
                        item.setText(Menus.getMenuName(dispNodeType));

                        ConvertNodeTypeSelectionListener listener = new ConvertNodeTypeSelectionListener(
                                dispNodeType, (BasicNode) eSelectedObj);

                        item.addSelectionListener(listener);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isDynamic() {
        return true;
    }

}
