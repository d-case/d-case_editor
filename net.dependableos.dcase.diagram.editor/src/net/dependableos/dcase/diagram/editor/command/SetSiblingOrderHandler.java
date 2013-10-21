/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.diagram.common.command.ChangeBasicLinkPropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.edit.parts.StrategyEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler to set sibling order to children of the selected node.
 */
public class SetSiblingOrderHandler extends AbstractHandler {

    /**
     * Set sibling order to children of the selected node.
     * 
     * @param event ExecutionEvent
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the edit part that represents the selected node.
        ShapeNodeEditPart selectedElement = getSelectedElement(event);
        if (selectedElement != null) {
            // gets children of the selected node.
            List<ConnectionNodeEditPart> editPartList = getTargetEditPart(selectedElement);

            if (editPartList != null && !editPartList.isEmpty()) {
                CompoundCommand cc = new CompoundCommand(
                        Menus.SetSiblingOrderHandler_0);

                TransactionalEditingDomain currentDomain = DcaseEditorUtil
                        .getCurrentArgumentEditPart().getEditingDomain();

                // sorts links by position of the target node.
                Collections.sort(editPartList, getBasicLinkComparator());

                int order = 1;
                for (ConnectionNodeEditPart editPart : editPartList) {
                    // gets the link.
                    BasicLink basicLink = (BasicLink) DcaseEditorUtil
                            .getElement(editPart);

                    // sets the sibling order.
                    Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
                    attrMap.put(AttributeType.USERDEF001, Integer
                            .toString(order++));

                    ICommand changeCommand = new ChangeBasicLinkPropertyTransactionCommand(
                            currentDomain, Menus.SetSiblingOrderHandler_0,
                            null, basicLink, attrMap);
                    cc.add(new ICommandProxy(changeCommand));
                }
                if (!cc.isEmpty()) {
                    selectedElement.getDiagramEditDomain()
                            .getDiagramCommandStack().execute(cc);
                }
            }
        }
        return null;
    }

    /**
     * Returns the selected node.
     * 
     * @param event the event.
     * @return element the selected node.
     */
    private ShapeNodeEditPart getSelectedElement(ExecutionEvent event) {
        ISelection selection = HandlerUtil.getActiveMenuSelection(event);
        ShapeNodeEditPart element = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof StrategyEditPart) {
                element = (ShapeNodeEditPart) structuredSelection
                        .getFirstElement();
            }
        }
        return element;
    }

    /**
     * Returns the list of the children of the selected node.
     * 
     * @param selectedElement the selected node.
     * @return the list of the children of the selected node.
     */
    @SuppressWarnings("unchecked")
    private List<ConnectionNodeEditPart> getTargetEditPart(
            ShapeNodeEditPart selectedElement) {

        List<ConnectionNodeEditPart> targetLinks = new ArrayList<ConnectionNodeEditPart>();
        // gets links.
        List<ConnectionNodeEditPart> sourceLinks = selectedElement
                .getSourceConnections();
        
        for (ConnectionNodeEditPart editPart : sourceLinks) {
            if (DcaseEditorUtil.isDcaseLinkEditPart(editPart)) {
                targetLinks.add(editPart);
            }
        }
        return targetLinks;
    }

    /**
     * Returns the comparator to compare the nodes by position.
     * 
     * @return the comparator to compare the nodes by position.
     */
    private Comparator<ConnectionNodeEditPart> getBasicLinkComparator() {
        return new Comparator<ConnectionNodeEditPart>() {
            /**
             * Compares the nodes by position.
             * 
             * @param arg0 the node.
             * @param arg1 the node.
             * 
             * @return 0 if arg0 equals arg1;1 if arg0 is greater than arg1;-1 if arg0 is less than arg1.
             */
            public int compare(ConnectionNodeEditPart arg0, ConnectionNodeEditPart arg1) {
                GraphicalEditPart editPart0 = (GraphicalEditPart) arg0.getTarget();
                GraphicalEditPart editPart1 = (GraphicalEditPart) arg1.getTarget();
                int arg0X = editPart0.getFigure().getBounds().x;
                int arg1X = editPart1.getFigure().getBounds().x;
                if (arg0X == arg1X) {
                    int arg0Y = editPart0.getFigure().getBounds().y;
                    int arg1Y = editPart1.getFigure().getBounds().y;
                    if (arg0Y == arg1Y) {
                        return 0;
                    } else if (arg0Y > arg1Y) {
                        return 1;
                    }
                    return -1;
                } else if (arg0X > arg1X) {
                    return 1;
                }
                return -1;
            }
        };
    }
}
