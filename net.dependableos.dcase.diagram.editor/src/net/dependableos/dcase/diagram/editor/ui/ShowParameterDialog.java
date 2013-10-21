/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.impl.ParameterItem;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A dialog to show parameters.
 */
public class ShowParameterDialog extends Dialog {

	/**
	 * the title.
	 */
	public static final String DIALOG_TITLE = "Show Parameters"; //$NON-NLS-1$

	/**
	 * the horizontal grid number.
	 */
	private static final int GRID_NR = 4;

	/**
	 * the width for each grid.
	 */
	private static final int[] GRID_WIDTHS = { 120, 160, 64, 120 };

	/**
	 * the title string for each grid.
	 */
	private static final String[] TITLE_NAMES = {
			"Name", "Value", "Type", "Node" //$NON-NLS-1$
	};

	/**
	 * the table viewer.
	 */
	private TableViewer viewer;

	/**
	 * 
	 */
	private DcaseNodeEditPart currentEditPart;

	/**
	 * the constructor.
	 */
	public ShowParameterDialog(Shell shell, DcaseNodeEditPart editPart) {
		super(shell);
		this.currentEditPart = editPart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Button createButton(Composite parent, int id, String label,
			boolean defaultButton) {
		// do not create Cancel button
		if (id == IDialogConstants.CANCEL_ID)
			return null;
		return super.createButton(parent, id, label, defaultButton);
	}

	/**
	 * Creates the controls.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite panel = (Composite) super.createDialogArea(parent);
		panel.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.numColumns = GRID_NR;
		panel.setLayout(layout);
		getShell().setText(DIALOG_TITLE);
		viewer = new TableViewer(parent, SWT.FULL_SELECTION);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		for (int i = 0; i < GRID_NR; i++) {
			TableColumn col = new TableColumn(table, SWT.NULL);
			col.setText(TITLE_NAMES[i]);
			col.setWidth(GRID_WIDTHS[i]);
		}
		table.addMouseListener(new MouseListener() {
			// copy name to clipboard.
			public void mouseDown(MouseEvent e) {
				String name = getSelectedParam();
				Clipboard clipboard = new Clipboard(Display.getCurrent());
				clipboard.setContents(new Object[] { name },
						new Transfer[] { TextTransfer.getInstance() });
			}

			// nop.
			public void mouseUp(MouseEvent e) {
			}

			// copy name to clipboard.
			public void mouseDoubleClick(MouseEvent e) {
				String name = "{" + getSelectedParam() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
				Clipboard clipboard = new Clipboard(Display.getCurrent());
				clipboard.setContents(new Object[] { name },
						new Transfer[] { TextTransfer.getInstance() });
			}
		});

		viewer.setLabelProvider(new ParamLabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(getParamItems());

		return panel;
	}

	/**
	 * Returns the parameters info.
	 * 
	 * @return the parameters info.
	 */
	private List<ParamModel> getParamItems() {
		LinkedHashMap<String, ParamModel> map = new LinkedHashMap<String, ParamModel>();
		HashSet<String> uuidSet = new HashSet<String>();
		String moduleName = ModuleUtil.getModuleName(DcaseEditorUtil
				.getCurrentDiagramFile());
		if (currentEditPart != null) {
			getParameter(currentEditPart, moduleName, map, uuidSet);
		} else {
			ArgumentEditPart argumentEditPart = DcaseEditorUtil
					.getCurrentArgumentEditPart();
			getParameter(argumentEditPart, moduleName, map, uuidSet);
		}
		return new ArrayList<ParamModel>(map.values());
	}

	/**
	 * Gets the parameters recursively.
	 * 
	 * @param gPart
	 *            the node or argument edit part.
	 * @param moduleName
	 *            the module name.
	 * @param map
	 *            the parameters.
	 * @param uuidSet
	 *            the uuid set of nodes.
	 */
	private void getParameter(GraphicalEditPart gPart, String moduleName,
			LinkedHashMap<String, ParamModel> map, HashSet<String> uuidSet) {
		if (gPart == null) {
			return;
		}
		// check current node
		BasicNode node = null;
		if (gPart instanceof DcaseNodeEditPart) {
			node = (BasicNode) ((DcaseNodeEditPart) gPart).getElement();
		} else {
			Object model = gPart.getModel();
			if (model instanceof View) {
				EObject eobj = ((View) model).getElement();
				if (eobj instanceof Argument) {
					node = (BasicNode) eobj;
				}
			}
		}
		String uuid = DcaseNodeEditPart.getUUID(node);
		if (uuidSet.contains(uuid)) {
			return;
		}
		uuidSet.add(uuid);

		// add current node parameters
		String userdef007 = node.getUserdef007();
		String userdef009 = node.getUserdef009();
		String nodeName = node.getName();
		String refName;
		if (node instanceof Argument) {
			refName = moduleName;
		} else {
			refName = ModuleUtil.createNodeReference(moduleName, nodeName);
		}
		List<ParameterItem> paramList = ParameterItem
				.getPatameterList(userdef007);
		Map<String, DataTypeVerifier> typeMap = ParameterDataItem
				.getDataTypeVerifierMapFromString(userdef009);
		for (ParameterItem item : paramList) {
			String name = item.getParameterId();
			if (map.containsKey(name)) {
				continue;
			}
			String type = "";
			DataTypeVerifier vf = typeMap.get(name);
			if (vf != null) {
				type = vf.getDataType();
			}
			ParamModel model = new ParamModel(name, item.getParameterValue(),
					type, refName);
			map.put(name, model);
		}

		if (node instanceof Argument) {
			// check parent module
			String userdef011 = node.getUserdef011();
			if (userdef011 == null || userdef011.length() == 0) {
				return;
			}
			for (String anotherName : userdef011.split(ModuleUtil
					.getReferenceSeparator())) {
				String names[] = anotherName.split(ModuleUtil
						.getModuleSeparator());
				if (names.length != 2) {
					continue;
				}
				ArgumentEditPart anotherEditPart = getArgumentEditPart(names[0]);
				if (anotherEditPart == null) {
					continue;
				}
				for (Object nobj : anotherEditPart.getChildren()) {
					if (nobj instanceof DcaseNodeEditPart) {
						BasicNode nnode = (BasicNode) DcaseNodeEditPart
								.getElement((DcaseNodeEditPart) nobj);
						if (nnode.getName().equals(names[1])) {
							getParameter((DcaseNodeEditPart) nobj, names[0],
									map, uuidSet);
						}
					}
				}
			}
		} else {
			// check parent nodes
			List list = ((DcaseNodeEditPart) gPart).getTargetConnections();
			if (list == null || list.size() == 0) {
				ArgumentEditPart argumentEditPart = getArgumentEditPart(moduleName);
				if (argumentEditPart != null) {
					getParameter(argumentEditPart, moduleName, map, uuidSet);
				}
			} else {
				for (Object link : list) {
					DcaseNodeEditPart parentEditPart = (DcaseNodeEditPart) ((DcaseLinkEditPart) link)
							.getSource();
					getParameter(parentEditPart, moduleName, map, uuidSet);
				}
			}
		}
	}

	/**
	 * Returns the argument edit part.
	 * 
	 * @param moduleName
	 *            the module name.
	 * @return the argument edit part.
	 */
	private ArgumentEditPart getArgumentEditPart(String moduleName) {
		IPath modelPath = ModuleUtil.getModelPath(moduleName);
		IPath diagramPath = modelPath.removeFileExtension().addFileExtension(
				ModuleUtil.getDiagramFileExtension());
		IFile diagramFile = FileUtil.getWorksapceFileFromPath(diagramPath
				.toOSString());
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart iEditor = null;
		try {
			iEditor = workbenchPage
					.findEditor(new FileEditorInput(diagramFile));
			if (iEditor == null) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		DiagramEditPart anotherEditPart = ((DcaseDiagramEditor) iEditor)
				.getDiagramEditPart();
		if (anotherEditPart == null) {
			return null;
		} else {
			return (anotherEditPart instanceof ArgumentEditPart) ? ((ArgumentEditPart) anotherEditPart)
					: null;
		}
	}

	/**
	 * A module model.
	 */
	private class ParamModel {
		private String name = null;
		private String value = null;
		private String type = null;
		private String node = null;

		/**
		 * The constructor.
		 * 
		 * @param name
		 *            the module name.
		 * @param node
		 *            the reference node name.
		 * @param links
		 *            the link number.
		 */
		public ParamModel(String name, String value, String type, String node) {
			this.name = name;
			this.value = value;
			this.type = type;
			this.node = node;
		}

		/**
		 * Returns the module name.
		 * 
		 * @return the module name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the value.
		 * 
		 * @return the value.
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Returns the type name.
		 * 
		 * @return the type name.
		 */
		public String getType() {
			return type;
		}

		/**
		 * Returns the node reference name.
		 * 
		 * @return the node reference name.
		 */
		public String getNode() {
			return node;
		}
	}

	/**
	 * A module label provider.
	 */
	private class ParamLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		/**
		 * Returns the properties of module info.
		 */
		public String getColumnText(Object obj, int index) {
			ParamModel model = (ParamModel) obj;
			switch (index) {
			case 0:
				return model.getName();
			case 1:
				return model.getValue();
			case 2:
				return model.getType();
			case 3:
				return model.getNode();
			}
			return "[Undefined]"; //$NON-NLS-1$
		}

		public Image getColumnImage(Object element, int index) {
			return null;
		}
	}

	/**
	 * Returns the selected module name.
	 * 
	 * @return the selected module name.
	 */
	public String getSelectedParam() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		Object obj = sel.getFirstElement();
		if (obj instanceof ParamModel) {
			return ((ParamModel) obj).getName();
		}
		return null;
	}

}
