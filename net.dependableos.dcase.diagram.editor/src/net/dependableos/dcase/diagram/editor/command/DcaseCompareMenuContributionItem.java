/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A ContributionItem that provides menus for D-Case compare operations.
 */
public class DcaseCompareMenuContributionItem extends ContributionItem {

    /**
     * Fills the parent menu with items those provide method for file operations.
     * 
     * @param menu the parent menu.
     * @param index the index where the controls are inserted, or -1 to insert at the end.
     */
    @Override
    public void fill(Menu menu, int index) {
        // adds "Compare To..." menu item.
        MenuItem compareFileItem = new MenuItem(menu, SWT.PUSH);
        compareFileItem.setText(Menus.DcaseFileMenuContributionItem_2);

        // adds "Switch Source and Target" menu item.
        MenuItem switchSTFileItem = new MenuItem(menu, SWT.PUSH);
        switchSTFileItem.setText(Menus.DcaseFileMenuContributionItem_3);

        // Sets menu enable or disable.
        
        DcaseDiagramEditor currentDcaseEditor = DcaseEditorUtil
                .getCurrentDcaseEditor();
        if (currentDcaseEditor == null) {
            compareFileItem.setEnabled(false);
            switchSTFileItem.setEnabled(false);
        } else {
            compareFileItem
                    .addSelectionListener(new CompareDcaseFileSelectionAdapter());
            IFile compareTargetFile = currentDcaseEditor.getCompareTargetFile();
            if (compareTargetFile != null) {
                switchSTFileItem
                        .addSelectionListener(new SwitchSourceAndTargetSelectionAdapter(
                                compareTargetFile));
            } else {
                switchSTFileItem.setEnabled(false);
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
}
