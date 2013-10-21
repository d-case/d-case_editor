/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import java.util.List;

/**
 * An abstract class that provides method to create and verifier for parameters.
 */
public abstract class VerifierFactory {

    /**
     * Returns the verifier for the parameter that has specified parameter.
     * 
     * @param paramName the parameter name.
     * @return the verifier for the parameter.
     */
    public abstract DataTypeVerifier getVerifier(String paramName);

    /**
     * Returns the list of the parameter names.
     * 
     * @return the list of the parameter names.
     */
    public abstract List<String> getParamNames();
    
    /**
     * Refreshes.
     */
    public abstract void refresh();
}
