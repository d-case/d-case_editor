/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.Stack;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;

/**
 * This class locates nodes.
 */
public class LocateNodeGraphVisitor extends DcaseGraphVisitor {

    /**
     * the stack to check the looped link.
     */
    private Stack<NodeEx> stack = new Stack<NodeEx>();

    /**
     * the stack to check the looped link adjusting location of nodes.
     */
    private Stack<NodeEx> adjustStack = new Stack<NodeEx>();

    /**
     * Locates the nodes.
     * 
     * @param graph the direct graph.
     */
    @Override
    protected void visit(DcaseDirectedGraph graph) {
        super.visit(graph);
        locateNode();
    }

    /**
     * Locates the nodes.
     */
    private void locateNode() {
        int xPoint = BASE_POINT_X;
        int yPoint = BASE_POINT_Y;

        NodeList baseNodeList = getGraph().getBaseNodeList();
        for (int i = 0; i < baseNodeList.size(); i++) {
            Node node = baseNodeList.getNode(i);
            NodeEx nodeEx = getNodeEx(node);

            Rectangle rect = nodeEx.getBlockBounds();
            locateNode(nodeEx, xPoint, yPoint);
            if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
                xPoint += rect.width + HORIZONTAL_SPACING;
            } else {
                yPoint += rect.height + VERTICAL_SPACING;
            }
        }
    }

    /**
     * Locates a node.
     * 
     * @param nodeEx a node.
     * @param xPoint x coordinate.
     * @param yPoint y coordinate.
     */
    private void locateNode(NodeEx nodeEx, int xPoint, int yPoint) {
        if (stack.contains(nodeEx)) {
            return;
        }
        stack.push(nodeEx);

        if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
            localNodeByVertical(nodeEx, xPoint, yPoint);
        } else {
            localNodeByHorizontal(nodeEx, xPoint, yPoint);
        }

        stack.pop();
    }

    /**
     * Locate node by vertical layout.
     * 
     * @param nodeEx a node.
     * @param xPoint x coordinate.
     * @param yPoint y coordinate.
     */
    private void localNodeByVertical(NodeEx nodeEx, int xPoint, int yPoint) {

        Node node = nodeEx.getNode();

        Rectangle blockRect = nodeEx.getBlockBounds();
        blockRect.x = xPoint;
        blockRect.y = yPoint;

        Dimension bottomDim = getSouthSize(nodeEx);
        Dimension rightDim = getEastSize(nodeEx);

        int x = xPoint;
        int y = yPoint;

        // locates the children in the south.
        NodeList childListBottom = nodeEx.getSouthNodes();
        if (childListBottom.size() > 0) {
            if (node.width > bottomDim.width) {
                x += ((node.width - bottomDim.width) / 2);
            }
            if (node.height > rightDim.height) {
                y += node.height;
            } else if (bottomDim.width > bottomDim.width / 2 + node.width / 2
                    + VERTICAL_SPACING) {
                y += rightDim.height;
            } else {
                y += node.height;
            }
            y += VERTICAL_SPACING;
        }
        for (int i = 0; i < childListBottom.size(); i++) {
            Node child = childListBottom.getNode(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                localNodeByVertical(childEx, x, y);
                x += childRect.width + HORIZONTAL_SPACING;
            }
        }

        x = xPoint;
        y = yPoint;

        // locates the children in the east.
        NodeList childListRight = nodeEx.getEastNodes();

        if (childListRight.size() > 0) {
            if (node.width > bottomDim.width) {
                x += node.width;
            } else {
                x += node.width / 2 + bottomDim.width / 2;
            }
            x += HORIZONTAL_CONTEXT_SPACING;

            if (node.height > rightDim.height) {
                y += ((node.height - rightDim.height) / 2);
            }
        }
        for (int i = 0; i < childListRight.size(); i++) {
            Node child = childListRight.getNode(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                localNodeByVertical(childEx, x, y);
                y += childRect.height + VERTICAL_CONTEXT_SPACING;
            }
        }

        // adjusts the position of the parent.
        x = xPoint;
        y = yPoint;

        if (childListBottom.size() > 0) {
            Node first = getWestOfSouth(nodeEx);
            Node last = getEastOfSouth(nodeEx);
            if (first != null) {
                x = (first.x + last.x + last.width - node.width) / 2;
            }
        }
        if (childListRight.size() > 0) {
            Node first = getNorthOfEast(nodeEx);
            Node last = getSouthOfEast(nodeEx);
            if (first != null) {
                y = (first.y + last.y + last.height - node.height) / 2;
            }
        }

        node.x = x;
        node.y = y;

        // adjusts the position of the children in the south.
        if (childListBottom.size() > 0) {
            if (y > yPoint) {
                for (int i = 0; i < childListBottom.size(); i++) {
                    Node childNode = childListBottom.getNode(i);
                    NodeEx childEx = getNodeEx(childNode);
                    if (childNode.y > node.y + node.height + HORIZONTAL_SPACING) {
                        break;
                    }
                    int yAdjust = y - yPoint;
                    adjustLocation(childEx, 0, yAdjust);
                }
            }
        }

        // adjusts the position of the children in the east.
        if (childListRight.size() > 0) {
            x = node.x + node.width + HORIZONTAL_CONTEXT_SPACING;
            for (int i = 0; i < childListRight.size(); i++) {
                Node childNode = childListRight.getNode(i);
                NodeEx childEx = getNodeEx(childNode);
                Rectangle dim = childEx.getBlockBounds();
                int xAdjust = Math.max(x - childNode.x, x - dim.x);
                adjustLocation(childEx, xAdjust, 0);
            }
        }
    }

    /**
     * Locate node by horizontal layout.
     * 
     * @param nodeEx a node.
     * @param xPoint x coordinate.
     * @param yPoint y coordinate.
     */
    private void localNodeByHorizontal(NodeEx nodeEx, int xPoint, int yPoint) {

        Node node = nodeEx.getNode();

        Rectangle blockRect = nodeEx.getBlockBounds();
        blockRect.x = xPoint;
        blockRect.y = yPoint;

        Dimension bottomDim = getSouthSize(nodeEx);
        Dimension rightDim = getEastSize(nodeEx);

        int x = xPoint;
        int y = yPoint;

        // locates the children in the south.
        NodeList childListBottom = nodeEx.getSouthNodes();
        if (childListBottom.size() > 0) {
            if (node.height > bottomDim.height) {
                y += ((node.height - bottomDim.height) / 2);
            }
            x += Math.max(node.width, rightDim.width);
            x += HORIZONTAL_SPACING;
        }
        for (int i = 0; i < childListBottom.size(); i++) {
            Node child = childListBottom.getNode(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                localNodeByHorizontal(childEx, x, y);
                y += childRect.height + VERTICAL_SPACING;
            }
        }

        x = xPoint;
        y = yPoint;

        // locates the children in the east.
        NodeList childListRight = nodeEx.getEastNodes();
        if (childListRight.size() > 0) {
            y += node.height;
            y += VERTICAL_CONTEXT_SPACING;

            if (node.width > rightDim.width) {
                x += ((node.width - rightDim.width) / 2);
            }
        }
        for (int i = 0; i < childListRight.size(); i++) {
            Node child = childListRight.getNode(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                localNodeByHorizontal(childEx, x, y);
                x += childRect.width + HORIZONTAL_CONTEXT_SPACING;
            }
        }

        // adjusts the position of the parent.
        x = xPoint;
        y = yPoint;

        if (childListBottom.size() > 0) {
            Node first = getWestOfSouth(nodeEx);
            Node last = getEastOfSouth(nodeEx);
            if (first != null) {
                y = (first.y + last.y + last.height - node.height) / 2;
            }
        }
        if (childListRight.size() > 0) {
            Node first = getNorthOfEast(nodeEx);
            Node last = getSouthOfEast(nodeEx);
            if (first != null) {
                x = (first.x + last.x + last.width - node.width) / 2;
            }
        }

        node.x = x;
        node.y = y;

        // adjusts the position of the children in the east.
        if (childListRight.size() > 0) {
            if (y > yPoint) {
                for (int i = 0; i < childListRight.size(); i++) {
                    Node childNode = childListRight.getNode(i);
                    NodeEx childEx = getNodeEx(childNode);
                    if (childNode.y > node.y + node.height + VERTICAL_CONTEXT_SPACING) {
                        break;
                    }
                    int yAdjust = y - yPoint;
                    adjustLocation(childEx, 0, yAdjust);
                }
            }
        }
    }

    /**
     * Returns the first node that should be locate in south.
     * 
     * @param nodeEx a node.
     * @return the first node that should be locate in south.
     */
    private Node getWestOfSouth(NodeEx nodeEx) {
        Node node = null;
        NodeList list = nodeEx.getSouthNodes();
        for (int i = 0; i < list.size(); i++) {
            Node child = (Node) list.get(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                node = child;
                break;
            }
        }
        return node;
    }

    /**
     * Returns the last child that should be locate in south.
     * 
     * @param nodeEx a node.
     * @return the last child that should be locate in south.
     */
    private Node getEastOfSouth(NodeEx nodeEx) {
        Node node = null;
        NodeList list = nodeEx.getSouthNodes();
        for (int i = list.size() - 1; i >= 0; i--) {
            Node child = (Node) list.get(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                node = child;
                break;
            }
        }
        return node;
    }

    /**
     * Returns the first child that should be locate in east.
     * 
     * @param nodeEx a node.
     * @return the first child that should be locate in east.
     */
    private Node getNorthOfEast(NodeEx nodeEx) {
        Node node = null;
        NodeList list = nodeEx.getEastNodes();
        for (int i = 0; i < list.size(); i++) {
            Node child = (Node) list.get(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                node = child;
                break;
            }
        }
        return node;
    }

    /**
     * Returns the last child that should be locate in east.
     * 
     * @param nodeEx a node.
     * @return the last child that should be locate in east.
     */
    private Node getSouthOfEast(NodeEx nodeEx) {
        Node node = null;
        NodeList list = nodeEx.getEastNodes();
        for (int i = list.size() - 1; i >= 0; i--) {
            Node child = (Node) list.get(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                node = child;
                break;
            }
        }
        return node;
    }

    /**
     * Adjusts the position of a node.
     * 
     * @param nodeEx a node.
     * @param x x axis movement.
     * @param y y axis movement.
     */
    private void adjustLocation(NodeEx nodeEx, int x, int y) {
        if (adjustStack.contains(nodeEx)) {
             return;
        }
        if (stack.contains(nodeEx)) {
            return;
        }
        adjustStack.push(nodeEx);

        nodeEx.getNode().x += x;
        nodeEx.getBlockBounds().x += x;
        nodeEx.getNode().y += y;
        nodeEx.getBlockBounds().y += y;
        NodeList childList = nodeEx.getChildren();
        for (int i = 0; i < childList.size(); i++) {
            adjustLocation(getNodeEx(childList.getNode(i)), x, y);
        }
        adjustStack.pop();
    }

    /**
     * Returns the size of the south.
     * 
     * @param nodeEx a node.
     * @return the size of the south.
     */
    public Dimension getSouthSize(NodeEx nodeEx) {
        Dimension dim = new Dimension();
        NodeList list = nodeEx.getSouthNodes();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Node child = list.getNode(i);
            NodeEx childEx = getNodeEx(child);

            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
                    dim.width += childRect.width;
                    dim.height = Math.max(dim.height, childRect.height);
                } else {
                    dim.width = Math.max(dim.width, childRect.width);
                    dim.height += childRect.height;
                }
                count++;
            }
        }
        if (count > 1) {
            if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
                dim.width += (HORIZONTAL_SPACING * (count - 1));
            } else {
                dim.height += (VERTICAL_SPACING * (count - 1));
            }
        }
        return dim;
    }

    /**
     * Returns the size of the east.
     * 
     * @param nodeEx a node.
     * @return the size of the east.
     */
    public Dimension getEastSize(NodeEx nodeEx) {
        Dimension dim = new Dimension();
        NodeList list = nodeEx.getEastNodes();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Node child = list.getNode(i);
            NodeEx childEx = getNodeEx(child);

            if (nodeEx.getDepth() < childEx.getDepth()) {
                Rectangle childRect = childEx.getBlockBounds();
                if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
                    dim.width = Math.max(dim.width, childRect.width);
                    dim.height += childRect.height;
                } else {
                    dim.width += childRect.width;
                    dim.height = Math.max(dim.height, childRect.height);
                }
                count++;
            }
        }
        if (count > 1) {
            if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
                dim.height += (VERTICAL_CONTEXT_SPACING * (count - 1));
            } else {
                dim.width += (HORIZONTAL_CONTEXT_SPACING * (count - 1));
            }
        }
        return dim;
    }
}
