/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;
import static net.dependableos.dcase.diagram.editor.command.ChangeBackgroundColorEditPartsCommand.CONST_CHANGE_BACKGROUNDCOLOR_COMMAND_LABEL;
import static net.dependableos.dcase.diagram.editor.command.ChangeVisibleEditPartsCommand.CONST_CHANGE_VISIBLE_COMMAND_LABEL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseDelegateNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler to hide children of the selected node.
  */
public class NodeChildrenHideHandler extends AbstractHandler {

    /**
     * the background color of the node hiding children.
     */
    public static final Color CONST_HIDE_COLOR = ColorConstants.orange;

    /**
     * the history to check looped link.
     */
    private LinkedList<String> cyclicRoute;

    /**
     * the Set of the EditParts to hide.
     */
    private Set<GraphicalEditPart> changeVisibleEditPartSet;

    /**
     * the Map of EditParts to change the background color.
     */
    private Map<DcaseDelegateNodeEditPart, Color> changeBackgroundColorEditPartMap;

    /**
     * the List of the checked node.
     */
    private Set<String> checkedNodeList;

    /**
     * the selected element.
     */
    private ShapeNodeEditPart selectedElement;

    /**
     * Hides children of the selected node.
     * 
     * @param event ExecutionEvent
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // gets the selected node.
        //selectedElement = getSelectedElement(event);
        selectedElement = DcaseEditorUtil.getFirstCurrentSelectedPart();
        if (selectedElement != null) {
            try {
                // initializes.
                checkedNodeList = new HashSet<String>(
                        COLLECTION_INITIAL_CAPACITY);
                cyclicRoute = new LinkedList<String>();
                changeVisibleEditPartSet = new HashSet<GraphicalEditPart>(
                        COLLECTION_INITIAL_CAPACITY);
                changeBackgroundColorEditPartMap = new HashMap<DcaseDelegateNodeEditPart, Color>();

                // tests if a child exists.
                if (selectedElement.getSourceConnections().size() == 0) {
                    throw new DcaseRuntimeException(
                            Messages.NodeChildrenHideHandler_2, null, null, 0,
                            MessageTypeImpl.HIDE_CHILDREN_HIDE_FAILED);
                }

                // gets nodes to hide.
                getHideNodeChildren(selectedElement);

                if (changeVisibleEditPartSet.size() > 0) {
                    // changes background color.
                    changeBackgroundColorEditPartMap.put(
                            (DcaseDelegateNodeEditPart) selectedElement,
                            CONST_HIDE_COLOR);

                    // creates a command.
                    CompoundCommand cc = new CompoundCommand(
                            Menus.NodeChildrenHideHandler_0);
                    cc.add(new ICommandProxy(new ChangeVisibleEditPartsCommand(
                            CONST_CHANGE_VISIBLE_COMMAND_LABEL,
                            changeVisibleEditPartSet, false)));
                    cc.add(new ICommandProxy(
                            new ChangeBackgroundColorEditPartsCommand(
                                    CONST_CHANGE_BACKGROUNDCOLOR_COMMAND_LABEL,
                                    changeBackgroundColorEditPartMap)));

                    // executes the command.
                    selectedElement.getDiagramEditDomain()
                            .getDiagramCommandStack().execute(cc);
                }

            } catch (DcaseRuntimeException dre) {
                // handles the runtime exception.
                MessageWriter.showErrorMessageBox(dre.getMessage());
            } catch (DcaseSystemException dse) {
                // handles the d-case system exception.
                MessageWriter.writeMessageToErrorLog(dse);
                MessageWriter.showMessageBoxSeeErroLog();
            }
        }
        return null;
    }

    /**
     * Returns the selected node.
     * 
     * @param event the event.
     * @return the selected node.
     */
    private ShapeNodeEditPart getSelectedElement(ExecutionEvent event) {
        ISelection selection = HandlerUtil.getActiveMenuSelection(event);
        ShapeNodeEditPart element = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof ShapeNodeEditPart) {
                element = (ShapeNodeEditPart) structuredSelection
                        .getFirstElement();
            }
        }
        return element;
    }

    /**
     * Add the children to hide to the map.
     * Throws exception if the looped link,the conjunction or the link besides D-Case links is detected.
     * 
     * @param editPart the parent node.
     */
    private void getHideNodeChildren(ShapeNodeEditPart editPart) {

        String uuid = DcaseEditorUtil.getUUIDs(editPart);
        // adds to the history.
        cyclicRoute.add(uuid);

        // checks the conjunction.
        if (selectedElement != editPart) {
            checkJunctionLink(editPart);
        }

        // checks the looped link.
        if (!checkedNodeList.contains(uuid)) {
            checkedNodeList.add(uuid);
        } else {
            MessageWriter.writeMessageToConsole(NLS.bind(
                    "Cyclic state was found.({0})", cyclicRoute.toString()), //$NON-NLS-1$
                    MessageTypeImpl.DIAGNOSIS);
            throw new DcaseRuntimeException(Messages.NodeChildrenHideHandler_0,
                    null, null, 0, MessageTypeImpl.HIDE_CHILDREN_HIDE_FAILED);
        }
        
        for (Object link : editPart.getSourceConnections()) {
            // throws exception if the link besides the D-Case links is detected.
            if (!DcaseEditorUtil.isDcaseLinkEditPart(link)) {
                throw new DcaseRuntimeException(
                        Messages.NodeChildrenHideHandler_3, null, null, 0,
                        MessageTypeImpl.HIDE_CHILDREN_HIDE_FAILED);
            }
            ConnectionNodeEditPart dLink = (ConnectionNodeEditPart) link;
            if (dLink.getFigure().isVisible()) {
                // checks descendants.
                getHideNodeChildren((ShapeNodeEditPart) (dLink.getTarget()));
                changeVisibleEditPartSet.add(dLink);
            }
        }
        // doesn't add the selected node to the map.
        if (selectedElement != editPart) {
            changeVisibleEditPartSet.add(editPart);
        }
        cyclicRoute.removeLast();
    }

    /**
     * Tests if the conjunction is exists.Throws the exception if the conjunction is detected.
     * 
     * @param editPart the node to test.
     */
    private void checkJunctionLink(ShapeNodeEditPart editPart) {
        if (editPart.getTargetConnections().size() > 1) {
            throw new DcaseRuntimeException(Messages.NodeChildrenHideHandler_1,
                    null, null, 0, MessageTypeImpl.HIDE_CHILDREN_HIDE_FAILED);
        }
    }
}
