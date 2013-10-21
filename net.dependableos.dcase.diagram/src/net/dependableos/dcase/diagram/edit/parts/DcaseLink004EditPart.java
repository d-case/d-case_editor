/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.LINK_LINE_DASH;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.policies.DcaseLink004ItemSemanticEditPolicy;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcaseLink004EditPart extends DcaseLinkEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3004;

    /**
     * @generated
     */
    public DcaseLink004EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new DcaseLink004ItemSemanticEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DcaseLink004Userdef001DescUserdef00EditPart) {
            ((DcaseLink004Userdef001DescUserdef00EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureDcaseLink004DescFigure());
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
        if (childEditPart instanceof DcaseLink004Userdef001DescUserdef00EditPart) {
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
        return new DcaseLink004Figure();
    }

    /**
     * @generated
     */
    public DcaseLink004Figure getPrimaryShape() {
        return (DcaseLink004Figure) getFigure();
    }

    /**
     * @generated
     */
    public class DcaseLink004Figure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureDcaseLink004DescFigure;

        /**
         * @generated NOT
         */
        public DcaseLink004Figure() {
            this.setLineWidth(1);
            this.setLineStyle(Graphics.LINE_CUSTOM);

            // sets the line dash style.
            this.setLineDash(LINK_LINE_DASH);

            createContents();
        }

        /**
         * @generated NOT
         */
        private void createContents() {

            fFigureDcaseLink004DescFigure = new WrappingLabel();
            
            // sets the label text should wrap.
            fFigureDcaseLink004DescFigure.setTextWrap(true);
            
            fFigureDcaseLink004DescFigure.setText("");

            this.add(fFigureDcaseLink004DescFigure);

        }

        /**
         * @generated
         */
        public WrappingLabel getFigureDcaseLink004DescFigure() {
            return fFigureDcaseLink004DescFigure;
        }

    }

}
