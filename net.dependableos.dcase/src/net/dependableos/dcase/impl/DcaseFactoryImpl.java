/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.impl;


import net.dependableos.dcase.Argument;
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DcaseFactoryImpl extends EFactoryImpl implements DcaseFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DcaseFactory init() {
        try {
            DcaseFactory theDcaseFactory = (DcaseFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.dependable-os.net/2010/03/dcase/"); 
            if (theDcaseFactory != null) {
                return theDcaseFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DcaseFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case DcasePackage.ARGUMENT: return createArgument();
            case DcasePackage.GOAL: return createGoal();
            case DcasePackage.SYSTEM: return createSystem();
            case DcasePackage.STRATEGY: return createStrategy();
            case DcasePackage.EVIDENCE: return createEvidence();
            case DcasePackage.MONITOR: return createMonitor();
            case DcasePackage.UNDEVELOPED: return createUndeveloped();
            case DcasePackage.CONTEXT: return createContext();
            case DcasePackage.JUSTIFICATION: return createJustification();
            case DcasePackage.POLICY: return createPolicy();
            case DcasePackage.USERDEF001: return createUserdef001();
            case DcasePackage.USERDEF002: return createUserdef002();
            case DcasePackage.USERDEF003: return createUserdef003();
            case DcasePackage.USERDEF004: return createUserdef004();
            case DcasePackage.USERDEF005: return createUserdef005();
            case DcasePackage.USERDEF006: return createUserdef006();
            case DcasePackage.DCASE_LINK001: return createDcaseLink001();
            case DcasePackage.DCASE_LINK002: return createDcaseLink002();
            case DcasePackage.DCASE_LINK003: return createDcaseLink003();
            case DcasePackage.DCASE_LINK004: return createDcaseLink004();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Argument createArgument() {
        ArgumentImpl argument = new ArgumentImpl();
        return argument;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Goal createGoal() {
        GoalImpl goal = new GoalImpl();
        return goal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public net.dependableos.dcase.System createSystem() {
        SystemImpl system = new SystemImpl();
        return system;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Strategy createStrategy() {
        StrategyImpl strategy = new StrategyImpl();
        return strategy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Evidence createEvidence() {
        EvidenceImpl evidence = new EvidenceImpl();
        return evidence;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Monitor createMonitor() {
        MonitorImpl monitor = new MonitorImpl();
        return monitor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Undeveloped createUndeveloped() {
        UndevelopedImpl undeveloped = new UndevelopedImpl();
        return undeveloped;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Context createContext() {
        ContextImpl context = new ContextImpl();
        return context;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Justification createJustification() {
        JustificationImpl justification = new JustificationImpl();
        return justification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Policy createPolicy() {
        PolicyImpl policy = new PolicyImpl();
        return policy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef001 createUserdef001() {
        Userdef001Impl userdef001 = new Userdef001Impl();
        return userdef001;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef002 createUserdef002() {
        Userdef002Impl userdef002 = new Userdef002Impl();
        return userdef002;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef003 createUserdef003() {
        Userdef003Impl userdef003 = new Userdef003Impl();
        return userdef003;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef004 createUserdef004() {
        Userdef004Impl userdef004 = new Userdef004Impl();
        return userdef004;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef005 createUserdef005() {
        Userdef005Impl userdef005 = new Userdef005Impl();
        return userdef005;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Userdef006 createUserdef006() {
        Userdef006Impl userdef006 = new Userdef006Impl();
        return userdef006;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseLink001 createDcaseLink001() {
        DcaseLink001Impl dcaseLink001 = new DcaseLink001Impl();
        return dcaseLink001;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseLink002 createDcaseLink002() {
        DcaseLink002Impl dcaseLink002 = new DcaseLink002Impl();
        return dcaseLink002;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseLink003 createDcaseLink003() {
        DcaseLink003Impl dcaseLink003 = new DcaseLink003Impl();
        return dcaseLink003;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseLink004 createDcaseLink004() {
        DcaseLink004Impl dcaseLink004 = new DcaseLink004Impl();
        return dcaseLink004;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcasePackage getDcasePackage() {
        return (DcasePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DcasePackage getPackage() {
        return DcasePackage.eINSTANCE;
    }

} //DcaseFactoryImpl
