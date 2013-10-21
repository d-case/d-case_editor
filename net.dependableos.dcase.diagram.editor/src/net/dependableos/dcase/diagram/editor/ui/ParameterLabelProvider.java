/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;


import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A label provider which maps a parameter item to a check box and a string to display on a table viewer.
 */
public class ParameterLabelProvider implements ITableLabelProvider {
    /**
     * the image registry.
     */
    private static ImageRegistry imageRegistry = new ImageRegistry();
    /**
     * the image that represents "used" status.
     */
    public static final String CHECKED_IMAGE = "checked"; //$NON-NLS-1$
    /**
     * the image that represents "not used" status.
     */
    public static final String UNCHECKED_IMAGE = "unchecked"; //$NON-NLS-1$

    /**
     * Creates image objects and stores them to the registry.
     */
    static {
        String iconPath = "icons/"; //$NON-NLS-1$
        DcaseDiagramEditorPlugin.getInstance();
        imageRegistry.put(CHECKED_IMAGE, DcaseDiagramEditorPlugin.findImageDescriptor(
                iconPath.concat(CHECKED_IMAGE).concat(".gif")).createImage()); //$NON-NLS-1$
        imageRegistry.put(UNCHECKED_IMAGE, DcaseDiagramEditorPlugin.findImageDescriptor(
                iconPath.concat(UNCHECKED_IMAGE).concat(".gif")).createImage()); //$NON-NLS-1$
    }

    /**
     * Returns the label image for the given column of the given element.
     * 
     * @param element the object representing the entire row, or null indicating
     *            that no input object is set in the viewer.
     * @param columnIndex the zero-based index of the column in which the label
     *            appears.
     * @return Image the image or null if there is no image for the given object
     *         at columnIndex
     */
    public Image getColumnImage(Object element, int columnIndex) {
        Image image = null;
        if (columnIndex == 0) {
            String key = UNCHECKED_IMAGE;
            if (((ParameterItem) element).isSelected()) {
                key = CHECKED_IMAGE;
            }
            image = imageRegistry.get(key);
        }
        return image;
    }

    /**
     * Returns the label text for the given column of the given element.
     * 
     * @param element the object representing the entire row, or null indicating
     *            that no input object is set in the viewer.
     * @param columnIndex the zero-based index of the column in which the label
     *            appears.
     * @return the string or null if there is no text for the given
     *         object at columnIndex.
     */
    public String getColumnText(Object element, int columnIndex) {
        String result = ""; //$NON-NLS-1$
        if (columnIndex == 1) {
            result = ((ParameterItem) element).getParameterId();

        }
        return result;
    }

    /**
     * Adds a listener.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     * @param listener a label provider listener.
     */
    public void addListener(ILabelProviderListener listener) {
    }

    /**
     * Disposes of this label provider.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
    }

    /**
     * Returns whether the label would be affected by a change to the given property of the given element.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
     *      java.lang.String)
     * @param element the element.
     * @param property the property.
     * @return boolean true if and only if the label would be affected;false otherwise.
     */
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    /**
     * Removes a listener.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(
     * org.eclipse.jface.viewers.ILabelProviderListener)
     * @param listener a label provider listener.
     */
    public void removeListener(ILabelProviderListener listener) {
    }

}
