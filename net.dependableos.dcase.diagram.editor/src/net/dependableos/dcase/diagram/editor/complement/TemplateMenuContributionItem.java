/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;

import static org.eclipse.swt.SWT.PUSH;

import java.util.List;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * This class provides menu items those represent candidates of templates to complement for the selected node.
 */
public class TemplateMenuContributionItem extends ContributionItem {

    /**
     * Creates the instance of this class and initializes.
     */
    public TemplateMenuContributionItem() {
        super();
    }

    @Override
    public void fill(Menu menu, int index) {
        try {
            EObject eObj = DcaseEditorUtil.getSelectedObject();

            if (eObj != null) {
                if (eObj instanceof BasicNode) {
                    NodeInfo nodeInfo = ModelUtil
                            .createNodeInfo((BasicNode) eObj);
                    List<ComplementItem> items = ComplementManager
                            .getInstance().getItems(nodeInfo);

                    for (ComplementItem item : items) {
                        MenuItem menuItem = new MenuItem(menu, PUSH);
                        menuItem.setText(item.getSubject());
                        menuItem
                                .addSelectionListener(new TemplateSelectionListener(
                                        item));

                    }

                }
            }

        } catch (DcaseRuntimeException dcaseRuntimeException) {
            MessageWriter.writeMessageToProblemsView(dcaseRuntimeException);
        } catch (DcaseSystemException dcaseSystemException) {
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
        }

    }

    @Override
    public final boolean isDynamic() {
        return true;
    }

}
