/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.Collections;
import java.util.Stack;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;

/**
 * A class that represents a node with information for locating.
 */
public class NodeEx {

    /**
     * the node.
     */
    private Node node = null;

    /**
     * the size of the node.
     */
    private Rectangle bounds = null;

    /**
     * the depth.
     */
    private int depth = 0;

    /**
     * the looped link flag.
     */
    private boolean looped = false;

    /**
     * the stack to check the looped link.
     */
    private Stack<NodeEx> stack = new Stack<NodeEx>();

    /**
     * Create an instance and initializes it.
     * 
     * @param node the node.
     */
    public NodeEx(Node node) {
        this.node = node;
        Dimension dim = ((ShapeEditPart) node.data).getSize();
        node.width = dim.width;
        node.height = dim.height;
        bounds = new Rectangle(0, 0, node.width, node.height);
    }

    /**
     * Returns the node.
     * 
     * @return the node.
     */
    public Node getNode() {
        return node;
    }

    /**
     * Returns the bounds to lay out the node and its children.
     * 
     * @return the bounds to lay out the node and its children.
     */
    public Rectangle getBlockBounds() {
        return bounds;
    }

    /**
     * Sets the bounds to lay out the node and its children.
     * 
     * 
     * @param rect the bounds to lay out the node and its children.
     */
    public void setBlockBounds(Rectangle rect) {
        this.bounds = rect;
    }

    /**
     * Returns the depth.
     * 
     * @return the depth.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the depth.
     * 
     * @param depth the depth.
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Returns whether the node is contained in a looped link.
     * 
     * @return true if and only if the node is contained in a looped link;false otherwise.
     */
    public boolean getLooped() {
        return looped;
    }

    /**
     * Sets whether the node is contained in a looped link.
     * 
     * @param looped true if and only if the node is contained in a looped link;false otherwise.
     */
    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    /**
     * Returns the count of the parents.
     * 
     * @return the count of the parents.
     */
    public int getParentCount() {
        return node.incoming.size();
    }

    /**
     * Returns the count of the children.
     * 
     * @return the count of the children.
     */
    public int getChildCount() {
        return node.outgoing.size();
    }

