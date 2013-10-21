/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;

import java.math.BigDecimal;


import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.Goal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Goal</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.dependableos.dcase.impl.GoalImpl#getScore <em>Score</em>}</li>
 * <li>{@link net.dependableos.dcase.impl.GoalImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GoalImpl extends BasicNodeImpl implements Goal {

    /**
     * The default value of the '{@link #getScore() <em>Score</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getScore()
     * @generated
     * @ordered
     */
    protected static final BigDecimal SCORE_EDEFAULT = new BigDecimal("0");

    /**
     * The cached value of the '{@link #getScore() <em>Score</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getScore()
     * @generated
     * @ordered
     */
    protected BigDecimal score = SCORE_EDEFAULT;

    /**
     * The default value of the '{@link #getWeight() <em>Weight</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected static final int WEIGHT_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected int weight = WEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GoalImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.GOAL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setScore(BigDecimal newScore) {
        BigDecimal oldScore = score;
        score = newScore;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    DcasePackage.GOAL__SCORE, oldScore, score));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getWeight() {
        return weight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setWeight(int newWeight) {
        int oldWeight = weight;
        weight = newWeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    DcasePackage.GOAL__WEIGHT, oldWeight, weight));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.GOAL__SCORE:
                return getScore();
            case DcasePackage.GOAL__WEIGHT:
                return getWeight();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DcasePackage.GOAL__SCORE:
                setScore((BigDecimal) newValue);
                return;
            case DcasePackage.GOAL__WEIGHT:
                setWeight((Integer) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case DcasePackage.GOAL__SCORE:
                setScore(SCORE_EDEFAULT);
                return;
            case DcasePackage.GOAL__WEIGHT:
                setWeight(WEIGHT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DcasePackage.GOAL__SCORE:
                return SCORE_EDEFAULT == null ? score != null : !SCORE_EDEFAULT
                        .equals(score);
            case DcasePackage.GOAL__WEIGHT:
                return weight != WEIGHT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (score: ");
        result.append(score);
        result.append(", weight: ");
        result.append(weight);
        result.append(')');
        return result.toString();
    }
    
} // GoalImpl
