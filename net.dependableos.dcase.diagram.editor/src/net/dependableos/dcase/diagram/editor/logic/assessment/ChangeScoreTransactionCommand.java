/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.assessment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to change the scores.
 */
public class ChangeScoreTransactionCommand extends AbstractTransactionalCommand {

    /**
     * the map of new scores.
     */
    private Map<BasicNode, BigDecimal> changeList;

    /**
     * Creates a command and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param changeList the map of new scores.
     */
    public ChangeScoreTransactionCommand(TransactionalEditingDomain domain,
            String label, Map<BasicNode, BigDecimal> changeList) {
        super(domain, label, null);
        this.changeList = changeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        Set<BasicNode> nodeList = changeList.keySet();

        for (BasicNode node : nodeList) {
            BigDecimal score = changeList.get(node);
            if (node instanceof Goal) {
                ((Goal) node).setScore(score);
            }
        }
        return CommandResult.newOKCommandResult();
    }

}
