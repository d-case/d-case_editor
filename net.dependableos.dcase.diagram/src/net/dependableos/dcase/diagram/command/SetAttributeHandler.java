/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */

package net.dependableos.dcase.diagram.command;

import net.dependableos.dcase.diagram.ui.AttributeDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Event;

/**
 * The base class of the handler classes for the command button in the attribute dialog.
 */

public abstract class SetAttributeHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        if (Dialog.OK == getDialog(event).open()) {
            
            return pressOk(event);
        }
        return null;
        
    }

    /**
     * Returns the dialog to input the attribute values.
     * 
     * @param event a event containing the caller attribute dialog.
     * @return the dialog.
     */
    protected abstract Dialog getDialog(ExecutionEvent event);

    /**
     * Notifies that the OK button of the dialog pressed.
     * @param event a event.
     * @return the result of the execution.
     */
    protected abstract Object pressOk(ExecutionEvent event);
    
    /**
     * Returns the caller attribute dialog containing by event object.
     * @param event a event object.
     * @return the caller attribute dialog.
     */
    protected static AttributeDialog getAttributeDialog(ExecutionEvent event) {
        AttributeDialogEvent attributeDialogEvent =  (AttributeDialogEvent) event.getTrigger();
        return attributeDialogEvent.getDialog();
    }
    
    /**
     * The inner event class to contain the caller attribute dialog. 
     */
    public static class AttributeDialogEvent extends Event {
        
        /**
         * the attribute dialog.
         */
        private AttributeDialog dialog;

        /**
         * Returns the attribute dialog.
         * @return the dialog
         */
        public AttributeDialog getDialog() {
            return dialog;
        }

        /**
         * Sets the attribute dialog.
         * @param dialog the dialog to set
         */
        public void setDialog(AttributeDialog dialog) {
            this.dialog = dialog;
        }
        
    }
}
