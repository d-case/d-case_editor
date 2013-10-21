/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.wizard;


import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.providers.FileExtensionRestrictTreeContentProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * An abstract class which is subclassed by all wizards to convert an XML file.
 */
public abstract class AbstractXmlConversionWizardPage extends WizardPage {

    /**
     * column numbers.
     */
    private static final int GRID_LAYOUT_COLUMN_NUMS = 2;

    /**
     * the label text for a GMF model file.
     */
    protected static final String GMF_MODEL_FILE_NAME = Messages.AbstractXmlConversionWizardPage_0;
    /**
     * the label text for a D-Case file.
     */
    protected static final String DCASE_MODEL_FILE_NAME = Messages.AbstractXmlConversionWizardPage_1;

    /**
     * the text for a dialog to select a GMF model file.
     */
    protected static final String DIALOG_TITLE_GMF_MODEL_FILE = Messages.AbstractXmlConversionWizardPage_2;
    /**
     * the text for a dialog to select a D-Case model file.
     */
    protected static final String DIALOG_TITLE_DCASE_MODEL_FILE = Messages.AbstractXmlConversionWizardPage_3;

    /**
     * the input filename.
     */
    private String inputModelFileName;
    /**
     * the output filename..
     */
    private String outputModelFileName;
    /**
     *  the title for a dialog to select the input file.
     */
    private String inputModelFileDialogTitle;
    /**
     * the title for a dialog to select the output file.
     */
    private String outputModelFileDialogTitle;

    /**
     * the file extension of the input file.
     */
    private String inputFileExtension;
    /**
     * the file extension of the output file.
     */
    private String outputFileExtension;

    /**
     * the selected object.
     */
    private IStructuredSelection selection;

    /**
     * the path to the input file.
     */
    private Text inputFilePath;
    /**
     * the path to the output file.
     */
    private Text outputFilePath;

    /**
     * determines whether the output file is allowed to override.
     */
    private boolean overwriteOption = false;
    
    /**
     * the path to the input model file.
     */
    private String inputModelFilePath = StringUtil.EMPTY;
    
    /**
     * the enable to input controls.
     */
    private boolean enabledInputControls = true;

    /**
     * the default message content.
     */
    private String defaultMessage = ""; //$NON-NLS-1$
    /**
     * Creates an instance and initializes it.
     * 
     * @param wizardTitle the title.
     * @param inputFileExtension the file extension of the input file.
     * @param outputFileExtension the file extension of the output file.
     * @param selection the selected object.
     */
    public AbstractXmlConversionWizardPage(String wizardTitle,
            String inputFileExtension, String outputFileExtension,
            IStructuredSelection selection) {

        super(Messages.AbstractXmlConversionWizardPage_4);

        this.inputFileExtension = inputFileExtension;
        this.outputFileExtension = outputFileExtension;
        this.selection = selection;

        setTitle(Messages.AbstractXmlConversionWizardPage_5);
        initDisplayText();
    }

