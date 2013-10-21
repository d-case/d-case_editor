/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * A utility class that handles system properties.
 */
public final class PropertyUtil {

    /**
     * the path to the system resource.
     */
    private static final String SYSTEM_PROPERTIES_FILE_PATH = "/resource/system.properties";    //$NON-NLS-1$

    /**
     * the instance.
     */
    private static PropertyUtil propertyUtil;

    /**
     * the system properties.
     */
    private Properties systemProperties;

    /**
     * A constructor.
     */
    private PropertyUtil() {
    }

    /**
     * Returns the instance.
     * 
     * @return the instance.
     */
    public static synchronized PropertyUtil getInstance() {
        if (propertyUtil == null) {
            propertyUtil = new PropertyUtil();
        }
        return propertyUtil;
    }

    /**
     * Returns the value of the system property.
     * 
     * @param key the key.
     * @return the value.
     */
    public static String getSystemProperty(String key) {
        return getInstance().readSystemProperty(key);
    }

    /**
     * Reads the value of the system property.
     * 
     * @param key the key.
     * @return the value.
     */
    private synchronized String readSystemProperty(String key) {
        if (systemProperties == null) {
            try {
                InputStream is = this.getClass().getResourceAsStream(
                        SYSTEM_PROPERTIES_FILE_PATH);
                systemProperties = new Properties();
                systemProperties.load(is);
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("system property file I/O error", e);
            }
        }
        if (systemProperties != null) {
            return systemProperties.getProperty(key);
        }

        return null;
    }
}
