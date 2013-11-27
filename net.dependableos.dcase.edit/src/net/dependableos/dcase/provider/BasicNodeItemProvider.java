/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.provider;


import java.util.Collection;
import java.util.List;


import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link net.dependableos.dcase.BasicNode} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BasicNodeItemProvider
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BasicNodeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addDescPropertyDescriptor(object);
            addAttachmentPropertyDescriptor(object);
            addStatusPropertyDescriptor(object);
            addFlagPropertyDescriptor(object);
            addRespNamePropertyDescriptor(object);
            addRespAddressPropertyDescriptor(object);
            addRespIconPropertyDescriptor(object);
            addRespTimePropertyDescriptor(object);
            addMessagePropertyDescriptor(object);
            addRequirementPropertyDescriptor(object);
            addParentPropertyDescriptor(object);
            addRefSourcePropertyDescriptor(object);
            addParameterDefsPropertyDescriptor(object);
            addParameterValsPropertyDescriptor(object);
            addParameterizedDescPropertyDescriptor(object);
            addUserdef001PropertyDescriptor(object);
            addUserdef002PropertyDescriptor(object);
            addUserdef003PropertyDescriptor(object);
            addUserdef004PropertyDescriptor(object);
            addUserdef005PropertyDescriptor(object);
            addUserdef006PropertyDescriptor(object);
            addUserdef007PropertyDescriptor(object);
            addUserdef008PropertyDescriptor(object);
            addUserdef009PropertyDescriptor(object);
            addUserdef010PropertyDescriptor(object);
            addUserdef011PropertyDescriptor(object);
            addUserdef012PropertyDescriptor(object);
            addUserdef013PropertyDescriptor(object);
            addUserdef014PropertyDescriptor(object);
            addUserdef015PropertyDescriptor(object);
            addUserdef016PropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_name_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_name_feature", ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Desc feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addDescPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
        (new DescPropertyDescription(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             getResourceLocator(),
             getString("_UI_BasicNode_desc_feature"),
             getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_desc_feature",  ((BasicNode)object).getTypeName()),
             DcasePackage.Literals.BASIC_NODE__DESC,
             true,
             true,
             false,
             ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
             null,
             null));
    }

    /**
     * This adds a property descriptor for the Attachment feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addAttachmentPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_attachment_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_attachment_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__ATTACHMENT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Status feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addStatusPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_status_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_status_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__STATUS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Flag feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addFlagPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_flag_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_flag_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__FLAG,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the RespName feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRespNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_respName_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_respName_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__RESPNAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the RespAddress feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRespAddressPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_respAddress_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_respAddress_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__RESPADDRESS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the RespIcon feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRespIconPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_respIcon_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_respIcon_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__RESPICON,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the RespTime feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRespTimePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_respTime_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_respTime_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__RESPTIME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Message feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_message_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_message_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__MESSAGE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Requirement feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRequirementPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_requirement_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_requirement_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__REQUIREMENT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Parent feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addParentPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_parent_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_parent_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__PARENT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the RefSource feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addRefSourcePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_refSource_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_refSource_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__REFSOURCE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the ParameterDefs feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addParameterDefsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_parameterDefs_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_parameterDefs_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__PARAMETERDEFS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the ParameterVals feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addParameterValsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_parameterVals_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_parameterVals_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__PARAMETERVALS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the ParameterizedDesc feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addParameterizedDescPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_parameterizedDesc_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_parameterizedDesc_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__PARAMETERIZEDDESC,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef001 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef001PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef001_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef001_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF001,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef002 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef002PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef002_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef002_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF002,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef003 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef003PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef003_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef003_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF003,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef004 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef004PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef004_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef004_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF004,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef005 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef005PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef005_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef005_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF005,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef006 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef006PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef006_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef006_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF006,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef007 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef007PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef007_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef007_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF007,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef008 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef008PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef008_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef008_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF008,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef009 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef009PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef009_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef009_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF009,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef010 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef010PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef010_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef010_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF010,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef011 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef011PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef011_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef011_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF011,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef012 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef012PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef012_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef012_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF012,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef013 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef013PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef013_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef013_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF013,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef014 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef014PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef014_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef014_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF014,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef015 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef015PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef015_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef015_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF015,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Userdef016 feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addUserdef016PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicNode_userdef016_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicNode_userdef016_feature",  ((BasicNode)object).getTypeName()),
                 DcasePackage.Literals.BASIC_NODE__USERDEF016,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((BasicNode)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_BasicNode_type") :
            getString("_UI_BasicNode_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(BasicNode.class)) {
            case DcasePackage.BASIC_NODE__NAME:
            case DcasePackage.BASIC_NODE__DESC:
            case DcasePackage.BASIC_NODE__ATTACHMENT:
            case DcasePackage.BASIC_NODE__STATUS:
            case DcasePackage.BASIC_NODE__FLAG:
            case DcasePackage.BASIC_NODE__RESPNAME:
            case DcasePackage.BASIC_NODE__RESPADDRESS:
            case DcasePackage.BASIC_NODE__RESPICON:
            case DcasePackage.BASIC_NODE__RESPTIME:
            case DcasePackage.BASIC_NODE__MESSAGE:
            case DcasePackage.BASIC_NODE__REQUIREMENT:
            case DcasePackage.BASIC_NODE__PARENT:
            case DcasePackage.BASIC_NODE__REFSOURCE:
            case DcasePackage.BASIC_NODE__PARAMETERDEFS:
            case DcasePackage.BASIC_NODE__PARAMETERVALS:
            case DcasePackage.BASIC_NODE__PARAMETERIZEDDESC:
            case DcasePackage.BASIC_NODE__USERDEF001:
            case DcasePackage.BASIC_NODE__USERDEF002:
            case DcasePackage.BASIC_NODE__USERDEF003:
            case DcasePackage.BASIC_NODE__USERDEF004:
            case DcasePackage.BASIC_NODE__USERDEF005:
            case DcasePackage.BASIC_NODE__USERDEF006:
            case DcasePackage.BASIC_NODE__USERDEF007:
            case DcasePackage.BASIC_NODE__USERDEF008:
            case DcasePackage.BASIC_NODE__USERDEF009:
            case DcasePackage.BASIC_NODE__USERDEF010:
            case DcasePackage.BASIC_NODE__USERDEF011:
            case DcasePackage.BASIC_NODE__USERDEF012:
            case DcasePackage.BASIC_NODE__USERDEF013:
            case DcasePackage.BASIC_NODE__USERDEF014:
            case DcasePackage.BASIC_NODE__USERDEF015:
            case DcasePackage.BASIC_NODE__USERDEF016:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return DcaseEditPlugin.INSTANCE;
    }

}
