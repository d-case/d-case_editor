/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts.custom;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.DSTAR_FILE_EXTENSION;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.System;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.impl.ParameterItem;
import net.dependableos.dcase.provider.DcaseEditPlugin;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.SystemEditPart;
import net.dependableos.dcase.diagram.ui.editpolicies.BasicNodeOpenEditPolicy;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.part.PatternUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.draw2d.Shape;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.part.FileEditorInput;

/**
 * An abstract class represents a node editpart.
 */
public abstract class DcaseNodeEditPart extends ShapeNodeEditPart implements
        DcaseDelegateNodeEditPart, DcaseDelegateEditPart {

	/**
	 * The module name of d*.
	 */
	private static final String DSTAR_MODULE_NAME = PatternUtil.getMainModuleName();
	
	 /**
	  * The console name.
	  */
    private static final String CONSOLE_NAME = "Console"; //$NON-NLS-1$
    
    /**
     * The key of Parameter subtype.
     */
    private static final String SUBTYPE_PARAMETER = "_UI_System_subType_param"; //$NON-NLS-1$
    private String subTypeParameter;

    /**
     * Allocates a DcaseNodeEditPart object.
     * 
     * @param view owned view by this edit part 
     */
    public DcaseNodeEditPart(View view) {
        super(view);
        subTypeParameter = DcaseEditPlugin.getPlugin().getString(SUBTYPE_PARAMETER);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract NodeFigure createNodeFigure();

    /**
     * Returns the primary shape.
     * 
     * @return primaryShape the primary shape.
     */
    public abstract IFigure getPrimaryShape();

    /**
     * Returns the background color.
     * 
     * @return the background color.
     */
    public Color getBackgroundColorEx() {
        IFigure primaryShape = getPrimaryShape();
        if (primaryShape != null) {
            return primaryShape.getBackgroundColor();
        }
        return null;
    }

    /**
     * Sets the background color.
     * 
     * @param color the background color.
     */
    public void setBackgroundColorEx(Color color) {
        setBackgroundColor(color);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // installs a edit policy to open a property input dialog.
        installEditPolicy(EditPolicyRoles.OPEN_ROLE,
                new BasicNodeOpenEditPolicy());
    }

    /**
     * Sets the foreground color.
     * 
     * @param color the foreground color.
     */
    public void setForegroundColorEx(Color color) {
        setForegroundColor(color);
    }
    
    /**
     * Returns the foreground color.
     * 
     * @return the foreground color.
     */
    public Color getForegroundColorEx() {
    	IFigure primaryShape = getPrimaryShape();
        if (primaryShape != null) {
        	return primaryShape.getForegroundColor();
        }
        return null;
    }
    
    //@SuppressWarnings("unchecked")
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class) {
            return SetParametersActionFilter.getSingleton();
        }
        return super.getAdapter(adapter);
    }

    /**
     * Returns the line width.
     * 
     * @return the line width.
     */
    public int getLineWidthEx() {
        return super.getLineWidth();
    }

    /**
     * Sets the line width.
     * 
     * @param width the line width.
     */
    public void setLineWidthEx(int width) {
        if (width < 0) {
            width = 1;
        }
        ((Shape) getPrimaryShape()).setLineWidth(width);
    }
    
    // AUTO_GENERATED:END
    
    /**
     * {@inheritDoc}
     */
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("PrimaryView"); //$NON-NLS-1$
    }

    /**
     * Handles the event for changing Attachment.
     * @param event the event.
     */
    protected void handleNotificationEvent(Notification event) {
    	Object feature = event.getFeature();
    	if(event.getEventType() == Notification.SET) {
    		if(feature instanceof EAttribute) {
    	    	// process parameters
    			EAttribute attr = (EAttribute)feature;
    			if(attr.getName().equals("parameterVals") || //$NON-NLS-1$
    					attr.getName().equals("subType")) { //$NON-NLS-1$
    				this.notifyParameters();
            	}
            	// change background
            	refreshAttributeColor();
    		}
    	}
    	
    	super.handleNotificationEvent(event);
    }
    
    private void refreshAttributeColor() {
		Color color = getAttributeBackground();
		if (getBackgroundColorEx() != color) {
			setBackgroundColor(color);
			setFillColor(color);
		}
    }
    
    /**
     * Notify about changing parameters.
     */
    public void notifyParameters() {
    	// add my UUID.
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return;
    	}
		HashSet<String> uuidSet = new HashSet<String>();
		uuidSet.add(getUUID(node));
    	// notify to parent.
    	for(Object link : this.getTargetConnections()) {
    		if(link instanceof DcaseLinkEditPart) {
    			DcaseNodeEditPart parent = (DcaseNodeEditPart)((DcaseLinkEditPart)link).getSource();
    			if(parent != null) {
    				parent.notifyParameters(uuidSet);
    			}
    		}
    	}
    }
    
    /**
     * Notify about changing parameters.
     * @param uuidSet the current uuid set.
     */
    public void notifyParameters(HashSet<String> uuidSet) {
    	// check and add UUID.
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return;
    	}
    	String uuid = getUUID(node);
    	if(uuidSet.contains(uuid)) {
    		return;
    	}
    	uuidSet.add(uuid);
    	
    	// apply parameters for the element only.
    	applyParameters();
    	
    	// set notify flag to attachment.
    	String attachment = node.getAttachment();
    	if((node instanceof Userdef005 || node instanceof Userdef001)
    			&& PatternUtil.isModuleReference(attachment)) {
    		String moduleName = PatternUtil.getModuleName(attachment);
    		DcaseDiagramEditor editor = getDcaseDiagramEditor(node, moduleName);
    		if(editor != null) {
    			editor.setNotifyFlag(true);
    		}
    	}
    	
    	// notify to children.
    	for(Object link : this.getSourceConnections()) {
    		if(link instanceof DcaseLinkEditPart) {
    			DcaseNodeEditPart child = (DcaseNodeEditPart)((DcaseLinkEditPart)link).getTarget();
    			child.notifyParameters(uuidSet);
    		}
    	}
    }

    /**
     * Applies parameter values to Desc attribute.
     */
    public void applyParameters() {
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return;
    	}
    	ParameterItem.setDesc(node, getParameters(null));
    }
    
    /**
     * Returns the parameter string.
     * @param userdef007 the current parameter string...never used...
     * @return the parameter string.
     */
    public String getParameters(String userdef007) {
    	HashSet<String> uuidSet = new HashSet<String>();
    	List<ParameterData> ret = getParameters(0, userdef007, uuidSet);
    	return parameterListToString(ret);
    }

    /**
     * Converts parameter list to string.
     * @param paramList the parameter list.
     * @return the string of parameter list.
     */
    protected String parameterListToString(List<ParameterData> paramList) {
    	Collections.sort(paramList, new ParameterComparator());
    	StringBuffer ret = new StringBuffer();
    	ArrayList<String> nameList = null;
    	int prevdist = -1;
    	
    	for (int i = 0; i < paramList.size(); i++) {
    		ParameterData curData = paramList.get(i);
    		/* check duplicate parameter */
    		if (curData.getDistance() != prevdist) {
    			nameList = new ArrayList<String>();
    			prevdist = curData.getDistance();
    		}
    		String parameters = curData.getValues();
    		if (parameters == null || parameters.length() == 0) {
    			continue;
    		}
    		for (ParameterItem item : ParameterItem.getPatameterList(parameters)) {
    			String name = item.getParameterId();
    			if (nameList.contains(name)) {
    				MessageConsoleStream stream = getMessageConsoleStream();
    				stream.println(NLS.bind(Messages.ParameterDuplicated, name));
    			} else {
    				nameList.add(name);
    			}
    		}
    		ret.append(curData.getValues());
    		if (i < paramList.size() - 1) {
    			ret.append(ParameterItem.SEPARATOR);
    		}
    	}
    	return ret.toString();
    }
    
    /**
     * Returns the parameter string.
     * @param distance the distance.
     * @param userdef007 the current parameter string.
     * @param uuidSet the node set.
     * @return the parameter string.
     */
    protected List<ParameterData> getParameters(int distance, String userdef007, HashSet<String> uuidSet) {
    	// "userdef007" is used only Parameter node.
    	ArrayList<ParameterData> myParameter = new ArrayList<ParameterData>();
    	boolean isNeedDstar = (uuidSet != null && uuidSet.size() == 0);
    	
    	// check UUID
    	if (uuidSet != null) {
    		BasicNode node = (BasicNode)getElement();
        	if(node == null) {
        		return myParameter;
        	}
        	String uuid = getUUID(node);
        	if(uuidSet.contains(uuid)) {
        		return myParameter;
        	}
        	uuidSet.add(uuid);
    	}
    	
    	// get parameters of child Parameter nodes.
    	for(Object link : this.getSourceConnections()) {
    		if(link instanceof DcaseLinkEditPart) {
    			DcaseNodeEditPart child = (DcaseNodeEditPart)((DcaseLinkEditPart)link).getTarget();
    			if (child instanceof SystemEditPart) {
    				BasicNode childNode = (BasicNode)child.getElement();
    				if (subTypeParameter.equals(((System)childNode).getSubType())) {
    					String childParameter = childNode.getParameterVals();
    					myParameter.add(new ParameterData(distance, childParameter));
    				}
    			}
    		}
    	}
    	
		// if uuidSet is null, only returns the current node's parameters.
    	if (uuidSet == null) {
    		return myParameter;
    	}
    	
    	// get Ancestor's parameters
    	List<ParameterData> ret = getParentParameters(distance + 1, uuidSet);
    	ret.addAll(myParameter);
    	
    	// get parameters of d*
    	if (isNeedDstar) {
    		DcaseDiagramEditor editor = getDstarDiagramEditor((BasicNode)getElement());
    		if(editor != null) {
    			DiagramEditPart dstarEditPart = editor.getDiagramEditPart();
    			if (dstarEditPart instanceof ArgumentEditPart) {
    				Argument argument = (Argument)getElement(dstarEditPart);
    				String dstarUserdef007 = argument.getParameterVals();
    				ret.add(new ParameterData(Integer.MAX_VALUE, dstarUserdef007));
    			}
			}
		}
    	
    	return ret;
    }
    
    /**
     * Returns the parameter string of ancestors.
     * @return the parameter string of ancestors.
     */
    private List<ParameterData> getParentParameters(int distance, HashSet<String> uuidSet) {
    	ArrayList<ParameterData> ret = new ArrayList<ParameterData>();
    	getParentParameters(ret, distance, uuidSet);
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return ret;
    	}
    	// check attachment
    	for(String nodeName : getReferringSourceNodes()) { //$NON-NLS-1$
    		getAnotherParameters(ret, distance + 1, nodeName, uuidSet);
    	}
    	
    	return ret;
    }
    
    /**
     * Returns the referring source node names.
     * @return the referring source node names.
     */
    public HashSet<String> getReferringSourceNodes() {
    	HashSet<String> ret = new HashSet<String>();
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return ret;
    	}
    	Argument argument = (Argument)node.eContainer();
    	String refSource = argument.getRefSource();
    	String parent = argument.getParent();
    	if (refSource != null && refSource.length() > 0 &&
    		parent != null && parent.length() > 0) {
			// search parent module
    		for(String nodeName : refSource.split(PatternUtil.getReferenceSeparator())) {
    			if (parent.equals(PatternUtil.getModuleName(nodeName))) {
    				ret.add(nodeName);
    			}
    		}
    	}
    	return ret;
    }
    
    /**
     * Returns the another module's parameters.
     * @param parameter the current parameters.
     * @param userdef011 the module name.
     * @param uuidSet the node set.
     * @return the another module's parameters.
     */
    private void getAnotherParameters(List<ParameterData> paramList, int distance, String nodePath, HashSet<String> uuidSet) {
    	BasicNode node = (BasicNode)getElement();
    	if(node == null) {
    		return;
    	}

    	String moduleName = PatternUtil.getModuleName(nodePath);
    	String nodeName = PatternUtil.getNodeName(nodePath);
    	if(PatternUtil.isModuleReference(nodePath) && (! nodePath.equals(nodeName))) {
    		DcaseDiagramEditor editor = getDcaseDiagramEditor(node, moduleName);
    		if(editor == null) {
    			return;
    		}
    		DiagramEditPart editPart = editor.getDiagramEditPart();
    		if (!(editPart instanceof ArgumentEditPart)) {
    			return;
    		}
    		for(Object nobj : ((ArgumentEditPart)editPart).getChildren()) {
    			if(nobj instanceof DcaseNodeEditPart) {
    				BasicNode nnode = (BasicNode)getElement((DcaseNodeEditPart)nobj);
    				if(!nnode.getName().equals(nodeName)) {
    					continue;
    				}
    				List<ParameterData> nret = ((DcaseNodeEditPart)nobj).getParentParameters(distance + 1, uuidSet);
    				paramList.addAll(nret);
    			}
    		}
    	}
    }
    
    /**
     * Returns the parameter string of ancestors.
     * @param parameter the current parameter...do not use...
     * @param uuidSet the current uuid set.
     * @return the parameter string of ancestors.
     */
    private void getParentParameters(List<ParameterData> paramList, int distance, HashSet<String> uuidSet) {
    	// add parent's parameters.
    	for(Object link : this.getTargetConnections()) {
    		if(link instanceof DcaseLinkEditPart) {
    			DcaseNodeEditPart parentEditPart = (DcaseNodeEditPart)((DcaseLinkEditPart)link).getSource();
    			BasicNode parentNode = (BasicNode)parentEditPart.getElement();
    			if(parentNode == null) {
    				continue;
    			}
    			paramList.addAll(parentEditPart.getParameters(distance, null, uuidSet));
    		}
    	}
    }
    
    /**
     * Returns the element.
     * @return the element.
     */
    public EObject getElement() {
    	return getElement(this);
    }
    
    /**
     * Returns the element.
     * @param editPart the edit part.
     * @return the element.
     */
    public static EObject getElement(GraphicalEditPart editPart) {
    	Object model = editPart.getModel();
    	if(! (model instanceof View)) {
    		return null;
    	}
    	return ((View)model).getElement();
    }
    
    /**
     * Returns the UUID.
     * @param node the element.
     * @return the UUID.
     */
    public static String getUUID(BasicNode node) {
    	XMLResource resource = (XMLResource)node.eResource();
    	return resource.getID(node);
    }
   
    /**
     * Returns the diagram editor of the specified module.
     * @param node the node.
     * @param moduleName the module name.
     * @return the diagram editor.
     */
    private DcaseDiagramEditor getDcaseDiagramEditor(BasicNode node, String moduleName) {
    	return getDcaseDiagramEditor(node, moduleName, false);
    }
    
    /**
     * Returns the diagram editor of the specified module.
     * @param node the node.
     * @param moduleName the module name.
     * @return the diagram editor.
     */
    public DcaseDiagramEditor getDstarDiagramEditor(BasicNode node) {
    	return getDcaseDiagramEditor(node, DSTAR_MODULE_NAME, true);
    }
    
    /**
     * Returns the diagram editor of the specified module.
     * @param node the node.
     * @param moduleName the module name.
     * @param isDstar whether dstar or not.
     * @return the diagram editor.
     */
    private DcaseDiagramEditor getDcaseDiagramEditor(BasicNode node, String moduleName, boolean isDstar) {
		XMLResource resource = (XMLResource)node.eResource();
		IFile modelFile = WorkspaceSynchronizer.getFile(resource);
		IPath diagramPath = null;
		if (isDstar) {
			diagramPath = PatternUtil.getDstarPath(modelFile);
		} else {
			diagramPath = PatternUtil.getDiagramPath(modelFile, moduleName);
		}
    	if(diagramPath == null) {
    		return null;
    	}
    	IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath.toOSString());
    	IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
    			.getActiveWorkbenchWindow().getActivePage();
    	IEditorPart editor = null;
    	try {
    		editor = workbenchPage.findEditor(new FileEditorInput(diagramFile));
    		if (editor == null && diagramFile.exists()) {
    			IEditorDescriptor desc = PlatformUI.getWorkbench()
                        .getEditorRegistry().getDefaultEditor(diagramFile.getName());
    			if (desc != null) {
    		    	Diagram currentDiagram = ((DcaseDiagramEditor)workbenchPage.getActiveEditor()).getDiagram();
    				editor = workbenchPage.openEditor(new FileEditorInput(diagramFile), desc.getId(), false);
    				DcaseDiagramEditorUtil.openDiagram(currentDiagram.eResource());
    			}
    		}
    	} catch (Exception e) {
    		return null;
    	}
    	return (DcaseDiagramEditor)editor;
    }
    
    protected void setFillColor(Color color) {
    	RGB rgb = color.getRGB();
    	int c = (rgb.blue<<16) | (rgb.green<<8) | rgb.red;
    	FillStyle fillStyle =
    			(FillStyle)getPrimaryView().getStyle(NotationPackage.eINSTANCE.getFillStyle());
    	if (fillStyle != null) {
    		fillStyle.setFillColor(c);
    	}
    }

    /**
     * Sets the background for Attachment and RefSource.
     */
    public Color getAttributeBackground() {
    	BasicNode node = getBasicNode();
    	int condition = 0;
    	IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
    			.getActiveWorkbenchWindow().getActivePage();
    	DcaseDiagramEditor editor = (DcaseDiagramEditor)workbenchPage.getActiveEditor();
    	if (editor != null) {
    		Diagram diagram = editor.getDiagram();
        	String dstarDiagramName = DSTAR_MODULE_NAME + "." + PropertyUtil.getSystemProperty(DSTAR_FILE_EXTENSION); //$NON-NLS-1$
        	if (! diagram.getName().equals(dstarDiagramName)) {
            	if(node != null) {
            		String attachmentValue = node.getAttachment();
            		if(PatternUtil.isModuleReference(attachmentValue)) {
            			condition += 1;
            		}
            		String refSourceValue = node.getRefSource();
            		if(refSourceValue != null && refSourceValue.length() > 0) {
            			condition += 2;
            		}
            	}
        	}
    	}
    	switch(condition) {
    	case 1:
    		return ColorConstants.green;
    	case 2:
    		return ColorConstants.orange;
    	case 3:
    		return ColorConstants.darkGreen;
    	default:
    		return ColorConstants.white;
		}
    }
    
    /**
     * Gets the current BasicNode.
     * @return the current BasicNode
     */
    private BasicNode getBasicNode() {
		Object obj = getModel();
		if(obj instanceof View) {
			EObject eobj = ((View)obj).getElement();
			if(eobj instanceof BasicNode) {
				return ((BasicNode)eobj);
			}
		}
		return null;
    }
    
    /**
     * A class for sorting parameters.
     *
     */
    private class ParameterData {
    	private int distance;
    	private String values;
    	public ParameterData(int distance, String values) {
    		this.distance = distance;
    		this.values = values;
    	}
    	public int getDistance() {
    		return distance;
    	}
    	public String getValues() {
    		return values;
    	}
    	public String toString() {
    		return "{" + distance + "," + values + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    	}
    }

    /**
     * A class for sorting parameters.
     *
     */
    private class ParameterComparator implements Comparator<ParameterData> {
    	public int compare(ParameterData d1, ParameterData d2) {
    		return d1.getDistance() - d2.getDistance();
    	}
    }
    
    /**
     * Returns the message console stream.
     * @returnthe message console stream.
     */
    public static MessageConsoleStream getMessageConsoleStream() {
		// get or create Console ... see editor.common.util.MessageWriter class
		IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager().getConsoles();
        MessageConsole console = null;
        for (int i = 0; i < consoles.length; i++) {
            if (CONSOLE_NAME.equals(consoles[i].getName())) {
                console = (MessageConsole) consoles[i];
                break;
            }
        }
        if (console == null) {
            console = new MessageConsole(CONSOLE_NAME, null);
        }
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		return console.newMessageStream();
    }

}
