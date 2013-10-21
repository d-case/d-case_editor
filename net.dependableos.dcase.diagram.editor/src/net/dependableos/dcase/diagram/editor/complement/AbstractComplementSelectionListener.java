/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;

import java.util.Set;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.command.ArrangeExcludesCommand;
import net.dependableos.dcase.diagram.editor.command.ModelAdditionCommand;
import net.dependableos.dcase.diagram.editor.command.NotationPersistanceCommand;
import net.dependableos.dcase.diagram.editor.command.SelectExcludesCommand;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * This class provides methods that deal with the events that are generated when a complement menu selected.
 */
public abstract class AbstractComplementSelectionListener  extends SelectionAdapter {

    /**
     * the command label.
     */
    private final String commandLabel;
    /**
     * the command label for adding a model.
     */
    private static final String MODEL_ADDITION_COMMAND_LABEL = "node addition command"; //$NON-NLS-1$
    /**
     * the command label for selecting nodes.
     */
    private static final String SELECT_COMMAND_LABEL = "select command for template addition"; //$NON-NLS-1$
    /**
     * the command label for arranging layout of nodes.
     */
    private static final String ARRANGE_COMMAND_LABEL = "arrange command for template addition"; //$NON-NLS-1$
    /**
     * 
     * the command label for persisting the nodes.
     */
    private static final String PERSISTANCE_COMMAND_LABEL = "persistance command for template addition"; //$NON-NLS-1$

    /**
     * Creates the adaptor and initializes it.
     * 
     * @param commandLabel the command label.
     */
    protected AbstractComplementSelectionListener(String commandLabel) {
        this.commandLabel = commandLabel;
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
     * Executes the complement.
     */
    protected void execute() {
        try {
            addModel(getModel());
        } catch (DcaseRuntimeException dcaseRuntimeException) {
            MessageWriter.writeMessageToProblemsView(dcaseRuntimeException);
            MessageWriter.showMessageBoxSeeProblems();
        } catch (DcaseSystemException dcaseSystemException) {
            MessageWriter.writeMessageToErrorLog(dcaseSystemException);
            MessageWriter.showMessageBoxSeeErroLog();
        }
    }

    /**
     * Adds the model.
     * 
     * @param argument the model.
     */
    protected void addModel(Argument argument) {
        if (argument == null) {
            return;
        }
        // gets the current diagram.
        Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
        if (currentDiagram == null) {
            return;
        }

        // gets the current argument edit part.
        ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
                .getCurrentArgumentEditPart();
        if (currentArgumentEditPart == null) {
            return;
        }

        // gets the IDs of the nodes in the current diagram.
        Set<String> idSet = DcaseEditorUtil
                .getChildUUIDs(currentArgumentEditPart);

        // creates the command to add the model.
        CompoundCommand command = createModelAdditionCommand(currentDiagram,
                argument, currentArgumentEditPart, idSet);

        // executes the command.
        currentArgumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
                .execute(command);

    }

    /**
     * Returns the model to add to the current diagram.
     * 
     * @return the model.
     */
    abstract Argument getModel();

    /**
     * Creates the command to add the model.
     * 
     * @param currentDiagram the current diagram.
     * @param argument the model.
     * @param currentArgumentEditPart the current argument edit part.
     * @param excludeIdSet the set of IDs those will be excluded to arrange layout.
     * @return the command to add the model.
     */
    private CompoundCommand createModelAdditionCommand(Diagram currentDiagram,
            Argument argument, ArgumentEditPart currentArgumentEditPart,
            Set<String> excludeIdSet) {

        TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
                .getEditingDomain(currentDiagram.eResource().getResourceSet());

        // creates the compound command.
        CompoundCommand cc = new CompoundCommand(commandLabel);

        Argument currentArgument = (Argument) currentDiagram.getElement();

        // creates the command to add the model.
        ICommand additionCommand = new ModelAdditionCommand(currentDomain,
                MODEL_ADDITION_COMMAND_LABEL, null, argument, currentArgument);
        cc.add(new ICommandProxy(additionCommand));

        // creates the command to select.
        ICommand selectCommand = new SelectExcludesCommand(
                SELECT_COMMAND_LABEL, currentArgumentEditPart, excludeIdSet);
        cc.add(new ICommandProxy(selectCommand));

        // creates the command to arrange layout.
        if (argument.getRootBasicNode().size() > 1) {

            // the command to arrange layout is work when the count of the nodes is 2 or more.

            ICommand arrangeCommand = new ArrangeExcludesCommand(currentDomain,
                    ARRANGE_COMMAND_LABEL, null, currentArgumentEditPart,
                    excludeIdSet);
            cc.add(new ICommandProxy(arrangeCommand));
        }

        // creates the command to persist the nodes.
        ICommand persistanceCommand = new NotationPersistanceCommand(
                currentDomain, PERSISTANCE_COMMAND_LABEL, null,
                currentArgumentEditPart);
        cc.add(new ICommandProxy(persistanceCommand));

        return cc;
    }

}
