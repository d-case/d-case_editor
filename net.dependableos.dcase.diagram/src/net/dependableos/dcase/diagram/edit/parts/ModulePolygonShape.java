/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts;

import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Renders a "Module" shape. 
 */
public class ModulePolygonShape extends PolygonShape {
    
    /**
     * A ratio of the width of the small rectangle.
     */
    private static final float SMALL_RECTANGLE_WIDTH_RATIO = 0.4f;
    
    /**
     * Size of the height of the small rectangle.
     */
    protected static final int SMALL_RECTANGLE_HEIGHT_SIZE = 15;
    
    /**
     * Sets the bounds of this Figure to the Rectangle rect.
     * @param rect The new bounds
     */
    @Override
    public void setBounds(Rectangle rect) {
        super.setBounds(rect);
        
        float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
        int inset1 = (int) Math.floor(lineInset);
        int inset2 = (int) Math.ceil(lineInset);
        
        Rectangle r = Rectangle.SINGLETON.setBounds(rect);
        r.x = inset1;
        r.y = inset2;
        r.width -= inset1 + inset2;
        r.height -= inset1 + inset2;
        
        // Map of point list.
        //
        // top1----------top2
        // |                |
        // main1---------top4----------------main2
        // |                                     |
        // |                                     |
        // |                                     |
        // |                                     |
        // main3-----------------------------main4

        // creates the points.
        Point top1 = new Point(0, 0);
        Point top2 = new Point((int) (r.width * SMALL_RECTANGLE_WIDTH_RATIO), 0);
        Point top4 = new Point((int) (r.width * SMALL_RECTANGLE_WIDTH_RATIO), SMALL_RECTANGLE_HEIGHT_SIZE);
        Point main1 = new Point(0, SMALL_RECTANGLE_HEIGHT_SIZE);
        Point main2 = new Point(r.width, SMALL_RECTANGLE_HEIGHT_SIZE);
        Point main3 = new Point(0, r.height);
        Point main4 = new Point(r.width, r.height);
        
        PointList points = new PointList();
        points.addPoint(top4);
        points.addPoint(top2);
        points.addPoint(top1);
        points.addPoint(main3);
        points.addPoint(main4);
        points.addPoint(main2);
        points.addPoint(main1);
        
        // draw the Figure on the rectangle.
        setPoints(points);
    }
}
