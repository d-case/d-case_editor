/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;

/**
 * An abstract class subclassed by all classes to lay out a D-Case diagram.
 */
abstract class DcaseGraphVisitor {

    /**
     * the vertical spacing between a parent and a its child.
     */
    protected static final int VERTICAL_SPACING = 32;
    /**
     * the horizontal spacing between sibling nodes.
     */
    protected static final int HORIZONTAL_SPACING = 48;
    /**
     * the horizontal spacing between a node and a its information node.
     */
    protected static final int HORIZONTAL_CONTEXT_SPACING = 24;
    /**
     * the vertical spacing between sibling information nodes.
     */
    protected static final int VERTICAL_CONTEXT_SPACING = 12;
    /**
     * x coordinates to start.
     */
    protected static final int BASE_POINT_X = 20;
    /**
     * y coordinates to start.
     */
    protected static final int BASE_POINT_Y = 20;

    /**
     * the direct graph for D-Case.
     */
    private DcaseDirectedGraph graph = null;

    /**
     * Lays out the given graph.
     *
     * @param graph the direct graph.
     */
    protected void visit(DcaseDirectedGraph graph) {
        this.graph = graph;
    }

    /**
     * Returns the direct graph.
     * 
     * @return graph the direct graph.
     */
    protected DcaseDirectedGraph getGraph() {
        return graph;
    }

    /**
     * Returns the NodeEx that represents the specified node.
     * 
     * @param node the node.
     * @return the NodeEx.
     */
    protected static NodeEx getNodeEx(Node node) {
        return DcaseDirectedGraph.getNodeEx(node);
    }

    /**
     * Adds the NodeEx that represents the specified node.
     * 
     * @param node the node.
     * @return the added NodeEx.
     */
    protected NodeEx addNodeEx(Node node) {
        NodeEx nodeEx = getNodeEx(node);
        if (nodeEx == null) {
            nodeEx = new NodeEx(node);
            DcaseDirectedGraph.setNodeEx(node, nodeEx);
        }
        return nodeEx;
    }

    /**
     * Returns the list of first nodes.
     * 
     * @return the first node.
     */
    protected NodeList getBaseNodeList() {
        return graph.getBaseNodeList();
    }

    /**
     * Adds the first node.
     * 
     * @param node the first node.
     */
    @SuppressWarnings("unchecked")
    protected void addBaseNodeList(Node node) {
        NodeList list = graph.getBaseNodeList();
        if (!list.contains(node)) {
            list.add(node);
        }
    }
}
