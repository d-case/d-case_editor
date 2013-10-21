/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.FunctionTypeImpl;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.assessment.Assessment;
import net.dependableos.dcase.diagram.editor.logic.assessment.ChangeScoreTransactionCommand;
import net.dependableos.dcase.impl.BasicNodeImpl;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to calculate the score.
 */
public class CalculateScoreAction implements IObjectActionDelegate {

    /**
     * the action ID.
     */
    public static final String ID = "net.dependableos.dcase.diagram.editor.CalculateScoreActionID"; //$NON-NLS-1$ 

    /**
     * the selected element.
     */
    private GraphicalEditPart selectedElement;

    /**
     * Sets the active part for the delegate.
     * 
     * @param action
     *            the action proxy that handles presentation portion of the
     *            action; must not be null.
     * @param targetPart
     *            the new part target; must not be null.
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    /**
     * Calculates the scores and set them to each nodes.
     * 
     * @param action IAction.
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        Object model = selectedElement.getModel();
        if (model instanceof ViewImpl) {
            EObject eObj = ((ViewImpl) model).getElement();

            if (eObj instanceof BasicNodeImpl) {
                XMLResource res = (XMLResource) eObj.eResource();
                LinkManager linkManager = new LinkManager();
                linkManager.load(res);
                Assessment assessment = new Assessment(linkManager);
                IFile diagramFile = DcaseEditorUtil.getCurrentDiagramFile();
                MessageWriter.clearMarkers(diagramFile,
                        FunctionTypeImpl.CALCULATE_SCORE);
                try {

                    // calculates the score.
                    assessment.calculateScore();

                    // creates the map of nodes to set the value of score attribute.
                    Map<BasicNode, BigDecimal> changeNodeList = new HashMap<BasicNode, BigDecimal>(
                            COLLECTION_INITIAL_CAPACITY);

                    for (Entry<String, BigDecimal> entry : assessment
                            .getChangeList().entrySet()) {
                        changeNodeList.put(linkManager.getBasicNode(entry
                                .getKey()), entry.getValue());
                    }

                    // creates a transaction command to update properties.
                    ChangeScoreTransactionCommand tranCommand = new ChangeScoreTransactionCommand(
                            selectedElement.getEditingDomain(),
                            Menus.CalculateScoreAction_0, changeNodeList);

                    selectedElement.getDiagramEditDomain()
                            .getDiagramCommandStack().execute(
                                    new ICommandProxy(tranCommand));

                } catch (DcaseRuntimeException dcaseRuntimeException) {
                    dcaseRuntimeException.setResource(diagramFile);
                    MessageWriter
                            .writeMessageToProblemsView(dcaseRuntimeException);
                    MessageWriter.showMessageBoxSeeProblems();
                } catch (DcaseSystemException dcaseSystemException) {
                    MessageWriter.writeMessageToErrorLog(dcaseSystemException);
                    MessageWriter.showMessageBoxSeeErroLog();
                }

            }
        }

    }


    /**
     * Notifies this action delegate that the selection in the workbench has changed.
     * 
     * @param action the action proxy that handles presentation portion of 
     *      the action
     * @param selection the current selection, or null if there
     *      is no selection.
     */
    public void selectionChanged(IAction action, ISelection selection) {
        selectedElement = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof GraphicalEditPart) {
                selectedElement = (GraphicalEditPart) structuredSelection
                        .getFirstElement();
            }
        }

    }

}
