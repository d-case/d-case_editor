/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.editor.preferences;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import net.dependableos.dcase.diagram.editor.logic.xmlconv.XSLConverterItem;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.ui.NewConverterDialog;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class XSLTransformPreferencePage
    extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {

    /**
     * A list of converter item.
     */
    private java.util.List<XSLConverterItem> converters;
    /**
     * A extensions.
     */
    private static final String [] FILTER_EXTENSIONS =
        {
        "*.xsl;*.xslt" //$NON-NLS-1$
        };
    /**
     * A extensions filter name.
     */
    private static final String [] FILTER_NAMES =
        {
        "XSL Transform file(*.xsl, *.xslt)" //$NON-NLS-1$
        };
    /**
     * A panel grid column number.
     */
    private static final int GRID_COLUMN = 2;
    /**
     * A list view vertical span.
     */
    private static final int LISTVIEW_VERTICAL_SPAN = 3;
    /**
     * A list view height.
     */
    private static final int LISTVIEW_HEIGHT = 100;
    /**
     * A list view width.
     */
    private static final int LISTVIEW_WIDTH = 200;
    /**
     * A converter item list control. 
     */
    private List converterList = null;
    /**
     * A button control.
     */
    private Button addButton = null;
    /**
     * A button control.
     */
    private Button deleteButton = null;

    /**
     * Constructor for the class.
     * Create new preference page instance.
     */
    public XSLTransformPreferencePage() {
        super(GRID);
    }

    /**
     * Creates the field editors. Field editors are abstractions of
     * the common GUI blocks needed to manipulate various types
     * of preferences. Each field editor knows how to save and
     * restore itself.
     */
    public void createFieldEditors() {
    }

    /**
     * The must method of IWorkbenchPreferencePage interface.
     * @param workbench A workbench object.
     *  (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite panel = (Composite) super.createContents(parent);
        
        // creates a grid layout.
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = GRID_COLUMN;
        panel.setLayout(layout);

        createContentsControl(panel);
        load();
        return panel;
    }
    /**
     * Creates the preference page control.
     * 
     * @param panel A composite object.
     */
    private void createContentsControl(Composite panel) {
        createConverterListArea(panel);
        createAddDeleteButtonArea(panel);
    }

    /**
     * Creates the list view control and set layout. 
     * 
     * @param panel A composite object.
     */
    private void createConverterListArea(Composite panel) {
        converterList = new List(panel, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL
                | SWT.FULL_SELECTION);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessVerticalSpace = true;
        gridData.minimumHeight = LISTVIEW_HEIGHT;
        gridData.minimumWidth = LISTVIEW_WIDTH;
        gridData.verticalSpan = LISTVIEW_VERTICAL_SPAN;
        converterList.setLayoutData(gridData);
    }

    /**
     * Creates the button area control. 
     * 
     * @param panel A composite object.
     */
    private void createAddDeleteButtonArea(Composite panel) {
        addButton = new Button(panel, SWT.PUSH);
        addButton.setText(Messages.XSLTransferPreferencePageAddButton);
        addButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                addConverter();
            }
        });
        GridData addGridData = new GridData(GridData.FILL_HORIZONTAL);
        addButton.setLayoutData(addGridData);

        deleteButton = new Button(panel, SWT.PUSH);
        deleteButton.setText(Messages.XSLTransferPreferencePageDeleteButton);
        deleteButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                deleteConverter();
            }
        });
        GridData deleteGridData = new GridData(GridData.FILL_HORIZONTAL);
        deleteButton.setLayoutData(deleteGridData);
    }

    /**
     * Loads the converters and initializes the list to display them.  
     */
    private void load() {
        converters = XSLConverterItem.getConverters();
        for (XSLConverterItem item : converters) {
            converterList.add(item.getName());
        }
    }

    /**
     * Adds new converter definition.
     */
    private void addConverter() {
        String path = getXSLFilePath();
        if (path == null || path.length() == 0) {
            return;
        }
        String name = getConverterName();
        if (name == null || name.length() == 0) {
            return;
        }

        // add converter
        XSLConverterItem item = new XSLConverterItem();
        item.setName(name);
        item.setPath(path);
        converters.add(item);
        converterList.add(name);
    }
    /**
     * Returns XSL transform file path.
     * @return A file path string.
     */
    private String getXSLFilePath() {
        FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
        dlg.setFilterExtensions(FILTER_EXTENSIONS);
        dlg.setFilterNames(FILTER_NAMES);
        String path = dlg.open();
        if (path != null && path.length() > 0) {
            return path;
        }
        return null;
    }
    /**
     * Returns A new converter name. 
     * @return A new converter name.
     */
    private String getConverterName() {
        NewConverterDialog dlg = new NewConverterDialog(getShell(), converters);
        if (dlg.open() == IDialogConstants.OK_ID) {
            return dlg.getName();
        }
        return null;
    }
    /**
     * Deletes converter definition.
     */
    private void deleteConverter() {
        int index = converterList.getSelectionIndex();
        if (index >= 0) {
            String name = converterList.getItem(index);
            for (XSLConverterItem item : converters) {
                if (item.getName().equals(name)) {
                    converters.remove(item);
                    break;
                }
            }
            converterList.remove(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        XSLConverterItem.save(converters);
        return super.performOk();
    }
}