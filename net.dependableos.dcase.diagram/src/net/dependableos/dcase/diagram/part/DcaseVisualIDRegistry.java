/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.diagram.edit.parts.*;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class DcaseVisualIDRegistry {

    /**
     * @generated
     */
    private static final String DEBUG_KEY = "net.dependableos.dcase.diagram/debug/visualID"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if (ArgumentEditPart.MODEL_ID.equals(view.getType())) {
                return ArgumentEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        return net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry
                .getVisualID(view.getType());
    }

    /**
     * @generated
     */
    public static String getModelID(View view) {
        View diagram = view.getDiagram();
        while (view != diagram) {
            EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
            if (annotation != null) {
                return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
            }
            view = (View) view.eContainer();
        }
        return diagram != null ? diagram.getType() : null;
    }

    /**
     * @generated
     */
    public static int getVisualID(String type) {
        try {
            return Integer.parseInt(type);
        } catch (NumberFormatException e) {
            if (Boolean.TRUE.toString().equalsIgnoreCase(
                    Platform.getDebugOption(DEBUG_KEY))) {
                DcaseDiagramEditorPlugin.getInstance().logError(
                        "Unable to parse view type as a visualID number: "
                                + type);
            }
        }
        return -1;
    }

    /**
     * @generated
     */
    public static String getType(int visualID) {
        return String.valueOf(visualID);
    }

    /**
     * @generated
     */
    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (DcasePackage.eINSTANCE.getArgument().isSuperTypeOf(
                domainElement.eClass())
                && isDiagram((Argument) domainElement)) {
            return ArgumentEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static int getNodeVisualID(View containerView, EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        String containerModelID = net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry
                .getModelID(containerView);
        if (!ArgumentEditPart.MODEL_ID.equals(containerModelID)) {
            return -1;
        }
        int containerVisualID;
        if (ArgumentEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = ArgumentEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        switch (containerVisualID) {
            case ArgumentEditPart.VISUAL_ID:
                if (DcasePackage.eINSTANCE.getGoal().isSuperTypeOf(
                        domainElement.eClass())) {
                    return GoalEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getStrategy().isSuperTypeOf(
                        domainElement.eClass())) {
                    return StrategyEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getEvidence().isSuperTypeOf(
                        domainElement.eClass())) {
                    return EvidenceEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getMonitor().isSuperTypeOf(
                        domainElement.eClass())) {
                    return MonitorEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUndeveloped().isSuperTypeOf(
                        domainElement.eClass())) {
                    return UndevelopedEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getContext().isSuperTypeOf(
                        domainElement.eClass())) {
                    return ContextEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getJustification().isSuperTypeOf(
                        domainElement.eClass())) {
                    return JustificationEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getSystem().isSuperTypeOf(
                        domainElement.eClass())) {
                    return SystemEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getPolicy().isSuperTypeOf(
                        domainElement.eClass())) {
                    return PolicyEditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef001().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef001EditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef002().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef002EditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef003().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef003EditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef004().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef004EditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef005().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef005EditPart.VISUAL_ID;
                }
                if (DcasePackage.eINSTANCE.getUserdef006().isSuperTypeOf(
                        domainElement.eClass())) {
                    return Userdef006EditPart.VISUAL_ID;
                }
                break;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        String containerModelID = net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry
                .getModelID(containerView);
        if (!ArgumentEditPart.MODEL_ID.equals(containerModelID)) {
            return false;
        }
        int containerVisualID;
        if (ArgumentEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = ArgumentEditPart.VISUAL_ID;
            } else {
                return false;
            }
        }
        switch (containerVisualID) {
            case GoalEditPart.VISUAL_ID:
                if (GoalNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (GoalUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (GoalDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (GoalUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (GoalAttachmentEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (GoalResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case StrategyEditPart.VISUAL_ID:
                if (StrategyNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (StrategyUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (StrategyDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (StrategyUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (StrategyResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case EvidenceEditPart.VISUAL_ID:
                if (EvidenceNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (EvidenceUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (EvidenceDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (EvidenceUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (EvidenceResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case MonitorEditPart.VISUAL_ID:
                if (MonitorNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (MonitorUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (MonitorDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (MonitorUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (MonitorResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case UndevelopedEditPart.VISUAL_ID:
                if (UndevelopedNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (UndevelopedUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (UndevelopedDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (UndevelopedUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (UndevelopedResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case ContextEditPart.VISUAL_ID:
                if (ContextNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (ContextUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (ContextDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (ContextUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (ContextResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case JustificationEditPart.VISUAL_ID:
                if (JustificationNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (JustificationUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (JustificationDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (JustificationUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (JustificationResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case SystemEditPart.VISUAL_ID:
                if (SystemNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (SystemUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (SystemDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (SystemUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (SystemResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case PolicyEditPart.VISUAL_ID:
                if (PolicyNameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (PolicyUserdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (PolicyDescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (PolicyUserdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (PolicyResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef001EditPart.VISUAL_ID:
                if (Userdef001NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001AttachmentEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef002EditPart.VISUAL_ID:
                if (Userdef002NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef002Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef002DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef002Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef002ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef003EditPart.VISUAL_ID:
                if (Userdef003NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef003Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef003DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef003Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef003ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef004EditPart.VISUAL_ID:
                if (Userdef004NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef004Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef004DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef004Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef004ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef005EditPart.VISUAL_ID:
                if (Userdef005NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005AttachmentEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case Userdef006EditPart.VISUAL_ID:
                if (Userdef006NameEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef006Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef006DescEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef006Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef006ResponsibilityEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case ArgumentEditPart.VISUAL_ID:
                if (GoalEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (StrategyEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (EvidenceEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (MonitorEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (UndevelopedEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (ContextEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (JustificationEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (SystemEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (PolicyEditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef001EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef002EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef003EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef004EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef005EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                if (Userdef006EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case DcaseLink001EditPart.VISUAL_ID:
                if (DcaseLink001Userdef001DescUserdef00EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case DcaseLink002EditPart.VISUAL_ID:
                if (DcaseLink002Userdef001DescUserdef00EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case DcaseLink003EditPart.VISUAL_ID:
                if (DcaseLink003Userdef001DescUserdef00EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
            case DcaseLink004EditPart.VISUAL_ID:
                if (DcaseLink004Userdef001DescUserdef00EditPart.VISUAL_ID == nodeVisualID) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * @generated
     */
    public static int getLinkWithClassVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (DcasePackage.eINSTANCE.getDcaseLink001().isSuperTypeOf(
                domainElement.eClass())) {
            return DcaseLink001EditPart.VISUAL_ID;
        }
        if (DcasePackage.eINSTANCE.getDcaseLink002().isSuperTypeOf(
                domainElement.eClass())) {
            return DcaseLink002EditPart.VISUAL_ID;
        }
        if (DcasePackage.eINSTANCE.getDcaseLink003().isSuperTypeOf(
                domainElement.eClass())) {
            return DcaseLink003EditPart.VISUAL_ID;
        }
        if (DcasePackage.eINSTANCE.getDcaseLink004().isSuperTypeOf(
                domainElement.eClass())) {
            return DcaseLink004EditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * User can change implementation of this method to handle some specific
     * situations not covered by default logic.
     * 
     * @generated
     */
    private static boolean isDiagram(Argument element) {
        return true;
    }

}
