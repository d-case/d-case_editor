/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;

/**
 * A factory class for managing parameter verifiers.
 */
public final class DataTypeVerifierFactory extends VerifierFactory {

    /**
     * the map of the parameter names and theirs verifiers.
     */
    private Map<String, DataTypeVerifier> verifierMap = new HashMap<String, DataTypeVerifier>();

    /**
     * the instance.
     */
    private static DataTypeVerifierFactory instance;

    /**
     * Creates an instance and initializes it.
     */
    private DataTypeVerifierFactory() {
        verifierMap = ParameterDataItem.getDataTypeVerifierMapFromPreferences();
    }

    /**
     * Returns the instance.
     * 
     * @return the instance.
     */
    public static DataTypeVerifierFactory getInstance() {
        if (instance == null) {
            instance = new DataTypeVerifierFactory();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized DataTypeVerifier getVerifier(String paramName) {
        return verifierMap.get(paramName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized List<String> getParamNames() {
        List <String> resultList = new ArrayList<String>();
        resultList.addAll(verifierMap.keySet());
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void refresh() {
        if (instance != null) {
            synchronized (instance) {
                instance = new DataTypeVerifierFactory();
            }
        }
    }
}
