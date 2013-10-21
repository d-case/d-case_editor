/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import org.eclipse.swt.graphics.Color;

/**
 * An interface represents a node editpart.
 */
public interface DcaseDelegateNodeEditPart {

    /**
     * Sets the background color.
     * 
     * @param color the background color.
     */
    void setBackgroundColorEx(Color color);

    /**
     * Returns the background color.
     * 
     * @return the background color.
     */
    Color getBackgroundColorEx();

}
