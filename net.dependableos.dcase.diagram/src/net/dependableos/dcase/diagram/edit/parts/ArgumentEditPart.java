/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_EDIT_CANVAS_MARGIN_BOTTOM;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_EDIT_CANVAS_MARGIN_LEFT;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_EDIT_CANVAS_MARGIN_RIGHT;
import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DIAGRAM_EDIT_CANVAS_MARGIN_TOP;

import java.util.List;
import java.util.HashSet;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.util.NumberUtil;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.SetParametersActionFilter;
import net.dependableos.dcase.diagram.edit.policies.ArgumentCanonicalEditPolicy;
import net.dependableos.dcase.diagram.edit.policies.ArgumentItemSemanticEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IActionFilter;

/**
 * @generated
 */
public class ArgumentEditPart extends DiagramEditPart {

    /**
     * @generated
     */
    public final static String MODEL_ID = "Dcase"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final int VISUAL_ID = 79;

    // AUTO_GENERATED:END

    /**
     * the default value of the margin.
     */
    private static final int DEFAULT_CANVAS_MARGIN = 0;

    /**
     * the value of the top margin.
     */
    private static final int CANVAS_MARGIN_TOP = getMargin(DIAGRAM_EDIT_CANVAS_MARGIN_TOP);

    /**
     * the value of the left margin.
     */
    private static final int CANVAS_MARGIN_LEFT = getMargin(DIAGRAM_EDIT_CANVAS_MARGIN_LEFT);

    /**
     * the value of the bottom margin.
     */
    private static final int CANVAS_MARGIN_BOTTOM = getMargin(DIAGRAM_EDIT_CANVAS_MARGIN_BOTTOM);

    /**
     * the value of the right margin.
     */
    private static final int CANVAS_MARGIN_RIGHT = getMargin(DIAGRAM_EDIT_CANVAS_MARGIN_RIGHT);

    /**
     * Returns the value of the margin.
     * 
     * @param propertyKey the key of the property.
     * @return the value of the margin.
     */
    private static int getMargin(String propertyKey) {

        // gets the value from system properties.
        int marginValue = NumberUtil.parseIntWithDefault(PropertyUtil
                .getSystemProperty(propertyKey), DEFAULT_CANVAS_MARGIN);

        // returns the default value if specified value is invalid. 
        if (marginValue >= 0) {
            return marginValue;
        } else {
            return DEFAULT_CANVAS_MARGIN;
        }
    }

    /**
     * Allocates a ArgumentEditPart object and initializes it.
     * 
     * @generated NOT
     * @param view  the view controlled by this edit part
     */
    public ArgumentEditPart(View view) {
        super(view);

        // sets the margins.
        IFigure figure = getFigure();
        MarginBorder border = new MarginBorder(CANVAS_MARGIN_TOP,
                CANVAS_MARGIN_LEFT, CANVAS_MARGIN_BOTTOM, CANVAS_MARGIN_RIGHT);
        figure.setBorder(border);

    }

    // AUTO_GENERATED:START

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new ArgumentItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
                new ArgumentCanonicalEditPolicy());
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
    }

    // AUTO_GENERATED:END

    /**
     * {@inheritDoc}
     */
    //@SuppressWarnings("unchecked")
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class) {
            return SetParametersActionFilter.getSingleton();
        }
        return super.getAdapter(adapter);
    }

    // AUTO_GENERATED:END
    
    /**
     * {@inheritDoc}
     */
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
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
    	if(event.getEventType() == Notification.SET) {
    		Object feature = event.getFeature();
    		if(feature instanceof EAttribute) {
    			EAttribute attr = (EAttribute)feature;
            	Object nobj = event.getNotifier();
            	if(nobj instanceof Argument) {
            		if(attr.getName().equals("parameterVals")) { //$NON-NLS-1$
            			notifyRootNodes();
            		}
            	}
    		}
    	}
    	super.handleNotificationEvent(event);
    }
    
    /**
     * Notifies to root nodes.
     */
    public void notifyRootNodes() {
    	for(Object obj : this.getChildren()) {
    		if(! (obj instanceof DcaseNodeEditPart)) {
    			continue;
    		}
    		// notify to root nodes.
    		List list = ((DcaseNodeEditPart)obj).getTargetConnections();
    		if(list == null || list.size() == 0) {
    			HashSet<String> uuidSet = new HashSet<String>();
    			((DcaseNodeEditPart)obj).notifyParameters(uuidSet);
    		}
    	}
    }

}
