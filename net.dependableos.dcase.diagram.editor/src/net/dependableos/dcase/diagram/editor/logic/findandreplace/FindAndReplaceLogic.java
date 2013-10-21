/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.findandreplace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.widgets.Text;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.LinkInfo;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.NumberUtil;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;

/**
 * Executes the find and replace for the node list.
 */
public class FindAndReplaceLogic {
    
    /**
     * The value of non ordered.
     */
    private static final int NON_NUMBER = Integer.MAX_VALUE;
    
    /**
     * The list of attributes of the search object.
     */
    private static final AttributeType[] TARGET_ATTRIBUTES = 
                new AttributeType[] { AttributeType.NAME, AttributeType.DESC };
    
    /**
     * The target D-Case diagram editor.
     */
    private DcaseDiagramEditor dcaseDiagramEditor = null;
    
    /**
     * The target ArgumentEditPart object.
     */
    private ArgumentEditPart argumentEditPart = null;
    
    /**
     * The LinkManager object.
     */
    private LinkManager linkManager = null;
    
    /**
     * The BasicNode list.
     */
    private List<BasicNode> searchNodeList;
    
    /**
     * The ID of the node which is hit by search.
     */
    private String selectedNodeId = null;
    
    /**
     * The current index within the node list.
     */
    private int currentNodeIndex = 0;
    
    /**
     * The index within the attribute of the substring which is hit.
     */
    private int foundDescIndex = 0;
    
    /**
     * The first index number that starts to search.
     */
    private int startNodeIndex = 0;
    
    /**
     * A string to find.
     */
    private String findString = StringUtil.EMPTY;
    
    /**
     * The index within the attribute which starts the next search.
     */
    private int startDescIndex = 0;
    
    /**
     * The current index of TARGET_ATTRIBUTES list.
     */
    private int currentAttributeIndex = 0;
    
    /**
     * The object to manage the replace handling of 'Desc' attribute and 'DescFormat' attribute.
     */
    private DescReplaceManager descReplaceManager;

   /**
    * Allocates a FindAndReplaceLogic object and initializes it to represent
    *  the DcaseDiagramEditor object.
    * 
    * @param dcaseDiagramEditor The current diagram.
    */
    public FindAndReplaceLogic(DcaseDiagramEditor dcaseDiagramEditor) {
        this.dcaseDiagramEditor = dcaseDiagramEditor;
        argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart(dcaseDiagramEditor);
        Argument argument = (Argument) dcaseDiagramEditor.getDiagram().getElement();
        
        // creates link manager.
        linkManager = new LinkManager();
        linkManager.load((XMLResource) argument.eResource());
        
        findSelectedNodeId();

        // creates the Global Parameter list. 
        descReplaceManager = new DescReplaceManager(argumentEditPart, argument.getUserdef007());

        if (this.argumentEditPart != null) {
            createSearchNodeList();
        }
    }
    
