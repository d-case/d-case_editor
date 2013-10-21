/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.util.Iterator;


import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.providers.TopDownProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * A custom provider that lays out the D-Case direct graph.
 */
public class DcaseLayoutProvider extends TopDownProvider {

    /**
     * the container type.
     */
    private static final String CONTAINER_TYPE = "Dcase"; //$NON-NLS-1$
    /**
     * The command parameter horizontal value.
     */
    private static final String CONST_HORIZONTAL_VALUE = "horizontal"; //$NON-NLS-1$
    /**
     * The command id.
     */
    private static final String CONST_ARRANGE_ANGLE_COMMAND =
            "net.dependableos.dcase.diagram.editor.command.arrangeAngle"; //$NON-NLS-1$
    /**
     * The radio command state id.
     */
    private static final String CONST_RADIO_COMMAND_STATE =
            "org.eclipse.ui.commands.radioState"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean provides(IOperation operation) {

        if (operation instanceof ILayoutNodeOperation) {
            Iterator nodes = ((ILayoutNodeOperation) operation)
                    .getLayoutNodes().listIterator();
            if (nodes.hasNext()) {
                View node = ((ILayoutNode) nodes.next()).getNode();
                Diagram container = node.getDiagram();
                if (container.getType().equals(CONTAINER_TYPE)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DirectedGraphLayout createGraphLayout() {

        // gets target command menu.
        ICommandService commandService =
                (ICommandService) PlatformUI.getWorkbench()
                    .getService(ICommandService.class);
        Command radioCommand = commandService.getCommand(CONST_ARRANGE_ANGLE_COMMAND);

        // gets current arrange direction state.
        State state = radioCommand.getState(CONST_RADIO_COMMAND_STATE);
        String currentState = state.getValue().toString();

        // sets command state to angle instance.
        if (currentState.equals(CONST_HORIZONTAL_VALUE)) {
            ArrangeAngle.createInstance().setAngle(ArrangeAngle.Direction.Horizontal);
        } else {
            ArrangeAngle.createInstance().setAngle(ArrangeAngle.Direction.Vertical);
        }

        return new DcaseDirectedGraphLayout();
    }
}