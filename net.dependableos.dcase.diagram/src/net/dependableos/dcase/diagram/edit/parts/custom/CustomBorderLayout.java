/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A custom border layout.
  */
public class CustomBorderLayout extends FlowLayout {

    /**
     * the figures to lays out.
     */
    private IFigure center, left, top, bottom, right;
    /**
     * the values of the spacings between each figure.
     */
    private int vSpacing = 0, hSpacing = 0;

    /**
     * true if the figure in center should stretches vertically.
     */
    private boolean stretchVertically = true;

    /**
     * the value of the ratio[panel-padding:panel].
     */
    private double paddingRatio = 1.0;

    /**
     * the value of the width of the aspect ratio.
     */
    private double aspectWidth = 1.0;

    /**
     * the value of the height of the aspect ratio.
     */
    private double aspectHeight = 1.0;



    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension calculateMinimumSize(IFigure container, int wHint, int hHint) {
        // returns a dimension of zero width and height. 
        return new Dimension();
    }



    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
        Insets border = container.getInsets();
        wHint = -1;
        hHint = -1;
        Dimension prefSize = new Dimension();
        int middleRowWidth = 0, middleRowHeight = 0;
        int rows = 0, columns = 0;

        if (top != null && top.isVisible()) {
            Dimension childSize = top.getPreferredSize(wHint, hHint);
            prefSize.setSize(childSize);
            rows += 1;
        }
        if (right != null && right.isVisible()) {
            Dimension childSize = right.getPreferredSize(wHint, hHint);
            middleRowWidth = childSize.width;
            middleRowHeight = childSize.height;
            prefSize.width += childSize.width;
            columns += 1;
        }
        if (bottom != null && bottom.isVisible()) {
            Dimension childSize = bottom.getPreferredSize(wHint, hHint);
            prefSize.width = Math.max(prefSize.width, childSize.width);
            prefSize.height += childSize.height;
            rows += 1;
        }
        if (left != null && left.isVisible()) {
            Dimension childSize = left.getPreferredSize(wHint, hHint);
            middleRowWidth += childSize.width;
            middleRowHeight = Math.max(childSize.height, middleRowHeight);
            columns += 1;
        }
        if (center != null && center.isVisible()) {
            Dimension childSize = center.getPreferredSize(wHint, hHint);
            middleRowWidth += childSize.width;
            middleRowHeight = Math.max(childSize.height, middleRowHeight);
            columns += 1;
        }

        if (columns > 0) {
            rows += 1;
        }

        prefSize.height += middleRowHeight + border.getHeight() + ((rows - 1) * vSpacing);
        prefSize.width = Math.max(prefSize.width, middleRowWidth) + border.getWidth() 
                        + ((columns - 1) * hSpacing);

        // applies the padding.
        prefSize.height /= paddingRatio;
        prefSize.width /= paddingRatio;

        // Applies the default aspect ratio.
        if (aspectWidth / aspectHeight != 1.0) {
            int height = prefSize.height;
            int width = prefSize.width;
            prefSize.width = Math.max(prefSize.width,
                    (int) Math.floor(height * aspectWidth / aspectHeight));
            prefSize.height = Math.max(prefSize.height,
                    (int) Math.floor(width * aspectHeight / aspectWidth));
        }


