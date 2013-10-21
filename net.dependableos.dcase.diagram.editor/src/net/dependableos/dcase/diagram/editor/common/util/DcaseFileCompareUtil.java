/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.common.util;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.notation.View;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.logic.compare.ExpressModelDiffrenceLogic;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.impl.ArgumentImpl;

/**
 * A utility class that compares current editing diagram to specified diagram.
 * 
 */
public final class DcaseFileCompareUtil {

    /**
     * A private contractor.
     */
    private DcaseFileCompareUtil() {
    }

    /**
     * Compares current editing diagram to specified diagram.
     * @param targetDiagramFile the target D-Case diagram file of comparison
     */
    @SuppressWarnings("rawtypes")
    public static void compare(IFile targetDiagramFile) {
        // gets the model file.
        IFile modelFile = ModelUtil
                .getModelFileFromDiagramFile(targetDiagramFile);
        if (!modelFile.exists()) {
            MessageWriter
                      .showErrorMessageBox(Messages.DcaseFileCompareUtil_ModelFileNotExistMessage);
            return;
        }
        
        EObject obj = ModelUtil.getModel(modelFile);
        if (obj instanceof ArgumentImpl) {
            // gets argument of the target.
            ArgumentImpl targetArgumentImpl = (ArgumentImpl) obj;
            LinkManager targetLinkManager = new LinkManager();
            targetLinkManager
                    .load((XMLResource) targetArgumentImpl.eResource());

            // gets argument of the source.
            ArgumentEditPart argumentEditPart = DcaseEditorUtil
                    .getCurrentArgumentEditPart();
            ArgumentImpl sourceArgumentImpl = (ArgumentImpl) ((View) argumentEditPart
                    .getModel()).getElement();
            LinkManager sourceLinkManager = new LinkManager();
            sourceLinkManager
                    .load((XMLResource) sourceArgumentImpl.eResource());

            // compares
            ExpressModelDiffrenceLogic diffLogic = new ExpressModelDiffrenceLogic(
                    sourceLinkManager, targetLinkManager);
            diffLogic.compare();

            CompoundCommand cc = new CompoundCommand(
                    Messages.DcaseFileCompareUtil_CompareErrorMessage_1);
            // gets a command to set line color.
            List cmds = diffLogic.getCommand().getCommands();
            for (Object cp : cmds) {
                if (cp instanceof ICommandProxy) {
                    cc.add((ICommandProxy) cp);
                }
            }

            // executes the command.
            argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
                    .execute(cc);


            // saves the target information.
            DcaseDiagramEditor currentDcaseEditor = DcaseEditorUtil
                    .getCurrentDcaseEditor();
            currentDcaseEditor.setCompareTargetFile(targetDiagramFile);
        } else {
            throw new DcaseSystemException(
                    Messages.DcaseFileCompareUtil_CompareErrorMessage_1, null,
                    MessageTypeImpl.COMPARE_MODEL_FILE_FAILED);
        }

    }
}
