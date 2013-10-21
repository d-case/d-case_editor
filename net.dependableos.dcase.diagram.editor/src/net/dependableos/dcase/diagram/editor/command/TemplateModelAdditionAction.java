/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.util.Set;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.System;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;
import net.dependableos.dcase.diagram.editor.template.TemplateViewPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.Action;
import org.eclipse.osgi.util.NLS;

/**
 * An action to add a model from a template.
 */
public class TemplateModelAdditionAction extends Action {
    /**
     * the label for command to add a model.
     */
    private static final String MODEL_ADDITION_COMMAND_LABEL = "template model addition command"; //$NON-NLS-1$
    /**
     * the label for command to select edit parts.
     */
    private static final String SELECT_COMMAND_LABEL = "select command for template addition"; //$NON-NLS-1$
    /**
     * the label for command to arrange layout.
     */
    private static final String ARRANGE_COMMAND_LABEL = "arrange command for template addition"; //$NON-NLS-1$
    /**
     * the label for command to persist edit parts.
     */
    private static final String PERSISTANCE_COMMAND_LABEL = "persistance command for template addition"; //$NON-NLS-1$
    /**
     * the label for command to notify parameters.
     */
    private static final String NOTIFY_COMMAND_LABEL = "notify command for template addition"; //$NON-NLS-1$

    /**
     * the template view part.
     */
    private TemplateViewPart viewPart;

    /**
     * Creates the action and initializes it.
     * 
     * @param viewPart the template view part.
     */
    public TemplateModelAdditionAction(TemplateViewPart viewPart) {
        super();
        this.viewPart = viewPart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {

        // gets the selected tamplate.
        IFile modelFile = viewPart.getSelectedTemplateFile();
        if (modelFile != null) {

            try {
                // gets the editing diagram.
                Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
                if (currentDiagram == null) {
                    throw new DcaseRuntimeException(
                            Messages.TemplateModelAdditionAction_0, null, null,
                            0, MessageTypeImpl.TEMPLATE_INSERT_OPERATION_FAILED);
                }

                // gets the editing argument edit part.
                ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
                        .getCurrentArgumentEditPart();
                if (currentArgumentEditPart == null) {
                    throw new DcaseSystemException(
                            Messages.TemplateModelAdditionAction_1, null,
                            MessageTypeImpl.TEMPLATE_INSERT_INTERNAL_ERROR);
                }

                // gets the model of the selected template.
                EObject templateModel = getModel(modelFile);
                if (templateModel == null) {
                    throw new DcaseSystemException(
                            Messages.TemplateModelAdditionAction_2, null,
                            MessageTypeImpl.TEMPLATE_INSERT_INTERNAL_ERROR);
                }
                // tests whether a node exists in the selected template.
                EList<BasicNode> nodeList = ((Argument) templateModel)
                        .getRootBasicNode();
                if (nodeList == null || nodeList.size() == 0) {
                    throw new DcaseRuntimeException(
                            Messages.TemplateModelAdditionAction_3, null, null,
                            0, MessageTypeImpl.TEMPLATE_INSERT_OPERATION_FAILED);
                }

                // copy the model of the selected template.
                EObject copyModel = (EObject) EcoreUtil.copy(templateModel);
                Argument copyArgument = (Argument)copyModel;

                // shows the dialog to set parameters.
                BasicNode rootNode = ModuleUtil.getRootNode(copyArgument);
                if (rootNode != null) {
                	// process Parameter nodes.
                    for (BasicLink link : copyArgument.getRootBasicLink()) {
                    	BasicNode cNode = link.getTarget();
                    	if (link.getSource() != rootNode || ! (cNode instanceof System)) {
                    		continue;
                    	}
                    	if (! ParameterUtil.processParameter(cNode)) {
                    		return;
                    	}
                    }
                    // gets the set of node IDs from the editing argument edit part.
                    Set<String> idSet = DcaseEditorUtil.getChildUUIDs(currentArgumentEditPart);

                    // creates the command to add the model.
                    CompoundCommand command = createTemplateAdditionCommand(
                    		currentDiagram, copyModel, currentArgumentEditPart,
                    		idSet);
                    
                    // executes the command.
                    currentArgumentEditPart.getDiagramEditDomain()
                    	.getDiagramCommandStack().execute(command);
                } else {
                    MessageWriter.writeMessageToConsole(
                            NLS.bind("{0}: not a single tree.", ModuleUtil.getModuleName(modelFile)), //$NON-NLS-1$
                            		MessageTypeImpl.DIAGNOSIS);
                }

            } catch (DcaseRuntimeException dre) {
                // handles the runtime exception.
                MessageWriter.showErrorMessageBox(dre.getMessage());
            } catch (DcaseSystemException dse) {
                // handles the d-case system exception.
                MessageWriter.writeMessageToErrorLog(dse);
                MessageWriter.showMessageBoxSeeErroLog();
            }

        } else {

            MessageWriter.writeMessageToConsole(
                    "No template file is selected.", MessageTypeImpl.DIAGNOSIS); //$NON-NLS-1$
        }

    }

    /**
     * Returns the EObject from the specified model file.
     * 
     * @param modelFile the model file.
     * @return the EObject
     */
    private EObject getModel(IFile modelFile) {
        return ModelUtil.getModel(modelFile);
    }

    /**
     * Creates the command to add the template.
     * 
     * @param currentDiagram the current diagram.
     * @param templateModel the EObject from the template.
     * @param currentArgumentEditPart the current argument edit part.
     * @param excludeIdSet the set of node IDs those will be excluded to select and arrange layout.
     * @return the command to add the template.
     */
    private CompoundCommand createTemplateAdditionCommand(
            Diagram currentDiagram, EObject templateModel,
            ArgumentEditPart currentArgumentEditPart, Set<String> excludeIdSet) {

        TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
                .getEditingDomain(currentDiagram.eResource().getResourceSet());

        // creates the compound command.
        CompoundCommand cc = new CompoundCommand(
                Menus.TemplateModelAdditionAction_0);

        // creates the command to add the model.
        Argument currentArgument = (Argument) currentDiagram.getElement();
        Argument templateArgument = (Argument) templateModel;
        ICommand additionCommand = new ModelAdditionCommand(currentDomain,
                MODEL_ADDITION_COMMAND_LABEL, null, templateArgument,
                currentArgument);
        cc.add(new ICommandProxy(additionCommand));

        // creates the command to select nodes those will be add from the template.
        ICommand selectCommand = new SelectExcludesCommand(
                SELECT_COMMAND_LABEL, currentArgumentEditPart, excludeIdSet);
        cc.add(new ICommandProxy(selectCommand));

        // creates the command to arrange layout nodes those will be add from the template.
        if (templateArgument.getRootBasicNode().size() > 1) {
            ICommand arrangeCommand = new ArrangeExcludesCommand(currentDomain,
                    ARRANGE_COMMAND_LABEL, null, currentArgumentEditPart,
                    excludeIdSet);
            cc.add(new ICommandProxy(arrangeCommand));

            // creates the command to notify parameters.
        	NotifyParametersCommand notifyCommand = new NotifyParametersCommand(currentDomain,
        			NOTIFY_COMMAND_LABEL, null, currentArgumentEditPart, excludeIdSet);
        	cc.add(new ICommandProxy(notifyCommand));
        }
        
        // creates the command to persist the edit parts.
        ICommand persistanceCommand = new NotationPersistanceCommand(
                currentDomain, PERSISTANCE_COMMAND_LABEL, null,
                currentArgumentEditPart);
        cc.add(new ICommandProxy(persistanceCommand));

        return cc;
    }

}
