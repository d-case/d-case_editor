/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

/**
 * A class that represents an operator for node connection rule.
 */
public enum RuleOperator {

    /** And. */
    AND,
    /** Or. */
    OR,
    /** Not and. */
    NAND,
    /** Exclusive or. */
    XOR;
}
