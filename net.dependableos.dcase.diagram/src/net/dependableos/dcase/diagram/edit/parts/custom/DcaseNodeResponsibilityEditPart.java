/*
 * Copyright (C) 2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.dependableos.dcase.diagram.edit.parts.DcaseEditPartFactory;
import net.dependableos.dcase.diagram.edit.policies.DcaseTextSelectionEditPolicy;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

/**
 * @generated
 */
public abstract class DcaseNodeResponsibilityEditPart extends CompartmentEditPart implements
        ITextAwareEditPart {

	/**
	 * Icon size.
	 */
	private static final int RESPONSIBILITY_ICON_WIDTH = 0;
	private static final int RESPONSIBILITY_ICON_HEIGHT = 64;
	
    /**
     * @generated NOT
     */
    // VISUAL_ID

    /**
     * @generated
     */
    private DirectEditManager manager;

    /**
     * @generated
     */
    protected IParser parser;

    /**
     * @generated
     */
    private List parserElements;

    /**
     * @generated
     */
    private String defaultText;

    /**
     * @generated
     */
    public DcaseNodeResponsibilityEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
                new DcaseTextSelectionEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
                new LabelDirectEditPolicy());
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                new NonResizableEditPolicy() {

                    protected List createSelectionHandles() {
                        List handles = new ArrayList();
                        NonResizableHandleKit.addMoveHandle(
                                (GraphicalEditPart) getHost(), handles);
                        ((MoveHandle) handles.get(0)).setBorder(null);
                        return handles;
                    }

                    public Command getCommand(Request request) {
                        return null;
                    }

                    public boolean understandsRequest(Request request) {
                        return false;
                    }
                });
    }

    /**
     * @generated
     */
    protected String getLabelTextHelper(IFigure figure) {
        if (figure instanceof WrappingLabel) {
            return ((WrappingLabel) figure).getText();
        } else {
            return ((Label) figure).getText();
        }
    }

    /**
     * @generated
     */
    protected void setLabelTextHelper(IFigure figure, String text) {
        if (figure instanceof WrappingLabel) {
            ((WrappingLabel) figure).setText(text);
        } else {
            ((Label) figure).setText(text);
        }
    }

    /**
     * @generated
     */
    protected Image getLabelIconHelper(IFigure figure) {
        if (figure instanceof WrappingLabel) {
            return ((WrappingLabel) figure).getIcon();
        } else {
            return ((Label) figure).getIcon();
        }
    }

    /**
     * @generated
     */
    protected void setLabelIconHelper(IFigure figure, Image icon) {
        if (figure instanceof WrappingLabel) {
            ((WrappingLabel) figure).setIcon(icon);
        } else {
            ((Label) figure).setIcon(icon);
        }
    }

    /**
     * @generated
     */
    public void setLabel(WrappingLabel figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    /**
     * @generated
     */
    protected List getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
        return null;
    }

    /**
     * @generated
     */
    protected EObject getParserElement() {
        return resolveSemanticElement();
    }

    /**
     * @generated NOT
     */
    private String getResponsibilityString(int index) {
        String text = null;
        EObject parserElement = getParserElement();
        if (parserElement != null && getParser() != null) {
            text = getParser().getPrintString(
                    new EObjectAdapter(parserElement),
                    getParserOptions().intValue());
        }
        if (text != null) {
        	String textArray[] = text.split(";"); //$NON-NLS-1$
        	if (textArray.length > index) {
        		text = textArray[index];
        	} else {
        		text = ""; //$NON-NLS-1$
        	}
        }
        return text;
    }
    
    /**
     * @generated NOT
     */
    protected Image getLabelIcon() {
    	Image image = null;
    	String iconPath = getResponsibilityString(1);
    	if (iconPath != null && iconPath.length() > 0) {
        	Display display = Display.getDefault();
            String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString() + iconPath;
            try {
            	Image orgImage = new Image(display, path);
            	Rectangle orgSize = orgImage.getBounds();
            	int width = RESPONSIBILITY_ICON_WIDTH;
            	int height = RESPONSIBILITY_ICON_HEIGHT;
            	if (width <= 0) {
            		width = orgSize.width * height / orgSize.height;
            	}
            	if (height <= 0) {
            		height = orgSize.height * width / orgSize.width;
            	}
            	image = new Image(display, width, height);
            	GC gc = new GC(image);
            	gc.setAntialias(SWT.ON);
            	gc.setInterpolation(SWT.HIGH);
            	gc.drawImage(orgImage, 0, 0, orgSize.width, orgSize.height, 0, 0, width, height);
            	gc.dispose();
            	orgImage.dispose(); // don't forget about me!
            } catch (Exception e) {
            	// ignore
            }
    	}
        return image;
    }

    /**
     * @generated NOT
     */
    protected String getLabelText() {
        String text = getResponsibilityString(0);
        if (text == null || text.length() == 0) {
            text = defaultText;
        }
        return (text != null && text.length() > 0) ?
        		" [" + text + "]":text; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @generated
     */
    public void setLabelText(String text) {
        setLabelTextHelper(getFigure(), text);
        Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pdEditPolicy instanceof DcaseTextSelectionEditPolicy) {
            ((DcaseTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
        }
        Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
        if (sfEditPolicy instanceof DcaseTextSelectionEditPolicy) {
            ((DcaseTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
        }
    }

    /**
     * @generated
     */
    public String getEditText() {
        if (getParserElement() == null || getParser() == null) {
            return ""; //$NON-NLS-1$
        }
        return getParser().getEditString(
                new EObjectAdapter(getParserElement()),
                getParserOptions().intValue());
    }

    /**
     * @generated
     */
    protected boolean isEditable() {
        return getParser() != null;
    }

    /**
     * @generated
     */
    public ICellEditorValidator getEditTextValidator() {
        return new ICellEditorValidator() {

            public String isValid(final Object value) {
                if (value instanceof String) {
                    final EObject element = getParserElement();
                    final IParser parser = getParser();
                    try {
                        IParserEditStatus valid = (IParserEditStatus) getEditingDomain()
                                .runExclusive(new RunnableWithResult.Impl() {

                                    public void run() {
                                        setResult(parser.isValidEditString(
                                                new EObjectAdapter(element),
                                                (String) value));
                                    }
                                });
                        return valid.getCode() == ParserEditStatus.EDITABLE ? null
                                : valid.getMessage();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

                // shouldn't get here
                return null;
            }
        };
    }

    /**
     * @generated
     */
    public IContentAssistProcessor getCompletionProcessor() {
        if (getParserElement() == null || getParser() == null) {
            return null;
        }
        return getParser().getCompletionProcessor(
                new EObjectAdapter(getParserElement()));
    }

    /**
     * @generated
     */
    public ParserOptions getParserOptions() {
        return ParserOptions.NONE;
    }

    /**
     * @generated
     */
    public abstract IParser getParser();

    /**
     * @generated NOT
     */
    protected DirectEditManager getManager() {
        if (manager == null) {
            // enables to edit a wrappable text.
            setManager(new TextDirectEditManager(this,
                    WrapTextCellEditor.class, DcaseEditPartFactory
                            .getTextCellEditorLocator(this)));
        }
        return manager;
    }

    /**
     * @generated
     */
    protected void setManager(DirectEditManager manager) {
        this.manager = manager;
    }

    /**
     * @generated
     */
    protected void performDirectEdit() {
        getManager().show();
    }

    /**
     * @generated
     */
    protected void performDirectEdit(Point eventLocation) {
        if (getManager().getClass() == TextDirectEditManager.class) {
            ((TextDirectEditManager) getManager()).show(eventLocation
                    .getSWTPoint());
        }
    }

    /**
     * @generated
     */
    private void performDirectEdit(char initialCharacter) {
        if (getManager() instanceof TextDirectEditManager) {
            ((TextDirectEditManager) getManager()).show(initialCharacter);
        } else {
            performDirectEdit();
        }
    }

    /**
     * @generated
     */
    protected void performDirectEditRequest(Request request) {
        final Request theRequest = request;
        try {
            getEditingDomain().runExclusive(new Runnable() {

                public void run() {
                    if (isActive() && isEditable()) {
                        if (theRequest
                                .getExtendedData()
                                .get(
                                        RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
                            Character initialChar = (Character) theRequest
                                    .getExtendedData()
                                    .get(
                                            RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
                            performDirectEdit(initialChar.charValue());
                        } else if ((theRequest instanceof DirectEditRequest)
                                && (getEditText().equals(getLabelText()))) {
                            DirectEditRequest editRequest = (DirectEditRequest) theRequest;
                            performDirectEdit(editRequest.getLocation());
                        } else {
                            performDirectEdit();
                        }
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @generated
     */
    protected void refreshVisuals() {
        super.refreshVisuals();
        refreshLabel();
        refreshFont();
        refreshFontColor();
        refreshUnderline();
        refreshStrikeThrough();
    }

    /**
     * @generated
     */
    protected void refreshLabel() {
    	Image iconImage = getLabelIcon();
        setLabelIconHelper(getFigure(), iconImage);
		setLabelTextHelper(getFigure(), (iconImage != null) ? "":getLabelText()); //$NON-NLS-1$
        Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pdEditPolicy instanceof DcaseTextSelectionEditPolicy) {
            ((DcaseTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
        }
        Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
        if (sfEditPolicy instanceof DcaseTextSelectionEditPolicy) {
            ((DcaseTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
        }
    }

    /**
     * @generated
     */
    protected void refreshUnderline() {
        FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
                NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && getFigure() instanceof WrappingLabel) {
            ((WrappingLabel) getFigure()).setTextUnderline(style.isUnderline());
        }
    }

    /**
     * @generated
     */
    protected void refreshStrikeThrough() {
        FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
                NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && getFigure() instanceof WrappingLabel) {
            ((WrappingLabel) getFigure()).setTextStrikeThrough(style
                    .isStrikeThrough());
        }
    }

    /**
     * @generated
     */
    protected void refreshFont() {
        FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
                NotationPackage.eINSTANCE.getFontStyle());
        if (style != null) {
            FontData fontData = new FontData(style.getFontName(), style
                    .getFontHeight(), (style.isBold() ? SWT.BOLD : SWT.NORMAL)
                    | (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
            setFont(fontData);
        }
    }

    /**
     * @generated
     */
    protected void setFontColor(Color color) {
        getFigure().setForegroundColor(color);
    }

    /**
     * @generated
     */
    protected void addSemanticListeners() {
        if (getParser() instanceof ISemanticParser) {
            EObject element = resolveSemanticElement();
            parserElements = ((ISemanticParser) getParser())
                    .getSemanticElementsBeingParsed(element);
            for (int i = 0; i < parserElements.size(); i++) {
                addListenerFilter(
                        "SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
            }
        } else {
            super.addSemanticListeners();
        }
    }

    /**
     * @generated
     */
    protected void removeSemanticListeners() {
        if (parserElements != null) {
            for (int i = 0; i < parserElements.size(); i++) {
                removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
            }
        } else {
            super.removeSemanticListeners();
        }
    }

    /**
     * @generated
     */
    protected AccessibleEditPart getAccessibleEditPart() {
        if (accessibleEP == null) {
            accessibleEP = new AccessibleGraphicalEditPart() {

                public void getName(AccessibleEvent e) {
                    e.result = getLabelTextHelper(getFigure());
                }
            };
        }
        return accessibleEP;
    }

    /**
     * @generated
     */
    private View getFontStyleOwnerView() {
        return getPrimaryView();
    }

    /**
     * @generated
     */
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("PrimaryView"); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    protected void handleNotificationEvent(Notification event) {
        Object feature = event.getFeature();
        if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
            Integer c = (Integer) event.getNewValue();
            setFontColor(DiagramColorRegistry.getInstance().getColor(c));
        } else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(
                feature)) {
            refreshUnderline();
        } else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough()
                .equals(feature)) {
            refreshStrikeThrough();
        } else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(
                feature)
                || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(
                        feature)
                || NotationPackage.eINSTANCE.getFontStyle_Bold()
                        .equals(feature)
                || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(
                        feature)) {
            refreshFont();
        } else {
            if (getParser() != null
                    && getParser().isAffectingEvent(event,
                            getParserOptions().intValue())) {
                refreshLabel();
            }
            if (getParser() instanceof ISemanticParser) {
                ISemanticParser modelParser = (ISemanticParser) getParser();
                if (modelParser.areSemanticElementsAffected(null, event)) {
                    removeSemanticListeners();
                    if (resolveSemanticElement() != null) {
                        addSemanticListeners();
                    }
                    refreshLabel();
                }
            }
        }
        super.handleNotificationEvent(event);
    }

    /**
     * @generated
     */
    protected IFigure createFigure() {
        // Parent should assign one using setLabel() method
        return null;
    }

}
