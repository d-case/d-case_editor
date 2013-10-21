/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;
import static net.dependableos.dcase.diagram.editor.command.ChangeBackgroundColorEditPartsCommand.CONST_CHANGE_BACKGROUNDCOLOR_COMMAND_LABEL;
import static net.dependableos.dcase.diagram.editor.command.ChangeVisibleEditPartsCommand.CONST_CHANGE_VISIBLE_COMMAND_LABEL;
import static net.dependableos.dcase.diagram.editor.command.NodeChildrenHideHandler.CONST_HIDE_COLOR;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.Menus;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A handler to show children of the selected node.
 */
public class NodeChildrenShowHandler extends AbstractHandler {

    /**
     * the background color of the node showing children.
     */
    public static final Color CONST_SHOW_COLOR = ColorConstants.white;

    /**
     * the selected element.
     */
    private ShapeNodeEditPart selectedElement;

    /**
     * the Set of the EditParts to show.
     */
    private Set<GraphicalEditPart> changeVisibleEditPartSet;

    /**
     * the Map of EditParts to change the background color.
     */
    private Map<DcaseDelegateNodeEditPart, Color> changeBackgroundColorEditPartMap;

    /**
     *  Shows children of the selected node.
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
                initialize();

                // gets nodes to show.
                collectEditpartsToShow(selectedElement);

                if (changeVisibleEditPartSet.size() > 0) {
                    // creates the command.
                    CompoundCommand cc = new CompoundCommand(
                            Menus.NodeChildrenShowHandler_0);
                    cc.add(new ICommandProxy(new ChangeVisibleEditPartsCommand(
                            CONST_CHANGE_VISIBLE_COMMAND_LABEL,
                            changeVisibleEditPartSet, true)));
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
     * Initializes.
     */
    public void initialize() {
        changeVisibleEditPartSet = new HashSet<GraphicalEditPart>(
                COLLECTION_INITIAL_CAPACITY);
        changeBackgroundColorEditPartMap = new HashMap<DcaseDelegateNodeEditPart, Color>();
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
     * Add the children to show to the map.
     * 
     * @param editPart the parent node.
     */
    private void collectEditpartsToShow(ShapeNodeEditPart editPart) {
        for (Object link : editPart.getSourceConnections()) {
            ConnectionNodeEditPart dLink = (ConnectionNodeEditPart) link;
            if (!dLink.getFigure().isVisible()) {
                // checks descendants.
                collectEditpartsToShow((ShapeNodeEditPart) (dLink.getTarget()));
                changeVisibleEditPartSet.add(dLink);
            }
        }
        if (((DcaseDelegateNodeEditPart) editPart).getBackgroundColorEx() == CONST_HIDE_COLOR) {
            changeBackgroundColorEditPartMap.put(
                    (DcaseDelegateNodeEditPart) editPart, CONST_SHOW_COLOR);
        }
        // doesn't add the selected node to the map.
        if (editPart != selectedElement) {
            changeVisibleEditPartSet.add(editPart);
        }
    }

    /**
     * Returns the children to show.
     * 
     * @param root the root.
     * @return the children to show.
     */
    public Set<GraphicalEditPart> getChildrenToShow(ShapeNodeEditPart root) {
        selectedElement = root;
        initialize();
        collectEditpartsToShow(selectedElement);
        return changeVisibleEditPartSet;
    }

}
