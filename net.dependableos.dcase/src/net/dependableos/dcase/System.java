/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.System#getSubType <em>SubType</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getLeafNode <em>LeafNode</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getI <em>I</em>}</li>
 *   <li>{@link net.dependableos.dcase.System#getJ <em>J</em>}</li>
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
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>SubType</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>SubType</em>' attribute.
     * @see #setSubType(String)
     * @see net.dependableos.dcase.DcasePackage#getSystem_SubType()
     */
    String getSubType();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getSubType <em>Subtype</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SubType</em>' attribute.
     * @see #getSubType()
     */
    void setSubType(String value);

    /**
     * Returns the value of the '<em><b>I</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>I</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>I</em>' attribute.
     * @see #setI(int)
     * @see net.dependableos.dcase.DcasePackage#getSystem_I()
     */
    int getI();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getI <em>I</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>I</em>' attribute.
     * @see #getI()
     */
    void setI(int value);

    /**
     * Returns the value of the '<em><b>J</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>J</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>J</em>' attribute.
     * @see #setI(int)
     * @see net.dependableos.dcase.DcasePackage#getSystem_J()
     */
    int getJ();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getI <em>J</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>J</em>' attribute.
     * @see #getJ()
     */
    void setJ(int value);

    /**
     * Returns the value of the '<em><b>LeafNode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>LeafNode</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>LeafNode</em>' attribute.
     * @see #setLeafNode(String)
     * @see net.dependableos.dcase.DcasePackage#getSystem_LeafNode()
     * @model
     */
    String getLeafNode();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.System#getLeafNode <em>LeafNode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>LeafNode</em>' attribute.
     * @see #getLeafNode()
     */
    void setLeafNode(String value);

} // System
