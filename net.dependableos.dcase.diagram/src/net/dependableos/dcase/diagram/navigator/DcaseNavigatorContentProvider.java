/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


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
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.part.Messages;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class DcaseNavigatorContentProvider implements ICommonContentProvider {

    /**
     * @generated
     */
    private static final Object[] EMPTY_ARRAY = new Object[0];

    /**
     * @generated
     */
    private Viewer myViewer;

    /**
     * @generated
     */
    private AdapterFactoryEditingDomain myEditingDomain;

    /**
     * @generated
     */
    private WorkspaceSynchronizer myWorkspaceSynchronizer;

    /**
     * @generated
     */
    private Runnable myViewerRefreshRunnable;

    /**
     * @generated
     */
    public DcaseNavigatorContentProvider() {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                .createEditingDomain();
        myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
        myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
            public Object get(Object key) {
                if (!containsKey(key)) {
                    put(key, Boolean.TRUE);
                }
                return super.get(key);
            }
        });
        myViewerRefreshRunnable = new Runnable() {
            public void run() {
                if (myViewer != null) {
                    myViewer.refresh();
                }
            }
        };
        myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
                new WorkspaceSynchronizer.Delegate() {
                    public void dispose() {
                    }

                    public boolean handleResourceChanged(final Resource resource) {
                        for (Iterator it = myEditingDomain.getResourceSet()
                                .getResources().iterator(); it.hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(
                                    myViewerRefreshRunnable);
                        }
                        return true;
                    }

                    public boolean handleResourceDeleted(Resource resource) {
                        for (Iterator it = myEditingDomain.getResourceSet()
                                .getResources().iterator(); it.hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(
                                    myViewerRefreshRunnable);
                        }
                        return true;
                    }

                    public boolean handleResourceMoved(Resource resource,
                            final URI newURI) {
                        for (Iterator it = myEditingDomain.getResourceSet()
                                .getResources().iterator(); it.hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(
                                    myViewerRefreshRunnable);
                        }
                        return true;
                    }
                });
    }

    /**
     * @generated
     */
    public void dispose() {
        myWorkspaceSynchronizer.dispose();
        myWorkspaceSynchronizer = null;
        myViewerRefreshRunnable = null;
        for (Iterator it = myEditingDomain.getResourceSet().getResources()
                .iterator(); it.hasNext();) {
            Resource resource = (Resource) it.next();
            resource.unload();
        }
        ((TransactionalEditingDomain) myEditingDomain).dispose();
        myEditingDomain = null;
    }

    /**
     * @generated
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        myViewer = viewer;
    }

    /**
     * @generated
     */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
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
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IFile) {
            IFile file = (IFile) parentElement;
            URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
                    .toString(), true);
            Resource resource = myEditingDomain.getResourceSet().getResource(
                    fileURI, true);
            Collection result = new ArrayList();
            result.addAll(createNavigatorItems(selectViewsByType(resource
                    .getContents(), ArgumentEditPart.MODEL_ID), file, false));
            return result.toArray();
        }

        if (parentElement instanceof DcaseNavigatorGroup) {
            DcaseNavigatorGroup group = (DcaseNavigatorGroup) parentElement;
            return group.getChildren();
        }

        if (parentElement instanceof DcaseNavigatorItem) {
            DcaseNavigatorItem navigatorItem = (DcaseNavigatorItem) parentElement;
            if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
                return EMPTY_ARRAY;
            }
            return getViewChildren(navigatorItem.getView(), parentElement);
        }

        return EMPTY_ARRAY;
    }

    /**
     * @generated
     */
    private Object[] getViewChildren(View view, Object parentElement) {
        switch (DcaseVisualIDRegistry.getVisualID(view)) {

            case ArgumentEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup links = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Argument_79_links,
                        "icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getChildrenByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(StrategyEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(EvidenceEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(MonitorEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(UndevelopedEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(ContextEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(JustificationEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry.getType(SystemEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry.getType(PolicyEditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef001EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef002EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef003EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef004EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef005EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getChildrenByType(Collections.singleton(view),
                        DcaseVisualIDRegistry
                                .getType(Userdef006EditPart.VISUAL_ID));
                result.addAll(createNavigatorItems(connectedViews,
                        parentElement, false));
                connectedViews = getDiagramLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                links.addChildren(createNavigatorItems(connectedViews, links,
                        false));
                connectedViews = getDiagramLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                links.addChildren(createNavigatorItems(connectedViews, links,
                        false));
                connectedViews = getDiagramLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                links.addChildren(createNavigatorItems(connectedViews, links,
                        false));
                connectedViews = getDiagramLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                links.addChildren(createNavigatorItems(connectedViews, links,
                        false));
                if (!links.isEmpty()) {
                    result.add(links);
                }
                return result.toArray();
            }

            case GoalEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Goal_1001_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Goal_1001_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case StrategyEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Strategy_1002_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Strategy_1002_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case EvidenceEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Evidence_1003_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Evidence_1003_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case MonitorEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Monitor_1004_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Monitor_1004_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case UndevelopedEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Undeveloped_1005_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Undeveloped_1005_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case ContextEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Context_1006_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Context_1006_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case JustificationEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Justification_1007_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Justification_1007_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case SystemEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_System_1008_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_System_1008_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case PolicyEditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Policy_1009_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Policy_1009_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef001EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef001_1010_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef001_1010_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef002EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef002_1011_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef002_1011_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef003EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef003_1012_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef003_1012_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef004EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef004_1013_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef004_1013_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef005EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef005_1014_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef005_1014_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case Userdef006EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup incominglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef006_1015_incominglinks,
                        "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup outgoinglinks = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_Userdef006_1015_outgoinglinks,
                        "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink001EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink002EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink003EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                connectedViews = getIncomingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                incominglinks.addChildren(createNavigatorItems(connectedViews,
                        incominglinks, true));
                connectedViews = getOutgoingLinksByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(DcaseLink004EditPart.VISUAL_ID));
                outgoinglinks.addChildren(createNavigatorItems(connectedViews,
                        outgoinglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }

            case DcaseLink001EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup target = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink001_3001_target,
                        "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup source = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink001_3001_source,
                        "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }

            case DcaseLink002EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup target = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink002_3002_target,
                        "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup source = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink002_3002_source,
                        "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }

            case DcaseLink003EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup target = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink003_3003_target,
                        "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup source = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink003_3003_source,
                        "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }

            case DcaseLink004EditPart.VISUAL_ID: {
                Collection result = new ArrayList();
                DcaseNavigatorGroup target = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink004_3004_target,
                        "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                DcaseNavigatorGroup source = new DcaseNavigatorGroup(
                        Messages.NavigatorGroupName_DcaseLink004_3004_source,
                        "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
                Collection connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksTargetByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                target.addChildren(createNavigatorItems(connectedViews, target,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(GoalEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(StrategyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(EvidenceEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(MonitorEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(UndevelopedEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(ContextEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(JustificationEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(SystemEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(PolicyEditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef001EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef002EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef003EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef004EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef005EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                connectedViews = getLinksSourceByType(Collections
                        .singleton(view), DcaseVisualIDRegistry
                        .getType(Userdef006EditPart.VISUAL_ID));
                source.addChildren(createNavigatorItems(connectedViews, source,
                        true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }
        }
        return EMPTY_ARRAY;
    }

    /**
     * @generated
     */
    private Collection getLinksSourceByType(Collection edges, String type) {
        Collection result = new ArrayList();
        for (Iterator it = edges.iterator(); it.hasNext();) {
            Edge nextEdge = (Edge) it.next();
            View nextEdgeSource = nextEdge.getSource();
            if (type.equals(nextEdgeSource.getType())
                    && isOwnView(nextEdgeSource)) {
                result.add(nextEdgeSource);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getLinksTargetByType(Collection edges, String type) {
        Collection result = new ArrayList();
        for (Iterator it = edges.iterator(); it.hasNext();) {
            Edge nextEdge = (Edge) it.next();
            View nextEdgeTarget = nextEdge.getTarget();
            if (type.equals(nextEdgeTarget.getType())
                    && isOwnView(nextEdgeTarget)) {
                result.add(nextEdgeTarget);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getOutgoingLinksByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getIncomingLinksByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getChildrenByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getChildren(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getDiagramLinksByType(Collection diagrams, String type) {
        Collection result = new ArrayList();
        for (Iterator it = diagrams.iterator(); it.hasNext();) {
            Diagram nextDiagram = (Diagram) it.next();
            result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection selectViewsByType(Collection views, String type) {
        Collection result = new ArrayList();
        for (Iterator it = views.iterator(); it.hasNext();) {
            View nextView = (View) it.next();
            if (type.equals(nextView.getType()) && isOwnView(nextView)) {
                result.add(nextView);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private boolean isOwnView(View view) {
        return ArgumentEditPart.MODEL_ID.equals(DcaseVisualIDRegistry
                .getModelID(view));
    }

    /**
     * @generated
     */
    private Collection createNavigatorItems(Collection views, Object parent,
            boolean isLeafs) {
        Collection result = new ArrayList();
        for (Iterator it = views.iterator(); it.hasNext();) {
            result
                    .add(new DcaseNavigatorItem((View) it.next(), parent,
                            isLeafs));
        }
        return result;
    }

    /**
     * @generated
     */
    public Object getParent(Object element) {
        if (element instanceof DcaseAbstractNavigatorItem) {
            DcaseAbstractNavigatorItem abstractNavigatorItem = (DcaseAbstractNavigatorItem) element;
            return abstractNavigatorItem.getParent();
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean hasChildren(Object element) {
        return element instanceof IFile || getChildren(element).length > 0;
    }

}
