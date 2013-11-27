/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.command;

import java.util.List;
import java.util.Map;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.diagram.common.model.AttributeType;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A transaction command to change the value of attributes of a link.
 */
public class ChangeBasicLinkPropertyTransactionCommand extends
        AbstractTransactionalCommand {

    /**
     * the link.
     */
    private BasicLink basicLink;

    /**
     * the map of attributes.
     */
    private Map<AttributeType, Object> attributeMap;

    /**
     * Allocates a ChangeBasicLinkPropertyTransactionCommand object and initialize it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param affectedFiles the list of affected IFiles.
     * @param basicLink the link.
     * @param attributeMap the map of attributes.
     */
    @SuppressWarnings("unchecked")
    public ChangeBasicLinkPropertyTransactionCommand(
            TransactionalEditingDomain domain, String label,
            List affectedFiles, BasicLink basicLink,
            Map<AttributeType, Object> attributeMap) {
        super(domain, label, affectedFiles);
        this.basicLink = basicLink;
        this.attributeMap = attributeMap;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
     *  #doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(
            IProgressMonitor progressMonitor, IAdaptable info)
            throws ExecutionException {

        // sets values of common attributes.
        setCommonAttribute();

        return CommandResult.newOKCommandResult();
    }

    /**
     * Sets values of common attributes.
     */
    private void setCommonAttribute() {

        for (Map.Entry<AttributeType, Object> attribute : attributeMap
                .entrySet()) {
            Object value = attribute.getValue();
            switch (attribute.getKey()) {
                case NAME:
                    basicLink.setName((String) value);
                    break;
                case DESC:
                    basicLink.setDesc((String) value);
                    break;
                case ATTACHMENT:
                    basicLink.setAttachment((String) value);
                    break;
                case STATUS:
                    basicLink.setStatus((String) value);
                    break;
                case SIBLINGORDER:
                    basicLink.setSiblingOrder((String) value);
                    break;
                case MESSAGE:
                    basicLink.setMessage((String) value);
                    break;
                case USERDEF001:
                    basicLink.setUserdef001((String) value);
                    break;
                case USERDEF002:
                    basicLink.setUserdef002((String) value);
                    break;
                case USERDEF003:
                    basicLink.setUserdef003((String) value);
                    break;
                case USERDEF004:
                    basicLink.setUserdef004((String) value);
                    break;
                case USERDEF005:
                    basicLink.setUserdef005((String) value);
                    break;
                case USERDEF006:
                    basicLink.setUserdef006((String) value);
                    break;
                case USERDEF007:
                    basicLink.setUserdef007((String) value);
                    break;
                case USERDEF008:
                    basicLink.setUserdef008((String) value);
                    break;
                case USERDEF009:
                    basicLink.setUserdef009((String) value);
                    break;
                case USERDEF010:
                    basicLink.setUserdef010((String) value);
                    break;
                case USERDEF011:
                    basicLink.setUserdef011((String) value);
                    break;
                case USERDEF012:
                    basicLink.setUserdef012((String) value);
                    break;
                case USERDEF013:
                    basicLink.setUserdef013((String) value);
                    break;
                case USERDEF014:
                    basicLink.setUserdef014((String) value);
                    break;
                case USERDEF015:
                    basicLink.setUserdef015((String) value);
                    break;
                case USERDEF016:
                    basicLink.setUserdef016((String) value);
                    break;
                default:
                    break;
            }
        }
    }
}
