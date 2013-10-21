package net.dependableos.dcase.diagram.edit.parts.custom;

import net.dependableos.dcase.BasicNode;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.notation.View;

public class DcaseNodeDescEditPart extends CompartmentEditPart {

    /**
     * Allocates a DcaseNodeEditPart object.
     * 
     * @param view owned view by this edit part 
     */
    public DcaseNodeDescEditPart(View view) {
        super(view);
    }

    /**
     * Returns true if the EditPart is editable. 
     * Editparts are editable after enableEditMode()is called, and until disableEditMode()is called.
     * Returns false if Desc Format String attribute is valid.
     * 
     * @generated NOT
     * @return boolean true when editable.
     */
    @Override
    public boolean isEditModeEnabled() {
        boolean isEditModeEnabled = super.isEditModeEnabled();
        if (isEditModeEnabled) {
            View view = (View) getModel();
            if (view != null) {
                BasicNode basicNode = (BasicNode) view.getElement();
                if (basicNode.getUserdef005() != null && basicNode.getUserdef005().trim().length() > 0) {
                    return false;
                }
            }
        }
        return isEditModeEnabled;
    }
}
