/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.ui;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_FILE_EXTENSION;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashSet;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.System;
import net.dependableos.dcase.diagram.command.SetAttributeHandler;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.common.util.TermsMessages;
import net.dependableos.dcase.diagram.common.util.UrlUtil;
import net.dependableos.dcase.diagram.common.util.UserInterfaceUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.part.Messages;
import net.dependableos.dcase.diagram.providers.FileExtensionRestrictTreeContentProvider;
import net.dependableos.dcase.impl.ParameterItem;
import net.dependableos.dcase.impl.RequirementItem;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.swt.events.SelectionListener;

/**
 * A property input dialog.
 */
public class AttributeDialog extends Dialog {

    /**
     * a horizontal span value of the command buttons.
     */
    private static final int COMMAND_BUTTON_HORIZONTAL_SPAN = 3;

    /**
     * a text control to input the value of the Name attribute.
     */
    private Text nameText = null;

    /**
     * the value of the Name attribute.
     */
    private String name = null;

    /**
     * a text control to input the value of the Desc attribute.
     */
    private Text descText = null;

    /**
     * the value of the Desc attribute.
     */
    private String desc = null;

    /**
     * a text control to input the value of the Attachment attribute.
     */
    private Text attachmentText = null;

    /**
     * the value of the Attachment attribute.
     */
    private String attachment = null;
    
    /**
     * the value of the Requirement.
     */
    private String requirement = "";    //$NON-NLS-1$
    
    /**
     * a text control to input the value of the Status attribute.
     */
    private Text statusText = null;

    /**
     * the value of the Status attribute.
     */
    private String status = null;
    
    /**
     * a text control to input the value of the NodeLink attribute.
     */
    private Text nodeLinkText = null;

    /**
     * the value of the NodeLink attribute.
     */
    private String nodeLink = null;

    /**
     * a text control to input the value of the Stakeholder attribute.
     */
    private Text stakeholderText = null;

    /**
     * the value of the Stakeholder attribute.
     */
    private String stakeholder = null;

    /**
     * a text control to input the value of the RiskAnalysis attribute.
     */
    private Text riskAnalysisText = null;

    /**
     * the value of the RiskAnalysis attribute.
     */
    private String riskAnalysis = null;

    /**
     * a combo box control to select the value of the IsNormal attribute.
     */
    private Combo isNormalCombo = null;

    /**
     * the value of the IsNormal attribute.
     */
    private boolean isNormal;
    
    /**
     * a combo box control to input requirement.
     */
    private Combo requeirementCombo;
    
    /**
     * a item list for requirement combo box. 
     */
    private List<RequirementItem> requirementList;

    /**
     * a spinner control to input the value of the Weight attribute.
     */
    private Spinner weightSpinner = null;

    /**
     * the value of the Weight attribute.
     */
    private int weight = 0;

    /**
     * a text control to display the value of Score attribute.
     */
    private Text scoreText = null;

    /**
     * the value of the Score attribute.
     */
    private BigDecimal score = null;
    
    /**
     * a text control to display the value of Desc Format.
     */
    private Text descFormatText;
    
    /**
     * the value of desc format.
     */
    private String descFormat;
    
    /**
     * a text control to display the value of Script.
     */
    private Text scriptText;
    
    /**
     * the value of script.
     */
    private String script;
    
    /**
     * the value of Parameters.
     */
    private String parameters;
    
    /**
     * the definition of Parameters.
     */
    private String parameterDefinitions;
    
    /**
     * a text control to input the value of the Responsibility attribute.
     */
    private Text respNameText = null;
    private Text respAddrText = null;
    private Text respIconText = null;

    /**
     * the value of Responsibility.
     */
    private String respName = null;
    private String respAddr = null;
    private String respIcon = null;
    
    /**
     * the table viewer for parameters.
     */
    TableViewer viewer;
    
    /**
     * the node.
     */
    private BasicNode basicNode = null;
    private DcaseNodeEditPart editPart = null;

    /**
     * the composite.
     */
    private Composite panel = null;

    /**
     * the expand bar.
     */
    private ExpandBar expandBar = null;

    /**
     * the sub panel.
     */
    private Composite expandPanel = null;

    /**
     * the initial width.
     */
    private static final int INIT_WIDTH = 560;

    /**
     * the count of the columns.
     */
    private static final int GRID_COLUMNS = 3;

    /**
     * the width of the control to input the value of the Weight attribute.
     */
    private static final int WEIGHT_WIDTH = 46;

    /**
     * the width of the control to input the value of the Score attribute.
     */
    private static final int SCORE_WIDTH = 62;

    /**
     * the height of a text area control.
     */
    private static final int TEXTAREA_HEIHGT = 96;

    /**
     * the height of a button.
     */
    private static final int BUTTON_HEIHGT = 22;

    /**
     * the width of a button.
     */
    private static final int BUTTON_WIDTH = 76;

    /**
     * the width of a label.
     */
    private static final int LABEL_WIDTH = 76;

    /**
     * width of a text control.
     */
    private static final int TEXT_WIDTH = 156;

    /**
     * the maximum value of a spinner control.
     */
    private static final int SPINNER_MAX = 100;
    
    /**
     * the width of the control to select the value of the IsNormal attribute.
     */
    private static final int IS_NORMAL_WIDTH = 62;

    /**
     * the key for the label text of the Name attribute.
     */
    private static final String ATTRIBUTEDIALOG_0 = "_UI_BasicNode_name_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Desc attribute.
     */
    private static final String ATTRIBUTEDIALOG_1 = "_UI_BasicNode_desc_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Attachment attribute.
     */
    private static final String ATTRIBUTEDIALOG_2 = "_UI_BasicNode_attachment_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Weight attribute of a Goal node.
     */
    private static final String ATTRIBUTEDIALOG_3 = "_UI_Goal_weight_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Weight attribute of a System node.
     */
    private static final String ATTRIBUTEDIALOG_4 = "_UI_System_weight_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Score attribute of a Goal node.
     */
    private static final String ATTRIBUTEDIALOG_5 = "_UI_Goal_score_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Score attribute of a System node.
     */
    private static final String ATTRIBUTEDIALOG_6 = "_UI_System_score_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the NodeLink attribute.
     */
    private static final String ATTRIBUTEDIALOG_7 = "_UI_System_nodeLink_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Stakeholder attribute.
     */
    private static final String ATTRIBUTEDIALOG_8 = "_UI_Justification_stakeholder_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the RiskAnalysis attribute.
     */
    private static final String ATTRIBUTEDIALOG_9 = "_UI_Justification_riskAnalysis_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the IsNormal attribute.
     */
    private static final String ATTRIBUTEDIALOG_10 = "_UI_Monitor_isNormal_feature"; //$NON-NLS-1$
    /**
     * the key for the label text of the Status attribute.
     */
    private static final String ATTRIBUTEDIALOG_11 = "_UI_BasicLink_status_feature"; //$NON-NLS-1$    

    /**
     * the key for the label text of the requirement attribute of a Goal node.
     */
    private static final String ATTRIBUTEDIALOG_12 = "_UI_Goal_requirement_feature"; //$NON-NLS-1$
    
    /**
     * the key for the label text of the requirement attribute of a Goal node.
     */
    private static final String ATTRIBUTEDIALOG_13 = "_UI_BasicNode_userdef005_feature"; //$NON-NLS-1$
    
    
    /**
     * the key for the label text of the requirement attribute of a Goal node.
     */
    private static final String ATTRIBUTEDIALOG_14 = "_UI_BasicNode_userdef006_feature"; //$NON-NLS-1$

    /**
     * the key for the label text of the Responsibility attribute.
     */
    private static final String ATTRIBUTEDIALOG_15 = "_UI_BasicNode_userdef012_feature"; //$NON-NLS-1$
    private static final String ATTRIBUTEDIALOG_16 = "_UI_BasicNode_userdef012_feature2"; //$NON-NLS-1$
    private static final String ATTRIBUTEDIALOG_17 = "_UI_BasicNode_userdef012_feature3"; //$NON-NLS-1$
    private static final String ATTRIBUTEDIALOG_18 = "_UI_BasicNode_userdef012_feature4"; //$NON-NLS-1$