    /**
     * Creates the top level control for this dialog page under the given parent composite.
     * 
     * @param parent the parent.
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout(GRID_LAYOUT_COLUMN_NUMS, false));

        Label inputFileLabel = new Label(composite, SWT.NULL);
        inputFileLabel.setText(Messages.AbstractXmlConversionWizardPage_6 + inputModelFileName);
        GridData lableGrid = new GridData();
        lableGrid.horizontalSpan = GRID_LAYOUT_COLUMN_NUMS;
        inputFileLabel.setLayoutData(lableGrid);

        inputFilePath = new Text(composite, SWT.BORDER);
        inputFilePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        inputFilePath.setEnabled(enabledInputControls);
        inputFilePath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                String outPath = outputFilePath.getText();
                if (outPath == null || outPath == "") { //$NON-NLS-1$
                    String autoFile = getModelFileName(inputFilePath.getText(),
                            inputFileExtension, outputFileExtension);
                    if (autoFile != null) {
                        outputFilePath.setText(autoFile);
                    }
                }
                doValidate();
            }
        });

        Button inputFileBrowseButton = new Button(composite, SWT.PUSH);
        inputFileBrowseButton.setText(Messages.AbstractXmlConversionWizardPage_7);
        inputFileBrowseButton.setEnabled(enabledInputControls);
        inputFileBrowseButton.addSelectionListener(createFileBrowseAdapter(
                inputFilePath, inputFileExtension,
                inputModelFileDialogTitle));

        Label outputFileLabel = new Label(composite, SWT.NULL);
        outputFileLabel.setText(Messages.AbstractXmlConversionWizardPage_8 + outputModelFileName);
        GridData lableGrid2 = new GridData();
        lableGrid2.horizontalSpan = GRID_LAYOUT_COLUMN_NUMS;
        outputFileLabel.setLayoutData(lableGrid2);

        outputFilePath = new Text(composite, SWT.BORDER);
        outputFilePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        outputFilePath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                doValidate();
            }
        });

        Button outputFileBrowseButton = new Button(composite, SWT.PUSH);
        outputFileBrowseButton.setText(Messages.AbstractXmlConversionWizardPage_9);
        outputFileBrowseButton.addSelectionListener(createFileBrowseAdapter(
                outputFilePath, outputFileExtension,
                outputModelFileDialogTitle));

        Group option = new Group(composite, SWT.SHADOW_ETCHED_IN);
        option.setText(Messages.AbstractXmlConversionWizardPage_10);
        GridData optionGrid = new GridData();
        optionGrid.horizontalSpan = GRID_LAYOUT_COLUMN_NUMS;
        option.setLayoutData(optionGrid);
        option.setLayout(new GridLayout(1, false));
        Button overwriteOptionButton = new Button(option, SWT.CHECK);
        overwriteOptionButton.setText(Messages.AbstractXmlConversionWizardPage_11);
        overwriteOptionButton.setSelection(overwriteOption);
        overwriteOptionButton.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
            }
            public void widgetSelected(SelectionEvent e) {
                Button bChk = (Button) e.widget;
                overwriteOption = bChk.getSelection();
                doValidate();
            }
        });

        setInitialSelection();
        
        doValidate();
        setControl(composite);
    }

    /**
     * Creates a selection adaptor for a file browse button.
     * 
     * @param text the text control.
     * @param fileExtension the file extension.
     * @param title the title for a dialog to select a file.
     * @return a selection adaptor.
     */
    private SelectionAdapter createFileBrowseAdapter(final Text text,
            final String fileExtension, final String title) {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {

                // create a tree selection dialog and initializes it.
                ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                        getShell(), new DecoratingLabelProvider(
                                new WorkbenchLabelProvider(), PlatformUI
                                        .getWorkbench().getDecoratorManager()
                                        .getLabelDecorator()),
                        new FileExtensionRestrictTreeContentProvider(fileExtension));

                // set projects from workspace.
                fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot()
                        .getProjects());

                fileDialog.setAllowMultiple(false);
                fileDialog.setBlockOnOpen(true);
                fileDialog.setTitle(title);

                fileDialog.open();

