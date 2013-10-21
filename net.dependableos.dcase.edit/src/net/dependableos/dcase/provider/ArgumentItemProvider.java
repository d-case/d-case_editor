/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
// AUTO_GENERATED:START
package net.dependableos.dcase.provider;


import java.util.Collection;
import java.util.List;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.DcasePackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link net.dependableos.dcase.Argument} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArgumentItemProvider
    extends BasicNodeItemProvider
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
    public ArgumentItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE);
            childrenFeatures.add(DcasePackage.Literals.ARGUMENT__ROOT_BASIC_LINK);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns Argument.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Argument"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Argument)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Argument_type") :
            getString("_UI_Argument_type") + " " + label;
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

        switch (notification.getFeatureID(Argument.class)) {
            case DcasePackage.ARGUMENT__ROOT_BASIC_NODE:
            case DcasePackage.ARGUMENT__ROOT_BASIC_LINK:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createArgument()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createGoal()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createSystem()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createStrategy()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createEvidence()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createMonitor()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUndeveloped()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createContext()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createJustification()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createPolicy()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef001()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef002()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef003()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef004()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef005()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_NODE,
                 DcaseFactory.eINSTANCE.createUserdef006()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_LINK,
                 DcaseFactory.eINSTANCE.createDcaseLink001()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_LINK,
                 DcaseFactory.eINSTANCE.createDcaseLink002()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_LINK,
                 DcaseFactory.eINSTANCE.createDcaseLink003()));

        newChildDescriptors.add
            (createChildParameter
                (DcasePackage.Literals.ARGUMENT__ROOT_BASIC_LINK,
                 DcaseFactory.eINSTANCE.createDcaseLink004()));
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_name_feature"), 
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_name_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addDescPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new DescPropertyDescription
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_desc_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_desc_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__DESC,
                 true,
                 true,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addAttachmentPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_attachment_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_attachment_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__ATTACHMENT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addStatusPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_status_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_status_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__STATUS,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef001PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef001_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef001_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF001,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef002PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef002_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef002_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF002,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef003PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef003_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef003_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF003,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef004PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef004_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef004_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF004,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef005PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef005_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef005_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF005,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef006PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef006_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef006_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF006,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef007PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef007_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef007_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF007,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef008PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef008_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef008_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF008,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef009PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef009_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef009_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF009,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef010PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef010_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef010_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF010,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef011PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef011_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef011_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF011,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef012PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef012_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef012_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF012,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef013PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef013_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef013_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF013,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef014PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef014_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef014_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF014,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef015PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef015_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef015_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF015,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addUserdef016PropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Argument_userdef016_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Argument_userdef016_feature", "_UI_Argument_type"), 
                 DcasePackage.Literals.BASIC_NODE__USERDEF016,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }


}
