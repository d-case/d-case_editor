/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.math.BigDecimal;
import java.util.List;

import net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem.DoubleParameterType;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem.EnumParameterType;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem.IntegerParameterType;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem.RawParameterType;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem.StringParameterType;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.editor.verifier.EnumVerifier;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard to edit parameter data types.
 */
public class ParameterDefinitionWizard extends Wizard {
    
    /**
     * the text width.
     */
    private static final int TEXT_WIDTH = 250;
    /**
     * the button width.
     */
    private static final int BUTTON_WIDTH = 80;
    /**
     * the list height.
     */
    private static final int HEIGHT_HINT = 200;
    
    /**
     * Array of data types. 
     */
    private static final String[] DATA_TYPE_ARRAY = new String[]{
        SystemDefinitionConst.DATA_TYPE_STRING,
        SystemDefinitionConst.DATA_TYPE_INT,
        SystemDefinitionConst.DATA_TYPE_DOUBLE,
        SystemDefinitionConst.DATA_TYPE_ENUM,
        SystemDefinitionConst.DATA_TYPE_RAW
    };

    /**
     * Array of parameter data. 
     */
    @SuppressWarnings("unchecked")
    private static final Class<? extends ParameterDataItem >[] PARAM_DATA_CLASS_ARRAY = new Class[] {
        StringParameterType.class,
        IntegerParameterType.class,
        DoubleParameterType.class,
        EnumParameterType.class,
        RawParameterType.class
    };
    
    /**
     * Array of parameter data setting pages. 
     */
    private final ParamSettingWizardPage[] paramSettingPageArray = new ParamSettingWizardPage[] {
        new StringParamSettingWizardPage(),
        new IntParamSettingWizardPage(),
        new DoubleParamSettingWizardPage(),
        new EnumParamSettingWizardPage(),
        new RawParamSettingWizardPage()
    };
    
    /**
     * The list of parameter data.
     */
    private final List<ParameterDataItem> parameterList;
    
    /**
     * The index of the selected parameter data in dialog.
     */
    private int index = -1;
    
    /**
     * The selected parameter data in dialog.
     */
    private ParameterDataItem originalParameterData;
    
    /**
     * The editing parameter data in this wizard.
     */
    private ParameterDataItem selectedParameterData;
    
    /**
     * The first wizard page.
     */
    private ParamDefinitionPage firstPage;
    
    /**
     * The second wizard page to set the parameter data value.
     */
    private ParamSettingWizardPage parameterSettingPage;
    
    /**
     * Returns result of editing parameter data.
     * In the case of create or edit, return result parameter data,
     * in the case of cancel, return, return original parameter data.  
     * @return the selectedParameterData.
     */
    public ParameterDataItem getResultedParameterdata() {
        return selectedParameterData;
    }
     
    /**
     * Constructor in the case of creation.
     * @param parameterList the list of parameter data
     */
    public ParameterDefinitionWizard(List<ParameterDataItem> parameterList) {
        super();
        setForcePreviousAndNextButtons(true);
        this.parameterList = parameterList;
        firstPage = new ParamDefinitionPage();
    }
    
    /**
     * Constructor in the case of edit.
     * @param ind the index of the selected parameter data in the dialog.
     * @param parameterList the list of parameter data.
     */
    public ParameterDefinitionWizard(int ind, List<ParameterDataItem> parameterList) {
        super();
        setForcePreviousAndNextButtons(true);
        this.index = ind;
        this.parameterList = parameterList;
        try {
            this.originalParameterData = (ParameterDataItem) parameterList.get(ind);
            this.selectedParameterData = (ParameterDataItem) this.originalParameterData.clone();
        } catch (CloneNotSupportedException e) {
            throw new DcaseSystemException(e.getMessage(), null, MessageTypeImpl.UNDEFINED);
        }
        firstPage = new ParamDefinitionPage();
    }
    
    /**
     * Adds first page to this wizard.
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
       addPage(firstPage);
    }

    /**
     * Returns the previous page.
     * when current page is ParamSettingWizardPage, return firstPage,
     * in other case, return null.
     * @param page the current page
     * @return the previous page
     * @see org.eclipse.jface.wizard.Wizard#getPreviousPage(IWizardPage)
     */
    public IWizardPage getPreviousPage(IWizardPage page) {
        if (page instanceof ParamSettingWizardPage) {
            return firstPage;
        } else {
            return null;
        }
    }
    
    /**
     * Returns the next page.
     * when current page is firstPage, return it's next page,
     * in other case, return null.
     * @param page the current page
     * @return next page
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(IWizardPage)
     */
    public IWizardPage getNextPage(IWizardPage page) {
        if (page == firstPage) {
            return page.getNextPage();
        } else {
            return null;
        }
    }
    
