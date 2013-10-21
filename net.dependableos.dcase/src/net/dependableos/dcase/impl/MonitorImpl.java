/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.Monitor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.MonitorImpl#isIsNormal <em>Is Normal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorImpl extends BasicNodeImpl implements Monitor {
    /**
     * The default value of the '{@link #isIsNormal() <em>Is Normal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsNormal()
     * @generated
     * @ordered
     */
    protected static final boolean IS_NORMAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsNormal() <em>Is Normal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsNormal()
     * @generated
     * @ordered
     */
    protected boolean isNormal = IS_NORMAL_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MonitorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.MONITOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsNormal() {
        return isNormal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsNormal(boolean newIsNormal) {
        boolean oldIsNormal = isNormal;
        isNormal = newIsNormal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.MONITOR__IS_NORMAL, oldIsNormal, isNormal));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.MONITOR__IS_NORMAL:
                return isIsNormal();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DcasePackage.MONITOR__IS_NORMAL:
                setIsNormal((Boolean)newValue);
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
            case DcasePackage.MONITOR__IS_NORMAL:
                setIsNormal(IS_NORMAL_EDEFAULT);
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
            case DcasePackage.MONITOR__IS_NORMAL:
                return isNormal != IS_NORMAL_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (isNormal: ");
        result.append(isNormal);
        result.append(')');
        return result.toString();
    }
    
} //MonitorImpl
