/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.policies;


import net.dependableos.dcase.diagram.edit.commands.ContextCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.EvidenceCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.GoalCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.JustificationCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.MonitorCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.PolicyCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.StrategyCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.SystemCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.UndevelopedCreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef001CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef002CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef003CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef004CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef005CreateCommand;
import net.dependableos.dcase.diagram.edit.commands.Userdef006CreateCommand;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class ArgumentItemSemanticEditPolicy extends
        DcaseBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public ArgumentItemSemanticEditPolicy() {
        super(DcaseElementTypes.Argument_79);
    }

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (DcaseElementTypes.Goal_1001 == req.getElementType()) {
            return getGEFWrapper(new GoalCreateCommand(req));
        }
        if (DcaseElementTypes.Strategy_1002 == req.getElementType()) {
            return getGEFWrapper(new StrategyCreateCommand(req));
        }
        if (DcaseElementTypes.Evidence_1003 == req.getElementType()) {
            return getGEFWrapper(new EvidenceCreateCommand(req));
        }
        if (DcaseElementTypes.Monitor_1004 == req.getElementType()) {
            return getGEFWrapper(new MonitorCreateCommand(req));
        }
        if (DcaseElementTypes.Undeveloped_1005 == req.getElementType()) {
            return getGEFWrapper(new UndevelopedCreateCommand(req));
        }
        if (DcaseElementTypes.Context_1006 == req.getElementType()) {
            return getGEFWrapper(new ContextCreateCommand(req));
        }
        if (DcaseElementTypes.Justification_1007 == req.getElementType()) {
            return getGEFWrapper(new JustificationCreateCommand(req));
        }
        if (DcaseElementTypes.System_1008 == req.getElementType()) {
            return getGEFWrapper(new SystemCreateCommand(req));
        }
        if (DcaseElementTypes.Policy_1009 == req.getElementType()) {
            return getGEFWrapper(new PolicyCreateCommand(req));
        }
        if (DcaseElementTypes.Userdef001_1010 == req.getElementType()) {
            return getGEFWrapper(new Userdef001CreateCommand(req));
        }
        if (DcaseElementTypes.Userdef002_1011 == req.getElementType()) {
            return getGEFWrapper(new Userdef002CreateCommand(req));
        }
        if (DcaseElementTypes.Userdef003_1012 == req.getElementType()) {
            return getGEFWrapper(new Userdef003CreateCommand(req));
        }
        if (DcaseElementTypes.Userdef004_1013 == req.getElementType()) {
            return getGEFWrapper(new Userdef004CreateCommand(req));
        }
        if (DcaseElementTypes.Userdef005_1014 == req.getElementType()) {
            return getGEFWrapper(new Userdef005CreateCommand(req));
        }
        if (DcaseElementTypes.Userdef006_1015 == req.getElementType()) {
            return getGEFWrapper(new Userdef006CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @generated
     */
    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
                .getEditingDomain();
        return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
    }

    /**
     * @generated
     */
    private static class DuplicateAnythingCommand extends
            DuplicateEObjectsCommand {

        /**
         * @generated
         */
        public DuplicateAnythingCommand(
                TransactionalEditingDomain editingDomain,
                DuplicateElementsRequest req) {
            super(editingDomain, req.getLabel(), req
                    .getElementsToBeDuplicated(), req
                    .getAllDuplicatedElementsMap());
        }

    }

}
