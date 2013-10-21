/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.CONST_FILE_PROTOCOL_STRING;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.CONST_HTTPS_PROTOCOL_STRING;
import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.CONST_HTTP_PROTOCOL_STRING;

/**
 * A utility class that handles strings.
 */
public final class UrlUtil {

    /**
     * A constructor.
     */
    private UrlUtil() {
    }

    /**
     * Tests whether the specified string represents available protocol.
      * 
     * @param protocol the string that represents a protocol.
     * @return true if and only if the specified string represents available protocol;false false otherwise. 
     */
    public static boolean checkDcaseReferenceProtocol(String protocol) {

        if (CONST_HTTPS_PROTOCOL_STRING.equals(protocol)
                || CONST_HTTP_PROTOCOL_STRING.equals(protocol)
                || CONST_FILE_PROTOCOL_STRING.equals(protocol)) {
            return true;
        }
        // not available protocol. 
        return false;
    }
}
