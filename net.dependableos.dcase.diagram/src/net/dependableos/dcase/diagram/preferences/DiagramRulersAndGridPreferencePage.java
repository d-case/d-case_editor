/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.preferences;


import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;

import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramRulersAndGridPreferencePage extends RulerGridPreferencePage {

    /**
     * @generated
     */
    public DiagramRulersAndGridPreferencePage() {
        setPreferenceStore(DcaseDiagramEditorPlugin.getInstance()
                .getPreferenceStore());
    }

// AUTO_GENERATED:END

    /**
     * Initializes the default preference values 
     * for this preference store.
     * 
     * @param preferenceStore the preference store.
     * @generated NOT
     */
    public static void initDefaults(IPreferenceStore preferenceStore) {

        RulerGridPreferencePage.initDefaults(preferenceStore);

        // sets to show rulers.
        preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_RULERS, true);

    }
}
