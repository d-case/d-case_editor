/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.computenodes;


import java.util.ArrayList;
import java.util.List;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Context;
import net.dependableos.dcase.DcaseLink001;
import net.dependableos.dcase.DcaseLink002;
import net.dependableos.dcase.DcaseLink003;
import net.dependableos.dcase.DcaseLink004;
import net.dependableos.dcase.Evidence;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.Policy;
import net.dependableos.dcase.Strategy;
import net.dependableos.dcase.Undeveloped;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef002;
import net.dependableos.dcase.Userdef003;
import net.dependableos.dcase.Userdef004;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.Userdef006;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.common.ui.util.PartListenerAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;


/**
 * The class that view part of compute nodes.
 */
public class ComputeNodesViewPart extends ViewPart {

    /**
     * The ID of the view as specified by the extension.
     */
    public static final String ID = "net.dependableos.dcase.diagram.editor.computenodes.ComputeNodes";
    
    /**
     * The part listener adaptor.
     */
    private PartListenerAdapter partListener;
    
    /**
     * The current argument edit part.
     */
    private ArgumentEditPart argumentEditPart;
    
    /**
     * The width hint for a table column.
     */
    private static final int TABLE_COLUMN_WIDTH = 100;

    /**
     * The table viewer.
     */
    private TableViewer viewer;
    
    /**
     * The titles.
     */
    private static final String[] TITLES = new String[]{
        Messages.ComputeNodesViewPart_AllNodesText,
        Messages.ComputeNodesViewPart_GoalText,
        Messages.ComputeNodesViewPart_SystemText,
        Messages.ComputeNodesViewPart_StrategyText,
        Messages.ComputeNodesViewPart_ContextText,
        Messages.ComputeNodesViewPart_JustificationText,
        Messages.ComputeNodesViewPart_AssumptionText,
        Messages.ComputeNodesViewPart_EvidenceText,
        Messages.ComputeNodesViewPart_UndevelopedText,
        Messages.ComputeNodesViewPart_ModuleText,
        Messages.ComputeNodesViewPart_ContractText,
        Messages.ComputeNodesViewPart_MonitorText,
        Messages.ComputeNodesViewPart_PolicyText,
        Messages.ComputeNodesViewPart_Userdef001Text,
        Messages.ComputeNodesViewPart_Userdef002Text,
        Messages.ComputeNodesViewPart_Userdef003Text,
        Messages.ComputeNodesViewPart_AllLinksText,
        Messages.ComputeNodesViewPart_SupportedByText,
        Messages.ComputeNodesViewPart_InContextOfText,
        Messages.ComputeNodesViewPart_LinkSolidText,
        Messages.ComputeNodesViewPart_LinkDashText
        };
    
    /**
     * The number of all node.
     */
    private static final int NUM_ALL_NODE = 0;
    
    /**
     * The number of goal.
     */
    private static final int NUM_GOAL = 1;
    
    /**
     * The number of system.
     */
    private static final int NUM_SYSTEM = 2;
    
    /**
     * The number of strategy.
     */
    private static final int NUM_STRATEGY = 3;
    
    /**
     * The number of context.
     */
    private static final int NUM_CONTEXT = 4;
    
    /**
     * The number of justification.
     */
    private static final int NUM_JUSTIFICATION = 5;
    
    /**
     * The number of assumption.
     */
    private static final int NUM_ASSUMPTION = 6;
    
    /**
     * The number of evidence.
     */
    private static final int NUM_EVIDENCE = 7;
    
    /**
     * The number of undeveloped.
     */
    private static final int NUM_UNDEVELOPED = 8;
    
    /**
     * The number of module.
     */
    private static final int NUM_MODULE = 9;
    
    /**
     * The number of contract.
     */
    private static final int NUM_CONTRACT = 10;
    
    /**
     * The number of monitor.
     */
    private static final int NUM_MONITOR = 11;
    
    /**
     * The number of policy.
     */
    private static final int NUM_POLICY = 12;
    
    /**
     * The number of userdef001.
     */
    private static final int NUM_USERDEF001 = 13;
    
    /**
     * The number of userdef002.
     */
    private static final int NUM_USERDEF002 = 14;
    
