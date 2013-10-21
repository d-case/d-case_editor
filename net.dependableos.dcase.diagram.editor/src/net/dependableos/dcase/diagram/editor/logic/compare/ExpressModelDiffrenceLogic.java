/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.compare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.LinkInfo;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.LinkManager;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseDelegateEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.command.ChangeForegroundColorEditPartsCommand;
import net.dependableos.dcase.diagram.editor.command.ChangeLineWidthCommand;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * A class that compares 2 D-Cases.
 */
public class ExpressModelDiffrenceLogic {
    /**
     * the property key of line separator.
     */
    private static final String LINE_SEPARATOR = "line.separator"; //$NON-NLS-1$

    /**
     * the format string for node name.
     */
    private static final String NODENAME_FORMAT = "\"%1s\""; //$NON-NLS-1$

    /**
     * the number of default line width.
     */
    private static final Integer DEFAULT_LINE_WIDTH = 1;

    /**
     * the number of change line width.
     */
    private static final Integer CHANGE_LINE_WIDTH = 3;

    /**
     * the type of the edit part.
     */
    private enum EditPartType {
        /** Node. */
        NODE,
        /** Link. */
        LINK;
    };

    /**
     * the status.
     */
    private enum CompareStatus {
        /** New. */
        NEW,
        /** Deleted. */
        DELETED,
        /** Changed. */
        CHANGED,
        /** Not changed. */
        EQUAL;
    };

    /**
     * the link manager of the source.
     */
    private LinkManager sourceLinkManager;

    /**
     * the link manager of the target.
     */
    private LinkManager targetLinkManager;

    /**
     * the map of the status.
     */
    private Map<String, CompareItem> compareResultMap;

    /**
     * the ID of the argument.
     */
    private String argumentNodeId = null;

    /**
     * Creates an instance and initialized it.
     * 
     * @param source the link manager of the source.
     * @param target the link manager of the target
     */
    public ExpressModelDiffrenceLogic(LinkManager source, LinkManager target) {
        sourceLinkManager = source;
        targetLinkManager = target;
    }

    /**
     * Compares the source and the target.
     */
    public void compare() {
        // clears the map of the status.
        if (compareResultMap != null) {
            compareResultMap.clear();
        } else {
            compareResultMap = new HashMap<String, CompareItem>();
        }
        // compares the nodes.
        Set<String> nodeIdSet = sourceLinkManager.getAllNodes();
        for (String nodeId : nodeIdSet) {
            NodeInfo sourceNodeInfo = sourceLinkManager.getNodeInfo(nodeId);
            NodeInfo targetNodeInfo = targetLinkManager.getNodeInfo(nodeId);

            
            // gets the ID of Argument.
            if (sourceNodeInfo.getNodeType() == NodeType.ARGUMENT) {
                argumentNodeId = nodeId;
            }
            CompareItem compareItem = new CompareItem();
            // tests whether the node is new. 
            if (targetNodeInfo == null) {
                compareItem.setEditPartType(EditPartType.NODE);
                compareItem.setStatus(CompareStatus.NEW);
            } else {
                // tests whether the node is changed.
                boolean isDiff = isDiffrentAttribute(sourceNodeInfo
                        .getAttributeMap(), targetNodeInfo.getAttributeMap());
                if (isDiff) {
                    compareItem.setEditPartType(EditPartType.NODE);
                    compareItem.setStatus(CompareStatus.CHANGED);
                } else {
                    compareItem.setEditPartType(EditPartType.NODE);
                    compareItem.setStatus(CompareStatus.EQUAL);
                }
            }
            compareResultMap.put(nodeId, compareItem);
        }
        // finds deleted nodes.
        Set<String> leftNodeIdSet = targetLinkManager.getAllNodes();
        for (String nodeId : leftNodeIdSet) {
            if (!nodeIdSet.contains(nodeId)) {
                // doesn't set Argument as a deleted node.
                if (targetLinkManager.getNodeInfo(nodeId).getNodeType() == NodeType.ARGUMENT) {
                    continue;
                }
                // sets as a deleted node.
                CompareItem compareItem = new CompareItem();
                compareItem.setEditPartType(EditPartType.NODE);
                compareItem.setStatus(CompareStatus.DELETED);
                compareItem.setName((String) targetLinkManager.getNodeInfo(
                        nodeId).getAttribute(AttributeType.NAME));
                compareResultMap.put(nodeId, compareItem);
            }
        }

        // compares the links.
        Set<String> linkIdSet = sourceLinkManager.getAllLinks();
        for (String linkId : linkIdSet) {
            LinkInfo sourceLinkInfo = sourceLinkManager.getLinkInfo(linkId);
            LinkInfo targetLinkInfo = targetLinkManager.getLinkInfo(linkId);

            CompareItem compareItem = new CompareItem();
            // tests whether the link is new. 
            if (targetLinkInfo == null) {
                compareItem.setEditPartType(EditPartType.LINK);
                compareItem.setStatus(CompareStatus.NEW);
            } else {
                // tests whether the link is changed.
                boolean isDiff = isDiffrentAttribute(sourceLinkInfo
                        .getAttributeMap(), targetLinkInfo.getAttributeMap());
                if (isDiff) {
                    compareItem.setEditPartType(EditPartType.LINK);
                    compareItem.setStatus(CompareStatus.CHANGED);
                } else {
                    compareItem.setEditPartType(EditPartType.LINK);
                    compareItem.setStatus(CompareStatus.EQUAL);
                }
            }
            compareResultMap.put(linkId, compareItem);
        }
        // finds deleted links
        Set<String> leftLinkIdSet = targetLinkManager.getAllLinks();
        for (String linkId : leftLinkIdSet) {
            if (!linkIdSet.contains(linkId)) {
                CompareItem compareItem = new CompareItem();
                compareItem.setEditPartType(EditPartType.LINK);
                compareItem.setStatus(CompareStatus.DELETED);
                compareItem.setName((String) targetLinkManager.getLinkInfo(
                        linkId).getAttribute(AttributeType.NAME));
                compareResultMap.put(linkId, compareItem);
            }
        }
    }

