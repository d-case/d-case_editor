/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.util.List;

import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The dialog class to edit bookmark for attachment selection.
 */
public class BookmarkEditDialog extends Dialog {

    /**
     * the dialog width.
     */
    private static final int INIT_WIDTH = 300;
    
    /**
     * column number.
     */
    private static final int GRID_COLUMNS = 2;
    
    /**
     * the text width.
     */
    private static final int TEXT_WIDTH = 250;
    
    /**
     * the create mode. 
     */
    public static final String CREATE = "create"; //$NON-NLS-1$
    
    /**
     * the edit mode.
     */
    public static final String EDIT = "edit"; //$NON-NLS-1$
    
    /**
     * a current mode.
     */
    private final String mode;
    
    /**
     * the selected bookmark when mode is edit.
     */
    private BookmarkItem selected;
    
    /**
     * the list of the bookmarks.
     */
    private final List<BookmarkItem> bookmarkList;
    
    /**
     * the text control to edit bookmak name.
     */
    private Text bookmarkNameText;
    
    /**
     * the text control to edit bookmark url.
     */
    private Text urlText;
   
    /**
     * constructor.
     * 
     * @param parentShell shell.
     * @param mode create or edit.
     * @param bookmarkList the list of bookmarks.
     */
    public BookmarkEditDialog(Shell parentShell, List<BookmarkItem> bookmarkList, String mode) {
        super(parentShell);
        this.bookmarkList = bookmarkList;
        this.mode = mode;
    }
    
    /**
     * Returns dialog size.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     * @return Dialog size.
     */
    @Override
    protected Point getInitialSize() {
        Point size = super.getInitialSize();
        if (size.x < INIT_WIDTH) {
            size.x = INIT_WIDTH;
        }
        return size;
    }
    
    /**
     * Creates dialog area.
     * @param parent parent
     * @return Control
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite panel = (Composite) super.createDialogArea(parent);
        
        // creates a grid layout.
        ((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;
        
        //
        if (CREATE.equals(mode)) {
            getShell().setText(Messages.BookmarkEditDialog_CreateDialogTile); //$NON-NLS-1$
        } else if (EDIT.equals(mode)) {
            getShell().setText(Messages.BookmarkEditDialog_EditDialogTitle); //$NON-NLS-1$
        }
        createDialogControl(panel);
        
        return panel;
    }
    
    /**
     * Creates dialog control.
     * 
     * @param parent parent
     */
    private void createDialogControl(Composite parent) {

        //Name
        Label label = new Label(parent, SWT.WRAP);
        label.setText(Messages.BookmarkEditDialog_NameLabelText); //$NON-NLS-1$
        
        bookmarkNameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        if (EDIT.equals(mode)) {
            bookmarkNameText.setText(selected.getName());
        }
        GridData requirementIdData = new GridData();
        requirementIdData.horizontalAlignment = GridData.FILL;
        requirementIdData.widthHint = TEXT_WIDTH;
        bookmarkNameText.setLayoutData(requirementIdData);

        //Requirement
        Label labe2 = new Label(parent, SWT.WRAP);
        labe2.setText(Messages.BookmarkEditDialog_UrlLabelText); //$NON-NLS-1$
        
        urlText = new Text(parent, SWT.SINGLE | SWT.BORDER);
        if (EDIT.equals(mode)) {
            urlText.setText(selected.getUrl());
        }
        GridData requirementTextdata = new GridData();
        requirementTextdata.horizontalAlignment = GridData.FILL;
        requirementTextdata.widthHint = TEXT_WIDTH;
        urlText.setLayoutData(requirementTextdata);
    }

    /**
     * In the case of edit, sets the selected bookmark.
     * In the case of creation, adds new bookmark to the list. 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        
        if (EDIT.equals(mode)) {
            
            selected.setName(bookmarkNameText.getText());
            selected.setUrl(urlText.getText());
            
        } else if (CREATE.equals(mode)) {

            BookmarkItem newBookmark = new BookmarkItem();
            newBookmark.setName(bookmarkNameText.getText());
            newBookmark.setUrl(urlText.getText());
            bookmarkList.add(newBookmark);
 
        }
        super.okPressed();
    }

    /**
     * Returns bookmark list.
     * @return the requirement
     */
    public List<BookmarkItem> getBookmarkList() {
        return bookmarkList;
    }
    
    /**
     * Sets selected bookmark in the case when mode is edit.
     * @param bookmark selected bookmark.
     */
    public void setSelected(BookmarkItem bookmark) {
        this.selected = bookmark;
    }
}
