/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Basic Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.dependableos.dcase.BasicLink#getSource <em>Source</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getTarget <em>Target</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getName <em>Name</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getDesc <em>Desc</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getAttachment <em>Attachment</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getStatus <em>Status</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef001 <em>Userdef001</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef002 <em>Userdef002</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef003 <em>Userdef003</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef004 <em>Userdef004</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef005 <em>Userdef005</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef006 <em>Userdef006</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef007 <em>Userdef007</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef008 <em>Userdef008</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef009 <em>Userdef009</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef010 <em>Userdef010</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef011 <em>Userdef011</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef012 <em>Userdef012</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef013 <em>Userdef013</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef014 <em>Userdef014</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef015 <em>Userdef015</em>}</li>
 *   <li>{@link net.dependableos.dcase.BasicLink#getUserdef016 <em>Userdef016</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.dependableos.dcase.DcasePackage#getBasicLink()
 * @model abstract="true"
 * @generated
 */
public interface BasicLink extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(BasicNode)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Source()
     * @model
     * @generated
     */
    BasicNode getSource();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(BasicNode value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(BasicNode)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Target()
     * @model
     * @generated
     */
    BasicNode getTarget();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(BasicNode value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Desc</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Desc</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Desc</em>' attribute.
     * @see #setDesc(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Desc()
     * @model
     * @generated
     */
    String getDesc();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getDesc <em>Desc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Desc</em>' attribute.
     * @see #getDesc()
     * @generated
     */
    void setDesc(String value);

    /**
     * Returns the value of the '<em><b>Attachment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attachment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attachment</em>' attribute.
     * @see #setAttachment(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Attachment()
     * @model
     * @generated
     */
    String getAttachment();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getAttachment <em>Attachment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attachment</em>' attribute.
     * @see #getAttachment()
     * @generated
     */
    void setAttachment(String value);

    /**
     * Returns the value of the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Status</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Status</em>' attribute.
     * @see #setStatus(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Status()
     * @model
     * @generated
     */
    String getStatus();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getStatus <em>Status</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Status</em>' attribute.
     * @see #getStatus()
     * @generated
     */
    void setStatus(String value);

    /**
     * Returns the value of the '<em><b>Userdef001</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef001</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef001</em>' attribute.
     * @see #setUserdef001(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef001()
     * @model
     * @generated
     */
    String getUserdef001();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef001 <em>Userdef001</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef001</em>' attribute.
     * @see #getUserdef001()
     * @generated
     */
    void setUserdef001(String value);

    /**
     * Returns the value of the '<em><b>Userdef002</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef002</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef002</em>' attribute.
     * @see #setUserdef002(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef002()
     * @model
     * @generated
     */
    String getUserdef002();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef002 <em>Userdef002</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef002</em>' attribute.
     * @see #getUserdef002()
     * @generated
     */
    void setUserdef002(String value);

    /**
     * Returns the value of the '<em><b>Userdef003</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef003</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef003</em>' attribute.
     * @see #setUserdef003(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef003()
     * @model
     * @generated
     */
    String getUserdef003();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef003 <em>Userdef003</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef003</em>' attribute.
     * @see #getUserdef003()
     * @generated
     */
    void setUserdef003(String value);

    /**
     * Returns the value of the '<em><b>Userdef004</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef004</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef004</em>' attribute.
     * @see #setUserdef004(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef004()
     * @model
     * @generated
     */
    String getUserdef004();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef004 <em>Userdef004</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef004</em>' attribute.
     * @see #getUserdef004()
     * @generated
     */
    void setUserdef004(String value);

    /**
     * Returns the value of the '<em><b>Userdef005</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef005</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef005</em>' attribute.
     * @see #setUserdef005(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef005()
     * @model
     * @generated
     */
    String getUserdef005();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef005 <em>Userdef005</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef005</em>' attribute.
     * @see #getUserdef005()
     * @generated
     */
    void setUserdef005(String value);

    /**
     * Returns the value of the '<em><b>Userdef006</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef006</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef006</em>' attribute.
     * @see #setUserdef006(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef006()
     * @model
     * @generated
     */
    String getUserdef006();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef006 <em>Userdef006</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef006</em>' attribute.
     * @see #getUserdef006()
     * @generated
     */
    void setUserdef006(String value);

    /**
     * Returns the value of the '<em><b>Userdef007</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef007</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef007</em>' attribute.
     * @see #setUserdef007(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef007()
     * @model
     * @generated
     */
    String getUserdef007();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef007 <em>Userdef007</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef007</em>' attribute.
     * @see #getUserdef007()
     * @generated
     */
    void setUserdef007(String value);

    /**
     * Returns the value of the '<em><b>Userdef008</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef008</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef008</em>' attribute.
     * @see #setUserdef008(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef008()
     * @model
     * @generated
     */
    String getUserdef008();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef008 <em>Userdef008</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef008</em>' attribute.
     * @see #getUserdef008()
     * @generated
     */
    void setUserdef008(String value);

    /**
     * Returns the value of the '<em><b>Userdef009</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef009</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef009</em>' attribute.
     * @see #setUserdef009(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef009()
     * @model
     * @generated
     */
    String getUserdef009();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef009 <em>Userdef009</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef009</em>' attribute.
     * @see #getUserdef009()
     * @generated
     */
    void setUserdef009(String value);

    /**
     * Returns the value of the '<em><b>Userdef010</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef010</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef010</em>' attribute.
     * @see #setUserdef010(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef010()
     * @model
     * @generated
     */
    String getUserdef010();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef010 <em>Userdef010</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef010</em>' attribute.
     * @see #getUserdef010()
     * @generated
     */
    void setUserdef010(String value);

    /**
     * Returns the value of the '<em><b>Userdef011</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef011</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef011</em>' attribute.
     * @see #setUserdef011(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef011()
     * @model
     * @generated
     */
    String getUserdef011();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef011 <em>Userdef011</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef011</em>' attribute.
     * @see #getUserdef011()
     * @generated
     */
    void setUserdef011(String value);

    /**
     * Returns the value of the '<em><b>Userdef012</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef012</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef012</em>' attribute.
     * @see #setUserdef012(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef012()
     * @model
     * @generated
     */
    String getUserdef012();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef012 <em>Userdef012</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef012</em>' attribute.
     * @see #getUserdef012()
     * @generated
     */
    void setUserdef012(String value);

    /**
     * Returns the value of the '<em><b>Userdef013</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef013</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef013</em>' attribute.
     * @see #setUserdef013(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef013()
     * @model
     * @generated
     */
    String getUserdef013();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef013 <em>Userdef013</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef013</em>' attribute.
     * @see #getUserdef013()
     * @generated
     */
    void setUserdef013(String value);

    /**
     * Returns the value of the '<em><b>Userdef014</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef014</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef014</em>' attribute.
     * @see #setUserdef014(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef014()
     * @model
     * @generated
     */
    String getUserdef014();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef014 <em>Userdef014</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef014</em>' attribute.
     * @see #getUserdef014()
     * @generated
     */
    void setUserdef014(String value);

    /**
     * Returns the value of the '<em><b>Userdef015</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef015</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef015</em>' attribute.
     * @see #setUserdef015(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef015()
     * @model
     * @generated
     */
    String getUserdef015();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef015 <em>Userdef015</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef015</em>' attribute.
     * @see #getUserdef015()
     * @generated
     */
    void setUserdef015(String value);

    /**
     * Returns the value of the '<em><b>Userdef016</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Userdef016</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Userdef016</em>' attribute.
     * @see #setUserdef016(String)
     * @see net.dependableos.dcase.DcasePackage#getBasicLink_Userdef016()
     * @model
     * @generated
     */
    String getUserdef016();

    /**
     * Sets the value of the '{@link net.dependableos.dcase.BasicLink#getUserdef016 <em>Userdef016</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Userdef016</em>' attribute.
     * @see #getUserdef016()
     * @generated
     */
    void setUserdef016(String value);
    
    /**
     * Returns the type name.
     * 
     * @return the type name.
     */
    String getTypeName();
    
} // BasicLink
