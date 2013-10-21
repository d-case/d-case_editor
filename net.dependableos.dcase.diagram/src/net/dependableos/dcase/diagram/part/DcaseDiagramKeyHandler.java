/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.part;


import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * A key handler request the editpart to perform key event.
 */
public class DcaseDiagramKeyHandler extends KeyHandler {

    /**
     * the viewer.
     */
    private GraphicalViewer viewer;


    /**
     * Allocates a DcaseDiagramKeyHandler object.
     * 
     * @param viewer the viewer.
     */
    public DcaseDiagramKeyHandler(GraphicalViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean keyPressed(KeyEvent event) {
        switch (event.keyCode) {
            case SWT.CR:
            case SWT.KEYPAD_CR:
                GraphicalEditPart editPart = (GraphicalEditPart) viewer
                        .getFocusEditPart();
                if (editPart instanceof DcaseNodeEditPart
                        || editPart instanceof ArgumentEditPart) {
                    // Create a Open Request and Perform Request
                    Request request = new Request(RequestConstants.REQ_OPEN);
                    editPart.performRequest(request);
                    return true;
                }
            default:

        }
        return super.keyPressed(event);
    }
}
