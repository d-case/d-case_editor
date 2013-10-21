/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_INC;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MAX;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_DOUBLE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_INT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_RAW;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;

/**
 * A dialog to set parameter values.
 */
public class ParameterDialog extends Dialog {

    /**
     * the verifier map.
     */
    Map<String, DataTypeVerifier> vf = null;
    /**
     * the label for the description.
     */
    private Label descLabel;
    /**
     * the node.
     */
    private NodeInfo nodeInfo = null;
    /**
     * the parameters.
     */
    private ParameterItem[] parameterItems;
    /**
     * the backup value of parameters.
     */
    private List<String> backupParameterValues;
    /**
     * the list of controls.
     */
    private List<Control> controlList = new ArrayList<Control>();

    /**
     * the description format string.
     */
    private String userdef005Str = null;

    /**
     * the initial value of width.
     */
    private static final int INIT_WIDTH = 500;

    /**
     * the decimal number.
     */
    private static final int DECIMAL_SYSTEM = 10;

    /**
     * colon.
     */
    private static final String LABEL_COLON = " :"; //$NON-NLS-1$
    /**
     * the off set for the description.
     */
    private static final String DESCSTR_OFFSET = "      "; //$NON-NLS-1$
    /**
     * mode of setting parameters.
     */
    public static final int NORMAL = 0;
    /**
     * mode of setting a specified parameters.
     */
    public static final int SINGLE = 1;
    /**
     * running mode.NORMAL or SINGLE.
     */
    private int runningMode = NORMAL;

    /**
     * Creates an instance and initializes it.
     * 
     * @param parentShell the parent.
     */
    public ParameterDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Creates an instance and initializes it.
     * 
     * @param parentShell the parent.
     * @param verifierFactory the verifier factory.
     */
    public ParameterDialog(Shell parentShell, VerifierFactory verifierFactory) {
        super(parentShell);
    }

    /**
     * Sets the running mode.
     * 
     * @param mode the running mode.NORMAL or SINGLE.
     */
    public void setRunningMode(int mode) {
        runningMode = mode;
    }

    /**
     * Sets the node.
     * 
     * @param nodeInfo the node.
     */
    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
        userdef005Str = (String) nodeInfo
                .getAttribute(AttributeType.USERDEF005);
        if (userdef005Str == null) {
            userdef005Str = ""; //$NON-NLS-1$
        }
        parameterItems = null;
        
