/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.LINK_LINE_DASH;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.policies.DcaseLink002ItemSemanticEditPolicy;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @generated
 */
public class DcaseLink002EditPart extends DcaseLinkEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3002;

    /**
     * @generated
     */
    public DcaseLink002EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new DcaseLink002ItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DcaseLink002Userdef001DescUserdef00EditPart) {
            ((DcaseLink002Userdef001DescUserdef00EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureDcaseLink002DescFigure());
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * @generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DcaseLink002Userdef001DescUserdef00EditPart) {
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */

    protected Connection createConnectionFigure() {
        return new DcaseLink002Figure();
    }

    /**
     * @generated
     */
    public DcaseLink002Figure getPrimaryShape() {
        return (DcaseLink002Figure) getFigure();
    }

    /**
     * @generated
     */
    public class DcaseLink002Figure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureDcaseLink002DescFigure;

        /**
         * @generated NOT
         */
        public DcaseLink002Figure() {
            this.setLineWidth(1);
            
            createContents();
            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated NOT
         */
        private void createContents() {

            fFigureDcaseLink002DescFigure = new WrappingLabel();
            
            // sets the label text should wrap.
            fFigureDcaseLink002DescFigure.setTextWrap(true);
            
            fFigureDcaseLink002DescFigure.setText("");

            this.add(fFigureDcaseLink002DescFigure);

        }

        /**
         * @generated
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setLineWidth(1);
            
            // sets a triangular background color in white
            df.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureDcaseLink002DescFigure() {
            return fFigureDcaseLink002DescFigure;
        }

    }

}
