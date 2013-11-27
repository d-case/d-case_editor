/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Basic Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getDesc <em>Desc</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getAttachment <em>Attachment</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getSiblingOrder <em>SiblingOrder</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef001 <em>Userdef001</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef002 <em>Userdef002</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef003 <em>Userdef003</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef004 <em>Userdef004</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef005 <em>Userdef005</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef006 <em>Userdef006</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef007 <em>Userdef007</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef008 <em>Userdef008</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef009 <em>Userdef009</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef010 <em>Userdef010</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef011 <em>Userdef011</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef012 <em>Userdef012</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef013 <em>Userdef013</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef014 <em>Userdef014</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef015 <em>Userdef015</em>}</li>
 *   <li>{@link net.dependableos.dcase.impl.BasicLinkImpl#getUserdef016 <em>Userdef016</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BasicLinkImpl extends EObjectImpl implements BasicLink {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected BasicNode source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected BasicNode target;

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
     * The default value of the '{@link #getSiblingOrder() <em>SiblingOrder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSiblingOrder()
     * @ordered
     */
    protected static final String SIBLINGORDER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSiblingOrder() <em>SiblingOrder</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSliblingOrder()
     * @ordered
     */
    protected String siblingOrder = SIBLINGORDER_EDEFAULT;

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
    protected BasicLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DcasePackage.Literals.BASIC_LINK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BasicNode getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject)source;
            source = (BasicNode)eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DcasePackage.BASIC_LINK__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BasicNode basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(BasicNode newSource) {
        BasicNode oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BasicNode getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject)target;
            target = (BasicNode)eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DcasePackage.BASIC_LINK__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BasicNode basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(BasicNode newTarget) {
        BasicNode oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__TARGET, oldTarget, target));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__DESC, oldDesc, desc));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__ATTACHMENT, oldAttachment, attachment));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__STATUS, oldStatus, status));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getSiblingOrder() {
        return siblingOrder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setSiblingOrder(String newSiblingOrder) {
        String oldSiblingOrder = siblingOrder;
        siblingOrder = newSiblingOrder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__SIBLINGORDER, oldSiblingOrder, siblingOrder));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__MESSAGE, oldValue, newValue));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF001, oldUserdef001, userdef001));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF002, oldUserdef002, userdef002));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF003, oldUserdef003, userdef003));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF004, oldUserdef004, userdef004));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF005, oldUserdef005, userdef005));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF006, oldUserdef006, userdef006));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF007, oldUserdef007, userdef007));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF008, oldUserdef008, userdef008));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF009, oldUserdef009, userdef009));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF010, oldUserdef010, userdef010));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF011, oldUserdef011, userdef011));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF012, oldUserdef012, userdef012));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF013, oldUserdef013, userdef013));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF014, oldUserdef014, userdef014));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF015, oldUserdef015, userdef015));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DcasePackage.BASIC_LINK__USERDEF016, oldUserdef016, userdef016));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DcasePackage.BASIC_LINK__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case DcasePackage.BASIC_LINK__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
            case DcasePackage.BASIC_LINK__NAME:
                return getName();
            case DcasePackage.BASIC_LINK__DESC:
                return getDesc();
            case DcasePackage.BASIC_LINK__ATTACHMENT:
                return getAttachment();
            case DcasePackage.BASIC_LINK__STATUS:
                return getStatus();
            case DcasePackage.BASIC_LINK__SIBLINGORDER:
                return getSiblingOrder();
            case DcasePackage.BASIC_LINK__MESSAGE:
                return getMessage();
            case DcasePackage.BASIC_LINK__USERDEF001:
                return getUserdef001();
            case DcasePackage.BASIC_LINK__USERDEF002:
                return getUserdef002();
            case DcasePackage.BASIC_LINK__USERDEF003:
                return getUserdef003();
            case DcasePackage.BASIC_LINK__USERDEF004:
                return getUserdef004();
            case DcasePackage.BASIC_LINK__USERDEF005:
                return getUserdef005();
            case DcasePackage.BASIC_LINK__USERDEF006:
                return getUserdef006();
            case DcasePackage.BASIC_LINK__USERDEF007:
                return getUserdef007();
            case DcasePackage.BASIC_LINK__USERDEF008:
                return getUserdef008();
            case DcasePackage.BASIC_LINK__USERDEF009:
                return getUserdef009();
            case DcasePackage.BASIC_LINK__USERDEF010:
                return getUserdef010();
            case DcasePackage.BASIC_LINK__USERDEF011:
                return getUserdef011();
            case DcasePackage.BASIC_LINK__USERDEF012:
                return getUserdef012();
            case DcasePackage.BASIC_LINK__USERDEF013:
                return getUserdef013();
            case DcasePackage.BASIC_LINK__USERDEF014:
                return getUserdef014();
            case DcasePackage.BASIC_LINK__USERDEF015:
                return getUserdef015();
            case DcasePackage.BASIC_LINK__USERDEF016:
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
            case DcasePackage.BASIC_LINK__SOURCE:
                setSource((BasicNode)newValue);
                return;
            case DcasePackage.BASIC_LINK__TARGET:
                setTarget((BasicNode)newValue);
                return;
            case DcasePackage.BASIC_LINK__NAME:
                setName((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__DESC:
                setDesc((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__ATTACHMENT:
                setAttachment((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__STATUS:
                setStatus((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__SIBLINGORDER:
                setSiblingOrder((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__MESSAGE:
                setMessage((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF001:
                setUserdef001((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF002:
                setUserdef002((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF003:
                setUserdef003((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF004:
                setUserdef004((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF005:
                setUserdef005((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF006:
                setUserdef006((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF007:
                setUserdef007((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF008:
                setUserdef008((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF009:
                setUserdef009((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF010:
                setUserdef010((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF011:
                setUserdef011((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF012:
                setUserdef012((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF013:
                setUserdef013((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF014:
                setUserdef014((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF015:
                setUserdef015((String)newValue);
                return;
            case DcasePackage.BASIC_LINK__USERDEF016:
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
            case DcasePackage.BASIC_LINK__SOURCE:
                setSource((BasicNode)null);
                return;
            case DcasePackage.BASIC_LINK__TARGET:
                setTarget((BasicNode)null);
                return;
            case DcasePackage.BASIC_LINK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__DESC:
                setDesc(DESC_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__ATTACHMENT:
                setAttachment(ATTACHMENT_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__STATUS:
                setStatus(STATUS_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__SIBLINGORDER:
                setSiblingOrder(SIBLINGORDER_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__MESSAGE:
                setMessage(MESSAGE_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF001:
                setUserdef001(USERDEF001_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF002:
                setUserdef002(USERDEF002_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF003:
                setUserdef003(USERDEF003_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF004:
                setUserdef004(USERDEF004_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF005:
                setUserdef005(USERDEF005_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF006:
                setUserdef006(USERDEF006_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF007:
                setUserdef007(USERDEF007_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF008:
                setUserdef008(USERDEF008_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF009:
                setUserdef009(USERDEF009_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF010:
                setUserdef010(USERDEF010_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF011:
                setUserdef011(USERDEF011_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF012:
                setUserdef012(USERDEF012_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF013:
                setUserdef013(USERDEF013_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF014:
                setUserdef014(USERDEF014_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF015:
                setUserdef015(USERDEF015_EDEFAULT);
                return;
            case DcasePackage.BASIC_LINK__USERDEF016:
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
            case DcasePackage.BASIC_LINK__SOURCE:
                return source != null;
            case DcasePackage.BASIC_LINK__TARGET:
                return target != null;
            case DcasePackage.BASIC_LINK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DcasePackage.BASIC_LINK__DESC:
                return DESC_EDEFAULT == null ? desc != null : !DESC_EDEFAULT.equals(desc);
            case DcasePackage.BASIC_LINK__ATTACHMENT:
                return ATTACHMENT_EDEFAULT == null ? attachment != null : !ATTACHMENT_EDEFAULT.equals(attachment);
            case DcasePackage.BASIC_LINK__STATUS:
                return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
            case DcasePackage.BASIC_LINK__SIBLINGORDER:
                return SIBLINGORDER_EDEFAULT == null ? siblingOrder != null : !SIBLINGORDER_EDEFAULT.equals(siblingOrder);
            case DcasePackage.BASIC_LINK__MESSAGE:
                return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
            case DcasePackage.BASIC_LINK__USERDEF001:
                return USERDEF001_EDEFAULT == null ? userdef001 != null : !USERDEF001_EDEFAULT.equals(userdef001);
            case DcasePackage.BASIC_LINK__USERDEF002:
                return USERDEF002_EDEFAULT == null ? userdef002 != null : !USERDEF002_EDEFAULT.equals(userdef002);
            case DcasePackage.BASIC_LINK__USERDEF003:
                return USERDEF003_EDEFAULT == null ? userdef003 != null : !USERDEF003_EDEFAULT.equals(userdef003);
            case DcasePackage.BASIC_LINK__USERDEF004:
                return USERDEF004_EDEFAULT == null ? userdef004 != null : !USERDEF004_EDEFAULT.equals(userdef004);
            case DcasePackage.BASIC_LINK__USERDEF005:
                return USERDEF005_EDEFAULT == null ? userdef005 != null : !USERDEF005_EDEFAULT.equals(userdef005);
            case DcasePackage.BASIC_LINK__USERDEF006:
                return USERDEF006_EDEFAULT == null ? userdef006 != null : !USERDEF006_EDEFAULT.equals(userdef006);
            case DcasePackage.BASIC_LINK__USERDEF007:
                return USERDEF007_EDEFAULT == null ? userdef007 != null : !USERDEF007_EDEFAULT.equals(userdef007);
            case DcasePackage.BASIC_LINK__USERDEF008:
                return USERDEF008_EDEFAULT == null ? userdef008 != null : !USERDEF008_EDEFAULT.equals(userdef008);
            case DcasePackage.BASIC_LINK__USERDEF009:
                return USERDEF009_EDEFAULT == null ? userdef009 != null : !USERDEF009_EDEFAULT.equals(userdef009);
            case DcasePackage.BASIC_LINK__USERDEF010:
                return USERDEF010_EDEFAULT == null ? userdef010 != null : !USERDEF010_EDEFAULT.equals(userdef010);
            case DcasePackage.BASIC_LINK__USERDEF011:
                return USERDEF011_EDEFAULT == null ? userdef011 != null : !USERDEF011_EDEFAULT.equals(userdef011);
            case DcasePackage.BASIC_LINK__USERDEF012:
                return USERDEF012_EDEFAULT == null ? userdef012 != null : !USERDEF012_EDEFAULT.equals(userdef012);
            case DcasePackage.BASIC_LINK__USERDEF013:
                return USERDEF013_EDEFAULT == null ? userdef013 != null : !USERDEF013_EDEFAULT.equals(userdef013);
            case DcasePackage.BASIC_LINK__USERDEF014:
                return USERDEF014_EDEFAULT == null ? userdef014 != null : !USERDEF014_EDEFAULT.equals(userdef014);
            case DcasePackage.BASIC_LINK__USERDEF015:
                return USERDEF015_EDEFAULT == null ? userdef015 != null : !USERDEF015_EDEFAULT.equals(userdef015);
            case DcasePackage.BASIC_LINK__USERDEF016:
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
        result.append(", attachment: ");
        result.append(attachment);
        result.append(", status: ");
        result.append(status);
        result.append(", message: ");
        result.append(message);
        result.append(", siblingOrder: ");
        result.append(siblingOrder);
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
    
} //BasicLinkImpl