    /**
     * Checks if this wizard can finish or not.
     * 
     * @return if this wizard can finish, returns true.
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    public boolean canFinish() {
        if (getContainer().getCurrentPage() == firstPage) {
            return false;
        } else if (getContainer().getCurrentPage() == parameterSettingPage) {
            return parameterSettingPage.isPageComplete(); 
        }
        return true;
    }

    /**
     * When the finish button is pushed, selectedParameterData is set and
     * patameterList is updated.
     * 
     * @return true
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        selectedParameterData = parameterSettingPage.getParameter();
        if (index >= 0) {
            parameterList.remove(index);
            parameterList.add(index, selectedParameterData);
        } else {
            parameterList.add(selectedParameterData);
        }
        return true;
    }
    
    /**
     * When The cancel button is pushed, 
     * original parameter data is set to selectedParameterData.
     * 
     * @return true
     * @see org.eclipse.jface.wizard.Wizard#performCancel()
     */
    @Override
    public boolean performCancel() {
        selectedParameterData = originalParameterData;
        return super.performCancel();
    }

    /**
     * Returns the editing parameter data.
     * @return the editing parameter data.
     */
    public ParameterDataItem getEditParamDatatypeItem() {
        if (parameterSettingPage != null) {
            return parameterSettingPage.getParameter();
        }
        return null;
    }
    
    /**
     * The first page class.
     */
    private class ParamDefinitionPage extends WizardPage {
        
        /**
         * column number.
         */
        private static final int GRID_COLUMNS = 2;
        
        /**
         * the text control for the name.
         */
        private Text nameText;
        
        /**
         * the combo box for the data type.
         */
        private Combo typeCombo;
        
        /**
         * Constructor.
         */
        protected ParamDefinitionPage() {
            super("ParamDifinitionPage"); //$NON-NLS-1$
            if (index >= 0) {
                setTitle(Messages.ParameterDatatypeWizard_EditParamDefinitionPageTitle);
            } else {
                setTitle(Messages.ParameterDatatypeWizard_CreationParamDefinitionPage);
            }
            setPageComplete(false);
        }

