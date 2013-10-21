/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import net.dependableos.dcase.diagram.part.DcaseDiagramUpdater;
import net.dependableos.dcase.diagram.part.DcaseLinkDescriptor;
import net.dependableos.dcase.diagram.part.DcaseNodeDescriptor;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ArgumentCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

    /**
     * @generated
     */
    Set myFeaturesToSynchronize;

    /**
     * @generated
     */
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        List result = new LinkedList();
        for (Iterator it = DcaseDiagramUpdater.getArgument_79SemanticChildren(
                viewObject).iterator(); it.hasNext();) {
            result.add(((DcaseNodeDescriptor) it.next()).getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean shouldDeleteView(View view) {
        return true;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection semanticChildren, final View view) {
        int visualID = DcaseVisualIDRegistry.getVisualID(view);
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
                if (!semanticChildren.contains(view.getElement())) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Not generated.
     */
    protected String getDefaultFactoryHint() {
        return "Dcase-CEP"; //$NON-NLS-1$
    }

    /**
     * @generated
     */
    protected Set getFeaturesToSynchronize() {
        if (myFeaturesToSynchronize == null) {
            myFeaturesToSynchronize = new HashSet();
            myFeaturesToSynchronize.add(DcasePackage.eINSTANCE
                    .getArgument_RootBasicNode());
        }
        return myFeaturesToSynchronize;
    }

    /**
     * @generated
     */
    protected List getSemanticConnectionsList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    protected EObject getSourceElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected EObject getTargetElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected boolean shouldIncludeConnection(Edge connector,
            Collection children) {
        return false;
    }

    /**
     * @generated
     */
    protected void refreshSemantic() {
        List createdViews = new LinkedList();
        createdViews.addAll(refreshSemanticChildren());
        List createdConnectionViews = new LinkedList();
        createdConnectionViews.addAll(refreshSemanticConnections());
        createdConnectionViews.addAll(refreshConnections());

        if (createdViews.size() > 1) {
            // perform a layout of the container
            DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
                    .getEditingDomain(), createdViews, host());
            executeCommand(new ICommandProxy(layoutCmd));
        }

        createdViews.addAll(createdConnectionViews);
        makeViewsImmutable(createdViews);
    }

    /**
     * @generated
     */
    private Diagram getDiagram() {
        return ((View) getHost().getModel()).getDiagram();
    }

    /**
     * @generated
     */
    private Collection refreshConnections() {
        Map domain2NotationMap = new HashMap();
        Collection linkDescriptors = collectAllLinks(getDiagram(),
                domain2NotationMap);
        Collection existingLinks = new LinkedList(getDiagram().getEdges());
        for (Iterator linksIterator = existingLinks.iterator(); linksIterator
                .hasNext();) {
            Edge nextDiagramLink = (Edge) linksIterator.next();
            int diagramLinkVisualID = DcaseVisualIDRegistry
                    .getVisualID(nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() != null
                        && nextDiagramLink.getTarget() != null) {
                    linksIterator.remove();
                }
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                    .hasNext();) {
                DcaseLinkDescriptor nextLinkDescriptor = (DcaseLinkDescriptor) linkDescriptorsIterator
                        .next();
                if (diagramLinkObject == nextLinkDescriptor.getModelElement()
                        && diagramLinkSrc == nextLinkDescriptor.getSource()
                        && diagramLinkDst == nextLinkDescriptor
                                .getDestination()
                        && diagramLinkVisualID == nextLinkDescriptor
                                .getVisualID()) {
                    linksIterator.remove();
                    linkDescriptorsIterator.remove();
                    break;
                }
            }
        }
        deleteViews(existingLinks.iterator());
        return createConnections(linkDescriptors, domain2NotationMap);
    }

    /**
     * @generated
     */
    private Collection collectAllLinks(View view, Map domain2NotationMap) {
        if (!ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                .getModelID(view))) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        switch (DcaseVisualIDRegistry.getVisualID(view)) {
            case ArgumentEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getArgument_79ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case GoalEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getGoal_1001ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case StrategyEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getStrategy_1002ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case EvidenceEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getEvidence_1003ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case MonitorEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getMonitor_1004ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case UndevelopedEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUndeveloped_1005ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case ContextEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getContext_1006ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case JustificationEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getJustification_1007ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case SystemEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getSystem_1008ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case PolicyEditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getPolicy_1009ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef001EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef001_1010ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef002EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef002_1011ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef003EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef003_1012ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef004EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef004_1013ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef005EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef005_1014ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case Userdef006EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getUserdef006_1015ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case DcaseLink001EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getDcaseLink001_3001ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case DcaseLink002EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getDcaseLink002_3002ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case DcaseLink003EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getDcaseLink003_3003ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
            case DcaseLink004EditPart.VISUAL_ID: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(DcaseDiagramUpdater
                            .getDcaseLink004_3004ContainedLinks(view));
                }
                if (!domain2NotationMap.containsKey(view.getElement())
                        || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                    domain2NotationMap.put(view.getElement(), view);
                }
                break;
            }
        }
        for (Iterator children = view.getChildren().iterator(); children
                .hasNext();) {
            result.addAll(collectAllLinks((View) children.next(),
                    domain2NotationMap));
        }
        for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
            result.addAll(collectAllLinks((View) edges.next(),
                    domain2NotationMap));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection createConnections(Collection linkDescriptors,
            Map domain2NotationMap) {
        List adapters = new LinkedList();
        for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                .hasNext();) {
            final DcaseLinkDescriptor nextLinkDescriptor = (DcaseLinkDescriptor) linkDescriptorsIterator
                    .next();
            EditPart sourceEditPart = getEditPart(nextLinkDescriptor
                    .getSource(), domain2NotationMap);
            EditPart targetEditPart = getEditPart(nextLinkDescriptor
                    .getDestination(), domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) {
                continue;
            }
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
                    nextLinkDescriptor.getSemanticAdapter(), String
                            .valueOf(nextLinkDescriptor.getVisualID()),
                    ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost())
                            .getDiagramPreferencesHint());
            CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(
                    descriptor);
            ccr.setType(RequestConstants.REQ_CONNECTION_START);
            ccr.setSourceEditPart(sourceEditPart);
            sourceEditPart.getCommand(ccr);
            ccr.setTargetEditPart(targetEditPart);
            ccr.setType(RequestConstants.REQ_CONNECTION_END);
            Command cmd = targetEditPart.getCommand(ccr);
            if (cmd != null && cmd.canExecute()) {
                executeCommand(cmd);
                IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
                if (viewAdapter != null) {
                    adapters.add(viewAdapter);
                }
            }
        }
        return adapters;
    }

    /**
     * @generated
     */
    private EditPart getEditPart(EObject domainModelElement,
            Map domain2NotationMap) {
        View view = (View) domain2NotationMap.get(domainModelElement);
        if (view != null) {
            return (EditPart) getHost().getViewer().getEditPartRegistry().get(
                    view);
        }
        return null;
    }
}
