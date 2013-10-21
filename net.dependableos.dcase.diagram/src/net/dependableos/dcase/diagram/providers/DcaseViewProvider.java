/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.providers;

import java.util.ArrayList;

import net.dependableos.dcase.diagram.edit.parts.*;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

/**
 * @generated
 */
public class DcaseViewProvider extends AbstractProvider implements
        IViewProvider {

    /**
     * @generated
     */
    public final boolean provides(IOperation operation) {
        if (operation instanceof CreateViewForKindOperation) {
            return provides((CreateViewForKindOperation) operation);
        }
        assert operation instanceof CreateViewOperation;
        if (operation instanceof CreateDiagramViewOperation) {
            return provides((CreateDiagramViewOperation) operation);
        } else if (operation instanceof CreateEdgeViewOperation) {
            return provides((CreateEdgeViewOperation) operation);
        } else if (operation instanceof CreateNodeViewOperation) {
            return provides((CreateNodeViewOperation) operation);
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateViewForKindOperation op) {
        /*
         if (op.getViewKind() == Node.class)
         return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
         if (op.getViewKind() == Edge.class)
         return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
         */
        return true;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateDiagramViewOperation op) {
        return ArgumentEditPart.MODEL_ID.equals(op.getSemanticHint())
                && DcaseVisualIDRegistry
                        .getDiagramVisualID(getSemanticElement(op
                                .getSemanticAdapter())) != -1;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateNodeViewOperation op) {
        if (op.getContainerView() == null) {
            return false;
        }
        IElementType elementType = getSemanticElementType(op
                .getSemanticAdapter());
        EObject domainElement = getSemanticElement(op.getSemanticAdapter());
        int visualID;
        if (op.getSemanticHint() == null
                || "Dcase-CEP".equals(op.getSemanticHint())) {  //$NON-NLS-1$
            // Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
            // In this situation there should be NO elementType, visualID will be determined
            // by VisualIDRegistry.getNodeVisualID() for domainElement.
            if (elementType != null || domainElement == null) {
                return false;
            }
            visualID = DcaseVisualIDRegistry.getNodeVisualID(op
                    .getContainerView(), domainElement);
        } else {
            visualID = DcaseVisualIDRegistry.getVisualID(op.getSemanticHint());
            if (elementType != null) {
                if (!DcaseElementTypes.isKnownElementType(elementType)
                        || (!(elementType instanceof IHintedType))) {
                    return false; // foreign element type
                }
                String elementTypeHint = ((IHintedType) elementType)
                        .getSemanticHint();
                if (!op.getSemanticHint().equals(elementTypeHint)) {
                    return false; // if semantic hint is specified it should be the same as in element type
                }
                if (domainElement != null
                        && visualID != DcaseVisualIDRegistry.getNodeVisualID(op
                                .getContainerView(), domainElement)) {
                    return false; // visual id for node EClass should match visual id from element type
                }
            } else {
                if (!ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                        .getModelID(op.getContainerView()))) {
                    return false; // foreign diagram
                }
                switch (visualID) {
                    case GoalEditPart.VISUAL_ID:
                    case StrategyEditPart.VISUAL_ID:
                    case EvidenceEditPart.VISUAL_ID:
                    case MonitorEditPart.VISUAL_ID:
                    case UndevelopedEditPart.VISUAL_ID:
                    case ContextEditPart.VISUAL_ID:
                    case JustificationEditPart.VISUAL_ID:
                    case SystemEditPart.VISUAL_ID:
                    case PolicyEditPart.VISUAL_ID:
                    case Userdef001EditPart.VISUAL_ID:
                    case Userdef002EditPart.VISUAL_ID:
                    case Userdef003EditPart.VISUAL_ID:
                    case Userdef004EditPart.VISUAL_ID:
                    case Userdef005EditPart.VISUAL_ID:
                    case Userdef006EditPart.VISUAL_ID:
                        if (domainElement == null
                                || visualID != DcaseVisualIDRegistry
                                        .getNodeVisualID(op.getContainerView(),
                                                domainElement)) {
                            return false; // visual id in semantic hint should match visual id for domain element
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return GoalEditPart.VISUAL_ID == visualID
                || StrategyEditPart.VISUAL_ID == visualID
                || EvidenceEditPart.VISUAL_ID == visualID
                || MonitorEditPart.VISUAL_ID == visualID
                || UndevelopedEditPart.VISUAL_ID == visualID
                || ContextEditPart.VISUAL_ID == visualID
                || JustificationEditPart.VISUAL_ID == visualID
                || SystemEditPart.VISUAL_ID == visualID
                || PolicyEditPart.VISUAL_ID == visualID
                || Userdef001EditPart.VISUAL_ID == visualID
                || Userdef002EditPart.VISUAL_ID == visualID
                || Userdef003EditPart.VISUAL_ID == visualID
                || Userdef004EditPart.VISUAL_ID == visualID
                || Userdef005EditPart.VISUAL_ID == visualID
                || Userdef006EditPart.VISUAL_ID == visualID;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateEdgeViewOperation op) {
        IElementType elementType = getSemanticElementType(op
                .getSemanticAdapter());
        if (!DcaseElementTypes.isKnownElementType(elementType)
                || (!(elementType instanceof IHintedType))) {
            return false; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (elementTypeHint == null
                || (op.getSemanticHint() != null && !elementTypeHint.equals(op
                        .getSemanticHint()))) {
            return false; // our hint is visual id and must be specified, and it should be the same as in element type
        }
        int visualID = DcaseVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = getSemanticElement(op.getSemanticAdapter());
        if (domainElement != null
                && visualID != DcaseVisualIDRegistry
                        .getLinkWithClassVisualID(domainElement)) {
            return false; // visual id for link EClass should match visual id from element type
        }
        return true;
    }

    /**
     * @generated
     */
    public Diagram createDiagram(IAdaptable semanticAdapter,
            String diagramKind, PreferencesHint preferencesHint) {
        Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
        diagram.setType(ArgumentEditPart.MODEL_ID);
        diagram.setElement(getSemanticElement(semanticAdapter));
        diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
        return diagram;
    }

    /**
     * @generated
     */
    public Node createNode(IAdaptable semanticAdapter, View containerView,
            String semanticHint, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        final EObject domainElement = getSemanticElement(semanticAdapter);
        final int visualID;
        if (semanticHint == null || "Dcase-CEP".equals(semanticHint)) { //$NON-NLS-1$
            visualID = DcaseVisualIDRegistry.getNodeVisualID(containerView,
                    domainElement);
        } else {
            visualID = DcaseVisualIDRegistry.getVisualID(semanticHint);
        }
        switch (visualID) {
            case GoalEditPart.VISUAL_ID:
                return createGoal_1001(domainElement, containerView, index,
                        persisted, preferencesHint);
            case StrategyEditPart.VISUAL_ID:
                return createStrategy_1002(domainElement, containerView, index,
                        persisted, preferencesHint);
            case EvidenceEditPart.VISUAL_ID:
                return createEvidence_1003(domainElement, containerView, index,
                        persisted, preferencesHint);
            case MonitorEditPart.VISUAL_ID:
                return createMonitor_1004(domainElement, containerView, index,
                        persisted, preferencesHint);
            case UndevelopedEditPart.VISUAL_ID:
                return createUndeveloped_1005(domainElement, containerView,
                        index, persisted, preferencesHint);
            case ContextEditPart.VISUAL_ID:
                return createContext_1006(domainElement, containerView, index,
                        persisted, preferencesHint);
            case JustificationEditPart.VISUAL_ID:
                return createJustification_1007(domainElement, containerView,
                        index, persisted, preferencesHint);
            case SystemEditPart.VISUAL_ID:
                return createSystem_1008(domainElement, containerView, index,
                        persisted, preferencesHint);
            case PolicyEditPart.VISUAL_ID:
                return createPolicy_1009(domainElement, containerView, index,
                        persisted, preferencesHint);
            case Userdef001EditPart.VISUAL_ID:
                return createUserdef001_1010(domainElement, containerView,
                        index, persisted, preferencesHint);
            case Userdef002EditPart.VISUAL_ID:
                return createUserdef002_1011(domainElement, containerView,
                        index, persisted, preferencesHint);
            case Userdef003EditPart.VISUAL_ID:
                return createUserdef003_1012(domainElement, containerView,
                        index, persisted, preferencesHint);
            case Userdef004EditPart.VISUAL_ID:
                return createUserdef004_1013(domainElement, containerView,
                        index, persisted, preferencesHint);
            case Userdef005EditPart.VISUAL_ID:
                return createUserdef005_1014(domainElement, containerView,
                        index, persisted, preferencesHint);
            case Userdef006EditPart.VISUAL_ID:
                return createUserdef006_1015(domainElement, containerView,
                        index, persisted, preferencesHint);
        }
        // can't happen, provided #provides(CreateNodeViewOperation) is correct
        return null;
    }

    /**
     * @generated
     */
    public Edge createEdge(IAdaptable semanticAdapter, View containerView,
            String semanticHint, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        IElementType elementType = getSemanticElementType(semanticAdapter);
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        switch (DcaseVisualIDRegistry.getVisualID(elementTypeHint)) {
            case DcaseLink001EditPart.VISUAL_ID:
                return createDcaseLink001_3001(
                        getSemanticElement(semanticAdapter), containerView,
                        index, persisted, preferencesHint);
            case DcaseLink002EditPart.VISUAL_ID:
                return createDcaseLink002_3002(
                        getSemanticElement(semanticAdapter), containerView,
                        index, persisted, preferencesHint);
            case DcaseLink003EditPart.VISUAL_ID:
                return createDcaseLink003_3003(
                        getSemanticElement(semanticAdapter), containerView,
                        index, persisted, preferencesHint);
            case DcaseLink004EditPart.VISUAL_ID:
                return createDcaseLink004_3004(
                        getSemanticElement(semanticAdapter), containerView,
                        index, persisted, preferencesHint);
        }
        // can never happen, provided #provides(CreateEdgeViewOperation) is correct
        return null;
    }

    /**
     * @generated
     */
    public Node createGoal_1001(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(GoalEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4001 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalNameEditPart.VISUAL_ID));
        Node label4002 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalUserdef001EditPart.VISUAL_ID));
        Node label4003 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalDescEditPart.VISUAL_ID));
        Node label4004 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalUserdef002EditPart.VISUAL_ID));
        Node label4066 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalAttachmentEditPart.VISUAL_ID));
        Node label4071 = createLabel(node, DcaseVisualIDRegistry
                .getType(GoalResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createStrategy_1002(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(StrategyEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4005 = createLabel(node, DcaseVisualIDRegistry
                .getType(StrategyNameEditPart.VISUAL_ID));
        Node label4006 = createLabel(node, DcaseVisualIDRegistry
                .getType(StrategyUserdef001EditPart.VISUAL_ID));
        Node label4007 = createLabel(node, DcaseVisualIDRegistry
                .getType(StrategyDescEditPart.VISUAL_ID));
        Node label4008 = createLabel(node, DcaseVisualIDRegistry
                .getType(StrategyUserdef002EditPart.VISUAL_ID));
        Node label4075 = createLabel(node, DcaseVisualIDRegistry
                .getType(StrategyResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createEvidence_1003(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(EvidenceEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4009 = createLabel(node, DcaseVisualIDRegistry
                .getType(EvidenceNameEditPart.VISUAL_ID));
        Node label4010 = createLabel(node, DcaseVisualIDRegistry
                .getType(EvidenceUserdef001EditPart.VISUAL_ID));
        Node label4011 = createLabel(node, DcaseVisualIDRegistry
                .getType(EvidenceDescEditPart.VISUAL_ID));
        Node label4012 = createLabel(node, DcaseVisualIDRegistry
                .getType(EvidenceUserdef002EditPart.VISUAL_ID));
        Node label4070 = createLabel(node, DcaseVisualIDRegistry
                .getType(EvidenceResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createMonitor_1004(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(MonitorEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4013 = createLabel(node, DcaseVisualIDRegistry
                .getType(MonitorNameEditPart.VISUAL_ID));
        Node label4014 = createLabel(node, DcaseVisualIDRegistry
                .getType(MonitorUserdef001EditPart.VISUAL_ID));
        Node label4015 = createLabel(node, DcaseVisualIDRegistry
                .getType(MonitorDescEditPart.VISUAL_ID));
        Node label4016 = createLabel(node, DcaseVisualIDRegistry
                .getType(MonitorUserdef002EditPart.VISUAL_ID));
        Node label4073 = createLabel(node, DcaseVisualIDRegistry
                .getType(MonitorResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUndeveloped_1005(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(UndevelopedEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4017 = createLabel(node, DcaseVisualIDRegistry
                .getType(UndevelopedNameEditPart.VISUAL_ID));
        Node label4018 = createLabel(node, DcaseVisualIDRegistry
                .getType(UndevelopedUserdef001EditPart.VISUAL_ID));
        Node label4019 = createLabel(node, DcaseVisualIDRegistry
                .getType(UndevelopedDescEditPart.VISUAL_ID));
        Node label4020 = createLabel(node, DcaseVisualIDRegistry
                .getType(UndevelopedUserdef002EditPart.VISUAL_ID));
        Node label4077 = createLabel(node, DcaseVisualIDRegistry
                .getType(UndevelopedResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createContext_1006(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(ContextEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4021 = createLabel(node, DcaseVisualIDRegistry
                .getType(ContextNameEditPart.VISUAL_ID));
        Node label4022 = createLabel(node, DcaseVisualIDRegistry
                .getType(ContextUserdef001EditPart.VISUAL_ID));
        Node label4023 = createLabel(node, DcaseVisualIDRegistry
                .getType(ContextDescEditPart.VISUAL_ID));
        Node label4024 = createLabel(node, DcaseVisualIDRegistry
                .getType(ContextUserdef002EditPart.VISUAL_ID));
        Node label4069 = createLabel(node, DcaseVisualIDRegistry
                .getType(ContextResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createJustification_1007(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(JustificationEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4025 = createLabel(node, DcaseVisualIDRegistry
                .getType(JustificationNameEditPart.VISUAL_ID));
        Node label4026 = createLabel(node, DcaseVisualIDRegistry
                .getType(JustificationUserdef001EditPart.VISUAL_ID));
        Node label4027 = createLabel(node, DcaseVisualIDRegistry
                .getType(JustificationDescEditPart.VISUAL_ID));
        Node label4028 = createLabel(node, DcaseVisualIDRegistry
                .getType(JustificationUserdef002EditPart.VISUAL_ID));
        Node label4072 = createLabel(node, DcaseVisualIDRegistry
                .getType(JustificationResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createSystem_1008(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(SystemEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4029 = createLabel(node, DcaseVisualIDRegistry
                .getType(SystemNameEditPart.VISUAL_ID));
        Node label4030 = createLabel(node, DcaseVisualIDRegistry
                .getType(SystemUserdef001EditPart.VISUAL_ID));
        Node label4031 = createLabel(node, DcaseVisualIDRegistry
                .getType(SystemDescEditPart.VISUAL_ID));
        Node label4032 = createLabel(node, DcaseVisualIDRegistry
                .getType(SystemUserdef002EditPart.VISUAL_ID));
        Node label4076 = createLabel(node, DcaseVisualIDRegistry
                .getType(SystemResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createPolicy_1009(EObject domainElement, View containerView,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry.getType(PolicyEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4033 = createLabel(node, DcaseVisualIDRegistry
                .getType(PolicyNameEditPart.VISUAL_ID));
        Node label4034 = createLabel(node, DcaseVisualIDRegistry
                .getType(PolicyUserdef001EditPart.VISUAL_ID));
        Node label4035 = createLabel(node, DcaseVisualIDRegistry
                .getType(PolicyDescEditPart.VISUAL_ID));
        Node label4036 = createLabel(node, DcaseVisualIDRegistry
                .getType(PolicyUserdef002EditPart.VISUAL_ID));
        Node label4074 = createLabel(node, DcaseVisualIDRegistry
                .getType(PolicyResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef001_1010(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef001EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4037 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001NameEditPart.VISUAL_ID));
        Node label4038 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001Userdef001EditPart.VISUAL_ID));
        Node label4039 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001DescEditPart.VISUAL_ID));
        Node label4040 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001Userdef002EditPart.VISUAL_ID));
        Node label4067 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001AttachmentEditPart.VISUAL_ID));
        Node label4078 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef001ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef002_1011(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef002EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4041 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef002NameEditPart.VISUAL_ID));
        Node label4042 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef002Userdef001EditPart.VISUAL_ID));
        Node label4043 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef002DescEditPart.VISUAL_ID));
        Node label4044 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef002Userdef002EditPart.VISUAL_ID));
        Node label4079 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef002ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef003_1012(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef003EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4045 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef003NameEditPart.VISUAL_ID));
        Node label4046 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef003Userdef001EditPart.VISUAL_ID));
        Node label4047 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef003DescEditPart.VISUAL_ID));
        Node label4048 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef003Userdef002EditPart.VISUAL_ID));
        Node label4080 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef003ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef004_1013(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef004EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4049 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef004NameEditPart.VISUAL_ID));
        Node label4050 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef004Userdef001EditPart.VISUAL_ID));
        Node label4051 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef004DescEditPart.VISUAL_ID));
        Node label4052 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef004Userdef002EditPart.VISUAL_ID));
        Node label4081 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef004ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef005_1014(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef005EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4053 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005NameEditPart.VISUAL_ID));
        Node label4054 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005Userdef001EditPart.VISUAL_ID));
        Node label4055 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005DescEditPart.VISUAL_ID));
        Node label4056 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005Userdef002EditPart.VISUAL_ID));
        Node label4065 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005AttachmentEditPart.VISUAL_ID));
        Node label4068 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef005ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Node createUserdef006_1015(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Shape node = NotationFactory.eINSTANCE.createShape();
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(DcaseVisualIDRegistry
                .getType(Userdef006EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_FILL_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE
                .getFillStyle_FillColor(), FigureUtilities
                .RGBToInteger(fillRGB));
        Node label4057 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef006NameEditPart.VISUAL_ID));
        Node label4058 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef006Userdef001EditPart.VISUAL_ID));
        Node label4059 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef006DescEditPart.VISUAL_ID));
        Node label4060 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef006Userdef002EditPart.VISUAL_ID));
        Node label4082 = createLabel(node, DcaseVisualIDRegistry
                .getType(Userdef006ResponsibilityEditPart.VISUAL_ID));
        return node;
    }

    /**
     * @generated
     */
    public Edge createDcaseLink001_3001(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        ArrayList points = new ArrayList(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(DcaseVisualIDRegistry
                .getType(DcaseLink001EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        Routing routing = Routing.get(prefStore
                .getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                    .getRoutingStyle_Routing(), routing);
        }
        Node label4061 = createLabel(edge, DcaseVisualIDRegistry
                .getType(DcaseLink001Userdef001DescUserdef00EditPart.VISUAL_ID));
        label4061.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        Location location4061 = (Location) label4061.getLayoutConstraint();
        location4061.setX(0);
        location4061.setY(40);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createDcaseLink002_3002(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        ArrayList points = new ArrayList(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(DcaseVisualIDRegistry
                .getType(DcaseLink002EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        Routing routing = Routing.get(prefStore
                .getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                    .getRoutingStyle_Routing(), routing);
        }
        Node label4062 = createLabel(edge, DcaseVisualIDRegistry
                .getType(DcaseLink002Userdef001DescUserdef00EditPart.VISUAL_ID));
        label4062.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        Location location4062 = (Location) label4062.getLayoutConstraint();
        location4062.setX(0);
        location4062.setY(40);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createDcaseLink003_3003(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        ArrayList points = new ArrayList(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(DcaseVisualIDRegistry
                .getType(DcaseLink003EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        Routing routing = Routing.get(prefStore
                .getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                    .getRoutingStyle_Routing(), routing);
        }
        Node label4063 = createLabel(edge, DcaseVisualIDRegistry
                .getType(DcaseLink003Userdef001DescUserdef00EditPart.VISUAL_ID));
        label4063.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        Location location4063 = (Location) label4063.getLayoutConstraint();
        location4063.setX(0);
        location4063.setY(40);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createDcaseLink004_3004(EObject domainElement,
            View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        ArrayList points = new ArrayList(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(DcaseVisualIDRegistry
                .getType(DcaseLink004EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                .getLineStyle_LineColor(), FigureUtilities
                .RGBToInteger(lineRGB));
        FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        Routing routing = Routing.get(prefStore
                .getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE
                    .getRoutingStyle_Routing(), routing);
        }
        Node label4064 = createLabel(edge, DcaseVisualIDRegistry
                .getType(DcaseLink004Userdef001DescUserdef00EditPart.VISUAL_ID));
        label4064.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        Location location4064 = (Location) label4064.getLayoutConstraint();
        location4064.setX(0);
        location4064.setY(40);
        return edge;
    }

    /**
     * @generated
     */
    private void stampShortcut(View containerView, Node target) {
        if (!ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                .getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
                    .createEAnnotation();
            shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
            shortcutAnnotation.getDetails().put(
                    "modelID", ArgumentEditPart.MODEL_ID); //$NON-NLS-1$
            target.getEAnnotations().add(shortcutAnnotation);
        }
    }

    /**
     * @generated
     */
    private Node createLabel(View owner, String hint) {
        DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
        rv.setType(hint);
        ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
        return rv;
    }

    /**
     * @generated
     */
    private EObject getSemanticElement(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            return EMFCoreUtil.resolve(TransactionUtil
                    .getEditingDomain(eObject), eObject);
        }
        return null;
    }

    /**
     * @generated
     */
    private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        return (IElementType) semanticAdapter.getAdapter(IElementType.class);
    }
}
