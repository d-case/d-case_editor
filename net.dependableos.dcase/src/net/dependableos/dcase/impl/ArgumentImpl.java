/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;

import java.util.Collection;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Argument</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.ArgumentImpl#getRootBasicNode <em>Root Basic Node</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.ArgumentImpl#getRootBasicLink <em>Root Basic Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArgumentImpl extends BasicNodeImpl implements Argument {
    /**
     * The cached value of the '{@link #getRootBasicNode() <em>Root Basic Node</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRootBasicNode()
     * @generated
     * @ordered
     */
    protected EList<BasicNode> rootBasicNode;

    /**
     * The cached value of the '{@link #getRootBasicLink() <em>Root Basic Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRootBasicLink()
     * @generated
     * @ordered
     */
    protected EList<BasicLink> rootBasicLink;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ArgumentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.ARGUMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BasicNode> getRootBasicNode() {
        if (rootBasicNode == null) {
            rootBasicNode = new EObjectContainmentEList<BasicNode>(BasicNode.class, this, DcasePackage.ARGUMENT__ROOT_BASIC_NODE);
        }
        return rootBasicNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BasicLink> getRootBasicLink() {
        if (rootBasicLink == null) {
            rootBasicLink = new EObjectContainmentEList<BasicLink>(BasicLink.class, this, DcasePackage.ARGUMENT__ROOT_BASIC_LINK);
        }
        return rootBasicLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
                return ((InternalEList<?>)getRootBasicNode()).basicRemove(otherEnd, msgs);
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                return ((InternalEList<?>)getRootBasicLink()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
                return getRootBasicNode();
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                return getRootBasicLink();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
                getRootBasicNode().clear();
                getRootBasicNode().addAll((Collection<? extends BasicNode>)newValue);
                return;
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                getRootBasicLink().clear();
                getRootBasicLink().addAll((Collection<? extends BasicLink>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
                getRootBasicNode().clear();
                return;
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                getRootBasicLink().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
                return rootBasicNode != null && !rootBasicNode.isEmpty();
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                return rootBasicLink != null && !rootBasicLink.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserdef007(String newUserdef007){
        super.setUserdef007(newUserdef007);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameters(){
        String parameters = getUserdef007();
        if(parameters == null){
            parameters = ""; //$NON-NLS-1$
        }
        return parameters;
    }
    
    /**
     * Refreshes children.
     */
    public void Refresh(){
    }
} //ArgumentImpl
