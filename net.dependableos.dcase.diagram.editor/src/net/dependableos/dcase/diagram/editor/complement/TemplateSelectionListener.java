/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.parameter.ParameterUtil;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;

/**
 * This class provides methods that deal with the events that are generated
 *  when a template of the complement menu selected..
 */
public class TemplateSelectionListener extends
        AbstractComplementSelectionListener {

    /**
     * the selected complement item.
     */
    private ComplementItem complementItem;

    /**
     * Creates the listener and initializes it.
     * 
     * @param complementItem the selected complement item.
     */
    public TemplateSelectionListener(ComplementItem complementItem) {
        super(Menus.TemplateSelectionListener_0);
        this.complementItem = complementItem;
    }

    /**
     * Returns the selected complement item.
     * 
     * @return the selected complement item.
     */
    public ComplementItem getComplementItem() {
        return complementItem;
    }

    /**
     * Returns the model to add.
     * 
     * @return the model to add.
     */
    @Override
    protected Argument getModel() {
        // create URI of the model.

        URI modelURI = URI.createPlatformResourceURI(complementItem.getPath(),
                true);

        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                .createEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        EObject diagramRoot = null;
        try {
            // gets the model to add.
            Resource resource = resourceSet.getResource(modelURI, true);
            diagramRoot = (EObject) EcoreUtil.copy(resource.getContents()
                    .get(0));
        } catch (WrappedException we) {
            throw new DcaseSystemException(
                    Messages.TemplateModelAdditionAction_4, we,
                    MessageTypeImpl.TEMPLATE_INSERT_INTERNAL_ERROR);
        }
        if (diagramRoot instanceof Argument) {
            if (ParameterUtil.processParameter((Argument) diagramRoot)) {
                return (Argument) diagramRoot;
            }
        }
        return null;
    }

}
