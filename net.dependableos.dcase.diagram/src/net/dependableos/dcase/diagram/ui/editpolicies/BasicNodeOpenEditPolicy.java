/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.ui.editpolicies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.dependableos.dcase.impl.ParameterItem.PARAM_ITEM_REGEX_FORMAT;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.part.PatternUtil;
import net.dependableos.dcase.diagram.ui.AttributeDialog;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.MessageConsoleStream;


/**
 * An open edit policy for a basic node.
 */
public class BasicNodeOpenEditPolicy extends OpenEditPolicy {

    /**
     * the command name.
     */
    private static final String COMMAND_NAME = "Set Attribute"; //$NON-NLS-1$
    
    /**
     * Returns a command to open a property input dialog.
     * 
     * @param request the request
     * @return command to perform the open
     */
    @Override
    protected Command getOpenCommand(Request request) {

        EditPart editPart = getTargetEditPart(request);
        View view = ((GraphicalEditPart) editPart).getNotationView();
        if (view == null) {
            return null;
        }
        BasicNode basicNode = (BasicNode) ViewUtil.resolveSemanticElement(view);
        
        // open module when dstar diagram
    	XMLResource resource = (XMLResource)basicNode.eResource();
    	IFile modelFile = WorkspaceSynchronizer.getFile(resource);
    	if (PatternUtil.isDstarModelFile(modelFile) ||
    		basicNode instanceof Userdef005 || basicNode instanceof Userdef001 ||
    		basicNode instanceof Goal) {
    		String moduleName = basicNode.getAttachment();
    		if (PatternUtil.isModuleReference(moduleName)) {
    			String nodeName = PatternUtil.getNodeName(moduleName);
    			moduleName = PatternUtil.getModuleName(moduleName);
    			DcaseDiagramEditor newEditor = (DcaseDiagramEditor)PatternUtil.openModuleEditor(moduleName);
    				
    			// Away Goal...
    			if (nodeName != null && nodeName.length() > 0 && newEditor != null) {
    				ArgumentEditPart argumentEditPart = (ArgumentEditPart)newEditor.getDiagramEditPart();
    				Argument argument = (Argument)newEditor.getDiagram().getElement();
    				for (BasicNode node : argument.getRootBasicNode()) {
    					if (node.getName().equals(nodeName)) {
    						EditPart nodeEditPart = argumentEditPart.findEditPart(null, node);
    						DcaseDiagramEditorUtil.selectElementsInDiagram(newEditor,
    								Arrays.asList(new EditPart[] { nodeEditPart }));
    						break;
    					}
    				}
    			}
    			return null;
    		} else if (PatternUtil.isDstarModelFile(modelFile)) {
    			return null;
    		}
    	}

        // gets attributes from the basic node.
        String name = basicNode.getName();
        String desc = basicNode.getDesc();
        String attachment = basicNode.getAttachment();
        int weight = 0;
        BigDecimal score = null;
        String descFormat = ParameterItem.unescapeLineSeparator(basicNode.getParameterizedDesc());
        String script = basicNode.getUserdef006();
        String parameter = basicNode.getParameterVals();
        String parameterDefinitions = basicNode.getParameterDefs();
        String respName = basicNode.getRespName();
        String respAddress = basicNode.getRespAddress();
        String respIcon = basicNode.getRespIcon();
        String respTime = basicNode.getRespTime();

        NodeType nodeType = NodeType.getNodeType(basicNode);
        switch (nodeType) {
            case GOAL:
                weight = ((Goal) basicNode).getWeight();
                score = ((Goal) basicNode).getScore();
                break;
            default:
        }

        AttributeDialog dialog = new AttributeDialog(getActiveWindowShell(),
                basicNode, (DcaseNodeEditPart)editPart);

        dialog.setName(name);
        dialog.setDesc(desc);
        dialog.setAttachment(attachment);
        dialog.setWeight(weight);
        dialog.setScore(score);
        dialog.setDescFormat(descFormat);
        dialog.setScript(script);
        dialog.setParameters(parameter);
        dialog.setParameterDefinitions(parameterDefinitions);
        dialog.setRespName(respName);
        dialog.setRespAddress(respAddress);
        dialog.setRespIcon(respIcon);
        dialog.setRespTime(respTime);
        
        dialog.setStatus(basicNode.getStatus());

        switch (nodeType) {
            case JUSTIFICATION:
                dialog.setStakeholder(((Justification) basicNode)
                        .getStakeholder());
                dialog.setRiskAnalysis(((Justification) basicNode)
                        .getRiskAnalysis());
                break;
            case MONITOR:
                dialog.setIsNormal(((Monitor) basicNode).isIsNormal());
                break;
            case SYSTEM:
                dialog.setSubType(((net.dependableos.dcase.System) basicNode).getSubType());
                dialog.setLeafNode(((net.dependableos.dcase.System) basicNode).getLeafNode());
                dialog.setI(((net.dependableos.dcase.System) basicNode).getI());
                dialog.setJ(((net.dependableos.dcase.System) basicNode).getJ());
                break;
            default:
        }

        // open the dialog.
        if (Dialog.OK == dialog.open()) {
            // check Desc string.
            checkDescParameters(dialog.getName(), dialog.getDesc());
            
            if (isValueChange(dialog, basicNode)) {

                // if any attribute changed,perform a change attribute command.
                TransactionalEditingDomain domain = ((GraphicalEditPart) editPart)
                        .getEditingDomain();

                // creates an attribute map.
                Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
                
                // check attachment.
                if(!dialog.getAttachment().equals(convertEmptyString(attachment))) {
                	dialog.postProcess(basicNode);
                }

                // puts the values to set.
                attrMap.put(AttributeType.NAME, dialog.getName());
                attrMap.put(AttributeType.DESC, dialog.getDesc());
                attrMap.put(AttributeType.ATTACHMENT, dialog.getAttachment());
                attrMap.put(AttributeType.STATUS, dialog.getStatus());
                attrMap.put(AttributeType.PARAMETERIZEDDESC,
                		ParameterItem.escapeLineSeparator(dialog.getDescFormat()));
                attrMap.put(AttributeType.USERDEF006, dialog.getScript());
                attrMap.put(AttributeType.PARAMETERVALS, dialog.getParameters());
                attrMap.put(AttributeType.PARAMETERDEFS, dialog.getParameterDefinitions());
                attrMap.put(AttributeType.RESPNAME, dialog.getRespName());
                attrMap.put(AttributeType.RESPADDRESS, dialog.getRespAddress());
                attrMap.put(AttributeType.RESPICON, dialog.getRespIcon());
                attrMap.put(AttributeType.RESPTIME, dialog.getRespTime());

                switch (nodeType) {
                    case GOAL:
                        attrMap.put(AttributeType.WEIGHT, dialog.getWeight());
                        attrMap.put(AttributeType.REQUIREMENT, dialog.getRequirement());
                        break;
                    case JUSTIFICATION:
                        attrMap.put(AttributeType.STAKEHOLDER, dialog
                                .getStakeholder());
                        attrMap.put(AttributeType.RISK_ANALYSIS, dialog
                                .getRiskAnalysis());
                        break;
                    case MONITOR:
                        attrMap.put(AttributeType.IS_NORMAL, dialog
                                .isIsNormal());
                        break;
                    case SYSTEM:
                        attrMap.put(AttributeType.SUBTYPE, dialog.getSubType());
                        attrMap.put(AttributeType.LEAFNODE, dialog.getLeafNode());
                        attrMap.put(AttributeType.I, dialog.getI());
                        attrMap.put(AttributeType.J, dialog.getJ());
                        break;
                    default:
                }

                //creates a change attribute command and perform it.
                ICommand changeAttributeCommand = new ChangeBasicNodePropertyTransactionCommand(
                        domain, COMMAND_NAME, null, basicNode, attrMap);

                return new ICommandProxy(changeAttributeCommand);
            }
        }
        return null;
    }

