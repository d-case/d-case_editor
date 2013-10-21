/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.parameter;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_DIGIT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_ATTRIBUTE_MIN;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_DOUBLE;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_ENUM;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_INT;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_RAW;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.DATA_TYPE_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.editor.verifier.ParameterDialog;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.jface.dialogs.Dialog;

/**
 * A utility class to handle parameter items.
 */
public final class ParameterUtil {

    /**
     * A contractor.
     */
    private ParameterUtil() {
    }

    /**
     * Shows a dialog to set parameters if the each node of the specified argument is configured parameters.
     * 
     * @param argument the argument.
     * @return true if and only if the parameters are set;false otherwise.
     */
    public static boolean processParameter(BasicNode node) {
        boolean result = true;
        String userdef007 = node.getUserdef007();
        String userdef009 = node.getUserdef009();
            // tests whether the node is configured parameters.
        if (userdef009 != null && !"".equals(userdef009.trim())) { //$NON-NLS-1$ 
        	if (ParameterItem.isValidParameter(userdef007)) {
        		// creates a node info and initializes it.
        		NodeInfo nodeInfo = new NodeInfo(null);
        		nodeInfo.setAttribute(AttributeType.USERDEF007, userdef007);
        		nodeInfo.setAttribute(AttributeType.USERDEF005, node.getUserdef005());
        		nodeInfo.setAttribute(AttributeType.USERDEF009, userdef009);
        		ParameterDialog dialog = new ParameterDialog(
        				DcaseEditorUtil.getActiveWindowShell());
        		dialog.setNodeInfo(nodeInfo);
        		if (Dialog.OK == dialog.open()) {
        			// applies the parameters to the node.
        			node.setUserdef007((String) nodeInfo.getAttribute(AttributeType.USERDEF007));
        		} else {
        			result = false;
                }
            }
        }
        return result;
    }
    
    /**
     * Returns the decimal places.
     * 
     * @param dataTypeVerifier the verifier.
     * @return the decimal places.
     */
    public static int getDigit(DataTypeVerifier dataTypeVerifier) {
        int result = 0;
        String digitStr = getDataTypeDefineXMLAttribute(DATA_ATTRIBUTE_DIGIT,
                dataTypeVerifier);
        if (digitStr == null) {
            result = 0;
        } else {
            result = Integer.parseInt(digitStr);
            if (result < 0) {
                result = 0;
            }
        }
        return result;
    }
    
    /**
     * Returns the attribute.
     * 
     * @param key the key.
     * @param dataTypeVerifier the verifier.
     * @return the attribute value.
     */
    public static String getDataTypeDefineXMLAttribute(String key,
            DataTypeVerifier dataTypeVerifier) {
        Map<String, String> attributeMap = dataTypeVerifier.getAttributeMap();
        String result = attributeMap.get(key);
        return result;
    }
    
    /**
     * Returns the parameter names.
     * @param defStr the Userdef009 string.
     * @return the parameter names.
     */
    public static List<String> getParameterDefinitionNames(String defStr) {
    	String[] sepString = defStr.split(";"); //$NON-NLS-1$
    	if(sepString.length >= 2) {
    		String[] namesString = sepString[0].split(","); //$NON-NLS-1$
    		List<String> ret = new ArrayList<String>();
    		for(String name : namesString) {
    			ret.add(name);
    		}
    		return ret;
    	}
    	return new ArrayList<String>();
    }
    
    /**
     * Returns the parameter attributes.
     * @param defStr the Userdef009 string.
     * @return the parameter attributes.
     */
    public static List<String> getParameterDefinitionAttrs(String defStr) {
    	String[] sepString = defStr.split(";"); //$NON-NLS-1$
    	if(sepString.length >= 2) {
    		List<String> ret = new ArrayList<String>();
    		for(int index=1; index<sepString.length; index++) {
    			ret.add(sepString[index]);
    		}
    		return ret;
    	}
    	return new ArrayList<String>();
    }
    
