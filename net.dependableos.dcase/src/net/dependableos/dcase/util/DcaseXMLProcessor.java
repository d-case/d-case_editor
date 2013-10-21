/**
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.util;

import java.util.Map;


import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DcaseXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DcaseXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        DcasePackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the DcaseResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new DcaseResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new DcaseResourceFactoryImpl());
        }
        return registrations;
    }

} //DcaseXMLProcessor
