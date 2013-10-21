/*******************************************************************************
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 *******************************************************************************/
package net.dependableos.dcase.diagram.common.validator;

/**
 * An enumeration that defines multiplicities of node connection.
 */
public enum NodeMultiplicity {

    /** 0. */
    ZERO,
    /** 1. */
    ONE,
    /** 1 or more. */
    ONE_OR_MORE,
    /** 0 or more. */
    ZERO_OR_MORE,
    /** 0 or 1. */
    ZERO_OR_ONE;

}
