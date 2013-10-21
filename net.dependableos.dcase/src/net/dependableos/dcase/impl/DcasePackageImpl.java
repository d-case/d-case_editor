/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Context;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.DcaseLink001;
import net.dependableos.dcase.DcaseLink002;
import net.dependableos.dcase.DcaseLink003;
import net.dependableos.dcase.DcaseLink004;
import net.dependableos.dcase.DcasePackage;
import net.dependableos.dcase.Evidence;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Justification;
import net.dependableos.dcase.Monitor;
import net.dependableos.dcase.Policy;
import net.dependableos.dcase.Strategy;
import net.dependableos.dcase.Undeveloped;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef002;
import net.dependableos.dcase.Userdef003;
import net.dependableos.dcase.Userdef004;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.Userdef006;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DcasePackageImpl extends EPackageImpl implements DcasePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass basicNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass argumentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass goalEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass systemEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass strategyEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass evidenceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass monitorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass undevelopedEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass contextEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass justificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass policyEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef001EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef002EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef003EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef004EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef005EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass userdef006EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass basicLinkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dcaseLink001EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dcaseLink002EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dcaseLink003EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dcaseLink004EClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see net.dependableos.dcase.DcasePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DcasePackageImpl() {
        super(eNS_URI, DcaseFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link DcasePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DcasePackage init() {
        if (isInited) return (DcasePackage)EPackage.Registry.INSTANCE.getEPackage(DcasePackage.eNS_URI);

        // Obtain or create and register package
        DcasePackageImpl theDcasePackage = (DcasePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DcasePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DcasePackageImpl());

        isInited = true;

        // Create package meta-data objects
        theDcasePackage.createPackageContents();

        // Initialize created meta-data
        theDcasePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDcasePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DcasePackage.eNS_URI, theDcasePackage);
        return theDcasePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBasicNode() {
        return basicNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Name() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Desc() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Attachment() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Status() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef001() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef002() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef003() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef004() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef005() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef006() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef007() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef008() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef009() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef010() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef011() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef012() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef013() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef014() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef015() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicNode_Userdef016() {
        return (EAttribute)basicNodeEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getArgument() {
        return argumentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgument_RootBasicNode() {
        return (EReference)argumentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getArgument_RootBasicLink() {
        return (EReference)argumentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGoal() {
        return goalEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGoal_Score() {
        return (EAttribute)goalEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGoal_Weight() {
        return (EAttribute)goalEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSystem() {
        return systemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSystem_Score() {
        return (EAttribute)systemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSystem_Weight() {
        return (EAttribute)systemEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSystem_NodeLink() {
        return (EAttribute)systemEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public EAttribute getSystem_Param() {
        return (EAttribute)systemEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStrategy() {
        return strategyEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEvidence() {
        return evidenceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMonitor() {
        return monitorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMonitor_IsNormal() {
        return (EAttribute)monitorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUndeveloped() {
        return undevelopedEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContext() {
        return contextEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getJustification() {
        return justificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getJustification_Stakeholder() {
        return (EAttribute)justificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getJustification_RiskAnalysis() {
        return (EAttribute)justificationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPolicy() {
        return policyEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef001() {
        return userdef001EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef002() {
        return userdef002EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef003() {
        return userdef003EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef004() {
        return userdef004EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef005() {
        return userdef005EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUserdef006() {
        return userdef006EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBasicLink() {
        return basicLinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBasicLink_Source() {
        return (EReference)basicLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBasicLink_Target() {
        return (EReference)basicLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Name() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Desc() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Attachment() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Status() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef001() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef002() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef003() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef004() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef005() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef006() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef007() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef008() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef009() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef010() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef011() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef012() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef013() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef014() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef015() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBasicLink_Userdef016() {
        return (EAttribute)basicLinkEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDcaseLink001() {
        return dcaseLink001EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDcaseLink002() {
        return dcaseLink002EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDcaseLink003() {
        return dcaseLink003EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDcaseLink004() {
        return dcaseLink004EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseFactory getDcaseFactory() {
        return (DcaseFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        basicNodeEClass = createEClass(BASIC_NODE);
        createEAttribute(basicNodeEClass, BASIC_NODE__NAME);
        createEAttribute(basicNodeEClass, BASIC_NODE__DESC);
        createEAttribute(basicNodeEClass, BASIC_NODE__ATTACHMENT);
        createEAttribute(basicNodeEClass, BASIC_NODE__STATUS);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF001);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF002);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF003);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF004);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF005);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF006);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF007);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF008);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF009);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF010);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF011);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF012);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF013);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF014);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF015);
        createEAttribute(basicNodeEClass, BASIC_NODE__USERDEF016);

        argumentEClass = createEClass(ARGUMENT);
        createEReference(argumentEClass, ARGUMENT__ROOT_BASIC_NODE);
        createEReference(argumentEClass, ARGUMENT__ROOT_BASIC_LINK);

        goalEClass = createEClass(GOAL);
        createEAttribute(goalEClass, GOAL__SCORE);
        createEAttribute(goalEClass, GOAL__WEIGHT);

        systemEClass = createEClass(SYSTEM);
        createEAttribute(systemEClass, SYSTEM__SCORE);
        createEAttribute(systemEClass, SYSTEM__WEIGHT);
        createEAttribute(systemEClass, SYSTEM__NODE_LINK);
        createEAttribute(systemEClass, SYSTEM__PARAM);

        strategyEClass = createEClass(STRATEGY);

        evidenceEClass = createEClass(EVIDENCE);

        monitorEClass = createEClass(MONITOR);
        createEAttribute(monitorEClass, MONITOR__IS_NORMAL);

        undevelopedEClass = createEClass(UNDEVELOPED);

        contextEClass = createEClass(CONTEXT);

        justificationEClass = createEClass(JUSTIFICATION);
        createEAttribute(justificationEClass, JUSTIFICATION__STAKEHOLDER);
        createEAttribute(justificationEClass, JUSTIFICATION__RISK_ANALYSIS);

        policyEClass = createEClass(POLICY);

        userdef001EClass = createEClass(USERDEF001);

        userdef002EClass = createEClass(USERDEF002);

        userdef003EClass = createEClass(USERDEF003);

        userdef004EClass = createEClass(USERDEF004);

        userdef005EClass = createEClass(USERDEF005);

        userdef006EClass = createEClass(USERDEF006);

        basicLinkEClass = createEClass(BASIC_LINK);
        createEReference(basicLinkEClass, BASIC_LINK__SOURCE);
        createEReference(basicLinkEClass, BASIC_LINK__TARGET);
        createEAttribute(basicLinkEClass, BASIC_LINK__NAME);
        createEAttribute(basicLinkEClass, BASIC_LINK__DESC);
        createEAttribute(basicLinkEClass, BASIC_LINK__ATTACHMENT);
        createEAttribute(basicLinkEClass, BASIC_LINK__STATUS);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF001);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF002);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF003);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF004);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF005);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF006);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF007);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF008);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF009);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF010);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF011);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF012);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF013);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF014);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF015);
        createEAttribute(basicLinkEClass, BASIC_LINK__USERDEF016);

        dcaseLink001EClass = createEClass(DCASE_LINK001);

        dcaseLink002EClass = createEClass(DCASE_LINK002);

        dcaseLink003EClass = createEClass(DCASE_LINK003);

        dcaseLink004EClass = createEClass(DCASE_LINK004);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        argumentEClass.getESuperTypes().add(this.getBasicNode());
        goalEClass.getESuperTypes().add(this.getBasicNode());
        systemEClass.getESuperTypes().add(this.getBasicNode());
        strategyEClass.getESuperTypes().add(this.getBasicNode());
        evidenceEClass.getESuperTypes().add(this.getBasicNode());
        monitorEClass.getESuperTypes().add(this.getBasicNode());
        undevelopedEClass.getESuperTypes().add(this.getBasicNode());
        contextEClass.getESuperTypes().add(this.getBasicNode());
        justificationEClass.getESuperTypes().add(this.getBasicNode());
        policyEClass.getESuperTypes().add(this.getBasicNode());
        userdef001EClass.getESuperTypes().add(this.getBasicNode());
        userdef002EClass.getESuperTypes().add(this.getBasicNode());
        userdef003EClass.getESuperTypes().add(this.getBasicNode());
        userdef004EClass.getESuperTypes().add(this.getBasicNode());
        userdef005EClass.getESuperTypes().add(this.getBasicNode());
        userdef006EClass.getESuperTypes().add(this.getBasicNode());
        dcaseLink001EClass.getESuperTypes().add(this.getBasicLink());
        dcaseLink002EClass.getESuperTypes().add(this.getBasicLink());
        dcaseLink003EClass.getESuperTypes().add(this.getBasicLink());
        dcaseLink004EClass.getESuperTypes().add(this.getBasicLink());

        // Initialize classes and features; add operations and parameters
        initEClass(basicNodeEClass, BasicNode.class, "BasicNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBasicNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Desc(), ecorePackage.getEString(), "desc", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Attachment(), ecorePackage.getEString(), "attachment", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Status(), ecorePackage.getEString(), "status", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef001(), ecorePackage.getEString(), "userdef001", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef002(), ecorePackage.getEString(), "userdef002", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef003(), ecorePackage.getEString(), "userdef003", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef004(), ecorePackage.getEString(), "userdef004", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef005(), ecorePackage.getEString(), "userdef005", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef006(), ecorePackage.getEString(), "userdef006", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef007(), ecorePackage.getEString(), "userdef007", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef008(), ecorePackage.getEString(), "userdef008", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef009(), ecorePackage.getEString(), "userdef009", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef010(), ecorePackage.getEString(), "userdef010", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef011(), ecorePackage.getEString(), "userdef011", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef012(), ecorePackage.getEString(), "userdef012", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef013(), ecorePackage.getEString(), "userdef013", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef014(), ecorePackage.getEString(), "userdef014", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef015(), ecorePackage.getEString(), "userdef015", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicNode_Userdef016(), ecorePackage.getEString(), "userdef016", null, 0, 1, BasicNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(argumentEClass, Argument.class, "Argument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getArgument_RootBasicNode(), this.getBasicNode(), null, "rootBasicNode", null, 0, -1, Argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getArgument_RootBasicLink(), this.getBasicLink(), null, "rootBasicLink", null, 0, -1, Argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(goalEClass, Goal.class, "Goal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGoal_Score(), ecorePackage.getEBigDecimal(), "score", "0", 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGoal_Weight(), ecorePackage.getEInt(), "weight", "1", 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(systemEClass, net.dependableos.dcase.System.class, "System", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSystem_Score(), ecorePackage.getEBigDecimal(), "score", "0", 0, 1, net.dependableos.dcase.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSystem_Weight(), ecorePackage.getEInt(), "weight", "1", 0, 1, net.dependableos.dcase.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSystem_NodeLink(), ecorePackage.getEString(), "nodeLink", null, 0, 1, net.dependableos.dcase.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSystem_Param(), ecorePackage.getEString(), "param", null, 0, 1, net.dependableos.dcase.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(strategyEClass, Strategy.class, "Strategy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(evidenceEClass, Evidence.class, "Evidence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(monitorEClass, Monitor.class, "Monitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMonitor_IsNormal(), ecorePackage.getEBoolean(), "isNormal", "false", 0, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(undevelopedEClass, Undeveloped.class, "Undeveloped", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(contextEClass, Context.class, "Context", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(justificationEClass, Justification.class, "Justification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getJustification_Stakeholder(), ecorePackage.getEString(), "stakeholder", "", 0, 1, Justification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getJustification_RiskAnalysis(), ecorePackage.getEString(), "riskAnalysis", null, 0, 1, Justification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(policyEClass, Policy.class, "Policy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef001EClass, Userdef001.class, "Userdef001", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef002EClass, Userdef002.class, "Userdef002", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef003EClass, Userdef003.class, "Userdef003", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef004EClass, Userdef004.class, "Userdef004", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef005EClass, Userdef005.class, "Userdef005", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(userdef006EClass, Userdef006.class, "Userdef006", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(basicLinkEClass, BasicLink.class, "BasicLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBasicLink_Source(), this.getBasicNode(), null, "source", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBasicLink_Target(), this.getBasicNode(), null, "target", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Name(), ecorePackage.getEString(), "name", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Desc(), ecorePackage.getEString(), "desc", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Attachment(), ecorePackage.getEString(), "attachment", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Status(), ecorePackage.getEString(), "status", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef001(), ecorePackage.getEString(), "userdef001", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef002(), ecorePackage.getEString(), "userdef002", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef003(), ecorePackage.getEString(), "userdef003", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef004(), ecorePackage.getEString(), "userdef004", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef005(), ecorePackage.getEString(), "userdef005", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef006(), ecorePackage.getEString(), "userdef006", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef007(), ecorePackage.getEString(), "userdef007", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef008(), ecorePackage.getEString(), "userdef008", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef009(), ecorePackage.getEString(), "userdef009", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef010(), ecorePackage.getEString(), "userdef010", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef011(), ecorePackage.getEString(), "userdef011", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef012(), ecorePackage.getEString(), "userdef012", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef013(), ecorePackage.getEString(), "userdef013", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef014(), ecorePackage.getEString(), "userdef014", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef015(), ecorePackage.getEString(), "userdef015", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLink_Userdef016(), ecorePackage.getEString(), "userdef016", null, 0, 1, BasicLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dcaseLink001EClass, DcaseLink001.class, "DcaseLink001", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dcaseLink002EClass, DcaseLink002.class, "DcaseLink002", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dcaseLink003EClass, DcaseLink003.class, "DcaseLink003", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dcaseLink004EClass, DcaseLink004.class, "DcaseLink004", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //DcasePackageImpl
