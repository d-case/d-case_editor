/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.model;

import net.dependableos.dcase.diagram.common.validator.AttributeRuleType;

/**
 * The ArrtibuteType represents the attribute type and regulation of the value. 
 */
public enum AttributeType {

    /** The ID attribute. */
    ID(AttributeRuleType.STRING_NOT_EMPTY),
    /** The Name attribute. */
    NAME(AttributeRuleType.NO_RULE),
    /** The Desc attribute. */
    DESC(AttributeRuleType.NO_RULE),
    /** The Attachment attribute. */
    ATTACHMENT(AttributeRuleType.NO_RULE),
    /** The Status attribute. */
    STATUS(AttributeRuleType.NO_RULE),
    /** The Flag attribute. */
    FLAG(AttributeRuleType.NO_RULE),
    /** The RespName attribute. */
    RESPNAME(AttributeRuleType.NO_RULE),
    /** The RespAddress attribute. */
    RESPADDRESS(AttributeRuleType.NO_RULE),
    /** The RespIcon attribute. */
    RESPICON(AttributeRuleType.NO_RULE),
    /** The RespTime attribute. */
    RESPTIME(AttributeRuleType.NO_RULE),
    /** The Message attribute. */
    MESSAGE(AttributeRuleType.NO_RULE),
    /** The Requirement attribute. */
    REQUIREMENT(AttributeRuleType.NO_RULE),
    /** The Parent attribute. */
    PARENT(AttributeRuleType.NO_RULE),
    /** The RefSource attribute. */
    REFSOURCE(AttributeRuleType.NO_RULE),
    /** The ParameterDefs attribute. */
    PARAMETERDEFS(AttributeRuleType.NO_RULE),
    /** The ParameterVals attribute. */
    PARAMETERVALS(AttributeRuleType.NO_RULE),
    /** The ParameterizedDesc attribute. */
    PARAMETERIZEDDESC(AttributeRuleType.NO_RULE),
    /** The Userdef001 attribute. */
    USERDEF001(AttributeRuleType.NO_RULE),
    /** The Userdef002 attribute. */
    USERDEF002(AttributeRuleType.NO_RULE),
    /** The Userdef003 attribute. */
    USERDEF003(AttributeRuleType.NO_RULE),
    /** The Userdef004 attribute. */
    USERDEF004(AttributeRuleType.NO_RULE),
    /** The Userdef005 attribute. */
    USERDEF005(AttributeRuleType.NO_RULE),
    /** The Userdef006 attribute. */
    USERDEF006(AttributeRuleType.NO_RULE),
    /** The Userdef007 attribute. */
    USERDEF007(AttributeRuleType.NO_RULE),
    /** The Userdef008 attribute. */
    USERDEF008(AttributeRuleType.NO_RULE),
    /** The Userdef009 attribute. */
    USERDEF009(AttributeRuleType.NO_RULE),
    /** The Userdef010 attribute. */
    USERDEF010(AttributeRuleType.NO_RULE),
    /** The Userdef011 attribute. */
    USERDEF011(AttributeRuleType.NO_RULE),
    /** The Userdef012 attribute. */
    USERDEF012(AttributeRuleType.NO_RULE),
    /** The Userdef013 attribute. */
    USERDEF013(AttributeRuleType.NO_RULE),
    /** The Userdef014 attribute. */
    USERDEF014(AttributeRuleType.NO_RULE),
    /** The Userdef015 attribute. */
    USERDEF015(AttributeRuleType.NO_RULE),
    /** The Userdef016 attribute. */
    USERDEF016(AttributeRuleType.NO_RULE),
    /** The Score attribute. */
    SCORE(AttributeRuleType.NO_RULE),
    /** The Weight attribute. */
    WEIGHT(AttributeRuleType.INTEGER_ONE_OR_MORE),
    /** requirements. */
    REQUIREMENTS(AttributeRuleType.NO_RULE),
    /** The IsNormal attribute. */
    IS_NORMAL(AttributeRuleType.NO_RULE),
    /** The Stakeholder attribute. */
    STAKEHOLDER(AttributeRuleType.NO_RULE),
    /** The RiskAnalysis attribute. */
    RISK_ANALYSIS(AttributeRuleType.STRING_URL),
    /** The SubType attribute. */
    SUBTYPE(AttributeRuleType.NO_RULE),
    /** The LeafNode attribute. */
    LEAFNODE(AttributeRuleType.NO_RULE),
    /** The I attribute. */
    I(AttributeRuleType.NO_RULE),
    /** The J attribute. */
    J(AttributeRuleType.NO_RULE),
    /** The source attribute. */
    SOURCE(AttributeRuleType.NO_RULE),
    /** The target attribute. */
    TARGET(AttributeRuleType.NO_RULE),
    /** The SiblingOrder attribute. */
    SIBLINGORDER(AttributeRuleType.NO_RULE);

    /**
     * the regulation of the value. 
     */
    private AttributeRuleType ruleType;

    /**
     * Allocates a Attribute type object and initializes it to represent the regulation of the value.
     * 
     * @param ruleType the regulation of the value. 
     */
    private AttributeType(AttributeRuleType ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * Returns the regulation of the value.
     * 
     * @return the regulation of the value.
     */
    public AttributeRuleType getRuleType() {
        return ruleType;
    }
    
}