/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.clipboard;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportFactory;

/**
 * A factory class that creates a custom clipboard support.
 */
public class DcaseClipboardSupportFactory implements IClipboardSupportFactory {

    /**
     * Returns new AutoNumberClipboardSupport object.
     * 
     * @param ePackage the model object.
     * @return an AutoNumberClipboardSupport object.
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportFactory
     * #newClipboardSupport(org.eclipse.emf.ecore.EPackage)
     */
    public IClipboardSupport newClipboardSupport(EPackage ePackage) {
        return new AutoNumberClipboardSupport();
    }

    
}
