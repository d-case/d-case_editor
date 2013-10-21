/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;

/**
 * Performs a graph layout of a DcaseDirectedGraph.
 */
public class DcaseDirectedGraphLayout extends DirectedGraphLayout {

    /**
     * the list of the graph visitors.
     */
    private List<DcaseGraphVisitor> steps = new ArrayList<DcaseGraphVisitor>();

    /**
     * Creates the instance and initializes it.
     */
    public DcaseDirectedGraphLayout() {
        steps.add(new CollectNodeGraphVisitor());
        steps.add(new CalcNodeSizeGraphVisitor());
        steps.add(new LocateNodeGraphVisitor());
        steps.add(new LocateEdgeGraphVisitor());
    }

    /**
     * Lays out the given graph.
     * 
     * @param graph the graph to layout.
     * @see org.eclipse.draw2d.graph#visit(DirectedGraph)
     */
    @Override
    public void visit(DirectedGraph graph) {
        super.visit(graph);

        DcaseDirectedGraph ddg = new DcaseDirectedGraph(graph);
        for (int i = 0; i < steps.size(); i++) {
            DcaseGraphVisitor visitor = (DcaseGraphVisitor) steps.get(i);
            visitor.visit(ddg);
        }
    }
}
