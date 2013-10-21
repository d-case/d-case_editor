/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.template;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.TEMPLATE_MODEL_FILE_EXTENSION;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.TEMPLATE_RESOURCE_PROJECT_NAME;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.command.TemplateModelAdditionAction;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.editor.provider.TemplateViewTreeContentProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

/**
 * A view part to select a template to add to the D-Case diagram.
 */
public class TemplateViewPart extends ViewPart {

    /**
     * the file extension of a model file.
     */
    private static final String CONST_TEMPLATE_MODEL_FILE_EXTENSION = PropertyUtil
            .getSystemProperty(TEMPLATE_MODEL_FILE_EXTENSION);

    /**
     * the name of the template project.
     */
    private static final String CONST_TEMPLATE_RESOURCE_PROJECT_NAME = PropertyUtil
            .getSystemProperty(TEMPLATE_RESOURCE_PROJECT_NAME);

    /**
     * the tree view.
     */
    private TreeViewer viewer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(Composite parent) {
        

        // creates a tree viewer to display templates.
        viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.BORDER);
        viewer.setLabelProvider(new DecoratingLabelProvider(
                new WorkbenchLabelProvider(), PlatformUI.getWorkbench()
                        .getDecoratorManager().getLabelDecorator()));
        viewer.setContentProvider(new TemplateViewTreeContentProvider(
                CONST_TEMPLATE_MODEL_FILE_EXTENSION));

        // sets the template project as the input of the tree viewer.
        try {
            IProject templateProject = ResourcesPlugin.getWorkspace().getRoot()
                    .getProject(CONST_TEMPLATE_RESOURCE_PROJECT_NAME);

            // tests whether the project is accessible.
            if (!templateProject.isAccessible()) {
                throw new DcaseSystemException(Messages.TemplateViewPart_0,
                        null, MessageTypeImpl.TEMPLATE_INIT_FAILED);
            }

            viewer.setInput(templateProject);

        } catch (DcaseSystemException dse) {
            MessageWriter.writeMessageToErrorLog(dse);
            MessageWriter.showMessageBoxSeeErroLog(getSite().getShell());
            return;
        }

        // registers actions.
        registerAction();
    }

    /**
     * {@inheritDoc}
     */    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * Registers actions.
     */
    private void registerAction() {
        
        // creates an action to add the selected template to the D-Case diagram.
        IAction addAction = new TemplateModelAdditionAction(this);

        addAction.setText(Messages.TemplateViewPart_1);
        addAction.setToolTipText(Messages.TemplateViewPart_2);
        addAction.setImageDescriptor(PlatformUI.getWorkbench()
                .getSharedImages()
                .getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
        
        IActionBars bars = getViewSite().getActionBars();
        bars.getToolBarManager().add(addAction);
        bars.getMenuManager().add(addAction);
        
    }

    /**
     * Returns the selected template.
     * 
     * @return the selected template,or null if the selected file is invalid.
     */
    public IFile getSelectedTemplateFile() {

        ISelection sel = viewer.getSelection();
        if (sel instanceof TreeSelection) {
            TreeSelection treeSel = (TreeSelection) sel;
            if (treeSel.size() == 1
                    && treeSel.getFirstElement() instanceof IFile) {
                IFile file = (IFile) treeSel.getFirstElement();

                // test the file extension means a D-Case model.
                String fileExtension = file.getFileExtension();
                if (CONST_TEMPLATE_MODEL_FILE_EXTENSION.equals(fileExtension)) {
                    return file;
                }
            }
        }

        return null;
    }

}
