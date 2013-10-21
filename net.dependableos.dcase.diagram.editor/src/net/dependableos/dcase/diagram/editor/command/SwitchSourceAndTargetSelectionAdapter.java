/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;


import java.util.List;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.logic.compare.ExpressModelDiffrenceLogic;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A adapter to switch target and source to compare.
 */
public class SwitchSourceAndTargetSelectionAdapter extends SelectionAdapter {
    /**
     *  new source diagram file.
     */
    private IFile diagramFile = null;

    /**
     * Creates the adaptor and initializes it.
     * 
     * @param diagramFile new source diagram file.
     */
    public SwitchSourceAndTargetSelectionAdapter(IFile diagramFile) {
        this.diagramFile = diagramFile;
    }

    /**
     * Switches target and source to compare.
     * 
     * @param e the event.
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        try {
            // tests whether the diagram file exits.
            if (!diagramFile.getLocation().toFile().exists()) {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.SwitchSourceAndTargetSelectionAdapter_2,
                        diagramFile.getFullPath().toString()));
                return;
            }
            // gets the model file.
            IFile modelFile = ModelUtil
                    .getModelFileFromDiagramFile(diagramFile);
            if (!modelFile.getLocation().toFile().exists()) {
                MessageWriter.showErrorMessageBox(NLS.bind(
                        Messages.SwitchSourceAndTargetSelectionAdapter_0,
                        modelFile.getFullPath().toString()));
                return;
            }

            // shows error message if the source equals the target.
            ArgumentEditPart currentArgumentEditpart = DcaseEditorUtil
                    .getCurrentArgumentEditPart();
            Argument targetArgument = (Argument) ((View) currentArgumentEditpart
                    .getModel()).getElement();
            URI modelUri = targetArgument.eResource().getURI();
            IFile currentModelFile = ResourcesPlugin.getWorkspace().getRoot()
                    .getFile(new Path(modelUri.toPlatformString(false)));
            if (currentModelFile.equals(modelFile)) {
                MessageWriter
                        .showErrorMessageBox(Messages.SwitchSourceAndTargetSelectionAdapter_1);
                return;
            }

            // opens the diagram
            openDcaseEditor(diagramFile);

            // gets the edit part of the source.
            ArgumentEditPart newArgumentEditpart = DcaseEditorUtil
                    .getCurrentArgumentEditPart();
            Argument sourceArgument = (Argument) ((View) newArgumentEditpart
                    .getModel()).getElement();

            // compares.
            compareModel(sourceArgument, targetArgument);
        } catch (DcaseSystemException de) {
            MessageWriter.writeMessageToErrorLog(de);
            MessageWriter.showMessageBoxSeeErroLog();
        }
    }

    /**
     * Opens the diagram.
     * 
     * @param diagramFile the diagram file.
     */
    private void openDcaseEditor(IFile diagramFile) {
        IEditorInput input = new FileEditorInput(diagramFile);
        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
        try {
            workbenchPage.openEditor(input, DcaseDiagramEditor.ID);
        } catch (PartInitException pie) {
            throw new DcaseSystemException(Messages.COMMON_EXCEPTION_partInit,
                    pie, MessageTypeImpl.UNDEFINED);
        }
    }

    /**
     * Compares the source diagram and the target diagram and shows the result on the source diagram and console.
     * 
     * @param sourceArgument the source Argument
     * @param targetArgument the targeted Argument
     */
    @SuppressWarnings("rawtypes")
    private void compareModel(Argument sourceArgument, Argument targetArgument) {
        // gets the link manager of the target.
        LinkManager targetLinkManager = new LinkManager();
        targetLinkManager.load((XMLResource) targetArgument.eResource());

        // gets the link manager of the source.
        LinkManager sourceLinkManager = new LinkManager();
        sourceLinkManager.load((XMLResource) sourceArgument.eResource());

        // compares.
        ExpressModelDiffrenceLogic diffLogic = new ExpressModelDiffrenceLogic(
                sourceLinkManager, targetLinkManager);
        diffLogic.compare();

        CompoundCommand cc = new CompoundCommand(
                Menus.SwitchSourceAndTargetSelectionAdapter_0);
        // gets the commands to change line color and line width.
        List cmds = diffLogic.getCommand().getCommands();
        for (Object cp : cmds) {
            if (cp instanceof ICommandProxy) {
                cc.add((ICommandProxy) cp);
            }
        }

        // executes the commands.
        ArgumentEditPart argumentEditPart = DcaseEditorUtil
                .getCurrentArgumentEditPart();
        argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
                .execute(cc);
    }
}
