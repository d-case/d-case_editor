/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

/**
 * A utility class that handles numbers.
 */
public final class NumberUtil {

    /**
     * A constructor.
     */
    private NumberUtil() {
    }

    /**
     * Parses and returns the string to the int value.
     * Returns the default value if failed to parse.
     * 
     * @param number the string.
     * @param defaultValue the default value.
     * @return the int value.
     */
    public static int parseIntWithDefault(String number, int defaultValue) {

        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
}
