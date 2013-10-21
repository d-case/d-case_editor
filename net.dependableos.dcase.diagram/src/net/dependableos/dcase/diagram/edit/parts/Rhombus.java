/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts;

import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
  * Renders a rhombus shape. 
 */
public class Rhombus extends PolygonShape {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBounds(Rectangle rect) {
        super.setBounds(rect);
        float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
        int inset1 = (int) Math.floor(lineInset);
        int inset2 = (int) Math.ceil(lineInset);

        Rectangle r = Rectangle.SINGLETON.setBounds(rect);
        r.x = inset1;
        r.y = inset1;
        r.width -= inset1 + inset2;
        r.height -= inset1 + inset2;

        PointList points = new PointList();
        points.addPoint(r.getLeft());
        points.addPoint(r.getTop());
        points.addPoint(r.getRight());
        points.addPoint(r.getBottom());
        setPoints(points);
    }
}
