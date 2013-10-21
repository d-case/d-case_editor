/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.editor.logic.xmlconv;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;

/**
 * XSL converter item class.
 *
 */
public class XSLConverterItem {

    /**
     * The preference store root prefix.
     */
    private static final String PREFERENCE_ROOT = "net.dependableos.dcase.convert"; //$NON-NLS-1$
    /**
     * A separator of converter names.
     */
    private static final String COMMA = ","; //$NON-NLS-1$
    /**
     * A empty string.
     */
    private static final String BLANK = ""; //$NON-NLS-1$
    /**
     * A all converter name key format.
     */
    private static final String CONVERTERS_KEY = "Converters"; //$NON-NLS-1$
    /**
     * A converter file path key format.
     */
    private static final String FORMAT_CONVERTER_PATH_KEY = "%sPath"; //$NON-NLS-1$
    /**
     * The converter name.
     */
    private String name = null;
    /**
     * The converter xsl file path.
     */
    private String path = null;
    /**
     * Constructor for the class.
     * Create new instance.
     */
    public XSLConverterItem() {
    }

    /**
     * Returns string value of converter name.
     *  
     * @return A string value.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets string value of converter name.
     * 
     * @param name A string value.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns string value of converter name.
     * 
     * @return A string value.
     */
    public String getPath() {
        return path;
    }
    /**
     * Sets string value of converter name.
     * 
     * @param path A string value.
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * Returns converter items.
     *  
     * @return A list of converter item.
     */
    public static List<XSLConverterItem> getConverters() {
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_ROOT);
        String keywords = store.get(CONVERTERS_KEY, BLANK);

        return load(store, keywords);
    }
    /**
     * Load converter configure.
     * @param store A Preference store.
     * @param keywords The converter names.
     * @return A list of converter item.
     */
    private static List<XSLConverterItem> load(IEclipsePreferences store, String keywords) {
        List<XSLConverterItem> converters = new ArrayList<XSLConverterItem>();
        String [] converterNames = keywords.split(COMMA);
        for (String name : converterNames) {
            if (name == null || name.length() == 0) {
                continue;
            }
            String path = store.get(String.format(
                    FORMAT_CONVERTER_PATH_KEY, name), BLANK);
            XSLConverterItem item = new XSLConverterItem();
            item.setName(name);
            item.setPath(path);
            converters.add(item);
        }
        return converters;
    }
    /**
     * Save the converter items.
     * 
     * @param items A list of converter item.
     * @return True is succeeded.
     */
    public static boolean save(List<XSLConverterItem> items) {
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_ROOT);
        try {
            store.clear();
        } catch (BackingStoreException e) {
            MessageWriter.showErrorMessageBox(
                    Messages.ErrorPreferenceStoreClear);
            return false;
        }

        StringBuilder sb = new StringBuilder();
        int limit = items.size();
        int index = 0;
        for (XSLConverterItem item : items) {
            store.put(
                    String.format(FORMAT_CONVERTER_PATH_KEY, item.getName()),
                    item.getPath());
            sb.append(item.getName());
            if (index < limit - 1) {
                sb.append(COMMA);
            }
            index++;
        }
        store.put(CONVERTERS_KEY, sb.toString());

        try {
            store.flush();
        } catch (BackingStoreException e) {
            MessageWriter.showErrorMessageBox(
                    Messages.ErrorPreferenceStoreFlush);
            return false;
        }
        
        return true;
    }
}
