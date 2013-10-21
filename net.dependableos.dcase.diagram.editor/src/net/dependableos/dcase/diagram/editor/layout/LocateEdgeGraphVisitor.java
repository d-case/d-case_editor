/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;

/**
 * This class locates edges.
 */
public class LocateEdgeGraphVisitor extends DcaseGraphVisitor {

    /**
     * the edge direction - right.
     */
    private static final int LEFT_TO_RIGHT = 1;
    /**
     * the edge direction - left.
     */
    private static final int RIGHT_TO_LEFT = 2;
    /**
     * the edge direction - top.
     */
    private static final int BOTTOM_TO_TOP = 3;
    /**
     * the edge direction - bottom.
     */
    private static final int TOP_TO_BOTTOM = 4;
    /**
     * the gap.
     */
    private static final int GAP = 3;

    /**
     * Locates the edges.
     * 
     * @param graph the direct graph.
     */
    @Override
    protected void visit(DcaseDirectedGraph graph) {
        super.visit(graph);
        locateEdge();
    }

    /**
     * Locates the edges.
     */
    private void locateEdge() {
        EdgeList list = getGraph().getEdgeList();
        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.getEdge(i);
            Node source = edge.source;
            Node target = edge.target;
            Point start = new Point();
            Point end = new Point();
            PointList mid = new PointList();
            int direction = getDirection(source, target);
            switch (direction) {
            case LEFT_TO_RIGHT:
                start.x = source.x + source.width;
                start.y = source.y + source.height / 2;
                end.x = target.x;
                end.y = target.y + target.height / 2;
                break;
            case RIGHT_TO_LEFT:
                start.x = source.x;
                start.y = source.y + source.height / 2;
                end.x = target.x + target.width;
                end.y = target.y + target.height / 2;
                break;
            case BOTTOM_TO_TOP:
                start.x = source.x + source.width / 2;
                start.y = source.y;
                end.x = target.x + target.width / 2;
                end.y = target.y + target.height;
                break;
            default:
                start.x = source.x + source.width / 2;
                start.y = source.y + source.height;
                end.x = target.x + target.width / 2;
                end.y = target.y;
            }

            if (getNodeEx(target).getLooped()) {
                if (direction == BOTTOM_TO_TOP || direction == RIGHT_TO_LEFT) {
                    if (Math.abs(start.x - end.x) < GAP) {
                        end.x = target.x + target.width / 2;
                        end.y = target.y + target.height;

                        int xPoint = start.x - source.width / 2 - VERTICAL_SPACING;
                        mid.addPoint(xPoint, start.y - HORIZONTAL_SPACING / 2);
                        mid.addPoint(xPoint, end.y + (start.y - end.y) / 2);
                        mid.addPoint(xPoint, end.y + HORIZONTAL_SPACING / 2);
                    } else if (Math.abs(start.y - end.y) < GAP) {
                        end.x = target.x + target.width;
                        end.y = target.y + target.height / 2;

                        int yPoint = start.y - source.height / 2
                                - HORIZONTAL_SPACING;
                        mid.addPoint(start.x - VERTICAL_SPACING / 2, yPoint);
                        mid.addPoint(end.x + (start.x - end.x) / 2, yPoint);
                        mid.addPoint(end.x + VERTICAL_SPACING / 2, yPoint);
                    }
                }
            }
            edge.getPoints().removeAllPoints();
            edge.getPoints().addPoint(start);
            for (int j = 0; j < mid.size(); j++) {
                edge.getPoints().addPoint(mid.getPoint(j));
            }
            edge.getPoints().addPoint(end);
            edge.getPoints();
        }
    }

    /**
     * Returns the direction of a link.
     * 
     * @param source the source.
     * @param target the target.
     * @return the direction of a link.
     */
    private int getDirection(Node source, Node target) {
        int result = 0;
        if (ArrangeAngle.createInstance().getAngle() == ArrangeAngle.Direction.Vertical) {
            if (DcaseDirectedGraph.isEast(target)) {
                if (source.x < target.x) {
                    result = LEFT_TO_RIGHT;
                } else {
                    result = RIGHT_TO_LEFT;
                }
            } else {
                if (source.y > target.y) {
                    result = BOTTOM_TO_TOP;
                } else {
                    result = TOP_TO_BOTTOM;
                }
            }
        } else {
            if (!DcaseDirectedGraph.isEast(target)) {
                if (source.x < target.x) {
                    result = LEFT_TO_RIGHT;
                } else {
                    result = RIGHT_TO_LEFT;
                }
            } else {
                if (source.y > target.y) {
                    result = BOTTOM_TO_TOP;
                } else {
                    result = TOP_TO_BOTTOM;
                }
            }
        }
        return result;
    }
}
