/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;


import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.policies.DcaseLink001ItemSemanticEditPolicy;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcaseLink001EditPart extends DcaseLinkEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3001;

    /**
     * @generated
     */
    public DcaseLink001EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new DcaseLink001ItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DcaseLink001Userdef001DescUserdef00EditPart) {
            ((DcaseLink001Userdef001DescUserdef00EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureDcaseLink001DescFigure());
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
        if (childEditPart instanceof DcaseLink001Userdef001DescUserdef00EditPart) {
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
        return new DcaseLink001Figure();
    }

    /**
     * @generated
     */
    public DcaseLink001Figure getPrimaryShape() {
        return (DcaseLink001Figure) getFigure();
    }

    /**
     * @generated
     */
    public class DcaseLink001Figure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureDcaseLink001DescFigure;

        /**
         * @generated
         */
        public DcaseLink001Figure() {
            this.setLineWidth(1);

            createContents();
            setTargetDecoration(createTargetDecoration());
        }

        /**
         * @generated NOT
         */
        private void createContents() {

            fFigureDcaseLink001DescFigure = new WrappingLabel();
            
            // sets the label text should wrap.
            fFigureDcaseLink001DescFigure.setTextWrap(true);
            
            fFigureDcaseLink001DescFigure.setText("");

            this.add(fFigureDcaseLink001DescFigure);

        }

        /**
         * @generated NOT
         */
        private RotatableDecoration createTargetDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setLineWidth(1);
            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureDcaseLink001DescFigure() {
            return fFigureDcaseLink001DescFigure;
        }

    }

}