        return prefSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout(IFigure container) {
        Rectangle area = container.getClientArea();

        // applies the padding.
        if (paddingRatio > 0.0 && paddingRatio < 1.0) {
            area.x = area.x + (int) (area.width * (1.0 - paddingRatio) / 2);
            area.y = area.y + (int) (area.height * (1.0 - paddingRatio) / 2);
            area.width = (int) (area.width * paddingRatio);
            area.height = (int) (area.height * paddingRatio);
        }
        
        Rectangle rect = new Rectangle();

        Dimension childSize;
        
        int topheight = 0;
        int rightwidth = 0;
        if (top != null && top.isVisible()) {
        	Dimension rightSize = (right != null && right.isVisible()) ?
        			right.getPreferredSize():new Dimension();
            childSize = top.getPreferredSize(Math.max(0, area.width - rightSize.width - hSpacing), -1);
            rect.setLocation(area.x, area.y);
            rect.setSize(childSize);
            top.setBounds(rect);
            topheight = rect.height + vSpacing;
            area.y += topheight;
            area.height -= topheight;
        }
        if (right != null && right.isVisible()) {
            childSize = right.getPreferredSize(-1, Math.max(0, area.height + topheight));
            rect.setSize(childSize);
            rect.setLocation(area.x + area.width - rect.width, area.y - topheight);
            right.setBounds(rect);
            rightwidth = rect.width + hSpacing;
        }
        if (bottom != null && bottom.isVisible()) {
            childSize = bottom.getPreferredSize(Math.max(area.width, 0), -1);
            // lays out if height is not zero.
            if (childSize.height > 0) {
                rect.setSize(childSize);
                rect.width = area.width;
                rect.setLocation(area.x, area.y + area.height - rect.height);
                bottom.setBounds(rect);
                area.height -= rect.height + vSpacing;
            }
        }
        area.width -= rightwidth;
        if (left != null && left.isVisible()) {
            childSize = left.getPreferredSize(-1, Math.max(0, area.height));
            rect.setLocation(area.x, area.y);
            rect.width = childSize.width;
            rect.height = Math.max(0, area.height);
            left.setBounds(rect);
            area.x += rect.width + hSpacing;
            area.width -= rect.width + hSpacing;
        }
        if (center != null && center.isVisible()) {
            if (area.width < 0) {
                area.width = 0;
            }
            if (area.height < 0) {
                area.height = 0;
            }

            if (!stretchVertically) {
                // lays out the figure in center.
                childSize = center.getPreferredSize(Math.max(area.width, 0), -1);
                area.y += (int) Math.ceil((area.height - childSize.height) / 2.0);
                area.height = childSize.height;
            }

            center.setBounds(area);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(IFigure child) {
        if (center == child) {
            center = null;
        } else if (top == child) {
            top = null;
        } else if (bottom == child) {
            bottom = null;
        } else if (right == child) {
            right = null;
        } else if (left == child) {
            left = null;
        }
    }

    /**
     * Sets the location of the child.
     *
     * @param child the figure to lays out.
     * @param constraint the location.
     */
    public void setConstraint(IFigure child, Object constraint) {
        remove(child);
        super.setConstraint(child, constraint);
        if (constraint == null) {
            return;
        }
       
        switch (((Integer) constraint).intValue()) {
            case PositionConstants.CENTER :
                center = child;
                break;
            case PositionConstants.TOP :
                top = child;
                break;
            case PositionConstants.BOTTOM :
                bottom = child;
                break;
            case PositionConstants.RIGHT :
                right = child;
                break;
            case PositionConstants.LEFT :
                left = child;
                break;
            default :
                break;
        }
    }

    /**
     * Sets the values of the horizontal spacing between each figure.
     *
     * @param spacing the horizontal margin
     */
    public void setHorizontalSpacing(int spacing) {
        hSpacing = spacing;
    }

    /**
     * Sets the values of the vertical spacing between each figure.
     *
     * @param spacing the vertical spacing.
     */
    public void setVerticalSpacing(int spacing) {
        vSpacing = spacing;
    }

    /**
     * Sets the value of the ratio[panel-padding:panel].
     *
     * @param ratio the value of the ratio[panel-padding:panel].
     */
    public void setPaddingRatio(double ratio) {
        if (ratio > 0 && ratio <= 1.0) {
            paddingRatio = ratio;
        }
    }

    /**
     * Sets whether the figure in center should stretches vertically.
     *
     * @param stretch true  if and only if the figure in center should stretches vertically; false otherwise.
     */
    public void setStretchVertically(boolean stretch) {
        stretchVertically = stretch;
    }


    /**
     * Sets the aspect ratio.
     *
     * @param width the value of the width of the aspect ratio.
     * @param height the value of the height of the aspect ratio.
     */
    public void setAspectRatio(double width, double height) {
        if (width > 0) {
            aspectWidth = width;
        }
        if (height > 0) {
            aspectHeight = height;
        }
    }
}
