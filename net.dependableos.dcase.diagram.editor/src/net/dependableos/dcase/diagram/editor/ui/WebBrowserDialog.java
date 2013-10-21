/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.util.List;

import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 * The web browser dialog class.
 */
public class WebBrowserDialog extends Dialog implements LocationListener {
    
    /**
     * the width of the dialog.
     */
    private static final int INIT_WIDTH = 800;
    
    /**
     * the width of the dialog.
     */
    private static final int INIT_HEIGHT = 800;
    
    /**
     * the text width.
     */
    private static final int TEXT_WIDTH = 250;

    /**
     * the number of columns.
     */
    private static final int COLUMN_NUMBER = 2;
    
    /**
     * the selected URL by user.
     */
    private String selectedUrl;
    
    /**
     * the option list of bookmarks.
     */
    private final List<BookmarkItem> bookmarkList;

    /**
     * the combo box to choice a bookmark.
     */
    private Combo bookmarkCombo;
    
    /**
     * the location that web browser is presenting now.
     */
    private Text locationText;
    
    /**
     * the browser control presenting bookmark url site.
     */
    private Browser browser;
    
    /**
     * constructor.
     * 
     * @param parentShell parent shell.
     * @param url the url for initial representation.
     */
    public WebBrowserDialog(Shell parentShell, String url) {
        super(parentShell);
        this.selectedUrl = url;
        this.bookmarkList = BookmarkItem.getListFromPreferrence();
    }
    
    /**
     * get the initial dialog size.
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
        if (size.y < INIT_HEIGHT) {
            size.y = INIT_WIDTH;
        }
        return size;
    }
    
    /**
     * create dialog area.
     * 
     * @param parent parent composite.
     * @return Control.
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite panel = (Composite) super.createDialogArea(parent);
        
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = COLUMN_NUMBER;
        panel.setLayout(layout);
        // sets the title.
        getShell().setText(Messages.WebBrowserDialog_DialogTitle); //$NON-NLS-1$
        
        //creates the content.
        createContent(panel);
        
        return panel;
    }
    
    /**
     * create the bowser control.
     * @param parent parent.
     */
    private void createContent(final Composite parent) {
        
        //Bookmark Name
        Label label = new Label(parent, SWT.WRAP);
        label.setText(Messages.WebBrowserDialog_BookmarkLabelText); //$NON-NLS-1$
        
        //Bookmark pulldown
        bookmarkCombo = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
        bookmarkCombo.addSelectionListener(
                new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        super.widgetSelected(e);
                        int ind = bookmarkCombo.getSelectionIndex();
                        selectedUrl = bookmarkList.get(ind).getUrl();
                        openUrl(parent);
                    }
                });
        GridData bookmarkComboData = new GridData();
        bookmarkComboData.horizontalAlignment = GridData.FILL;
        bookmarkComboData.widthHint = TEXT_WIDTH;
        bookmarkCombo.setLayoutData(bookmarkComboData);
        // sets the items to the combo box.
        String[] bookmarkArray = new String[bookmarkList.size()];
        for (int i = 0; i < bookmarkList.size(); i++) {
            bookmarkArray[i] = bookmarkList.get(i).getName();
        }
        bookmarkCombo.setItems(bookmarkArray);
        if (selectedUrl != null) {
            for (int i = 0; i < bookmarkList.size(); i++) {
                if (selectedUrl.equals(bookmarkList.get(i).getUrl())) {
                    bookmarkCombo.select(i);
                }
            }
        }
        
        Label locationLabel = new Label(parent, SWT.WRAP);
        locationLabel.setText("url");  //$NON-NLS-1$
        locationText = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        locationText.setText(selectedUrl);
        GridData localtiontextData = new GridData();
        localtiontextData.horizontalAlignment = GridData.FILL;
        localtiontextData.widthHint = TEXT_WIDTH;
        locationText.setLayoutData(localtiontextData);
        
        //Web Content
        openUrl(parent);
    }
    
    /**
     * Opens the page of selected bookmark URL site in the browser control.
     * @param parent parent.
     */
    private void openUrl(Composite parent) {
        if (browser == null) {
            browser = new Browser(parent, SWT.BORDER);
            browser.addLocationListener(this);
            GridData browserData = new GridData();
            browserData.horizontalSpan = 2;
            browserData.heightHint = INIT_HEIGHT;
            browserData.widthHint = INIT_WIDTH;
            browserData.horizontalAlignment = GridData.FILL;
            browserData.verticalAlignment = GridData.FILL;
            browser.setLayoutData(browserData);
        }
        if (selectedUrl != null && selectedUrl.length() != 0) {
            browser.setUrl(selectedUrl);
            browser.forward();
        }
    }
    
    /**
     * Sets the selected url.
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        selectedUrl = locationText.getText();
        super.okPressed();
    }

    /**
     * Returns  the selected bookmark.
     * @return Bookmark the selected bookmark.
     */
    public String getSelectedUrl() {
        return selectedUrl;
    }

    /** 
     * Sets the location to text control when web browser changes site. 
     * Sets the browser enable when it has changed.
     * @see org.eclipse.swt.browser.LocationListener#changed(org.eclipse.swt.browser.LocationEvent)
     * @param event event.
     */
    public void changed(LocationEvent event) {
        if (event.top) {
            String url = event.location;
            locationText.setText(url);
            locationText.redraw();
        } 
        getButton(IDialogConstants.OK_ID).setEnabled(true);
    }

    /**
     * Sets the browser disable when it is changing.
     * @see org.eclipse.swt.browser.LocationListener#changing(org.eclipse.swt.browser.LocationEvent)
     * @param event event.
     */
    public void changing(LocationEvent event) {
        getButton(IDialogConstants.OK_ID).setEnabled(false);
    }
}
