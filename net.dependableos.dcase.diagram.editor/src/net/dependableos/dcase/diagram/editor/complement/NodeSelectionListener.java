/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.Menus;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.impl.ArgumentImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * This class provides methods that deal with the events that are generated when a node of the complement menu selected.
 */
public class NodeSelectionListener extends AbstractComplementSelectionListener {

    /**
     * the selected type of the node to add.
     */
    private NodeType type;

    /**
     * Creates the listener and initializes it.
     * 
     * @param type the selected type of the node to add.
     */
    public NodeSelectionListener(NodeType type) {
        super(Menus.NodeSelectionListener_0);
        this.type = type;
    }

    /**
     * Returns the the selected type of the node to add.
     * 
     * @return the selected type of the node to add.
     */
    public NodeType getType() {
        return type;
    }

    /**
     * Returns the model to add.
     * 
     * @return the model to add.
     */
    @Override
    protected Argument getModel() {

        // creates the argument.
        ArgumentImpl argument = (ArgumentImpl) DcaseFactory.eINSTANCE
                .createArgument();

        // creates the node to add.
        BasicNode node = null;

        switch (type) {
            case CONTEXT:
                node = DcaseFactory.eINSTANCE.createContext();
                break;
            case EVIDENCE:
                node = DcaseFactory.eINSTANCE.createEvidence();
                break;
            case GOAL:
                node = DcaseFactory.eINSTANCE.createGoal();
                break;
            case JUSTIFICATION:
                node = DcaseFactory.eINSTANCE.createJustification();
                break;
            case MONITOR:
                node = DcaseFactory.eINSTANCE.createMonitor();
                break;
            case POLICY:
                node = DcaseFactory.eINSTANCE.createPolicy();
                break;
            case STRATEGY:
                node = DcaseFactory.eINSTANCE.createStrategy();
                break;
            case SYSTEM:
                node = DcaseFactory.eINSTANCE.createSystem();
                break;
            case UNDEVELOPED:
                node = DcaseFactory.eINSTANCE.createUndeveloped();
                break;
            case USERDEF001:
                node = DcaseFactory.eINSTANCE.createUserdef001();
                break;
            case USERDEF002:
                node = DcaseFactory.eINSTANCE.createUserdef002();
                break;
            case USERDEF003:
                node = DcaseFactory.eINSTANCE.createUserdef003();
                break;
            case USERDEF004:
                node = DcaseFactory.eINSTANCE.createUserdef004();
                break;
            case USERDEF005:
                node = DcaseFactory.eINSTANCE.createUserdef005();
                break;
            case USERDEF006:
                node = DcaseFactory.eINSTANCE.createUserdef006();
                break;
            default:
                return null;
        }

        // creates the link.
        BasicLink link;
        if (type == NodeType.CONTEXT) {
        	link = DcaseFactory.eINSTANCE.createDcaseLink002();
        } else {
        	link = DcaseFactory.eINSTANCE.createDcaseLink001();
        }

        // sets the target.
        link.setTarget(node);

        EObject selectedObject = DcaseEditorUtil.getSelectedObject();
        if (selectedObject instanceof BasicNode) {
            link.setSource((BasicNode) selectedObject);
        }

        // add the node and the link to the argument.
        argument.getRootBasicNode().add(node);
        argument.getRootBasicLink().add(link);

        return argument;

    }

}
