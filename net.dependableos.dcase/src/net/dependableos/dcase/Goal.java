/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.Goal#getScore <em>Score</em>}</li>
 *   <li>{@link net.dependableos.dcase.Goal#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getGoal()
 * @model
 * @generated
 */
public interface Goal extends BasicNode {
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
     * @see net.dependableos.dcase.DcasePackage#getGoal_Score()
     * @model default="0"
     * @generated
     */
    BigDecimal getScore();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.Goal#getScore <em>Score</em>}' attribute.
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
     * @see net.dependableos.dcase.DcasePackage#getGoal_Weight()
     * @model default="1"
     * @generated
     */
    int getWeight();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.Goal#getWeight <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Weight</em>' attribute.
     * @see #getWeight()
     * @generated
     */
    void setWeight(int value);

} // Goal
