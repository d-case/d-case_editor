/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_DOUBLE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_INT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_RAW;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_STRING;

import java.util.ArrayList;
import java.util.List;



import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifierFactory;
import net.dependableos.dcase.diagram.editor.verifier.ParameterDialog;
import net.dependableos.dcase.diagram.editor.verifier.VerifierFactory;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to configure parameters.
 */
public class ParameterConfigDialog extends Dialog implements
        IStructuredContentProvider {
    /**
     * the verifier factory.
     */
    private VerifierFactory vf = null;
    /**
     * the node.
     */
    private NodeInfo nodeInfo = null;
    /**
     * parameters.
     */
    private ParameterItem[] parameterItems;
    /**
     * the table.
     */
    private Table table;
    /**
     * the table viewer.
     */
    private TableViewer tableViewer;
    /**
     * the text box for the script.
     */
    private Text scriptStrText;
    /**
     * the text box for the description.
     */
    private Text descFormatStrText;
    /**
     * the height of the table.
     */
    private static final int HEIGHT_HINT = 200;

    /**
     * the vertical span of the grid layout for the table.
     */
    private static final int VERTICAL_SPAN = 4;

    /**
     * the width of the dialog.
     */
    private static final int INIT_WIDTH = 500;

    /**
     * the width of a button.
     */
    private static final int BUTTON_WIDTH = 80;

    /**
     * the width of the first column of the table.
     */
    private static final int COLUMN_WIDTH_SELECT = 60;

    /**
     * the width of the second column of the table.
     */
    private static final int COLUMN_WIDTH_PARAMETER = 300;

    /**
     * the number of the column.
     */
    private static final int COLUMN_NUMBER = 3;

    /**
     * the title of the dialog.
     */
    private static final String DIALOG_TITLE = "Configure Parameters"; //$NON-NLS-1$

    /**
     * the label for the Desc Format String.
     */
    private static final String DESC_FORMAT_LABEL = "Desc Format String:"; //$NON-NLS-1$

    /**
     * the label for the Script.
     */
    private static final String SCRIPT_LABEL = "Script:"; //$NON-NLS-1$

    /**
     * Creates an instance and initializes it.
     * 
     * @param parentShell the parent shell.
     */
    public ParameterConfigDialog(Shell parentShell) {
        super(parentShell);
        vf = DataTypeVerifierFactory.getInstance();
    }

    /**
     * Sets the node.
     * 
     * @param nodeInfo the node.
     */
    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    /**
     * Returns the parameters.
     * 
     * @return the parameters.
     */
    private ParameterItem[] createParameters() {
        List<ParameterItem> list = null;
        // gets the parameterVals.
        String parameterVals = (String) nodeInfo
                .getAttribute(AttributeType.PARAMETERVALS);
        if (parameterVals != null && parameterVals.trim().length() != 0
                && ParameterItem.isValidParameter(parameterVals)) {
            list = ParameterItem.getPatameterList(parameterVals);
            // sets the parameters to use.
            for (ParameterItem item : list) {
                item.setSelected(true);
            }
        } else {
            list = new ArrayList<ParameterItem>();
        }
        // adds parameters which are not set to use.
        addRemainingParameter(list);

        return list.toArray(new ParameterItem[0]);
    }

    /**
     * Adds parameters which are not set to use to the list.
     * 
     * @param list the list.
     */
    private void addRemainingParameter(List<ParameterItem> list) {
        List<String> paraNames = vf.getParamNames();
        if (paraNames == null) {
            return;
        }
        for (String paramName : paraNames) {
            if (!containsParamList(paramName, list)) {
                ParameterItem item = new ParameterItem();
                item.setParameterId(paramName);
                if (DATA_TYPE_DOUBLE.equals(vf.getVerifier(paramName)
                        .getDataType())) {
                    item.setParameterValue(getDigitFixedDoubleString(vf
                            .getVerifier(paramName), DATA_ATTRIBUTE_MIN));
                }

                if (DATA_TYPE_INT.equals(vf.getVerifier(paramName)
                        .getDataType())) {
                    String result = (String) vf.getVerifier(paramName)
                            .getAttributeMap().get(DATA_ATTRIBUTE_MIN);
                    if (result == null) {
                        result = String.valueOf(0);
                    }
                    item.setParameterValue(result);
                }

                if (DATA_TYPE_STRING.equals(vf.getVerifier(paramName)
                        .getDataType())) {
                    item.setParameterValue(""); //$NON-NLS-1$
                }

                if (DATA_TYPE_RAW.equals(vf.getVerifier(paramName)
                        .getDataType())) {
                    item.setParameterValue(""); //$NON-NLS-1$
                }

                if (DATA_TYPE_ENUM.equals(vf.getVerifier(paramName)
                        .getDataType())) {
                    item.setParameterValue((String) vf.getVerifier(paramName)
                            .getAttributeList().get(0));
                }

                list.add(item);
            }
        }
    }

    /**
     * Returns the string that represents double value.
     * 
     * @param verifier the verifier.
     * @param attribute the attribute name.
     * @return String the string that represents double value.
     */
    private String getDigitFixedDoubleString(DataTypeVerifier verifier,
            String attribute) {
        String result = (String) verifier.getAttributeMap().get(attribute);
        // rounds off to specified decimal places.
        int digit = ParameterUtil.getDigit(verifier);
        if (result == null) {
            result = String.valueOf(0);
        }
        result = result.trim();
        int dotIndex = result.indexOf("."); //$NON-NLS-1$
        if (dotIndex > 0) {
            if (digit > 0) {
                dotIndex++;
            }
            int resultLength = dotIndex + digit;
            StringBuffer sb = new StringBuffer(result);
            for (int i = result.length(); i < resultLength; i++) {
                sb.append("0"); //$NON-NLS-1$
            }

            result = sb.substring(0, resultLength);
        }
        return result;
    }

    /**
     * Tests whether the list contains the specified parameter.
     * 
     * @param paraName the parameter name.
     * @param list the parameter list.
     * @return true if and only if the list contains the specified parameter;false otherwise.
     */
    private boolean containsParamList(String paraName, List<ParameterItem> list) {
        boolean result = false;
        for (ParameterItem item : list) {
            if (paraName.equals(item.getParameterId())) {
                result = true;
                break;
            }
        }
        return result;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite panel = (Composite) super.createDialogArea(parent);

        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = COLUMN_NUMBER;
        panel.setLayout(layout);
        getShell().setText(DIALOG_TITLE);

        if (nodeInfo == null) {
            return panel;
        }

        parameterItems = createParameters();

        createTable(panel);
        createOperateButton(panel);

        Label spaceLabel = new Label(panel, SWT.WRAP);
        GridData spaceLabelGridData = new GridData(GridData.FILL_HORIZONTAL);
        spaceLabelGridData.horizontalSpan = COLUMN_NUMBER;
        spaceLabel.setLayoutData(spaceLabelGridData);

        Label descFormatStrLabel = new Label(panel, SWT.WRAP);
        descFormatStrLabel.setText(DESC_FORMAT_LABEL);
        descFormatStrLabel.setLayoutData(new GridData());

        descFormatStrText = new Text(panel, SWT.SINGLE | SWT.BORDER);
        GridData descFormatStrGridData = new GridData(GridData.FILL_HORIZONTAL);
        descFormatStrGridData.horizontalSpan = 2;
        descFormatStrText.setLayoutData(descFormatStrGridData);

        String tempStr = (String) nodeInfo.getAttribute(AttributeType.PARAMETERIZEDDESC);
        if (tempStr == null) {
            tempStr = ""; //$NON-NLS-1$
        }
        descFormatStrText.setText(tempStr);

        Label scriptStrLabel = new Label(panel, SWT.WRAP);
        scriptStrLabel.setText(SCRIPT_LABEL);
        scriptStrLabel.setLayoutData(new GridData());

        scriptStrText = new Text(panel, SWT.SINGLE | SWT.BORDER);
        GridData scriptStrGridData = new GridData(GridData.FILL_HORIZONTAL);
        scriptStrGridData.horizontalSpan = 2;
        scriptStrText.setLayoutData(scriptStrGridData);

        tempStr = (String) nodeInfo.getAttribute(AttributeType.USERDEF006);
        if (tempStr == null) {
            tempStr = ""; //$NON-NLS-1$
        }
        scriptStrText.setText(tempStr);

        return panel;
    }

    /**
     * Creates the table for parameters and initializes it.
     * 
     * @param panel the panel.
     */
    private void createTable(Composite panel) {
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalSpan = VERTICAL_SPAN;
        gridData.heightHint = HEIGHT_HINT;
        gridData.horizontalSpan = 2;

        // creates the table for parameters.
        table = new Table(panel, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setLayoutData(gridData);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        // adds the mouse listener.
        table.addMouseListener(new MouseListener() {
            // nop.
            public void mouseDoubleClick(MouseEvent e) {
            }

            // select the parameter.
            public void mouseDown(MouseEvent e) {
                int width = table.getColumn(0).getWidth();
                // tests whether the title row is in the position.
                if (e.x < width
                        && e.y < (table.getHeaderHeight() + table
                                .getItemHeight()
                                * parameterItems.length)) {
                    // select the parameter.
                    int index = table.getSelectionIndex();
                    parameterItems[index].setSelected(!parameterItems[index]
                            .isSelected());
                    tableViewer.refresh();
                }
            }

            // nop.
            public void mouseUp(MouseEvent e) {
            }
        });

        TableColumn column = new TableColumn(table, SWT.CENTER, 0);
        column.setText(Messages.ParameterConfigDialog_0);
        column.setWidth(COLUMN_WIDTH_SELECT);

        column = new TableColumn(table, SWT.LEFT, 1);
        column.setText(Messages.ParameterConfigDialog_1);
        column.setWidth(COLUMN_WIDTH_PARAMETER);

        // creates the table view.
        tableViewer = new TableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(new String[] {
                Messages.ParameterConfigDialog_0,
                Messages.ParameterConfigDialog_1 });

        CellEditor[] editors = new CellEditor[2];
        // creates check box editors.
        editors[0] = new CheckboxCellEditor(table);

        // text editors.
        TextCellEditor textEditor = new TextCellEditor(table);
        editors[1] = textEditor;

        tableViewer.setCellEditors(editors);
        tableViewer.setCellModifier(new ParameterCellModifier());

        tableViewer.setContentProvider(this);
        tableViewer.setLabelProvider(new ParameterLabelProvider());
        tableViewer.setInput(parameterItems);
    }

    /**
     * Creates buttons and initializes them.
     * 
     * @param panel the panel.
     */
    private void createOperateButton(Composite panel) {
        // creates the 'Edit' button and initializes it.
        Button editButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.ParameterConfigDialog_2);
        GridData editGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        editGridData.minimumWidth = BUTTON_WIDTH;
        editButton.setLayoutData(editGridData);
        // adds the selection listener.
        editButton.addSelectionListener(new SelectionAdapter() {
            // 'Edit' button is selected.
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    ParameterDialog dialog = new ParameterDialog(
                            ((Button) e.getSource()).getShell());
                    // shows a dialog to edit the value of the selected parameter.
                    NodeType nodeType = null;
                    if (nodeInfo != null) {
                        nodeType = nodeInfo.getNodeType();
                    }
                    NodeInfo info = new NodeInfo(nodeType);
                    info.setAttribute(AttributeType.PARAMETERVALS,
                            (parameterItems[selectedIndex]).getParamString());
                    dialog.setNodeInfo(info);
                    dialog.setRunningMode(ParameterDialog.SINGLE);
                    if (Dialog.OK == dialog.open()) {
                        boolean selectedStatus = parameterItems[selectedIndex]
                                .isSelected();
                        parameterItems[selectedIndex] = new ParameterItem(
                                (String) info
                                        .getAttribute(AttributeType.PARAMETERVALS));
                        parameterItems[selectedIndex]
                                .setSelected(selectedStatus);
                        tableViewer.refresh();
                    }
                }
            }
        });
        // creates the "Up" button and initializes it.
        Button upButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        upButton.setText(Messages.ParameterConfigDialog_3);
        GridData upGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        upGridData.minimumWidth = BUTTON_WIDTH;
        upButton.setLayoutData(upGridData);
        /// adds the selection listener.
        upButton.addSelectionListener(new SelectionAdapter() {
            // "Up" button is selected.
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex > 0) {
                    ParameterItem item = parameterItems[selectedIndex - 1];
                    parameterItems[selectedIndex - 1] = parameterItems[selectedIndex];
                    parameterItems[selectedIndex] = item;
                    tableViewer.refresh();
                }
            }
        });

        // creates the "Down" button and initializes it.
        Button downButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        downButton.setText(Messages.ParameterConfigDialog_4);
        GridData downGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        downGridData.minimumWidth = BUTTON_WIDTH;
        downButton.setLayoutData(downGridData);
        /// adds the selection listener.
        downButton.addSelectionListener(new SelectionAdapter() {
            // "Down" button is selected.
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (0 <= selectedIndex
                        && selectedIndex < parameterItems.length - 1) {
                    ParameterItem item = parameterItems[selectedIndex + 1];
                    parameterItems[selectedIndex + 1] = parameterItems[selectedIndex];
                    parameterItems[selectedIndex] = item;
                    tableViewer.refresh();
                }
            }
        });

        // sets layout data.
        Label scriptStrLabel = new Label(panel, SWT.WRAP);
        GridData spaceGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        spaceGridData.widthHint = BUTTON_WIDTH;
        scriptStrLabel.setLayoutData(spaceGridData);

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
    protected void okPressed() {
        // updates the parameters.
        updateParameters();
        // sets the description
        nodeInfo.setAttribute(AttributeType.PARAMETERIZEDDESC, descFormatStrText.getText());
        // sets the scripts.
        nodeInfo.setAttribute(AttributeType.USERDEF006, scriptStrText.getText());
        // closes the dialog.
        super.okPressed();
    }

    /**
     * Updates the parameters.
     */
    private void updateParameters() {
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < parameterItems.length; i++) {
            // outputs the selected items.
            if (parameterItems[i].isSelected()) {
                strBuf.append(parameterItems[i].getParamString());
                strBuf.append(","); //$NON-NLS-1$
            }
        }
        String result = strBuf.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        // sets the parameters to ParameterVals.
        nodeInfo.setAttribute(AttributeType.PARAMETERVALS, result);
    }

    
    /**
     * Returns the elements to display in the viewer when its input is set to
     * the given element. These elements can be presented as rows in a table,
     * items in a list, etc. The result is not modified by the viewer.
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     * 
     * @param inputElement the input element.
     * @return the array of elements to display in the viewer.
     */
    public Object[] getElements(Object inputElement) {
        return (ParameterItem[]) inputElement;
    }

    /**
     * Disposes of this content provider.
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        parameterItems = null;
    }

    /**
     * Notifies this content provider that the given viewer's input has been switched to a different element. 
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     * 
     * @param viewer the viewer
     * @param oldInput the old input element, or null if the viewer did not previously have an input.
     * @param newInput the new input element, or null if the viewer does not have an input.
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}
