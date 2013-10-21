/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

/**
 * A enumeration that represents a rule for the value of the attribute.
 */
public enum AttributeRuleType {
    /** No rule. */
    NO_RULE,
    /** the string that is not nillable. */
    STRING_NOT_NULL,
    /** the string that is not empty. */
    STRING_NOT_EMPTY,
    /** the string that matches URL format. */
    STRING_URL,
    /** the integer that is 1 or more. */
    INTEGER_ONE_OR_MORE;
}
