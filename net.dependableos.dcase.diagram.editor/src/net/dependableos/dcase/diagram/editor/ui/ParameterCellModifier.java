/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import org.eclipse.jface.viewers.ICellModifier;

/**
 * This class is used to access to a parameter from a parameter value editor.
 */
public class ParameterCellModifier implements ICellModifier {

    /**
     * Creates the instance and initializes it.
     */
    public ParameterCellModifier() {
        super();
    }

    /**
     * Checks whether the given property of the given element can be modified. 
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     * @param element the element.
     * @param property the property.
     * @return true if the property can be modified, and false if it is not modifiable.
     */
    public boolean canModify(Object element, String property) {
        return false;
    }

    /**
     * Returns the value for the given property of the given element. Returns
     * null if the element does not have the given property.
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
     *      java.lang.String)
     * 
     * @param element the element.
     * @param property the property.
     * @return Object the property value.
     */
    public Object getValue(Object element, String property) {
        return null;
    }

    /**
     * Modifies the value for the given property of the given element.
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     * 
     * @param element the element.
     * @param property the property.
     * @param value the property value.
     */
    public void modify(Object element, String property, Object value) {

    }
}
