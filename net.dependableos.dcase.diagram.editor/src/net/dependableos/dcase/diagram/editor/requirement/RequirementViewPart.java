/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.requirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.Context;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.impl.RequirementItem;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.ui.util.PartListenerAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/**
 * the requirement view part class.
 */
public class RequirementViewPart extends ViewPart {
    
    /**
     * the view id.
     */
    public static final String ID = "net.dependableos.dcase.requrement.views.requirementview"; //$NON-NLS-1$
    
    /**
     * the horizontal grid number.
     */
    private static final int GRID_NUM = 4;
    
    /**
     * the bounds of view part.
     */
    private static final int[] BOUNDS = {100, 100, 50 , 250};
    
    /**
     * the width hint for a combo box.
     */
    private static final int COMBO_WIDTH = 160;
    
    /**
     * the combo box for Context.
     */
    private Combo contextCombo;
    
    /**
     * the combo box for requirement.
     */
    private Combo requirementCombo;
    
    /**
     * the table viewer.
     */
    private TableViewer viewer;
    
    /**
     * the part listener adaptor.
     */
    private PartListenerAdapter partListener;
    
    /**
     * contexts those have requirements.
     */
    private List<NodeInfo> contexts;
    
    /**
     * the requirements.
     */
    private List<RequirementItem> requirements;
    
    /**
     *  goals those have relation with a requirement. 
     */
    private List<NodeInfo> goals;
    
    /**
     * the current argument edit part.
     */
    private ArgumentEditPart argumentEditPart;
    
    /**
     * the link manager.
     */
    private LinkManager linkManager;
    
    /**
     * The filtering context.
     */
    private NodeInfo filterContext = null;

    /**
     * The filtering requirement.
     */
    private RequirementItem filterRequirement = null;
    
