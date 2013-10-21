/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.Monitor#isIsNormal <em>Is Normal</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getMonitor()
 * @model
 * @generated
 */
public interface Monitor extends BasicNode {
    /**
     * Returns the value of the '<em><b>Is Normal</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Normal</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Normal</em>' attribute.
     * @see #setIsNormal(boolean)
     * @see net.dependableos.dcase.DcasePackage#getMonitor_IsNormal()
     * @model default="false"
     * @generated
     */
    boolean isIsNormal();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.Monitor#isIsNormal <em>Is Normal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Normal</em>' attribute.
     * @see #isIsNormal()
     * @generated
     */
    void setIsNormal(boolean value);

} // Monitor
