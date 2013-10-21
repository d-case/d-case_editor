/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;


import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * A validator that tests weather selected element is a file.
 */
public class FileSelectionStatusValidator implements
        ISelectionStatusValidator {

    /**
     * Validates an array of elements and returns the resulting status.
     * 
     * @param selection the elements to validate
     * @return OK if and only if first of array of elements is a file; ERROR otherwise
     * @see org.eclipse.ui.dialogs.ISelectionStatusValidator.validate#validate(java.lang.Object[])
     */
    public IStatus validate(Object[] selection) {
        if (selection.length == 1) {
            if (selection[0] instanceof IFile) {
                return new Status(IStatus.OK, DcaseDiagramEditorPlugin.ID, ""); //$NON-NLS-1$
            }
        }
        return new Status(IStatus.ERROR, DcaseDiagramEditorPlugin.ID, ""); //$NON-NLS-1$
    }
}
