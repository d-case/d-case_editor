/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getScore <em>Score</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getNodeLink <em>Node Link</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.SystemImpl#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemImpl extends BasicNodeImpl implements net.dependableos.dcase.System {
    /**
     * The default value of the '{@link #getScore() <em>Score</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScore()
     * @generated
     * @ordered
     */
    protected static final BigDecimal SCORE_EDEFAULT = new BigDecimal("0");

    /**
     * The cached value of the '{@link #getScore() <em>Score</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScore()
     * @generated
     * @ordered
     */
    protected BigDecimal score = SCORE_EDEFAULT;

    /**
     * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected static final int WEIGHT_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected int weight = WEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getNodeLink() <em>Node Link</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeLink()
     * @generated
     * @ordered
     */
    protected static final String NODE_LINK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNodeLink() <em>Node Link</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeLink()
     * @generated
     * @ordered
     */
    protected String nodeLink = NODE_LINK_EDEFAULT;

    /**
     * The default value of the '{@link #getNodeLink() <em>Node Link</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeLink()
     * @generated
     * @ordered
     */
    protected static final String PARAM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParam() <em>Param</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParam()
     * @ordered
     */
    protected String param = PARAM_EDEFAULT;
    
    /**
     * The delimiter for Userdef009.
     */
    public static final String PARAMDEF_DELIMITER = ";"; //$NON-NLS-1$

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
     * @generated
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScore(BigDecimal newScore) {
        BigDecimal oldScore = score;
        score = newScore;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__SCORE, oldScore, score));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getWeight() {
        return weight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWeight(int newWeight) {
        int oldWeight = weight;
        weight = newWeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__WEIGHT, oldWeight, weight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNodeLink() {
        return nodeLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeLink(String newNodeLink) {
        String oldNodeLink = nodeLink;
        nodeLink = newNodeLink;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__NODE_LINK, oldNodeLink, nodeLink));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getParam() {
    	// parse userdef007 and userdef009
    	StringBuffer paramBuffer = new StringBuffer();
    	List<ParameterItem> paramList = ParameterItem.getPatameterList(userdef007);
    	HashMap<String,String> typeMap = new HashMap<String,String>();
    	if (userdef009 != null && userdef009.length() > 0) {
    		String[] paramDefs = userdef009.split(PARAMDEF_DELIMITER);
    		// paramDefs[0] is the list of parameter names, so ignore.
    		for (int i=1; i<paramDefs.length; i++) {
    			String pname = null;
    			String ptype = null;
    			// trim after type...
    			int index1 = paramDefs[i].indexOf(ParameterItem.SEPARATOR);
    			int index2 = paramDefs[i].indexOf(ParameterItem.SEPARATOR, index1+1);
    			String paramDef = paramDefs[i].substring(0, (index2 < 0) ? paramDefs[i].length():index2);
    			for (ParameterItem item : ParameterItem.getPatameterList(paramDef)) {
    				if (item.getParameterId().equals("name")) { //$NON-NLS-1$
    					pname = item.getParameterValue();
    				} else if (item.getParameterId().equals("type")) { //$NON-NLS-1$
    					ptype = item.getParameterValue();
    				}
    				if (pname != null && ptype != null) {
    					typeMap.put(pname, ptype);
    					break;
    				}
    			}
    		}
    	}
    	// create param string.
    	boolean pfirst = true;
    	for (ParameterItem item : paramList) {
    		String pname = item.getParameterId();
    		String pvalue = item.getParameterValue();
    		String ptype = typeMap.get(pname);
    		if (ptype == null || ptype.length() == 0) {
    			ptype = "?"; //$NON-NLS-1$
    		}
    		if (! pfirst) {
    			paramBuffer.append(System.getProperty("line.separator")); //$NON-NLS-3$
    		} else {
    			pfirst = false;
    		}
    		paramBuffer.append(pname + ":" + ptype + "=" + pvalue); //$NON-NLS-1$ //$NON-NLS-2$
    	}
    	param = paramBuffer.toString();
        return param;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setParam(String dummy) {
        String oldParam = param;
    	param = getParam();
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__PARAM, oldParam, param));
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setUserdef007(String newStr) {
    	super.setUserdef007(newStr);
    	if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__PARAM, param, null));
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setUserdef009(String newStr) {
    	super.setUserdef009(newStr);
    	if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.SYSTEM__PARAM, param, null));
    }
    
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.SYSTEM__SCORE:
                return getScore();
            case DcasePackage.SYSTEM__WEIGHT:
                return getWeight();
            case DcasePackage.SYSTEM__NODE_LINK:
                return getNodeLink();
            case DcasePackage.SYSTEM__PARAM:
            	return getParam();
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
            case DcasePackage.SYSTEM__SCORE:
                setScore((BigDecimal)newValue);
                return;
            case DcasePackage.SYSTEM__WEIGHT:
                setWeight((Integer)newValue);
                return;
            case DcasePackage.SYSTEM__NODE_LINK:
                setNodeLink((String)newValue);
            case DcasePackage.SYSTEM__PARAM:
            	setParam((String)newValue);
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
            case DcasePackage.SYSTEM__SCORE:
                setScore(SCORE_EDEFAULT);
                return;
            case DcasePackage.SYSTEM__WEIGHT:
                setWeight(WEIGHT_EDEFAULT);
                return;
            case DcasePackage.SYSTEM__NODE_LINK:
                setNodeLink(NODE_LINK_EDEFAULT);
            case DcasePackage.SYSTEM__PARAM:
            	setParam(PARAM_EDEFAULT);
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
            case DcasePackage.SYSTEM__SCORE:
                return SCORE_EDEFAULT == null ? score != null : !SCORE_EDEFAULT.equals(score);
            case DcasePackage.SYSTEM__WEIGHT:
                return weight != WEIGHT_EDEFAULT;
            case DcasePackage.SYSTEM__NODE_LINK:
                return NODE_LINK_EDEFAULT == null ? nodeLink != null : !NODE_LINK_EDEFAULT.equals(nodeLink);
            case DcasePackage.SYSTEM__PARAM:
            	return PARAM_EDEFAULT == null ? param != null : !PARAM_EDEFAULT.equals(param);
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
        result.append(" (score: ");
        result.append(score);
        result.append(", weight: ");
        result.append(weight);
        result.append(')');
        return result.toString();
    }

} //SystemImpl
