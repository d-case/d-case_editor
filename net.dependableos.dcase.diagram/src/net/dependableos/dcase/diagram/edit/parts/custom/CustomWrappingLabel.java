/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;

/**
 * A  custom wrapping label.
 */
public class CustomWrappingLabel extends WrappingLabel implements MouseMotionListener {

    /**
     * the background color of the label.
     */
    private static final Color BG_COLOR = new Color(null, 220, 220, 220);
    
    /**
     * the default background color of the label.
     */
    private Color backgroundColor = ColorConstants.white;
    
    /**
     * the auto setting flag for visible/invisible.
     */
    private boolean autoVisible = true;
    
    /**
     * constructor.
     */
    public CustomWrappingLabel() {
        super();
        addMouseMotionListener(this);
    }
    
    /**
     * constructor.
     * @param autoVisible The auto visible flag.
     */
    public CustomWrappingLabel(boolean autoVisible) {
        super();
        this.autoVisible = autoVisible;
        addMouseMotionListener(this);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel#setText(java.lang.String)
     */
    @Override
    public void setText(String text) {
        super.setText(text);
        if (autoVisible) {
            if (text == null || text.length() == 0) {
                setVisible(false);
            } else {
                setVisible(true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintClientArea(Graphics graphics) {
        if (!isSelected()) {
            graphics.pushState();
            graphics.setBackgroundColor(backgroundColor);
            graphics.fillRectangle(getVisibleTextBounds());
            graphics.popState();
            graphics.setForegroundColor(getForegroundColor());
        }
        super.paintClientArea(graphics);
    }

    /**
     * Returns the bounds of the visible text.
     * 
     * @return the bounds of the visible text.
     */
    private Rectangle getVisibleTextBounds() {
        Rectangle rect = getTextBounds().getIntersection(getClientArea());
        rect.width = Math.max(rect.width, getBounds().width);
        rect.height = Math.max(rect.height, getBounds().height);
        return rect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {

        Dimension dimension = super.getPreferredSize(wHint, hHint);

        // returns zero width and height if the text is empty.
        if (getText() == null || getText().trim().length() < 1) {
            dimension = new Dimension();
        }
        return dimension;
    }

    /**
     * Sets the background color.
     * @param color the color
     */
    public void setBackGroundColor(Color color) {
        backgroundColor = color;
        repaint();
    }
    
    /**
     * Sets the default background color.
     */
    public void setDefaultBGColor() {
        setBackGroundColor(ColorConstants.white);
    }
    
    /**
     * Sets the highlight background color.
     */
    public void setHighlightBGColor() {
        setBackGroundColor(BG_COLOR);
    }

    /**
     * The function that mouse dragged event.
     * @param arg0 the mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    /**
     * The function that mouse entered event.
     * @param arg0 the mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {
        setHighlightBGColor();
    }

    /**
     * The function that mouse exited event.
     * @param arg0 the mouse event.
     */
    @Override
    public void mouseExited(MouseEvent arg0) {
        setDefaultBGColor();
    }

    /**
     * The function that mouse hover event.
     * @param arg0 the mouse event.
     */
    @Override
    public void mouseHover(MouseEvent arg0) {
    }

    /**
     * The function that mouse moved event.
     * @param arg0 the mouse event.
     */
    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}

