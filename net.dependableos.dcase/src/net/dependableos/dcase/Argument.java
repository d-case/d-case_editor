/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Argument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.Argument#getRootBasicNode <em>Root Basic Node</em>}</li>
 *   <li>{@link net.dependableos.dcase.Argument#getRootBasicLink <em>Root Basic Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getArgument()
 * @model
 * @generated
 */
public interface Argument extends BasicNode {
    /**
     * Returns the value of the '<em><b>Root Basic Node</b></em>' containment reference list.
     * The list contents are of type {@link net.dependableos.dcase.BasicNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Basic Node</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Basic Node</em>' containment reference list.
     * @see net.dependableos.dcase.DcasePackage#getArgument_RootBasicNode()
     * @model containment="true"
     * @generated
     */
    EList<BasicNode> getRootBasicNode();

    /**
     * Returns the value of the '<em><b>Root Basic Link</b></em>' containment reference list.
     * The list contents are of type {@link net.dependableos.dcase.BasicLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Basic Link</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Basic Link</em>' containment reference list.
     * @see net.dependableos.dcase.DcasePackage#getArgument_RootBasicLink()
     * @model containment="true"
     * @generated
     */
    EList<BasicLink> getRootBasicLink();

    /** 
     * Refreshes children. 
     */ 
 	void Refresh(); 

} // Argument
