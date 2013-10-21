package net.dependableos.dcase.diagram.editor.command;

import java.util.Map;
import java.util.Set;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseDelegateNodeEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * A transaction command to change the background color of EditParts.
 */
public class ChangeFillColorEditPartsCommand extends AbstractTransactionalCommand {

    /**
     * the map of new background color.
     */
    private Map<DcaseDelegateNodeEditPart, Color> changeBackgroundColorEditPartMap = null;
    /**
     * the bit shift of blue code.
     */
    private static final int SHIFT_BLUE = 16;
    /**
     * the bit shift of green code.
     */
    private static final int SHIFT_GREEN = 8;

    /**
     * Allocates a ChangeFillColorEditPartCommand object and initialize it.
     * 
     * @param domain the editing domain.
     * @param label the command label.
     * @param changeMap the map of new background color.
     */
    public ChangeFillColorEditPartsCommand(
            TransactionalEditingDomain domain, String label,
            Map<DcaseDelegateNodeEditPart, Color> changeMap) {
        super(domain, label, null);
        changeBackgroundColorEditPartMap = changeMap;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        
        Set<DcaseDelegateNodeEditPart> changeSet = changeBackgroundColorEditPartMap
                .keySet();
        for (DcaseDelegateNodeEditPart editPart : changeSet) {
            if (editPart instanceof DcaseNodeEditPart) {
                View view = (View) ((DcaseNodeEditPart) editPart).getModel();
                Color color = changeBackgroundColorEditPartMap.get(editPart);
                RGB rgb = color.getRGB();
                int c = (rgb.blue << SHIFT_BLUE) | (rgb.green << SHIFT_GREEN) | rgb.red;
                // sets the fill color
                ((FillStyle) view.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(c);
            }
        }
        return CommandResult.newOKCommandResult();
    }
}
