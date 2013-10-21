/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;

import static org.eclipse.swt.SWT.PUSH;

import java.util.Map;
import java.util.Map.Entry;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.validator.NodeMultiplicity;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * This class provides menu items those represent candidates of nodes to complement for the selected node.
 */
public class NodeContributionItem extends ContributionItem {

    /**
     * Creates the instance of this class and initializes.
     */
    public NodeContributionItem() {
        super();
    }

    @Override
    public void fill(Menu menu, int index) {
        Map<NodeType, NodeMultiplicity> childMultiplicity = getChildMultiplicity();
        if (childMultiplicity != null) {
            for (Entry<NodeType, NodeMultiplicity> entry : childMultiplicity
                    .entrySet()) {

                if (entry.getValue() != NodeMultiplicity.ZERO) {
                    NodeType nodeType = entry.getKey();
                    MenuItem item = new MenuItem(menu, PUSH);
                    item.setText(Menus.getMenuName(nodeType));
                    item.addSelectionListener(new NodeSelectionListener(
                            nodeType));
                }
            }
        }

    }

    /**
     * Returns the connection rule for the selected node.
     * 
     * @return the connection rule for the selected node.
     */
    private Map<NodeType, NodeMultiplicity> getChildMultiplicity() {

        EObject eObj = DcaseEditorUtil.getSelectedObject();

        if (eObj != null) {
            if (eObj instanceof BasicNode) {
                NodeType type = NodeType.getNodeType((BasicNode) eObj);
                return type.getNodeValidatorRule().getChildMultiplicity();
            }
        }
        return null;

    }

    @Override
    public final boolean isDynamic() {
        return true;
    }

}
