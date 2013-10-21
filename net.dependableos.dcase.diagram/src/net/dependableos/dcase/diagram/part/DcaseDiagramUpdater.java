/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Context;
import net.dependableos.dcase.DcaseLink001;
import net.dependableos.dcase.DcaseLink002;
import net.dependableos.dcase.DcaseLink003;
import net.dependableos.dcase.DcaseLink004;
import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.Evidence;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.Policy;
import net.dependableos.dcase.Strategy;
import net.dependableos.dcase.System;
import net.dependableos.dcase.Undeveloped;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef002;
import net.dependableos.dcase.Userdef003;
import net.dependableos.dcase.Userdef004;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.Userdef006;
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
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcaseDiagramUpdater {

    /**
     * @generated
     */
    public static List getSemanticChildren(View view) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case ArgumentEditPart.VISUAL_ID:
                return getArgument_79SemanticChildren(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getArgument_79SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        Argument modelElement = (Argument) view.getElement();
        List result = new LinkedList();
        for (Iterator it = modelElement.getRootBasicNode().iterator(); it
                .hasNext();) {
            BasicNode childElement = (BasicNode) it.next();
            int visualID = DcaseVisualIDRegistry.getNodeVisualID(view,
                    childElement);
            if (visualID == GoalEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == StrategyEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == EvidenceEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == MonitorEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == UndevelopedEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == ContextEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == JustificationEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == SystemEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == PolicyEditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef001EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef002EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef003EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef004EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef005EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Userdef006EditPart.VISUAL_ID) {
                result.add(new DcaseNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List getContainedLinks(View view) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case ArgumentEditPart.VISUAL_ID:
                return getArgument_79ContainedLinks(view);
            case GoalEditPart.VISUAL_ID:
                return getGoal_1001ContainedLinks(view);
            case StrategyEditPart.VISUAL_ID:
                return getStrategy_1002ContainedLinks(view);
            case EvidenceEditPart.VISUAL_ID:
                return getEvidence_1003ContainedLinks(view);
            case MonitorEditPart.VISUAL_ID:
                return getMonitor_1004ContainedLinks(view);
            case UndevelopedEditPart.VISUAL_ID:
                return getUndeveloped_1005ContainedLinks(view);
            case ContextEditPart.VISUAL_ID:
                return getContext_1006ContainedLinks(view);
            case JustificationEditPart.VISUAL_ID:
                return getJustification_1007ContainedLinks(view);
            case SystemEditPart.VISUAL_ID:
                return getSystem_1008ContainedLinks(view);
            case PolicyEditPart.VISUAL_ID:
                return getPolicy_1009ContainedLinks(view);
            case Userdef001EditPart.VISUAL_ID:
                return getUserdef001_1010ContainedLinks(view);
            case Userdef002EditPart.VISUAL_ID:
                return getUserdef002_1011ContainedLinks(view);
            case Userdef003EditPart.VISUAL_ID:
                return getUserdef003_1012ContainedLinks(view);
            case Userdef004EditPart.VISUAL_ID:
                return getUserdef004_1013ContainedLinks(view);
            case Userdef005EditPart.VISUAL_ID:
                return getUserdef005_1014ContainedLinks(view);
            case Userdef006EditPart.VISUAL_ID:
                return getUserdef006_1015ContainedLinks(view);
            case DcaseLink001EditPart.VISUAL_ID:
                return getDcaseLink001_3001ContainedLinks(view);
            case DcaseLink002EditPart.VISUAL_ID:
                return getDcaseLink002_3002ContainedLinks(view);
            case DcaseLink003EditPart.VISUAL_ID:
                return getDcaseLink003_3003ContainedLinks(view);
            case DcaseLink004EditPart.VISUAL_ID:
                return getDcaseLink004_3004ContainedLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getIncomingLinks(View view) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case GoalEditPart.VISUAL_ID:
                return getGoal_1001IncomingLinks(view);
            case StrategyEditPart.VISUAL_ID:
                return getStrategy_1002IncomingLinks(view);
            case EvidenceEditPart.VISUAL_ID:
                return getEvidence_1003IncomingLinks(view);
            case MonitorEditPart.VISUAL_ID:
                return getMonitor_1004IncomingLinks(view);
            case UndevelopedEditPart.VISUAL_ID:
                return getUndeveloped_1005IncomingLinks(view);
            case ContextEditPart.VISUAL_ID:
                return getContext_1006IncomingLinks(view);
            case JustificationEditPart.VISUAL_ID:
                return getJustification_1007IncomingLinks(view);
            case SystemEditPart.VISUAL_ID:
                return getSystem_1008IncomingLinks(view);
            case PolicyEditPart.VISUAL_ID:
                return getPolicy_1009IncomingLinks(view);
            case Userdef001EditPart.VISUAL_ID:
                return getUserdef001_1010IncomingLinks(view);
            case Userdef002EditPart.VISUAL_ID:
                return getUserdef002_1011IncomingLinks(view);
            case Userdef003EditPart.VISUAL_ID:
                return getUserdef003_1012IncomingLinks(view);
            case Userdef004EditPart.VISUAL_ID:
                return getUserdef004_1013IncomingLinks(view);
            case Userdef005EditPart.VISUAL_ID:
                return getUserdef005_1014IncomingLinks(view);
            case Userdef006EditPart.VISUAL_ID:
                return getUserdef006_1015IncomingLinks(view);
            case DcaseLink001EditPart.VISUAL_ID:
                return getDcaseLink001_3001IncomingLinks(view);
            case DcaseLink002EditPart.VISUAL_ID:
                return getDcaseLink002_3002IncomingLinks(view);
            case DcaseLink003EditPart.VISUAL_ID:
                return getDcaseLink003_3003IncomingLinks(view);
            case DcaseLink004EditPart.VISUAL_ID:
                return getDcaseLink004_3004IncomingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getOutgoingLinks(View view) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case GoalEditPart.VISUAL_ID:
                return getGoal_1001OutgoingLinks(view);
            case StrategyEditPart.VISUAL_ID:
                return getStrategy_1002OutgoingLinks(view);
            case EvidenceEditPart.VISUAL_ID:
                return getEvidence_1003OutgoingLinks(view);
            case MonitorEditPart.VISUAL_ID:
                return getMonitor_1004OutgoingLinks(view);
            case UndevelopedEditPart.VISUAL_ID:
                return getUndeveloped_1005OutgoingLinks(view);
            case ContextEditPart.VISUAL_ID:
                return getContext_1006OutgoingLinks(view);
            case JustificationEditPart.VISUAL_ID:
                return getJustification_1007OutgoingLinks(view);
            case SystemEditPart.VISUAL_ID:
                return getSystem_1008OutgoingLinks(view);
            case PolicyEditPart.VISUAL_ID:
                return getPolicy_1009OutgoingLinks(view);
            case Userdef001EditPart.VISUAL_ID:
                return getUserdef001_1010OutgoingLinks(view);
            case Userdef002EditPart.VISUAL_ID:
                return getUserdef002_1011OutgoingLinks(view);
            case Userdef003EditPart.VISUAL_ID:
                return getUserdef003_1012OutgoingLinks(view);
            case Userdef004EditPart.VISUAL_ID:
                return getUserdef004_1013OutgoingLinks(view);
            case Userdef005EditPart.VISUAL_ID:
                return getUserdef005_1014OutgoingLinks(view);
            case Userdef006EditPart.VISUAL_ID:
                return getUserdef006_1015OutgoingLinks(view);
            case DcaseLink001EditPart.VISUAL_ID:
                return getDcaseLink001_3001OutgoingLinks(view);
            case DcaseLink002EditPart.VISUAL_ID:
                return getDcaseLink002_3002OutgoingLinks(view);
            case DcaseLink003EditPart.VISUAL_ID:
                return getDcaseLink003_3003OutgoingLinks(view);
            case DcaseLink004EditPart.VISUAL_ID:
                return getDcaseLink004_3004OutgoingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getArgument_79ContainedLinks(View view) {
        Argument modelElement = (Argument) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getContainedTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getContainedTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getContainedTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getContainedTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getGoal_1001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getStrategy_1002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getEvidence_1003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getMonitor_1004ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUndeveloped_1005ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getContext_1006ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getJustification_1007ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getSystem_1008ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getPolicy_1009ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef001_1010ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef002_1011ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef003_1012ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef004_1013ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef005_1014ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getUserdef006_1015ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink001_3001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink002_3002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink003_3003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink004_3004ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getGoal_1001IncomingLinks(View view) {
        Goal modelElement = (Goal) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getStrategy_1002IncomingLinks(View view) {
        Strategy modelElement = (Strategy) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getEvidence_1003IncomingLinks(View view) {
        Evidence modelElement = (Evidence) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getMonitor_1004IncomingLinks(View view) {
        Monitor modelElement = (Monitor) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUndeveloped_1005IncomingLinks(View view) {
        Undeveloped modelElement = (Undeveloped) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getContext_1006IncomingLinks(View view) {
        Context modelElement = (Context) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getJustification_1007IncomingLinks(View view) {
        Justification modelElement = (Justification) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getSystem_1008IncomingLinks(View view) {
        System modelElement = (System) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getPolicy_1009IncomingLinks(View view) {
        Policy modelElement = (Policy) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef001_1010IncomingLinks(View view) {
        Userdef001 modelElement = (Userdef001) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef002_1011IncomingLinks(View view) {
        Userdef002 modelElement = (Userdef002) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef003_1012IncomingLinks(View view) {
        Userdef003 modelElement = (Userdef003) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef004_1013IncomingLinks(View view) {
        Userdef004 modelElement = (Userdef004) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef005_1014IncomingLinks(View view) {
        Userdef005 modelElement = (Userdef005) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef006_1015IncomingLinks(View view) {
        Userdef006 modelElement = (Userdef006) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
                .getResourceSet().getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink001_3001(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink002_3002(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink003_3003(
                modelElement, crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_DcaseLink004_3004(
                modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getDcaseLink001_3001IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink002_3002IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink003_3003IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink004_3004IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getGoal_1001OutgoingLinks(View view) {
        Goal modelElement = (Goal) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getStrategy_1002OutgoingLinks(View view) {
        Strategy modelElement = (Strategy) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getEvidence_1003OutgoingLinks(View view) {
        Evidence modelElement = (Evidence) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getMonitor_1004OutgoingLinks(View view) {
        Monitor modelElement = (Monitor) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUndeveloped_1005OutgoingLinks(View view) {
        Undeveloped modelElement = (Undeveloped) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getContext_1006OutgoingLinks(View view) {
        Context modelElement = (Context) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getJustification_1007OutgoingLinks(View view) {
        Justification modelElement = (Justification) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getSystem_1008OutgoingLinks(View view) {
        System modelElement = (System) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getPolicy_1009OutgoingLinks(View view) {
        Policy modelElement = (Policy) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef001_1010OutgoingLinks(View view) {
        Userdef001 modelElement = (Userdef001) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef002_1011OutgoingLinks(View view) {
        Userdef002 modelElement = (Userdef002) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef003_1012OutgoingLinks(View view) {
        Userdef003 modelElement = (Userdef003) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef004_1013OutgoingLinks(View view) {
        Userdef004 modelElement = (Userdef004) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef005_1014OutgoingLinks(View view) {
        Userdef005 modelElement = (Userdef005) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getUserdef006_1015OutgoingLinks(View view) {
        Userdef006 modelElement = (Userdef006) view.getElement();
        List result = new LinkedList();
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink001_3001(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink002_3002(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink003_3003(modelElement));
        result
                .addAll(getOutgoingTypeModelFacetLinks_DcaseLink004_3004(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getDcaseLink001_3001OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink002_3002OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink003_3003OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getDcaseLink004_3004OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_DcaseLink001_3001(
            Argument container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink001) {
                continue;
            }
            DcaseLink001 link = (DcaseLink001) linkObject;
            if (DcaseLink001EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink001_3001,
                    DcaseLink001EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_DcaseLink002_3002(
            Argument container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink002) {
                continue;
            }
            DcaseLink002 link = (DcaseLink002) linkObject;
            if (DcaseLink002EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink002_3002,
                    DcaseLink002EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_DcaseLink003_3003(
            Argument container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink003) {
                continue;
            }
            DcaseLink003 link = (DcaseLink003) linkObject;
            if (DcaseLink003EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink003_3003,
                    DcaseLink003EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_DcaseLink004_3004(
            Argument container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink004) {
                continue;
            }
            DcaseLink004 link = (DcaseLink004) linkObject;
            if (DcaseLink004EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink004_3004,
                    DcaseLink004EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_DcaseLink001_3001(
            BasicNode target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
                    .next();
            if (setting.getEStructuralFeature() != DcasePackage.eINSTANCE
                    .getBasicLink_Target()
                    || false == setting.getEObject() instanceof DcaseLink001) {
                continue;
            }
            DcaseLink001 link = (DcaseLink001) setting.getEObject();
            if (DcaseLink001EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, target, link,
                    DcaseElementTypes.DcaseLink001_3001,
                    DcaseLink001EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_DcaseLink002_3002(
            BasicNode target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
                    .next();
            if (setting.getEStructuralFeature() != DcasePackage.eINSTANCE
                    .getBasicLink_Target()
                    || false == setting.getEObject() instanceof DcaseLink002) {
                continue;
            }
            DcaseLink002 link = (DcaseLink002) setting.getEObject();
            if (DcaseLink002EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, target, link,
                    DcaseElementTypes.DcaseLink002_3002,
                    DcaseLink002EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_DcaseLink003_3003(
            BasicNode target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
                    .next();
            if (setting.getEStructuralFeature() != DcasePackage.eINSTANCE
                    .getBasicLink_Target()
                    || false == setting.getEObject() instanceof DcaseLink003) {
                continue;
            }
            DcaseLink003 link = (DcaseLink003) setting.getEObject();
            if (DcaseLink003EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, target, link,
                    DcaseElementTypes.DcaseLink003_3003,
                    DcaseLink003EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_DcaseLink004_3004(
            BasicNode target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
                    .next();
            if (setting.getEStructuralFeature() != DcasePackage.eINSTANCE
                    .getBasicLink_Target()
                    || false == setting.getEObject() instanceof DcaseLink004) {
                continue;
            }
            DcaseLink004 link = (DcaseLink004) setting.getEObject();
            if (DcaseLink004EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode src = link.getSource();
            result.add(new DcaseLinkDescriptor(src, target, link,
                    DcaseElementTypes.DcaseLink004_3004,
                    DcaseLink004EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getOutgoingTypeModelFacetLinks_DcaseLink001_3001(
            BasicNode source) {
        Argument container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element
                .eContainer()) {
            if (element instanceof Argument) {
                container = (Argument) element;
            }
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink001) {
                continue;
            }
            DcaseLink001 link = (DcaseLink001) linkObject;
            if (DcaseLink001EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink001_3001,
                    DcaseLink001EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getOutgoingTypeModelFacetLinks_DcaseLink002_3002(
            BasicNode source) {
        Argument container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element
                .eContainer()) {
            if (element instanceof Argument) {
                container = (Argument) element;
            }
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink002) {
                continue;
            }
            DcaseLink002 link = (DcaseLink002) linkObject;
            if (DcaseLink002EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink002_3002,
                    DcaseLink002EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getOutgoingTypeModelFacetLinks_DcaseLink003_3003(
            BasicNode source) {
        Argument container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element
                .eContainer()) {
            if (element instanceof Argument) {
                container = (Argument) element;
            }
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink003) {
                continue;
            }
            DcaseLink003 link = (DcaseLink003) linkObject;
            if (DcaseLink003EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink003_3003,
                    DcaseLink003EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getOutgoingTypeModelFacetLinks_DcaseLink004_3004(
            BasicNode source) {
        Argument container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element
                .eContainer()) {
            if (element instanceof Argument) {
                container = (Argument) element;
            }
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        for (Iterator links = container.getRootBasicLink().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof DcaseLink004) {
                continue;
            }
            DcaseLink004 link = (DcaseLink004) linkObject;
            if (DcaseLink004EditPart.VISUAL_ID != DcaseVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            BasicNode dst = link.getTarget();
            BasicNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new DcaseLinkDescriptor(src, dst, link,
                    DcaseElementTypes.DcaseLink004_3004,
                    DcaseLink004EditPart.VISUAL_ID));
        }
        return result;
    }

}
