/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Justification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.Justification#getStakeholder <em>Stakeholder</em>}</li>
 *   <li>{@link net.dependableos.dcase.Justification#getRiskAnalysis <em>Risk Analysis</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getJustification()
 * @model
 * @generated
 */
public interface Justification extends BasicNode {
    /**
     * Returns the value of the '<em><b>Stakeholder</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stakeholder</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stakeholder</em>' attribute.
     * @see #setStakeholder(String)
     * @see net.dependableos.dcase.DcasePackage#getJustification_Stakeholder()
     * @model default=""
     * @generated
     */
    String getStakeholder();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.Justification#getStakeholder <em>Stakeholder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Stakeholder</em>' attribute.
     * @see #getStakeholder()
     * @generated
     */
    void setStakeholder(String value);

    /**
     * Returns the value of the '<em><b>Risk Analysis</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Risk Analysis</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Risk Analysis</em>' attribute.
     * @see #setRiskAnalysis(String)
     * @see net.dependableos.dcase.DcasePackage#getJustification_RiskAnalysis()
     * @model
     * @generated
     */
    String getRiskAnalysis();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.Justification#getRiskAnalysis <em>Risk Analysis</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Risk Analysis</em>' attribute.
     * @see #getRiskAnalysis()
     * @generated
     */
    void setRiskAnalysis(String value);

} // Justification
