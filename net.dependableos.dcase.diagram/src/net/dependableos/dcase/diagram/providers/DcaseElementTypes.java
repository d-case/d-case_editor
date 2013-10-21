/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink001EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink002EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink003EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink004EditPart;
import net.dependableos.dcase.diagram.edit.parts.EvidenceEditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalEditPart;
import net.dependableos.dcase.diagram.edit.parts.JustificationEditPart;
import net.dependableos.dcase.diagram.edit.parts.MonitorEditPart;
import net.dependableos.dcase.diagram.edit.parts.PolicyEditPart;
import net.dependableos.dcase.diagram.edit.parts.StrategyEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.edit.parts.UndevelopedEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef001EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef002EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef003EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef004EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef005EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef006EditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class DcaseElementTypes extends ElementInitializers {

    /**
     * @generated
     */
    private DcaseElementTypes() {
    }

    /**
     * @generated
     */
    private static Map elements;

    /**
     * @generated
     */
    private static ImageRegistry imageRegistry;

    /**
     * @generated
     */
    private static Set KNOWN_ELEMENT_TYPES;

    /**
     * @generated
     */
    public static final IElementType Argument_79 = getElementType("net.dependableos.dcase.diagram.Argument_79"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Goal_1001 = getElementType("net.dependableos.dcase.diagram.Goal_1001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Strategy_1002 = getElementType("net.dependableos.dcase.diagram.Strategy_1002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Evidence_1003 = getElementType("net.dependableos.dcase.diagram.Evidence_1003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Monitor_1004 = getElementType("net.dependableos.dcase.diagram.Monitor_1004"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Undeveloped_1005 = getElementType("net.dependableos.dcase.diagram.Undeveloped_1005"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Context_1006 = getElementType("net.dependableos.dcase.diagram.Context_1006"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Justification_1007 = getElementType("net.dependableos.dcase.diagram.Justification_1007"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType System_1008 = getElementType("net.dependableos.dcase.diagram.System_1008"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Policy_1009 = getElementType("net.dependableos.dcase.diagram.Policy_1009"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef001_1010 = getElementType("net.dependableos.dcase.diagram.Userdef001_1010"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef002_1011 = getElementType("net.dependableos.dcase.diagram.Userdef002_1011"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef003_1012 = getElementType("net.dependableos.dcase.diagram.Userdef003_1012"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef004_1013 = getElementType("net.dependableos.dcase.diagram.Userdef004_1013"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef005_1014 = getElementType("net.dependableos.dcase.diagram.Userdef005_1014"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Userdef006_1015 = getElementType("net.dependableos.dcase.diagram.Userdef006_1015"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType DcaseLink001_3001 = getElementType("net.dependableos.dcase.diagram.DcaseLink001_3001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType DcaseLink002_3002 = getElementType("net.dependableos.dcase.diagram.DcaseLink002_3002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType DcaseLink003_3003 = getElementType("net.dependableos.dcase.diagram.DcaseLink003_3003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType DcaseLink004_3004 = getElementType("net.dependableos.dcase.diagram.DcaseLink004_3004"); //$NON-NLS-1$

    /**
     * @generated
     */
    private static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * @generated
     */
    private static String getImageRegistryKey(ENamedElement element) {
        return element.getName();
    }

    /**
     * @generated
     */
    private static ImageDescriptor getProvidedImageDescriptor(
            ENamedElement element) {
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = ((EStructuralFeature) element);
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            } else if (eType instanceof EClass
                    && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return DcaseDiagramEditorPlugin.getInstance()
                        .getItemImageDescriptor(
                                eClass.getEPackage().getEFactoryInstance()
                                        .create(eClass));
            }
        }
        // TODO : support structural features
        return null;
    }

    /**
     * @generated
     */
    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        String key = getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    /**
     * @generated
     */
    public static Image getImage(ENamedElement element) {
        String key = getImageRegistryKey(element);
        Image image = getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
            image = getImageRegistry().get(key);
        }
        return image;
    }

    /**
     * @generated
     */
    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImageDescriptor(element);
    }

    /**
     * @generated
     */
    public static Image getImage(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImage(element);
    }

    /**
     * Returns 'type' of the ecore object associated with the hint.
     * 
     * @generated
     */
    public static ENamedElement getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (elements == null) {
            elements = new IdentityHashMap();

            elements.put(Argument_79, DcasePackage.eINSTANCE.getArgument());

            elements.put(Goal_1001, DcasePackage.eINSTANCE.getGoal());

            elements.put(Strategy_1002, DcasePackage.eINSTANCE.getStrategy());

            elements.put(Evidence_1003, DcasePackage.eINSTANCE.getEvidence());

            elements.put(Monitor_1004, DcasePackage.eINSTANCE.getMonitor());

            elements.put(Undeveloped_1005, DcasePackage.eINSTANCE
                    .getUndeveloped());

            elements.put(Context_1006, DcasePackage.eINSTANCE.getContext());

            elements.put(Justification_1007, DcasePackage.eINSTANCE
                    .getJustification());

            elements.put(System_1008, DcasePackage.eINSTANCE.getSystem());

            elements.put(Policy_1009, DcasePackage.eINSTANCE.getPolicy());

            elements.put(Userdef001_1010, DcasePackage.eINSTANCE
                    .getUserdef001());

            elements.put(Userdef002_1011, DcasePackage.eINSTANCE
                    .getUserdef002());

            elements.put(Userdef003_1012, DcasePackage.eINSTANCE
                    .getUserdef003());

            elements.put(Userdef004_1013, DcasePackage.eINSTANCE
                    .getUserdef004());

            elements.put(Userdef005_1014, DcasePackage.eINSTANCE
                    .getUserdef005());

            elements.put(Userdef006_1015, DcasePackage.eINSTANCE
                    .getUserdef006());

            elements.put(DcaseLink001_3001, DcasePackage.eINSTANCE
                    .getDcaseLink001());

            elements.put(DcaseLink002_3002, DcasePackage.eINSTANCE
                    .getDcaseLink002());

            elements.put(DcaseLink003_3003, DcasePackage.eINSTANCE
                    .getDcaseLink003());

            elements.put(DcaseLink004_3004, DcasePackage.eINSTANCE
                    .getDcaseLink004());
        }
        return (ENamedElement) elements.get(type);
    }

    /**
     * @generated
     */
    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    /**
     * @generated
     */
    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet();
            KNOWN_ELEMENT_TYPES.add(Argument_79);
            KNOWN_ELEMENT_TYPES.add(Goal_1001);
            KNOWN_ELEMENT_TYPES.add(Strategy_1002);
            KNOWN_ELEMENT_TYPES.add(Evidence_1003);
            KNOWN_ELEMENT_TYPES.add(Monitor_1004);
            KNOWN_ELEMENT_TYPES.add(Undeveloped_1005);
            KNOWN_ELEMENT_TYPES.add(Context_1006);
            KNOWN_ELEMENT_TYPES.add(Justification_1007);
            KNOWN_ELEMENT_TYPES.add(System_1008);
            KNOWN_ELEMENT_TYPES.add(Policy_1009);
            KNOWN_ELEMENT_TYPES.add(Userdef001_1010);
            KNOWN_ELEMENT_TYPES.add(Userdef002_1011);
            KNOWN_ELEMENT_TYPES.add(Userdef003_1012);
            KNOWN_ELEMENT_TYPES.add(Userdef004_1013);
            KNOWN_ELEMENT_TYPES.add(Userdef005_1014);
            KNOWN_ELEMENT_TYPES.add(Userdef006_1015);
            KNOWN_ELEMENT_TYPES.add(DcaseLink001_3001);
            KNOWN_ELEMENT_TYPES.add(DcaseLink002_3002);
            KNOWN_ELEMENT_TYPES.add(DcaseLink003_3003);
            KNOWN_ELEMENT_TYPES.add(DcaseLink004_3004);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

    /**
     * @generated
     */
    public static IElementType getElementType(int visualID) {
        switch (visualID) {
            case ArgumentEditPart.VISUAL_ID:
                return Argument_79;
            case GoalEditPart.VISUAL_ID:
                return Goal_1001;
            case StrategyEditPart.VISUAL_ID:
                return Strategy_1002;
            case EvidenceEditPart.VISUAL_ID:
                return Evidence_1003;
            case MonitorEditPart.VISUAL_ID:
                return Monitor_1004;
            case UndevelopedEditPart.VISUAL_ID:
                return Undeveloped_1005;
            case ContextEditPart.VISUAL_ID:
                return Context_1006;
            case JustificationEditPart.VISUAL_ID:
                return Justification_1007;
            case SystemEditPart.VISUAL_ID:
                return System_1008;
            case PolicyEditPart.VISUAL_ID:
                return Policy_1009;
            case Userdef001EditPart.VISUAL_ID:
                return Userdef001_1010;
            case Userdef002EditPart.VISUAL_ID:
                return Userdef002_1011;
            case Userdef003EditPart.VISUAL_ID:
                return Userdef003_1012;
            case Userdef004EditPart.VISUAL_ID:
                return Userdef004_1013;
            case Userdef005EditPart.VISUAL_ID:
                return Userdef005_1014;
            case Userdef006EditPart.VISUAL_ID:
                return Userdef006_1015;
            case DcaseLink001EditPart.VISUAL_ID:
                return DcaseLink001_3001;
            case DcaseLink002EditPart.VISUAL_ID:
                return DcaseLink002_3002;
            case DcaseLink003EditPart.VISUAL_ID:
                return DcaseLink003_3003;
            case DcaseLink004EditPart.VISUAL_ID:
                return DcaseLink004_3004;
        }
        return null;
    }

}
