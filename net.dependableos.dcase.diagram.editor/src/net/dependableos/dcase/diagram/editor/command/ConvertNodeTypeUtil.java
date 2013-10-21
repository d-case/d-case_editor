/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.editor.command.ChangeVisibleEditPartsCommand.CONST_CHANGE_VISIBLE_COMMAND_LABEL;

import java.util.Set;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * This class creates the compound command for converting the node type.
 */
public class ConvertNodeTypeUtil {

	/**
	 * the compound command label.
	 */
	private static final String CONVERT_NODE_TYPE_CMD_LABEL = "command for converting node"; //$NON-NLS-1$
	/**
	 * the command label for updating links.
	 */
	private static final String UPDATE_BASICLINK_COMMAND_LABEL = "command for converting basiclink"; //$NON-NLS-1$
	/**
	 * the command label for copying attributes of a node.
	 */
	private static final String COPY_NODE_ATTRIBUTE_COMMAND_LABEL = "command for converting node attributes"; //$NON-NLS-1$
	/**
	 * the command label for selecting new node.
	 */
	private static final String SELECT_COMMAND_LABEL = "command for converting node type"; //$NON-NLS-1$

	/**
	 * current domain.
	 */
	TransactionalEditingDomain currentDomain;

	/**
	 * current diagram.
	 */
	Diagram currentDiagram;

	/**
	 * current argument EditPart.
	 */
	ArgumentEditPart currentArgumentEditPart;

	/**
	 * target EditPart.
	 */
	ShapeEditPart currentEditPart;

	/**
	 * new node type.
	 */
	private NodeType newNodeType;

	/**
	 * the original node.
	 */
	private BasicNode oldNode;

	/**
	 * the new node name name.
	 */
	private String newName = null;

	/**
	 * Creates a ConvertNodeCommand and initializes it.
	 * 
	 * @param domain
	 *            the editing domain.
	 * @param label
	 *            the command label.
	 * @param newNodeType
	 *            new node type.
	 * @param oldNode
	 *            the original node.
	 */
	public ConvertNodeTypeUtil(TransactionalEditingDomain domain, String label,
			Diagram diagram, ArgumentEditPart argumentEditPart,
			ShapeEditPart editPart, NodeType newNodeType, BasicNode oldNode) {
		this.currentDomain = domain;
		this.currentDiagram = diagram;
		this.currentArgumentEditPart = argumentEditPart;
		this.currentEditPart = editPart;
		this.newNodeType = newNodeType;
		this.oldNode = oldNode;
	}

	/**
	 * Creates a ConvertNodeCommand and initializes it.
	 * 
	 * @param domain
	 *            the editing domain.
	 * @param label
	 *            the command label.
	 * @param newNodeType
	 *            new node type.
	 * @param oldNode
	 *            the original node.
	 * @param isCopyName
	 *            the flag for copying name.
	 */
	public ConvertNodeTypeUtil(TransactionalEditingDomain domain, String label,
			Diagram diagram, ArgumentEditPart argumentEditPart,
			ShapeEditPart editPart, NodeType newNodeType, BasicNode oldNode,
			String newName) {
		this(domain, label, diagram, argumentEditPart, editPart, newNodeType,
				oldNode);
		this.newName = newName;
	}

