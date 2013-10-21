package net.dependableos.dcase.diagram.editor.command;

import static org.eclipse.swt.SWT.PUSH;

import java.util.List;

import net.dependableos.dcase.diagram.editor.logic.xmlconv.XSLConverterItem;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class XslTransfromContributionItem extends ContributionItem {
    /**
     * Constructor for the class.
     * Creates a ScenarioSelectContributionItem.
     */
    public XslTransfromContributionItem() {
    }

    /**
     * Constructor for the class.
     * Creates a ScenarioSelectContributionItem and initialize it.
     * 
     * @param id the contribution item identifier, or null.
     */
    public XslTransfromContributionItem(String id) {
        super(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(Menu menu, int index) {
        List<XSLConverterItem> configurations = XSLConverterItem
                .getConverters();

        for (XSLConverterItem config : configurations) {
            MenuItem item = new MenuItem(menu, PUSH);
            item.setText(config.getName());
            XslConverterSelectionAdapter adapter = new XslConverterSelectionAdapter();
            adapter.setConverter(config);
            item.addSelectionListener(adapter);
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDynamic() {
        return true;
    }
}
