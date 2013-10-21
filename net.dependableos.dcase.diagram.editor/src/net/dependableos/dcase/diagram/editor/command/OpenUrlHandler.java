/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import java.net.MalformedURLException;
import java.net.URL;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 *  A handler to open attachment or risk analysis with the web browser.
 */
public class OpenUrlHandler extends AbstractHandler {

    /**
     * INTERNAL BROWSER ID.
     */
    private static final String CONST_INTERNAL_BROWSER_ID = "DCaseBrowser"; //$NON-NLS-1$

    /**
     * INTERNAL BROWSER NAME.
     */
    private static final String CONST_INTERNAL_BROWSER_NAME = "D-Case External File";

    /**
     * INTERNAL BROWSER TOOLTIP.
     */
    private static final String CONST_INTERNAL_BROWSER_TOOLTIP = "Showing External File";

    /**
     * the String that represents the name of the Attachment attribute.
     */
    private static final String CONST_URL_ATTRIBUTE_ATTACHMENT = "Attachment"; //$NON-NLS-1$

    /**
     * the String that represents the name of the Risk Analysis attribute.
     */
    private static final String CONST_URL_ATTRIBUTE_RISKANALYSIS = "RiskAnalysis"; //$NON-NLS-1$

    /**
     * the key of event parameter that contains url attribute name.
     */
    private static final String CONST_URL_ATTRIBUTE_NAME
            = "net.dependableos.dcase.diagram.editor.command.OpenUrl.parameter.Attribute"; //$NON-NLS-1$

    /**
     * the selected node.
     */
    private AbstractGraphicalEditPart selectedElement;

    /**
     * Opens attachment or risk analysis with the web browser.
     * 
     * @param event ExecutionEvent
     * @return the result of the execution.
     * @throws ExecutionException if an exception occurred during execution.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {

        selectedElement = getSelectedElement(event);

        if (selectedElement != null) {

            try {
                // gets the parameter from the event.
                String attributeName = event
                        .getParameter(CONST_URL_ATTRIBUTE_NAME);

                // gets the attribute.
                String attribute = getAttribute(attributeName);

                // gets the URL.
                URL url = getUrl(attribute, attributeName);

                // starts the web browser.
                startWebBrowser(url);

            } catch (DcaseRuntimeException dre) {
                // handles the runtime exception.
                MessageWriter.showErrorMessageBox(dre.getMessage());
            } catch (DcaseSystemException dse) {
                // handles the d-case system exception.
                MessageWriter.writeMessageToErrorLog(dse);
                MessageWriter.showMessageBoxSeeErroLog();
            }
        }
        return null;
    }

    /**
     * Returns the attachment URL.
     * Throws the exception if the value of the attachment is not URL format.
     * 
     * @param attribute the attribute.
     * @param attributeName Attachment or RiskAnalysis.
     * @return the URL.
     */
    private URL getUrl(String attribute, String attributeName) {
        URL url = null;
        try {
            url = new URL(attribute);
            MessageWriter.writeMessageToConsole(
                    "protocol=" + url.getProtocol(), MessageTypeImpl.DIAGNOSIS); //$NON-NLS-1$
            // check the protocol
            if (!DcaseEditorUtil.checkDcaseReferenceProtocol(url.getProtocol())) {
                throw new DcaseRuntimeException(NLS.bind(
                        Messages.OpenUrlHandler_6, url.getProtocol()), null,
                        null, 0, MessageTypeImpl.OPEN_URL_INVALID_URL);
            }

        } catch (MalformedURLException e) {
            throw new DcaseRuntimeException(NLS.bind(Messages.OpenUrlHandler_0,
                    attributeName), e, null, 0,
                    MessageTypeImpl.OPEN_URL_INVALID_URL);
        }
        return url;
    }

