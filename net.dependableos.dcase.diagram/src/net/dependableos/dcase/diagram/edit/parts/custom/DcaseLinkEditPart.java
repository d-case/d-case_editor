/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
 package net.dependableos.dcase.diagram.edit.parts.custom;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * An abstract class represents a link editpart.
 */
public abstract class DcaseLinkEditPart extends ConnectionNodeEditPart
        implements DcaseDelegateEditPart {
    /**
     * the foreground color.
     */
    private Color foreGroundColor = null;

    /**
     * Allocates a DcaseLinkEditPart object.
     * 
     * @param view owned view by this edit part 
     */
    public DcaseLinkEditPart(View view) {
        super(view);
    }

    /**
     * Sets the foreground color.
     * 
     * @param color the foreground color.
     */
    public void setForegroundColorEx(Color color) {
        foreGroundColor = color;
        setForegroundColor(color);
    }

    /**
     * Returns the foreground color.
     * 
     * @return the foreground color.
     */
    public Color getForegroundColorEx() {
        return getFigure().getForegroundColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshForegroundColor() {
        if (foreGroundColor != null) {
            setForegroundColor(foreGroundColor);
        } else {
            super.refreshForegroundColor();
        }
    }

    /**
     * Returns the line width.
     * 
     * @return the line width.
     */
    public int getLineWidthEx() {
        return super.getLineWidth();
    }

    /**
     * Sets the line width.
     * 
     * @param width the line width.
     */
    public void setLineWidthEx(int width) {
        if (width < 0) {
            width = 1;
        }
        PolylineConnectionEx conn = (PolylineConnectionEx) getFigure();
        conn.setLineWidth(getMapMode().DPtoLP(width));
    }
}
