package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.editor.logic.findandreplace.FindAndReplaceLogic;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * the find and replace dialog class.
 */
public class FindAndReplaceDialog extends Dialog {

    /**
     * The FindAndReplaceLogic object.
     */
    private FindAndReplaceLogic findAndReplaceLogic = null;
    
    /**
     * True if a hit one or more by a search in the diagram.
     */
    private boolean isFound = false;
    
    /**
     * The text control to input a search string.
     */
    private Text findText = null;

    /**
     * A search string.
     */
    private String findString = StringUtil.EMPTY;
    
    /**
     * The text control to input a replacement string.
     */
    private Text replaceWithText = null;
    
    /**
     * The text control to view the 'Name' attribute.
     */
    private Text nodeNameText = null;

    /**
     * The text control to view the 'Desc' attribute.
     */
    private Text descText = null;

    /**
     * The 'replace' button.
     */
    private Button replaceButton = null;

    /**
     * The 'replace/find' button.
     */
    private Button replaceFindButton = null;

    /**
     * The composite.
     */
    private Composite panel = null;
    
    /**
     * The count of the columns.
     */
    private static final int GRID_COLUMNS = 3;

    /**
     *The column number of the start position of buttons.
     */
    private static final int GRID_BUTTONS_START_COLUMN = 1;
    
    /**
     * The height of a text area control.
     */
    private static final int TEXTAREA_HEIHGT = 96;

    /**
     * The height of a button.
     */
    private static final int BUTTON_HEIHGT = 22;

    /**
     * The width of a button.
     */
    private static final int BUTTON_WIDTH = 100;

    /**
     * The width of a label text.
     */
    private static final int LABEL_MINIMUM_WIDTH = 50;

    /**
     * The width of a text control.
     */
    private static final int TEXT_MINIMUM_WIDTH = 100;

    /**
     *The key for the label text of the 'Name' attribute.
     */
    private static final String ATTRIBUTEDIALOG_0 = "_UI_BasicNode_name_feature"; //$NON-NLS-1$
    /**
     * The key for the label text of the 'Desc' attribute.
     */
    private static final String ATTRIBUTEDIALOG_1 = "_UI_BasicNode_desc_feature"; //$NON-NLS-1$

