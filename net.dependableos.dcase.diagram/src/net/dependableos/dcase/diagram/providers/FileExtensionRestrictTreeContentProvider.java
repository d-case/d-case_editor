/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.providers;

import java.util.ArrayList;
import java.util.List;


import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A tree content provider restrict files by extension.
 * 
 */
public class FileExtensionRestrictTreeContentProvider implements
        ITreeContentProvider {

    /**
     * the file extension.
     */
    private String fileExtension;
    
    /**
     *  Allocates a FileExtensionRestrictTreeContentProvider object
     *   and initializes it to represent the specified file extension.
     * 
     * @param fileExtension the file extension.
     */
    public FileExtensionRestrictTreeContentProvider(String fileExtension) {
        super();
        this.fileExtension = fileExtension;
    }

    /**
     * Returns the child elements of the given parent element.
     * 
     * @param parentElement the parent element
     * @return  an array of child elements
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        
        if (parentElement instanceof IProject[]) {
            IProject[] projects = (IProject[]) parentElement;
            return projects;
        }
        
        if (parentElement instanceof IContainer) {
            try {
                IContainer container = (IContainer) parentElement;
                if (container.isAccessible()) {
                    List<IResource> resourceList = new ArrayList<IResource>();
                    for (IResource resource : container.members()) {
                        // tests the file extension.
                        if (fileExtension != null && resource instanceof IFile) {
                            if (fileExtension.equals(((IFile) resource)
                                    .getFileExtension())) {
                                resourceList.add(resource);
                            }
                        } else {
                            resourceList.add(resource);
                        }
                    }
                    return resourceList.toArray();
                }
            } catch (CoreException ce) {
                throw new DcaseSystemException(
                        Messages.FileExtensionRestrictTreeContentProvider_0,
                        ce, MessageTypeImpl.UNDEFINED);
            }
        }
        
        // the default.
        return new Object[0];
    }

    /**
     * Returns the parent for the given element.
     * 
     * @param element the element
     * @return the parent element, or null if it
     *   has none or if the parent cannot be computed
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        return null;
    }

    /**
     * Returns whether the given element has children.
     * 
     * @param element  the element
     * @return true if and only if the element has children; false otherwise.
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /**
     * Returns the child elements of the given parent element.
     * 
     * @param inputElement the parent element
     * @return  an array of child elements
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    /**
     * Disposes this object.
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }

    /**
     *  Notifies this content provider that the given viewer's input
     * has been switched to a different element.
     * 
     * @param viewer the viewer
     * @param oldInput the old input element, or null if the viewer
     *   did not previously have an input
     * @param newInput the new input element, or null if the viewer
     *   does not have an input
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(
     *     org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}