    /**
     * Returns the active window shell.
     * 
     * @return the active window shell.
     */
    private Shell getActiveWindowShell() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
        return activeWindow.getShell();
    }

    /**
     * Tests whether attribute values changed.
     * 
     * @param dialog the dialog.
     * @param node the node.
     * @return true if and only if attribute value changed; false otherwise.
     */
    private boolean isValueChange(AttributeDialog dialog, BasicNode node) {
        if (!dialog.getName().equals(convertEmptyString(node.getName()))
                || !dialog.getDesc().equals(convertEmptyString(node.getDesc()))
                || !dialog.getAttachment().equals(convertEmptyString(node.getAttachment()))
                || !dialog.getStatus().equals(convertEmptyString(node.getStatus()))
                || !dialog.getDescFormat().equals(convertEmptyString(node.getParameterizedDesc()))
                || !dialog.getScript().equals(convertEmptyString(node.getUserdef006()))
                || !dialog.getParameters().equals(convertEmptyString(node.getParameterVals()))
                || !dialog.getParameterDefinitions().equals(convertEmptyString(node.getParameterDefs()))
                || !dialog.getRespName().equals(convertEmptyString(node.getRespName()))
                || !dialog.getRespAddress().equals(convertEmptyString(node.getRespAddress()))
                || !dialog.getRespIcon().equals(convertEmptyString(node.getRespIcon()))
                || !dialog.getRespTime().equals(convertEmptyString(node.getRespTime()))
                ) {
            return true;
        }
        NodeType nodeType = NodeType.getNodeType(node);
        if (nodeType == NodeType.GOAL) {
            int weight = 0;
            String requirement = null;
            Goal goal = (Goal) node;
            weight = goal.getWeight();
            requirement = goal.getRequirement();
            if (requirement == null) {
                requirement = "";   //$NON-NLS-1$
            }
            if ((dialog.getWeight() != weight) 
                    || !dialog.getRequirement().equals(requirement)) {
                return true;
            }
        }
        if (nodeType == NodeType.JUSTIFICATION) {
            if (!dialog.getStakeholder()
                    .equals(convertEmptyString(((Justification) node)
                                    .getStakeholder()))
                    || !dialog.getRiskAnalysis().equals(convertEmptyString(((Justification) node)
                                    .getRiskAnalysis()))) {
                return true;
            }
        }
        if (nodeType == NodeType.MONITOR) {
            if (!dialog.isIsNormal() == ((Monitor) node).isIsNormal()) {
                return true;
            }
        }       
        if (nodeType == NodeType.SYSTEM) {
            if (!dialog.getSubType()
                    .equals(convertEmptyString(((net.dependableos.dcase.System) node)
                            .getSubType()))) {
                return true;
            }
            if (!dialog.getLeafNode()
                    .equals(convertEmptyString(((net.dependableos.dcase.System) node)
                            .getLeafNode()))) {
                return true;
            }
            if (dialog.getI() != ((net.dependableos.dcase.System) node).getI()) {
                return true;
            }
            if (dialog.getJ() != ((net.dependableos.dcase.System) node).getJ()) {
                return true;
            }
        }       
        return false;
    }

    /**
     * Returns empty if the given text is null.Return the given text if it is not null.
     * 
     * @param text the text.
     * @return empty if and only if the given text is null; the given text otherwise.
     */
    private String convertEmptyString(String text) {
        if (text == null) {
            return ""; //$NON-NLS-1$
        }
        return text;
    }
    
    /**
     * Checks Description string.
     * @param name the node name.
     * @param desc the description.
     */
    private void checkDescParameters(String name, String desc) {
    	Pattern p = Pattern.compile("\\{([^\\}]+)\\}"); //$NON-NLS-1$
    	ArrayList<String> paramList = new ArrayList<String>();
    	while (true) {
    		Matcher m = p.matcher(desc);
    		if (! m.find()) {
    			break;
    		}
    		String parameter = m.group(1);
    		paramList.add(parameter);
    		desc = desc.replaceAll(String.format(PARAM_ITEM_REGEX_FORMAT, parameter), ""); //$NON-NLS-1$
    	}
    	// found unknown parameters.
    	if (paramList.size() > 0) {
    		MessageConsoleStream stream = DcaseNodeEditPart.getMessageConsoleStream();
    		stream.print(name + ": cannot found "); //$NON-NLS-1$
    		for (int i = 0; i < paramList.size(); i++) {
    			stream.print(paramList.get(i) + ((i == paramList.size()-1) ? "":",")); //$NON-NLS-1$
    		}
    		stream.println();
    	}
    }
}