        /**
         * Creates dialog controls.
         * @param parent the parent composite
         * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
         */
        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NULL);
            composite.setLayout(new GridLayout(GRID_COLUMNS, false));
            createWizardControl(composite);
            setControl(composite);
        }
        
        /**
         * Creates wizard page controls.
         * @param parent the parent composite
         */
        public void createWizardControl(Composite parent) {
            
            //Name
            Label label = new Label(parent, SWT.WRAP);
            label.setText(Messages.ParameterDatatypeWizard_ParamDifinitionPage_NameLabel);
            
            nameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
            nameText.addModifyListener(new ModifyListener() {
                public void modifyText(ModifyEvent arg0) {
                    validateComplete();
                }
            });
            GridData nameTextData = new GridData();
            nameTextData.horizontalAlignment = GridData.FILL;
            nameTextData.widthHint = TEXT_WIDTH;
            nameText.setLayoutData(nameTextData);

            //Requirement
            Label labe2 = new Label(parent, SWT.WRAP);
            labe2.setText(Messages.ParameterDatatypeWizard_ParamDifinitionPage_TypeLabel);
            
            typeCombo = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
            typeCombo.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent arg0) {
                    validateComplete();
                }
                public void widgetDefaultSelected(SelectionEvent arg0) {
                }
            });
            for (String type : DATA_TYPE_ARRAY) {
                typeCombo.add(type);
            }

            GridData typeCombodata = new GridData();
            typeCombodata.horizontalAlignment = GridData.FILL;
            typeCombodata.widthHint = TEXT_WIDTH;
            typeCombo.setLayoutData(typeCombodata);
            //in the edit case.
            if (selectedParameterData != null) {
                
                nameText.setText(selectedParameterData.getName());
                nameText.setEditable(false);
                
                setComboSelect(selectedParameterData);
                
                setPageComplete(true);
            }
        }
        
        /**
         * Sets the selection of combo box.
         * @param selectedParameterData the selected parameter data.
         */
        private void setComboSelect(ParameterDataItem selectedParameterData) {
            for (int i = 0; i < PARAM_DATA_CLASS_ARRAY.length; i++) {
                Class<? extends ParameterDataItem> paramClass = PARAM_DATA_CLASS_ARRAY[i];
                if (paramClass.isInstance(selectedParameterData)) {
                    typeCombo.select(i);
                    typeCombo.setEnabled(false);
                }
            }
        }
        
        /**
         * Sets the finish button enable when the wizard can finish.
         */
        private void validateComplete() {
            if ((nameText.getText() != null && nameText.getText().length() > 0)
                    && typeCombo.getSelectionIndex() >= 0) {
                
                // checks if forbidden characters exist.
                try {
                    DataTypeVerifier.validString(
                            false, SystemDefinitionConst.DATA_ATTRIBUTE_NAME, nameText.getText());
                } catch (Exception e) {
                    setErrorMessage(e.getMessage());
                    setPageComplete(false);
                    return;
                }
                
                // checks duplication of parameter names.
                for (ParameterDataItem param : parameterList) {
                    if (param != originalParameterData
                            && nameText.getText().equals(param.getName())) {
                        setErrorMessage(Messages.ParameterDatatypeWizard_ParamDifinitionPage_NameDuplicationErrMessage);
                        setPageComplete(false);
                        return;
                    }
                }
                setErrorMessage(null);
                setPageComplete(true);
            } else {
                setPageComplete(false);
            }
        }
        
        /**
         * Returns the next page.
         * @return the next page
         */
        @Override
        public IWizardPage getNextPage() {
            
            ParamSettingWizardPage page
                = paramSettingPageArray[typeCombo.getSelectionIndex()];
            
            ParameterDefinitionWizard.this.parameterSettingPage = page;

            return ParameterDefinitionWizard.this.parameterSettingPage;
        }
        
        /**
         * Returns the parameter name that user input.
         * @return the parameter name.
         */
        protected String getParamName() {
            return nameText.getText();
        }
    }

    /**
     * The base class of the wizard page to set parameter data value. 
     */
    private abstract class ParamSettingWizardPage extends WizardPage {
        
        /**
         * Constructor.
         * @param pageName the page name
         * @param title the page title
         */
        protected ParamSettingWizardPage(String pageName, String title) {
            super(pageName);
            setTitle(title);
            setWizard(ParameterDefinitionWizard.this);
            setControl(null);
        }
        
        /**
         * Returns the editing parameter data.
         * @return the editing parameter data.
         */
        protected abstract ParameterDataItem getParameter();
        
        /**
         * Returns the next page.
         * @return null
         */
        @Override
        public IWizardPage getNextPage() {
            return null;
        }
        /**
         */
        protected void validate() {
            try {
                getParameter().validate();
                
                setErrorMessage(null);
                setPageComplete(true);
            } catch (Exception e) {
                setErrorMessage(e.getMessage());
                setPageComplete(false);
            }
        }
    }
    
    /**
     * The wizard page class to configure the string data type.
     */
    private class StringParamSettingWizardPage extends ParamSettingWizardPage {
        
        /**
         * the Grid number.
         */
        private static final int GRID_NUM = 4;
        /**
         * the default value of min.
         */
        private static final int DEFAULT_MIN = 0;
        /**
         * the default value of min.
         */
        private static final int MAX_MIN = 1;
        /**
         * the initial value of max for creation.
         */
        private static final int INITIAL_MAX = 256;
        /**
         * the default value of max for edit.
         */
        private static final int DEFAULT_MAX = Integer.MAX_VALUE;
        /**
         * the incremental value.
         */
        private static final int INC = 1;
        /**
         * the class for creating the editing parameter data.
         */
        private final Class<? extends ParameterDataItem> paramDataClass;
        /**
         * the spinner control for minimum.
         */
        private Spinner minSpinner;
        /**
         * the spinner control for maximum.
         */
        private Spinner maxSpinner;
        /**
         * the text control for element name.
         */
        private String elementName;
        /**
         * the spinner listener.
         */
        private ParamDataSelectionListener listener = new ParamDataSelectionListener();
                
        /**
         * Constructor.
         */
        public StringParamSettingWizardPage() {
            super(
                    "StringParamSettingWizardPage", //$NON-NLS-1$
                    Messages.ParameterDatatypeWizard_StringParamSettingWizardPage_PageTile);
            paramDataClass = StringParameterType.class;
            this.elementName = Messages.ParameterDatatypeWizard_StringParamSettingWizardPage_LengthLabel;
        }
        
        /**
         * @return the paramDatatypeClass
         */
        public Class<? extends ParameterDataItem> getParamDataClass() {
            return paramDataClass;
        }
        
        /**
         * Constructor.
         * 
         * @param paramDataClass the parameter data class
         * @param pageName the page name
         * @param title the page title
         */
        protected StringParamSettingWizardPage(
                Class<? extends ParameterDataItem> paramDataClass, String pageName, String title) {
            super(pageName , title);
            this.paramDataClass = paramDataClass;
            this.elementName = Messages.ParameterDatatypeWizard_StringParamSettingWizardPage_LengthLabel;
        }
        
        /**
         * Constructor. 
         * @param paramDataClass the parameter data class
         * @param pageName the page name
         * @param title the page title
         * @param elementName the element name
         */
        protected StringParamSettingWizardPage(
                Class<? extends ParameterDataItem> paramDataClass,
                String pageName,
                String title,
                String elementName) {
            super(pageName, title);
            this.paramDataClass = paramDataClass;
            this.elementName = elementName;
        }

        /**
         * Creates dialog controls.
         * @param parent the parent composite
         * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
         */
        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NULL);
            composite.setLayout(new GridLayout(GRID_NUM, false));
            createWizardControl(composite);
            
            setValuesToControl();
            
            setControl(composite);
        }
        
        /**
         * Creates the wizard controls.
         * @param parent the parent composite
         */
        protected void createWizardControl(Composite parent) {
            //Range
            Label label1 = new Label(parent, SWT.WRAP);
            label1.setText(elementName);
            
            // min
            minSpinner = new Spinner(parent, SWT.BORDER);
            minSpinner.setMinimum(DEFAULT_MIN);
            minSpinner.setMaximum(Integer.MAX_VALUE);
            minSpinner.setIncrement(INC);
            minSpinner.setSelection(DEFAULT_MIN);
            minSpinner.addSelectionListener(listener);
            GridData minData = new GridData();
            minData.horizontalAlignment = GridData.FILL;
            minData.widthHint = TEXT_WIDTH;
            minSpinner.setLayoutData(minData);
            
            //-
            Label label2 = new Label(parent, SWT.WRAP);
            label2.setText("-"); //$NON-NLS-1$
            
            //max
            maxSpinner = new Spinner(parent, SWT.BORDER);
            maxSpinner.setMinimum(MAX_MIN);
            maxSpinner.setMaximum(Integer.MAX_VALUE);
            maxSpinner.setIncrement(INC);
            if (ParameterDefinitionWizard.this.selectedParameterData == null) {
                // In the creation case, sets 255 to the max spinner.
                maxSpinner.setSelection(INITIAL_MAX);
            } else {
                // In the edit case, sets Integer.MAX_VALUE to the max spinner.
                maxSpinner.setSelection(DEFAULT_MAX);
            }
            maxSpinner.addSelectionListener(listener);
            GridData maxData = new GridData();
            maxData.horizontalAlignment = GridData.FILL;
            maxData.widthHint = TEXT_WIDTH;
            maxSpinner.setLayoutData(maxData);
        }
        
        /**
         * Sets the parameter values to controls, and validates.
         */
        protected void setValuesToControl() {
            //in the edit case, set values to controls.
            if (ParameterDefinitionWizard.this.selectedParameterData != null
                    && ParameterDefinitionWizard.this.selectedParameterData.getClass() == getParamDataClass()) {
                ParameterDataItem param = ParameterDefinitionWizard.this.selectedParameterData;
                setParameter(param);
            } else {
                setParameter(null);
            }
            validate();
        }
        
        /**
         * Sets the value of parameter data to controls.
         * @param param  the parameter data
         */
        protected void setParameter(ParameterDataItem param) {
            if (param != null && param.getMin() != null) {
                minSpinner.setSelection(param.getMin());
            }
            if (param != null && param.getMax() != null) {
                maxSpinner.setSelection(param.getMax());
            }
        }
        
        /** 
         * Returns the editing parameter data.
         * @return the editing parameter data
         * @see net.dependableos.dcase.diagram.editor.ui.ParameterDefinitionWizard.ParamSettingWizardPage#getParameter()
         */
        @Override
        public ParameterDataItem getParameter() {
            StringParameterType param = new StringParameterType();
            param.setName(firstPage.getParamName());
            if (minSpinner.getText() != null && minSpinner.getText().length() > 0) {
                param.setMin(minSpinner.getSelection());
            }
            if (maxSpinner.getText() != null && maxSpinner.getText().length() > 0) {
                param.setMax(maxSpinner.getSelection());
            }
            return param;
        }
        
        /**
         * Validates the range.
         */
        protected void validate() {
            try {
                getParameter().validate();
                setErrorMessage(null);
                setPageComplete(true);
            } catch (DcaseSystemException e) {
                setErrorMessage(e.getMessage());
                setPageComplete(false);
            }
        }
        
        /**
         * Returns the minText.
         * @return the minText
         */
        public Spinner getMinSpinner() {
            return minSpinner;
        }

        /**
         * Returns the maxText.
         * @return the maxText
         */
        public Spinner getMaxSpinner() {
            return maxSpinner;
        }
        
        /**
         * the listener class to validate the range of length.
         */
        private class ParamDataSelectionListener extends SelectionAdapter {

            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.getSource() == minSpinner || e.getSource() == maxSpinner) {
                    validate();
                }
            }
        }
    }
    
    /**
     * The wizard page class to configure the int data type.
     */
    private class IntParamSettingWizardPage extends StringParamSettingWizardPage {
        
        /**
         * the initial max value for creation. 
         */
        private static final int INITIAL_MAX = 100;
        
        /**
         * Constructor.
         */
        public IntParamSettingWizardPage() {
            super(
                    IntegerParameterType.class,
                    "IntParamSettingWizardPage", //$NON-NLS-1$
                    Messages.ParameterDatatypeWizard_IntParamSettingWizardPage_PageTitle,
                    Messages.ParameterDatatypeWizard_IntParamSettingWizardPage_RangeLabel);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        protected void createWizardControl(Composite parent) {
            super.createWizardControl(parent);
            getMaxSpinner().setMinimum(Integer.MIN_VALUE);
            getMinSpinner().setMinimum(Integer.MIN_VALUE);
        }
        
        /**
         * Set the value of parameter data to controls.
         * @param param  the parameter data
         */
        protected void setParameter(ParameterDataItem param) {
            super.setParameter(param);
            
            if (param == null) {
                getMaxSpinner().setSelection(INITIAL_MAX);
            } else {
                getMaxSpinner().setSelection(Integer.MAX_VALUE);
            }
            
            if (param != null && param.getMax() != null) {
                getMaxSpinner().setSelection(param.getMax());
            }
        }

        /**
         * Returns the editing enum parameter data.
         * @return the editing int parameter data 
         */
        @Override
        public ParameterDataItem getParameter() {
            IntegerParameterType param = new IntegerParameterType();
            param.setName(firstPage.getParamName());
            if (getMinSpinner().getText() != null && getMinSpinner().getText().length() > 0) {
                param.setMin(Integer.parseInt(getMinSpinner().getText()));
            }
            if (getMaxSpinner().getText() != null && getMaxSpinner().getText().length() > 0) {
                param.setMax(Integer.parseInt(getMaxSpinner().getText()));
            }
            return param;
        }
    }
    
    /**
     * The wizard page class to configure the double data type.
     */
    private class DoubleParamSettingWizardPage extends ParamSettingWizardPage {
        
        /**
         * the number of horizontal grid.
         */
        private static final int GRID_NUM = 4;
        /**
         * the number of horizontal span.
         */
        private static final int HORIZONTAL_SPAN = 3;
        /**
         * the radix of decimal number.
         */
        private static final  int DECIMAL_RADIX = 10;
        /**
         * the constant value of digit.
         */
        private static final int CONST_DIGIT = 1;
        /**
         * the default value of digit.
         */
        private static final int DEFAULT_DIGIT = 2;
        /**
         * the minimum value to input range.
         */
        private static final double INITIAL_MIN = 0.0d;
        /**
         * the initial maximum value to input range for create.
         */
        private static final double INITIAL_MAX = 100.0d;
        /**
         * the default maximum value to input range for edit.
         */
        private static final double DEFAULT_MAX = Integer.MAX_VALUE;
        /**
         * the initial value of incremental.
         */
        private static final double INITIAL_INC = 0.1d;
        /**
         * the min value of incremental.
         */
        private static final int MIN_INC = 1;
        /**
         * the sinner control for the minimum.
         */
        private Spinner minSpinner;
        /**
         * the sinner control for the maximum.
         */
        private Spinner maxSpinner;
        /**
         * the sinner control for the digit.
         */
        private Spinner digitSpinner;
        /**
         * the sinner control for the incremental.
         */
        private Spinner incrementalSpinner;
        /**
         * the spinner listener.
         */
        private DoubleParamDataSelectionListener listener = new DoubleParamDataSelectionListener();
        
        /**
         * Constructor.
         */
        public DoubleParamSettingWizardPage() {
            super(
                    "DoubleParamSettingWizardPage", //$NON-NLS-1$
                    Messages.ParameterDatatypeWizard_DoubleParamSettingWizardPage_PageTitle);
        }

        /**
         * Creates dialog controls.
         * @param parent the parent composite
         * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
         */
        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NULL);
            composite.setLayout(new GridLayout(GRID_NUM, false));
            createWizardControl(composite);
            setControl(composite);
        }
        
        /**
         * Creates wizard controls.
         * @param parent the parent composite
         */
        private void createWizardControl(Composite parent) {
            // Range
            Label label1 = new Label(parent, SWT.WRAP);
            label1.setText(Messages.ParameterDatatypeWizard_DoubleParamSettingWizardPage_RangeLabel);

            // min
            minSpinner = new Spinner(parent, SWT.BORDER);
            minSpinner.setMaximum(Integer.MAX_VALUE);
            minSpinner.setMinimum(Integer.MIN_VALUE);
            minSpinner.setDigits(DEFAULT_DIGIT);
            minSpinner.setIncrement(MIN_INC);
            minSpinner
                    .setSelection(convertIntValue(INITIAL_MIN));
            minSpinner.addSelectionListener(listener);
            GridData minData = new GridData();
            minData.horizontalAlignment = GridData.FILL;
            minData.widthHint = TEXT_WIDTH;
            minSpinner.setLayoutData(minData);

            // -
            Label label2 = new Label(parent, SWT.WRAP);
            label2.setText("-"); //$NON-NLS-1$

            // max
            maxSpinner = new Spinner(parent, SWT.BORDER);
            maxSpinner.setMaximum(Integer.MAX_VALUE);
            maxSpinner.setMinimum(Integer.MIN_VALUE);
            maxSpinner.setDigits(DEFAULT_DIGIT);
            maxSpinner.setIncrement(MIN_INC);
            if (ParameterDefinitionWizard.this.selectedParameterData == null) {
                // In the creation case, sets 100.0d to the max spinner.
                maxSpinner.setSelection(convertIntValue(INITIAL_MAX));
            } else {
                // In the edit case, sets nteger.MAX_VALUE to the max spinner.
                maxSpinner.setSelection(convertIntValue(DEFAULT_MAX));
            }
            maxSpinner.addSelectionListener(listener);
            GridData maxData = new GridData();
            maxData.horizontalAlignment = GridData.FILL;
            maxData.widthHint = TEXT_WIDTH;
            maxSpinner.setLayoutData(maxData);

            // digit
            Label label3 = new Label(parent, SWT.WRAP);
            label3.setText(Messages.ParameterDatatypeWizard_DoubleParamSettingWizardPage_DigitLabel);
            digitSpinner = new Spinner(parent, SWT.BORDER);
            digitSpinner.setMaximum(Integer.MAX_VALUE);
            digitSpinner.setMinimum(CONST_DIGIT);
            digitSpinner.addSelectionListener(listener);
            digitSpinner.setSelection(DEFAULT_DIGIT);
            GridData digitData = new GridData();
            digitData.horizontalAlignment = GridData.FILL;
            digitData.widthHint = TEXT_WIDTH;
            digitData.horizontalSpan = HORIZONTAL_SPAN;
            digitSpinner.setLayoutData(digitData);

            // incremental
            Label label4 = new Label(parent, SWT.WRAP);
            label4.setText(Messages.ParameterDatatypeWizard_DoubleParamSettingWizardPage_IncrementalLabel);
            incrementalSpinner = new Spinner(parent, SWT.BORDER);
            incrementalSpinner.setMaximum(Integer.MAX_VALUE);
            incrementalSpinner.setMinimum(MIN_INC);
            incrementalSpinner.setIncrement(MIN_INC);
            incrementalSpinner.setDigits(DEFAULT_DIGIT);
            incrementalSpinner.setSelection(convertIntValue(INITIAL_INC));
            incrementalSpinner.addSelectionListener(listener);
            GridData incrementalData = new GridData();
            incrementalData.horizontalAlignment = GridData.FILL;
            incrementalData.widthHint = TEXT_WIDTH;
            incrementalData.horizontalSpan = HORIZONTAL_SPAN;
            incrementalSpinner.setLayoutData(incrementalData);

            // In the edit case, set the value of the selected parameter data to
            // controls.
            if (ParameterDefinitionWizard.this.selectedParameterData instanceof DoubleParameterType) {
                DoubleParameterType param = (DoubleParameterType) ParameterDefinitionWizard.this.selectedParameterData;
                int digit = DEFAULT_DIGIT;
                if (param.getDigit() != null) {
                    digit = param.getDigit();
                    digitSpinner.setSelection(digit);
                }
                applyDigit();

                if (param.getDoubleMin() != null) {
                    minSpinner.setSelection(convertIntValue(digit,
                            param.getDoubleMin()));
                }
                if (param.getDoubleMax() != null) {
                    maxSpinner.setSelection(convertIntValue(digit,
                            param.getDoubleMax()));
                }
                if (param.getIncremental() != null) {
                    incrementalSpinner.setSelection(convertIntValue(digit,
                            param.getIncremental()));
                }
            }
            validate();
        }
        
        /**
         * Returns the editing double parameter data.
         * 
         * @return the editing double parameter data
         * @see net.dependableos.dcase.diagram.editor.ui.ParameterDefinitionWizard.ParamSettingWizardPage
         *      #getParameter()
         */
        public ParameterDataItem getParameter() {
            DoubleParameterType param = new DoubleParameterType();
            param.setName(firstPage.getParamName());
            if (minSpinner.getText() != null && minSpinner.getText().length() > 0) {
                param.setDoubleMin(convertDoubleValue(minSpinner));
            }
            if (maxSpinner.getText() != null && maxSpinner.getText().length() > 0) {
                param.setDoubleMax(convertDoubleValue(maxSpinner));
            }
            if (digitSpinner.getText() != null && digitSpinner.getText().length() > 0) {
                param.setDigit(Integer.parseInt(digitSpinner.getText()));
            }
            if (incrementalSpinner.getText() != null && incrementalSpinner.getText().length() > 0) {
                param.setIncremental(convertDoubleValue(incrementalSpinner));
            }
            return param;
        }
        
        /**
         * Returns the int value of argument to represent in spinner control.
         * @param value the double value.
         * @return the int value to represent in spinner control.
         */
        private int convertIntValue(double value) {
            return convertIntValue(DEFAULT_DIGIT, value);
        }
        
        /**
         * Returns the int value to set to a spinner control.
         * 
         * @param digit the digits.
         * @param value the double value.
         * @return the int value to set to a spinner control.
         */
        private int convertIntValue(int digit, double value) {
            BigDecimal multipler = new BigDecimal(String.valueOf(DECIMAL_RADIX));
            multipler = multipler.pow(digit);
            BigDecimal result = new BigDecimal(String.valueOf(value));
            result = result.multiply(multipler);
            return result.intValue();
        }
        
        /**
         * Returns the double value that is specified with a spinner control. 
         * @param spinner the source spinner.
         * @return the double value. 
         */
        private double convertDoubleValue(Spinner spinner) {
            return Double.parseDouble(spinner.getText());
        }
        
        /**
         * Applies the specified digit to controls.
         */
        private void applyDigit() {
            double inc = convertDoubleValue(incrementalSpinner);
            double max = convertDoubleValue(maxSpinner);
            double min = convertDoubleValue(minSpinner);
            
            int digit = digitSpinner.getSelection();
            // sets digit
            incrementalSpinner.setDigits(digit);
            maxSpinner.setDigits(digit);
            minSpinner.setDigits(digit);

            // sets selection
            incrementalSpinner.setSelection(convertIntValue(digit, inc));
            maxSpinner.setSelection(convertIntValue(digit, max));
            minSpinner.setSelection(convertIntValue(digit, min));
        }
        
        /**
         * the listener class to validate the range.
         */
        private class DoubleParamDataSelectionListener extends SelectionAdapter {

            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.getSource() == minSpinner) {
                    validate();
                } else if (e.getSource() == maxSpinner) {
                    validate();
                } else if (e.getSource() == incrementalSpinner) {
                    validate();
                } else if (e.getSource() == digitSpinner) {
                    applyDigit();
                }
            }
        }
    }
    
    /**
     * The wizard page class for the enum parameter data type.
     */
    private class EnumParamSettingWizardPage extends ParamSettingWizardPage {
        /**
         * the number of horizontal grid.
         */
        private static final int GRID_NUM = 4;
        /**
         * the list of  enum items.
         */
        private org.eclipse.swt.widgets.List enumulationList;
        /**
         * the text control for item.
         */
        private Text itemText;
        /**
         * the item creation button.
         */
        private Button addBut;
        /**
         * the item deletion button.
         */
        private Button delBut;
        
        /**
         * Constructor.
         */
        public EnumParamSettingWizardPage() {
            super(
                    "EnumParamSettingWizardPage", //$NON-NLS-1$
                    Messages.ParameterDatatypeWizard_EnumParamSettingWizardPage_PageTitle);
        }

        /**
         * Creates dialog controls.
         * @param parent the parent composite
         * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
         */
        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, SWT.NULL);
            composite.setLayout(new GridLayout(2, false));
            createWizardControl(composite);
            setControl(composite);
        }
        
        /**
         * Creates wizard controls.
         * @param parent the parent composite
         */
        private void createWizardControl(Composite parent) {
            
            //List
            enumulationList = new org.eclipse.swt.widgets.List(
                    parent, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
            enumulationList.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    if (enumulationList.getSelectionIndex() >= 0) {
                        delBut.setEnabled(true);
                    } else {
                        delBut.setEnabled(false);
                    }
                }
            });
            GridData listData = new GridData(GridData.FILL_BOTH);
            listData.grabExcessVerticalSpace = true;
            listData.verticalSpan = GRID_NUM;
            listData.heightHint = HEIGHT_HINT;
            enumulationList.setLayoutData(listData);
            
            //Name
            itemText = new Text(parent, SWT.SINGLE | SWT.BORDER);
            itemText.addModifyListener(new ModifyListener() {
                public void modifyText(ModifyEvent arg0) {
                    validate();
                }
            });
            GridData itemTextData = new GridData();
            itemTextData.horizontalAlignment = GridData.FILL;
            itemTextData.widthHint = TEXT_WIDTH;
            itemText.setLayoutData(itemTextData);
            
            //item creation
            addBut = new Button(parent, SWT.PUSH | SWT.CENTER);
            addBut.setText(Messages.ParameterDatatypeWizard_EnumParamSettingWizardPage_CreationButton);
            addBut.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    enumulationList.add(itemText.getText());
                    itemText.setText(""); //$NON-NLS-1$
                    validate();
                }
            });
            GridData addButData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
            addButData.widthHint = BUTTON_WIDTH;
            addBut.setLayoutData(addButData);
            addBut.setEnabled(false);
            
            //item deletion
            delBut = new Button(parent, SWT.PUSH | SWT.CENTER);
            delBut.setText(Messages.ParameterDatatypeWizard_EnumParamSettingWizardPage_DeletionButton);
            delBut.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    enumulationList.remove(enumulationList.getSelectionIndex());
                    delBut.setEnabled(false);
                    validate();
                }
            });
            GridData delButData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
            delButData.widthHint = BUTTON_WIDTH;
            delBut.setLayoutData(delButData);
            delBut.setEnabled(false);
            
            // in the edit case.
            if (ParameterDefinitionWizard.this.selectedParameterData instanceof EnumParameterType) {
                EnumParameterType param = 
                        (EnumParameterType) ParameterDefinitionWizard.this.selectedParameterData;
                for (String item : param.getEnumList()) {
                    enumulationList.add(item);
                }
            }
            
            validate();
        }
        
        /**
         * Validates the enum parameter data.
         */
        protected void validate() {

            String newItem = itemText.getText();
            if (newItem != null && newItem.length() > 0) {
                // checks if item value is valid or not.
                try {
                    EnumVerifier.validItemString(newItem);
                } catch (Exception e) {
                    setErrorMessage(e.getMessage());
                    addBut.setEnabled(false);
                    return;
                }
                for (String item : enumulationList.getItems()) {
                    // checks if item name is duplicated nor not.
                    if (newItem.equals(item)) {
                        setErrorMessage(
                                Messages.ParameterDatatypeWizard_EnumParamSettingWizardPage_ItemDuplicationErrMessage);
                        addBut.setEnabled(false);
                        return;
                    }
                }
                addBut.setEnabled(true);
            } else {
                addBut.setEnabled(false);
            }
            super.validate();
        }
        
        
        /**
         * Returns the editing enum parameter data.
         * 
         * @return the editing enum parameter data
         * @see net.dependableos.dcase.diagram.editor.ui.ParameterDefinitionWizard.ParamSettingWizardPage
         *      #getParameter()
         */
        public ParameterDataItem getParameter() {
            EnumParameterType param = new EnumParameterType();
            param.setName(firstPage.getParamName());
            for (String item : enumulationList.getItems()) {
                param.addEnumList(item);
            }
            return param;
        }
    }
    
    /**
     * The wizard page class for the raw parameter data type.
     */
    private class RawParamSettingWizardPage extends StringParamSettingWizardPage {
        /**
         * Constructor.
         */
        public RawParamSettingWizardPage() {
            super(
                    RawParameterType.class,
                    "RawParamSettingWizardPage", //$NON-NLS-1$
                    Messages.ParameterDatatypeWizard_RawParamSettingWizardPage_PageTitle);
        }
        /**
         * Returns the editing raw parameter data.
         * 
         * @return the editing raw parameter data
         * @see net.dependableos.dcase.diagram.editor.ui.ParameterDefinitionWizard.StringParamSettingWizardPage
         *      #getParameter()
         */
        public ParameterDataItem getParameter() {
            RawParameterType param = new RawParameterType();
            param.setName(firstPage.getParamName());
            if (getMinSpinner().getText() != null && getMinSpinner().getText().length() > 0) {
                param.setMin(Integer.parseInt(getMinSpinner().getText()));
            }
            if (getMaxSpinner().getText() != null && getMaxSpinner().getText().length() > 0) {
                param.setMax(Integer.parseInt(getMaxSpinner().getText()));
            }
            return param;
        }
    }
}