    /**
     * Creates the searched node list.
     */
    private void createSearchNodeList() {
        Set<String> allIdSet = linkManager.getAllNodes();
        List<String> allIdList = new ArrayList<String>();
        
        Map<String, List<String>> nodeTreeMap = new HashMap<String, List<String>>();
        List<String> topNodeIdList = new ArrayList<String>();
        
        searchNodeList = new ArrayList<BasicNode>();
        for (String id : allIdSet) {
            if (!(linkManager.getBasicNode(id) instanceof Argument)) {
                allIdList.add(id);
                List<String> sourceIdList = linkManager.getSource(id);
                if (sourceIdList == null || sourceIdList.isEmpty()) {
                    // adds a top node
                    topNodeIdList.add(id);
                }
            }
        }
        
        // creates the tree information from top node
        for (String topNodeId : topNodeIdList) {
            List<String> treeIdList = new ArrayList<String>();
            treeIdList.add(topNodeId);
            searchTree(topNodeId, treeIdList);
            nodeTreeMap.put(topNodeId, treeIdList);
        }
        
        // removes the node ID that the tree information is included in
        for (Map.Entry<String, List<String>> entry : nodeTreeMap.entrySet()) {
            List<String> nodeIdList = entry.getValue();
            for (String id : nodeIdList) {
                if (allIdList.contains(id)) {
                    allIdList.remove(id);
                }
            }
        }
        
        // the node ID left in the list is a circulation tree
        if (!allIdList.isEmpty()) {
            String topNodeId = allIdList.get(0);
            List<String> treeIdList = new ArrayList<String>();
            treeIdList.add(topNodeId);
            topNodeIdList.add(topNodeId);
            searchTree(topNodeId, treeIdList);
            nodeTreeMap.put(topNodeId, treeIdList);
        }
        
        // sorts the list of top node
        topNodeIdList = sortTopNodeList(topNodeIdList);
        
        // creates the search list
        List<String> searchNodeIdList = new ArrayList<String>();
        searchNodeList = new ArrayList<BasicNode>();
        for (String topNodeId : topNodeIdList) {
            List<String> nodeIdList = nodeTreeMap.get(topNodeId);
            for (String nodeId : nodeIdList) {
                if (!searchNodeIdList.contains(nodeId)) {
                    BasicNode basicNode = linkManager.getBasicNode(nodeId);
                    searchNodeList.add(basicNode);
                    searchNodeIdList.add(nodeId);
                }
            }
        }
        
        // sets the index number to start searching at
        if (!StringUtil.isNullOrEmpty(selectedNodeId)) {
            startNodeIndex = searchNodeIdList.indexOf(selectedNodeId);
        }
        currentNodeIndex = startNodeIndex;
    }

    /**
     * Sorts the list of the top node by the x coordinate.
     * @param nodeIdList The list of the top node
     * @return The list that was sorted
     */
    private List<String> sortTopNodeList(List<String> nodeIdList) {
        List<String> resultNodeIdList = new ArrayList<String>();
        Map<String, Rectangle> map = new HashMap<String, Rectangle>();
        
        for (String id : nodeIdList) {
            BasicNode basicNode = linkManager.getBasicNode(id);
            EditPart editPart = argumentEditPart.findEditPart(null, basicNode);
            map.put(id, ((GraphicalEditPart) editPart).getFigure().getBounds());
        }

        // sorts it by the x-origin
        List<Map.Entry<String, Rectangle>> entries = 
                new ArrayList<Map.Entry<String, Rectangle>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Rectangle>>() {
            public int compare(Map.Entry<String, Rectangle> o1, Map.Entry<String, Rectangle> o2) {
                Map.Entry<String, Rectangle> e1 = (Map.Entry<String, Rectangle>) o1;
                Map.Entry<String, Rectangle> e2 = (Map.Entry<String, Rectangle>) o2;
                int compareToResult = ((Integer) e1.getValue().x).compareTo((Integer) e2.getValue().x);
                if (compareToResult != 0) {
                    return compareToResult;
                }
                return ((Integer) e1.getValue().y).compareTo((Integer) e2.getValue().y);
            }
        });

        for (Map.Entry<String, Rectangle> entry : entries) {
            resultNodeIdList.add(entry.getKey());
        }

