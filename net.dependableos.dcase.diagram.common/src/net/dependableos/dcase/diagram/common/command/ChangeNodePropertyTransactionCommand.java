/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.command;

import java.util.List;
import java.util.Map.Entry;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.model.NodeInfo;
import net.dependableos.dcase.diagram.common.util.LinkManager;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to change the value of attributes of nodes.
 */
public class ChangeNodePropertyTransactionCommand extends
        AbstractTransactionalCommand {
    
    /**
     * the list of nodes.
     */
    private List<NodeInfo> changeNodeList;
    /**
     * the link manager.
     */
    private LinkManager linkManager;


    /**
     * Allocates a ChangeNodePropertyTransactionCommand object and initialize it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param nodeList the list of nodes.
     * @param linkManager the link manager.
     */
    public ChangeNodePropertyTransactionCommand(
            TransactionalEditingDomain domain, String label, List<NodeInfo> nodeList,
            LinkManager linkManager) {
        super(domain, label, null);
        
        this.linkManager = linkManager;
        this.changeNodeList = nodeList;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        
        for (NodeInfo nodeInfo : changeNodeList) {
            BasicNode basicNode = this.linkManager.getBasicNode((String) nodeInfo.getAttribute(AttributeType.ID));
            
            for (Entry<AttributeType, Object> entry : nodeInfo.getAttributeMap().entrySet()) {
                
                Object value = entry.getValue();
                if (value == null) { continue; }
                switch (entry.getKey()) {
                    case NAME:
                        basicNode.setName((String) value);
                        break;
                    case DESC:
                        basicNode.setDesc((String) value);
                        break;
                    case ATTACHMENT:
                        basicNode.setAttachment((String) value);
                        break;
                    case STATUS:
                        basicNode.setStatus((String) value);
                        break;
                    case FLAG:
                        basicNode.setFlag((String) value);
                        break;
                    case RESPNAME:
                        basicNode.setRespName((String) value);
                        break;
                    case RESPADDRESS:
                        basicNode.setRespAddress((String) value);
                        break;
                    case RESPICON:
                        basicNode.setRespIcon((String) value);
                        break;
                    case RESPTIME:
                        basicNode.setRespTime((String) value);
                        break;
                    case MESSAGE:
                        basicNode.setMessage((String) value);
                        break;
                    case REQUIREMENT:
                        basicNode.setRequirement((String) value);
                        break;
                    case PARENT:
                        basicNode.setParent((String) value);
                        break;
                    case REFSOURCE:
                        basicNode.setRefSource((String) value);
                        break;
                    case PARAMETERDEFS:
                        basicNode.setParameterDefs((String) value);
                        break;
                    case PARAMETERVALS:
                        basicNode.setParameterVals((String) value);
                        break;
                    case PARAMETERIZEDDESC:
                        basicNode.setParameterizedDesc((String) value);
                        break;
                    case USERDEF001:
                        basicNode.setUserdef001((String) value);
                        break;
                    case USERDEF002:
                        basicNode.setUserdef002((String) value);
                        break;
                    case USERDEF003:
                        basicNode.setUserdef003((String) value);
                        break;
                    case USERDEF004:
                        basicNode.setUserdef004((String) value);
                        break;
                    case USERDEF005:
                        basicNode.setUserdef005((String) value);
                        break;
                    case USERDEF006:
                        basicNode.setUserdef006((String) value);
                        break;
                    case USERDEF007:
                        basicNode.setUserdef007((String) value);
                        break;
                    case USERDEF008:
                        basicNode.setUserdef008((String) value);
                        break;
                    case USERDEF009:
                        basicNode.setUserdef009((String) value);
                        break;
                    case USERDEF010:
                        basicNode.setUserdef010((String) value);
                        break;
                    case USERDEF011:
                        basicNode.setUserdef011((String) value);
                        break;
                    case USERDEF012:
                        basicNode.setUserdef012((String) value);
                        break;
                    case USERDEF013:
                        basicNode.setUserdef013((String) value);
                        break;
                    case USERDEF014:
                        basicNode.setUserdef014((String) value);
                        break;
                    case USERDEF015:
                        basicNode.setUserdef015((String) value);
                        break;
                    case USERDEF016:
                        basicNode.setUserdef016((String) value);
                        break;
                    default:
                        break;
                }
            }
        }
        
        return CommandResult.newOKCommandResult();
    }

}
