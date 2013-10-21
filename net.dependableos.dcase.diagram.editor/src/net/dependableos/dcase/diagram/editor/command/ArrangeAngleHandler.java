/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

/**
 * The handler of Arrange angle command.
 */
public class ArrangeAngleHandler extends AbstractHandler {

    /**
     * Execute arrange of all layout.
     * 
     * @param event the event argument.
     * @throws ExecutionException the execution exception.
     * @return null.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        if (HandlerUtil.matchesRadioState(event)) {
            return null; // we are already in the updated state - do nothing
        }
        
        // Set Arrange angle.
        String paramValue = event.getParameter(RadioState.PARAMETER_ID);

        // and finally update the current state
        HandlerUtil.updateRadioState(event.getCommand(), paramValue);
        
        return null;
    }

}