    /**
     * Returns the count of the children those should be locate to the south.
     * 
     * @return the count of the children those should be locate to the south.
     */
    public int getSouthCount() {
        int count = 0;
        for (Object obj : node.outgoing) {
            if (!DcaseDirectedGraph.isEast((Node) ((Edge) obj).target)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the count of the children those should be locate to the east.
     * 
     * @return the count of the children those should be locate to the east.
     */
    public int getEastCount() {
        int count = 0;
        for (Object obj : node.outgoing) {
            if (DcaseDirectedGraph.isEast((Node) ((Edge) obj).target)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the parents.
     * 
     * @return the parents.
     */
    @SuppressWarnings("unchecked")
    public NodeList getParent() {
        NodeList nodeList = new NodeList();
        for (Object obj : node.incoming) {
            nodeList.add((Node) ((Edge) obj).source);
        }
        return nodeList;
    }

    /**
     * Returns the children.
     * 
     * @return Returns the children.
     */
    @SuppressWarnings("unchecked")
    public NodeList getChildren() {
        NodeList nodeList = new NodeList();
        for (Object obj : node.outgoing) {
            nodeList.add((Node) ((Edge) obj).target);
        }
        return nodeList;
    }

    /**
     * Returns the children those should be locate to the south.
     * 
     * @return the children those should be locate to the south.
     */
    @SuppressWarnings("unchecked")
    public NodeList getSouthNodes() {
        EdgeList edgeList = new EdgeList();
        NodeList nodeList = new NodeList();
        for (Object obj : node.outgoing) {
            if (!DcaseDirectedGraph.isEast((Node) ((Edge) obj).target)) {
                edgeList.add(obj);
            }
        }
        // sorts by the sibling order.
        if (edgeList.size() > 1) {
            Collections.sort(edgeList, new DcaseEditPartLayoutComparator());
        }
        for (int i = 0; i < edgeList.size(); i++) {
            nodeList.add(((Edge) edgeList.get(i)).target);
        }
        return nodeList;
    }

    /**
     * Returns the children those should be locate to the east.
     * 
     * @return the children those should be locate to the east.
     */
    @SuppressWarnings("unchecked")
    public NodeList getEastNodes() {
        EdgeList edgeList = new EdgeList();
        NodeList nodeList = new NodeList();
        for (Object obj : node.outgoing) {
            if (DcaseDirectedGraph.isEast((Node) ((Edge) obj).target)) {
                edgeList.add(obj);
            }
        }
        // sorts by the sibling order.
        if (edgeList.size() > 1) {
            Collections.sort(edgeList, new DcaseEditPartLayoutComparator());
        }
        for (int i = 0; i < edgeList.size(); i++) {
            nodeList.add(((Edge) edgeList.get(i)).target);
        }
        return nodeList;
    }

    /**
     * Returns the size to lay out children to the south.
     * 
     * @param gap the horizontal space between nodes.
     * @param flag true if and only if the last node should be except;false otherwise.
     * @return the size to lay out children to the south.
     */
    public Dimension getSouthSize(int gap, boolean flag) {
        int height = 0;
        int width = 0;
        int count = 0;
        NodeList list = getSouthNodes();
        if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
            // top down layout
            for (int i = 0; i < list.size(); i++) {
                if (flag && i == list.size() - 1) {
                    continue;
                }
                Node childNode = (Node) list.get(i);
                NodeEx nodeEx = DcaseDirectedGraph.getNodeEx(childNode);
                if (this.getDepth() < nodeEx.getDepth()) {
                    Dimension dim = nodeEx.calcBlockSize(flag);
                    width += dim.width;
                    height = Math.max(height, dim.height);
                    count++;
                }
            }
            if (count > 1) {
                width += (gap * (count - 1));
            }
            return new Dimension(width, height);
        } else {
            // left to right layout
            for (int i = 0; i < list.size(); i++) {
                if (flag && i == list.size() - 1) {
                    continue;
                }
                Node childNode = (Node) list.get(i);
                NodeEx nodeEx = DcaseDirectedGraph.getNodeEx(childNode);
                if (this.getDepth() < nodeEx.getDepth()) {
                    Dimension dim = nodeEx.calcBlockSize(flag);
                    width = Math.max(width, dim.width);
                    height += dim.height;
                    count++;
                }
            }
            if (count > 1) {
                height += (gap * (count - 1));
            }
            return new Dimension(width, height);
        }
    }

    /**
     * Returns the size to lay out children to the east.
     * 
     * @param gap the vertical space between nodes.
     * @param flag true if and only if the last node should be except;false otherwise.
     * @return the size to lay out children to the east.
     */
    public Dimension getEastSize(int gap, boolean flag) {
        int width = 0;
        int height = 0;
        int count = 0;
        NodeList list = getEastNodes();
        if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
            // top down layout
            for (int i = 0; i < list.size(); i++) {
                if (flag && i == list.size() - 1) {
                    continue;
                }
                Node childNode = (Node) list.get(i);
                NodeEx nodeEx = DcaseDirectedGraph.getNodeEx(childNode);
                if (this.getDepth() < nodeEx.getDepth()) {
                    Dimension dim = nodeEx.calcBlockSize(flag);
                    width = Math.max(width, dim.width);
                    height += dim.height;
                    count++;
                }
            }
            if (count > 1) {
                height += (gap * (count - 1));
            }
            return new Dimension(width, height);
        } else {
            // left to right layout
            for (int i = 0; i < list.size(); i++) {
                if (flag && i == list.size() - 1) {
                    continue;
                }
                Node childNode = (Node) list.get(i);
                NodeEx nodeEx = DcaseDirectedGraph.getNodeEx(childNode);
                if (this.getDepth() < nodeEx.getDepth()) {
                    Dimension dim = nodeEx.calcBlockSize(flag);
                    width += dim.width;
                    height = Math.max(height, dim.height);
                    count++;
                }
            }
            if (count > 1) {
                width += (gap * (count - 1));
            }
            return new Dimension(width, height);
        }
    }

    /**
     * Returns the size to lay out the node and its children.
     * 
     * @return the size to lay out the node and its children.
     */
    public Dimension calcBlockSize() {
        return calcBlockSize(false);
    }

    /**
     * Returns the size to lay out the node and its children.
     * 
     * @param flag true if and only if the last node should be except;false otherwise.
     * @return the size to lay out the node and its children.
     */
    public Dimension calcBlockSize(boolean flag) {
        if (stack.contains(this)) {
            return new Dimension();
        }
        Dimension dim = null;
        stack.push(this);
        if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
            dim = calcBlockSizeByVertical(flag);
        } else {
            dim = calcBlockSizeByHorizontal(flag);
        }
        stack.pop();
        return dim;
    }

    /**
     * Returns the size to vertical layout the node and its children.
     * 
     * @param flag true if and only if the last node should be except;false otherwise.
     * @return the size to lay out the node and its children.
     */
    private Dimension calcBlockSizeByVertical(boolean flag) {

        int width = 0;
        int height = 0;
        Dimension dimBottom = getSouthSize(DcaseGraphVisitor.HORIZONTAL_SPACING, flag);
        Dimension dimRight = null;
        if (flag) {
            dimRight = new Dimension();
        } else {
            dimRight = getEastSize(DcaseGraphVisitor.VERTICAL_SPACING, flag);
        }
        
        width = Math.max(node.width, dimBottom.width);
        if (dimRight.width > 0) {
            width = Math.max(width / 2 + node.width / 2
                    + DcaseGraphVisitor.VERTICAL_CONTEXT_SPACING + dimRight.width,
                    width);
        }

        height = Math.max(node.height, dimRight.height);
        if (dimBottom.height > 0) {
            height += (DcaseGraphVisitor.VERTICAL_SPACING + dimBottom.height);
        }
        return new Dimension(width, height);
    }

    /**
     * Returns the size to horizontal layout the node and its children.
     * 
     * @param flag true if and only if the last node should be except;false otherwise.
     * @return the size to lay out the node and its children.
     */
    private Dimension calcBlockSizeByHorizontal(boolean flag) {

        int width = 0;
        int height = 0;
        Dimension dimBottom = null;
        Dimension dimRight = getSouthSize(DcaseGraphVisitor.VERTICAL_SPACING, flag);
        if (flag) {
            dimBottom = new Dimension();
        } else {
            dimBottom = getEastSize(DcaseGraphVisitor.HORIZONTAL_SPACING, flag);
        }
        
        height = Math.max(node.height, dimRight.height);
        if (dimBottom.height > 0) {
            height = Math.max(height / 2 + node.height / 2
                    + DcaseGraphVisitor.HORIZONTAL_SPACING + dimBottom.height,
                    height);
        }

        width = Math.max(node.width, dimBottom.width);
        if (dimRight.width > 0) {
            width += (DcaseGraphVisitor.HORIZONTAL_SPACING + dimRight.width);
        }
        return new Dimension(width, height);
    }

    /**
     * Returns the bounds of the node.
     * 
     * @return the bounds of the node.
     */
    public Rectangle getNodeBounds() {
        return new Rectangle(node.x, node.y, node.width, node.height);
    }
}
