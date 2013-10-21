/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.List;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.util.NamePropertyAutoCreator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to add a model to a diagram.
 */
public class ModelAdditionCommand extends AbstractTransactionalCommand {

    /**
     * the source Argument.
     */
    private Argument sourceArgument;

    /**
     * the target Argument.
     */
    private Argument targetArgument;
    
    /**
     * whether rewrites node name.
     */
    private boolean rewriteName;

    /**
     * Creates transaction command to add a model to a diagram and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the user-readable label, should never be null.
     * @param affectedFiles the list of affected IFiles; may be null.
     * @param sourceArgument the source Argument.
     * @param targetArgument the target Argument.
     */
    @SuppressWarnings("unchecked")
    public ModelAdditionCommand(TransactionalEditingDomain domain,
            String label, List affectedFiles, Argument sourceArgument,
            Argument targetArgument) {
        super(domain, label, affectedFiles);
        this.sourceArgument = sourceArgument;
        this.targetArgument = targetArgument;
        this.rewriteName = true;
    }
    
    /**
     * Creates transaction command to add a model to a diagram and initializes it.
     * 
     * @param domain the editing domain.
     * @param label the user-readable label, should never be null.
     * @param affectedFiles the list of affected IFiles; may be null.
     * @param sourceArgument the source Argument.
     * @param targetArgument the target Argument.
     * @param rewriteName whether rewrites node name.
     */
    public ModelAdditionCommand(TransactionalEditingDomain domain,
            String label, List affectedFiles, Argument sourceArgument,
            Argument targetArgument, boolean rewriteName) {
        super(domain, label, affectedFiles);
        this.sourceArgument = sourceArgument;
        this.targetArgument = targetArgument;
        this.rewriteName = rewriteName;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
     *  #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {

        EList<BasicNode> nodeList = sourceArgument.getRootBasicNode();
        EList<BasicLink> linkList = sourceArgument.getRootBasicLink();

        // sets the value of the name attribute.
        NamePropertyAutoCreator nameCreator = new NamePropertyAutoCreator();
        nameCreator.loadDiagram(targetArgument);
        if(rewriteName) {
        	for (BasicNode node : nodeList) {
        		node.setName(nameCreator.getInitialName(node));
        	}
        	for (BasicLink list : linkList) {
        		list.setName(nameCreator.getInitialName(list));
        	}
        }

        // adds nodes.
        while (!nodeList.isEmpty()) {
            targetArgument.getRootBasicNode().add(nodeList.get(0));
        }
        // adds links.
        while (!linkList.isEmpty()) {
            targetArgument.getRootBasicLink().add(linkList.get(0));
        }
        
        /** 
         * refreshes children those use global parameters. 
         */ 
        targetArgument.Refresh();

        return CommandResult.newOKCommandResult();
    }

}
