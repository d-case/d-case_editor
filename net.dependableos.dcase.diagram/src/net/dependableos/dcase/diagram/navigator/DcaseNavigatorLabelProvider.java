/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.navigator;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;
import net.dependableos.dcase.diagram.edit.parts.ContextNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink001EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink001Userdef001DescUserdef00EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink002EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink002Userdef001DescUserdef00EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink003EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink003Userdef001DescUserdef00EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink004EditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink004Userdef001DescUserdef00EditPart;
import net.dependableos.dcase.diagram.edit.parts.EvidenceEditPart;
import net.dependableos.dcase.diagram.edit.parts.EvidenceNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalEditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.JustificationEditPart;
import net.dependableos.dcase.diagram.edit.parts.JustificationNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.MonitorEditPart;
import net.dependableos.dcase.diagram.edit.parts.MonitorNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.PolicyEditPart;
import net.dependableos.dcase.diagram.edit.parts.PolicyNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.StrategyEditPart;
import net.dependableos.dcase.diagram.edit.parts.StrategyNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.UndevelopedEditPart;
import net.dependableos.dcase.diagram.edit.parts.UndevelopedNameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef001EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef001NameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef002EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef002NameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef003EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef003NameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef004EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef004NameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef005EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef005NameEditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef006EditPart;
import net.dependableos.dcase.diagram.edit.parts.Userdef006NameEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;
import net.dependableos.dcase.diagram.providers.DcaseParserProvider;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class DcaseNavigatorLabelProvider extends LabelProvider implements
        ICommonLabelProvider, ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        DcaseDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        DcaseDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof DcaseNavigatorItem
                && !isOwnView(((DcaseNavigatorItem) element).getView())) {
            return;
        }
        label.setText(getText(element));
        label.setImage(getImage(element));
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof DcaseNavigatorGroup) {
            DcaseNavigatorGroup group = (DcaseNavigatorGroup) element;
            return DcaseDiagramEditorPlugin.getInstance().getBundledImage(
                    group.getIcon());
        }

        if (element instanceof DcaseNavigatorItem) {
            DcaseNavigatorItem navigatorItem = (DcaseNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return super.getImage(element);
            }
            return getImage(navigatorItem.getView());
        }

        return super.getImage(element);
    }

    /**
     * @generated
     */
    public Image getImage(View view) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case ArgumentEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?Diagram?http://www.dependable-os.net/2013/11/dcase_model/?Argument", DcaseElementTypes.Argument_79); //$NON-NLS-1$
            case GoalEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Goal", DcaseElementTypes.Goal_1001); //$NON-NLS-1$
            case StrategyEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Strategy", DcaseElementTypes.Strategy_1002); //$NON-NLS-1$
            case EvidenceEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Evidence", DcaseElementTypes.Evidence_1003); //$NON-NLS-1$
            case MonitorEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Monitor", DcaseElementTypes.Monitor_1004); //$NON-NLS-1$
            case UndevelopedEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Undeveloped", DcaseElementTypes.Undeveloped_1005); //$NON-NLS-1$
            case ContextEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Context", DcaseElementTypes.Context_1006); //$NON-NLS-1$
            case JustificationEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Justification", DcaseElementTypes.Justification_1007); //$NON-NLS-1$
            case SystemEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Pattern", DcaseElementTypes.System_1008); //$NON-NLS-1$
            case PolicyEditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Action", DcaseElementTypes.Policy_1009); //$NON-NLS-1$
            case Userdef001EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?External", DcaseElementTypes.Userdef001_1010); //$NON-NLS-1$
            case Userdef002EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Userdef002", DcaseElementTypes.Userdef002_1011); //$NON-NLS-1$
            case Userdef003EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Userdef003", DcaseElementTypes.Userdef003_1012); //$NON-NLS-1$
            case Userdef004EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Assumption", DcaseElementTypes.Userdef004_1013); //$NON-NLS-1$
            case Userdef005EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Module", DcaseElementTypes.Userdef005_1014); //$NON-NLS-1$
            case Userdef006EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?TopLevelNode?http://www.dependable-os.net/2013/11/dcase_model/?Contract", DcaseElementTypes.Userdef006_1015); //$NON-NLS-1$
            case DcaseLink001EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?Link?http://www.dependable-os.net/2013/11/dcase_model/?SupportedBy", DcaseElementTypes.DcaseLink001_3001); //$NON-NLS-1$
            case DcaseLink002EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?Link?http://www.dependable-os.net/2013/11/dcase_model/?InContextOf", DcaseElementTypes.DcaseLink002_3002); //$NON-NLS-1$
            case DcaseLink003EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?Link?http://www.dependable-os.net/2013/11/dcase_model/?Responsibility", DcaseElementTypes.DcaseLink003_3003); //$NON-NLS-1$
            case DcaseLink004EditPart.VISUAL_ID:
                return getImage(
                        "Navigator?Link?http://www.dependable-os.net/2013/11/dcase_model/?DcaseLink004", DcaseElementTypes.DcaseLink004_3004); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = DcaseDiagramEditorPlugin.getInstance()
                .getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null
                && DcaseElementTypes.isKnownElementType(elementType)) {
            image = DcaseElementTypes.getImage(elementType);
            imageRegistry.put(key, image);
        }

        if (image == null) {
            image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
            imageRegistry.put(key, image);
        }
        return image;
    }

    /**
     * @generated
     */
    public String getText(Object element) {
        if (element instanceof DcaseNavigatorGroup) {
            DcaseNavigatorGroup group = (DcaseNavigatorGroup) element;
            return group.getGroupName();
        }

        if (element instanceof DcaseNavigatorItem) {
            DcaseNavigatorItem navigatorItem = (DcaseNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return null;
            }
            return getText(navigatorItem.getView());
        }

        return super.getText(element);
    }

    /**
     * @generated
     */
    public String getText(View view) {
        if (view.getElement() != null && view.getElement().eIsProxy()) {
            return getUnresolvedDomainElementProxyText(view);
        }
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case ArgumentEditPart.VISUAL_ID:
                return getArgument_79Text(view);
            case GoalEditPart.VISUAL_ID:
                return getGoal_1001Text(view);
            case StrategyEditPart.VISUAL_ID:
                return getStrategy_1002Text(view);
            case EvidenceEditPart.VISUAL_ID:
                return getEvidence_1003Text(view);
            case MonitorEditPart.VISUAL_ID:
                return getMonitor_1004Text(view);
            case UndevelopedEditPart.VISUAL_ID:
                return getUndeveloped_1005Text(view);
            case ContextEditPart.VISUAL_ID:
                return getContext_1006Text(view);
            case JustificationEditPart.VISUAL_ID:
                return getJustification_1007Text(view);
            case SystemEditPart.VISUAL_ID:
                return getSystem_1008Text(view);
            case PolicyEditPart.VISUAL_ID:
                return getPolicy_1009Text(view);
            case Userdef001EditPart.VISUAL_ID:
                return getUserdef001_1010Text(view);
            case Userdef002EditPart.VISUAL_ID:
                return getUserdef002_1011Text(view);
            case Userdef003EditPart.VISUAL_ID:
                return getUserdef003_1012Text(view);
            case Userdef004EditPart.VISUAL_ID:
                return getUserdef004_1013Text(view);
            case Userdef005EditPart.VISUAL_ID:
                return getUserdef005_1014Text(view);
            case Userdef006EditPart.VISUAL_ID:
                return getUserdef006_1015Text(view);
            case DcaseLink001EditPart.VISUAL_ID:
                return getDcaseLink001_3001Text(view);
            case DcaseLink002EditPart.VISUAL_ID:
                return getDcaseLink002_3002Text(view);
            case DcaseLink003EditPart.VISUAL_ID:
                return getDcaseLink003_3003Text(view);
            case DcaseLink004EditPart.VISUAL_ID:
                return getDcaseLink004_3004Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getArgument_79Text(View view) {
        Argument domainModelElement = (Argument) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getName();
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 79); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getGoal_1001Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Goal_1001, view.getElement() != null ? view
                        .getElement() : view, DcaseVisualIDRegistry
                        .getType(GoalNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4001); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getStrategy_1002Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Strategy_1002,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry.getType(StrategyNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4005); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEvidence_1003Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Evidence_1003,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry.getType(EvidenceNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4009); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getMonitor_1004Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Monitor_1004,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry.getType(MonitorNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4013); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUndeveloped_1005Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Undeveloped_1005,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry
                        .getType(UndevelopedNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4017); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getContext_1006Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Context_1006,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry.getType(ContextNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4021); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getJustification_1007Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Justification_1007,
                view.getElement() != null ? view.getElement() : view,
                DcaseVisualIDRegistry
                        .getType(JustificationNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4025); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getSystem_1008Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.System_1008, view.getElement() != null ? view
                        .getElement() : view, DcaseVisualIDRegistry
                        .getType(SystemNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4029); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getPolicy_1009Text(View view) {
        IParser parser = DcaseParserProvider.getParser(
                DcaseElementTypes.Policy_1009, view.getElement() != null ? view
                        .getElement() : view, DcaseVisualIDRegistry
                        .getType(PolicyNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4033); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef001_1010Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef001_1010,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef001NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4037); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef002_1011Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef002_1011,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef002NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4041); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef003_1012Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef003_1012,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef003NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4045); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef004_1013Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef004_1013,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef004NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4049); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef005_1014Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef005_1014,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef005NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4053); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUserdef006_1015Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(DcaseElementTypes.Userdef006_1015,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(Userdef006NameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4057); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getDcaseLink001_3001Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(
                        DcaseElementTypes.DcaseLink001_3001,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(DcaseLink001Userdef001DescUserdef00EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4061); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getDcaseLink002_3002Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(
                        DcaseElementTypes.DcaseLink002_3002,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(DcaseLink002Userdef001DescUserdef00EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4062); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getDcaseLink003_3003Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(
                        DcaseElementTypes.DcaseLink003_3003,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(DcaseLink003Userdef001DescUserdef00EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4063); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getDcaseLink004_3004Text(View view) {
        IParser parser = DcaseParserProvider
                .getParser(
                        DcaseElementTypes.DcaseLink004_3004,
                        view.getElement() != null ? view.getElement() : view,
                        DcaseVisualIDRegistry
                                .getType(DcaseLink004Userdef001DescUserdef00EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            DcaseDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 4064); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUnknownElementText(View view) {
        return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
    }

    /**
     * @generated
     */
    private String getUnresolvedDomainElementProxyText(View view) {
        return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
    }

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public String getDescription(Object anElement) {
        return null;
    }

    /**
     * @generated
     */
    private boolean isOwnView(View view) {
        return ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                .getModelID(view));
    }

}
