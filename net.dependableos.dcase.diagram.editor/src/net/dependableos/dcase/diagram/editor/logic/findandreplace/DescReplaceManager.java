/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.findandreplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.message.Messages;
import net.dependableos.dcase.impl.ParameterItem;

/**
 * A class to manage the replace handling of 'Desc' attribute and 'DescFormat' attribute.
 */
public class DescReplaceManager {
    
    /**
     * The left brace.
     */
    private static final String LEFT_BRACE = "{"; //$NON-NLS-1$
    
    /**
     * The right brace.
     */
    private static final String RIGHT_BRACE = "}"; //$NON-NLS-1$
    
    /**
     * The target ArgumentEditPart object.
     */
    private ArgumentEditPart argumentEditPart = null;
    
    /**
     * The target BasicNode.
     */
    private BasicNode basicNode;

    /**
     * The attribute value of 'Desc'.
     */
    private String desc;
    
    /**
     * The attribute value of 'DescFormatList'.
     */
    private String descFormatString;
    
    /**
     * The hash map of ParameterItem and parameter name.
     */
    private Map<String, ParameterItem> parameterItemMap;
    
    /**
     * The hash map of ParameterItem of the global parameters.
     */
    private Map<String, ParameterItem> globalParameterItemMap;
    
    /**
     * The list of indexes that don't execute the replace of 'Desc'.
     */
    private List<int[]> notSearchIndexListOfDesc = null;
    
    /**
     * The list of indexes that don't execute the replace of 'DescFormatString'.
     */
    private List<int[]> notSearchIndexListOfDfs = null;
    
    /**
     * The constructor.
     * @param argumentEditPart The target ArgumentEditPart object.
     * @param globalParameters the global parameter attribute.
     */
    public DescReplaceManager(ArgumentEditPart argumentEditPart, String globalParameters) {
        this.argumentEditPart = argumentEditPart;
        globalParameterItemMap = createParameterItemMap(globalParameters);
    }
    
    /**
     * Sets a target BasicNode.
     * @param basicNode a target BasicNode
     */
    public void setTargetBasicNode(BasicNode basicNode) {
        
        if ((this.basicNode != null && this.basicNode == basicNode)
                    && (desc != null && desc.equals(basicNode.getDesc()))) {
            return;
        }

        this.basicNode = basicNode;
        desc = this.basicNode.getDesc();
        descFormatString = this.basicNode.getParameterizedDesc();
        
        if (descFormatString != null && descFormatString.trim().length() > 0) {
            // creates the hash map of parameters.
            parameterItemMap = createParameterItemMap(this.basicNode.getParameterVals());
            for (String paramName : globalParameterItemMap.keySet()) {
                parameterItemMap.put(paramName, globalParameterItemMap.get(paramName));
            }
            if (parameterItemMap.size() > 0) {
                createNotSearchIndexList();
            }
        } else {
            parameterItemMap = new HashMap<String, ParameterItem>();
            notSearchIndexListOfDesc = new ArrayList<int[]>();
            notSearchIndexListOfDfs = new ArrayList<int[]>();
        }
    }
    
    /**
     * Executes the replace.
     * @param startIndex the start index of the replace
     * @param endIndex the end index of the replace
     * @param replaceWith the replace string
     * @return true if the replacement is executed.
     */
    public boolean replace(int startIndex, int endIndex, String replaceWith) {
        
        boolean result = false;
        AttributeType attributeType = AttributeType.PARAMETERIZEDDESC;
        String replaceString = ""; //$NON-NLS-1$
        
        if (basicNode != null && (desc != null && desc.trim().length() > 0)) {
            if (descFormatString != null && descFormatString.trim().length() > 0) {
                // executes replace of 'DescFormatString'
                int dfsStartIndex = startIndex;
                int dfsEndIndex = endIndex;
                if (notSearchIndexListOfDesc != null && notSearchIndexListOfDfs != null) {
                    for (int i = 0; i < notSearchIndexListOfDesc.size(); i++) {
                        int[] notSearchIndexDesc = notSearchIndexListOfDesc.get(i);
                        int[] notSearchIndexDfs = notSearchIndexListOfDfs.get(i);
                        if (startIndex >= notSearchIndexDesc[0] && startIndex < notSearchIndexDesc[1]) {
                            return false;
                        } else if (startIndex < notSearchIndexDesc[0]) {
                            break;
                        } else if (startIndex >= notSearchIndexDesc[1]) {
                            dfsStartIndex = notSearchIndexDfs[1] + (startIndex - notSearchIndexDesc[1]);
                            dfsEndIndex = notSearchIndexDfs[1] + (endIndex - notSearchIndexDesc[1]);
                        }
                        if (endIndex >= notSearchIndexDesc[0] && endIndex < notSearchIndexDesc[1]) {
                            return false;
                        }
                        if (startIndex < notSearchIndexDesc[0] && endIndex >= notSearchIndexDesc[1]) {
                            return false;
                        }
                    }
                }
                    
                // executes replace of 'DescFormatString'
                StringBuilder sb = new StringBuilder(descFormatString);
                sb.replace(dfsStartIndex, dfsEndIndex, replaceWith);
                replaceString = sb.toString();
                attributeType = AttributeType.PARAMETERIZEDDESC;
            } else {
                // executes replace of 'Desc'
                StringBuilder sb = new StringBuilder(desc);
                sb.replace(startIndex, endIndex, replaceWith);
                replaceString = sb.toString();
                attributeType = AttributeType.DESC;
            }
            // executes the replace command.
            executeReplaceCommand(basicNode, attributeType, replaceString);
            result = true;
        }
        return result;
    }
    
