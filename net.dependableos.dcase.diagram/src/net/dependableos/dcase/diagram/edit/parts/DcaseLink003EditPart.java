/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.policies.DcaseLink003ItemSemanticEditPolicy;
import net.dependableos.dcase.diagram.ui.editpolicies.BasicLinkOpenEditPolicy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class DcaseLink003EditPart extends DcaseLinkEditPart implements
        ITreeBranchEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3003;

    /**
     * @generated
     */
    public DcaseLink003EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new DcaseLink003ItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.OPEN_ROLE,
                new BasicLinkOpenEditPolicy());
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DcaseLink003Userdef001DescUserdef00EditPart) {
            ((DcaseLink003Userdef001DescUserdef00EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureDcaseLink003DescFigure());
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
        if (childEditPart instanceof DcaseLink003Userdef001DescUserdef00EditPart) {
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
        return new DcaseLink003Figure();
    }

    /**
     * @generated
     */
    public DcaseLink003Figure getPrimaryShape() {
        return (DcaseLink003Figure) getFigure();
    }

    /**
     * @generated
     */
    public class DcaseLink003Figure extends PolylineConnectionEx {

        /**
         * @generated
         */
        private WrappingLabel fFigureDcaseLink003DescFigure;
        
        /**
         * @generated
         */
        public DcaseLink003Figure() {

            this.setLineWidth(1);
            createContents();

            //setSourceDecoration(createDecoration());
            setTargetDecoration(createDecoration());

        }

        /**
         * @generated NOT
         */
        private void createContents() {

        	fFigureDcaseLink003DescFigure = new WrappingLabel();
            
            // sets the label text should wrap.
            fFigureDcaseLink003DescFigure.setTextWrap(true);
            
            fFigureDcaseLink003DescFigure.setText("");

            this.add(fFigureDcaseLink003DescFigure);

        }

        /**
         * @generated NOT
         */
        private RotatableDecoration createDecoration() {
            PolygonDecoration df = new PolygonDecoration();
            df.setLineWidth(1);
            PointList list = new PointList();
            list.addPoint(0, 0);
            list.addPoint(-1, 1);
            list.addPoint(0, 0);
            list.addPoint(-1, -1);
            list.addPoint(0, 0);
            list.addPoint(-1, 0);
            list.addPoint(-2, 1);
            list.addPoint(-1, 0);
            list.addPoint(-2, -1);
            list.addPoint(-1, 0);
            df.setTemplate(list);
            
            return df;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureDcaseLink003DescFigure() {
            return fFigureDcaseLink003DescFigure;
        }

    }

    /**
     * {@inheritDoc}
     */
    protected void setForegroundColor(Color color) {
    	DcaseLink003Figure figure = getPrimaryShape();
    	if (figure != null) {
    		figure.setForegroundColor(color);
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    protected void setLineWidth(int width) {
    	DcaseLink003Figure figure = getPrimaryShape();
    	if (figure != null) {
    		figure.setLineWidth(width);
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
    	refreshLineColor();
    }

    /**
     * {@inheritDoc}
     */
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("PrimaryView"); //$NON-NLS-1$
    }

    /**
     * Handles the event for changing Attachment.
     * @param event the event.
     */
    protected void handleNotificationEvent(Notification event) {
    	refreshLineColor();
    	super.handleNotificationEvent(event);
    }

    private void refreshLineColor() {
    	Object obj = getModel();
    	if(obj instanceof View) {
    		EObject eobj = ((View)obj).getElement();
    		if (eobj instanceof BasicLink) {
    			String userdef012 = ((BasicLink)eobj).getUserdef012();
    			if (userdef012 != null && userdef012.length() > 0) {
    				setForegroundColor(ColorConstants.black);
    				//setLineWidth(1);
    			} else {
    				setForegroundColor(ColorConstants.blue);
    				//setLineWidth(1);
            	}
            }
    	}
    }
}
