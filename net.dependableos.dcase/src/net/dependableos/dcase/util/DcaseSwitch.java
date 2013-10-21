/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.util;

import java.util.List;


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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see net.dependableos.dcase.DcasePackage
 * @generated
 */
public class DcaseSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DcasePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseSwitch() {
        if (modelPackage == null) {
            modelPackage = DcasePackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case DcasePackage.BASIC_NODE: {
                BasicNode basicNode = (BasicNode)theEObject;
                T result = caseBasicNode(basicNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.ARGUMENT: {
                Argument argument = (Argument)theEObject;
                T result = caseArgument(argument);
                if (result == null) result = caseBasicNode(argument);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.GOAL: {
                Goal goal = (Goal)theEObject;
                T result = caseGoal(goal);
                if (result == null) result = caseBasicNode(goal);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.SYSTEM: {
                net.dependableos.dcase.System system = (net.dependableos.dcase.System)theEObject;
                T result = caseSystem(system);
                if (result == null) result = caseBasicNode(system);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.STRATEGY: {
                Strategy strategy = (Strategy)theEObject;
                T result = caseStrategy(strategy);
                if (result == null) result = caseBasicNode(strategy);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.EVIDENCE: {
                Evidence evidence = (Evidence)theEObject;
                T result = caseEvidence(evidence);
                if (result == null) result = caseBasicNode(evidence);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.MONITOR: {
                Monitor monitor = (Monitor)theEObject;
                T result = caseMonitor(monitor);
                if (result == null) result = caseBasicNode(monitor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.UNDEVELOPED: {
                Undeveloped undeveloped = (Undeveloped)theEObject;
                T result = caseUndeveloped(undeveloped);
                if (result == null) result = caseBasicNode(undeveloped);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.CONTEXT: {
                Context context = (Context)theEObject;
                T result = caseContext(context);
                if (result == null) result = caseBasicNode(context);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.JUSTIFICATION: {
                Justification justification = (Justification)theEObject;
                T result = caseJustification(justification);
                if (result == null) result = caseBasicNode(justification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.POLICY: {
                Policy policy = (Policy)theEObject;
                T result = casePolicy(policy);
                if (result == null) result = caseBasicNode(policy);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF001: {
                Userdef001 userdef001 = (Userdef001)theEObject;
                T result = caseUserdef001(userdef001);
                if (result == null) result = caseBasicNode(userdef001);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF002: {
                Userdef002 userdef002 = (Userdef002)theEObject;
                T result = caseUserdef002(userdef002);
                if (result == null) result = caseBasicNode(userdef002);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF003: {
                Userdef003 userdef003 = (Userdef003)theEObject;
                T result = caseUserdef003(userdef003);
                if (result == null) result = caseBasicNode(userdef003);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF004: {
                Userdef004 userdef004 = (Userdef004)theEObject;
                T result = caseUserdef004(userdef004);
                if (result == null) result = caseBasicNode(userdef004);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF005: {
                Userdef005 userdef005 = (Userdef005)theEObject;
                T result = caseUserdef005(userdef005);
                if (result == null) result = caseBasicNode(userdef005);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.USERDEF006: {
                Userdef006 userdef006 = (Userdef006)theEObject;
                T result = caseUserdef006(userdef006);
                if (result == null) result = caseBasicNode(userdef006);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.BASIC_LINK: {
                BasicLink basicLink = (BasicLink)theEObject;
                T result = caseBasicLink(basicLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.DCASE_LINK001: {
                DcaseLink001 dcaseLink001 = (DcaseLink001)theEObject;
                T result = caseDcaseLink001(dcaseLink001);
                if (result == null) result = caseBasicLink(dcaseLink001);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.DCASE_LINK002: {
                DcaseLink002 dcaseLink002 = (DcaseLink002)theEObject;
                T result = caseDcaseLink002(dcaseLink002);
                if (result == null) result = caseBasicLink(dcaseLink002);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.DCASE_LINK003: {
                DcaseLink003 dcaseLink003 = (DcaseLink003)theEObject;
                T result = caseDcaseLink003(dcaseLink003);
                if (result == null) result = caseBasicLink(dcaseLink003);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DcasePackage.DCASE_LINK004: {
                DcaseLink004 dcaseLink004 = (DcaseLink004)theEObject;
                T result = caseDcaseLink004(dcaseLink004);
                if (result == null) result = caseBasicLink(dcaseLink004);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicNode(BasicNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Argument</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Argument</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseArgument(Argument object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Goal</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Goal</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGoal(Goal object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>System</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>System</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSystem(net.dependableos.dcase.System object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Strategy</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Strategy</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStrategy(Strategy object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Evidence</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Evidence</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEvidence(Evidence object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Monitor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Monitor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMonitor(Monitor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Undeveloped</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Undeveloped</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUndeveloped(Undeveloped object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Context</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Context</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContext(Context object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Justification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Justification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseJustification(Justification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Policy</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Policy</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePolicy(Policy object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef001</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef001</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef001(Userdef001 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef002</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef002</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef002(Userdef002 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef003</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef003</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef003(Userdef003 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef004</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef004</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef004(Userdef004 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef005</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef005</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef005(Userdef005 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Userdef006</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Userdef006</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserdef006(Userdef006 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLink(BasicLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link001</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link001</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDcaseLink001(DcaseLink001 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link002</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link002</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDcaseLink002(DcaseLink002 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link003</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link003</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDcaseLink003(DcaseLink003 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link004</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link004</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDcaseLink004(DcaseLink004 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} //DcaseSwitch