    /**
     * Returns the value of the attachment attribute.
     * 
     * @param eObj the EObject that represents a node or a link.
     * @return the value of the attachment attribute.
     */
    private String getAttachment(EObject eObj) {

        if (eObj instanceof BasicLink) {
            BasicLink basicLink = (BasicLink) eObj;
            return basicLink.getAttachment();
        } else if (eObj instanceof BasicNode) {
            BasicNode basicNode = (BasicNode) eObj;
            return basicNode.getAttachment();
        } else {
            throw new DcaseSystemException(Messages.OpenUrlHandler_1, null,
                    MessageTypeImpl.OPEN_URL_CRITICAL_FAILED);
        }
    }

    /**
     * Starts the web browser.
     * 
     * @param url the URL to open.
     */
    private void startWebBrowser(URL url) {
        IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench()
                .getBrowserSupport();
        if (browserSupport.isInternalWebBrowserAvailable()) {
            try {
                IWebBrowser browser = browserSupport.createBrowser(
                        IWorkbenchBrowserSupport.LOCATION_BAR 
                                | IWorkbenchBrowserSupport.NAVIGATION_BAR
                                | IWorkbenchBrowserSupport.AS_EDITOR,
                        CONST_INTERNAL_BROWSER_ID, CONST_INTERNAL_BROWSER_NAME,
                        CONST_INTERNAL_BROWSER_TOOLTIP);
                browser.openURL(url);
            } catch (PartInitException e) {
                throw new DcaseSystemException(Messages.COMMON_EXCEPTION_partInit, e,
                        MessageTypeImpl.OPEN_URL_CRITICAL_FAILED);
            }
        } else {
            throw new DcaseSystemException(Messages.OpenUrlHandler_4, null,
                    MessageTypeImpl.OPEN_URL_CRITICAL_FAILED);
        }
    }

    /**
     * Returns the selected edit part.
     * 
     * @param event the event.
     * @return element the selected edit part.
     */
    private AbstractGraphicalEditPart getSelectedElement(ExecutionEvent event) {
        ISelection selection = HandlerUtil.getActiveMenuSelection(event);
        AbstractGraphicalEditPart element = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof GraphicalEditPart
                    || structuredSelection.getFirstElement() instanceof ConnectionNodeEditPart) {
                element = (AbstractGraphicalEditPart) structuredSelection
                        .getFirstElement();
            }
        }
        return element;
    }

    /**
     * Returns the URL from risk analysis attribute of a Justification node.
     * Throws the exception if the specified EObject is not the instance of the Justification.
     * 
     * @param eObj the EObject
     * @return the String that represents URL.
     */
    private String getRiskAnalysis(EObject eObj) {

        if (eObj instanceof Justification) {
            Justification justificationNode = (Justification) eObj;
            return justificationNode.getRiskAnalysis();
        } else {
            throw new DcaseSystemException(Messages.OpenUrlHandler_7, null,
                    MessageTypeImpl.OPEN_URL_CRITICAL_FAILED);
        }
    }

    /**
     * Returns the value of the attribute from the selected edit part.
     * 
     * @param attributeName the name of the attribute.
     * @return the value of the attribute.
     */
    private String getAttribute(String attributeName) {

        String attribute = null;

        Object model = selectedElement.getModel();
        if (model instanceof ViewImpl) {
            ViewImpl view = (ViewImpl) model;
            EObject eObj = view.getElement();

            if (CONST_URL_ATTRIBUTE_ATTACHMENT.equals(attributeName)) {
                attribute = getAttachment(eObj);
            } else if (CONST_URL_ATTRIBUTE_RISKANALYSIS.equals(attributeName)) {
                attribute = getRiskAnalysis(eObj);
            }

            if (attribute == null || attribute.trim().length() == 0) {
                throw new DcaseRuntimeException(NLS.bind(
                        Messages.OpenUrlHandler_5, attributeName), null, null,
                        0, MessageTypeImpl.OPEN_URL_INVALID_URL);
            }

        } else {
            throw new DcaseSystemException(Messages.OpenUrlHandler_2, null,
                    MessageTypeImpl.OPEN_URL_CRITICAL_FAILED);
        }

        return attribute;
    }
}