	/**
	 * Returns the compound command for converting the node type.
	 * 
	 * @return the command.
	 */
	public CompoundCommand createCommand() {
		Argument currentArgument = (Argument) currentDiagram.getElement();
		if (currentArgument == null) {
			return null;
		}

		Set<String> excludeIdSet = DcaseEditorUtil
				.getChildUUIDs(currentArgumentEditPart);

		CompoundCommand cc = new CompoundCommand(CONVERT_NODE_TYPE_CMD_LABEL);

		PreferencesHint diagramPrefHint = currentArgumentEditPart
				.getDiagramPreferencesHint();

		// shows children.
		NodeChildrenShowHandler showHandler = new NodeChildrenShowHandler();
		ChangeVisibleEditPartsCommand changeVisibleEditPartsCommand = new ChangeVisibleEditPartsCommand(
				CONST_CHANGE_VISIBLE_COMMAND_LABEL,
				showHandler
						.getChildrenToShow((ShapeNodeEditPart) currentEditPart),
				true);
		cc.add(new ICommandProxy(changeVisibleEditPartsCommand));

		IElementType elementType = getElementType(newNodeType);

		ViewAndElementDescriptor descriptor = new ViewAndElementDescriptor(
				new CreateElementRequestAdapter(new CreateElementRequest(
						elementType)), Node.class,
				((IHintedType) elementType).getSemanticHint(), diagramPrefHint);

		CreateViewAndElementRequest createNodeRequest = new CreateViewAndElementRequest(
				descriptor);
		createNodeRequest
				.setLocation(getNewNodeAbsoluteLocation(currentEditPart));
		createNodeRequest.setSize(new Dimension(-1, -1));
		Command createNodeCommand = currentArgumentEditPart
				.getCommand(createNodeRequest);
		cc.add(createNodeCommand);

		UpdateLinkCommand convertLinkCommand = new UpdateLinkCommand(
				currentDomain, UPDATE_BASICLINK_COMMAND_LABEL, currentArgument,
				oldNode, currentArgumentEditPart, createNodeRequest);
		cc.add(new ICommandProxy(convertLinkCommand));

		CopyNodeAttributeCommand copyAttributeCommand = new CopyNodeAttributeCommand(
				currentDomain, COPY_NODE_ATTRIBUTE_COMMAND_LABEL, oldNode,
				currentArgumentEditPart, createNodeRequest, newName);
		cc.add(new ICommandProxy(copyAttributeCommand));

		ICommand selectCommand = new SelectExcludesCommand(
				SELECT_COMMAND_LABEL, currentArgumentEditPart, excludeIdSet);
		cc.add(new ICommandProxy(selectCommand));
		return cc;
	}

	/**
	 * Returns the absolute location of the specified EditPart.
	 * 
	 * @param sourceEditPart
	 *            an EditPart.
	 * @return the absolute location.
	 */
	private Point getNewNodeAbsoluteLocation(ShapeEditPart sourceEditPart) {

		// gets the location.
		Point basePoint = sourceEditPart.getLocation();

		// gets the figure.
		IFigure figure = sourceEditPart.getFigure();

		// creates the new Point object.
		Point newPoint = new Point(basePoint.x, basePoint.y);

		// translates to absolute.
		figure.translateToAbsolute(newPoint);

		return newPoint;
	}

	/**
	 * Returns the IElementType object that is represent specified NodeType
	 * object.
	 * 
	 * @param newNodeType
	 *            a NodeType object.
	 * @return the IElementType object.
	 */
	private IElementType getElementType(NodeType newNodeType) {

		IElementType elementType = null;

		switch (newNodeType) {
		case CONTEXT:
			elementType = DcaseElementTypes.Context_1006;
			break;
		case EVIDENCE:
			elementType = DcaseElementTypes.Evidence_1003;
			break;
		case GOAL:
			elementType = DcaseElementTypes.Goal_1001;
			break;
		case JUSTIFICATION:
			elementType = DcaseElementTypes.Justification_1007;
			break;
		case MONITOR:
			elementType = DcaseElementTypes.Monitor_1004;
			break;
		case POLICY:
			elementType = DcaseElementTypes.Policy_1009;
			break;
		case STRATEGY:
			elementType = DcaseElementTypes.Strategy_1002;
			break;
		case SYSTEM:
			elementType = DcaseElementTypes.System_1008;
			break;
		case UNDEVELOPED:
			elementType = DcaseElementTypes.Undeveloped_1005;
			break;
		case USERDEF001:
			elementType = DcaseElementTypes.Userdef001_1010;
			break;
		case USERDEF002:
			elementType = DcaseElementTypes.Userdef002_1011;
			break;
		case USERDEF003:
			elementType = DcaseElementTypes.Userdef003_1012;
			break;
		case USERDEF004:
			elementType = DcaseElementTypes.Userdef004_1013;
			break;
		case USERDEF005:
			elementType = DcaseElementTypes.Userdef005_1014;
			break;
		case USERDEF006:
			elementType = DcaseElementTypes.Userdef006_1015;
			break;
		default:
			return null;
		}
		return elementType;
	}
}
