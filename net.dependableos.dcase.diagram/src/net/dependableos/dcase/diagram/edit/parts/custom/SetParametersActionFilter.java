/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IActionFilter;

/**
 * An action filter to test whether the target is appropriate to configure or set parameters.
 */
public class SetParametersActionFilter implements IActionFilter {
    /**
     * the attribute name.
     */
    public static final String NAME = "ParameterVals"; //$NON-NLS-1$
    /**
     * the attribute value to set parameters.
     */
    public static final String VALUE = "PARAM FORMAT"; //$NON-NLS-1$
    /**
     * the attribute value to configure parameters.
     */
    public static final String CONFIG_VALUE = "PARAM CONFIG"; //$NON-NLS-1$
    /**
     * the instance.
     */
    private static SetParametersActionFilter singleton;

    /**
     * Returns the instance.
     * 
     * @return  the instance.
     */
    public static SetParametersActionFilter getSingleton() {
        if (singleton == null) {
            singleton = new SetParametersActionFilter();
        }
        return singleton;
    }

    /**
     * Returns whether the target is appropriate to configure or set parameters.
     * 
     * @see IActionFilter#testAttribute(Object, String, String)
     * @param target the target.
     * @param name the attribute name.
     * @param value the attribute value.
     * @return true if and only if the target is appropriate to configure or set parameters; false otherwise.
     */
    public boolean testAttribute(Object target, String name, String value) {
        boolean result = false;
        if (NAME.equals(name) && VALUE.equals(value)) {
            GraphicalEditPart graphicalEditPart = (GraphicalEditPart) target;
            View view = (View) graphicalEditPart.getModel();
            BasicNode node = (BasicNode) view.getElement();
            String userdef007 = (String) node.getParameterVals();
            if (userdef007 != null && userdef007.trim().length() != 0
                    && ParameterItem.isValidParameter(userdef007)) {
                result = true;
            }
        }
        if (NAME.equals(name) && CONFIG_VALUE.equals(value)) {
            result = true;
        }
        return result;
    }

}
