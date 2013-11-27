/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcasePackage;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Basic Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getAttachment <em>Attachment</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getFlag <em>Flag</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRespName <em>RespName</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRespAddress <em>RespAddress</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRespIcon <em>RespIcon</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRespTime <em>RespTime</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRequirement <em>Requirement</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getRefSource <em>RefSource</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getParameterDefs <em>ParameterDefs</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getParameterVals <em>ParameterVals</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getParameterizedDesc <em>ParameterizedDesc</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef001 <em>Userdef001</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef002 <em>Userdef002</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef003 <em>Userdef003</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef004 <em>Userdef004</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef005 <em>Userdef005</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef006 <em>Userdef006</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef007 <em>Userdef007</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef008 <em>Userdef008</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef009 <em>Userdef009</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef010 <em>Userdef010</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef011 <em>Userdef011</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef012 <em>Userdef012</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef013 <em>Userdef013</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef014 <em>Userdef014</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef015 <em>Userdef015</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicNodeImpl#getUserdef016 <em>Userdef016</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BasicNodeImpl extends EObjectImpl implements BasicNode {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDesc() <em>Desc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDesc()
     * @generated
     * @ordered
     */
    protected static final String DESC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDesc() <em>Desc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDesc()
     * @generated
     * @ordered
     */
    protected String desc = DESC_EDEFAULT;

    /**
     * The default value of the '{@link #getAttachment() <em>Attachment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttachment()
     * @generated
     * @ordered
     */
    protected static final String ATTACHMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAttachment() <em>Attachment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttachment()
     * @generated
     * @ordered
     */
    protected String attachment = ATTACHMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    protected static final String STATUS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    protected String status = STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getFlag() <em>Flag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlag()
     * @ordered
     */
    protected static final String FLAG_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFlag() <em>Flag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlag()
     * @ordered
     */
    protected String flag = FLAG_EDEFAULT;

    /**
     * The default value of the '{@link #getRespName() <em>RespName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespName()
     * @ordered
     */
    protected static final String RESPNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRespName() <em>RespName</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespName()
     * @ordered
     */
    protected String respName = RESPNAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRespAddress() <em>RespAddress</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespAddress()
     * @ordered
     */
    protected static final String RESPADDRESS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRespAddress() <em>RespAddress</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespAddress()
     * @ordered
     */
    protected String respAddress = RESPADDRESS_EDEFAULT;

    /**
     * The default value of the '{@link #getRespIcon() <em>RespIcon</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespIcon()
     * @ordered
     */
    protected static final String RESPICON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRespIcon() <em>RespIcon</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespIcon()
     * @ordered
     */
    protected String respIcon = RESPICON_EDEFAULT;

    /**
     * The default value of the '{@link #getRespTime() <em>RespTime</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespTime()
     * @ordered
     */
    protected static final String RESPTIME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRespTime() <em>RespTime</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespTime()
     * @ordered
     */
    protected String respTime = RESPTIME_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessage()
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessage()
     * @ordered
     */
    protected String message = MESSAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getRequirement() <em>Requirement</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequirement()
     * @ordered
     */
    protected static final String REQUIREMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRequirement() <em>Requirement</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequirement()
     * @ordered
     */
    protected String requirement = REQUIREMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getParent() <em>Parent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @ordered
     */
    protected static final String PARENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @ordered
     */
    protected String parent = PARENT_EDEFAULT;

    /**
     * The default value of the '{@link #getRefSource() <em>RefSource</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefSource()
     * @ordered
     */
    protected static final String REFSOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefSource() <em>RefSource</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefSource()
     * @ordered
     */
    protected String refSource = REFSOURCE_EDEFAULT;

    /**
     * The default value of the '{@link #getParameterDefs() <em>ParameterDefs</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterDefs()
     * @ordered
     */
    protected static final String PARAMETERDEFS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParameterDefs() <em>ParameterDefs</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterDefs()
     * @ordered
     */
    protected String parameterDefs = PARAMETERDEFS_EDEFAULT;

    /**
     * The default value of the '{@link #getParameterVals() <em>ParameterVals</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterVals()
     * @ordered
     */
    protected static final String PARAMETERVALS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParameterVals() <em>ParameterVals</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterVals()
     * @ordered
     */
    protected String parameterVals = PARAMETERVALS_EDEFAULT;

    /**
     * The default value of the '{@link #getParameterizedDesc() <em>ParameterizedDesc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterizedDesc()
     * @ordered
     */
    protected static final String PARAMETERIZEDDESC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParameterizedDesc() <em>ParameterizedDesc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterizedDesc()
     * @ordered
     */
    protected String parameterizedDesc = PARAMETERIZEDDESC_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef001() <em>Userdef001</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef001()
     * @generated
     * @ordered
     */
    protected static final String USERDEF001_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef001() <em>Userdef001</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef001()
     * @generated
     * @ordered
     */
    protected String userdef001 = USERDEF001_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef002() <em>Userdef002</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef002()
     * @generated
     * @ordered
     */
    protected static final String USERDEF002_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef002() <em>Userdef002</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef002()
     * @generated
     * @ordered
     */
    protected String userdef002 = USERDEF002_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef003() <em>Userdef003</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef003()
     * @generated
     * @ordered
     */
    protected static final String USERDEF003_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef003() <em>Userdef003</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef003()
     * @generated
     * @ordered
     */
    protected String userdef003 = USERDEF003_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef004() <em>Userdef004</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef004()
     * @generated
     * @ordered
     */
    protected static final String USERDEF004_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef004() <em>Userdef004</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef004()
     * @generated
     * @ordered
     */
    protected String userdef004 = USERDEF004_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef005() <em>Userdef005</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef005()
     * @generated
     * @ordered
     */
    protected static final String USERDEF005_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef005() <em>Userdef005</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef005()
     * @generated
     * @ordered
     */
    protected String userdef005 = USERDEF005_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef006() <em>Userdef006</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef006()
     * @generated
     * @ordered
     */
    protected static final String USERDEF006_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef006() <em>Userdef006</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef006()
     * @generated
     * @ordered
     */
    protected String userdef006 = USERDEF006_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef007() <em>Userdef007</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef007()
     * @generated
     * @ordered
     */
    protected static final String USERDEF007_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef007() <em>Userdef007</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef007()
     * @generated
     * @ordered
     */
    protected String userdef007 = USERDEF007_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef008() <em>Userdef008</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef008()
     * @generated
     * @ordered
     */
    protected static final String USERDEF008_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef008() <em>Userdef008</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef008()
     * @generated
     * @ordered
     */
    protected String userdef008 = USERDEF008_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef009() <em>Userdef009</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef009()
     * @generated
     * @ordered
     */
    protected static final String USERDEF009_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef009() <em>Userdef009</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef009()
     * @generated
     * @ordered
     */
    protected String userdef009 = USERDEF009_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef010() <em>Userdef010</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef010()
     * @generated
     * @ordered
     */
    protected static final String USERDEF010_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef010() <em>Userdef010</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef010()
     * @generated
     * @ordered
     */
    protected String userdef010 = USERDEF010_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef011() <em>Userdef011</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef011()
     * @generated
     * @ordered
     */
    protected static final String USERDEF011_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef011() <em>Userdef011</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef011()
     * @generated
     * @ordered
     */
    protected String userdef011 = USERDEF011_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef012() <em>Userdef012</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef012()
     * @generated
     * @ordered
     */
    protected static final String USERDEF012_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef012() <em>Userdef012</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef012()
     * @generated
     * @ordered
     */
    protected String userdef012 = USERDEF012_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef013() <em>Userdef013</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef013()
     * @generated
     * @ordered
     */
    protected static final String USERDEF013_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef013() <em>Userdef013</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef013()
     * @generated
     * @ordered
     */
    protected String userdef013 = USERDEF013_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef014() <em>Userdef014</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef014()
     * @generated
     * @ordered
     */
    protected static final String USERDEF014_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef014() <em>Userdef014</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef014()
     * @generated
     * @ordered
     */
    protected String userdef014 = USERDEF014_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef015() <em>Userdef015</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef015()
     * @generated
     * @ordered
     */
    protected static final String USERDEF015_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef015() <em>Userdef015</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef015()
     * @generated
     * @ordered
     */
    protected String userdef015 = USERDEF015_EDEFAULT;

    /**
     * The default value of the '{@link #getUserdef016() <em>Userdef016</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef016()
     * @generated
     * @ordered
     */
    protected static final String USERDEF016_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserdef016() <em>Userdef016</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserdef016()
     * @generated
     * @ordered
     */
    protected String userdef016 = USERDEF016_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BasicNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.BASIC_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDesc(String newDesc) {
        String oldDesc = desc;
        desc = newDesc;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__DESC, oldDesc, desc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttachment(String newAttachment) {
        String oldAttachment = attachment;
        attachment = newAttachment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__ATTACHMENT, oldAttachment, attachment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStatus(String newStatus) {
        String oldStatus = status;
        status = newStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__STATUS, oldStatus, status));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getFlag() {
        return flag;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setFlag(String newFlag) {
        String oldFlag = flag;
        flag = newFlag;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__FLAG, oldFlag, flag));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRespName() {
        return respName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRespName(String newValue) {
        String oldValue = respName;
        respName = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__RESPNAME, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRespAddress() {
        return respAddress;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRespAddress(String newValue) {
        String oldValue = respAddress;
        respAddress = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__RESPADDRESS, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRespIcon() {
        return respIcon;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRespIcon(String newValue) {
        String oldValue = respIcon;
        respIcon = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__RESPICON, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRespTime() {
        return respTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRespTime(String newValue) {
        String oldValue = respTime;
        respTime = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__RESPTIME, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setMessage(String newValue) {
        String oldValue = message;
        message = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__MESSAGE, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRequirement(String newValue) {
        String oldValue = requirement;
        requirement = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__REQUIREMENT, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setParent(String newValue) {
        String oldValue = parent;
        parent = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__PARENT, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getRefSource() {
        return refSource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setRefSource(String newValue) {
        String oldValue = refSource;
        refSource = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__REFSOURCE, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getParameterDefs() {
        return parameterDefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setParameterDefs(String newValue) {
        String oldValue = parameterDefs;
        parameterDefs = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__PARAMETERDEFS, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getParameterVals() {
        return parameterVals;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setParameterVals(String newValue) {
        String oldValue = parameterVals;
        parameterVals = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__PARAMETERVALS, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getParameterizedDesc() {
        return parameterizedDesc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setParameterizedDesc(String newValue) {
        String oldValue = parameterizedDesc;
        parameterizedDesc = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__PARAMETERIZEDDESC, oldValue, newValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef001() {
        return userdef001;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef001(String newUserdef001) {
        String oldUserdef001 = userdef001;
        userdef001 = newUserdef001;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF001, oldUserdef001, userdef001));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef002() {
        return userdef002;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef002(String newUserdef002) {
        String oldUserdef002 = userdef002;
        userdef002 = newUserdef002;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF002, oldUserdef002, userdef002));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef003() {
        return userdef003;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef003(String newUserdef003) {
        String oldUserdef003 = userdef003;
        userdef003 = newUserdef003;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF003, oldUserdef003, userdef003));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef004() {
        return userdef004;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef004(String newUserdef004) {
        String oldUserdef004 = userdef004;
        userdef004 = newUserdef004;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF004, oldUserdef004, userdef004));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef005() {
        return userdef005;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef005(String newUserdef005) {
        String oldUserdef005 = userdef005;
        userdef005 = newUserdef005;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF005, oldUserdef005, userdef005));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef006() {
        return userdef006;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef006(String newUserdef006) {
        String oldUserdef006 = userdef006;
        userdef006 = newUserdef006;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF006, oldUserdef006, userdef006));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef007() {
        return userdef007;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef007(String newUserdef007) {
        String oldUserdef007 = userdef007;
        userdef007 = newUserdef007;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF007, oldUserdef007, userdef007));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef008() {
        return userdef008;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef008(String newUserdef008) {
        String oldUserdef008 = userdef008;
        userdef008 = newUserdef008;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF008, oldUserdef008, userdef008));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef009() {
        return userdef009;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef009(String newUserdef009) {
        String oldUserdef009 = userdef009;
        userdef009 = newUserdef009;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF009, oldUserdef009, userdef009));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef010() {
        return userdef010;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef010(String newUserdef010) {
        String oldUserdef010 = userdef010;
        userdef010 = newUserdef010;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF010, oldUserdef010, userdef010));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef011() {
        return userdef011;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef011(String newUserdef011) {
        String oldUserdef011 = userdef011;
        userdef011 = newUserdef011;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF011, oldUserdef011, userdef011));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef012() {
        return userdef012;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef012(String newUserdef012) {
        String oldUserdef012 = userdef012;
        userdef012 = newUserdef012;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF012, oldUserdef012, userdef012));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef013() {
        return userdef013;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef013(String newUserdef013) {
        String oldUserdef013 = userdef013;
        userdef013 = newUserdef013;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF013, oldUserdef013, userdef013));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef014() {
        return userdef014;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef014(String newUserdef014) {
        String oldUserdef014 = userdef014;
        userdef014 = newUserdef014;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF014, oldUserdef014, userdef014));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef015() {
        return userdef015;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef015(String newUserdef015) {
        String oldUserdef015 = userdef015;
        userdef015 = newUserdef015;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF015, oldUserdef015, userdef015));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUserdef016() {
        return userdef016;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserdef016(String newUserdef016) {
        String oldUserdef016 = userdef016;
        userdef016 = newUserdef016;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_NODE__USERDEF016, oldUserdef016, userdef016));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.BASIC_NODE__NAME:
                return getName();
            case DcasePackage.BASIC_NODE__DESC:
                return getDesc();
            case DcasePackage.BASIC_NODE__ATTACHMENT:
                return getAttachment();
            case DcasePackage.BASIC_NODE__STATUS:
                return getStatus();
            case DcasePackage.BASIC_NODE__FLAG:
                return getFlag();
            case DcasePackage.BASIC_NODE__RESPNAME:
                return getRespName();
            case DcasePackage.BASIC_NODE__RESPADDRESS:
                return getRespAddress();
            case DcasePackage.BASIC_NODE__RESPICON:
                return getRespIcon();
            case DcasePackage.BASIC_NODE__RESPTIME:
                return getRespTime();
            case DcasePackage.BASIC_NODE__MESSAGE:
                return getMessage();
            case DcasePackage.BASIC_NODE__REQUIREMENT:
                return getRequirement();
            case DcasePackage.BASIC_NODE__PARENT:
                return getParent();
            case DcasePackage.BASIC_NODE__REFSOURCE:
                return getRefSource();
            case DcasePackage.BASIC_NODE__PARAMETERDEFS:
                return getParameterDefs();
            case DcasePackage.BASIC_NODE__PARAMETERVALS:
                return getParameterVals();
            case DcasePackage.BASIC_NODE__PARAMETERIZEDDESC:
                return getParameterizedDesc();
            case DcasePackage.BASIC_NODE__USERDEF001:
                return getUserdef001();
            case DcasePackage.BASIC_NODE__USERDEF002:
                return getUserdef002();
            case DcasePackage.BASIC_NODE__USERDEF003:
                return getUserdef003();
            case DcasePackage.BASIC_NODE__USERDEF004:
                return getUserdef004();
            case DcasePackage.BASIC_NODE__USERDEF005:
                return getUserdef005();
            case DcasePackage.BASIC_NODE__USERDEF006:
                return getUserdef006();
            case DcasePackage.BASIC_NODE__USERDEF007:
                return getUserdef007();
            case DcasePackage.BASIC_NODE__USERDEF008:
                return getUserdef008();
            case DcasePackage.BASIC_NODE__USERDEF009:
                return getUserdef009();
            case DcasePackage.BASIC_NODE__USERDEF010:
                return getUserdef010();
            case DcasePackage.BASIC_NODE__USERDEF011:
                return getUserdef011();
            case DcasePackage.BASIC_NODE__USERDEF012:
                return getUserdef012();
            case DcasePackage.BASIC_NODE__USERDEF013:
                return getUserdef013();
            case DcasePackage.BASIC_NODE__USERDEF014:
                return getUserdef014();
            case DcasePackage.BASIC_NODE__USERDEF015:
                return getUserdef015();
            case DcasePackage.BASIC_NODE__USERDEF016:
                return getUserdef016();
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
            case DcasePackage.BASIC_NODE__NAME:
                setName((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__DESC:
                setDesc((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__ATTACHMENT:
                setAttachment((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__STATUS:
                setStatus((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__FLAG:
                setFlag((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__RESPNAME:
                setRespName((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__RESPADDRESS:
                setRespAddress((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__RESPICON:
                setRespIcon((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__RESPTIME:
                setRespTime((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__MESSAGE:
                setMessage((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__REQUIREMENT:
                setRequirement((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__PARENT:
                setParent((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__REFSOURCE:
                setRefSource((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERDEFS:
                setParameterDefs((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERVALS:
                setParameterVals((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERIZEDDESC:
                setParameterizedDesc((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF001:
                setUserdef001((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF002:
                setUserdef002((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF003:
                setUserdef003((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF004:
                setUserdef004((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF005:
                setUserdef005((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF006:
                setUserdef006((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF007:
                setUserdef007((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF008:
                setUserdef008((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF009:
                setUserdef009((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF010:
                setUserdef010((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF011:
                setUserdef011((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF012:
                setUserdef012((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF013:
                setUserdef013((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF014:
                setUserdef014((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF015:
                setUserdef015((String)newValue);
                return;
            case DcasePackage.BASIC_NODE__USERDEF016:
                setUserdef016((String)newValue);
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
            case DcasePackage.BASIC_NODE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__DESC:
                setDesc(DESC_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__ATTACHMENT:
                setAttachment(ATTACHMENT_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__STATUS:
                setStatus(STATUS_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__FLAG:
                setFlag(FLAG_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__RESPNAME:
                setRespName(RESPNAME_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__RESPADDRESS:
                setRespAddress(RESPADDRESS_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__RESPICON:
                setRespIcon(RESPICON_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__RESPTIME:
                setRespTime(RESPTIME_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__MESSAGE:
                setMessage(MESSAGE_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__REQUIREMENT:
                setRequirement(REQUIREMENT_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__PARENT:
                setParent(PARENT_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__REFSOURCE:
                setRefSource(REFSOURCE_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERDEFS:
                setParameterDefs(PARAMETERDEFS_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERVALS:
                setParameterVals(PARAMETERVALS_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__PARAMETERIZEDDESC:
                setParameterVals(PARAMETERIZEDDESC_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF001:
                setUserdef001(USERDEF001_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF002:
                setUserdef002(USERDEF002_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF003:
                setUserdef003(USERDEF003_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF004:
                setUserdef004(USERDEF004_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF005:
                setUserdef005(USERDEF005_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF006:
                setUserdef006(USERDEF006_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF007:
                setUserdef007(USERDEF007_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF008:
                setUserdef008(USERDEF008_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF009:
                setUserdef009(USERDEF009_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF010:
                setUserdef010(USERDEF010_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF011:
                setUserdef011(USERDEF011_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF012:
                setUserdef012(USERDEF012_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF013:
                setUserdef013(USERDEF013_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF014:
                setUserdef014(USERDEF014_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF015:
                setUserdef015(USERDEF015_EDEFAULT);
                return;
            case DcasePackage.BASIC_NODE__USERDEF016:
                setUserdef016(USERDEF016_EDEFAULT);
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
            case DcasePackage.BASIC_NODE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DcasePackage.BASIC_NODE__DESC:
                return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
            case DcasePackage.BASIC_NODE__ATTACHMENT:
                return ATTACHMENT_EDEFAULT == null ? attachment != null : !ATTACHMENT_EDEFAULT.equals(attachment);
            case DcasePackage.BASIC_NODE__STATUS:
                return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
            case DcasePackage.BASIC_NODE__FLAG:
                return FLAG_EDEFAULT == null ? flag != null : !FLAG_EDEFAULT.equals(flag);
            case DcasePackage.BASIC_NODE__RESPNAME:
                return RESPNAME_EDEFAULT == null ? respName != null : !RESPNAME_EDEFAULT.equals(respName);
            case DcasePackage.BASIC_NODE__RESPADDRESS:
                return RESPADDRESS_EDEFAULT == null ? respAddress != null : !RESPADDRESS_EDEFAULT.equals(respAddress);
            case DcasePackage.BASIC_NODE__RESPICON:
                return RESPICON_EDEFAULT == null ? respIcon != null : !RESPICON_EDEFAULT.equals(respIcon);
            case DcasePackage.BASIC_NODE__RESPTIME:
                return RESPTIME_EDEFAULT == null ? respTime != null : !RESPTIME_EDEFAULT.equals(respTime);
            case DcasePackage.BASIC_NODE__MESSAGE:
                return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
            case DcasePackage.BASIC_NODE__REQUIREMENT:
                return REQUIREMENT_EDEFAULT == null ? requirement != null : !REQUIREMENT_EDEFAULT.equals(requirement);
            case DcasePackage.BASIC_NODE__PARENT:
                return PARENT_EDEFAULT == null ? parent != null : !PARENT_EDEFAULT.equals(parent);
            case DcasePackage.BASIC_NODE__REFSOURCE:
                return REFSOURCE_EDEFAULT == null ? refSource != null : !REFSOURCE_EDEFAULT.equals(refSource);
            case DcasePackage.BASIC_NODE__PARAMETERDEFS:
                return PARAMETERDEFS_EDEFAULT == null ? parameterDefs != null : !PARAMETERDEFS_EDEFAULT.equals(parameterDefs);
            case DcasePackage.BASIC_NODE__PARAMETERVALS:
                return PARAMETERVALS_EDEFAULT == null ? parameterVals != null : !PARAMETERVALS_EDEFAULT.equals(parameterVals);
            case DcasePackage.BASIC_NODE__PARAMETERIZEDDESC:
                return PARAMETERIZEDDESC_EDEFAULT == null ? parameterizedDesc != null : !PARAMETERIZEDDESC_EDEFAULT.equals(parameterizedDesc);
            case DcasePackage.BASIC_NODE__USERDEF001:
                return USERDEF001_EDEFAULT == null ? userdef001 != null : !USERDEF001_EDEFAULT.equals(userdef001);
            case DcasePackage.BASIC_NODE__USERDEF002:
                return USERDEF002_EDEFAULT == null ? userdef002 != null : !USERDEF002_EDEFAULT.equals(userdef002);
            case DcasePackage.BASIC_NODE__USERDEF003:
                return USERDEF003_EDEFAULT == null ? userdef003 != null : !USERDEF003_EDEFAULT.equals(userdef003);
            case DcasePackage.BASIC_NODE__USERDEF004:
                return USERDEF004_EDEFAULT == null ? userdef004 != null : !USERDEF004_EDEFAULT.equals(userdef004);
            case DcasePackage.BASIC_NODE__USERDEF005:
                return USERDEF005_EDEFAULT == null ? userdef005 != null : !USERDEF005_EDEFAULT.equals(userdef005);
            case DcasePackage.BASIC_NODE__USERDEF006:
                return USERDEF006_EDEFAULT == null ? userdef006 != null : !USERDEF006_EDEFAULT.equals(userdef006);
            case DcasePackage.BASIC_NODE__USERDEF007:
                return USERDEF007_EDEFAULT == null ? userdef007 != null : !USERDEF007_EDEFAULT.equals(userdef007);
            case DcasePackage.BASIC_NODE__USERDEF008:
                return USERDEF008_EDEFAULT == null ? userdef008 != null : !USERDEF008_EDEFAULT.equals(userdef008);
            case DcasePackage.BASIC_NODE__USERDEF009:
                return USERDEF009_EDEFAULT == null ? userdef009 != null : !USERDEF009_EDEFAULT.equals(userdef009);
            case DcasePackage.BASIC_NODE__USERDEF010:
                return USERDEF010_EDEFAULT == null ? userdef010 != null : !USERDEF010_EDEFAULT.equals(userdef010);
            case DcasePackage.BASIC_NODE__USERDEF011:
                return USERDEF011_EDEFAULT == null ? userdef011 != null : !USERDEF011_EDEFAULT.equals(userdef011);
            case DcasePackage.BASIC_NODE__USERDEF012:
                return USERDEF012_EDEFAULT == null ? userdef012 != null : !USERDEF012_EDEFAULT.equals(userdef012);
            case DcasePackage.BASIC_NODE__USERDEF013:
                return USERDEF013_EDEFAULT == null ? userdef013 != null : !USERDEF013_EDEFAULT.equals(userdef013);
            case DcasePackage.BASIC_NODE__USERDEF014:
                return USERDEF014_EDEFAULT == null ? userdef014 != null : !USERDEF014_EDEFAULT.equals(userdef014);
            case DcasePackage.BASIC_NODE__USERDEF015:
                return USERDEF015_EDEFAULT == null ? userdef015 != null : !USERDEF015_EDEFAULT.equals(userdef015);
            case DcasePackage.BASIC_NODE__USERDEF016:
                return USERDEF016_EDEFAULT == null ? userdef016 != null : !USERDEF016_EDEFAULT.equals(userdef016);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", desc: ");
        result.append(desc);
        result.append(", parameterizedDesc: ");
        result.append(parameterizedDesc);
        result.append(", attachment: ");
        result.append(attachment);
        result.append(", status: ");
        result.append(status);
        result.append(", message: ");
        result.append(message);
        result.append(", requirement: ");
        result.append(requirement);
        result.append(", parent: ");
        result.append(parent);
        result.append(", refSource: ");
        result.append(refSource);
        result.append(", flag: ");
        result.append(flag);
        result.append(", parameterDefs: ");
        result.append(parameterDefs);
        result.append(", parameterVals: ");
        result.append(parameterVals);
        result.append(", respName: ");
        result.append(respName);
        result.append(", respAddress: ");
        result.append(respAddress);
        result.append(", respIcon: ");
        result.append(respIcon);
        result.append(", respTime: ");
        result.append(respTime);
        result.append(", userdef001: ");
        result.append(userdef001);
        result.append(", userdef002: ");
        result.append(userdef002);
        result.append(", userdef003: ");
        result.append(userdef003);
        result.append(", userdef004: ");
        result.append(userdef004);
        result.append(", userdef005: ");
        result.append(userdef005);
        result.append(", userdef006: ");
        result.append(userdef006);
        result.append(", userdef007: ");
        result.append(userdef007);
        result.append(", userdef008: ");
        result.append(userdef008);
        result.append(", userdef009: ");
        result.append(userdef009);
        result.append(", userdef010: ");
        result.append(userdef010);
        result.append(", userdef011: ");
        result.append(userdef011);
        result.append(", userdef012: ");
        result.append(userdef012);
        result.append(", userdef013: ");
        result.append(userdef013);
        result.append(", userdef014: ");
        result.append(userdef014);
        result.append(", userdef015: ");
        result.append(userdef015);
        result.append(", userdef016: ");
        result.append(userdef016);
        result.append(')');
        return result.toString();
    }

    /**
     * Returns the type name.
     * 
     * @return the type name.
     */
    public String getTypeName() {
        String typeName = this.getClass().getSimpleName();
        return "_UI_" + typeName.substring(0, typeName.length() - 4) + "_type"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    /**
     * {@inheritDoc}
     */
    public String getParameters(){
    	String ret = getParentParameters();
		if(parameterVals != null && parameterVals.length() > 0) {
			if(ret != null && ret.length() > 0) {
    			ret = parameterVals + ParameterItem.SEPARATOR + ret;
    		} else {
    			ret = parameterVals;
    		}
    	}
    	return ret;
    }
    
    /**
     * Returns the hole parameter string.
     * @return the parameter string.
     */
    public String getParentParameters() {
    	String ret = getParentParameters(null, new ArrayList<BasicNodeImpl>());
    	Argument argument = (Argument)eContainer();
    	String argumentParameter = argument.getParameterVals();
    	if(argumentParameter != null && argumentParameter.length() > 0) {
    		if(ret != null && ret.length() > 0) {
    			return ret + ParameterItem.SEPARATOR + argumentParameter;
    		} else {
    			return argumentParameter;
    		}
    	}
    	return ret;
    }
    
    /**
     * Returns the hole parameter string.
     * @param parameter the current parameter string.
     * @param checkedNodeList the list of checked nodes.
     * @return the parameter string.
     */
    public String getParentParameters(String parameter, ArrayList<BasicNodeImpl> checkedNodeList) {
    	if(checkedNodeList.indexOf(this) >= 0) {
    		return parameter;
    	}
    	checkedNodeList.add(this);
    	Argument argument = (Argument)eContainer();
    	for(BasicLink link : argument.getRootBasicLink()) {
    		if(link.getTarget() == this) {
    			BasicNodeImpl parentNode = (BasicNodeImpl)link.getSource();
    			String parentParameter = parentNode.getParameterVals();
    			if(parentParameter != null && parentParameter.length() > 0) {
    				if(parameter != null && parameter.length() > 0) {
    					parameter = parameter + ParameterItem.SEPARATOR + parentParameter;
    				} else {
    					parameter = parentParameter;
    				}
    			}
    			parameter = parentNode.getParentParameters(parameter, checkedNodeList);
    		}
    	}
    	return parameter;
    }
     
} //BasicNodeImpl
