/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

/**
 * A simple XML Namespace context.
 */
public class SimpleNamespaceContext implements NamespaceContext {

    /**
     * the map that manages prefixes and Namespaces.
     */
    private Map<String, String> namespaces = new HashMap<String, String>();

    /**
     * Returns the Namespace URI that is represented specified prefix.
     * 
     * @param arg0 the prefix.
     * @return the Namespace URI.
     */
    public String getNamespaceURI(String arg0) {
        return namespaces.get(arg0);
    }

    /**
     * Returns the prefix that represents specified Namespace URI.
     * 
     * @param arg0 the Namespace URI.
     * @return the prefix.
     */
    public String getPrefix(String arg0) {
        for (Map.Entry<String, String> e : namespaces.entrySet()) {
            if (e.getKey().equals(arg0)) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * Returns all prefixes bound to a Namespace URI in the current scope.
     * 
     * @param arg0 URI of Namespace to lookup.
     * 
     * @return Iterator for all prefixes bound to the Namespace URI in the
     *         current scope.
     */
    @SuppressWarnings("unchecked")
    public Iterator getPrefixes(String arg0) {
        return null;
    }

    /**
     * Adds a Namespace.
     * 
     * @param prefix the prefix.
     * @param namespace the Namespace URI.
     */
    public void addNamespace(String prefix, String namespace) {
        namespaces.put(prefix, namespace);
    }

}
