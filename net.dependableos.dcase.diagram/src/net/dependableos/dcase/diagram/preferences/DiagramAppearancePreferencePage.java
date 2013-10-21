/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.diagram.preferences;


import net.dependableos.dcase.diagram.part.DcaseDiagramEditorPlugin;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

/**
 * @generated
 */
public class DiagramAppearancePreferencePage extends AppearancePreferencePage {

    /**
     * @generated
     */
    public DiagramAppearancePreferencePage() {
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
        AppearancePreferencePage.initDefaults(preferenceStore);

        // sets the default line color.
        PreferenceConverter.setDefault(preferenceStore,
                IPreferenceConstants.PREF_LINE_COLOR, ColorConstants.black
                        .getRGB());
    }
}
