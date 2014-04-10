/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;

import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getSubType <em>SubType</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getLeafNode <em>LeafNode</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getI <em>I</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getJ <em>J</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemImpl extends BasicNodeImpl implements net.dependableos.dcase.System {
    /**
     * The default value of the '{@link #getSubType() <em>SubType</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubType()
^     * @ordered
     */
    protected static final String SUBTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSubType() <em>SubType</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubType()
     * @ordered
     */
    protected String subType = SUBTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getLeafNode() <em>LeafNode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeafNode()
^     * @ordered
     */
    protected static final String LEAFNODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLeafNode() <em>LeafNode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLefNode()
     * @ordered
     */
    protected String leafNode = LEAFNODE_EDEFAULT;

    /**
     * The default value of the '{@link #getI() <em>I</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getI()
     * @ordered
     */
    protected static final int I_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getI() <em>I</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getI()
     * @ordered
     */
    protected int i = I_EDEFAULT;

    /**
     * The default value of the '{@link #getJ() <em>J</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJ()
     * @ordered
     */
    protected static final int J_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getJ() <em>J</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJ()
     * @ordered
     */
    protected int j = J_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SystemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.SYSTEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getSubType() {
        return subType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setSubType(String newSubType) {
        String oldSubType = subType;
        subType = newSubType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__SUBTYPE, oldSubType, subType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getLeafNode() {
        return leafNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setLeafNode(String newLeafNode) {
        String oldLeafNode = leafNode;
        leafNode = newLeafNode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__LEAFNODE, oldLeafNode, leafNode));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public int getI() {
        return i;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setI(int newI) {
        int oldI = i;
        i = newI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__I, oldI, i));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public int getJ() {
        return j;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setJ(int newJ) {
        int oldJ = j;
        j = newJ;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__J, oldJ, j));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.SYSTEM__SUBTYPE:
                return getSubType();
            case DcasePackage.SYSTEM__LEAFNODE:
                return getLeafNode();
            case DcasePackage.SYSTEM__I:
                return getI();
            case DcasePackage.SYSTEM__J:
                return getJ();
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
            case DcasePackage.SYSTEM__SUBTYPE:
                setSubType((String)newValue);
                return;
            case DcasePackage.SYSTEM__LEAFNODE:
                setLeafNode((String)newValue);
                return;
            case DcasePackage.SYSTEM__I:
                setI((Integer)newValue);
                return;
            case DcasePackage.SYSTEM__J:
                setJ((Integer)newValue);
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
            case DcasePackage.SYSTEM__SUBTYPE:
                setSubType(SUBTYPE_EDEFAULT);
                return;
            case DcasePackage.SYSTEM__LEAFNODE:
                setLeafNode(LEAFNODE_EDEFAULT);
                return;
            case DcasePackage.SYSTEM__I:
                setI(I_EDEFAULT);
                return;
            case DcasePackage.SYSTEM__J:
                setI(J_EDEFAULT);
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
            case DcasePackage.SYSTEM__SUBTYPE:
                return SUBTYPE_EDEFAULT == null ? subType != null : !SUBTYPE_EDEFAULT.equals(subType);
            case DcasePackage.SYSTEM__LEAFNODE:
                return LEAFNODE_EDEFAULT == null ? leafNode != null : !LEAFNODE_EDEFAULT.equals(leafNode);
            case DcasePackage.SYSTEM__I:
                return i != I_EDEFAULT;
            case DcasePackage.SYSTEM__J:
                return j != J_EDEFAULT;
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
        result.append(" (subType: ");
        result.append(subType);
        result.append(", leafNode: ");
        result.append(leafNode);
        result.append(", i: ");
        result.append(i);
        result.append(", j: ");
        result.append(j);
        result.append(')');
        return result.toString();
    }

} //SystemImpl
