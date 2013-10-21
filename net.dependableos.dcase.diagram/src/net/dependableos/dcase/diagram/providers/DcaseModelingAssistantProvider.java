/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;
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
import net.dependableos.dcase.diagram.part.Messages;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class DcaseModelingAssistantProvider extends ModelingAssistantProvider {

    /**
     * @generated
     */
    public List getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) host
                .getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof ArgumentEditPart) {
            ArrayList types = new ArrayList(15);
            types.add(DcaseElementTypes.Goal_1001);
            types.add(DcaseElementTypes.Evidence_1003);
            types.add(DcaseElementTypes.Strategy_1002);
            types.add(DcaseElementTypes.Context_1006);
            types.add(DcaseElementTypes.Justification_1007);
            types.add(DcaseElementTypes.Userdef004_1013);   // Assumption
            types.add(DcaseElementTypes.Undeveloped_1005);
            types.add(DcaseElementTypes.Userdef005_1014);   // Module
            types.add(DcaseElementTypes.Userdef006_1015);   // Contract

            types.add(DcaseElementTypes.Monitor_1004);
            types.add(DcaseElementTypes.System_1008);
            types.add(DcaseElementTypes.Policy_1009);
            types.add(DcaseElementTypes.Userdef001_1010);
            types.add(DcaseElementTypes.Userdef002_1011);
            types.add(DcaseElementTypes.Userdef003_1012);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnSource(IAdaptable source) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof GoalEditPart) {
            return ((GoalEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof StrategyEditPart) {
            return ((StrategyEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof EvidenceEditPart) {
            return ((EvidenceEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof MonitorEditPart) {
            return ((MonitorEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof UndevelopedEditPart) {
            return ((UndevelopedEditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof ContextEditPart) {
            return ((ContextEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof JustificationEditPart) {
            return ((JustificationEditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof SystemEditPart) {
            return ((SystemEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof PolicyEditPart) {
            return ((PolicyEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef001EditPart) {
            return ((Userdef001EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef002EditPart) {
            return ((Userdef002EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef003EditPart) {
            return ((Userdef003EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef004EditPart) {
            return ((Userdef004EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef005EditPart) {
            return ((Userdef005EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Userdef006EditPart) {
            return ((Userdef006EditPart) sourceEditPart)
                    .getMARelTypesOnSource();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof GoalEditPart) {
            return ((GoalEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof StrategyEditPart) {
            return ((StrategyEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            return ((EvidenceEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof MonitorEditPart) {
            return ((MonitorEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            return ((UndevelopedEditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof ContextEditPart) {
            return ((ContextEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof JustificationEditPart) {
            return ((JustificationEditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof SystemEditPart) {
            return ((SystemEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof PolicyEditPart) {
            return ((PolicyEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            return ((Userdef001EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            return ((Userdef002EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            return ((Userdef003EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            return ((Userdef004EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            return ((Userdef005EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            return ((Userdef006EditPart) targetEditPart)
                    .getMARelTypesOnTarget();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnSourceAndTarget(IAdaptable source,
            IAdaptable target) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof GoalEditPart) {
            return ((GoalEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof StrategyEditPart) {
            return ((StrategyEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof EvidenceEditPart) {
            return ((EvidenceEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof MonitorEditPart) {
            return ((MonitorEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof UndevelopedEditPart) {
            return ((UndevelopedEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof ContextEditPart) {
            return ((ContextEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof JustificationEditPart) {
            return ((JustificationEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof SystemEditPart) {
            return ((SystemEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof PolicyEditPart) {
            return ((PolicyEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef001EditPart) {
            return ((Userdef001EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef002EditPart) {
            return ((Userdef002EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef003EditPart) {
            return ((Userdef003EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef004EditPart) {
            return ((Userdef004EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef005EditPart) {
            return ((Userdef005EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Userdef006EditPart) {
            return ((Userdef006EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getTypesForSource(IAdaptable target,
            IElementType relationshipType) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof GoalEditPart) {
            return ((GoalEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof StrategyEditPart) {
            return ((StrategyEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            return ((EvidenceEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof MonitorEditPart) {
            return ((MonitorEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            return ((UndevelopedEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof ContextEditPart) {
            return ((ContextEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof JustificationEditPart) {
            return ((JustificationEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof SystemEditPart) {
            return ((SystemEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof PolicyEditPart) {
            return ((PolicyEditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            return ((Userdef001EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            return ((Userdef002EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            return ((Userdef003EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            return ((Userdef004EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            return ((Userdef005EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            return ((Userdef006EditPart) targetEditPart)
                    .getMATypesForSource(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getTypesForTarget(IAdaptable source,
            IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof GoalEditPart) {
            return ((GoalEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof StrategyEditPart) {
            return ((StrategyEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof EvidenceEditPart) {
            return ((EvidenceEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof MonitorEditPart) {
            return ((MonitorEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof UndevelopedEditPart) {
            return ((UndevelopedEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof ContextEditPart) {
            return ((ContextEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof JustificationEditPart) {
            return ((JustificationEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof SystemEditPart) {
            return ((SystemEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof PolicyEditPart) {
            return ((PolicyEditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef001EditPart) {
            return ((Userdef001EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef002EditPart) {
            return ((Userdef002EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef003EditPart) {
            return ((Userdef003EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef004EditPart) {
            return ((Userdef004EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef005EditPart) {
            return ((Userdef005EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Userdef006EditPart) {
            return ((Userdef006EditPart) sourceEditPart)
                    .getMATypesForTarget(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public EObject selectExistingElementForSource(IAdaptable target,
            IElementType relationshipType) {
        return selectExistingElement(target, getTypesForSource(target,
                relationshipType));
    }

    /**
     * @generated
     */
    public EObject selectExistingElementForTarget(IAdaptable source,
            IElementType relationshipType) {
        return selectExistingElement(source, getTypesForTarget(source,
                relationshipType));
    }

    /**
     * @generated
     */
    protected EObject selectExistingElement(IAdaptable host, Collection types) {
        if (types.isEmpty()) {
            return null;
        }
        IGraphicalEditPart editPart = (IGraphicalEditPart) host
                .getAdapter(IGraphicalEditPart.class);
        if (editPart == null) {
            return null;
        }
        Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
        Collection elements = new HashSet();
        for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
            EObject element = (EObject) it.next();
            if (isApplicableElement(element, types)) {
                elements.add(element);
            }
        }
        if (elements.isEmpty()) {
            return null;
        }
        return selectElement((EObject[]) elements.toArray(new EObject[elements
                .size()]));
    }

    /**
     * @generated
     */
    protected boolean isApplicableElement(EObject element, Collection types) {
        IElementType type = ElementTypeRegistry.getInstance().getElementType(
                element);
        return types.contains(type);
    }

    /**
     * @generated
     */
    protected EObject selectElement(EObject[] elements) {
        Shell shell = Display.getCurrent().getActiveShell();
        ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
                DcaseDiagramEditorPlugin.getInstance()
                        .getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(
                shell, labelProvider);
        dialog.setMessage(Messages.DcaseModelingAssistantProviderMessage);
        dialog.setTitle(Messages.DcaseModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements(elements);
        EObject selected = null;
        if (dialog.open() == Window.OK) {
            selected = (EObject) dialog.getFirstResult();
        }
        return selected;
    }
}
