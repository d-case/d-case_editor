/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.requirement;

import java.util.ArrayList;
import java.util.List;

import net.dependableos.dcase.Context;
import net.dependableos.dcase.impl.ParameterItem;
import net.dependableos.dcase.impl.RequirementItem;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A dialog to edit requirements.
 */
public class RequirementDialog extends Dialog implements
IStructuredContentProvider {
    
    /**
     * the dialog tile.
     */
    private static final String DIALOG_TITLE = Messages.RequirementDialog_DialogTitle;
    
    /**
     * the width of the dialog.
     */
    private static final int INIT_WIDTH = 500;

    /**
     * the width of the button.
     */
    private static final int BUTTON_WIDTH = 80;

    /**
     * the width of the first row of table.
     */
    private static final int COLUMN_WIDTH_SELECT = 60;

    /**
     * the width of the second row of table.
     */
    private static final int COLUMN_WIDTH_PARAMETER = 300;

    /**
     * the number of columns.
     */
    private static final int COLUMN_NUMBER = 3;
    
    /**
     * the height of dialog.
     */
    private static final int HEIGHT_HINT = 200;
    
    /**
     * the vertical span.
     */
    private static final int VERTICAL_SPAN = 4;
    
    /**
     * the table represented in dialog.
     */
    private Table table;
    
    /**
     * the table viewer for representing table.
     */
    private TableViewer tableViewer;
    
    /**
     * the requirements.
     */
    private List<RequirementItem> requirements;
    
    /**
     * the parameters.
     */
    private String parameters;
    
    /**
     * the context id.
     */
    private String contextId;

    /**
     * Constructor.
     * 
     * @param parentShell parent shell.
     * @param context target node.
     */
    public RequirementDialog(Shell parentShell, ContextEditPart editPart) {
        super(parentShell);
        Context context = (Context) DcaseEditorUtil.getElement(editPart);
        requirements = context.getRequirements();
        try {
            parameters = editPart.getParameters(context.getParameterVals());
        } catch (Exception e) {
            parameters = ""; //$NON-NLS-1$
        }
        contextId = ((XMLResource) context.eResource()).getID(context);
    }
    
    /**
     * Returns the initial dialog size.
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
        return size;
    }
    
    /**
     * Creates dialog area.
     * 
     * @param parent parent composite.
     * @return the parent composite.
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        // set GridLayout data.
        Composite panel = (Composite) super.createDialogArea(parent);
        
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.numColumns = COLUMN_NUMBER;
        panel.setLayout(layout);
        // set the dialog title.
        getShell().setText(DIALOG_TITLE);
        
        // create a table
        createTable(panel);
        // create the operation button.
        createOperationButton(panel);
        
        return panel;
    }

    /**
     * Creates a table.
     * 
     * @param panel parent composite.
     */
    private void createTable(Composite panel) {
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalSpan = VERTICAL_SPAN;
        gridData.heightHint = HEIGHT_HINT;
        gridData.horizontalSpan = 2;
        table = new Table(panel, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
       
        TableColumn column = new TableColumn(table, SWT.CENTER, 0);
        column.setText(Messages.RequirementDialog_NameLabelText);
        column.setWidth(COLUMN_WIDTH_SELECT);
        
        column = new TableColumn(table, SWT.LEFT, 1);
        column.setText(Messages.RequirementDialog_DescriptionLabelText);
        column.setWidth(COLUMN_WIDTH_PARAMETER);
        
        //create a table viewer.
        tableViewer = new TableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(new String[]{
                Messages.RequirementDialog_NameLabelText, 
                Messages.RequirementDialog_DescriptionLabelText 
        });
        
        tableViewer.setContentProvider(this);
        tableViewer.setLabelProvider(new RequirementLabeProvider());
        tableViewer.setInput(requirements);
    }
    
    /**
     * Returns the existing ids.
     * 
     * @param avoid the id to avoid.
     * @return the existing ids.
     */
    private List<String> getExistingIds(String avoid) {
        List<String> existingIds = new ArrayList<String>();
        for (RequirementItem requirement : requirements) {
            String id = requirement.getRequirementId();
            if (!id.equals(avoid)) {
                existingIds.add(id);
            }
        }
        return existingIds;
    }

    /**
     * Creates buttons.
     * 
     * @param panel parent composite.
     */
    private void createOperationButton(Composite panel) {
        // creates a button to add a new requirement.
        Button addButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        addButton.setText(Messages.RequirementDialog_CreateButtonLabelText);
        GridData editGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_FILL);
        editGridData.minimumWidth = BUTTON_WIDTH;
        addButton.setLayoutData(editGridData);
        // registers the selection listener.
        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                RequirementEditDialog editDialog = new RequirementEditDialog(
                        getActiveWindowShell(), "", "", getExistingIds(null), //$NON-NLS-1$ //$NON-NLS-2$
                        RequirementEditDialog.CREATE);
                if (Dialog.OK == editDialog.open()) {
                    RequirementItem requirementItem = new RequirementItem(
                            contextId, editDialog.getId(), editDialog
                                    .getRequirement(), parameters);
                    requirements.add(requirementItem);
                    tableViewer.setInput(requirements);
                }
            }
        });
        
        // creates a button to edit the selected requirement.
        Button editButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        editButton.setText(Messages.RequirementDialog_EditButtonlabelText);
        GridData upGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        upGridData.minimumWidth = BUTTON_WIDTH;
        editButton.setLayoutData(upGridData);
        // registers the selection listener.
        editButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    RequirementItem requirement = requirements
                            .get(selectedIndex);
                    String id = requirement.getRequirementId();
                    RequirementEditDialog editDialog = new RequirementEditDialog(
                            getActiveWindowShell(), id, requirement
                                    .getRequirement(), getExistingIds(id),
                            RequirementEditDialog.EDIT);
                    if (Dialog.OK == editDialog.open()) {
                        RequirementItem requirementItem = new RequirementItem(
                                contextId, editDialog.getId(), editDialog
                                        .getRequirement(), parameters);
                        requirements.remove(selectedIndex);
                        requirements.add(selectedIndex, requirementItem);
                        tableViewer.setInput(requirements);
                    }
                }
            }
        });

        // creates a button to delete the selected requirement.
        Button deleteButton = new Button(panel, SWT.PUSH | SWT.CENTER);
        deleteButton.setText(Messages.RequirementDialog_DeleteButtonLabelText);
        GridData downGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_FILL);
        downGridData.minimumWidth = BUTTON_WIDTH;
        deleteButton.setLayoutData(downGridData);
        // registers the selection listener.
        deleteButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    RequirementItem selectedRequirement = requirements.get(selectedIndex);
                    requirements.remove(selectedRequirement);
                    tableViewer.setInput(requirements);
                }
            }
        });

        Label scriptStrLabel = new Label(panel, SWT.WRAP);
        GridData spaceGridData = new GridData(
                GridData.HORIZONTAL_ALIGN_BEGINNING);
        spaceGridData.widthHint = BUTTON_WIDTH;
        scriptStrLabel.setLayoutData(spaceGridData);
    }

    /**
     * @return the requirements
     */
    public List<RequirementItem> getRequirements() {
        return requirements;
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
     * The label provider class for the requirement view part.
     */
    private class RequirementLabeProvider implements ITableLabelProvider {

        /**
         * Does nothing.
         * @param element the element
         * @param columnIndex the column index
         * @return null
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /**
         * Returns the requirement id or the replaced requirement as the column text.
         * @param element the element
         * @param columnIndex the column index
         * @return the column text
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            if (columnIndex == 0) {
                return ((RequirementItem) element).getRequirementId();
            } else if (columnIndex == 1) {
                return ParameterItem.getFormattedDesc(parameters, ((RequirementItem) element).getDescription());
            }
            return ""; //$NON-NLS-1$
        }

        /**
         * Does nothing.
         * @param listener a label provider listener
         * @see org.eclipse.jface.viewers.IBaseLabelProvider
         *      #addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
        }

        /**
         * Does nothing.
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * Does nothing.
         * @param element the element
         * @param property the property
         * @return false 
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        /**
         * Does nothing.
         * @param listener a label provider listener
         * @see org.eclipse.jface.viewers.IBaseLabelProvider
         *      #removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) { 
        }
    }

    /**
     * Returns the input elements casted into RequirementItem[].
     * 
     * @param inputElement the input element
     * @return the array of RequirementItem to display in the viewer
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        return ((List<?>) inputElement).toArray();
    }

    /**
     * Disposes.
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }

    /**
     * Does nothing.
     * 
     * @param viewer the viewer
     * @param oldInput the old input element
     * @param newInput the new input element
     * @see org.eclipse.jface.viewers.IContentProvider
     *      #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
    
}