        String userdef009 = (String)nodeInfo.getAttribute(AttributeType.USERDEF009);
        vf = ParameterDataItem.getDataTypeVerifierMapFromString(userdef009);
    }

    /**
     * Returns the list of the parameters.
     * 
     * @return the list of the parameters.
     */
    public ParameterItem[] getParameters() {
        
        if (parameterItems == null) {
            String userdef007 = (String) nodeInfo
                    .getAttribute(AttributeType.USERDEF007);
            List<ParameterItem> list = ParameterItem.getPatameterList(userdef007);
            List<ParameterItem> newList = new ArrayList<ParameterItem>();
            backupParameterValues = new ArrayList<String>();
            
            String userdef009 = (String) nodeInfo.getAttribute(AttributeType.USERDEF009);
            if(userdef009 != null) {
            	List<String> nameList = ParameterUtil.getParameterDefinitionNames(userdef009);
            	if(nameList != null && nameList.size() > 0) {
            		for(String name : nameList) {
            			int index;
            			for(index=0; index<list.size(); index++) {
            				if(name.equals(list.get(index).getParameterId())) {
            					break;
            				}
            			}
            			ParameterItem item;
            			if(index < list.size()) {
            				item = list.get(index);
            			} else {
            				item = new ParameterItem();
            				item.setParameterId(name);
            			}
            			newList.add(item);
            			backupParameterValues.add(item.getParameterValue());
            		}
            	}
            }
            
            parameterItems = newList.toArray(new ParameterItem[0]);
        }
        return parameterItems;
    }

    /**
     * Returns the array of flags.The flag is true if the parameter is local.
     * 
     * @return the array of flags.
     */
    private Boolean[] getArrayIsLocal() {
    	// all parameters are local.
        List<Boolean> list = new ArrayList<Boolean>();
        for(int i=0; i<parameterItems.length; i++) {
        	list.add(true);
        }
        return list.toArray(new Boolean[0]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite panel = (Composite) super.createDialogArea(parent);

        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        panel.setLayout(layout);
        getShell().setText(Messages.ParameterDialog_1);

        if (nodeInfo == null) {
            return panel;
        }
        ParameterItem[] parameters = getParameters();

        if (runningMode == NORMAL) {
            descLabel = createLabel(panel, DESCSTR_OFFSET.concat(userdef005Str));
            GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
            //gridData.horizontalSpan = 2;
            descLabel.setLayoutData(gridData);
        }
        
        Label spaceLabel = createLabel(panel, ""); //$NON-NLS-1$
        GridData spacegridData = new GridData();
        spaceLabel.setLayoutData(spacegridData);
        
        final Composite parameterPanel = new Composite(panel, SWT.NONE);
        GridLayout parameterLayout = new GridLayout();
        parameterLayout.numColumns = 2;
        parameterPanel.setLayout(parameterLayout);
        parameterPanel.setLayoutData(new GridData(GridData.FILL_BOTH));
        for (int i = 0; i < parameters.length; i++) {
        	Label urlLabel = new Label(parameterPanel, SWT.None);
        	urlLabel.setText(parameters[i].getParameterId().concat(LABEL_COLON));
            urlLabel.setLayoutData(new GridData());
            urlLabel.addListener(SWT.Selection, new Listener() {
            	public void handleEvent(Event e) {
            		//***USU***
            	}
            });
            createControl(parameterPanel, parameters[i], getArrayIsLocal()[i]);
        }
        
        return panel;
    }
    
    /**
     * Creates a control to set the value of the specified parameter.
     * 
     * @param panel the parent.
     * @param parameterItem the parameter.
     * @param enabled true if and only if the control should be enabled;false otherwise.
     */
    private void createControl(Composite panel, ParameterItem parameterItem, Boolean enabled) {
        String paramDataType = null;
        DataTypeVerifier dataTypeVerifier = null;
        if (vf != null) {
            dataTypeVerifier = vf.get(parameterItem.getParameterId());
            if (dataTypeVerifier != null) {
                paramDataType = dataTypeVerifier.getDataType();
            }
        }
        Control control = null;

        // in case of string or raw.
       if (paramDataType == null 
                || DATA_TYPE_STRING.equals(paramDataType)
                || DATA_TYPE_RAW.equals(paramDataType)) {
            control = createTextControl(panel, parameterItem
                    .getParameterValue());
        }

        // in case of double or integer.
        if (DATA_TYPE_DOUBLE.equals(paramDataType)
                || DATA_TYPE_INT.equals(paramDataType)) {
            control = createSpinnerControl(panel, parameterItem,
                    dataTypeVerifier);
        }

        // in case of enumeration.
        if (DATA_TYPE_ENUM.equals(paramDataType)) {
            control = createComboControl(panel, parameterItem, dataTypeVerifier);
        }

        // enables if the parameter is local. 
        if (nodeInfo.getNodeType() != null) {
            if (!nodeInfo.getNodeType().equals(NodeType.ARGUMENT)) {
                control.setEnabled(enabled);
            }
        }

        control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        controlList.add(control);
    }

    /**
     * Creates a label and initializes it.
     * 
     * @param parent the parent.
     * @param text the text.
     * @return a label.
     */
    private Label createLabel(Composite parent, String text) {
        Label label = new Label(parent, SWT.WRAP);
        label.setText(text);

        return label;
    }

    /**
     * Creates a text control and initializes it.
     * 
     * @param parent the parent.
     * @param text the text.
     * @return a text control.
     */
    private Text createTextControl(Composite parent, String text) {
        if (text == null) {
            text = ""; //$NON-NLS-1$
        }
        Text textControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
        textControl.setText(text);

        return textControl;
    }

    /**
     * Creates a spinner and initializes it.
     * 
     * @param parent the parent.
     * @param parameterItem the parameter.
     * @param dataTypeVerifier the verifier.
     * @return a spinner.
     */
    private Spinner createSpinnerControl(Composite parent,
            ParameterItem parameterItem, DataTypeVerifier dataTypeVerifier) {
        int min = 0;
        int max = 0;
        int digit = 0;
        int inc = 0;
        String minStr = null;
        String maxStr = null;

        String dataType = dataTypeVerifier.getDataType();

        // in case of double.
        if (DATA_TYPE_DOUBLE.equals(dataType)) {
            minStr = ParameterUtil.getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_MIN,
                    dataTypeVerifier);
            double minDouble = 0.0;
            double maxDouble = 0.0;
            if (minStr == null) {
                minDouble = 0;
            } else {
                minDouble = Double.parseDouble(minStr);
            }
            maxStr = ParameterUtil.getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_MAX,
                    dataTypeVerifier);
            if (maxStr == null) {
                maxDouble = Double.POSITIVE_INFINITY;
            } else {
                maxDouble = Double.parseDouble(maxStr);
            }

            digit = ParameterUtil.getDigit(dataTypeVerifier);

            min = (int) (minDouble * (int) Math.pow(DECIMAL_SYSTEM, digit));
            max = (int) (maxDouble * (int) Math.pow(DECIMAL_SYSTEM, digit));

            inc = getIncrement(dataTypeVerifier, digit);
        }

        // in case of integer.
        if (DATA_TYPE_INT.equals(dataType)) {
            minStr = ParameterUtil.getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_MIN,
                    dataTypeVerifier);
            if (minStr == null) {
                min = 0;
            } else {
                min = (int) Integer.parseInt(minStr);
            }
            maxStr = ParameterUtil.getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_MAX,
                    dataTypeVerifier);
            if (maxStr == null) {
                max = (int) Integer.MAX_VALUE;
            } else {
                max = (int) Integer.parseInt(maxStr);
            }

            inc = getIncrement(dataTypeVerifier, digit);
        }

        // creates a spinner and initializes it.
        Spinner spinnerControl = new Spinner(parent, SWT.SINGLE | SWT.BORDER);
        spinnerControl.setMinimum(min);
        spinnerControl.setMaximum(max);
        spinnerControl.setIncrement(inc);
        spinnerControl.setDigits(digit);
        spinnerControl.pack();

        // sets the value.
        String value = parameterItem.getParameterValue();
        if (value == null) {
            spinnerControl.setSelection(min);
        } else {
            try {
                if (DATA_TYPE_INT.equals(dataType)) {
                    spinnerControl.setSelection(Integer.parseInt(value.trim()));
                }
                if (DATA_TYPE_DOUBLE.equals(dataType)) {
                    spinnerControl.setSelection((int) (Double
                            .parseDouble(value) * (int) Math.pow(
                            DECIMAL_SYSTEM, digit)));
                }
            } catch (NumberFormatException e) {
                spinnerControl.setSelection(min);
            }
        }

        return spinnerControl;
    }

    /**
     * Returns the incremental.
     * 
     * @param dataTypeVerifier the verifier.
     * @param digit decimal places.
     * @return the incremental.
     */
    private int getIncrement(DataTypeVerifier dataTypeVerifier, int digit) {
        int result = 1;
        String incStr = ParameterUtil.getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_INC,
                dataTypeVerifier);
        if (incStr != null) {
            // in case of integer.
            if (DATA_TYPE_INT.equals(dataTypeVerifier.getDataType())) {
                result = Integer.parseInt(incStr);
            }

            // in case of double.
            if (DATA_TYPE_DOUBLE.equals(dataTypeVerifier.getDataType())) {
                double incDouble = Double.parseDouble(incStr);
                result = (int) (incDouble * (int) Math.pow(DECIMAL_SYSTEM,
                        digit));
            }
        }

        return result;
    }

    /**
     * Creates a combo box.
     * 
     * @param parent the parent.
     * @param parameterItem the parameter.
     * @param dataTypeVerifier the verifier.
     * @return a combo box.
     */
    private Combo createComboControl(Composite parent,
            ParameterItem parameterItem, DataTypeVerifier dataTypeVerifier) {
        // create a combo box and initializes it.
        Combo comboControl = new Combo(parent, SWT.READ_ONLY | SWT.FLAT
                | SWT.BORDER);
        for (String str : dataTypeVerifier.getAttributeList()) {
            comboControl.add(str);
        }

        // sets the value.
        comboControl.setText(parameterItem.getParameterValue());

        return comboControl;
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
        // verifies.
        if (!verifyInput()) {
            return;
        }
        // updates the parameters.
        ParameterItem[] parameters = getParameters();
        for (int i = 0; i < controlList.size(); i++) {
            Control control = controlList.get(i);
            if (control.isEnabled()) {
                parameters[i].setParameterValue(getInputValue(i));
            } else {
                parameters[i].setParameterValue(backupParameterValues.get(i));
            }
        }
        String userdef007 = getPrameterString();
        nodeInfo.setAttribute(AttributeType.USERDEF007, userdef007);

        controlList.clear();
        super.okPressed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void cancelPressed() {
        controlList.clear();
        super.cancelPressed();
    }

    /**
     * Verifies.
     * 
     * @return true if and only if the parameters are verified;false otherwise.
     */
    private boolean verifyInput() {
        boolean result = true;
        if (vf == null) {
            return result;
        }
        ParameterItem[] parameters = getParameters();
        for (int i = 0; i < parameters.length; i++) {
            DataTypeVerifier dataVerifier = vf.get(parameters[i].getParameterId());
            if (dataVerifier != null && !dataVerifier.verify(getInputValue(i))) {
                result = false;
                MessageDialog.openError(getShell(), Messages.ParameterDialog_2, NLS
                        .bind(Messages.ParameterDialog_0, parameters[i]
                                .getParameterId()));
                break;
            }
        }
        return result;
    }

    /**
     * Returns the string that represents the parameter value.
     * 
     * @param index the index.
     * @return the string that represents the parameter value.
     */
    private String getInputValue(int index) {
        String result = null;
        Control control = controlList.get(index);
        if (control instanceof Text) {
            result = ((Text) control).getText();
        }
        if (control instanceof Spinner) {
            result = String.valueOf(((Spinner) control).getSelection());
            ParameterItem[] parameters = getParameters();
            DataTypeVerifier dataTypeVerifier = vf.get(parameters[index].getParameterId());
            if (DATA_TYPE_DOUBLE.equals(dataTypeVerifier.getDataType())) {
                int digit = ParameterUtil.getDigit(dataTypeVerifier);
                if (digit > 0) {
                    String positStr = null;
                    String digitStr = null;
                    if (result.length() > digit) {
                        positStr = result.substring(0, result.length() - digit);
                        digitStr = result.substring(result.length() - digit);
                    } else {
                        positStr = "0"; //$NON-NLS-1$
                        digitStr = frontZeroString(result, digit);
                    }
                    result = positStr.concat(".").concat(digitStr); //$NON-NLS-1$
                }
            }
        }
        if (control instanceof Combo) {
            result = ((Combo) control).getText();
        }
        return result;
    }

    /**
     * Sets the first digit to zero if the specified string that represent the double value does not have first digit.
     * 
     * @param str the string that represent the double value
     * @param count decimal places.
     * @return new string that represents the double value.
     */
    private String frontZeroString(String str, int count) {
        if (str == null) {
            str = ""; //$NON-NLS-1$
        }
        if (count > str.length()) {
            str = "0".concat(str); //$NON-NLS-1$
        }
        return str;
    }

    /**
     * Returns the string that represents the parameters.
     * 
     * @return the string that represents the parameters.
     */
    private String getPrameterString() {
        ParameterItem[] parameters = getParameters();
        return ParameterItem.getSavedString(Arrays.asList(parameters));
    }
    
}
