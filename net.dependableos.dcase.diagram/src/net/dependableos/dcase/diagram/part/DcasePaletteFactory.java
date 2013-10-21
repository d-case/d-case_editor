/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.part;

import java.util.ArrayList;
import java.util.List;


import net.dependableos.dcase.diagram.providers.DcaseElementTypes;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class DcasePaletteFactory {

    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createNodes1Group());
        paletteRoot.add(createOptionNodes2Group());
        paletteRoot.add(createLinks3Group());
    }

    /**
     * Creates "Nodes" palette tool group
     * @generated
     */
    private PaletteContainer createNodes1Group() {
        PaletteDrawer paletteContainer = new PaletteDrawer(
                Messages.Nodes1Group_title);
        paletteContainer.setId("createNodes1Group"); //$NON-NLS-1$
        paletteContainer.setDescription(Messages.Nodes1Group_desc);
        
        paletteContainer.add(createGoal1CreationTool());
        paletteContainer.add(createEvidence3CreationTool());
        paletteContainer.add(createStrategy2CreationTool());
        paletteContainer.add(createContext6CreationTool());
        paletteContainer.add(createJustification7CreationTool());
        paletteContainer.add(createUserdef0044CreationTool());  // Assumption
        paletteContainer.add(createUndeveloped5CreationTool());
        paletteContainer.add(createUserdef0055CreationTool());  // Module
        paletteContainer.add(createUserdef0066CreationTool());  // Contract
        
        return paletteContainer;
    }

    /**
     * Creates "Option Nodes" palette tool group
     * @generated
     */
    private PaletteContainer createOptionNodes2Group() {
        PaletteDrawer paletteContainer = new PaletteDrawer(
                Messages.OptionNodes2Group_title);
        paletteContainer.setId("createOptionNodes2Group"); //$NON-NLS-1$
        paletteContainer.setDescription(Messages.OptionNodes2Group_desc);

        paletteContainer.add(createMonitor4CreationTool());
        paletteContainer.add(createSystem8CreationTool());
        paletteContainer.add(createPolicy9CreationTool());
        paletteContainer.add(createUserdef0011CreationTool());
        paletteContainer.add(createUserdef0022CreationTool());
        paletteContainer.add(createUserdef0033CreationTool());
        
        return paletteContainer;
    }

    /**
     * Creates "Links" palette tool group
     * @generated
     */
    private PaletteContainer createLinks3Group() {
        PaletteDrawer paletteContainer = new PaletteDrawer(
                Messages.Links3Group_title);
        paletteContainer.setId("createLinks3Group"); //$NON-NLS-1$
        paletteContainer.setDescription(Messages.Links3Group_desc);
        paletteContainer.add(createLink1CreationTool());
        paletteContainer.add(createLink2CreationTool());
        paletteContainer.add(createLink3CreationTool());
        paletteContainer.add(createLink4CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createGoal1CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Goal_1001);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Goal1CreationTool_title,
                Messages.Goal1CreationTool_desc, types);
        entry.setId("createGoal1CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Goal_1001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createStrategy2CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Strategy_1002);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Strategy2CreationTool_title,
                Messages.Strategy2CreationTool_desc, types);
        entry.setId("createStrategy2CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Strategy_1002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createEvidence3CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Evidence_1003);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Evidence3CreationTool_title,
                Messages.Evidence3CreationTool_desc, types);
        entry.setId("createEvidence3CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Evidence_1003));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createMonitor4CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Monitor_1004);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Monitor4CreationTool_title,
                Messages.Monitor4CreationTool_desc, types);
        entry.setId("createMonitor4CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Monitor_1004));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUndeveloped5CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Undeveloped_1005);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Undeveloped5CreationTool_title,
                Messages.Undeveloped5CreationTool_desc, types);
        entry.setId("createUndeveloped5CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Undeveloped_1005));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createContext6CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Context_1006);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Context6CreationTool_title,
                Messages.Context6CreationTool_desc, types);
        entry.setId("createContext6CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Context_1006));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createJustification7CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Justification_1007);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Justification7CreationTool_title,
                Messages.Justification7CreationTool_desc, types);
        entry.setId("createJustification7CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Justification_1007));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createSystem8CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.System_1008);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.System8CreationTool_title,
                Messages.System8CreationTool_desc, types);
        entry.setId("createSystem8CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.System_1008));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createPolicy9CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Policy_1009);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Policy9CreationTool_title,
                Messages.Policy9CreationTool_desc, types);
        entry.setId("createPolicy9CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Policy_1009));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0011CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef001_1010);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0011CreationTool_title,
                Messages.Userdef0011CreationTool_desc, types);
        entry.setId("createUserdef0011CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef001_1010));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0022CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef002_1011);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0022CreationTool_title,
                Messages.Userdef0022CreationTool_desc, types);
        entry.setId("createUserdef0022CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef002_1011));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0033CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef003_1012);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0033CreationTool_title,
                Messages.Userdef0033CreationTool_desc, types);
        entry.setId("createUserdef0033CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef003_1012));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0044CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef004_1013);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0044CreationTool_title,
                Messages.Userdef0044CreationTool_desc, types);
        entry.setId("createUserdef0044CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef004_1013));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0055CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef005_1014);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0055CreationTool_title,
                Messages.Userdef0055CreationTool_desc, types);
        entry.setId("createUserdef0055CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef005_1014));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUserdef0066CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.Userdef006_1015);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Userdef0066CreationTool_title,
                Messages.Userdef0066CreationTool_desc, types);
        entry.setId("createUserdef0066CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.Userdef006_1015));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createLink1CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.DcaseLink001_3001);
        LinkToolEntry entry = new LinkToolEntry(
                Messages.Link1CreationTool_title,
                Messages.Link1CreationTool_desc, types);
        entry.setId("createLink1CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.DcaseLink001_3001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createLink2CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.DcaseLink002_3002);
        LinkToolEntry entry = new LinkToolEntry(
                Messages.Link2CreationTool_title,
                Messages.Link2CreationTool_desc, types);
        entry.setId("createLink2CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.DcaseLink002_3002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createLink3CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.DcaseLink003_3003);
        LinkToolEntry entry = new LinkToolEntry(
                Messages.Link3CreationTool_title,
                Messages.Link3CreationTool_desc, types);
        entry.setId("createLink3CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.DcaseLink003_3003));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createLink4CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DcaseElementTypes.DcaseLink004_3004);
        LinkToolEntry entry = new LinkToolEntry(
                Messages.Link4CreationTool_title,
                Messages.Link4CreationTool_desc, types);
        entry.setId("createLink4CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DcaseElementTypes
                .getImageDescriptor(DcaseElementTypes.DcaseLink004_3004));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private static class NodeToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List elementTypes;

        /**
         * @generated
         */
        private NodeToolEntry(String title, String description,
                List elementTypes) {
            super(title, description, null, null);
            this.elementTypes = elementTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }

    /**
     * @generated
     */
    private static class LinkToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List relationshipTypes;

        /**
         * @generated
         */
        private LinkToolEntry(String title, String description,
                List relationshipTypes) {
            super(title, description, null, null);
            this.relationshipTypes = relationshipTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }
}
