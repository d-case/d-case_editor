/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * This class provides methods that deal with the events that are generated when a menu
 *  item that represents a node type for converting selected.
 */
public class ConvertNodeTypeSelectionListener extends SelectionAdapter {

    /**
     * new node type.
     */
    private NodeType newNodeType;

    /**
     * the original node.
     */
    private BasicNode oldNode;

    /**
     * Creates a ConvertNodeTypeSelectionListener and initializes it.
     * 
     * @param newNodeType new node type.
     * @param oldNode the original node.
     */
    public ConvertNodeTypeSelectionListener(NodeType newNodeType,
            BasicNode oldNode) {
        super();
        this.newNodeType = newNodeType;
        this.oldNode = oldNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
     * .swt.events.SelectionEvent)
     */
    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        execute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt
     * .events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        execute();
    }

    /**
     * converts node type.
     */
    private void execute() {
        try {
            // confirms if new node type is not compatible for original node.
            if (isIncompatible()) {
                if (!MessageDialog.openConfirm(DcaseEditorUtil
                        .getActiveWindowShell(),
                        Messages.ConvertNodeTypeSelectionListener_0,
                        Messages.ConvertNodeTypeSelectionListener_1)) {
                    return;
                }
            }

            Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
            if (currentDiagram == null) {
                return;
            }

            ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
                    .getCurrentArgumentEditPart();
            if (currentArgumentEditPart == null) {
                return;
            }

            TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
                    .getEditingDomain(currentDiagram.eResource().getResourceSet());

            ShapeEditPart currentEditPart = getCurrentSelectedParts();

    		ConvertNodeTypeUtil util = new ConvertNodeTypeUtil(currentDomain,
    				Menus.ConvertNodeTypeSelectionListener_0,
    				currentDiagram, currentArgumentEditPart,
    				currentEditPart, newNodeType, oldNode);
    		CompoundCommand cc = util.createCommand();
    		currentArgumentEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(cc);
        } catch (DcaseRuntimeException dcaseRuntimeException) {
            MessageWriter.writeMessageToProblemsView(dcaseRuntimeException);
            MessageWriter.showMessageBoxSeeProblems();
        } catch (DcaseSystemException dcaseSystemException) {
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
        }
    }

    /**
     * Tests whether new node type is incompatible for original node.
     * 
     * @return true if and only if incompatible; false otherwise.
     */
    private boolean isIncompatible() {
        NodeType oldNodeType = NodeType.getNodeType(oldNode);
        switch (oldNodeType) {
            case GOAL:
            case JUSTIFICATION:
            case MONITOR:
                return true;
            default:
        }
        return false;
    }

    /**
     * Returns selected EditPart object.
     * 
     * @return selected EditPart object
     */
    private DcaseNodeEditPart getCurrentSelectedParts() {
        DcaseNodeEditPart result = null;
        DiagramEditor editor = (DiagramEditor) DcaseEditorUtil
                .getCurrentDcaseEditor();
        if (editor != null) {
            ISelection selection = editor.getSite().getPage().getSelection();
            if (selection instanceof StructuredSelection) {
                Object selectedObj = ((StructuredSelection) selection)
                        .getFirstElement();
                if (selectedObj instanceof DcaseNodeEditPart) {
                    result = (DcaseNodeEditPart) selectedObj;
                }
            }
        }
        return result;
    }

}
