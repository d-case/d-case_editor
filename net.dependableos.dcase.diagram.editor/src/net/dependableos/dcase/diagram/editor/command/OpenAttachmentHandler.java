/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.net.MalformedURLException;
import java.net.URL;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.osgi.util.NLS;

/**
 * The handler to open an attachment.
 */
public class OpenAttachmentHandler extends AbstractEditPartHandler {

    /**
     * execute {@link OpenUrlHandler} or {@link OpenDefaultEditorHandler}
     * dependent on the type of the selected node's attachment.
     * 
     * @param event ExecutionEvent An event
     * @return result of the execution
     * @throws ExecutionException if an exception occurred during execution OpenUrlHandler or OpenDefaultEditorHandler.
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        GraphicalEditPart editPart = getSelectedElement(event);
        String attachment = null;
        
        if (editPart == null) {
        	DcaseLinkEditPart linkEditPart = DcaseEditorUtil.getFirstCurrentSelectedLink();
        	if (linkEditPart != null) {
        		BasicLink basicLink = (BasicLink)DcaseEditorUtil.getElement(linkEditPart);
        		attachment = basicLink.getAttachment();
        	}
        } else {
            BasicNode basicNode = getBasicNode(editPart);
            attachment = basicNode.getAttachment();
        }
        
        if (attachment != null && attachment.length() > 0) {
            
            if (isUrl(attachment)) {
                // in the case of uri type, execute OpenUrlHandler.
                AbstractHandler openUrlHandler = new OpenUrlHandler();
                return openUrlHandler.execute(event);
            } else if (ModuleUtil.isWorkspaceReference(attachment)) {
                // in the case of Workspace type, execute OpenDefaultEditorHandler.
                AbstractHandler openDefaultEditorHandler = new OpenDefaultEditorHandler();
                return openDefaultEditorHandler.execute(event);
            } else {
                AbstractHandler openModuleHandler = new OpenModuleHandler();
                return openModuleHandler.execute(event);
            }
        } else {
            MessageWriter.showErrorMessageBox(NLS.bind(Messages.OpenUrlHandler_5, "Attachment")); //$NON-NLS-1$
            return null;
        }
    }
    
    /**
     * Checks if the attachment is URL or not.
     * 
     * @param attachment the attachment.
     * @return if the attachment is url: true.
     */
    private static boolean isUrl(String attachment) {
        URL url = null;
        try {
            url = new URL(attachment);
            MessageWriter.writeMessageToConsole(
                    "protocol=" + url.getProtocol(), MessageTypeImpl.DIAGNOSIS); //$NON-NLS-1$
            // check the protocol
            if (!DcaseEditorUtil.checkDcaseReferenceProtocol(url.getProtocol())) {
                MessageWriter.showErrorMessageBox(
                        NLS.bind(
                                Messages.OpenUrlHandler_6, url.getProtocol()));
                return false;
            }

        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
