/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NODE_MARGIN_BOTTOM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NODE_MARGIN_LEFT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NODE_MARGIN_RIGHT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NODE_MARGIN_TOP;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.NODE_MARGIN_VERTICAL;

import java.util.ArrayList;
import java.util.List;


import net.dependableos.dcase.diagram.edit.parts.custom.CustomBorderLayout;
import net.dependableos.dcase.diagram.edit.parts.custom.CustomWrappingLabel;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.SetParametersActionFilter;
import net.dependableos.dcase.diagram.edit.policies.MonitorItemSemanticEditPolicy;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IActionFilter;

/**
 * @generated
 */
public class MonitorEditPart extends DcaseNodeEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 1004;

    /**
     * @generated
     */
    protected IFigure contentPane;

    /**
     * @generated
     */
    protected IFigure primaryShape;

    /**
     * @generated
     */
    public MonitorEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new MonitorItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {

        FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

            protected Command createAddCommand(EditPart child, EditPart after) {
                return null;
            }

            protected Command createMoveChildCommand(EditPart child,
                    EditPart after) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    /**
     * @generated
     */
    protected IFigure createNodeShape() {
        MonitorFigure figure = new MonitorFigure();
        return primaryShape = figure;
    }

    /**
     * @generated
     */
    public MonitorFigure getPrimaryShape() {
        return (MonitorFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof MonitorDescEditPart) {
            ((MonitorDescEditPart) childEditPart).setLabel(getPrimaryShape()
                    .getFigureMonitorDescFigure());
            return true;
        }
        if (childEditPart instanceof MonitorNameEditPart) {
            ((MonitorNameEditPart) childEditPart).setLabel(getPrimaryShape()
                    .getFigureMonitorNameFigure());
            return true;
        }
        if (childEditPart instanceof MonitorUserdef001EditPart) {
            ((MonitorUserdef001EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureMonitorUserdef001Figure());
            return true;
        }
        if (childEditPart instanceof MonitorUserdef002EditPart) {
            ((MonitorUserdef002EditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureMonitorUserdef002Figure());
            return true;
        }
        if (childEditPart instanceof MonitorResponsibilityEditPart) {
            ((MonitorResponsibilityEditPart) childEditPart)
                    .setLabel(getPrimaryShape()
                            .getFigureMonitorResponsibilityFigure());
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof MonitorDescEditPart) {
            return true;
        }
        if (childEditPart instanceof MonitorNameEditPart) {
            return true;
        }
        if (childEditPart instanceof MonitorUserdef001EditPart) {
            return true;
        }
        if (childEditPart instanceof MonitorUserdef002EditPart) {
            return true;
        }
        if (childEditPart instanceof MonitorResponsibilityEditPart) {
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
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * @generated
     */
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        return getContentPane();
    }

    /**
     * @generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
        return result;
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */
    protected NodeFigure createNodeFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane.
     * Respects layout one may have set for generated figure.
     * @param nodeShape instance of generated figure class
     * @generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(5);
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @generated
     */
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @generated
     */
    protected void setForegroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setForegroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setBackgroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setBackgroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setLineWidth(int width) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineWidth(width);
        }
    }

    /**
     * @generated
     */
    protected void setLineType(int style) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineStyle(style);
        }
    }

    /**
     * @generated
     */
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(DcaseVisualIDRegistry
                .getType(MonitorNameEditPart.VISUAL_ID));
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        types.add(DcaseElementTypes.DcaseLink001_3001);
        types.add(DcaseElementTypes.DcaseLink002_3002);
        types.add(DcaseElementTypes.DcaseLink003_3003);
        types.add(DcaseElementTypes.DcaseLink004_3004);
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(
            IGraphicalEditPart targetEditPart) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (targetEditPart instanceof GoalEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof StrategyEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof net.dependableos.dcase.diagram.edit.parts.MonitorEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof ContextEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof JustificationEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof SystemEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof PolicyEditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            types.add(DcaseElementTypes.DcaseLink001_3001);
        }
        if (targetEditPart instanceof GoalEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof StrategyEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof net.dependableos.dcase.diagram.edit.parts.MonitorEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof ContextEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof JustificationEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof SystemEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof PolicyEditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            types.add(DcaseElementTypes.DcaseLink002_3002);
        }
        if (targetEditPart instanceof GoalEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof StrategyEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof net.dependableos.dcase.diagram.edit.parts.MonitorEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof ContextEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof JustificationEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof SystemEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof PolicyEditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            types.add(DcaseElementTypes.DcaseLink003_3003);
        }
        if (targetEditPart instanceof GoalEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof StrategyEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof EvidenceEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof net.dependableos.dcase.diagram.edit.parts.MonitorEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof UndevelopedEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof ContextEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof JustificationEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof SystemEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof PolicyEditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef001EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef002EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef003EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef004EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef005EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        if (targetEditPart instanceof Userdef006EditPart) {
            types.add(DcaseElementTypes.DcaseLink004_3004);
        }
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(
            IElementType relationshipType) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        types.add(DcaseElementTypes.DcaseLink001_3001);
        types.add(DcaseElementTypes.DcaseLink002_3002);
        types.add(DcaseElementTypes.DcaseLink003_3003);
        types.add(DcaseElementTypes.DcaseLink004_3004);
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(
            IElementType relationshipType) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink001_3001) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink002_3002) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink003_3003) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Goal_1001);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Evidence_1003);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Strategy_1002);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Context_1006);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Justification_1007);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef004_1013);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Undeveloped_1005);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef005_1014);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef006_1015);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Monitor_1004);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.System_1008);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Policy_1009);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef001_1010);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef002_1011);
        }
        if (relationshipType == DcaseElementTypes.DcaseLink004_3004) {
            types.add(DcaseElementTypes.Userdef003_1012);
        }
        return types;
    }

    /**
     * @generated NOT
     */
    public class MonitorFigure extends Ellipse implements MouseMotionListener {

        /**
         * @generated NOT
         */
        private CustomWrappingLabel fFigureMonitorNameFigure;
        /**
         * @generated NOT
         */
        private CustomWrappingLabel fFigureMonitorUserdef001Figure;
        /**
         * @generated NOT
         */
        private CustomWrappingLabel fFigureMonitorDescFigure;
        /**
         * @generated NOT
         */
        private CustomWrappingLabel fFigureMonitorUserdef002Figure;
        /**
         * @generated NOT
         */
        private WrappingLabel fFigureMonitorResponsibilityFigure;

        /**
         * @generated NOT
         */
        public MonitorFigure() {

            // creates a custom layout.
        	CustomBorderLayout layoutThis = new CustomBorderLayout();

            layoutThis.setVerticalSpacing(NODE_MARGIN_VERTICAL);
        	// sets the padding ratio.
        	layoutThis.setPaddingRatio(0.8);
        	// sets the aspect ratio.
        	layoutThis.setAspectRatio(3, 2);
            // stretches the Desc attribute area vertically to fill any space left over.
        	layoutThis.setStretchVertically(true);

            this.setLayoutManager(layoutThis);

            this.setLineWidth(1);
            createContents();

            // sets the margins.
            MarginBorder border = new MarginBorder(NODE_MARGIN_TOP,
                    NODE_MARGIN_LEFT, NODE_MARGIN_BOTTOM, NODE_MARGIN_RIGHT);
            setBorder(border);

            addMouseMotionListener(this);
        }

        /**
         * @generated NOT
         */
        private void createContents() {

            fFigureMonitorNameFigure = new CustomWrappingLabel();
            fFigureMonitorNameFigure.setText("");
            fFigureMonitorNameFigure.setAlignment(PositionConstants.CENTER);

            this.add(fFigureMonitorNameFigure, BorderLayout.TOP);

            fFigureMonitorUserdef001Figure = new CustomWrappingLabel();
            fFigureMonitorUserdef001Figure.setText("");
            fFigureMonitorUserdef001Figure.setVisible(false);

            this.add(fFigureMonitorUserdef001Figure);

            fFigureMonitorDescFigure = new CustomWrappingLabel();

            // sets the label text should wrap.
            fFigureMonitorDescFigure.setTextWrap(true);

            fFigureMonitorDescFigure.setText("[Undefined]");

            this.add(fFigureMonitorDescFigure, BorderLayout.CENTER);

            fFigureMonitorUserdef002Figure = new CustomWrappingLabel();
            fFigureMonitorUserdef002Figure.setText("");

            this.add(fFigureMonitorUserdef002Figure, BorderLayout.BOTTOM);
            
            fFigureMonitorResponsibilityFigure = new WrappingLabel();
            fFigureMonitorResponsibilityFigure.setTextWrap(true);
            fFigureMonitorResponsibilityFigure.setText(""); //$NON-NLS-1$
            this.add(fFigureMonitorResponsibilityFigure, BorderLayout.RIGHT);

        }

        /**
         * @generated
         */
        private boolean myUseLocalCoordinates = false;

        /**
         * @generated
         */
        protected boolean useLocalCoordinates() {
            return myUseLocalCoordinates;
        }

        /**
         * @generated
         */
        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            myUseLocalCoordinates = useLocalCoordinates;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureMonitorNameFigure() {
            return fFigureMonitorNameFigure;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureMonitorUserdef001Figure() {
            return fFigureMonitorUserdef001Figure;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureMonitorDescFigure() {
            return fFigureMonitorDescFigure;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureMonitorUserdef002Figure() {
            return fFigureMonitorUserdef002Figure;
        }

        /**
         * @generated NOT
         */
        public WrappingLabel getFigureMonitorResponsibilityFigure() {
            return fFigureMonitorResponsibilityFigure;
        }

        /**
         * The function that mouse dragged event.
         * @param arg0 the mouse event.
         */
        @Override
        public void mouseDragged(MouseEvent arg0) {
        }

        /**
         * The function that mouse entered event.
         * @param arg0 the mouse event.
         */
        @Override
        public void mouseEntered(MouseEvent arg0) {
            fFigureMonitorNameFigure.setHighlightBGColor();
            fFigureMonitorDescFigure.setHighlightBGColor();
            fFigureMonitorUserdef001Figure.setHighlightBGColor();
            fFigureMonitorUserdef002Figure.setHighlightBGColor();
        }

        /**
         * The function that mouse exited event.
         * @param arg0 the mouse event.
         */
        @Override
        public void mouseExited(MouseEvent arg0) {
            fFigureMonitorNameFigure.setDefaultBGColor();
            fFigureMonitorDescFigure.setDefaultBGColor();
            fFigureMonitorUserdef001Figure.setDefaultBGColor();
            fFigureMonitorUserdef002Figure.setDefaultBGColor();
        }

        /**
         * The function that mouse hover event.
         * @param arg0 the mouse event.
         */
        @Override
        public void mouseHover(MouseEvent arg0) {
        }

        /**
         * The function that mouse moved event.
         * @param arg0 the mouse event.
         */
        @Override
        public void mouseMoved(MouseEvent arg0) {
        }
    }
    
    // AUTO_GENERATED:END
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class) {
            return SetParametersActionFilter.getSingleton();
        }
        return super.getAdapter(adapter);
    }
}
