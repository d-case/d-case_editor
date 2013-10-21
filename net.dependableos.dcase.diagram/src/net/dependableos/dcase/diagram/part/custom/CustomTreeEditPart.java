/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.part.custom;


import net.dependableos.dcase.BasicNode;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TreeEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * A custom tree edit part.
 */
public class CustomTreeEditPart extends TreeEditPart {

    /**
     * the label text to display if the Desc attribute is empty.
     */
    private static final String UNDEFINED_STRING = "[Undefined]";

    /**
     *  the element parser.
     */
    private IParser parser;

    /**
     *  the element.
     */
    private IAdaptable referenceAdapter;

    /**
     * Allocates a CustomTreeEditPart object.
     * 
     * @param model the model.
     */
    public CustomTreeEditPart(Object model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getText() {
        if (getParser() != null) {
            return getParser().getPrintString(referenceAdapter,
                    ParserOptions.NONE.intValue());
        }
        EObject eObject = ((View) getModel()).getElement();

        String desc = null;
        if (eObject != null) {
            if (eObject instanceof BasicNode) {
                desc = ((BasicNode) eObject).getDesc();
            }
        }

        if (desc == null || desc.equals("")) {
            return UNDEFINED_STRING;
        }
        return desc;
    }

    /**
     * Method getParser.
     * 
     * @return IParser
     */
    private IParser getParser() {
        if (parser == null) {
            if (referenceAdapter != null
                    && referenceAdapter.getAdapter(EObject.class) != null) {
                parser = ParserService.getInstance()
                        .getParser(referenceAdapter);
            }
        }
        return parser;
    }

}
