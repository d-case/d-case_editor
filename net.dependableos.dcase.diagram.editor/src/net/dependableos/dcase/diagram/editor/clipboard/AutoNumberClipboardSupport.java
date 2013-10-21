/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.clipboard;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.util.NamePropertyAutoCreator;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * A class that overrides paste operation to set the name attribute of a node or a link.
 */
public class AutoNumberClipboardSupport extends AbstractClipboardSupport {

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.AbstractClipboardSupport
     * #setName(org.eclipse.emf.ecore.EObject, java.lang.String)
     */
    @Override
    public void setName(EObject eObject, String name) {

        Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
        Argument currentArgument = (Argument) currentDiagram.getElement();

        // initializes the name attribute.
        NamePropertyAutoCreator nameCreator = new NamePropertyAutoCreator();
        nameCreator.loadDiagram(currentArgument);
        String autoName = nameCreator.getInitialName(eObject);

        super.setName(eObject, autoName);
    }
}
