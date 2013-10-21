/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.navigator;


import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class DcaseNavigatorSorter extends ViewerSorter {

    /**
     * @generated
     */
    private static final int GROUP_CATEGORY = 3006;

    /**
     * @generated
     */
    public int category(Object element) {
        if (element instanceof DcaseNavigatorItem) {
            DcaseNavigatorItem item = (DcaseNavigatorItem) element;
            return DcaseVisualIDRegistry.getVisualID(item.getView());
        }
        return GROUP_CATEGORY;
    }

}
