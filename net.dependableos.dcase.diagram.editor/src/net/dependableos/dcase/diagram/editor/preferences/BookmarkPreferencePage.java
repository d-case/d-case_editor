/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.preferences;

import java.util.List;

import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.ui.BookmarkEditDialog;
import net.dependableos.dcase.diagram.editor.ui.BookmarkItem;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * A preference page class for the bookmarks.
 */
public class BookmarkPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * the width of the button.
     */
    private static final int BUTTON_WIDTH = 80;

    /**
     * the number of columns.
     */
    private static final int COLUMN_NUMBER = 2;

    /**
     * the height of dialog.
     */
    private static final int HEIGHT_HINT = 200;

    /**
     * the vertical span.
     * the table occupy the 7 rows to represent the operation buttons.
     */
    private static final int VERTICAL_SPAN = 7;

    /**
     * a list control for bookmark items.
     */
    private org.eclipse.swt.widgets.List bookmarkList;

    /**
     * a list of bookmark items.
     */
    private final List<BookmarkItem> bookmarkItems;

    /**
     * the selected Bookmark item.
     */
    private BookmarkItem selectedBookmarkItem = null;

    /**
     * Constructor.
     */
    public BookmarkPreferencePage() {
        super();
        this.bookmarkItems = BookmarkItem.getListFromPreferrence();
    }
    
    /**
     * Initializes this preference page for the given workbench.
     * 
     * @param workbench the workbench
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // suppresses creation of the standard Default and Apply buttons
        noDefaultAndApplyButton();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        // configure GridLayout.
       
        GridLayout layout = new GridLayout();
        layout.numColumns = COLUMN_NUMBER;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        parent.setLayout(layout);
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));
        parent.setFont(parent.getFont());
        
        //creates the list.
        initializeBookmarkList(parent);
        //creates the operation buttons.
        createOperationButton(parent);
        
        return parent;
    }
    
    /**
     * Configures the select list.
     * 
     * @param panel parent.
     */
    private void initializeBookmarkList(Composite panel) {
        if (bookmarkList == null) {
            bookmarkList = new org.eclipse.swt.widgets.List(
                    panel, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
            GridData gridData = new GridData(GridData.FILL_BOTH);
            gridData.grabExcessVerticalSpace = true;
            gridData.verticalSpan = VERTICAL_SPAN;
            gridData.heightHint = HEIGHT_HINT;
            bookmarkList.setLayoutData(gridData);
        }
        bookmarkList.removeAll();
        String[] items = new String[bookmarkItems.size()];
        for (int i = 0; i < bookmarkItems.size(); i++) {
            items[i] = bookmarkItems.get(i).getName();
        }
        bookmarkList.setItems(items);
        if (selectedBookmarkItem != null) {
            for (int i = 0; i < bookmarkItems.size(); i++) {
                if (selectedBookmarkItem.equals(bookmarkItems.get(i))) {
                    bookmarkList.select(i);
                    break;
                }
            }
        }
        bookmarkList.redraw();
    }

    /**
     * Saves the bookmarks into the preferences.
     * @return  <code>false</code> to abort the container's OK
     *  processing and <code>true</code> to allow the OK to happen
     */
    @Override
    public boolean performOk() {
        BookmarkItem.saveToPreferrence(bookmarkItems);
        return super.performOk();
    }

    /**
     * Creates buttons.
     * 
     * @param panel parent composite.
     */
    private void createOperationButton(final Composite panel) {
        // creates the creation button.
        Button addButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        addButton.setText(Messages.BookmarkPreferencePage_0);
        GridData createGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        createGridData.widthHint = BUTTON_WIDTH;
        addButton.setLayoutData(createGridData);
        addButton.addSelectionListener(new SelectionAdapter() {
            // the event method executed when creation button is pushed.
            public void widgetSelected(SelectionEvent e) {
                BookmarkEditDialog dialog = 
                    new BookmarkEditDialog(panel.getShell(), bookmarkItems, BookmarkEditDialog.CREATE);
                if (dialog.open() == Dialog.OK) {
                    initializeBookmarkList(panel);
                }
            }
        });
        // creates the edit button.
        Button editButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.BookmarkPreferencePage_1);
        GridData editGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        editGridData.widthHint = BUTTON_WIDTH;
        editButton.setLayoutData(editGridData);
        editButton.addSelectionListener(new SelectionAdapter() {
            // the event method executed when the edit button is pushed.
            public void widgetSelected(SelectionEvent e) {
                BookmarkItem bookmark = getSelectedBookmarkItem();
                if (bookmark != null) {
                    BookmarkEditDialog dialog = 
                        new BookmarkEditDialog(panel.getShell(), bookmarkItems, BookmarkEditDialog.EDIT);
                    dialog.setSelected(bookmark);
                    if (dialog.open() == Dialog.OK) {
                        selectedBookmarkItem = bookmark;
                        initializeBookmarkList(panel);
                    }
                }
            }
        });

        // creates the deletion button.
        Button delButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        delButton.setText(Messages.BookmarkPreferencePage_2);
        GridData delGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        delGridData.widthHint = BUTTON_WIDTH;
        delButton.setLayoutData(delGridData);
        delButton.addSelectionListener(new SelectionAdapter() {
            // the event method executed when the deletion button is pushed.
            public void widgetSelected(SelectionEvent e) {
                BookmarkItem item = getSelectedBookmarkItem();
                bookmarkItems.remove(item);
                initializeBookmarkList(panel);
            }
        });
        
        Label blankLabel = new Label(panel, SWT.NONE);
        blankLabel.setText(""); //$NON-NLS-1$
    }
    
    /**
     * Returns the selected bookmark item.
     * @return the selected bookmark item.
     */
    private BookmarkItem getSelectedBookmarkItem() {
        int index = bookmarkList.getSelectionIndex();
        if (index >= 0) {
            return bookmarkItems.get(index);
        } else {
            return null;
        }
    }

}
