/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.policies;


import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class DcaseLink004ItemSemanticEditPolicy extends
        DcaseBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public DcaseLink004ItemSemanticEditPolicy() {
        super(DcaseElementTypes.DcaseLink004_3004);
    }

    /**
     * @generated
     */
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return getGEFWrapper(new DestroyElementCommand(req));
    }

}
