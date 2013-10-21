/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.Justification;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Justification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.JustificationImpl#getStakeholder <em>Stakeholder</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.JustificationImpl#getRiskAnalysis <em>Risk Analysis</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JustificationImpl extends BasicNodeImpl implements Justification {
    /**
     * The default value of the '{@link #getStakeholder() <em>Stakeholder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStakeholder()
     * @generated
     * @ordered
     */
    protected static final String STAKEHOLDER_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getStakeholder() <em>Stakeholder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStakeholder()
     * @generated
     * @ordered
     */
    protected String stakeholder = STAKEHOLDER_EDEFAULT;

    /**
     * The default value of the '{@link #getRiskAnalysis() <em>Risk Analysis</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRiskAnalysis()
     * @generated
     * @ordered
     */
    protected static final String RISK_ANALYSIS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRiskAnalysis() <em>Risk Analysis</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRiskAnalysis()
     * @generated
     * @ordered
     */
    protected String riskAnalysis = RISK_ANALYSIS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected JustificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.JUSTIFICATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStakeholder() {
        return stakeholder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStakeholder(String newStakeholder) {
        String oldStakeholder = stakeholder;
        stakeholder = newStakeholder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.JUSTIFICATION__STAKEHOLDER, oldStakeholder, stakeholder));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRiskAnalysis(String newRiskAnalysis) {
        String oldRiskAnalysis = riskAnalysis;
        riskAnalysis = newRiskAnalysis;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.JUSTIFICATION__RISK_ANALYSIS, oldRiskAnalysis, riskAnalysis));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.JUSTIFICATION__STAKEHOLDER:
                return getStakeholder();
            case DcasePackage.JUSTIFICATION__RISK_ANALYSIS:
                return getRiskAnalysis();
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
            case DcasePackage.JUSTIFICATION__STAKEHOLDER:
                setStakeholder((String)newValue);
                return;
            case DcasePackage.JUSTIFICATION__RISK_ANALYSIS:
                setRiskAnalysis((String)newValue);
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
            case DcasePackage.JUSTIFICATION__STAKEHOLDER:
                setStakeholder(STAKEHOLDER_EDEFAULT);
                return;
            case DcasePackage.JUSTIFICATION__RISK_ANALYSIS:
                setRiskAnalysis(RISK_ANALYSIS_EDEFAULT);
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
            case DcasePackage.JUSTIFICATION__STAKEHOLDER:
                return STAKEHOLDER_EDEFAULT == null ? stakeholder != null : !STAKEHOLDER_EDEFAULT.equals(stakeholder);
            case DcasePackage.JUSTIFICATION__RISK_ANALYSIS:
                return RISK_ANALYSIS_EDEFAULT == null ? riskAnalysis != null : !RISK_ANALYSIS_EDEFAULT.equals(riskAnalysis);
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
        result.append(" (stakeholder: ");
        result.append(stakeholder);
        result.append(", riskAnalysis: ");
        result.append(riskAnalysis);
        result.append(')');
        return result.toString();
    }

} //JustificationImpl
