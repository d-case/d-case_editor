/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.util;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Context;
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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.dependableos.dcase.DcasePackage
 * @generated
 */
public class DcaseAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DcasePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = DcasePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DcaseSwitch<Adapter> modelSwitch =
        new DcaseSwitch<Adapter>() {
            @Override
            public Adapter caseBasicNode(BasicNode object) {
                return createBasicNodeAdapter();
            }
            @Override
            public Adapter caseArgument(Argument object) {
                return createArgumentAdapter();
            }
            @Override
            public Adapter caseGoal(Goal object) {
                return createGoalAdapter();
            }
            @Override
            public Adapter caseSystem(net.dependableos.dcase.System object) {
                return createSystemAdapter();
            }
            @Override
            public Adapter caseStrategy(Strategy object) {
                return createStrategyAdapter();
            }
            @Override
            public Adapter caseEvidence(Evidence object) {
                return createEvidenceAdapter();
            }
            @Override
            public Adapter caseMonitor(Monitor object) {
                return createMonitorAdapter();
            }
            @Override
            public Adapter caseUndeveloped(Undeveloped object) {
                return createUndevelopedAdapter();
            }
            @Override
            public Adapter caseContext(Context object) {
                return createContextAdapter();
            }
            @Override
            public Adapter caseJustification(Justification object) {
                return createJustificationAdapter();
            }
            @Override
            public Adapter casePolicy(Policy object) {
                return createPolicyAdapter();
            }
            @Override
            public Adapter caseUserdef001(Userdef001 object) {
                return createUserdef001Adapter();
            }
            @Override
            public Adapter caseUserdef002(Userdef002 object) {
                return createUserdef002Adapter();
            }
            @Override
            public Adapter caseUserdef003(Userdef003 object) {
                return createUserdef003Adapter();
            }
            @Override
            public Adapter caseUserdef004(Userdef004 object) {
                return createUserdef004Adapter();
            }
            @Override
            public Adapter caseUserdef005(Userdef005 object) {
                return createUserdef005Adapter();
            }
            @Override
            public Adapter caseUserdef006(Userdef006 object) {
                return createUserdef006Adapter();
            }
            @Override
            public Adapter caseBasicLink(BasicLink object) {
                return createBasicLinkAdapter();
            }
            @Override
            public Adapter caseDcaseLink001(DcaseLink001 object) {
                return createDcaseLink001Adapter();
            }
            @Override
            public Adapter caseDcaseLink002(DcaseLink002 object) {
                return createDcaseLink002Adapter();
            }
            @Override
            public Adapter caseDcaseLink003(DcaseLink003 object) {
                return createDcaseLink003Adapter();
            }
            @Override
            public Adapter caseDcaseLink004(DcaseLink004 object) {
                return createDcaseLink004Adapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.BasicNode <em>Basic Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.BasicNode
     * @generated
     */
    public Adapter createBasicNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Argument <em>Argument</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Argument
     * @generated
     */
    public Adapter createArgumentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Goal <em>Goal</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Goal
     * @generated
     */
    public Adapter createGoalAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.System <em>System</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.System
     * @generated
     */
    public Adapter createSystemAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Strategy <em>Strategy</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Strategy
     * @generated
     */
    public Adapter createStrategyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Evidence <em>Evidence</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Evidence
     * @generated
     */
    public Adapter createEvidenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Monitor <em>Monitor</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Monitor
     * @generated
     */
    public Adapter createMonitorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Undeveloped <em>Undeveloped</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Undeveloped
     * @generated
     */
    public Adapter createUndevelopedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Context <em>Context</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Context
     * @generated
     */
    public Adapter createContextAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Justification <em>Justification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Justification
     * @generated
     */
    public Adapter createJustificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Policy <em>Policy</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Policy
     * @generated
     */
    public Adapter createPolicyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef001 <em>Userdef001</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef001
     * @generated
     */
    public Adapter createUserdef001Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef002 <em>Userdef002</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef002
     * @generated
     */
    public Adapter createUserdef002Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef003 <em>Userdef003</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef003
     * @generated
     */
    public Adapter createUserdef003Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef004 <em>Userdef004</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef004
     * @generated
     */
    public Adapter createUserdef004Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef005 <em>Userdef005</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef005
     * @generated
     */
    public Adapter createUserdef005Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.Userdef006 <em>Userdef006</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.Userdef006
     * @generated
     */
    public Adapter createUserdef006Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.BasicLink <em>Basic Link</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.BasicLink
     * @generated
     */
    public Adapter createBasicLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.DcaseLink001 <em>Link001</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.DcaseLink001
     * @generated
     */
    public Adapter createDcaseLink001Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.DcaseLink002 <em>Link002</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.DcaseLink002
     * @generated
     */
    public Adapter createDcaseLink002Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.DcaseLink003 <em>Link003</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.DcaseLink003
     * @generated
     */
    public Adapter createDcaseLink003Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.dependableos.dcase.DcaseLink004 <em>Link004</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.dependableos.dcase.DcaseLink004
     * @generated
     */
    public Adapter createDcaseLink004Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //DcaseAdapterFactory
