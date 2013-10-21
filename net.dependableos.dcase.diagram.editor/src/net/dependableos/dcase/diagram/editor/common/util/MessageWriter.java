/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.common.util;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.MESSAGE_OUTPUT_LEVEL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;


import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.IFunctionType;
import net.dependableos.dcase.diagram.common.util.IMessageType;
import net.dependableos.dcase.diagram.common.util.MessageLevel;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * A class to output messages.
 */
public final class MessageWriter {

    /**
     * the default value of the message output level.
     */
    private static final MessageLevel DEFAULT_OUTPUT_LEVEL = MessageLevel.INFORMATION;

    /**
     * the value of the message output level.
     */
    private static final MessageLevel OUTPUT_LEVEL = getMessageLevel();

    /**
     * Returns the message level.
     * 
     * @return the message level.
     */
    private static MessageLevel getMessageLevel() {

        MessageLevel messageLevel = DEFAULT_OUTPUT_LEVEL;

        // gets the message level of system property.
        String levelProperty = PropertyUtil
                .getSystemProperty(MESSAGE_OUTPUT_LEVEL);
        if (levelProperty != null) {

            // tests whether the value of the property is valid.
            EnumSet<MessageLevel> levelSet = EnumSet.allOf(MessageLevel.class);
            for (MessageLevel level : levelSet) {
                if (level.getName().equalsIgnoreCase(levelProperty)) {
                    messageLevel = level;
                    break;
                }
            }
        }

        return messageLevel;
    }

    /**
     * Constructor.
     */
    private MessageWriter() {
        super();
    }

    /**
     * Outputs the message to the console.
     * 
     * @param message the message.
     * @param messageType the message type.
     */
    public static void writeMessageToConsole(String message,
            IMessageType messageType) {

        if (enableWrite(messageType.getMessageLevel())) {

            // gets the console.
            IConsole[] consoles = ConsolePlugin.getDefault()
                    .getConsoleManager().getConsoles();
            MessageConsole console = null;
            for (int i = 0; i < consoles.length; i++) {
                if (Messages.MessageWriter_2.equals(consoles[i].getName())) {
                    console = (MessageConsole) consoles[i];
                }
            }
            if (console == null) {
                console = new MessageConsole(Messages.MessageWriter_2, null);
            }

            // shows the console.
            IConsoleManager manager = ConsolePlugin.getDefault()
                    .getConsoleManager();
            manager.addConsoles(new IConsole[] { console });
            manager.showConsoleView(console);

            // outputs the message.
            if (message == null) {
                message = Messages.MessageWriter_0;
            }
            MessageConsoleStream stream = console.newMessageStream();
            stream.println(getDateTime()
                    + " " + DcaseDiagramEditorPlugin.ID + " " //$NON-NLS-1$ //$NON-NLS-2$
                    + message);

        }
    }

    /**
     * Outputs the exception to the Problems View.
     * 
     * @param exception the exception.
     */
    public static void writeMessageToProblemsView(
            DcaseRuntimeException exception) {

        if (enableWrite(exception.getMessageType().getMessageLevel())) {

            String message = exception.getMessage();
            if (message == null) {
                message = Messages.MessageWriter_0;
            }

            // tests whether the resource and marker ID are valid.
            String markerId = exception.getMessageType().getFunctionType()
                    .getMarkerId();
            if (exception.getResource() != null && markerId != null) {

                try {
                    // creates the marker and initializes it.
                    IMarker marker = exception.getResource().createMarker(
                            markerId);

                    // sets severity.
                    int severity = getMarkerSeverity(exception.getMessageType()
                            .getMessageLevel());
                    marker.setAttribute(IMarker.SEVERITY, severity);

                    // sets the line number.
                    marker.setAttribute(IMarker.LINE_NUMBER, exception
                            .getLineNumber());

                    // sets the message.
                    marker.setAttribute(IMarker.MESSAGE, message);

                } catch (Exception e) {
                    throw new DcaseSystemException(null, e,
                            MessageTypeImpl.UNDEFINED);
                }

            } else {
                // outputs to the console if the resource or marker ID is invalid.
                writeMessageToConsole(message, exception.getMessageType());
            }

        }
    }

