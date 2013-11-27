/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.provider;


import java.util.Collection;
import java.util.List;


import net.dependableos.dcase.BasicLink;
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
 * This is the item provider adapter for a {@link net.dependableos.dcase.BasicLink} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BasicLinkItemProvider
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
    public BasicLinkItemProvider(AdapterFactory adapterFactory) {
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

            addSourcePropertyDescriptor(object);
            addTargetPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addDescPropertyDescriptor(object);
            addAttachmentPropertyDescriptor(object);
            addStatusPropertyDescriptor(object);
            addSiblingOrderPropertyDescriptor(object);
            addMessagePropertyDescriptor(object);
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
     * This adds a property descriptor for the Source feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSourcePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicLink_source_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_source_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__SOURCE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Target feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addTargetPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicLink_target_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_target_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__TARGET,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
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
                 getString("_UI_BasicLink_name_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_name_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__NAME,
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
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicLink_desc_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_desc_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__DESC,
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
                 getString("_UI_BasicLink_attachment_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_attachment_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__ATTACHMENT,
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
                 getString("_UI_BasicLink_status_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_status_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__STATUS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the SiblingOrder feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected void addSiblingOrderPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_BasicLink_siblingOrder_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_siblingOrder_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__SLIBINGORDER,
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
                 getString("_UI_BasicLink_message_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_message_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__MESSAGE,
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
                 getString("_UI_BasicLink_userdef001_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef001_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF001,
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
                 getString("_UI_BasicLink_userdef002_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef002_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF002,
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
                 getString("_UI_BasicLink_userdef003_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef003_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF003,
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
                 getString("_UI_BasicLink_userdef004_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef004_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF004,
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
                 getString("_UI_BasicLink_userdef005_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef005_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF005,
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
                 getString("_UI_BasicLink_userdef006_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef006_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF006,
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
                 getString("_UI_BasicLink_userdef007_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef007_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF007,
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
                 getString("_UI_BasicLink_userdef008_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef008_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF008,
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
                 getString("_UI_BasicLink_userdef009_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef009_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF009,
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
                 getString("_UI_BasicLink_userdef010_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef010_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF010,
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
                 getString("_UI_BasicLink_userdef011_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef011_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF011,
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
                 getString("_UI_BasicLink_userdef012_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef012_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF012,
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
                 getString("_UI_BasicLink_userdef013_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef013_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF013,
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
                 getString("_UI_BasicLink_userdef014_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef014_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF014,
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
                 getString("_UI_BasicLink_userdef015_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef015_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF015,
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
                 getString("_UI_BasicLink_userdef016_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_BasicLink_userdef016_feature", ((BasicLink)object).getTypeName()),
                 DcasePackage.Literals.BASIC_LINK__USERDEF016,
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
        String label = ((BasicLink)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_BasicLink_type") :
            getString("_UI_BasicLink_type") + " " + label;
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

        switch (notification.getFeatureID(BasicLink.class)) {
            case DcasePackage.BASIC_LINK__NAME:
            case DcasePackage.BASIC_LINK__DESC:
            case DcasePackage.BASIC_LINK__ATTACHMENT:
            case DcasePackage.BASIC_LINK__STATUS:
            case DcasePackage.BASIC_LINK__SIBLINGORDER:
            case DcasePackage.BASIC_LINK__MESSAGE:
            case DcasePackage.BASIC_LINK__USERDEF001:
            case DcasePackage.BASIC_LINK__USERDEF002:
            case DcasePackage.BASIC_LINK__USERDEF003:
            case DcasePackage.BASIC_LINK__USERDEF004:
            case DcasePackage.BASIC_LINK__USERDEF005:
            case DcasePackage.BASIC_LINK__USERDEF006:
            case DcasePackage.BASIC_LINK__USERDEF007:
            case DcasePackage.BASIC_LINK__USERDEF008:
            case DcasePackage.BASIC_LINK__USERDEF009:
            case DcasePackage.BASIC_LINK__USERDEF010:
            case DcasePackage.BASIC_LINK__USERDEF011:
            case DcasePackage.BASIC_LINK__USERDEF012:
            case DcasePackage.BASIC_LINK__USERDEF013:
            case DcasePackage.BASIC_LINK__USERDEF014:
            case DcasePackage.BASIC_LINK__USERDEF015:
            case DcasePackage.BASIC_LINK__USERDEF016:
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
