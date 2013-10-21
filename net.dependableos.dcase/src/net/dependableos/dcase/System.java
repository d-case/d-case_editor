/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

import java.math.BigDecimal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.System#getScore <em>Score</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getWeight <em>Weight</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getNodeLink <em>Node Link</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getSystem()
 * @model
 * @generated
 */
public interface System extends BasicNode {
    /**
     * Returns the value of the '<em><b>Score</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Score</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Score</em>' attribute.
     * @see #setScore(BigDecimal)
     * @see net.dependableos.dcase.DcasePackage#getSystem_Score()
     * @model default="0"
     * @generated
     */
    BigDecimal getScore();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getScore <em>Score</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Score</em>' attribute.
     * @see #getScore()
     * @generated
     */
    void setScore(BigDecimal value);

    /**
     * Returns the value of the '<em><b>Weight</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Weight</em>' attribute.
     * @see #setWeight(int)
     * @see net.dependableos.dcase.DcasePackage#getSystem_Weight()
     * @model default="1"
     * @generated
     */
    int getWeight();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getWeight <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Weight</em>' attribute.
     * @see #getWeight()
     * @generated
     */
    void setWeight(int value);

    /**
     * Returns the value of the '<em><b>Node Link</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Link</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Link</em>' attribute.
     * @see #setNodeLink(String)
     * @see net.dependableos.dcase.DcasePackage#getSystem_NodeLink()
     * @model
     * @generated
     */
    String getNodeLink();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getNodeLink <em>Node Link</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Link</em>' attribute.
     * @see #getNodeLink()
     * @generated
     */
    void setNodeLink(String value);

    /**
     * Returns the value of the '<em><b>Param</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Param</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Param</em>' attribute.
     * @see #setParam(String)
     * @see net.dependableos.dcase.DcasePackage#getSystem_Param()
     * @model
     */
    String getParam();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getParam <em>Param</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Param</em>' attribute.
     * @see #getParam()
     */
    void setParam(String value);

} // System