                Object[] results = fileDialog.getResult();
                if (results != null && results.length == 1
                        && results[0] instanceof IResource) {
                    text.setText(((IResource) results[0]).getFullPath()
                            .toString());
                }
            }
        };
    }

    /**
     * Returns the output filename the counter part of the input filename.
     * 
     * @param path the path to the input file.
     * @param originExtension the file extension of the input file.
     * @param derivedExtension the file extension of the output file.
     * @return the output filename.
     */
    private String getModelFileName(String path, String originExtension,
            String derivedExtension) {
        if (path == null || originExtension == null || derivedExtension == null) {
            return null;
        }

        String extension = FileUtil.getFileExtension(path);
        if (extension == null || !originExtension.equals(extension)) {
            return null;
        }

        return FileUtil.getFileBasePath(path) + "." + derivedExtension; //$NON-NLS-1$
    }

    /**
     * Initializes the input filename.
     */
    private void setInitialSelection() {
        if (selection == null) {
            return;
        }
        if (selection instanceof ITreeSelection) {
            ITreeSelection treeSelection = (ITreeSelection) selection;
            if (treeSelection.size() == 1 && treeSelection.getFirstElement() instanceof IFile) {
                IFile selFile = (IFile) treeSelection.getFirstElement();
                if (inputFileExtension.equals(selFile.getFileExtension())) {
                    inputFilePath.setText(selFile.getFullPath().toString());
                }
            }
        }
        if (selection.getFirstElement() instanceof ShapeNodeEditPart) {
            inputFilePath.setText(inputModelFilePath);
        }
    }

    /**
     * Validates.
     */
    private void doValidate() {

        setMessage(getDefaultMessage());
        setErrorMessage(null);

        if (!doValidateInput() || !doValidateOutput()) {
            setPageComplete(false);
            return;
        }

        setPageComplete(true);
    }

    /**
     * Validates the input file.
     * 
     * @return true if and only if the input file is valid;false otherwise.
     */
    private boolean doValidateInput() {

        String inputFileText = inputFilePath.getText();

        // tests whether the input file is null or empty.
        if (inputFileText == null || inputFileText.length() == 0) {
            setMessage(Messages.AbstractXmlConversionWizardPage_13);
            return false;
        }
        // tests whether the input file has valid extension.
        if (!inputFileExtension.equals(FileUtil
                .getFileExtension(inputFileText))) {
            setErrorMessage(Messages.AbstractXmlConversionWizardPage_14);
            return false;
        }
        // tests whether the input file exists.
        if (!FileUtil.isFileExistInWorkspace(inputFileText)) {
            setErrorMessage(Messages.AbstractXmlConversionWizardPage_15);
            return false;
        }

        return true;
    }

    /**
     * Validates the output file.
     * 
     * @return true if and only if the output file is valid;false otherwise.
     */
    private boolean doValidateOutput() {

        String outputFileText = outputFilePath.getText();

        // tests whether the output file is null or empty.
        if (outputFileText == null || outputFileText.length() == 0) {
            setMessage(Messages.AbstractXmlConversionWizardPage_16);
            return false;
        }
        // tests whether the output file has valid extension.
        if (outputFileExtension != null
                && !outputFileExtension.equals(FileUtil
                        .getFileExtension(outputFileText))) {
            setErrorMessage(Messages.AbstractXmlConversionWizardPage_17);
            return false;
        }
        // tests whether the output file exists.
        if (!overwriteOption && FileUtil.isFileExistInWorkspace(outputFileText)) {
            setErrorMessage(Messages.AbstractXmlConversionWizardPage_18);
            return false;
        }
        // tests the same files.
        String inputFileText = inputFilePath.getText();
        if (inputFileText != null && inputFileText.length() > 0) {
            if (inputFileText.compareToIgnoreCase(outputFileText) == 0) {
                setErrorMessage(Messages.AbstractXmlConversionWizardPage_19);
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the path to the input file.
     * 
     * @return the path to the input file.
     */
    public Text getInputFilePath() {
        return inputFilePath;
    }

    /**
     * the path to the output file.
     * 
     * @return the path to the output file.
     */
    public Text getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Returns the default message text.
     * 
     * @return default message text.
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

    /**
     * Tests whether the output file is allowed to override.
     * 
     * @return true if and only if the output file is allowed to override;false otherwise.
     */
    public boolean isOverwriteOption() {
        return overwriteOption;
    }
    /**
     * Sets whether the output file is allowed to override.
     * 
     * @param overwriteOption true if and only if the output file is allowed to override;false otherwise.
     */
    protected void setOverwriteOption(boolean overwriteOption) {
        this.overwriteOption = overwriteOption;
    }
    /**
     * Sets the input filename.
     * 
     * @param inputModelFileName the input filename.
     */
    protected void setInputModelFileName(String inputModelFileName) {
        this.inputModelFileName = inputModelFileName;
    }
    /**
     * Sets the output filename.
     * 
     * @param outputModelFileName the output filename.
     */
    protected void setOutputModelFileName(String outputModelFileName) {
        this.outputModelFileName = outputModelFileName;
    }
    /**
     * Sets the title for a dialog to select the input file.
     * 
     * @param inputModelFileDialogTitle the title for a dialog to select the input file.
     */
    protected void setInputModelFileDialogTitle(String inputModelFileDialogTitle) {
        this.inputModelFileDialogTitle = inputModelFileDialogTitle;
    }
    /**
     * Sets the title for a dialog to select the output file.
     * 
     * @param outputModelFileDialogTitle the title for a dialog to select the output file.
     */
    protected void setOutputModelFileDialogTitle(String outputModelFileDialogTitle) {
        this.outputModelFileDialogTitle = outputModelFileDialogTitle;
    }
    /**
     * Sets the input model file path.
     * 
     * @param filePath the input file path
     */
    protected void setInputModelFilePath(String filePath) {
        this.inputModelFilePath = filePath;
    }
    /**
     * Sets the enable to input controls.
     * 
     * @param enable the enable to input controls
     */
    protected void setEnabledInputControls(boolean enable) {
        this.enabledInputControls = enable;
    }

    /**
     * Sets the default message content.
     * 
     * @param defaultMessage the text of default message.
     */
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    /**
     * Initializes.
     */
    protected abstract void initDisplayText();
}