    /**
     * Returns the new parameter string.
     * @param paramStr the current parameter string.
     * @param defStr the parameter definitions.
     * @return the new parameter string.
     */
    public static String updateParameters(String paramStr, String defStr) {
    	Map<String, DataTypeVerifier> vf = ParameterDataItem.getDataTypeVerifierMapFromString(defStr);
    	List<String> defList = getParameterDefinitionNames(defStr);
    	List<ParameterItem> paramList = ParameterItem.getPatameterList(paramStr);
    	List<ParameterItem> newList = new ArrayList<ParameterItem>();
    	for (String name : defList) {
    		ParameterItem foundItem = null;
    		for (ParameterItem item : paramList) {
    			if (item.getParameterId().equals(name)) {
    				// verify value
    				DataTypeVerifier dataTypeVerifier = vf.get(name);
    				if (dataTypeVerifier == null ||
    						dataTypeVerifier.verify(item.getParameterValue())) {
    					newList.add(item);
    					foundItem = item;
    				}
    				break;
    			}
    		}
    		if (foundItem == null) {
    			// set default parameter value
    			ParameterItem newItem = new ParameterItem();
    			newItem.setParameterId(name);
    			DataTypeVerifier dataTypeVerifier = vf.get(name);
    			newItem.setParameterValue(getDefaultParameterValue(dataTypeVerifier));
    			newList.add(newItem);
    		}
    	}
    	
    	if(newList.size() <= 0) {
    		return ""; //$NON-NLS-1$
    	}
    	
    	StringBuffer ret = new StringBuffer(newList.get(0).getParamString());
    	for(int index=1; index<newList.size(); index++) {
    		ret.append(",").append(newList.get(index).getParamString()); //$NON-NLS-1$
    	}
    	return ret.toString();
    }
    
    /**
     * Returns the default parameter value.
     * @param dataTypeVerifier the data type verifier.
     * @return the default parameter value.
     */
    public static String getDefaultParameterValue(DataTypeVerifier dataTypeVerifier) {
    	String ret = ""; //$NON-NLS-1$
    	String paramDataType = dataTypeVerifier.getDataType();
		if (paramDataType == null 
				|| DATA_TYPE_STRING.equals(paramDataType)
				|| DATA_TYPE_RAW.equals(paramDataType)) {
			// do nothing
		} else if (DATA_TYPE_DOUBLE.equals(paramDataType)
				|| DATA_TYPE_INT.equals(paramDataType)) {
			return ParameterUtil.getDataTypeDefineXMLAttribute(
					DATA_ATTRIBUTE_MIN, dataTypeVerifier);
		} else if (DATA_TYPE_ENUM.equals(paramDataType)) {
			List<String> enumList = dataTypeVerifier.getAttributeList();
			if (! enumList.isEmpty()) {
				ret = enumList.get(0);
			}
		}
		return ret;
    }
    
    /***
     * Returns the merged parameters.
     * @param baseStr the base parameter string.
     * @param addedStr the added parameter string.
     * @return the merged parameters.
     */
    public static String mergeParameters(String baseStr, String addedStr) {
    	List<ParameterItem> baseList = ParameterItem.getPatameterList(baseStr);
    	List<ParameterItem> addedList = ParameterItem.getPatameterList(addedStr);
    	for (ParameterItem item : addedList) {
        	boolean found = false;
    		for (ParameterItem bitem : baseList) {
    			if (item.getParameterId().equals(bitem.getParameterId())) {
    				found = true;
    				break;
    			}
    		}
    		if (! found) {
    			baseList.add(item);
    		}
    	}
    	// make parameter String
    	return ParameterItem.getSavedString(baseList);
    }
    
    /***
     * Returns the merged parameter definition string.
     * @param baseStr the base definition string.
     * @param addedStr the added definition string.
     * @return the merged parameter definition string.
     */
    public static String mergeParameterDefinitions(String baseStr, String addedStr) {
    	List<ParameterDataItem> baseList = ParameterDataItem.getParamDatatypeListFromString(baseStr);
    	List<ParameterDataItem> addedList = ParameterDataItem.getParamDatatypeListFromString(addedStr);
    	for (ParameterDataItem item : addedList) {
        	boolean found = false;
    		for (ParameterDataItem bitem : baseList) {
    			if (item.getName().equals(bitem.getName())) {
    				found = true;
    				break;
    			}
    		}
    		if (! found) {
    			baseList.add(item);
    		}
    	}
    	return ParameterDataItem.getSavedString(baseList);
    }
}
