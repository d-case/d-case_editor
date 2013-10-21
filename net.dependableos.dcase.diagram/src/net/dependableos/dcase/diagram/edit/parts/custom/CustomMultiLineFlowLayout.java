/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import java.util.List;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * A custom layout for wrapping label.
 */
public class CustomMultiLineFlowLayout extends FlowLayout {

    /* (non-Javadoc)
     * @see org.eclipse.draw2d.FlowLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure, int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Dimension calculatePreferredSize(IFigure container, int wHint,
            int hHint) {
        
        if (wHint > -1) {
            wHint = Math.max(0, wHint - container.getInsets().getWidth());
        }
        if (hHint > -1) {
            hHint = Math.max(0, hHint - container.getInsets().getHeight());
        }
        
        if (isHorizontal()) {
            hHint = -1;
        } else {
            wHint = -1;
        }

        Dimension prefSize = new Dimension();

        List children = container.getChildren();
        int width = 0;
        int height = 0;

        IFigure child;
        Dimension childSize;

        for (int i = 0; i < children.size(); i++) {
            child = (IFigure) children.get(i);
            childSize = transposer.t(getChildSize(child, wHint, hHint));
            if (i == 0) {
                width = childSize.width;
                height = childSize.height;

            } else {
                width = Math.max(childSize.width, width);

                if (childSize.height > 0) {
                    height += childSize.height + getMajorSpacing();
                }
            }
        }

        prefSize.height = height;
        prefSize.width = width;

        prefSize = transposer.t(prefSize);
        prefSize.width += container.getInsets().getWidth();
        prefSize.height += container.getInsets().getHeight();
        prefSize.union(getBorderPreferredSize(container));

        return prefSize;
    }

}