    /**
     * the format string for a label text.
     */
    private static final String LABEL_FORMAT = "%s:"; //$NON-NLS-1$
    
    /**
     * the list of the attachment selector.
     */
    private List<IAttachmentSelector> attachmentSelectorList = null;
    private IAttachmentSelector currentAttachmentSelector = null;
    
    /**
     * the map of commands for the command buttons.
     */
    private List<ParameterizedCommand> setAttributeCommand = null;

    /**
     * the values for parameter table.
     */
	private static final String[] PARAMETER_TITLE_NAMES = {
			"Name", "Value", "Type", "Node"
		};
	private static final int[] PARAMETER_GRID_WIDTHS = { 120, 160, 64, 120 };

    /**
     * Allocates a AttributeDialog object and initializes it to edit the properties of given node.
     * 
     * @param basicNode the node.
     * @param parentShell the parent shell.
     */
    public AttributeDialog(Shell parentShell, BasicNode basicNode, DcaseNodeEditPart editPart) {
        super(parentShell);

        this.basicNode = basicNode;
        this.editPart = editPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(TermsMessages.AttributeDialog_1);
        newShell.setActive();
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
        // gets the values input.
        name = nameText.getText();
        desc = descText.getText();
        attachment = attachmentText.getText();
        if (weightSpinner != null) {
            weight = Integer.valueOf(weightSpinner.getText());
        }
        if (scoreText != null) {
            score = new BigDecimal(scoreText.getText());
        }
        if (statusText != null) {
            status = statusText.getText();
        }
        if (nodeLinkText != null) {
            nodeLink = nodeLinkText.getText();
        }
        if (stakeholderText != null) {
            stakeholder = stakeholderText.getText();
        }
        if (riskAnalysisText != null) {
            riskAnalysis = riskAnalysisText.getText();
        }
        if (isNormalCombo != null) {
            isNormal = new Boolean(isNormalCombo.getText());
        }
        if (respNameText != null) {
        	respName = respNameText.getText();
        }
        if (respAddrText != null) {
        	respAddr = respAddrText.getText();
        }
        if (respIconText != null) {
        	respIcon = respIconText.getText();
        }

        if (requeirementCombo != null) {
            if (requirementList != null && requirementList.size() > 0) {
                
                if (requeirementCombo.getSelectionIndex() <= 0) {
                    requirement = ""; //$NON-NLS-1$
                } else {
                    RequirementItem item = requirementList.get(requeirementCombo.getSelectionIndex() - 1);
                    requirement = item.getFullId();
                }
            }
        }
        descFormat = descFormatText.getText();
        script = scriptText.getText();
        super.okPressed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        // creates a grid layout.
        panel = (Composite) super.createDialogArea(parent);
        ((GridLayout) panel.getLayout()).numColumns = GRID_COLUMNS;

        // creates controllers
        // Label:Name
        createLabel(panel, getAttributeName(ATTRIBUTEDIALOG_0));

        // Text:Name
        nameText = createText(panel, name);
        GridData gridTextName = new GridData();
        gridTextName.horizontalAlignment = GridData.FILL;
        gridTextName.minimumWidth = TEXT_WIDTH;
        gridTextName.grabExcessHorizontalSpace = true;
        nameText.setLayoutData(gridTextName);

        // LabelDunny:Name
        createLabel(panel);

        // Label:Desc
        Label descLabel = createLabel(panel,
                getAttributeName(ATTRIBUTEDIALOG_1));
        GridData gridLabelDesc = new GridData();
        gridLabelDesc.verticalAlignment = GridData.BEGINNING;
        descLabel.setLayoutData(gridLabelDesc);

        // Text:Desc
        descText = createMultiLineText(panel, desc);
        GridData gridTextDesc = new GridData();
        gridTextDesc.minimumWidth = TEXT_WIDTH;
        gridTextDesc.minimumHeight = TEXTAREA_HEIHGT;
        gridTextDesc.horizontalAlignment = GridData.FILL;
        gridTextDesc.verticalAlignment = GridData.FILL;
        gridTextDesc.grabExcessHorizontalSpace = true;
        gridTextDesc.grabExcessVerticalSpace = true;
        descText.setLayoutData(gridTextDesc);
        
        // LabelDunny:Desc
        createLabel(panel);

        // ExpandBar
        expandBar = new ExpandBar(panel, SWT.V_SCROLL);
        expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, GRID_COLUMNS, 1));
        expandPanel = new Composite(expandBar, SWT.NULL);
        expandPanel.setLayout(new GridLayout(GRID_COLUMNS, false));
        
        // Label:Attachment
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_2));

        // Text:Attachment
        attachmentText = createText(expandPanel, attachment);
        GridData gridTextAttachment = new GridData();
        gridTextAttachment.horizontalAlignment = GridData.FILL;
        gridTextAttachment.minimumWidth = TEXT_WIDTH;
        gridTextAttachment.grabExcessHorizontalSpace = true;
        attachmentText.setLayoutData(gridTextAttachment);

        // create the control to select a attachment.
        createBrowseControl(parent);

        // Label:Status
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_11));

        // Text:Status
        statusText = createText(expandPanel, status);
        GridData gridTextStatus = new GridData();
        gridTextStatus.horizontalAlignment = GridData.FILL;
        gridTextStatus.minimumWidth = TEXT_WIDTH;
        gridTextStatus.grabExcessHorizontalSpace = true;
        statusText.setLayoutData(gridTextStatus);

        // LabelDunny:Name
        createLabel(expandPanel);

        // Label:Responsibility_Name
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_15));
        
        // Text:Responsibility_Name
        respNameText = createText(expandPanel, respName);
        GridData gridRespName = new GridData();
        gridRespName.horizontalAlignment = GridData.FILL;
        gridRespName.minimumWidth = TEXT_WIDTH;
        gridRespName.grabExcessHorizontalSpace = true;
        respNameText.setLayoutData(gridRespName);

        // LabelDummy:Responsibility_Name
        createLabel(expandPanel);
        
        // Label:Responsibility_Address
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_16));
        
        // Text:Responsibility_Address
        respAddrText = createText(expandPanel, respAddr);
        GridData gridRespAddr = new GridData();
        gridRespAddr.horizontalAlignment = GridData.FILL;
        gridRespAddr.minimumWidth = TEXT_WIDTH;
        gridRespAddr.grabExcessHorizontalSpace = true;
        respAddrText.setLayoutData(gridRespAddr);
        
        // LabelDummy:Responsibility_Address
        createLabel(expandPanel);

        // Label:Responsibility_Icon
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_17));
        
        // Text:Responsibility_Icon
        respIconText = createText(expandPanel, respIcon);
        GridData gridRespIcon = new GridData();
        gridRespIcon.horizontalAlignment = GridData.FILL;
        gridRespIcon.minimumWidth = TEXT_WIDTH;
        gridRespIcon.grabExcessHorizontalSpace = true;
        respIconText.setLayoutData(gridRespIcon);
        
        // Button:Responsibility_Icon
        Button respIconButton = createButton(expandPanel,
        		DcaseEditPlugin.getPlugin().getString(ATTRIBUTEDIALOG_18));
        respIconButton.addSelectionListener(new SelectionListener() {
        	public void widgetDefaultSelected(SelectionEvent e) {
        	}
        	public void widgetSelected(SelectionEvent e) {
                ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                        getShell(), new DecoratingLabelProvider(
                                new WorkbenchLabelProvider(), PlatformUI
                                .getWorkbench().getDecoratorManager()
                                .getLabelDecorator()),
                        new FileExtensionRestrictTreeContentProvider(null));
                // set projects from workspace.
                fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot().getProjects());
                fileDialog.setAllowMultiple(false);
                fileDialog.setBlockOnOpen(true);
                fileDialog.setTitle("Select Icon Image"); //$NON-NLS-1$
                fileDialog.open();
                Object[] results = fileDialog.getResult();
                if (results != null && results.length == 1
                        && results[0] instanceof IResource) {
                	respIconText.setText(((IResource) results[0]).getFullPath().toString());
                }
        	}
        });

        // Justification
        if (basicNode instanceof Justification) {
            createDialogAreaForJustification();
        }

        // Monitor
        if (basicNode instanceof Monitor) {
            createDialogAreaForMonitor();
        }
        
        if (basicNode instanceof Goal) {
            createDialogForSystemOrGoal();
            createDialogAreaForGoal();
        }
        
        // Label:Desc Format String
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_13));

        // Text:Desc Format String
        descFormatText = createText(expandPanel, descFormat);
        descFormatText.addModifyListener(
                new ModifyListener() {
                    @Override
                    public void modifyText(ModifyEvent e) {
                        String input = descFormatText.getText();
                        AttributeDialog.this.setDescFormat(input, false);
                    }
                }
        );
        GridData gridTextDescFormat = new GridData();
        gridTextDescFormat.horizontalAlignment = GridData.FILL;
        gridTextDescFormat.minimumWidth = TEXT_WIDTH;
        gridTextDescFormat.grabExcessHorizontalSpace = true;
        descFormatText.setLayoutData(gridTextDescFormat);
        setDescFormat(descFormat);
        
        // LabelDunny:Name
        createLabel(expandPanel);
        
        // Label:Script
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_14));

        // Text:Script
        scriptText = createText(expandPanel, script);
        scriptText.addModifyListener(
                new ModifyListener() {
                    @Override
                    public void modifyText(ModifyEvent e) {
                        String input = scriptText.getText();
                        AttributeDialog.this.setScript(input, false);
                    }
                }
        );
        GridData gridTextScript = new GridData();
        gridTextScript.horizontalAlignment = GridData.FILL;
        gridTextScript.minimumWidth = TEXT_WIDTH;
        gridTextScript.grabExcessHorizontalSpace = true;
        scriptText.setLayoutData(gridTextScript);
        
        // LabelDunny:Name
        createLabel(expandPanel);
        
        // Command Button
        createCommandButton();
        
        // TableView:Parameters
        createParameterTable();

        createExpandBarItem();
        
        return panel;
    }

    /**
     * Creates the ExpandBar item.
     */
    private void createExpandBarItem() {

        ExpandItem expandItem1 = new ExpandItem(expandBar, SWT.NONE);
        expandItem1.setExpanded(true);
        expandItem1.setText(Messages.AttributeDialog_ExpandBarMore);
        getShell().layout();
        expandItem1.setHeight(expandPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        expandItem1.setControl(expandPanel);

        expandItem1.setExpanded(false);

        expandBar.addExpandListener(new ExpandAdapter() {
            @Override
            public void itemCollapsed(ExpandEvent e) {
                Display.getCurrent().asyncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        ExpandItem [] items = expandBar.getItems();
                        if (items.length > 0) {
                            int expandItemHeight = items[0].getHeight();
                            Rectangle rect = panel.getBounds();
                            rect.height -= expandItemHeight;
                            panel.setBounds(rect);
                            rect = getShell().getBounds();
                            rect.height -= expandItemHeight;
                            getShell().setBounds(rect);
                            panel.layout();
                            getShell().layout();
                        }
                    }
                });
            }

            @Override
            public void itemExpanded(ExpandEvent e) {
                Display.getCurrent().asyncExec(new Runnable() {
                    
                    @Override
                    public void run() {
                        ExpandItem [] items = expandBar.getItems();
                        if (items.length > 0) {
                            int expandItemHeight = items[0].getHeight();
                            Rectangle rect = panel.getBounds();
                            rect.height += expandItemHeight;
                            panel.setBounds(rect);
                            rect = getShell().getBounds();
                            rect.height += expandItemHeight;
                            getShell().setBounds(rect);
                            panel.layout();
                            getShell().layout();
                        }
                    }
                });
            }
        });
        
        expandBar.setBackground(this.panel.getBackground());
    }

    /**
     * Creates the additional controls for System node.
     */
    private void createDialogAreaForSystem() {
        // Label:Node Link
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_7));

        // Text:Node Link
        nodeLinkText = createText(expandPanel, nodeLink);
        nodeLinkText.setEditable(false);
        GridData gridTextNodeLink = new GridData();
        gridTextNodeLink.horizontalAlignment = GridData.FILL;
        gridTextNodeLink.minimumWidth = TEXT_WIDTH;
        gridTextNodeLink.grabExcessHorizontalSpace = true;
        nodeLinkText.setLayoutData(gridTextNodeLink);

        // Button:Browse...
        Button nodeLinkBrowseButton = createButton(expandPanel, TermsMessages.AttributeDialog_2);
        GridData nodeLinkGridButton = new GridData();
        nodeLinkGridButton.horizontalAlignment = GridData.FILL;
        nodeLinkGridButton.minimumWidth = LABEL_WIDTH;
        nodeLinkBrowseButton.setLayoutData(nodeLinkGridButton);

        // adds a selection listener to the browse button for the NodeLink attribute.
        nodeLinkBrowseButton
                .addSelectionListener(createNodeLinkSelectionAdapter());
    }
    
    /**
     * Creates the additional controls for Justification node.
     */
    private void createDialogAreaForJustification() {
        // Label:Stakeholder
        Label stakeholderLabel = createLabel(expandPanel,
                getAttributeName(ATTRIBUTEDIALOG_8));
        GridData gridLabelStakeholder = new GridData();
        gridLabelStakeholder.verticalAlignment = GridData.BEGINNING;
        stakeholderLabel.setLayoutData(gridLabelStakeholder);

        // Text:Stakeholder
        stakeholderText = createMultiLineText(expandPanel, stakeholder);
        GridData gridTextStakeholder = new GridData();
        gridTextStakeholder.minimumWidth = TEXT_WIDTH;
        gridTextStakeholder.minimumHeight = TEXTAREA_HEIHGT;
        gridTextStakeholder.horizontalAlignment = GridData.FILL;
        gridTextStakeholder.verticalAlignment = GridData.FILL;
        gridTextStakeholder.grabExcessHorizontalSpace = true;
        gridTextStakeholder.grabExcessVerticalSpace = true;
        stakeholderText.setLayoutData(gridTextStakeholder);

        // LabelDunny:Name
        createLabel(expandPanel);

        // Label:Risk Analysis
        Label riskAnalysisLabel = createLabel(expandPanel,
                getAttributeName(ATTRIBUTEDIALOG_9));
        GridData gridLabelRiskAnalysisText = new GridData();
        gridLabelRiskAnalysisText.verticalAlignment = GridData.BEGINNING;
        riskAnalysisLabel.setLayoutData(gridLabelRiskAnalysisText);

        // Text:Risk Analysis
        riskAnalysisText = createText(expandPanel, riskAnalysis);
        GridData gridTextriskAnalysis = new GridData();
        gridTextriskAnalysis.horizontalAlignment = GridData.FILL;
        gridTextriskAnalysis.minimumWidth = TEXT_WIDTH;
        gridTextriskAnalysis.grabExcessHorizontalSpace = true;
        riskAnalysisText.setLayoutData(gridTextriskAnalysis);

        // LabelDunny:Name
        createLabel(expandPanel);
    }
    
    /**
     * 
     */
    private void createDialogAreaForMonitor() {
        // Label:Is Normal
        Label isNormalLabel = createLabel(expandPanel,
                getAttributeName(ATTRIBUTEDIALOG_10));
        GridData gridLabelIsNormal = new GridData();
        gridLabelIsNormal.verticalAlignment = GridData.BEGINNING;
        isNormalLabel.setLayoutData(gridLabelIsNormal);

        // Text:Is Normal
        isNormalCombo = createIsNormalCombo(expandPanel, isNormal);
        GridData gridComboIsNormal = new GridData();
        gridComboIsNormal.minimumWidth = IS_NORMAL_WIDTH;
        gridComboIsNormal.grabExcessHorizontalSpace = true;
        isNormalCombo.setLayoutData(gridComboIsNormal);

        // LabelDunny:Name
        createLabel(expandPanel);
    }
    
    /**
     * 
     */
    private void createDialogForSystemOrGoal() {
        // Label:Weight
        String goalLabel = null;
        if (basicNode instanceof Goal) {
            goalLabel = ATTRIBUTEDIALOG_3;
        } else if (basicNode instanceof System) {
            goalLabel = ATTRIBUTEDIALOG_4;
        }
        createLabel(expandPanel, getAttributeName(goalLabel));

        // Spinner:Weight
        weightSpinner = createSpinner(expandPanel, weight);
        GridData gridSpinner = new GridData();
        gridSpinner.minimumWidth = WEIGHT_WIDTH;
        gridSpinner.grabExcessHorizontalSpace = true;
        weightSpinner.setLayoutData(gridSpinner);

        // LabelDummy:Weight
        createLabel(expandPanel);

        // Label:Score
        String systemLabel = null;
        if (basicNode instanceof Goal) {
            systemLabel = ATTRIBUTEDIALOG_5;
        } else if (basicNode instanceof System) {
            systemLabel = ATTRIBUTEDIALOG_6;
        }
        createLabel(expandPanel, getAttributeName(systemLabel));

        // Text:Score
        scoreText = createScoreText(expandPanel, score);
        scoreText.setEditable(false);
        GridData gridTextScore = new GridData();
        gridTextScore.minimumWidth = SCORE_WIDTH;
        gridTextScore.grabExcessHorizontalSpace = true;
        scoreText.setLayoutData(gridTextScore);

        // LabelDummy:Score
        createLabel(expandPanel);
    }
    
    /**
     * 
     */
    private void createDialogAreaForGoal() {
        Goal goal = (Goal) basicNode;
        // Label:Requirement
        createLabel(expandPanel, getAttributeName(ATTRIBUTEDIALOG_12));
        requeirementCombo = new Combo(expandPanel, SWT.DROP_DOWN | SWT.MULTI | SWT.BORDER | SWT.READ_ONLY);
        LinkManager manager = new LinkManager();
        manager.load((XMLResource) basicNode.eResource());
        requirementList = manager.getRequirements();
        if (requirementList != null && requirementList.size() > 0) {
            requeirementCombo.add(""); //$NON-NLS-1$
            int selectNo = 1;
            for (RequirementItem item : requirementList) {
                requeirementCombo.add(item.getDescription());
                if (item.getFullId().equals(goal.getUserdef003())) {
                    requeirementCombo.select(selectNo);
                }
                selectNo++;
            }
        }
        GridData gridComboRequirement = new GridData();
        gridComboRequirement.horizontalAlignment = GridData.FILL;
        gridComboRequirement.minimumWidth = IS_NORMAL_WIDTH;
        gridComboRequirement.grabExcessHorizontalSpace = true;
        requeirementCombo.setLayoutData(gridComboRequirement);
        // LabelDunny:Name
        createLabel(expandPanel);
    }

    
    /**
     * Create the control to set the attachment property of the selected node. 
     * @param parent the parent composite
     */
    private void createBrowseControl(final Composite parent) {

        attachmentSelectorList = getAttachmentSelectors();
        // when no attachment selector is set. 
        if (attachmentSelectorList.size() == 0) {
            Button browseButton = createButton(expandPanel, ""); //$NON-NLS-1$
            GridData gridButton = new GridData();
            gridButton.horizontalAlignment = GridData.FILL;
            gridButton.minimumWidth = LABEL_WIDTH;
            browseButton.setLayoutData(gridButton);
        // when one attachment selector is set. 
        } else if (attachmentSelectorList.size() == 1) {
            
            Button browseButton = createButton(expandPanel,
                    TermsMessages.AttributeDialog_2);
            GridData gridButton = new GridData();
            gridButton.horizontalAlignment = GridData.FILL;
            gridButton.minimumWidth = LABEL_WIDTH;
            browseButton.setLayoutData(gridButton);
            
            final IAttachmentSelector selector = attachmentSelectorList.get(0);
            currentAttachmentSelector = selector;

            browseButton.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent event) {

                    String currentAttachment = attachmentText.getText();
                    String selectedAttachment = 
                            selector.selectAttachment(getShell(), currentAttachment, basicNode);

                    if (selectedAttachment != null) {
                        attachmentText.setText(selectedAttachment);
                    }
                }
            });
        // when multiple attachment selectors are set. 
        } else {
            
            Button browseButton = createButton(expandPanel,
                    TermsMessages.AttributeDialog_2);
            GridData gridButton = new GridData();
            gridButton.horizontalAlignment = GridData.FILL;
            gridButton.minimumWidth = LABEL_WIDTH;
            browseButton.setLayoutData(gridButton);

            browseButton.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent event) {

                    AttachmentSelectorDialog dialog = 
                            new AttachmentSelectorDialog(parent.getShell(), attachmentSelectorList);

                    if (dialog.open() == OK) {

                        IAttachmentSelector selector = dialog.getAttachmentSelector();
                        currentAttachmentSelector = selector;
                        String currentAttachment = attachmentText.getText();
                        String selectedAttachment = selector.selectAttachment(
                                getShell(), currentAttachment, basicNode);

                        if (selectedAttachment != null) {
                            attachmentText.setText(selectedAttachment);
                            if (currentAttachmentSelector != null) {
                            	String respStr = currentAttachmentSelector.getResponsibility(selectedAttachment);
                            	if (respStr != null && respStr.length() > 0) {
                            		String respArray[] = respStr.split(";"); //$NON-NLS-1$
                            		respNameText.setText((respArray.length > 0) ? respArray[0] : ""); //$NON-NLS-1$
                            		respAddrText.setText((respArray.length > 1) ? respArray[1] : ""); //$NON-NLS-1$
                            		respIconText.setText((respArray.length > 2) ? respArray[2] : ""); //$NON-NLS-1$
                            	}
                            }
                        }
                    }
                }
            });
        }
    }
    
    /**
     * Return the map of attachment selectors from the extension repository.
     * @return the map of attachment selectors
     */
    private List<IAttachmentSelector> getAttachmentSelectors() {

        List<IAttachmentSelector> ret = new ArrayList<IAttachmentSelector>();

        IConfigurationElement[] config = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(
                        "net.dependableos.dcase.diagram.setAttributeContribution"); //$NON-NLS-1$

        try {
            for (IConfigurationElement elment : config) {
                // menu
                if ("attachmentSelector".equals(elment.getName())) { //$NON-NLS-1$
                    String selectorName = elment.getAttribute("name"); //$NON-NLS-1$
                    // handler
                    IAttachmentSelector selectorObj = (IAttachmentSelector) elment
                            .createExecutableExtension("class"); //$NON-NLS-1$
                    selectorObj.setName(selectorName);
                    ret.add(selectorObj);
                }
            }

        } catch (InvalidRegistryObjectException e) {
            throw new DcaseSystemException(e.getMessage(),
                    e, MessageTypeImpl.UNDEFINED);
        } catch (CoreException e) {
            throw new DcaseRuntimeException(e.getMessage(),
                    e, null, 0, MessageTypeImpl.UNDEFINED);
        }

        return ret;
    }

    /**
     * validate the URL string.
     * @param urlString URL string.
     * @return if URL is valid : true. 
     */
    public static boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            // check the protocol
            if (UrlUtil.checkDcaseReferenceProtocol(url.getProtocol())) {
                return true;
            }
        } catch (MalformedURLException e) {
            return false;
        }
        return false;
    }

    /**
     * Creates a selection adapter to select a diagram file from the workspace.
     * 
     * @return  a selection adapter to select a diagram file from the workspace.
     */
    private SelectionAdapter createNodeLinkSelectionAdapter() {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                // creates a element tree selection dialog.
               ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                       expandPanel.getShell(),
                        new DecoratingLabelProvider(
                                new WorkbenchLabelProvider(), PlatformUI
                                        .getWorkbench().getDecoratorManager()
                                        .getLabelDecorator()),
                        new FileExtensionRestrictTreeContentProvider(
                                PropertyUtil
                                        .getSystemProperty(DIAGRAM_FILE_EXTENSION)));

               // Sets the projects as tree input.
                fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot()
                        .getProjects());
                fileDialog.setAllowMultiple(false);
                fileDialog.setBlockOnOpen(true);

                // sets the validator.
                fileDialog
                        .setValidator(createElementSelectionStatusValidator());

                fileDialog.setTitle(TermsMessages.AttributeDialog_0);

                // sets the selected file.
                TreePath treePath = UserInterfaceUtil
                        .convertFilePathToTreePath(nodeLinkText.getText(),
                                ResourcesPlugin.getWorkspace().getRoot());
                if (treePath != null) {
                    fileDialog.setInitialSelection(treePath);
                }

                // opens the dialog.
                fileDialog.open();

                // gets the selected file.
                Object[] results = fileDialog.getResult();

                // tests whether selected file is valid.
                if (results != null && results.length == 1
                        && results[0] instanceof IResource) {
                    // sets the string representation of the file path.
                    nodeLinkText.setText(((IResource) results[0]).getFullPath()
                            .toString());
                }
            }
        };
    }
    
    /**
     * Creates the command buttons.
     */
    private void createCommandButton() {
        // gets commands.
        setAttributeCommand = getCommandSet();
        
        // get the handler service.
        final IHandlerService handlerService = 
                (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        
        for (final ParameterizedCommand command : setAttributeCommand) {
            // creates the button.
            Button commandButton = null;
            try {
                commandButton = createButton(expandPanel, command.getName());
            } catch (NotDefinedException e2) {
                throw new RuntimeException();
            }
            commandButton.addSelectionListener(
                    
                    new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                            try {
                                // executes the command when the button is pushed.
                                SetAttributeHandler.AttributeDialogEvent event
                                    = new SetAttributeHandler.AttributeDialogEvent();
                                event.setDialog(AttributeDialog.this);
                                handlerService.executeCommand(command, event);

                            } catch (ExecutionException ee) {
                                throw new DcaseSystemException(
                                        Messages.AttributeDialog_CommandExecErrMessage,
                                        ee, MessageTypeImpl.UNDEFINED);
                            } catch (NotDefinedException nde) {
                                throw new DcaseSystemException(
                                        Messages.AttributeDialog_CommandExecErrMessage,
                                        nde, MessageTypeImpl.UNDEFINED);
                            } catch (NotEnabledException nee) {
                                throw new DcaseSystemException(
                                        Messages.AttributeDialog_CommandExecErrMessage,
                                        nee, MessageTypeImpl.UNDEFINED);
                            } catch (NotHandledException nhe) {
                                throw new DcaseSystemException(
                                        Messages.AttributeDialog_CommandExecErrMessage,
                                        nhe, MessageTypeImpl.UNDEFINED);
                            }
                        }
            });
            GridData gridButtonsetParam = new GridData();
            gridButtonsetParam.horizontalAlignment = GridData.FILL_HORIZONTAL;
            //gridButtonsetParam.widthHint = BUTTON_WIDTH;
            gridButtonsetParam.horizontalSpan = COMMAND_BUTTON_HORIZONTAL_SPAN;
            commandButton.setLayoutData(gridButtonsetParam);
        }
    }
    
    /**
     * Returns the map of commands.
     * 
     * @return the map of commands and handler activations.
     */
    private List<ParameterizedCommand> getCommandSet() {
        
        IConfigurationElement[] commands = Platform.getExtensionRegistry()
                .getConfigurationElementsFor("net.dependableos.dcase.diagram.setAttributeContribution"); //$NON-NLS-1$
        
        List<ParameterizedCommand> ret 
            = new ArrayList<ParameterizedCommand>();

        try {
            if (commands != null && commands.length > 0) {
                
                for (IConfigurationElement element : commands) {
                    if ("command".equals(element.getName())) { //$NON-NLS-1$
                        String cmdId = (String) element.getAttribute("commandId"); //$NON-NLS-1$
                        ParameterizedCommand cmd = getCommandList(cmdId);
                        ret.add(cmd);
                    }
                }
            }

        } catch (InvalidRegistryObjectException e) {
            throw new DcaseSystemException(e.getMessage(),
                    e, MessageTypeImpl.UNDEFINED);
        }
        return ret;
    }
    
    /**
     * Retrieve the command with a command id.
     * 
     * @param id a command id
     * @return the command
     */
    private ParameterizedCommand getCommandList(String id) {
        // parameter is not used now.
        List<Parameterization> params = new ArrayList<Parameterization>();
        
        ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        Command cmd = cmdService.getCommand(id);
        return new ParameterizedCommand(
                cmd, (Parameterization[]) params.toArray(new Parameterization[params.size()]));
    }
    
    /**
     * Returns the name of the attribute.
     * 
     * @param key the key for the name of the attribute.
     * @return the name of the attribute.
     */
    private String getAttributeName(String key) {
        return String.format(LABEL_FORMAT, DcaseEditPlugin.getPlugin()
                .getString(key));
    }

    /**
     * Creates a label and initializes it to represent the empty text.
     * 
     * @param parent the parent.
     * @return a label.
     */
    private Label createLabel(Composite parent) {
        return createLabel(parent, ""); //$NON-NLS-1$
    }

    /**
     * Creates a label and initializes it to represent the specified text.
     * 
     * @param parent the parent.
     * @param text the text.
     * @return a label.
     */
    private Label createLabel(Composite parent, String text) {
        if (text == null) {
            text = ""; //$NON-NLS-1$
        }

        Label label = new Label(parent, SWT.WRAP);
        label.setText(text);
        label.setAlignment(SWT.LEFT);
        GridData gridLabel = new GridData();
        gridLabel.horizontalAlignment = GridData.FILL;
        gridLabel.minimumWidth = LABEL_WIDTH;
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
            text = ""; //$NON-NLS-1$
        }
        // creates a single line text control.
        Text textControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
        textControl.setText(text);
        return textControl;
    }

    /**
     * Creates a single line text control and initializes it to represent the specified number.
     * 
     * @param parent the parent
     * @param text a number
     * @return a text control
     */
    private Text createScoreText(Composite parent, BigDecimal text) {
        if (text == null) {
            text = new BigDecimal(0);
        }
        // creates a single line text control.
        Text textControl = new Text(parent, SWT.SINGLE | SWT.BORDER);
        textControl.setText(text.toString());
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
            text = ""; //$NON-NLS-1$
        }
        // creates a multi line text control.
        Text textControl = new Text(parent, SWT.MULTI | SWT.BORDER
                | SWT.H_SCROLL | SWT.V_SCROLL);
        textControl.setText(text);
        return textControl;
    }
    
    /**
     * Creates a combo box control and initializes it to represent the specified boolean value.
     * 
     * @param panel the parent.
     * @param isNormal a boolean value.
     * @return a combo box control
     */
    private Combo createIsNormalCombo(Composite panel, boolean isNormal) {
        // creates a combo box control.
        Combo comboControl = new Combo(panel, SWT.READ_ONLY | SWT.FLAT
                | SWT.BORDER);
        comboControl.add("false"); //$NON-NLS-1$
        comboControl.add("true"); //$NON-NLS-1$

        int selectedIndex = 0;
        if (isNormal) {
            selectedIndex = 1;
        }
        comboControl.select(selectedIndex);

        return comboControl;
    }

    /**
     * Creates a spinner control and initializes it to represent the specified number.
     * 
     * @param parent the parent.
     * @param text a number.
     * @return a spinner control.
     */
    private Spinner createSpinner(Composite parent, int text) {

        Spinner spinner = new Spinner(parent, SWT.BORDER);
        spinner.setMaximum(SPINNER_MAX);
        spinner.setMinimum(0);
        spinner.setIncrement(1);
        spinner.setSelection(text);
        return spinner;
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
            text = ""; //$NON-NLS-1$
        }

        Button button = new Button(parent, SWT.PUSH);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIHGT);
        button.setText(text);

        return button;
    }

    /**
     * Sets the value of Name attribute.
     * 
     * @param name the value of Name attribute.
     */
    public void setName(String name) {
        if (name == null) {
            this.name = ""; //$NON-NLS-1$
        } else {
            this.name = name;
        }
    }

    /**
     * Returns the value of Name attribute.
     * 
     * @return the value of Name attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of Desc attribute.
     * 
     * @param desc the value of Desc attribute.
     */
    public void setDesc(String desc) {
        if (desc == null) {
            this.desc = ""; //$NON-NLS-1$
        } else {
            this.desc = desc;
        }
        if (descText != null) {
            if (descFormat == null || descFormat.length() <= 0) {
                descText.setText(this.desc);
            }
        }
    }

    /**
     * Returns the value of Desc attribute.
     * 
     * @return the value of Desc attribute.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of Attachment attribute.
     * 
     * @param attachment the value of Attachment attribute.
     */
    public void setAttachment(String attachment) {
        if (attachment == null) {
            this.attachment = ""; //$NON-NLS-1$
        } else {
            this.attachment = attachment;
        }
    }

    /**
     * Returns the value of Attachment attribute.
     * 
     * @return the value of Attachment attribute.
     */
    public String getAttachment() {
        return attachment;
    }
    
    /**
     * Sets the value of Status attribute.
     * 
     * @param status the value of Status attribute.
     */
    public void setStatus(String status) {
        if (status == null) {
            this.status = ""; //$NON-NLS-1$
        } else {
            this.status = status;
        }
    }

    /**
     * Returns the value of Status attribute.
     * 
     * @return the value of Status attribute.
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Sets the value of NodeLink attribute.
     * 
     * @param nodeLink the value of NodeLink attribute.
     */
    public void setNodeLink(String nodeLink) {
        if (nodeLink == null) {
            this.nodeLink = ""; //$NON-NLS-1$
        } else {
            this.nodeLink = nodeLink;
        }
    }

    /**
     * Returns the value of NodeLink attribute.
     * 
     * @return the value of NodeLink attribute.
     */
    public String getNodeLink() {
        return nodeLink;
    }

    /**
     * Sets the value of Stakeholder attribute.
     * 
     * @param stakeholder the value of Stakeholder attribute.
     */
    public void setStakeholder(String stakeholder) {
        if (stakeholder == null) {
            this.stakeholder = ""; //$NON-NLS-1$
        } else {
            this.stakeholder = stakeholder;
        }
    }

    /**
     * Returns the value of Stakeholder attribute.
     * 
     * @return the value of Stakeholder attribute.
     */
    public String getStakeholder() {
        return stakeholder;
    }

    /**
     * Sets the value of RiskAnalysis attribute.
     * 
     * @param riskAnalysis the value of RiskAnalysis attribute.
     */
    public void setRiskAnalysis(String riskAnalysis) {
        if (riskAnalysis == null) {
            this.riskAnalysis = ""; //$NON-NLS-1$
        } else {
            this.riskAnalysis = riskAnalysis;
        }
    }

    /**
     * Returns the value of RiskAnalysis attribute.
     * 
     * @return the value of RiskAnalysis attribute.
     */
    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    /**
     * Sets the value of IsNormal attribute.
     * 
     * @param isNormal the value of IsNormal attribute.
     */
    public void setIsNormal(boolean isNormal) {
        this.isNormal = isNormal;
    }

    /**
     * Returns the value of IsNormal attribute.
     * 
     * @return the value of IsNormal attribute.
     */
    public boolean isIsNormal() {
        return isNormal;
    }
    
    /**
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * @param requirement the requirement to set
     */
    public void setRequirement(String requirement) {
        if (requirement == null) {
            this.requirement = ""; //$NON-NLS-1$
        } else {
            this.requirement = requirement;
        }
    }
    
    /**
     * Sets the value of Weight attribute.
     * 
     * @param weight the value of Weight attribute.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the value of Weight attribute.
     * 
     * @return the value of Weight attribute.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the value of Score attribute.
     * 
     * @param score the value of Score attribute.
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * Returns the value of Score attribute.
     * 
     * @return the value of Score attribute.
     */
    public BigDecimal getScore() {
        return score;
    }
    
    /**
     * Sets the value of Desc Format attribute.
     * @param descFormat a Desc Format string
     */
    public void setDescFormat(String descFormat) {
        setDescFormat(descFormat, true);
    }
    
    /**
     * Sets the value of Desc Format attribute.
     * if desc format is not empty, edits descText control.
     * @param descFormat a Desc Format string.
     * @param text text control flag. if desc format text control should be updated: true.
     */
    private void setDescFormat(String descFormat, boolean text) {
        
        if (descFormat == null) {
            this.descFormat = ""; //$NON-NLS-1$
        } else {
            this.descFormat = descFormat;
        }
        if (descFormatText != null && text) {
            descFormatText.setText(this.descFormat);
        }

        if (this.descFormat != null && this.descFormat.length() > 0) {
            // if desc format is not empty,
            // sets the formatted desc string to the desc text control
            // and turns the desc text control disable. 
        	String allParameters = editPart.getParameters(parameters);
        	String formattedDesc = ParameterItem.getFormattedDesc(
        			basicNode, allParameters, this.descFormat);
            if (descText != null) {
                descText.setText(formattedDesc);
                descText.setEditable(false);
            }
        } else {
            // if desc format is empty, turns the desc text control enable. 
            if (descText != null) {
                descText.setText(this.desc);
                descText.setEditable(true);
            }
        }
    }
    
    /**
     * Returns the value of Desc Format attribute.
     * @return the value of Desc Format attribute.
     */
    public String getDescFormat() {
        return this.descFormat;
    }
    
    /**
     * Sets the value of Script attribute.
     * @param script the value of Script attribute.
     */
    public void setScript(String script) {
        setScript(script, true);
    }
    
    /**
     * Sets the value of Script attribute.
     * @param script the value of Sctript attribute.
     * @param text text control flag. if Script text control should be updated: true.
     */
    private void setScript(String script, boolean text) {
        if (script == null) {
            this.script = ""; //$NON-NLS-1$
        } else {
            this.script = script;
        }
        if (scriptText != null && text) {
            scriptText.setText(this.script);
        }
    }
    
    /**
     * Returns the value of Script attribute.
     * @return the value of Script attribute.
     */
    public String getScript() {
        return this.script;
    }
    
    /**
     * Sets the value of the Patameters attribute.
     * if Desc Format String is not empty, updates Desc text control.  
     * @param parameters the parameters
     */
    public void setParameters(String parameters) {
        if (parameters == null) {
            this.parameters = ""; //$NON-NLS-1$
        } else {
            this.parameters = parameters;
        }
        if (this.descFormat != null && this.descFormat.length() > 0) {
            if (descText != null) {
            	String allParameters = editPart.getParameters(parameters);
            	String formattedDesc = ParameterItem.getFormattedDesc(
            			basicNode, allParameters, this.descFormat);
            	descText.setText(formattedDesc);
            }
        }
    }
    
    /**
     * Returns the value of Parameter attribute.
     * @return the value of Parameter attribute.
     */
    public String getParameters() {
        return this.parameters;
    }

    /**
     * Sets the definition of the Patameters.
     * @param parameterDefinitions the definition of parameters.
     */
    public void setParameterDefinitions(String parameterDefinitions) {
        if (parameterDefinitions == null) {
            this.parameterDefinitions = ""; //$NON-NLS-1$
        } else {
            this.parameterDefinitions = parameterDefinitions;
        }
    }

    /**
     * Returns the definition of Parameters.
     * @return the definition of Parameters.
     */
    public String getParameterDefinitions() {
        return this.parameterDefinitions;
    }
    
    /**
     * Returns the value of Desc Format.
     * @return the desc format string.
     */
    public String getDescFormatText() {
    	return descFormatText.getText();
    }

    /**
     * Sets the value of Responsibility attribute.
     * 
     * @param nane the value of Responsiblity attribute.
     */
    public void setResponsibility(String name) {
        this.respName = this.respAddr = this.respIcon = ""; //$NON-NLS-1$
        if (name != null && name.length() > 0) {
    		String nameArray[] = name.split(";"); //$NON-NLS-1$
    		if (nameArray.length > 0) {
    			respName = nameArray[0];
    			if (nameArray.length > 1) {
    				respAddr = nameArray[1];
    				if (nameArray.length > 2) {
    					respIcon = nameArray[2];
    				}
        		}
    		}
        }
    }

    /**
     * Returns the value of Responsibility attribute.
     * 
     * @return the value of Responsibility attribute.
     */
    public String getResponsibility() {
        return respName + ";" + respAddr + ";" + respIcon; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#isResizable()
     */
    @Override
    public boolean isResizable() {
        return true;
    }

    /**
     * A validator to indicate whether the selected file is valid.
     * 
     * @return a validator.
     */
    private ISelectionStatusValidator createElementSelectionStatusValidator() {
        return new ISelectionStatusValidator() {
            /**
             * Indicates the selected file is valid.
             * 
             * @param selection the selected file.
             * @return the status that indicates whether the selected file is valid.
             * @see org.eclipse.ui.dialogs.ISelectionStatusValidator.validate#validate(java.lang.Object[])
             */
            public IStatus validate(Object[] selection) {
                if (selection.length == 1) {
                    if (selection[0] instanceof IFile) {
                        return new Status(IStatus.OK,
                                DcaseDiagramEditorPlugin.ID, ""); //$NON-NLS-1$
                    }
                }
                return new Status(IStatus.ERROR, DcaseDiagramEditorPlugin.ID,
                        ""); //$NON-NLS-1$
            }
        };
    }
    
    /**
     * Processes after care.
     * @param node the node.
     */
    public void postProcess(BasicNode node) {
    	if(currentAttachmentSelector != null) {
    		currentAttachmentSelector.postProcess(node, getAttachment());
    	}
    }

    /**
     * Creates the Parameters' Table.
     */
    public void createParameterTable() {
        viewer = new TableViewer(expandPanel, SWT.FULL_SELECTION|SWT.MULTI|SWT.V_SCROLL|SWT.BORDER);
        GridData gridTable = new GridData();
        gridTable.horizontalSpan = GRID_COLUMNS;
        gridTable.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridTable);

    	Table table = viewer.getTable();
    	table.setHeaderVisible(true);
    	table.setLinesVisible(false);
    	for(int i=0; i<4; i++) {
    		TableColumn col = new TableColumn(table, SWT.NULL);
    		col.setText(PARAMETER_TITLE_NAMES[i]);
    		col.setWidth(PARAMETER_GRID_WIDTHS[i]);
    	}
    	
    	table.addMouseListener(new MouseListener() {
    		// copy name to clipboard.
    		public void mouseDown(MouseEvent e) {
    			String name = getSelectedParam();
    			Clipboard clipboard = new Clipboard(Display.getCurrent());
    			clipboard.setContents(new Object[]{name},
    					new Transfer[]{TextTransfer.getInstance()});
    		}
    		// nop.
    		public void mouseUp(MouseEvent e) {
    		}
    		// copy "{name}" to clipboard.
    		public void mouseDoubleClick(MouseEvent e) {
    			String name = "{" + getSelectedParam() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
    			Clipboard clipboard = new Clipboard(Display.getCurrent());
    			clipboard.setContents(new Object[]{name},
    					new Transfer[]{TextTransfer.getInstance()});
    		}
    	});

        viewer.setLabelProvider(new ParamLabelProvider());
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setInput(getParamItems());
    }
    
    /**
     * Returns the parameters info.
     * @return the parameters info.
     */
    private List<ParamModel> getParamItems() {
        LinkedHashMap<String, ParamModel> map = new LinkedHashMap<String, ParamModel>();
    	HashSet<String> uuidSet = new HashSet<String>();
    	String moduleName = getCurrentModuleName();
    	// add parameters of all nodes.
    	getParameter(editPart, moduleName, map, uuidSet);
    	// add d*'s parameters.
    	BasicNode node = (BasicNode)editPart.getElement();
    	DcaseDiagramEditor editor = editPart.getDstarDiagramEditor(node);
        if(editor != null) {
        	DiagramEditPart dstarEditPart = editor.getDiagramEditPart();
        	Argument argument = (Argument)DcaseNodeEditPart.getElement(dstarEditPart);
        	addNodeParameter(Integer.MAX_VALUE, argument, "d*", map); //$NON-NLS-1$
        }
    	ArrayList<ParamModel> paramList = new ArrayList<ParamModel>(map.values());
    	Collections.sort(paramList, new ParamModelComparator());
    	return paramList;
    }
    
    /**
     * Returns the current directory.
     * @return the current directory.
     */
    private IPath getCurrentDir() {
    	View view = ((GraphicalEditPart) editPart).getNotationView();
        if (view == null) {
            return null;
        }
        BasicNode basicNode = (BasicNode) ViewUtil.resolveSemanticElement(view);
        XMLResource resource = (XMLResource)basicNode.eResource();
        IFile modelFile = WorkspaceSynchronizer.getFile(resource);
        return modelFile.getParent().getFullPath();
    }
    
    /**
     * Returns the current module name.
     * @return the current module name.
     */
    private String getCurrentModuleName() {
    	View view = ((GraphicalEditPart) editPart).getNotationView();
        if (view == null) {
            return null;
        }
        BasicNode basicNode = (BasicNode) ViewUtil.resolveSemanticElement(view);
        XMLResource resource = (XMLResource)basicNode.eResource();
        IFile modelFile = WorkspaceSynchronizer.getFile(resource);
        return modelFile.getFullPath().removeFileExtension().lastSegment();
    }
    
    /**
     * Gets the parameters recursively.
     * @param gPart the node or argument edit part.
     * @param moduleName the module name.
     * @param map the parameters.
     * @param uuidSet the uuid set of nodes.
     */
    private void getParameter(GraphicalEditPart gPart, String moduleName,
    		LinkedHashMap<String, ParamModel>map, HashSet<String>uuidSet) {
    	getParameter(0, gPart, moduleName, map, uuidSet);
    }
    
    /**
     * Gets the parameters recursively.
     * @param gPart the node or argument edit part.
     * @param moduleName the module name.
     * @param map the parameters.
     * @param uuidSet the uuid set of nodes.
     */
    private void getParameter(int distance, GraphicalEditPart gPart, String moduleName,
    		LinkedHashMap<String, ParamModel>map, HashSet<String>uuidSet) {
    	if(gPart == null) {
    		return;
    	}
    	// check current node
    	if(!(gPart instanceof DcaseNodeEditPart)) {
    		return;
    	}
    	BasicNode node = (BasicNode)((DcaseNodeEditPart)gPart).getElement();
    	String uuid = DcaseNodeEditPart.getUUID(node);
    	if(uuidSet.contains(uuid)) {
    		return;
    	}
    	uuidSet.add(uuid);
    	
    	// add child Parameter node's parameters
    	for (Object cLink : ((DcaseNodeEditPart)gPart).getSourceConnections()) {
    		if(cLink instanceof DcaseLinkEditPart) {
                DcaseNodeEditPart cEditPart = (DcaseNodeEditPart)((DcaseLinkEditPart)cLink).getTarget();
                if (! (cEditPart instanceof SystemEditPart)) {
                	continue;
                }
                BasicNode cNode = (BasicNode)cEditPart.getElement();
                addNodeParameter(distance, cNode, moduleName, map);
    		}
    	}

    	// check parent nodes
    	for(Object pLink : ((DcaseNodeEditPart)gPart).getTargetConnections()) {
    		DcaseNodeEditPart parentEditPart =
    				(DcaseNodeEditPart)((DcaseLinkEditPart)pLink).getSource();
    		getParameter(distance + 1, parentEditPart, moduleName, map, uuidSet);
    	}

    	// if root node...
    	if(((DcaseNodeEditPart)gPart).getTargetConnections().size() == 0) {
    		// if lonely parameter node...
    		if(gPart instanceof SystemEditPart &&
    			((DcaseNodeEditPart)gPart).getSourceConnections().size() == 0) {
    			addNodeParameter(distance, node, moduleName, map);
    		}
    		// check parent module
    		Argument argument = (Argument)node.eContainer();
    		String userdef011 = argument.getUserdef011();
    		String userdef013 = argument.getUserdef013();

    		if(userdef011 == null || userdef011.length() == 0 ||
    				userdef013 == null || userdef013.length() == 0) {
    			return;
    		}
    		for(String anotherName : userdef011.split(";")) { //$NON-NLS-1$
    			String names[] = anotherName.split("/"); //$NON-NLS-1$
    			if(names.length != 2 || !userdef013.equals(names[0])) {
    				continue;
    			}
    			ArgumentEditPart anotherEditPart = getArgumentEditPart(names[0]);
    			if (anotherEditPart == null) {
    				continue;
    			}
    			for(Object nobj : anotherEditPart.getChildren()) {
    				if(nobj instanceof DcaseNodeEditPart) {
    					BasicNode nnode = (BasicNode)DcaseNodeEditPart.getElement((DcaseNodeEditPart)nobj);
    					if(nnode.getName().equals(names[1])) {
    						getParameter(distance + 1, (DcaseNodeEditPart)nobj, names[0], map, uuidSet);
        				}
            		}
            	}
    		}
    	}
    }
    
    private void addNodeParameter(int distance, BasicNode node, String moduleName, LinkedHashMap<String, ParamModel>map) {
    	String userdef007 = node.getUserdef007();
    	String userdef009 = node.getUserdef009();
    	String nodeName = node.getName();
    	String refName = (node instanceof Argument) ? moduleName : moduleName + "/" + nodeName; //$NON-NLS-1$
    	// name + type
    	HashMap<String, String> userdef009Map = new HashMap<String, String>();
    	if (userdef009 != null && userdef009.length() > 0) {
    		String[] userdef009Array = userdef009.split(";"); //$NON-NLS-1$
    		for (int i = 1; i < userdef009Array.length; i++) {
    			String name = null;
    			String type = null;
    			String values = ""; //$NON-NLS-1$
    			String[] defArray = userdef009Array[i].split(","); //$NON-NLS-1$
    			for (int j = 0; j < defArray.length; j++) {
    				if (defArray[j].startsWith("name=") || defArray[j].startsWith("type=")) {
    					values += ((values.length() > 0) ? ",":"") + defArray[j];
    				}
    			}
    			for (ParameterItem item : ParameterItem.getPatameterList(values)) {
    				if (item.getParameterId().equals("name")) { //$NON-NLS-1$
    					name = item.getParameterValue();
    				} else if (item.getParameterId().equals("type")) {
    					type = item.getParameterValue();
    				}
    			}
    			if (name != null && type != null) {
    				userdef009Map.put(name, type);
    			}
    		}
    	}
    	// name + value
    	List<ParameterItem> paramList = ParameterItem.getPatameterList(userdef007);

    	// add parameters to map
    	for(ParameterItem item : paramList) {
    		String name = item.getParameterId();
    		String type = userdef009Map.get(name);
    		if (map.containsKey(name)) {
    			ParamModel model = map.get(name);
    			if (model.getDistance() <= distance) {
    				continue;
    			}
    		}
    		ParamModel model =
    				new ParamModel(distance, name, item.getParameterValue(), type, refName);
    		map.put(name, model);
    	}
	}
    
    /**
     * Returns the argument edit part.
     * @param moduleName the module name.
     * @return the argument edit part.
     */
    private ArgumentEditPart getArgumentEditPart(String moduleName) {
    	IPath modelPath = getCurrentDir().append(moduleName);
    	IPath diagramPath = modelPath.addFileExtension("dcase_diagram"); //$NON-NLS-1$
    	IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath.toOSString());
    	IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
    			.getActiveWorkbenchWindow().getActivePage();
    	IEditorPart iEditor = null;
    	try {
    		iEditor = workbenchPage.findEditor(new FileEditorInput(diagramFile));
    		if(iEditor == null) {
    			IEditorDescriptor desc = PlatformUI.getWorkbench()
                        .getEditorRegistry().getDefaultEditor(diagramFile.getName());
    			if (desc != null) {
    				Diagram currentDiagram = ((DcaseDiagramEditor)workbenchPage.getActiveEditor()).getDiagram();
    				iEditor = workbenchPage.openEditor(new FileEditorInput(diagramFile), desc.getId(), false);
    				DcaseDiagramEditorUtil.openDiagram(currentDiagram.eResource());
    				if (iEditor == null) {
    					return null;
    				}
    			}
    		}
    	} catch (Exception e) {
			return null;
    	}
    	DiagramEditPart anotherEditPart = ((DcaseDiagramEditor)iEditor).getDiagramEditPart();
    	if(anotherEditPart == null) {
    		return null;
    	} else {
    		return (anotherEditPart instanceof ArgumentEditPart) ?
    				((ArgumentEditPart)anotherEditPart):null;
    	}
    }

    /**
     * A module model.
     */
    private class ParamModel {
   	 private String name = null;
   	 private String value = null;
   	 private String type = null;
   	 private String node = null;
   	 private int distance = 0;
   	 
   	 /**
   	  * The constructor.
   	  * @param name the module name.
   	  * @param node the reference node name.
   	  * @param links the link number.
   	  */
   	 public ParamModel(int distance, String name, String value, String type, String node) {
   		 this.name = name;
   		 this.value = value;
   		 this.type = type;
   		 this.node = node;
   		 this.distance = distance;
   	 }
   	 /**
   	  * Returns the module name.
   	  * @return the module name.
   	  */
   	 public String getName() { return name; }
   	 /**
   	  * Returns the value.
   	  * @return the value.
   	  */
   	 public String getValue() { return value; }
   	 /**
   	  * Returns the type name.
   	  * @return the type name.
   	  */
   	 public String getType() { return type; }
   	 /**
   	  * Returns the node reference name.
   	  * @return the node reference name.
   	  */
   	 public String getNode() { return node; }
   	 /**
   	  * Returns the distance.
   	  * @return the distance.
   	  */
   	 public int getDistance() { return distance; }
   	 public String toString() {
   		 return "{" + name + "," + value + "," + type + "," + node + ":" + distance + "}";
   	 }
    }

    private class ParamModelComparator implements Comparator<ParamModel> {
    	public int compare(ParamModel d1, ParamModel d2) {
    		return d1.getDistance() - d2.getDistance();
    	}
    }
    
    /**
     * A module label provider.
     */
    private class ParamLabelProvider extends LabelProvider 
    	implements ITableLabelProvider {
   	 /**
   	  * Returns the properties of module info.
   	  */
   	 public String getColumnText(Object obj, int index) {
   		 ParamModel model = (ParamModel)obj;
   		 switch(index) {
   		 case 0: return model.getName();
   		 case 1: return model.getValue();
   		 case 2: return model.getType();
   		 case 3: return model.getNode();
   		 }
   		 return "[Undefined]"; //$NON-NLS-1$
   	 }
   	 
        public Image getColumnImage(Object element, int index) {
       	 return null;
        }
    }
   	 
    /**
     * Returns the selected module name.
     * @return the selected module name.
     */
    public String getSelectedParam() {
        IStructuredSelection sel = (IStructuredSelection)viewer.getSelection();
        Object obj = sel.getFirstElement();
        if(obj instanceof ParamModel) {
       	 return ((ParamModel)obj).getName();
        }
        return null;
    }


    /**
     * The interface class to select the attachment.
     * This interface should be substantiated by contributing classes that are use in selecting the attachment.
     */
    public interface IAttachmentSelector {
        /**
         * Select a attachment.
         * 
         * @param parent the parenet Shell while displaying dialog.
         * @param currentAttachment the attachment set now.
         * @param basicNode the selected node.
         * @return the selected attachment.
         */
        String selectAttachment(Shell parent, String currentAttachment, BasicNode basicNode);
        
        /**
         * Return the name.
         * @return the name
         */
        String getName();
        
        /**
         * Set the name.
         * @param name the name of selector
         */
        void setName(String name);
        
        /**
         * Processes after care.
         * @param node the node.
         * @param attachment the new attachment value.
         */
        void postProcess(BasicNode node, String attachment);
        
        /**
         * Returns the responsibility value.
         * @param attachment the attachment value.
         * @return the responsibility value.
         */
        String getResponsibility(String attachment);
    }

}
