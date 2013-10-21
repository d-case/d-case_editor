/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.computenodes;

/**
 * The class that computes the count of the node and link.
 */
public class ComputeNodesModel {
    
    /**
     * The name of node and Link.
     */
    private String nodeLink = null;
    
    /**
     * The total count of node and link.
     */
    private String total = null;

    /**
     * Returns the nodeLink.
     * 
     * @return the nodeLink
     */
    public String getNodeLink() {
        return nodeLink;
    }

    /**
     * Sets the nodeLink.
     * 
     * @param nodeLink the nodeLink to set
     */
    public void setNodeLink(String nodeLink) {
        this.nodeLink = nodeLink;
    }

    /**
     * Returns the total.
     * 
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the total.
     * 
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }
}
