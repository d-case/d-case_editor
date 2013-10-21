/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.part.custom;


import net.dependableos.dcase.Argument;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TreeDiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * A custom tree diagram edit part.
 */
public class CustomTreeDiagramEditPart extends TreeDiagramEditPart {

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
     * Allocates a CustomTreeDiagramEditPart object.
     * 
     * @param model the model.
     */
    public CustomTreeDiagramEditPart(Object model) {
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
            if (eObject instanceof Argument) {
                desc = ((Argument) eObject).getDesc();
            }
        }

        if (desc == null || desc.equals("")) {
            return UNDEFINED_STRING;
        }
        return desc;
    }

    /**
     * Returns the parser.
     * 
     * @return the parser.
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