    /**
     * Compares the attributes.
     * 
     * @param sourceMap the map of the attributes of the source node.
     * @param targetMap the map of the attributes of the target node.
     * @return true if and only any change exists;false otherwise.
     */
    private boolean isDiffrentAttribute(Map<AttributeType, Object> sourceMap,
            Map<AttributeType, Object> targetMap) {
        boolean result = false;
        Set<Map.Entry<AttributeType, Object>> sourceEntrySet = sourceMap
                .entrySet();
        for (Map.Entry<AttributeType, Object> entry : sourceEntrySet) {
            Object sourceObj = entry.getValue();
            Object targetObj = targetMap.get(entry.getKey());
            if (sourceObj == null) {
                if (targetObj == null) {
                    continue;
                } else {
                    result = true;
                    break;
                }
            } else {
                if (targetObj == null) {
                    result = true;
                    break;
                } else {
                    if (!sourceObj.equals(targetObj)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns the node name.
     * 
     * @param nodeId the ID of the node.
     * @return the node name.
     */
    private String getNodeName(String nodeId) {
        return (String) targetLinkManager.getNodeInfo(nodeId).getAttribute(
                AttributeType.NAME);
    }

    /**
     * Creates and returns the command to express differences on the D-Case diagram.
     * 
     * @return the command to express differences on the D-Case diagram.
     */
    @SuppressWarnings("rawtypes")
    public CompoundCommand getCommand() {

        String separator = System.getProperty(LINE_SEPARATOR);
        String message = separator;
        // a start message.
        message = message.concat(NLS.bind(
                Messages.ExpressModelDiffrenceLogic_0, new String[] {
                        sourceLinkManager.getUri().toPlatformString(false),
                        targetLinkManager.getUri().toPlatformString(false) }));

        // differences of Arguments.
        CompareItem argumentInfo = compareResultMap.get(argumentNodeId);
        if (argumentInfo.getStatus() == CompareStatus.NEW
                || argumentInfo.getStatus() == CompareStatus.CHANGED) {
            message = message.concat(separator).concat(
                    Messages.ExpressModelDiffrenceLogic_1);
        }

        // deleted parts.
        Set<Map.Entry<String, CompareItem>> compareItemSet = compareResultMap
                .entrySet();
        for (Map.Entry<String, CompareItem> item : compareItemSet) {
            if (item.getValue().getStatus() == CompareStatus.DELETED) {
                message = message.concat(separator);
                CompareItem value = item.getValue();
                
                // a node.
                if (value.getEditPartType() == EditPartType.NODE) {
                    List<String> parentIdList = targetLinkManager
                            .getSource(item.getKey());
                    if (parentIdList != null) {
                        String parentNamesStr = null;
                        for (String parentId : parentIdList) {
                            if (parentNamesStr == null) {
                                parentNamesStr = String.format(
                                        NODENAME_FORMAT, getNodeName(parentId));
                            } else {
                                parentNamesStr = parentNamesStr.concat(",") //$NON-NLS-1$
                                        .concat(
                                                String.format(NODENAME_FORMAT,
                                                        getNodeName(parentId)));
                            }
                        }
                        message = message.concat(NLS
                                .bind(Messages.ExpressModelDiffrenceLogic_2,
                                        new String[] { value.getName(),
                                                parentNamesStr }));
                    } else {
                        message = message.concat(NLS.bind(
                                Messages.ExpressModelDiffrenceLogic_2,
                                new String[] { value.getName(), "" })); //$NON-NLS-1$
                    }
                } else {
                    // a link.
                    LinkInfo linkInfo = targetLinkManager.getLinkInfo(item
                            .getKey());
                    String sourceNodeId = (String) linkInfo
                            .getAttribute(AttributeType.SOURCE);
                    String targetNodeId = (String) linkInfo
                            .getAttribute(AttributeType.TARGET);
                    message = message.concat(NLS.bind(
                            Messages.ExpressModelDiffrenceLogic_3,
                            new String[] { item.getValue().getName(),
                                    getNodeName(sourceNodeId),
                                    getNodeName(targetNodeId) }));
                }
            }
        }
        // writes the message.
        MessageWriter.writeMessageToConsole(message,
                MessageTypeImpl.WRITE_COMPARE_INFO);


        //creates and initializes the map of edit parts to change the line color.
        Map<DcaseDelegateEditPart, Color> changeLineColorMap = new HashMap<DcaseDelegateEditPart, Color>();

        // creates and initializes the map of edit parts to change the line width.
        Map<DcaseDelegateEditPart, Integer> changeLineWidthMap = new HashMap<DcaseDelegateEditPart, Integer>();
        
        ArgumentEditPart argumentEditPart = DcaseEditorUtil
                .getCurrentArgumentEditPart();

        // nodes.
        List edirPartList = argumentEditPart.getChildren();
        RGB black = new RGB(0, 0, 0);
        for (Object editPart : edirPartList) {
            if (!(editPart instanceof DcaseNodeEditPart)) {
                continue;
            }
            String uuId = DcaseEditorUtil
                    .getUUIDs((DcaseNodeEditPart) editPart);
            CompareItem item = compareResultMap.get(uuId);
            Color color = item.getLineColor();
            changeLineColorMap.put((DcaseDelegateEditPart) editPart, color);
            if (!color.getRGB().equals(black)) {
                changeLineWidthMap.put((DcaseDelegateEditPart) editPart, CHANGE_LINE_WIDTH);
            } else {
                changeLineWidthMap.put((DcaseDelegateEditPart) editPart, DEFAULT_LINE_WIDTH);
            }
        }

        // links.
        List connectionList = argumentEditPart.getConnections();
        for (Object linkPart : connectionList) {
            if (!(linkPart instanceof DcaseLinkEditPart)) {
                continue;
            }
            String uuId = DcaseEditorUtil
                    .getUUIDs((ConnectionNodeEditPart) linkPart);
            CompareItem item = compareResultMap.get(uuId);
            Color color = item.getLineColor();
            changeLineColorMap.put((DcaseLinkEditPart) linkPart, color);

            if (!color.getRGB().equals(black)) {
                changeLineWidthMap.put((DcaseDelegateEditPart) linkPart, CHANGE_LINE_WIDTH);
            } else {
                changeLineWidthMap.put((DcaseDelegateEditPart) linkPart, DEFAULT_LINE_WIDTH);
            }
        }

        CompoundCommand cc = new CompoundCommand(Menus.ExpressModelDiffrenceLogic_0);
        
        // creates and initializes the command to change the line color of edit parts.
        ICommand cmdChangeColor = new ChangeForegroundColorEditPartsCommand(
                Menus.ExpressModelDiffrenceLogic_1, changeLineColorMap);
        
        cc.add(new ICommandProxy(cmdChangeColor));
        
        // creates and initializes the command to change the line width of edit parts.
        ChangeLineWidthCommand cmdLineWidth =
                new ChangeLineWidthCommand(
                        Menus.ExpressModelDiffrenceLogic_2, changeLineWidthMap);
        
        cc.add(new ICommandProxy(cmdLineWidth));

        return cc;
    }

    /**
     * A class which represents a deference.
     */
    private static class CompareItem {
        /**
         * the status.
         */
        private CompareStatus status = null;
        /**
         * the type of the edit part.
         */
        private EditPartType editPartType = null;
        /**
         * the name of the edit part.
         */
        private String name = null;

        /**
         * Returns the type of the edit part.
         * 
         * @return the type of the edit part.
         */
        public EditPartType getEditPartType() {
            return editPartType;
        }

        /**
         * Sets the type of the edit part.
         * 
         * @param editPartType the type of the edit part.
         */
        public void setEditPartType(EditPartType editPartType) {
            this.editPartType = editPartType;
        }

        /**
         * Returns the status.
         * 
         * @return the status.
         */
        public CompareStatus getStatus() {
            return status;
        }

        /**
         * Sets the status.
         * 
         * @param status the status.
         */
        public void setStatus(CompareStatus status) {
            this.status = status;
        }

        /**
         * Returns the name of the edit part.
         * 
         * @return the name of the edit part.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of the edit part.
         * 
         * @param name the name of the edit part.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Returns the line color that represents the status.
         * 
         * @return the line color that represents the status.
         */
        public Color getLineColor() {
            Color result = null;
            switch (status) {
                case NEW:
                    result = ColorConstants.blue;
                    break;
                case CHANGED:
                    result = ColorConstants.red;
                    break;
                case EQUAL:
                    result = ColorConstants.black;
                    break;
                default:
                    result = ColorConstants.black;
                    break;
            }
            return result;
        }
    }
}