    /**
     * True if replace is possible.
     * @param startIndex the start index of the replace
     * @param endIndex the end index of the replace
     * @return True if replace is possible.
     */
    public boolean isReplacePossible(int startIndex, int endIndex) {
        if (notSearchIndexListOfDesc != null && notSearchIndexListOfDfs != null) {
            for (int i = 0; i < notSearchIndexListOfDesc.size(); i++) {
                int[] notSearchIndexDesc = notSearchIndexListOfDesc.get(i);
                if (startIndex >= notSearchIndexDesc[0] && startIndex < notSearchIndexDesc[1]) {
                    return false;
                } else if (startIndex < notSearchIndexDesc[0]) {
                    break;
                }
                if (endIndex >= notSearchIndexDesc[0] && endIndex < notSearchIndexDesc[1]) {
                    return false;
                }
                if (startIndex < notSearchIndexDesc[0] && endIndex >= notSearchIndexDesc[1]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Executes the command of replace to the BasicNode.
     * @param basicNode The BasicNode that is changed
     * @param attributeType the AttributeType
     * @param replaceString The string which replaces
     */
    private void executeReplaceCommand(BasicNode basicNode, AttributeType attributeType, String replaceString) {
        
        // creates an attribute map.
        Map<AttributeType, Object> attrMap = new HashMap<AttributeType, Object>();
        // puts the values to set.
        attrMap.put(attributeType, replaceString);
        // creates a change the specified attribute command and perform it.
        ICommand changeAttrCommand = new ChangeBasicNodePropertyTransactionCommand(
                argumentEditPart.getEditingDomain(), 
                Messages.DescReplaceManager_ReplaceDescCommandName, 
                null, 
                basicNode, 
                attrMap);
        
        argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
                .execute(new ICommandProxy(
                        changeAttrCommand));
    }
    
    /**
     * Creates the list of indexes that don't execute the replace from the Parameter name.
     */
    private void createNotSearchIndexList() {
        
        notSearchIndexListOfDesc = new ArrayList<int[]>();
        notSearchIndexListOfDfs = new ArrayList<int[]>();
        
        StringBuilder descValue = new StringBuilder(descFormatString);
        int currentDfsIndex = 0;
        int currentDescIndex = 0;
        int dfsRightBraceIndex = -1;
        
        while ((dfsRightBraceIndex = descFormatString.indexOf(LEFT_BRACE, currentDfsIndex)) >= 0) {
            int dfsLeftBraceIndex = descFormatString.indexOf(RIGHT_BRACE, dfsRightBraceIndex);
            if (dfsLeftBraceIndex < 0) {
                break;
            }
            int descRightBraceIndex = descValue.indexOf(LEFT_BRACE, currentDescIndex);
            int descLeftBraceIndex = -1;
            if (descRightBraceIndex >= 0) {
                descLeftBraceIndex = descValue.indexOf(RIGHT_BRACE, descRightBraceIndex);
                if (descLeftBraceIndex < 0) {
                    break;
                }
            
                String paramName = descFormatString.substring(dfsRightBraceIndex + 1, dfsLeftBraceIndex);
                if (parameterItemMap.containsKey(paramName)) {
                    notSearchIndexListOfDfs.add(new int[] { dfsRightBraceIndex, dfsLeftBraceIndex + 1 });
                    String paramValue = parameterItemMap.get(paramName).getParameterValue();
                    descValue = descValue.replace(descRightBraceIndex, 
                            descRightBraceIndex + paramName.length() + 2, paramValue);
                    descLeftBraceIndex = descRightBraceIndex + paramValue.length() - 1;
                    notSearchIndexListOfDesc.add(
                            new int[] { descRightBraceIndex, descLeftBraceIndex + 1 });
                }
            } else {
                break;
            }
            
            currentDfsIndex = dfsLeftBraceIndex + 1;
            currentDescIndex = descLeftBraceIndex + 1;
            
        }
        
        if (!desc.equals(descValue.toString())) {
            // The 'Desc' attribute is not in accord with the descValue.
            notSearchIndexListOfDesc.clear();
            notSearchIndexListOfDfs.clear();
        }
    }


    /**
     * Returns the map list of ParameterItem and parameter name.
     * @param parameters the Parameters attribute
     * @return the map list of ParameterItem and parameter name
     */
    private Map<String, ParameterItem> createParameterItemMap(String parameters) {
        
        Map<String, ParameterItem> results = new HashMap<String, ParameterItem>();
        
        // gets the userdef007.
        if (parameters != null && parameters.trim().length() != 0
                        && ParameterItem.isValidParameter(parameters)) {
            List<ParameterItem> list = ParameterItem.getPatameterList(parameters);
            for (ParameterItem parameterItem : list) {
                results.put(parameterItem.getParameterId(), parameterItem);
            }
        }

        return results;
    }
}