    /**
     * The number of userdef003.
     */
    private static final int NUM_USERDEF003 = 15;
    
    /**
     * The number of all links.
     */
    private static final int NUM_ALL_LINKS = 16;
    
    /**
     * The number of supported by.
     */
    private static final int NUM_SUPPORTED_BY = 17;
    
    /**
     * The number of in context of.
     */
    private static final int NUM_IN_CONTEXST_OF = 18;
    
    /**
     * The number of link Solid.
     */
    private static final int NUM_LINK_SOLID = 19;
    
    /**
     * The number of link dash.
     */
    private static final int NUM_LINK_DASH = 20;

    /**
     * A private class that provides the label of the table view.
     */
    class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
        /**
         * Returns the properties of requirement as the label of columns.
         * 
         * @param obj the object representing the entire row
         * @param index the zero-based index of the column
         * @return the label of column.
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
         */
        public String getColumnText(Object obj, int index) {
            ComputeNodesModel computeNodesModel = (ComputeNodesModel) obj;
            switch(index) {
                case 0:
                    return computeNodesModel.getNodeLink();
                case 1:
                    return computeNodesModel.getTotal();
                default:
                    return StringUtil.EMPTY;
            }
        }
        /**
         * Does nothing.
         * @param obj the object representing the entire row
         * @param index the zero-based index of the column
         * @return null
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object obj, int index) {
            return null;
        }
    }

    /**
     * Initializes.
     */
    private void initialize() {
        List<ComputeNodesModel> list = new ArrayList<ComputeNodesModel>();

        try {
            argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart();
        } catch (Exception e) {
            argumentEditPart = null;
        }
        if (argumentEditPart != null) {
         
            Argument argument = (Argument) DcaseEditorUtil
                    .getElement(argumentEditPart);
            if (argument != null) {
                // get nodes count
                ComputeNodesMember computeNodesMember = new ComputeNodesMember(TITLES.length);
                for (BasicNode basicNode : argument.getRootBasicNode()) {
                    if (basicNode instanceof Goal) {
                        computeNodesMember.incCount(NUM_GOAL);
                    } else if (basicNode instanceof net.dependableos.dcase.System) {
                        computeNodesMember.incCount(NUM_SYSTEM);
                    } else if (basicNode instanceof Strategy) {
                        computeNodesMember.incCount(NUM_STRATEGY);
                    } else if (basicNode instanceof Context) {
                        computeNodesMember.incCount(NUM_CONTEXT);
                    } else if (basicNode instanceof Justification) {
                        computeNodesMember.incCount(NUM_JUSTIFICATION);
                    } else if (basicNode instanceof Userdef004) {
                        computeNodesMember.incCount(NUM_ASSUMPTION);
                    } else if (basicNode instanceof Evidence) {
                        computeNodesMember.incCount(NUM_EVIDENCE);
                    } else if (basicNode instanceof Undeveloped) {
                        computeNodesMember.incCount(NUM_UNDEVELOPED);
                    } else if (basicNode instanceof Userdef005) {
                        computeNodesMember.incCount(NUM_MODULE);
                    } else if (basicNode instanceof Userdef006) {
                        computeNodesMember.incCount(NUM_CONTRACT);
                    } else if (basicNode instanceof Monitor) {
                        computeNodesMember.incCount(NUM_MONITOR);
                    } else if (basicNode instanceof Policy) {
                        computeNodesMember.incCount(NUM_POLICY);
                    } else if (basicNode instanceof Userdef001) {
                        computeNodesMember.incCount(NUM_USERDEF001);
                    } else if (basicNode instanceof Userdef002) {
                        computeNodesMember.incCount(NUM_USERDEF002);
                    } else if (basicNode instanceof Userdef003) {
                        computeNodesMember.incCount(NUM_USERDEF003);
                    }
                    // all nodes
                    computeNodesMember.incCount(NUM_ALL_NODE);
                }
                
                // get link count
                for (BasicLink basicLink : argument.getRootBasicLink()) {            
                    if (basicLink instanceof DcaseLink001) {
                        computeNodesMember.incCount(NUM_SUPPORTED_BY);
                    } else if (basicLink instanceof DcaseLink002) {
                        computeNodesMember.incCount(NUM_IN_CONTEXST_OF);
                    } else if (basicLink instanceof DcaseLink003) {
                        computeNodesMember.incCount(NUM_LINK_SOLID);
                    } else if (basicLink instanceof DcaseLink004) {
                        computeNodesMember.incCount(NUM_LINK_DASH);
                    }
                    // all links
                    computeNodesMember.incCount(NUM_ALL_LINKS);
                }
                
                // set model list
                for (int i = 0; i < TITLES.length; i++) {
                    ComputeNodesModel model = new ComputeNodesModel();
                    model.setNodeLink(TITLES[i]);
                    model.setTotal(Integer.toString(computeNodesMember.getCount(i)));
                    list.add(model);
                }
            }
        }        
        
        viewer.setInput(list);
        viewer.refresh();
    }