    /**
     * a list of selectable requirements.
     */
    private List<RequirementItem> selectableRequirements = new ArrayList<RequirementItem>();

    
    /**
     * Initializes.
     */
    private void initialize() {
        contexts = new ArrayList<NodeInfo>();
        requirements = new ArrayList<RequirementItem>();
        goals = new ArrayList<NodeInfo>();

        try {
            argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart();
        } catch (Exception e) {
            argumentEditPart = null;
        }
        if (argumentEditPart != null) {
            Argument argument = (Argument) DcaseEditorUtil
                    .getElement(argumentEditPart);
            if (argument != null) {
                linkManager = new LinkManager();
                linkManager.load((XMLResource) argument.eResource());
                requirements = linkManager.getRequirements();
                for (String id : linkManager.getAllNodes()) {
                    NodeInfo nodeInfo = linkManager.getNodeInfo(id);
                    if (nodeInfo.getNodeType() == NodeType.CONTEXT) {
                        try {
                            List<RequirementItem> contextRequirement = (List<RequirementItem>) nodeInfo
                                    .getAttribute(AttributeType.REQUIREMENT);
                            if (contextRequirement != null
                                    && contextRequirement.size() > 0) {
                                contexts.add(nodeInfo);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    } else if (nodeInfo.getNodeType() == NodeType.GOAL) {
                        Object requirement = nodeInfo.getAttribute(AttributeType.REQUIREMENT);
                        if (requirement != null
                                && requirement instanceof RequirementItem) {
                            goals.add(nodeInfo);
                        }
                    }
                }
            }
        }

        filterContext = null;
        filterRequirement = null;
        initializeContextSelectBox();
        initializeRequirementSelectBox(null);
        viewer.setInput(goals);
        viewer.refresh();
    }

    /**
     * Creates the controls.
     * @param parent the parent composite
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        
        GridLayout layout = new GridLayout(GRID_NUM, false);
        parent.setLayout(layout);
        
        Label contextLabel = new Label(parent, SWT.NONE);
        contextLabel.setText(Messages.RequirementViewPart_ContextLabelText);
        contextCombo = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
        GridData gridData = new GridData();
        gridData.widthHint = COMBO_WIDTH;
        contextCombo.setLayoutData(gridData);
        contextCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                int index = contextCombo.getSelectionIndex();
                requirementCombo.removeAll();
                filterRequirement = null;
                filterContext = null;
                String contextId = null;
                if (index > 0) {
                    filterContext = contexts.get(index - 1);
                    contextId = (String) filterContext
                            .getAttribute(AttributeType.ID);
                }

                initializeRequirementSelectBox(contextId);

                requirementCombo.select(0);
                viewer.refresh();
            }
        });

        Label requiremntLabel = new Label(parent, SWT.NONE);
        requiremntLabel.setText(Messages.RequirementViewPart_RequirementLabelText);
        requirementCombo = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
        requirementCombo.setLayoutData(gridData);
        requirementCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                int index = requirementCombo.getSelectionIndex();
                filterRequirement = null;
                if (index > 0) {
                    filterRequirement = selectableRequirements.get(index - 1);
                }
                viewer.refresh();
            }
        });
       
        //create a table.
        createTable(parent);
        registerToolBar();
        setPartListener();
        initialize();
    }
    
    /**
     * Creates the table.
     * 
     * @param parent parent composite.
     */
    private void createTable(Composite parent) {
        
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        String[] titles = new String[]{
                Messages.RequirementViewPart_ContextLabelText,
                Messages.RequirementViewPart_RequirementLabelText,
                Messages.RequirementViewPart_NameLabelText,
                Messages.RequirementViewPart_DescriptionLabelText};
       
        for (int i = 0; i < BOUNDS.length; i++) {
            createTableViewerColumn(titles[i], BOUNDS[i], i);
        }

        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        viewer.addFilter(new RequestViewerFilter());
        
        viewer.setContentProvider(new RequirementContentsProvider());
        
        getSite().setSelectionProvider(viewer);
        
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = GRID_NUM;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        
        viewer.setLabelProvider(new RequirementLabeProvider());
        viewer.getControl().setLayoutData(gridData);
    }
 
    /**
     * Initializes a combo box to display requirements. 
     * 
     * @param id the context id;
     */
    private void initializeRequirementSelectBox(String id) {
        selectableRequirements.clear();
        requirementCombo.removeAll();
        requirementCombo.add("All"); //$NON-NLS-1$
        for (RequirementItem requirement : requirements) {
            if (id == null || requirement.getContextId().equals(id)) {
                selectableRequirements.add(requirement);
                requirementCombo.add(requirement.getRequirementId());
            }
        }

        requirementCombo.select(0);
    }
    
    /**
     * Creates combo box control and action.
     */
    private void initializeContextSelectBox() {

        contextCombo.removeAll();
        
        contextCombo.add("All"); //$NON-NLS-1$
        for (NodeInfo nodeInfo : contexts) {
            contextCombo
                    .add((String) nodeInfo.getAttribute(AttributeType.NAME));
        }
        
        contextCombo.select(0);
    }
    
    /**
     * Registers tool bar action for activate target node (Goal or Context) of selected requirement.
     */
    private void registerToolBar() {
        
        IActionBars bars = getViewSite().getActionBars();
        bars.getToolBarManager().removeAll();

        Action selectContextAction = new Action() {
            @Override
            public void run() {

                // search the EditPart from the diagram editor.
                int index = viewer.getTable().getSelectionIndex();
                if (index >= 0) {

                    Table table = viewer.getTable();
                    NodeInfo selectedGoal = (NodeInfo) table
                            .getItem(table.getSelectionIndex()).getData();
                    RequirementItem selectedRequirement = (RequirementItem) selectedGoal
                            .getAttribute(AttributeType.REQUIREMENT);

                    Context context = (Context) linkManager
                            .getBasicNode(selectedRequirement
                                    .getContextId());
                    EditPart editPart = argumentEditPart.findEditPart(null, context);
                    try {
                        DcaseDiagramEditorUtil.selectElementsInDiagram(
                                DcaseEditorUtil.getCurrentDcaseEditor(),
                                Arrays.asList(new EditPart[] { editPart }));
                    } catch (Exception e) {
                        MessageWriter
                                .showErrorMessageBox(Messages.RequirementViewPart_NotFoundContextMessage);
                    }
                    
                }
            }
        };

        IAction selectgoalAction = new Action() {
            @Override
            public void run() {
                 // search the EditPart from the diagram editor.
                int index = viewer.getTable().getSelectionIndex();
                if (index >= 0) {
                    Table table = viewer.getTable();
                    NodeInfo selectedGoal = (NodeInfo) table
                            .getItem(table.getSelectionIndex()).getData();

                    Goal goal = (Goal) linkManager
                            .getBasicNode((String) selectedGoal.getAttribute(AttributeType.ID));
                                  
                    EditPart editPart = argumentEditPart.findEditPart(null, goal);
                    try {
                        DcaseDiagramEditorUtil.selectElementsInDiagram(
                                DcaseEditorUtil.getCurrentDcaseEditor(),
                                Arrays.asList(new EditPart[] { editPart }));
                    } catch (Exception e) {
                        MessageWriter
                                .showErrorMessageBox(Messages.RequirementViewPart_NotFoundGoalMessage);
                    }
               }
            }
        };

        IAction refreshAction = new Action() {
            @Override
            public void run() {
                initialize();
            }
        };

        selectContextAction
                .setText(Messages.RequirementViewPart_ScrollRequirementActionLabelText);
        selectgoalAction
                .setText(Messages.RequirementViewPart_ScrollGoalActionLabelText);
        refreshAction.setText("Refresh");

        // set menu items.
        bars.getToolBarManager().add(selectContextAction);
        bars.getToolBarManager().add(selectgoalAction);
        bars.getToolBarManager().add(refreshAction);
    }
    
    /**
     * Sets Part Listener for detecting that activating D-Case diagram editor change.
     */
    private void setPartListener() {
        // set the PartListener that observe the diagram editor.
        partListener = new PartListenerAdapter() {

            /**
             * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
             */
            public void partActivated(IWorkbenchPart part) {
                initialize();
            }

            /**
             * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
             */
            public void partClosed(IWorkbenchPart part) {
            }

        };

        getSite().getPage().addPartListener(partListener);
    }
    
    /**
     * The function when this view part is exiting.
     */
    @Override
    public void dispose() {
        super.dispose();
        IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
        if (activePage != null) {
            activePage.removePartListener(partListener);
        }
    }
    
    /**
     * Creates table column.
     * 
     * @param title the column title
     * @param width the width.
     * @param colNumber the column number
     * @return the table column
     */
    private TableViewerColumn createTableViewerColumn(String title, int width, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
                SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(width);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }
    
    /**
     * Does nothing.
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
    }
    
    /**
     * Contents Provider inner class for this View Part.
     */
    private class RequirementContentsProvider implements IStructuredContentProvider {

        /**
         * Does nothing.
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

        /**
         * Returns the array of requirement as elements.
         * @param inputElement inputElement the input element
         * @return the array of requirements.
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement) {
            return ((List<NodeInfo>) inputElement).toArray();
        }
    }
    
    /**
     * Label Provider inner class for this View Part.
     */
    private class RequirementLabeProvider implements ITableLabelProvider {
        
        /**
         * column number 1.
         */
        private static final int COLUMN_1 = 0;
        /**
         * column number 2.
         */
        private static final int COLUMN_2 = 1;
        /**
         * column number 3.
         */
        private static final int COLUMN_3 = 2;
        /**
         * column number 4.
         */
        private static final int COLUMN_4 = 3;
        
        /**
         * Does nothing.
         * @param element the object representing the entire row
         * @param columnIndex the zero-based index of the column
         * @return null
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        /**
         * Returns the properties of requirement as the label of columns.
         * 
         * @param element the object representing the entire row
         * @param columnIndex the zero-based index of the column
         * @return the label of column.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object element, int columnIndex) {
            NodeInfo goal = (NodeInfo) element;
            RequirementItem requirement = (RequirementItem) goal.getAttribute(AttributeType.REQUIREMENT);
            if (columnIndex == COLUMN_1) {
                NodeInfo context = linkManager.getNodeInfo(requirement.getContextId());
                return (String) context.getAttribute(AttributeType.NAME);
            } else if (columnIndex == COLUMN_2) {
                return requirement.getDescription();
            } else if (columnIndex == COLUMN_3) {
                return (String) goal.getAttribute(AttributeType.NAME);
            } else if (columnIndex == COLUMN_4) {
                return (String) goal.getAttribute(AttributeType.DESC);
            }
            return ""; //$NON-NLS-1$
        }

        /**
         *  Does nothing.
         * @param listener a label provider listener
         * @see org.eclipse.jface.viewers.IBaseLabelProvider
         *      #addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener) {
        }

        /**
         * Dose nothing.
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose() {
        }

        /**
         * Does nothing.
         * 
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
         * 
         * @param listener a label provider listener
         * @see org.eclipse.jface.viewers.IBaseLabelProvider
         *      #removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener) { 
        }
    }
    
    /**
     * Viewer Filter class.
     */
    private class RequestViewerFilter extends ViewerFilter {

        /**
         * @param viewer the viewer
         * @param parentElement the parent element
         * @param element the element
         * @return if the element is to display: true
         * @see org.eclipse.jface.viewers.ViewerFilter
         *      #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
         */
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            NodeInfo goal = (NodeInfo) element;
            RequirementItem requirement = (RequirementItem) goal.getAttribute(AttributeType.REQUIREMENT);
           
            
            if (filterContext != null) {
                String filterID = (String) filterContext.getAttribute(AttributeType.ID);
                if (!filterID.equals(requirement.getContextId())) {
                    return false;
                }
            }
            if (filterRequirement != null) {
                if (!filterRequirement.getFullId().equals(requirement.getFullId())) {
                    return false;
                }
            }
            return true;
        }
    }
}