    /**
     * Returns the severity of the specified message level.
     * 
     * @param level the message level.
     * @return the severity.
     */
    private static int getMarkerSeverity(MessageLevel level) {
        int severity;
        switch (level) {
            case VERBOSE:
            case INFORMATION:
                severity = IMarker.SEVERITY_INFO;
                break;
            case WARNING:
                severity = IMarker.SEVERITY_WARNING;
                break;
            case ERROR:
            case CRITICAL:
            default:
                severity = IMarker.SEVERITY_ERROR;
        }

        return severity;
    }

    /**
     * Outputs the exception to the Error Log.
     * 
     * @param exception the exception.
     */
    public static void writeMessageToErrorLog(DcaseSystemException exception) {

        if (enableWrite(exception.getMessageType().getMessageLevel())) {

            // sets the status.
            int status = getStatus(exception.getMessageType().getMessageLevel());

            // outputs.
            String message = exception.getMessage();
            if (message == null) {
                message = Messages.MessageWriter_0;
            }
            ILog log = DcaseDiagramEditorPlugin.getInstance().getLog();
            log.log(new Status(status, DcaseDiagramEditorPlugin.ID, 0, message,
                    exception));
        }
    }

    /**
     * Returns the status of the specified message level.
     * 
     * @param level the message level.
     * @return the status.
     */
    private static int getStatus(MessageLevel level) {
        int status;
        switch (level) {
            case VERBOSE:
            case INFORMATION:
                status = IStatus.INFO;
                break;
            case WARNING:
                status = IStatus.WARNING;
                break;
            case ERROR:
            case CRITICAL:
            default:
                status = IStatus.ERROR;
        }

        return status;
    }

    /**
     * Clears the markers.
     * 
     * @param iFile the file.
     * @param functionType the function type.
     */
    public static void clearMarkers(IFile iFile, IFunctionType functionType) {
        try {
            String markerId = functionType.getMarkerId();
            if (markerId != null) {
                iFile.deleteMarkers(markerId, true, 0);
            }
        } catch (Exception e) {
            throw new DcaseSystemException(null, e, MessageTypeImpl.UNDEFINED);
        }
    }

    /**
     * Shows the message box that urge to see the Problems View.
     */
    public static void showMessageBoxSeeProblems() {
        showMessageBoxSeeProblems(DcaseEditorUtil.getActiveWindowShell());
    }

    /**
     * Shows the message box that urge to see the Problems View.
     * 
     * @param shell the parent.
     */
    public static void showMessageBoxSeeProblems(Shell shell) {
        MessageBox box = new MessageBox(shell, SWT.OK
                | SWT.ICON_ERROR);
        box.setText(Messages.MessageWriter_1);
        box.setMessage("Please watch \"Problems View\"."); //$NON-NLS-1$
        box.open();
    }

    /**
     * Shows the message box that urge to see the Error Log.
     */
    public static void showMessageBoxSeeErroLog() {
        showMessageBoxSeeErroLog(DcaseEditorUtil.getActiveWindowShell());
    }

    /**
     * Shows the message box that urge to see the Error Log.
     * 
     * @param shell the parent.
     */
    public static void showMessageBoxSeeErroLog(Shell shell) {
        MessageBox box = new MessageBox(shell, SWT.OK
                | SWT.ICON_ERROR);
        box.setText(Messages.MessageWriter_1);
        box.setMessage("Please watch \"Error Log View\"."); //$NON-NLS-1$
        box.open();
    }

    /**
     * Shows the error message.
     * 
     * @param message the message.
     */
    public static void showErrorMessageBox(String message) {
        showErrorMessageBox(message, DcaseEditorUtil.getActiveWindowShell());
    }
    
    /**
     * Shows the error message.
     * 
     * @param message the message.
     * @param shell the parent.
     */
    public static void showErrorMessageBox(String message, Shell shell) {
        MessageBox box = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
        box.setText(Messages.MessageWriter_1);
        box.setMessage(message);
        box.open();
    }
    
    /**
     * Tests whether the specified message level is to output.
     * 
     * @param level the message level.
     * @return true if and only if the specified message level is to output;false otherwise.
     */
    private static boolean enableWrite(MessageLevel level) {
        if (level.compareTo(OUTPUT_LEVEL) < 0) {
            return false;
        }
        return true;
    }

    /**
     * Returns the string that represents the current date time.
     * 
     * @return the string that represents the current date time.
     */
    private static String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dfm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //$NON-NLS-1$
        return dfm.format(date);
    }

}
