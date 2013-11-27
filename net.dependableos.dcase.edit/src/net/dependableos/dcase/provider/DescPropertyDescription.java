/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.provider;


import net.dependableos.dcase.BasicNode;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

/**
 * An item property descriptor for the description attribute of a node.
 */
public class DescPropertyDescription extends ItemPropertyDescriptor {

    /**
     * Creates an instance and initializes it.
     * 
     * @param adapterFactory the adapter factory.
     * @param resourceLocator the resource locator.
     * @param displayName the display name.
     * @param description the description.
     * @param feature the feature.
     * @param isSettable whether this descriptor's property for the object supports set.
     * @param multiLine whether the value of this property consists of multi-line text.
     * @param sortChoices whether the choices for this property should be sorted for display.
     * @param staticImage the image that will be used with the value no matter what type of object it is.
     * @param category the group of properties into which this one should be placed.
     * @param filterFlags the flags used as filters in the property sheet.
     */
    public DescPropertyDescription(AdapterFactory adapterFactory,
            ResourceLocator resourceLocator, String displayName,
            String description, EStructuralFeature feature, boolean isSettable,
            boolean multiLine, boolean sortChoices, Object staticImage,
            String category, String[] filterFlags) {
        super(adapterFactory, resourceLocator, displayName, description,
                feature, isSettable, multiLine, sortChoices, staticImage,
                category, filterFlags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSetProperty(Object object) {
        boolean canSet = super.canSetProperty(object);
        // tests the property can be set.
        if (canSet) {
            // tests whether the desc format string is set.
            String descScript = ((BasicNode) object).getParameterizedDesc();
            if (descScript != null && descScript.trim().length() > 0) {
                canSet = false;
            }
        }
        return canSet;
    }
}
