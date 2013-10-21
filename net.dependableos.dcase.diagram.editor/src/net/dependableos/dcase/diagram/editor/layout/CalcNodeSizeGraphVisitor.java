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
 * This class calculates the preferred size of the area to show the nodes and theirs children.
 */
public class CalcNodeSizeGraphVisitor extends DcaseGraphVisitor {

    /**
     * the stack to check the looped link.
     */
    private Stack<NodeEx> stack = new Stack<NodeEx>();


    /**
     * {@inheritDoc}
     */
    @Override
    protected void visit(DcaseDirectedGraph graph) {
        super.visit(graph);
        calcBlockSize();
    }

    /**
     * Calculates the preferred size of the area to show the nodes and theirs children.
     */
    private void calcBlockSize() {
        NodeList baseNodeList = getGraph().getBaseNodeList();
        for (int i = 0; i < baseNodeList.size(); i++) {
            Node node = baseNodeList.getNode(i);
            calcBlockSize(node);
        }
    }

    /**
     * Calculates the preferred size of the area to show the specified node and its children.
     * 
     * @param node the root node.
     */
    private void calcBlockSize(Node node) {
        NodeEx nodeEx = getNodeEx(node);

        if (stack.contains(nodeEx)) {
            return;
        }
        stack.push(nodeEx);

        NodeList list = nodeEx.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node child = list.getNode(i);
            NodeEx childEx = getNodeEx(child);
            if (nodeEx.getDepth() < childEx.getDepth()) {
                calcBlockSize(child);
            }
        }
        Dimension dim = nodeEx.calcBlockSize();
        Rectangle rect = nodeEx.getBlockBounds();
        rect.width = dim.width;
        rect.height = dim.height;
        stack.pop();
    }
}