    /**
     * Creates the controls.
     * @param parent the parent composite
     */
    public void createPartControl(Composite parent) {
        
        // mode layout
        viewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        
        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn column1 = new TableColumn(table, SWT.NULL);
        column1.setText(Messages.ComputeNodesViewPart_NodeLinkText);
        column1.setWidth(TABLE_COLUMN_WIDTH);

        TableColumn column2 = new TableColumn(table, SWT.NULL);
        column2.setText(Messages.ComputeNodesViewPart_TotalText);
        column2.setWidth(TABLE_COLUMN_WIDTH);
        

        viewer.setContentProvider(new ComputeNodesContentsProvider());
        
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);
        
        getSite().setSelectionProvider(viewer);
        
        viewer.setLabelProvider(new ViewLabelProvider());        
        
        registerToolBar();
        setPartListener();
        setKeyListener();
        initialize();
    }

    /**
     * Does nothing.
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
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
     * Sets the Key Listener for table.
     */
    private void setKeyListener() {
        viewer.getTable().addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.stateMask == SWT.CTRL
                        && (e.keyCode == 'c' || e.keyCode == 'C')) { //$NON-NLS-1$
                    setClopboard();
                }
                
                }
                public void keyReleased(KeyEvent e) {
                }
        });
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
     * Registers tool bar action for copy.
     */
    private void registerToolBar() {
        
        IActionBars bars = getViewSite().getActionBars();
        bars.getToolBarManager().removeAll();
        
        Action copyAction = new Action() {
            @Override
            public void run() {
                setClopboard();
            }
        };
        
        copyAction.setText(Messages.ComputeNodesViewPart_CopyText);
        
        // set menu item.
        bars.getToolBarManager().add(copyAction);
    }
    
    /**
     * Sets the contents for clip board.
     */
    private void setClopboard() {
        Display display = DisplayUtils.getDisplay();
        Clipboard clipboard = new Clipboard(display);
        StringBuffer sb = new StringBuffer();
        Table table = viewer.getTable();
        int itemMaxCnt = table.getItemCount();
        int itemCount = 1;
        sb.append(Messages.ComputeNodesViewPart_NodeLinkText);
        sb.append(","); //$NON-NLS-1$
        sb.append(Messages.ComputeNodesViewPart_TotalText);
        sb.append(System.getProperty("line.separator")); //$NON-NLS-1$
        for (TableItem item : table.getItems()) {
            sb.append(item.getText(0));
            sb.append(","); //$NON-NLS-1$
            sb.append(item.getText(1));
            if (itemCount != itemMaxCnt) {
                sb.append(System.getProperty("line.separator")); //$NON-NLS-1$
            }
            itemCount++;
        }
        
        clipboard.setContents(new Object[]{sb.toString()}, new Transfer[]{TextTransfer.getInstance()});
    }
    
    /**
     * Contents Provider inner class for this View Part.
     */
    private class ComputeNodesContentsProvider implements IStructuredContentProvider {

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
         * Returns the array of compute nodes as elements.
         * @param inputElement the input element
         * @return the array of compute nodes model.
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        @SuppressWarnings("unchecked")
        public Object[] getElements(Object inputElement) {
            return ((List<ComputeNodesModel>) inputElement).toArray();
        }
    }
}