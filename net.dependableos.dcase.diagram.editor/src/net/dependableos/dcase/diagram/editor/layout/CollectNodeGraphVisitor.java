/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.Stack;

import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.gmf.runtime.draw2d.ui.graph.ConstantSizeNode;

/**
 * This class collects the first nodes.
 */
public class CollectNodeGraphVisitor extends DcaseGraphVisitor {

    /**
     * the stack to check the looped link.
     */
    private Stack<NodeEx> stack = new Stack<NodeEx>();

    /**
     * the stack to check the looped link visiting the parent.
     */
    private Stack<Node> workStack = new Stack<Node>();

    /**
     * Collects the first nodes.
     * 
     * @param graph the graph.
     */
    @Override
    protected void visit(DcaseDirectedGraph graph) {
        super.visit(graph);
        collectBaseNode();
    }

    /**
     * Collects the first nodes.
     */
    private void collectBaseNode() {
        NodeList nodeList = getGraph().getNodeList();

        // collects the root nodes.
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.getNode(i);
            if (node instanceof ConstantSizeNode) {
                if (node.incoming.size() == 0) {
                    addBaseNodeList(node);
                    collectBaseNode(node);
                }
            }
        }

        // collects the first nodes of the looped link.
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.getNode(i);
            if (node instanceof ConstantSizeNode) {
                if (getNodeEx(node) == null) {
                    if (node.outgoing.size() > 0) {
                        addBaseNodeList(node);
                        collectBaseNode(node);
                    }
                }
            }
        }
    }

    /**
     * Collects the first node.
     * 
     * @param node the node.
     */
    private void collectBaseNode(Node node) {
        NodeEx nodeEx = getNodeEx(node);
        if (nodeEx != null) {
            if (stack.contains(nodeEx)) {
                nodeEx.setLooped(true);
            }
            return;
        }
        nodeEx = addNodeEx(node);
        stack.push(nodeEx);
        NodeList list = nodeEx.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node child = list.getNode(i);
            collectBaseNode(child);
        }
        if (nodeEx.getParentCount() > 1) {
            list = nodeEx.getParent();
            for (int i = 0; i < list.size(); i++) {
                Node parent = list.getNode(i);
                getRootParent(parent);
            }
        }
        nodeEx.setDepth(stack.size());
        stack.pop();
    }

    /**
     * Returns the root of the specified node.
     * 
     * @param node the node.
     */
    private void getRootParent(Node node) {
        if (getNodeEx(node) != null) {
            return;
        }
        if (workStack.contains(node)) {
            return;
        }
        workStack.push(node);
        EdgeList edgeList = node.incoming;
        if (edgeList.size() > 0) {
            for (int i = 0; i < edgeList.size(); i++) {
                Node parent = ((Edge) edgeList.get(i)).source;
                getRootParent(parent);
            }
        } else {
            addBaseNodeList(node);
            collectBaseNode(node);
        }
        workStack.pop();
        return;
    }

}
