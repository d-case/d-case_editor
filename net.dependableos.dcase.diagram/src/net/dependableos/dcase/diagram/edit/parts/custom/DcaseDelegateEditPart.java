/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import org.eclipse.swt.graphics.Color;

/**
 * An interface represents a editpart of d-case.
 */
public interface DcaseDelegateEditPart {

    /**
     * Sets the foreground color.
     * 
     * @param color the foreground color.
     */
    void setForegroundColorEx(Color color);

    /**
     * Returns the foreground color.
     * 
     * @return the foreground color.
     */
    Color getForegroundColorEx();
 
    /**
     * Sets the line width.
     * 
     * @param width the line width pixel.
     */
    void setLineWidthEx(int width);

    /**
     * Returns the line width pixel.
     * 
     * @return the line width.
     */
    int getLineWidthEx();
}
