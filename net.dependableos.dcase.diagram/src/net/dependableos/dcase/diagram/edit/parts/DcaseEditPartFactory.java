/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;


import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class DcaseEditPartFactory implements EditPartFactory {

    /**
     * @generated
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View) model;
            switch (DcaseVisualIDRegistry.getVisualID(view)) {

                case ArgumentEditPart.VISUAL_ID:
                    return new ArgumentEditPart(view);

                case GoalEditPart.VISUAL_ID:
                    return new GoalEditPart(view);

                case GoalNameEditPart.VISUAL_ID:
                    return new GoalNameEditPart(view);

                case GoalUserdef001EditPart.VISUAL_ID:
                    return new GoalUserdef001EditPart(view);

                case GoalDescEditPart.VISUAL_ID:
                    return new GoalDescEditPart(view);

                case GoalUserdef002EditPart.VISUAL_ID:
                    return new GoalUserdef002EditPart(view);

                case GoalAttachmentEditPart.VISUAL_ID:
                    return new GoalAttachmentEditPart(view);

                case GoalResponsibilityEditPart.VISUAL_ID:
                    return new GoalResponsibilityEditPart(view);

                case StrategyEditPart.VISUAL_ID:
                    return new StrategyEditPart(view);

                case StrategyNameEditPart.VISUAL_ID:
                    return new StrategyNameEditPart(view);

                case StrategyResponsibilityEditPart.VISUAL_ID:
                    return new StrategyResponsibilityEditPart(view);

                case StrategyUserdef001EditPart.VISUAL_ID:
                    return new StrategyUserdef001EditPart(view);

                case StrategyDescEditPart.VISUAL_ID:
                    return new StrategyDescEditPart(view);

                case StrategyUserdef002EditPart.VISUAL_ID:
                    return new StrategyUserdef002EditPart(view);

                case EvidenceEditPart.VISUAL_ID:
                    return new EvidenceEditPart(view);

                case EvidenceNameEditPart.VISUAL_ID:
                    return new EvidenceNameEditPart(view);

                case EvidenceUserdef001EditPart.VISUAL_ID:
                    return new EvidenceUserdef001EditPart(view);

                case EvidenceDescEditPart.VISUAL_ID:
                    return new EvidenceDescEditPart(view);

                case EvidenceUserdef002EditPart.VISUAL_ID:
                    return new EvidenceUserdef002EditPart(view);

                case EvidenceResponsibilityEditPart.VISUAL_ID:
                    return new EvidenceResponsibilityEditPart(view);

                case MonitorEditPart.VISUAL_ID:
                    return new MonitorEditPart(view);

                case MonitorNameEditPart.VISUAL_ID:
                    return new MonitorNameEditPart(view);

                case MonitorUserdef001EditPart.VISUAL_ID:
                    return new MonitorUserdef001EditPart(view);

                case MonitorDescEditPart.VISUAL_ID:
                    return new MonitorDescEditPart(view);

                case MonitorUserdef002EditPart.VISUAL_ID:
                    return new MonitorUserdef002EditPart(view);

                case MonitorResponsibilityEditPart.VISUAL_ID:
                    return new MonitorResponsibilityEditPart(view);

                case UndevelopedEditPart.VISUAL_ID:
                    return new UndevelopedEditPart(view);

                case UndevelopedNameEditPart.VISUAL_ID:
                    return new UndevelopedNameEditPart(view);

                case UndevelopedUserdef001EditPart.VISUAL_ID:
                    return new UndevelopedUserdef001EditPart(view);

                case UndevelopedDescEditPart.VISUAL_ID:
                    return new UndevelopedDescEditPart(view);

                case UndevelopedUserdef002EditPart.VISUAL_ID:
                    return new UndevelopedUserdef002EditPart(view);

                case UndevelopedResponsibilityEditPart.VISUAL_ID:
                    return new UndevelopedResponsibilityEditPart(view);

                case ContextEditPart.VISUAL_ID:
                    return new ContextEditPart(view);

                case ContextNameEditPart.VISUAL_ID:
                    return new ContextNameEditPart(view);

                case ContextUserdef001EditPart.VISUAL_ID:
                    return new ContextUserdef001EditPart(view);

                case ContextDescEditPart.VISUAL_ID:
                    return new ContextDescEditPart(view);

                case ContextUserdef002EditPart.VISUAL_ID:
                    return new ContextUserdef002EditPart(view);

                case ContextResponsibilityEditPart.VISUAL_ID:
                    return new ContextResponsibilityEditPart(view);

                case JustificationEditPart.VISUAL_ID:
                    return new JustificationEditPart(view);

                case JustificationNameEditPart.VISUAL_ID:
                    return new JustificationNameEditPart(view);

                case JustificationUserdef001EditPart.VISUAL_ID:
                    return new JustificationUserdef001EditPart(view);

                case JustificationDescEditPart.VISUAL_ID:
                    return new JustificationDescEditPart(view);

                case JustificationUserdef002EditPart.VISUAL_ID:
                    return new JustificationUserdef002EditPart(view);

                case JustificationResponsibilityEditPart.VISUAL_ID:
                    return new JustificationResponsibilityEditPart(view);

                case SystemEditPart.VISUAL_ID:
                    return new SystemEditPart(view);

                case SystemNameEditPart.VISUAL_ID:
                    return new SystemNameEditPart(view);

                case SystemUserdef001EditPart.VISUAL_ID:
                    return new SystemUserdef001EditPart(view);

                case SystemDescEditPart.VISUAL_ID:
                    return new SystemDescEditPart(view);

                case SystemUserdef002EditPart.VISUAL_ID:
                    return new SystemUserdef002EditPart(view);

                case SystemResponsibilityEditPart.VISUAL_ID:
                    return new SystemResponsibilityEditPart(view);

                case PolicyEditPart.VISUAL_ID:
                    return new PolicyEditPart(view);

                case PolicyNameEditPart.VISUAL_ID:
                    return new PolicyNameEditPart(view);

                case PolicyUserdef001EditPart.VISUAL_ID:
                    return new PolicyUserdef001EditPart(view);

                case PolicyDescEditPart.VISUAL_ID:
                    return new PolicyDescEditPart(view);

                case PolicyUserdef002EditPart.VISUAL_ID:
                    return new PolicyUserdef002EditPart(view);

                case PolicyResponsibilityEditPart.VISUAL_ID:
                    return new PolicyResponsibilityEditPart(view);

                case Userdef001EditPart.VISUAL_ID:
                    return new Userdef001EditPart(view);

                case Userdef001NameEditPart.VISUAL_ID:
                    return new Userdef001NameEditPart(view);

                case Userdef001Userdef001EditPart.VISUAL_ID:
                    return new Userdef001Userdef001EditPart(view);
                    
                case Userdef001AttachmentEditPart.VISUAL_ID:
                    return new Userdef001AttachmentEditPart(view);

                case Userdef001DescEditPart.VISUAL_ID:
                    return new Userdef001DescEditPart(view);

                case Userdef001Userdef002EditPart.VISUAL_ID:
                    return new Userdef001Userdef002EditPart(view);

                case Userdef001ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef001ResponsibilityEditPart(view);

                case Userdef002EditPart.VISUAL_ID:
                    return new Userdef002EditPart(view);

                case Userdef002NameEditPart.VISUAL_ID:
                    return new Userdef002NameEditPart(view);

                case Userdef002Userdef001EditPart.VISUAL_ID:
                    return new Userdef002Userdef001EditPart(view);

                case Userdef002DescEditPart.VISUAL_ID:
                    return new Userdef002DescEditPart(view);

                case Userdef002Userdef002EditPart.VISUAL_ID:
                    return new Userdef002Userdef002EditPart(view);

                case Userdef002ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef002ResponsibilityEditPart(view);

                case Userdef003EditPart.VISUAL_ID:
                    return new Userdef003EditPart(view);

                case Userdef003NameEditPart.VISUAL_ID:
                    return new Userdef003NameEditPart(view);

                case Userdef003Userdef001EditPart.VISUAL_ID:
                    return new Userdef003Userdef001EditPart(view);

                case Userdef003DescEditPart.VISUAL_ID:
                    return new Userdef003DescEditPart(view);

                case Userdef003Userdef002EditPart.VISUAL_ID:
                    return new Userdef003Userdef002EditPart(view);

                case Userdef003ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef003ResponsibilityEditPart(view);

                case Userdef004EditPart.VISUAL_ID:
                    return new Userdef004EditPart(view);

                case Userdef004NameEditPart.VISUAL_ID:
                    return new Userdef004NameEditPart(view);

                case Userdef004Userdef001EditPart.VISUAL_ID:
                    return new Userdef004Userdef001EditPart(view);

                case Userdef004DescEditPart.VISUAL_ID:
                    return new Userdef004DescEditPart(view);

                case Userdef004Userdef002EditPart.VISUAL_ID:
                    return new Userdef004Userdef002EditPart(view);

                case Userdef004ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef004ResponsibilityEditPart(view);

                case Userdef005EditPart.VISUAL_ID:
                    return new Userdef005EditPart(view);

                case Userdef005NameEditPart.VISUAL_ID:
                    return new Userdef005NameEditPart(view);

                case Userdef005Userdef001EditPart.VISUAL_ID:
                    return new Userdef005Userdef001EditPart(view);

                case Userdef005DescEditPart.VISUAL_ID:
                    return new Userdef005DescEditPart(view);

                case Userdef005Userdef002EditPart.VISUAL_ID:
                    return new Userdef005Userdef002EditPart(view);

                case Userdef005AttachmentEditPart.VISUAL_ID:
                    return new Userdef005AttachmentEditPart(view);

                case Userdef005ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef005ResponsibilityEditPart(view);

                case Userdef006EditPart.VISUAL_ID:
                    return new Userdef006EditPart(view);

                case Userdef006NameEditPart.VISUAL_ID:
                    return new Userdef006NameEditPart(view);

                case Userdef006Userdef001EditPart.VISUAL_ID:
                    return new Userdef006Userdef001EditPart(view);

                case Userdef006DescEditPart.VISUAL_ID:
                    return new Userdef006DescEditPart(view);

                case Userdef006Userdef002EditPart.VISUAL_ID:
                    return new Userdef006Userdef002EditPart(view);

                case Userdef006ResponsibilityEditPart.VISUAL_ID:
                    return new Userdef006ResponsibilityEditPart(view);

                case DcaseLink001EditPart.VISUAL_ID:
                    return new DcaseLink001EditPart(view);

                case DcaseLink001Userdef001DescUserdef00EditPart.VISUAL_ID:
                    return new DcaseLink001Userdef001DescUserdef00EditPart(view);

                case DcaseLink002EditPart.VISUAL_ID:
                    return new DcaseLink002EditPart(view);

                case DcaseLink002Userdef001DescUserdef00EditPart.VISUAL_ID:
                    return new DcaseLink002Userdef001DescUserdef00EditPart(view);

                case DcaseLink003EditPart.VISUAL_ID:
                    return new DcaseLink003EditPart(view);

                case DcaseLink003Userdef001DescUserdef00EditPart.VISUAL_ID:
                    return new DcaseLink003Userdef001DescUserdef00EditPart(view);

                case DcaseLink004EditPart.VISUAL_ID:
                    return new DcaseLink004EditPart(view);

                case DcaseLink004Userdef001DescUserdef00EditPart.VISUAL_ID:
                    return new DcaseLink004Userdef001DescUserdef00EditPart(view);

            }
        }
        return createUnrecognizedEditPart(context, model);
    }

    /**
     * @generated
     */
    private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
        // Handle creation of unrecognized child node EditParts here
        return null;
    }

    /**
     * @generated
     */
    public static CellEditorLocator getTextCellEditorLocator(
            ITextAwareEditPart source) {
        if (source.getFigure() instanceof WrappingLabel)
            return new TextCellEditorLocator((WrappingLabel) source.getFigure());
        else {
            return new LabelCellEditorLocator((Label) source.getFigure());
        }
    }

    /**
     * @generated
     */
    static private class TextCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private WrappingLabel wrapLabel;

        /**
         * @generated
         */
        public TextCellEditorLocator(WrappingLabel wrapLabel) {
            this.wrapLabel = wrapLabel;
        }

        /**
         * @generated
         */
        public WrappingLabel getWrapLabel() {
            return wrapLabel;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getWrapLabel().getTextBounds().getCopy();
            getWrapLabel().translateToAbsolute(rect);
            if (getWrapLabel().isTextWrapOn()
                    && getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width,
                        SWT.DEFAULT)));
            } else {
                int avr = FigureUtilities.getFontMetrics(text.getFont())
                        .getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                        SWT.DEFAULT)).expand(avr * 2, 0));
            }
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    /**
     * @generated
     */
    private static class LabelCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private Label label;

        /**
         * @generated
         */
        public LabelCellEditorLocator(Label label) {
            this.label = label;
        }

        /**
         * @generated
         */
        public Label getLabel() {
            return label;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getLabel().getTextBounds().getCopy();
            getLabel().translateToAbsolute(rect);
            int avr = FigureUtilities.getFontMetrics(text.getFont())
                    .getAverageCharWidth();
            rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                    SWT.DEFAULT)).expand(avr * 2, 0));
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}
