/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;




import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeDescEditPart;
import net.dependableos.dcase.diagram.edit.policies.DcaseTextSelectionEditPolicy;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;
import net.dependableos.dcase.diagram.providers.DcaseParserProvider;
import net.dependableos.dcase.impl.ParameterItem;
import net.dependableos.dcase.provider.DcaseEditPlugin;

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
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class SystemDescEditPart extends DcaseNodeDescEditPart implements
        ITextAwareEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 4031;

    /**
     * @generated
     */
    private DirectEditManager manager;

    /**
     * @generated
     */
    private IParser parser;

    /**
     * @generated
     */
    private List parserElements;

    /**
     * @generated
     */
    private String defaultText;

	/**
	 * The text of the Pattern SubType.
	 */
	private static final String PATTERN_SUBTYPE[] = {
        "_UI_System_subType_param", //$NON-NLS-1$
        "_UI_System_subType_loop", //$NON-NLS-1$
        "_UI_System_subType_choice", //$NON-NLS-1$
        "_UI_System_subType_multi", //$NON-NLS-1$
    };
	private String[] subTypes;
	
	/**
	 * The format string for the Desc.
	 */
	private static final String FORMAT_PARAM = "%s:%s=%s"; //$NON-NLS-1$
	private static final String FORMAT_LOOP = "leafNode=%s"; //$NON-NLS-1$
	private static final String FORMAT_I_J = "i=%s,j=%s"; //$NON-NLS-1$
	
    /**
     * @generated
     */
    public SystemDescEditPart(View view) {
        super(view);
        subTypes = new String[PATTERN_SUBTYPE.length];
        for (int i = 0; i < PATTERN_SUBTYPE.length; i++) {
        	subTypes[i] = DcaseEditPlugin.getPlugin().getString(PATTERN_SUBTYPE[i]);
        }
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
     * @generated
     */
    protected Image getLabelIcon() {
        return null;
    }

    /**
     * @generated NOT
     */
    protected String getLabelText() {
        String text = null;
        EObject parserElement = getParserElement();
        if (parserElement != null && getParser() != null) {
            text = getParser().getPrintString(
                    new EObjectAdapter(parserElement),
                    getParserOptions().intValue());
        }
       if (text == null || text.length() == 0) {
            return defaultText;
       }
       
       // parse "subType;leafNode;i;j;parameterVals;parameterDefs"
       String textArray[] = text.split(";", 6); //$NON-NLS-1$
       if (textArray.length != 6) {
    	   return defaultText;
       }
       // Parameter
       if (subTypes[0].equals(textArray[0])) {
    	   return getParametersText(textArray[4], textArray[5]);
       }
       // Loop
       if (subTypes[1].equals(textArray[0])) {
    	   return String.format(FORMAT_LOOP, textArray[1]);
       }
       // Choice or Multiplicity
       if (subTypes[2].equals(textArray[0]) || subTypes[3].equals(textArray[0])) {
    	   return String.format(FORMAT_I_J, textArray[2], textArray[3]);
       }
	   return defaultText;
    }
    
    /**
     * Returns the parameter information.
     * @param parameterVals the text of parameter values.
     * @param parameterDefs the text of parameter definitions.
     * @return the parameter information.
     */
    private String getParametersText(String parameterVals, String parameterDefs) {
    	StringBuffer paramBuffer = new StringBuffer();
    	if (parameterVals == null || parameterVals.length() == 0 ||
    			parameterDefs == null || parameterDefs.length() == 0) {
    		return defaultText;
    	}
    	// parse parameterVals and parameterDefs
    	List<ParameterItem> paramList = ParameterItem.getPatameterList(parameterVals);
    	HashMap<String,String> typeMap = new HashMap<String,String>();
    	String[] paramDefs = parameterDefs.split(";"); //$NON-NLS-1$
    	// paramDefs[0] is the list of parameter names, so ignore.
    	for (int i=1; i<paramDefs.length; i++) {
    		String pname = null;
    		String ptype = null;
    		// trim after type...
    		int index1 = paramDefs[i].indexOf(ParameterItem.SEPARATOR);
    		int index2 = paramDefs[i].indexOf(ParameterItem.SEPARATOR, index1+1);
    		String paramDef = paramDefs[i].substring(0, (index2 < 0) ? paramDefs[i].length():index2);
    		for (ParameterItem item : ParameterItem.getPatameterList(paramDef)) {
    			if (item.getParameterId().equals("name")) { //$NON-NLS-1$
    				pname = item.getParameterValue();
    			} else if (item.getParameterId().equals("type")) { //$NON-NLS-1$
    				ptype = item.getParameterValue();
    			}
    			if (pname != null && ptype != null) {
    				typeMap.put(pname, ptype);
    				break;
    			}
    		}
    	}
    	// create text.
    	boolean pfirst = true;
    	for (ParameterItem item : paramList) {
    		String pname = item.getParameterId();
    		String pvalue = item.getParameterValue();
    		String ptype = typeMap.get(pname);
    		if (ptype == null || ptype.length() == 0) {
    			ptype = "?"; //$NON-NLS-1$
    		}
    		if (! pfirst) {
    			paramBuffer.append(System.getProperty("line.separator")); //$NON-NLS-3$
    		} else {
    			pfirst = false;
    		}
    		paramBuffer.append(String.format(FORMAT_PARAM, pname, ptype, pvalue));
    	}
        	
    	return paramBuffer.toString();
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
    public IParser getParser() {
        if (parser == null) {
            parser = DcaseParserProvider
                    .getParser(
                            DcaseElementTypes.System_1008,
                            getParserElement(),
                            DcaseVisualIDRegistry
                                    .getType(net.dependableos.dcase.diagram.edit.parts.SystemDescEditPart.VISUAL_ID));
        }
        return parser;
    }

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
        setLabelTextHelper(getFigure(), getLabelText());
        setLabelIconHelper(getFigure(), getLabelIcon());
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
