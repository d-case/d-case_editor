/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_FILE_EXTENSION;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseFileCompareUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.providers.FileExtensionRestrictTreeContentProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This class provides methods that deal with the events events that are generated when "Compare To" menu selected.
 */
public class CompareDcaseFileSelectionAdapter extends SelectionAdapter {


    /**
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        try {
            // shows a dialog to select a file to compare.
            IFile selectedDiagramFile = showDialogAndGetDiagramFile();
            if (selectedDiagramFile == null) {
                return;
            }
            
            // compares.
            DcaseFileCompareUtil.compare(selectedDiagramFile);
            
        } catch (DcaseSystemException de) {
            MessageWriter.writeMessageToErrorLog(de);
            MessageWriter.showMessageBoxSeeErroLog();
        }
    }

    /**
     * Shows a dialog to select a diagram file to compare.
     * 
     * @return selected diagram file.
     */
    private IFile showDialogAndGetDiagramFile() {
        ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
                DcaseEditorUtil.getActiveWindowShell(),
                new DecoratingLabelProvider(new WorkbenchLabelProvider(),
                        PlatformUI.getWorkbench().getDecoratorManager()
                                .getLabelDecorator()),
                new FileExtensionRestrictTreeContentProvider(PropertyUtil
                        .getSystemProperty(DIAGRAM_FILE_EXTENSION)));

        fileDialog.setInput(ResourcesPlugin.getWorkspace().getRoot()
                .getProjects());
        fileDialog.setAllowMultiple(false);
        fileDialog.setBlockOnOpen(true);
        // sets validator that tests whether a file is selected.
        fileDialog.setValidator(new FileSelectionStatusValidator());
        fileDialog.setTitle(Messages.CompareDcaseFileSelectionAdapter_1);

        fileDialog.open();

        Object[] results = fileDialog.getResult();

        if (results == null) {
            return null;
        }
        return (IFile) results[0];
    }
}
