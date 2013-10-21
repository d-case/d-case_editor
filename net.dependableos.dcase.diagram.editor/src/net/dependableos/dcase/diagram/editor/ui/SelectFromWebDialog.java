/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.net.MalformedURLException;
import java.net.URL;

import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A dialog to select an attachment from web.
 */
public class SelectFromWebDialog extends Dialog {
    
    /**
     * the dialog width.
     */
    private static final int INIT_WIDTH = 300;
    
    /**
     * column number.
     */
    private static final int GRID_COLUMNS = 3;
    
    /**
     * the text width.
     */
    private static final int TEXT_WIDTH = 250;
    
    /**
     * the Text control for the URL.
     */
    private Text urlText;
    
    /**
     * the URL.
     */
    private String url = "";    //$NON-NLS-1$
    
    /**
     * Constructor.
     * @param parentShell shell.
     */
    public SelectFromWebDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite panel = (Composite) super.createDialogArea(parent);
        
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = GRID_COLUMNS;
        panel.setLayout(layout);
        getShell().setText(Messages.SelectFromWebDialog_DialogTitle);
        
        Label label = new Label(panel, SWT.WRAP);
        label.setText(Messages.SelectFromWebDialog_0);
        
        urlText = new Text(panel, SWT.SINGLE | SWT.BORDER);
        urlText.setText(this.url);
        GridData urlGridData = new GridData();
        urlGridData.horizontalAlignment = GridData.FILL;
        urlGridData.widthHint = TEXT_WIDTH;
        urlText.setLayoutData(urlGridData);

        Button browseButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        browseButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                    WebBrowserDialog browserDialog = 
                        new WebBrowserDialog(getActiveWindowShell(), urlText.getText());
                    if (Dialog.OK == browserDialog.open()) {
                        setUrl(browserDialog.getSelectedUrl());
                    }
            }
        });
        browseButton.setText(Messages.SelectFromWebDialog_BrowseButtonName);
        
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void okPressed() {
        try {
            if (isValidUrl(urlText.getText())) {
                this.url = urlText.getText();
                super.okPressed();
            }
        } catch (DcaseRuntimeException e) {
            MessageWriter.showErrorMessageBox(e.getMessage(), getShell());
        }
    }
    
    /**
     * Sets the URL.
     * 
     * @param url the URL.
     */
    public void setUrl(String url) {
        if (url == null) {
            this.url = "";  //$NON-NLS-1$
        } else {
            this.url = url;
        }
        if (urlText != null) {
            urlText.setText(this.url);
        }
    }
    
    /**
     * Returns the URL.
     * 
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Returns the active window shell.
     * 
     * @return the active window shell.
     */
    private Shell getActiveWindowShell() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
        return activeWindow.getShell();
    }
    
    /**
     * Validates the URL string.
     * @param urlString URL string.
     * @return if url string is valid: true.
     */
    private boolean isValidUrl(String urlString) {
        if (urlString == null || urlString.trim().length() == 0) {
            throw new DcaseRuntimeException(
                    Messages.SelectFromWebDialog_URLEmptyMessage, null,
                    null, 0, MessageTypeImpl.OPEN_URL_INVALID_URL);
        }
        
        try {
            URL attachmentUrl = new URL(urlString);
            MessageWriter.writeMessageToConsole(
                    "protocol=" + attachmentUrl.getProtocol(), MessageTypeImpl.DIAGNOSIS); //$NON-NLS-1$
            // check the protocol
            if (!DcaseEditorUtil.checkDcaseReferenceProtocol(attachmentUrl.getProtocol())) {
                throw new DcaseRuntimeException(NLS.bind(
                        Messages.SelectFromWebDialog_NotSupportedProtocolMessage, attachmentUrl.getProtocol()), null,
                        null, 0, MessageTypeImpl.OPEN_URL_INVALID_URL);
            }
        } catch (MalformedURLException e) {
            throw new DcaseRuntimeException(NLS.bind(
                    Messages.SelectFromWebDialog_URLInvalidMessage, "\"" + urlString + "\""), e,
                    null, 0, MessageTypeImpl.OPEN_URL_INVALID_URL);
        }
        return true;
    }

}
