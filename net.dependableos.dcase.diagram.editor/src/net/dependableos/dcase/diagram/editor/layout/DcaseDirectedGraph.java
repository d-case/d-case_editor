/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.Collection;
import java.util.HashMap;


import net.dependableos.dcase.diagram.edit.parts.ContextEditPart;
import net.dependableos.dcase.diagram.edit.parts.JustificationEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;

import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;

/**
 * This class manages the properties of the nodes for arranging layout.
 */
public class DcaseDirectedGraph {

    /**
     * the direct graph.
     */
    private DirectedGraph graph = null;

    /**
     * the map that manages nodes in the diagram.
     */
    private static HashMap<Node, NodeEx> map = new HashMap<Node, NodeEx>();

    /**
     * the first nodes.
     */
    private NodeList baseNodeList = new NodeList();

    /**
     * Creates the DcaseDirectGraph and initializes it.
     * 
     * @param graph the direct graph.
     */
    public DcaseDirectedGraph(DirectedGraph graph) {
        this.graph = graph;
        map.clear();
        baseNodeList.clear();
    }

    /**
     * Returns the all of the edges in the graph.
     * 
     * @return the all of the edges in the graph.
     */
    public EdgeList getEdgeList() {
        return graph.edges;
    }

    /**
     * Returns the all of the nodes in the graph.
     * 
     * @return the all of the nodes in the graph.
     */
    public NodeList getNodeList() {
        return graph.nodes;
    }

    /**
     * Returns the nodes in the diagram.
     * 
     * @return the nodes in the diagram.
     */
    public Collection<NodeEx> getNodeExList() {
        return map.values();
    }

    /**
     * Returns the NodeEx object that represents the specified Node object.
     * 
     * @param node the Node object.
     * @return the NodeEx object.
     */
    protected static NodeEx getNodeEx(Node node) {
        return map.get(node);
    }

    /**
     * Set the NodeEx object as the representation of the specified Node object.
     * 
     * @param node the Node object.
     * @param nodeEx the NodeEx object.
     */
    protected static void setNodeEx(Node node, NodeEx nodeEx) {
        map.put(node, nodeEx);
    }

    /**
     * Tests whether the specified node should be put on the east.
     * 
     * @param node the node.
     * @return true if and only if the specified node should be put on the east;false otherwise.
     */
    protected static boolean isEast(Node node) {
        if (node.data instanceof JustificationEditPart
                || node.data instanceof ContextEditPart
                || node.data instanceof SystemEditPart) { // Parameter node
            return true;
        }
        return false;
        
    }

    /**
     * Returns the first nodes.
     * 
     * @return the first nodes.
     */
    protected NodeList getBaseNodeList() {
        return baseNodeList;
    }

    /**
     * Add the node as the first node.
     * 
     * @param node the node.
     */
    @SuppressWarnings("unchecked")
    protected void addBaseNodeList(Node node) {
        if (!baseNodeList.contains(node)) {
            baseNodeList.add(node);
        }
    }
}
