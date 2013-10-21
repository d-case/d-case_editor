/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;
import net.dependableos.dcase.diagram.editor.ui.ParameterDefinitionWizard;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.xml.sax.SAXException;

/**
 * A preference page class for the parameters.
 */
public class ParameterPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

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
     * Spans 7 rows to display operation buttons at left side.
     */
    private static final int VERTICAL_SPAN = 8;
    
    /**
     * the default export file name. 
     */
    private static final String EXPORT_PARAMETER_FILE_NAME = "parameters.xml"; //$NON-NLS-1$
    
    /**
     * a wildcard character.
     */
    private static final String ASTERISK = "*"; //$NON-NLS-1$

    /**
     * The file extension of xml. 
     */
    public static final String XML_FILE_EXTENSION = ".xml"; //$NON-NLS-1$

    /**
     * a list control for parameters.
     */
    private org.eclipse.swt.widgets.List parameterDataList;
    
    /**
     * parameters.
     */
    private List<ParameterDataItem> prameterDataItems;
    
    /**
     * the selected parameter.
     */
    private ParameterDataItem selectedParameter = null;
    
    /**
     * the add button.
     */
    private Button addButton;
    
    /**
     * the edit button.
     */
    private Button editButton;
    
    /**
     * the delete button.
     */
    private Button delButton;
    
    /**
     * the import button.
     */
    private Button importButton;
    
    /**
     * the export button.
     */
    private Button exportButton;

    /**
     * Constructor.
     */
    public ParameterPreferencePage() {
        super();
        prameterDataItems = ParameterDataItem.getParamDatatypeListFromPreferences();
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
        GridLayout layout = new GridLayout();
        layout.numColumns = COLUMN_NUMBER;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        parent.setLayout(layout);
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));
        parent.setFont(parent.getFont());
        
        setParameterTypeListSelect(parent);
        // creates buttons.
        createOperationButton(parent);
        
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        List<ParameterDataItem> parameterDataItems =  getParameterDatatypeList();
        ParameterDataItem.savePreferences(parameterDataItems);
        return super.performOk();
    }

    /**
     * Initializes a list for parameters.
     * 
     * @param panel parent composite.
     */
    private void setParameterTypeListSelect(Composite panel) {
        // creates the select list if it dose not exist.
        if (parameterDataList == null) {
            parameterDataList = new org.eclipse.swt.widgets.List(
                    panel, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
            GridData gridData = new GridData(GridData.FILL_BOTH);
            gridData.grabExcessVerticalSpace = true;
            gridData.verticalSpan = VERTICAL_SPAN;
            gridData.heightHint = HEIGHT_HINT;
            parameterDataList.setLayoutData(gridData);
            
        }
        parameterDataList.removeAll();
        String[] items = new String[prameterDataItems.size()];
        for (int i = 0; i < prameterDataItems.size(); i++) {
            items[i] = prameterDataItems.get(i).getName();
        }
        parameterDataList.setItems(items);
        
        //If a parameter type is selected, sets it selected status in the select list. 
        if (selectedParameter != null) {
            for (int i = 0; i < prameterDataItems.size(); i++) {
                if (selectedParameter.equals(prameterDataItems.get(i))) {
                    parameterDataList.select(i);
                    break;
                }
            }
        }
        
        validate();
    }
    
    /**
     * Validates the list of parameter data.
     */
    private void validate() {
        try {
            for (ParameterDataItem data : prameterDataItems) {
                data.validate();
            }
            setErrorMessage(null);
            setValid(true);
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setValid(false);
        }
    }

    /**
     * Creates buttons.
     * 
     * @param panel parent composite.
     */
    private void createOperationButton(final Composite panel) {

        // creates the add button.
        addButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        addButton.setText(Messages.ParameterDatatypePreferencePage_1);
        GridData createGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        createGridData.widthHint = BUTTON_WIDTH;
        addButton.setLayoutData(createGridData);
        addButton.addSelectionListener(new SelectionAdapter() {
            // The event method executed when the creation button is pushed.
            public void widgetSelected(SelectionEvent e) {
                ParameterDefinitionWizard wizard = new ParameterDefinitionWizard(prameterDataItems);
                wizard.setWindowTitle(Messages.ParameterDatatypeDialog_CreationWizardTile);
                DcaseDiagramEditorUtil.runWizard(getShell(), wizard, "CreateParameterDatatype"); //$NON-NLS-1$
                selectedParameter = wizard.getResultedParameterdata();
                setParameterTypeListSelect(panel);
            }
        });
        // creates the edit button.
        editButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.ParameterDatatypePreferencePage_2);
        GridData editGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        editGridData.widthHint = BUTTON_WIDTH;
        editButton.setLayoutData(editGridData);
        editButton.addSelectionListener(new SelectionAdapter() {
         // The event method executed when the edit button is pushed.
            public void widgetSelected(SelectionEvent e) {
                int index = parameterDataList.getSelectionIndex(); 
                if (index >= 0) {
                    ParameterDefinitionWizard wizard = 
                            new ParameterDefinitionWizard(index, prameterDataItems);
                    wizard.setWindowTitle(Messages.ParameterDatatypeDialog_EditWizardTitle);
                    DcaseDiagramEditorUtil.runWizard(getShell(), wizard, "EditParameterDatatype"); //$NON-NLS-1$
                    selectedParameter = wizard.getResultedParameterdata();
                    setParameterTypeListSelect(panel);
                }
            }
        });

        // creates the deletion button.
        delButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        delButton.setText(Messages.ParameterDatatypePreferencePage_3);
        GridData delGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        delGridData.widthHint = BUTTON_WIDTH;
        delButton.setLayoutData(delGridData);
        delButton.addSelectionListener(new SelectionAdapter() {
            // // The event method executed when the deletion button is pushed.
            public void widgetSelected(SelectionEvent e) {
                int index = parameterDataList.getSelectionIndex();
                if (index >= 0) {
                    parameterDataList.remove(index);
                    prameterDataItems.remove(index);
                    setParameterTypeListSelect(panel);
                }
            }
        });
        
        Label label1 = new Label(panel, SWT.NONE);
        label1.setText(""); //$NON-NLS-1$
        Label label2 = new Label(panel, SWT.NONE);
        label2.setText(""); //$NON-NLS-1$

        // creates the import button.
        importButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        importButton.setText(Messages.ParameterDatatypePreferencePage_ImportButtonLabel);
        GridData importGridData = new GridData(GridData.VERTICAL_ALIGN_END);
        importGridData.widthHint = BUTTON_WIDTH;
        importButton.setLayoutData(importGridData);
        importButton.addSelectionListener(new SelectionAdapter() {
            // // The event method executed when the deletion button is pushed.
            public void widgetSelected(SelectionEvent e) {
                try {
                    File importFile = selectImportFile(panel.getShell(), 
                            Messages.ParameterDatatypePreferencePage_ImportFileDialogTitle);
                    if (importFile != null) {
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(importFile);
                            mergeParamItems(ParameterDataItem.getParamDatatypeListFromXml(fis));
                        } finally {
                            if (fis != null) {
                                fis.close();
                            }
                        }
                        setParameterTypeListSelect(panel);
                        showMessageBox(Messages.ParameterDatatypePreferencePage_ImportSuccessMessage, panel.getShell());
                    }
                } catch (ParserConfigurationException pe) {
                    showErrorMessageBox(Messages.ParameterDatatypePreferencePage_ImportErrorMessage, panel.getShell());
                } catch (SAXException se) {
                    showErrorMessageBox(Messages.ParameterDatatypePreferencePage_ImportErrorMessage, panel.getShell());
                } catch (IOException ie) {
                    showErrorMessageBox(Messages.ParameterDatatypePreferencePage_ImportErrorMessage, panel.getShell());
                }
            }
        });
        
        // creates the export button.
        exportButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        exportButton.setText(Messages.ParameterDatatypePreferencePage_ExportButtonLabel);
        GridData exportGridData = new GridData(GridData.VERTICAL_ALIGN_END);
        exportGridData.widthHint = BUTTON_WIDTH;
        exportButton.setLayoutData(exportGridData);
        exportButton.addSelectionListener(new SelectionAdapter() {
            // // The event method executed when the deletion button is pushed.
            public void widgetSelected(SelectionEvent e) {
                File exportFile = selectExportFile(panel.getShell(), 
                        Messages.ParameterDatatypePreferencePage_ExportFileDialogTitle);
                if (exportFile != null) {
                    String xml = ""; //$NON-NLS-1$
                    try {
                        xml = ParameterDataItem.getXmlString(
                                ParameterPreferencePage.this.prameterDataItems);
                    } catch (ParserConfigurationException pce) {
                        showErrorMessageBox(
                                Messages.ParameterDatatypePreferencePage_ExportErrorMessage, panel.getShell());
                    } catch (TransformerException te) {
                        showErrorMessageBox(
                                Messages.ParameterDatatypePreferencePage_ExportErrorMessage, panel.getShell());
                    }
                    FileWriter writer = null;
                    try {
                        writer = new FileWriter(exportFile);
                        writer.write(xml);
                        writer.flush();
                        showMessageBox(
                                Messages.ParameterDatatypePreferencePage_ExportSuccessMessage, 
                                panel.getShell());
                    } catch (IOException ioe) {
                          showErrorMessageBox(
                                  Messages.ParameterDatatypePreferencePage_ExportErrorMessage, panel.getShell());
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ioe) {
                            showErrorMessageBox(
                                    Messages.ParameterDatatypePreferencePage_ExportErrorMessage, panel.getShell());
                        }
                    }
                    ParameterDataItem.refresh();
                }
            }
        });
    }
    
    /**
     * Merges the editing list and the imported list of parameter data.
     * @param importItems the imported list of parameter data. 
     */
    private void mergeParamItems(List<ParameterDataItem> importItems) {
        for (ParameterDataItem item : importItems) {
            int i = 0;
            try {
                DataTypeVerifier.validString(false,
                        SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                        item.getName());
            } catch (Exception e) {
                continue;
            }

            for (i = 0; i < prameterDataItems.size(); i++) {
                ParameterDataItem paramData = prameterDataItems.get(i);
                if (item.getName().equals(paramData.getName())) {
                    break;
                }
            }
            if (i < prameterDataItems.size()) {
                prameterDataItems.remove(i);
                prameterDataItems.add(i, item);
            } else {
                prameterDataItems.add(item);
            }
        }
    }
    
    /**
     * Displays the message dialog.
     * @param message message
     * @param shell the parent shell
     */
    public static void showMessageBox(String message, Shell shell) {
        MessageBox box = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
        box.setMessage(message);
        box.open();
    }

    
    /**
     * Displays the message dialog.
     * @param message message
     * @param shell the parent shell
     */
    public static void showErrorMessageBox(String message, Shell shell) {
        MessageBox box = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
        box.setText("ERROR"); //$NON-NLS-1$
        box.setMessage(message);
        box.open();
    }
   
     
    /**
     * Returns the list of parameter data types contained in this dialog.
     * @return the list of parameter data types.
     */
    private List<ParameterDataItem> getParameterDatatypeList() {
        return prameterDataItems;
    }
    
    /**
     * Opens the dialog to select the export file path.
     * @param parent the parent shell
     * @param title the title
     * @return the selected export file.
     */
    private static File selectExportFile(Shell parent, String title) {
        FileDialog dialog = new FileDialog(parent, SWT.SAVE);
        dialog.setText(title);
        dialog.setFileName(EXPORT_PARAMETER_FILE_NAME);
        dialog.setFilterExtensions(new String[] { ASTERISK.concat(XML_FILE_EXTENSION) });
        dialog.setOverwrite(true);
        String parametersFilePath = dialog.open();
        // terminates if canceled.
        if (parametersFilePath == null) {
            return null;
        }
        return new File(parametersFilePath);

    }
    
    /**
     * Opens the dialog to select the import file path.
     * @param parent the parent shell
     * @param title the title
     * @return the selected import file.
     */
    private static File selectImportFile(Shell parent, String title) {
        FileDialog dialog = new FileDialog(parent, SWT.OPEN);
        dialog.setText(title);
        dialog.setFilterExtensions(new String[] { ASTERISK.concat(XML_FILE_EXTENSION) });
        String parametersFilePath = dialog.open();
        if (parametersFilePath == null || parametersFilePath.trim().length() == 0) {
            return null;
        }
        return new File(parametersFilePath);
    }

}