        return resultNodeIdList;
    }

    /**
     * Search for the tree and creates the node list.
     * @param nodeId The node ID of start point
     * @param treeIdList The list of a node included in the tree
     */
    private void searchTree(String nodeId, List<String> treeIdList) {
        List<String> childNodeIdList = linkManager.getTarget(nodeId);
        
        if (childNodeIdList == null || childNodeIdList.isEmpty()) {
            return;
        }
        
        // compares brother nodes
        List<String> addToChildNodeIdList = compareSiblingNode(nodeId, childNodeIdList);
        for (String id : addToChildNodeIdList) {
            if (!treeIdList.contains(id)) {
                treeIdList.add(id);
                searchTree(id, treeIdList);
            }
        }
    }

    /**
     * Sorts by the sibling order.
     * @param sourceId The ID of the parent node
     * @param targetIdList The node ID list of the node
     * @return The node ID list which was sorted
     */
    private List<String> compareSiblingNode(String sourceId, List<String> targetIdList) {
        List<String> nodeList = new ArrayList<String>();
        
        // compares the value of the 'Sibling Order' attribute of Link
        if (!StringUtil.isNullOrEmpty(sourceId)) {
            Set<String> allLinkIds = linkManager.getAllLinks();
            Map<String, Integer> siblingOrderMap = new HashMap<String, Integer>();
            for (String linkId : allLinkIds) {
                LinkInfo linkInfo = linkManager.getLinkInfo(linkId);
                // deletes the '#' character.
                String linkSourceId = (String) linkInfo.getAttribute(AttributeType.SOURCE);
                if (linkSourceId.equals(sourceId)) {
                    String linkTargetId = (String) linkInfo.getAttribute(AttributeType.TARGET);
                    if (targetIdList.contains(linkTargetId)) {
                        int siblingOrder = NumberUtil.parseIntWithDefault(
                                (String) linkInfo.getAttribute(AttributeType.USERDEF001), NON_NUMBER);
                        if (siblingOrder != NON_NUMBER) {
                            siblingOrderMap.put(linkTargetId, siblingOrder);
                        }
                    }
                }
            }
            
            // sorts the value of the 'Sibling Order' attribute.
            List<Map.Entry<String, Integer>> entries = 
                    new ArrayList<Map.Entry<String, Integer>>(siblingOrderMap.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    Map.Entry<String, Integer> e1 = (Map.Entry<String, Integer>) o1;
                    Map.Entry<String, Integer> e2 = (Map.Entry<String, Integer>) o2;
                    return (e1.getValue()).compareTo(e2.getValue());
                }
            });
            
            for (Map.Entry<String, Integer> entry : entries) {
                nodeList.add(entry.getKey());
                targetIdList.remove(entry.getKey());
            }
        }
        
        // 'Sibling Order' attribute: not set
        for (String id : targetIdList) {
            nodeList.add(id);
        }
        
        return nodeList;
    }

    /**
     * Searches the node ID that was selected in diagram.
     */
    @SuppressWarnings("unchecked")
    private void findSelectedNodeId() {
        List editPartList = argumentEditPart.getChildren();
        selectedNodeId = StringUtil.EMPTY;
        for (Object editPart : editPartList) {
            if (editPart instanceof DcaseNodeEditPart) {
                if (((DcaseNodeEditPart) editPart).getSelected() == EditPart.SELECTED_PRIMARY) {
                    selectedNodeId = DcaseEditorUtil
                            .getUUIDs((DcaseNodeEditPart) editPart);
                    break;
                }
            }
        }
    }

    /**
     * Searches the next.
     * @return The BasicNode object that was found
     */
    public BasicNode findNext() {
        // the index number of node in diagram
        int searchIndex = currentNodeIndex;
        
        BasicNode node = null;
        do {
            BasicNode currentNode = searchNodeList.get(searchIndex);
            boolean isFound = false;
            
            for (int i = currentAttributeIndex; i < TARGET_ATTRIBUTES.length; i++) {
            
                String attributeValue = StringUtil.EMPTY;
                if (TARGET_ATTRIBUTES[i] == AttributeType.NAME) {
                    attributeValue = currentNode.getName();
                } else if (TARGET_ATTRIBUTES[i] == AttributeType.DESC) {
                    attributeValue = currentNode.getDesc();
                }
                if (!StringUtil.isNullOrEmpty(attributeValue)) {
                    attributeValue = attributeValue.toLowerCase();
                    int findStringIndex = attributeValue.indexOf(findString.toLowerCase(), startDescIndex);
                    if (findStringIndex >= 0) {
                        
                        foundDescIndex = findStringIndex;
                        startDescIndex = foundDescIndex + 1;
                        currentNodeIndex = searchIndex;
                        currentAttributeIndex = i;
                        
                        if (TARGET_ATTRIBUTES[i] == AttributeType.DESC) {
                            // sets the target BasicNode
                            descReplaceManager.setTargetBasicNode(currentNode);
                        }
                        
                        node = currentNode;
                        isFound = true;
                        break;
                    }
                }
            
                foundDescIndex = 0;
                startDescIndex = 0;
            }
            
            if (isFound) {
                break;
            }
            searchIndex++;
            if (searchIndex >= searchNodeList.size()) {
                searchIndex = 0;
            }
            currentAttributeIndex = 0;
        } while (searchIndex != startNodeIndex);
        
        if (searchIndex == startNodeIndex) {
          currentNodeIndex = startNodeIndex;
        }
        
        return node;
    }
    
    /**
     * Replaces the substring which is hit with the new string.
     * @param replaceWith The string which replaces
     * @param descText the text control of desc
     * @return True when the replace is run
     */
    public boolean replace(String replaceWith, Text descText) {
        
        BasicNode currentNode = getCurrentBasicNode();
        String description = currentNode.getDesc();
        String sourceDesc = description.substring(
                foundDescIndex, foundDescIndex + findString.length());
        if (findString.equalsIgnoreCase(sourceDesc)) {
            if (descReplaceManager.replace(foundDescIndex, 
                    foundDescIndex + findString.length(), replaceWith)) {
                if (descText != null) {
                    descText.setText(currentNode.getDesc());
                    descText.setSelection(foundDescIndex, foundDescIndex + replaceWith.length());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * The BasicNode that hits the finding.
     * @return The found BasicNode object
     */
    public BasicNode getCurrentBasicNode() {
        if (currentNodeIndex < 0 && currentNodeIndex >= searchNodeList.size()) {
            return null;
        }
        return searchNodeList.get(currentNodeIndex);
    }

    /**
     * Cursors the BasicNode to the diagram.
     * @param basicNode The BasicNode that cursors
     */
    public void selectElementsInDiagram(BasicNode basicNode) {
        EditPart currentEditPart = argumentEditPart.findEditPart(null, basicNode);
        DcaseDiagramEditorUtil.selectElementsInDiagram(dcaseDiagramEditor, Arrays
                .asList(new EditPart[] { currentEditPart }));
    }
    
    /**
     * Returns the size of searching BasicNode list.
     * @return The size of list
     */
    public int getSearchNodeListSize() {
        return searchNodeList.size();
    }
    
    /**
     * Sets the find string.
     * @param findString the find string.
     */
    public void setFindString(String findString) {
        if (!StringUtil.isNullOrEmpty(findString)) {
            this.findString = findString;
        } else {
            this.findString = StringUtil.EMPTY;
        }
        
        reset();
    }
    
    /**
     * Returns the position of string that was found the value of the attribute.
     * @return The position of string
     */
    public int getFoundPosition() {
        return foundDescIndex;
    }
    
    /**
     * Returns the AttributeType that was found the value of the attribute.
     * @return The AttributeType
     */
    public AttributeType getFoundAttributeType() {
        return TARGET_ATTRIBUTES[currentAttributeIndex];
    }
    
    /**
     * Resets the search condition.
     */
    public void reset() {
        startNodeIndex = currentNodeIndex;
        foundDescIndex = 0;
        startDescIndex = 0;
        currentAttributeIndex = 0;
    }
    
    /**
     * True if replace is possible.
     * @return True if replace is possible.
     */
    public boolean isReplacePossible() {
        return descReplaceManager.isReplacePossible(foundDescIndex, 
                foundDescIndex + findString.length());
    }
}
