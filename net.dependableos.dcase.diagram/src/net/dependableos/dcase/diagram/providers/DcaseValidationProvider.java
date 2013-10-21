/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.providers;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseValidatorException;
import net.dependableos.dcase.diagram.common.validator.DcaseValidator;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.part.ValidateAction;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcaseValidationProvider {

    /**
     * @generated
     */
    private static boolean constraintsActive = false;

    /**
     * @generated
     */
    public static boolean shouldConstraintsBePrivate() {
        return false;
    }

    /**
     * @generated
     */
    public static void runWithConstraints(
            TransactionalEditingDomain editingDomain, Runnable operation) {
        final Runnable op = operation;
        Runnable task = new Runnable() {
            public void run() {
                try {
                    constraintsActive = true;
                    op.run();
                } finally {
                    constraintsActive = false;
                }
            }
        };
        if (editingDomain != null) {
            try {
                editingDomain.runExclusive(task);
            } catch (Exception e) {
                DcaseDiagramEditorPlugin.getInstance().logError(
                        "Validation failed", e); //$NON-NLS-1$
            }
        } else {
            task.run();
        }
    }

    /**
     * @generated
     */
    static boolean isInDefaultEditorContext(Object object) {
        if (shouldConstraintsBePrivate() && !constraintsActive) {
            return false;
        }
        if (object instanceof View) {
            return constraintsActive
                    && ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                            .getModelID((View) object));
        }
        return true;
    }

    /**
     * @generated
     */
    public static class DefaultCtx implements IClientSelector {

        /**
         * @generated
         */
        public boolean selects(Object object) {
            return isInDefaultEditorContext(object);
        }
    }

    /**
     * @generated
     */
    public static class Adapter1 extends AbstractModelConstraint {

        // AUTO_GENERATED:END

        /**
         * Validates connections of a node.
         * 
         * @param ctx the validation context that provides access to the current
         *         constraint evaluation environment.
         * @return the status of validation of the target object.
         * @generated NOT
         */
        @Override
        public IStatus validate(IValidationContext ctx) {
            BasicNode context = (BasicNode) ctx.getTarget();
            if (context instanceof Argument) {
                return ctx.createSuccessStatus();
            }

            DcaseValidator dcaseValidator = ValidateAction.VALIDATOR_COLLECTION
                    .get();

            try {
                dcaseValidator.validateLink(context);
            } catch (DcaseValidatorException dve) {
                return ctx.createFailureStatus(dve.getMessage());
            }

            return ctx.createSuccessStatus();
        }

        // AUTO_GENERATED:START
    }

    /**
     * @generated
     */
    public static class Adapter2 extends AbstractModelConstraint {

        // AUTO_GENERATED:END

        /**
         * Validates connections(cyclic) of a node.
         * 
         * @param ctx the validation context that provides access to the current
         *         constraint evaluation environment.
         * @return the status of validation of the target object.
         * @generated NOT
         */
        public IStatus validate(IValidationContext ctx) {

            DcaseValidator dcaseValidator = ValidateAction.VALIDATOR_COLLECTION
                    .get();

            try {
                dcaseValidator.validateCyclicStateAll();
            } catch (DcaseValidatorException dve) {
                return ctx.createFailureStatus(dve.getMessage());
            }

            return ctx.createSuccessStatus();
        }

        // AUTO_GENERATED:START
    }

    /**
     * @generated
     */
    public static class Adapter3 extends AbstractModelConstraint {

        // AUTO_GENERATED:END

        /**
         * Validates properties of a node.
         * 
         * @param ctx the validation context that provides access to the current
         *         constraint evaluation environment.
         * @return the status of validation of the target object.
         * @generated NOT
         */
        public IStatus validate(IValidationContext ctx) {
            BasicNode context = (BasicNode) ctx.getTarget();

            DcaseValidator dcaseValidator = ValidateAction.VALIDATOR_COLLECTION
                    .get();

            try {
                dcaseValidator.validateNodeAttribute(context);
            } catch (DcaseValidatorException dve) {
                return ctx.createFailureStatus(dve.getMessage());
            }

            return ctx.createSuccessStatus();
        }

        // AUTO_GENERATED:START
    }

    /**
     * @generated
     */
    public static class Adapter4 extends AbstractModelConstraint {

        // AUTO_GENERATED:END

        /**
         * Validates properties of a link.
         * 
         * @param ctx the validation context that provides access to the current
         *         constraint evaluation environment.
         * @return the status of validation of the target object.
         * @generated NOT
         */
        public IStatus validate(IValidationContext ctx) {
            BasicLink context = (BasicLink) ctx.getTarget();

            DcaseValidator dcaseValidator = ValidateAction.VALIDATOR_COLLECTION
                    .get();

            try {
                dcaseValidator.validateLinkAttribute(context);
            } catch (DcaseValidatorException dve) {
                return ctx.createFailureStatus(dve.getMessage());
            }

            return ctx.createSuccessStatus();
        }

        // AUTO_GENERATED:START
    }

    /**
     * @generated
     */
    static String formatElement(EObject object) {
        return EMFCoreUtil.getQualifiedName(object, true);
    }

}
