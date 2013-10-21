/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import java.util.Collection;


/**
 * A utility class that handles strings.
 */
public final class StringUtil {

    /**
     * The empty String "".
     */
    public static final String EMPTY = "";
    
    /**
     * A constructor.
     */
    private StringUtil() {
    }

    /**
     * Returns the joined string.
     * 
     * @param coll the collection of strings to join.
     * @param delimiter the delimiter.
     * @return the joined string.
     */
    public static String join(Collection<String> coll, String delimiter) {

        StringBuilder buff = new StringBuilder();
        for (String str : coll) {
            if (buff.length() > 0) {
                buff.append(delimiter);
            }
            buff.append(str);
        }

        return buff.toString();
    }
    
    /**
     * Checks if a String is empty ("") or null.
     * 
     * StringUtil.isNullOrEmpty(null)      = true
     * StringUtil.isNullOrEmpty("")        = true
     * StringUtil.isNullOrEmpty(" ")       = false
     * StringUtil.isNullOrEmpty("abc")     = false
     * StringUtil.isNullOrEmpty("  abc  ") = false
     * 
     * @param str the String to check, may be null
     * @return true if the String is empty or null
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a String is whitespace, empty ("") or null.
     * 
     * StringUtil.isNullOrBlank(null)      = true
     * StringUtil.isNullOrBlank("")        = true
     * StringUtil.isNullOrBlank(" ")       = true
     * StringUtil.isNullOrBlank("     ")   = true
     * StringUtil.isNullOrBlank("abc")     = false
     * StringUtil.isNullOrBlank("  abc  ") = false
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isNullOrBlank(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