    /**
     * The format string for a label text.
     */
    private static final String LABEL_FORMAT = "%s:"; //$NON-NLS-1$

    
    /**
     * Constructor.
     * 
     * @param parentShell parent shell.
     * @param dcaseDiagramEditor The current DcaseDiagramEditor
     */
    public FindAndReplaceDialog(Shell parentShell, DcaseDiagramEditor dcaseDiagramEditor) {
        super(parentShell);
        
        findAndReplaceLogic = new FindAndReplaceLogic(dcaseDiagramEditor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        
        Control ctl = super.createContents(parent);
        // disables the OK button.
        getButton(IDialogConstants.OK_ID).setVisible(false);
        getButton(IDialogConstants.CANCEL_ID).setText(Messages.FindAndReplaceDialog_0);
        return ctl;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        // creates a grid layout.
        panel = (Composite) super.createDialogArea(parent);
        ((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;
        
        // sets the dialog title
        getShell().setText(Messages.FindAndReplaceDialog_1);
        
        createDialogControl(panel);
        
        return panel;
    }
    
    /**
     * Creates dialog control.
     * 
     * @param parent parent
     */
    private void createDialogControl(Composite parent) {
        // creates controllers
        // Label:Find
        Label findLabel = new Label(panel, SWT.LEFT);
        findLabel.setText(Messages.FindAndReplaceDialog_2);
        GridData gridFindLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridFindLabel.grabExcessHorizontalSpace = true;
        gridFindLabel.minimumWidth = LABEL_MINIMUM_WIDTH;
        findLabel.setLayoutData(gridFindLabel);
        
        // Text:Find
        findText = createText(panel, findString);
        GridData gridFindText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridFindText.grabExcessHorizontalSpace = true;
        gridFindText.horizontalSpan = GRID_COLUMNS - 1;
        gridFindText.minimumWidth = TEXT_MINIMUM_WIDTH;
        findText.setLayoutData(gridFindText);
        findText.addModifyListener(new ModifyListener() {
            
            public void modifyText(ModifyEvent e) {
                if (findText.getText().equals(findString) && isFound) {
                    replaceButton.setEnabled(true);
                    replaceFindButton.setEnabled(true);
                } else {
                    replaceButton.setEnabled(false);
                    replaceFindButton.setEnabled(false);
                }
            }
        });
        
        // Label:ReplaceWith
        Label replaceWithLabel = new Label(panel, SWT.WRAP);
        replaceWithLabel.setText(Messages.FindAndReplaceDialog_3);
        replaceWithLabel.setAlignment(SWT.LEFT);
        GridData gridReplaceWithLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridReplaceWithLabel.grabExcessHorizontalSpace = true;
        gridReplaceWithLabel.minimumWidth = LABEL_MINIMUM_WIDTH;
        replaceWithLabel.setLayoutData(gridReplaceWithLabel);
        
        // Text:ReplaceWith
        replaceWithText = createText(panel, findString);
        GridData gridReplaceWithText = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridReplaceWithText.grabExcessHorizontalSpace = true;
        gridReplaceWithText.horizontalSpan = GRID_COLUMNS - 1;
        gridReplaceWithText.minimumWidth = TEXT_MINIMUM_WIDTH;
        replaceWithText.setLayoutData(gridReplaceWithText);
        
        // blank line.
        createLabel(panel, GRID_COLUMNS);

        // Label: Node name
        Label nameLabel = new Label(panel, SWT.LEFT);
        nameLabel.setText(getAttributeName(ATTRIBUTEDIALOG_0));
        nameLabel.setAlignment(SWT.LEFT);
        GridData gridNameLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridNameLabel.grabExcessHorizontalSpace = true;
        gridNameLabel.minimumWidth = LABEL_MINIMUM_WIDTH;
        nameLabel.setLayoutData(gridNameLabel);
        
        // Text:Node name
        nodeNameText = createText(panel, StringUtil.EMPTY);
        GridData gridTextName = new GridData(GridData.FILL_HORIZONTAL);
        gridTextName.horizontalSpan = GRID_COLUMNS - 1;
        gridTextName.verticalAlignment = GridData.FILL;
        gridTextName.grabExcessHorizontalSpace = true;
        gridTextName.grabExcessVerticalSpace = true;
        nodeNameText.setLayoutData(gridTextName);
        nodeNameText.setEditable(false);
        
        // Label:Desc
        Label descLabel = new Label(panel, SWT.WRAP);
        descLabel.setText(getAttributeName(ATTRIBUTEDIALOG_1));
        descLabel.setAlignment(SWT.LEFT);
        GridData gridDescLabel = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridDescLabel.grabExcessHorizontalSpace = true;
        gridDescLabel.minimumWidth = LABEL_MINIMUM_WIDTH;
        gridDescLabel.verticalAlignment = GridData.BEGINNING;
        descLabel.setLayoutData(gridDescLabel);
        
        // Text:Desc
        descText = createMultiLineText(panel, StringUtil.EMPTY);
        GridData gridTextDesc = new GridData(GridData.FILL_HORIZONTAL);
        gridTextDesc.minimumHeight = TEXTAREA_HEIHGT;
        gridTextDesc.horizontalSpan = GRID_COLUMNS - 1;
        gridTextDesc.verticalAlignment = GridData.FILL;
        gridTextDesc.grabExcessHorizontalSpace = true;
        gridTextDesc.grabExcessVerticalSpace = true;
        descText.setLayoutData(gridTextDesc);
        descText.setEditable(false);
        
        // blank line.
        createLabel(panel, GRID_COLUMNS);
        
        // blank one columns.
        createLabel(panel, GRID_BUTTONS_START_COLUMN);
        
        // Button:Find
        Button findButton = createButton(panel, Messages.FindAndReplaceDialog_4);
        GridData gridFindButton = new GridData(GridData.FILL_HORIZONTAL);
        gridFindButton.minimumWidth = BUTTON_WIDTH;
        findButton.setLayoutData(gridFindButton);
        findButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (!StringUtil.isNullOrEmpty(findText.getText())) {
                    findNext();
                }
            }
        });
        
        // Button:Replace/Find
        replaceFindButton = createButton(panel, Messages.FindAndReplaceDialog_5);
        replaceFindButton.setEnabled(false);
        GridData gridReplaceFindButton = new GridData(GridData.FILL_HORIZONTAL);
        gridReplaceFindButton.minimumWidth = BUTTON_WIDTH;
        replaceFindButton.setLayoutData(gridReplaceFindButton);
        replaceFindButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (replace()) {
                    findNext();
                }
            }
        });
        
        // blank one columns.
        createLabel(panel, GRID_BUTTONS_START_COLUMN);
        
        // Button:Replace
        replaceButton = createButton(panel, Messages.FindAndReplaceDialog_6);
        replaceButton.setEnabled(false);
        GridData gridReplaceButton = new GridData(GridData.FILL_HORIZONTAL);
        gridReplaceButton.minimumWidth = BUTTON_WIDTH;
        replaceButton.setLayoutData(gridReplaceButton);
        replaceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                replace();
            }
        });
        
        // Button:Replace All
        Button replaceAllButton = createButton(panel, Messages.FindAndReplaceDialog_7);
        GridData gridReplaceAllButton = new GridData(GridData.FILL_HORIZONTAL);
        gridReplaceAllButton.minimumWidth = BUTTON_WIDTH;
        replaceAllButton.setLayoutData(gridReplaceAllButton);
        replaceAllButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (!StringUtil.isNullOrEmpty(findText.getText())) {
                    replaceAll();
                }
            }
        });
    }

    /**
     * Executes the Find Next.
     */
    private void findNext() {
        if (!findText.getText().equalsIgnoreCase(findString)) {
            // resets the search condition when the search string was changed.
            findInitialize();
        }
        
        BasicNode basicNode = findAndReplaceLogic.findNext();
        if (basicNode != null) {
            isFound = true;
            displaySearchResult(basicNode);
        } else {
          if (isFound) {
              BasicNode firstFoundNode = findAndReplaceLogic.findNext();
              if (firstFoundNode != null) {
                  displaySearchResult(firstFoundNode);
                 
                  MessageDialog.openInformation(getShell(), 
                          Messages.FindAndReplaceDialog_8, Messages.FindAndReplaceDialog_9);
              } else {
                  MessageDialog.openInformation(getShell(), 
                          Messages.FindAndReplaceDialog_10, Messages.FindAndReplaceDialog_11);
              }
          } else {
              MessageDialog.openInformation(getShell(), 
                      Messages.FindAndReplaceDialog_10, Messages.FindAndReplaceDialog_11);
              findInitialize();
          }
        }
    }
    
    /**
     * Executes the Replaces All.
     */
    private void replaceAll() {
        if (!findText.getText().equalsIgnoreCase(findString)) {
            // resets the search condition when the search string was changed.
            findInitialize();
        }
        
        BasicNode node = null;
        BasicNode lastNode = null;
        int replaceCount = 0;
        while ((node = findAndReplaceLogic.findNext()) != null) {
            if (findAndReplaceLogic.getFoundAttributeType() == AttributeType.DESC) {
                if (replace()) {
                    replaceCount++;
                }
                lastNode = node;
            }
        }
        
        if (lastNode != null) {
            findAndReplaceLogic.selectElementsInDiagram(lastNode);
        }
        
        if (replaceCount == 0) {
            MessageDialog.openInformation(getShell(), 
                    Messages.FindAndReplaceDialog_12, Messages.FindAndReplaceDialog_13);
        } else {
            String message = StringUtil.EMPTY;
            if (replaceCount == 1) {
                message = Messages.FindAndReplaceDialog_14;
            } else {
                message = String.format(Messages.FindAndReplaceDialog_15, replaceCount);
            }
            MessageDialog.openInformation(getShell(), Messages.FindAndReplaceDialog_16, message);
            findInitialize();
        }
    }
    
    /**
     * Replaces the replacement string.
     * @return true if the replacement was executed.
     */
    private boolean replace() {
        String replaceWithString = replaceWithText.getText();
        if (replaceWithString == null) {
            replaceWithString = StringUtil.EMPTY;
        }
        
        return findAndReplaceLogic.replace(replaceWithString, descText);
    }
    
    /**
     * Displays the information of the BasicNode object on the dialog.
     * @param basicNode The BasicNode object
     */
    private void displaySearchResult(BasicNode basicNode) {
        String nodeName = basicNode.getName();
        if (!StringUtil.isNullOrEmpty(nodeName)) {
            nodeNameText.setText(nodeName);
        } else {
            nodeNameText.setText(StringUtil.EMPTY);
        }
        String desc = basicNode.getDesc();
        if (!StringUtil.isNullOrEmpty(desc)) {
            descText.setText(desc);
        } else {
            descText.setText(StringUtil.EMPTY);
        }
        int foundPosition = findAndReplaceLogic.getFoundPosition();
        AttributeType foundAttributeType = findAndReplaceLogic.getFoundAttributeType();
        
        if (foundAttributeType == AttributeType.NAME) {
            nodeNameText.setFocus();
            nodeNameText.setSelection(foundPosition, foundPosition + findString.length());
            replaceButton.setEnabled(false);
            replaceFindButton.setEnabled(false);
        } else if (foundAttributeType == AttributeType.DESC) {
            descText.setFocus();
            descText.setSelection(foundPosition, foundPosition + findString.length());
            if (findAndReplaceLogic.isReplacePossible())  {
                replaceButton.setEnabled(true);
                replaceFindButton.setEnabled(true);
            } else {
                replaceButton.setEnabled(false);
                replaceFindButton.setEnabled(false);
            }
        }
        
        findAndReplaceLogic.selectElementsInDiagram(basicNode);
    }
    
    /**
     * Resets the search condition.
     */
    private void findInitialize() {
        isFound = false;
        nodeNameText.setText(StringUtil.EMPTY);
        descText.setText(StringUtil.EMPTY);
        
        replaceButton.setEnabled(false);
        replaceFindButton.setEnabled(false);
        
        findString = findText.getText();
        findAndReplaceLogic.setFindString(findString);
        
        if (findAndReplaceLogic.getSearchNodeListSize() > 0) {
            findAndReplaceLogic.selectElementsInDiagram(
                    findAndReplaceLogic.getCurrentBasicNode());
        }
    }

    /**
     * Returns the attribute name. 
     * 
     * @param key The key for the attribute name.
     * @return The attribute name.
     */
    private String getAttributeName(String key) {
        return String.format(LABEL_FORMAT, DcaseEditPlugin.getPlugin()
                .getString(key));
    }

    /**
     * Creates a button and initializes it to represent the specified string.
     * 
     * @param parent the parent
     * @param text a text.
     * @return a button.
     */
    private Button createButton(Composite parent, String text) {
        if (text == null) {
            text = StringUtil.EMPTY;
        }

        Button button = new Button(parent, SWT.PUSH);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIHGT);
        button.setText(text);

        return button;
    }

    /**
     * Creates a label and initializes it to represent the empty text.
     * 
     * @param parent the parent.
     * @param horizontalSpan the value of horizontal span.
     * @return a label.
     */
    private Label createLabel(Composite parent, int horizontalSpan) {

        Label label = new Label(parent, SWT.NULL);
        label.setText(StringUtil.EMPTY);
        label.setAlignment(SWT.LEFT);
        GridData gridLabel = new GridData();
        if (horizontalSpan > 1 && horizontalSpan <= GRID_COLUMNS) {
            gridLabel.horizontalSpan = horizontalSpan;
        }
        label.setLayoutData(gridLabel);
        return label;
    }

    /**
     * Creates a single line text control and initializes it to represent the specified text.
     * 
     * @param parent the parent
     * @param text a text
     * @return a text control
     */
    private Text createText(Composite parent, String text) {
        if (text == null) {
            text = StringUtil.EMPTY;
        }
        // creates a single line text control.
        Text textControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
        textControl.setText(text);
        return textControl;
    }

    /**
     * Creates a multi line text control and initializes it to represent the specified string.
     * 
     * @param parent the parent
     * @param text a text
     * @return a text control
     */
    private Text createMultiLineText(Composite parent, String text) {
        if (text == null) {
            text = StringUtil.EMPTY;
        }
        // creates a multi line text control.
        Text textControl = new Text(parent, SWT.MULTI | SWT.BORDER
                | SWT.H_SCROLL | SWT.V_SCROLL);
        textControl.setText(text);
        return textControl;
    }
    
}
