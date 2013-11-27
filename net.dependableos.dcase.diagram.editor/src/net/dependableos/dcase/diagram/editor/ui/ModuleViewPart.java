/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.editor.command.ModuleDeleteAction;
import net.dependableos.dcase.diagram.editor.command.ModuleOpenAction;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * A view part to show Modules.
 */
public class ModuleViewPart extends ViewPart {

	/**
	 * the horizontal grid number.
	 */
	private static final int GRID_NR = 4;

	/**
	 * the width for each grid.
	 */
	private static final int[] GRID_WIDTHS = { 320, 64, 64, 240 };

	/**
	 * the title string for each grid.
	 */
	private static final String[] TITLE_NAMES = {
		net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Label0,
		net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Label1,
		net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Label2,
		net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Label3,
	};

	private static final int NORMAL = 0;
	private static final int CHANGED = 1;
	private static final int DELETED = 2;

	/**
	 * the module infomation list.
	 */
	private Map<String, String> moduleList;

	/**
	 * the table viewer.
	 */
	private TableViewer viewer;

	/**
	 * the current file.
	 */
	private IFile currentFile;

	/**
	 * the file observer.
	 */
	private IFileObserver fileObserver;

	/**
	 * the page listener.
	 */
	private ISelectionListener pageListener;

	/**
	 * the constructor.
	 */
	public ModuleViewPart() {
		super();
	}

	/**
	 * Creates the controls.
	 * 
	 * @param parent
	 *            the parent composite.
	 */
	@Override
	public void createPartControl(Composite parent) {
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
			// nop.
			public void mouseDown(MouseEvent e) {
			}

			// nop.
			public void mouseUp(MouseEvent e) {
			}

			// open module.
			public void mouseDoubleClick(MouseEvent e) {
				String moduleName = getSelectedModuleFile();
				if (!ModuleUtil.isPublicNodeName(moduleName)) {
					ModuleUtil.openModuleEditor(moduleName);
				}
			}
		});

		// register file observer and page listener.
		removeObservers();
		fileObserver = new ModuleFileObserver();
		FileChangeManager.getInstance().addFileObserver(fileObserver);
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		pageListener = new ModuleSelectionListener();
		page.addSelectionListener(pageListener);

		viewer.setLabelProvider(new ModuleLabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(getModuleItems(null, NORMAL));

		registerAction();
	}

	/**
	 * Returns the module info.
	 * 
	 * @return the module info.
	 */
	private List<ModuleModel> getModuleItems(IFile file, int type) {
		List<ModuleModel> input = new ArrayList<ModuleModel>();
		switch (type) {
		case DELETED:
			Object orgInput = viewer.getInput();
			String delName = ModuleUtil.getModuleName(file);
			if (orgInput instanceof List) {
				for (ModuleModel elem : (List<ModuleModel>) orgInput) {
					if (!delName
							.equals(ModuleUtil.getModuleName(elem.getName()))) {
						input.add(elem);
					}
				}
			}
			return input;
		case CHANGED:
			if (isSameProject(currentFile, file)) {
				break;
			}
			// fall-through...
		case NORMAL:
			try {
				if (file != null) {
					moduleList = ModuleUtil.getModulesAndNodes(file, true);
				} else {
					ArgumentEditPart argumentEditPart = DcaseEditorUtil
							.getCurrentArgumentEditPart();
					moduleList = ModuleUtil.getModulesAndNodes(
							argumentEditPart, true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				MessageWriter.writeMessageToConsole(Messages.ModuleList,
						MessageTypeImpl.MODULE_INFO_GET_FAILED);
				return input;
			}
			break;
		}

		for (Map.Entry<String, String> module : moduleList.entrySet()) {
			input.add(new ModuleModel(module.getKey(), module.getValue(),
					ModuleUtil.getReferenceNumber(module.getValue()),
					ModuleUtil.countNodes(module.getKey())));
		}
		currentFile = file;

		return input;
	}

	private boolean isSameProject(IFile file1, IFile file2) {
		if (file1 == null || file2 == null) {
			return false;
		}
		return (file1.getProject() == file2.getProject());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		removeObservers();
	}

	/**
	 * Removes file observer and page listener.
	 */
	private void removeObservers() {
		if (fileObserver != null) {
			FileChangeManager.getInstance().removeFileObserver(fileObserver);
			fileObserver = null;
		}
		if (pageListener != null) {
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				page.removeSelectionListener(pageListener);
			} catch (NullPointerException e) {
				// don't care
			}
			pageListener = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Registers the actions.
	 */
	private void registerAction() {
		IActionBars bars = getViewSite().getActionBars();

		IAction openAction = new ModuleOpenAction(this);
		openAction.setText(net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Title0);
		openAction.setToolTipText(net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Tooltip0);
		openAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
		bars.getToolBarManager().add(openAction);
		bars.getMenuManager().add(openAction);

		IAction deleteAction = new ModuleDeleteAction(this);
		deleteAction.setText(net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Title1);
		deleteAction.setToolTipText(net.dependableos.dcase.diagram.editor.message.Messages.ModuleView_Tooltip1);
		deleteAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		bars.getToolBarManager().add(deleteAction);
		bars.getMenuManager().add(deleteAction);
	}

	/**
	 * A module model.
	 */
	private class ModuleModel {
		private String name = null;
		private String node = null;
		private int links = 0;
		private int nodes = 0;

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
		public ModuleModel(String name, String node, int links, int nodes) {
			this.name = name;
			this.node = node;
			this.links = links;
			this.nodes = nodes;
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
		 * Returns the node reference name.
		 * 
		 * @return the node reference name.
		 */
		public String getNode() {
			return node;
		}

		/**
		 * Returns the link number.
		 * 
		 * @return the link number.
		 */
		public String getLinks() {
			return "" + links;
		}

		/**
		 * Returns the node number.
		 * 
		 * @return the node number.
		 */
		public String getNodes() {
			return (nodes != 0) ? ("" + nodes) : "";
		}
	}

	/**
	 * A module label provider.
	 */
	private class ModuleLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		/**
		 * Returns the properties of module info.
		 */
		public String getColumnText(Object obj, int index) {
			ModuleModel model = (ModuleModel) obj;
			switch (index) {
			case 0:
				return model.getName();
			case 1:
				return model.getNodes();
			case 2:
				return model.getLinks();
			case 3:
				return model.getNode();
			}
			return "[Undefined]"; //$NON-NLS-1$
		}

		/**
		 * Returns the icon image.
		 */
		public Image getColumnImage(Object obj, int index) {
			if (index == 0) {
				ModuleModel model = (ModuleModel) obj;
				if (ModuleUtil.isPublicNodeName(model.getName())) {
					return null;
				}
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(ISharedImages.IMG_OBJ_ELEMENT);
			}
			return null;
		}
	}

	/**
	 * Returns the selected module name.
	 * 
	 * @return the selected module name.
	 */
	public String getSelectedModuleFile() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		Object obj = sel.getFirstElement();
		if (obj instanceof ModuleModel) {
			return ((ModuleModel) obj).getName();
		}
		return null;
	}

	/**
	 * File Observer class for detecting changes.
	 */
	private class ModuleFileObserver implements IFileObserver {
		public ModuleFileObserver() {
		}

		public void handleFileRenamed(IFile oldFile, IFile file) {
			commonFileHandler(oldFile, false);
			commonFileHandler(file, true);
		}

		public void handleFileMoved(IFile oldFile, IFile file) {
			commonFileHandler(oldFile, false);
			commonFileHandler(file, true);
		}

		public void handleFileDeleted(IFile file) {
			commonFileHandler(file, false);
		}

		public void handleFileChanged(IFile file) {
			commonFileHandler(file, true);
		}

		public void handleMarkerAdded(IMarker marker) {
			commonMarkerHandler(marker);
		}

		public void handleMarkerDeleted(IMarker marker, Map attributes) {
			commonMarkerHandler(marker);
		}

		public void handleMarkerChanged(IMarker marker) {
			commonMarkerHandler(marker);
		}

		private void commonFileHandler(final IFile file, boolean isNormal) {
			if (ModuleUtil.isModelFile(file)) {
				if (isNormal) {
					viewer.setInput(getModuleItems(file, NORMAL));
					viewer.refresh();
				} else {
					IWorkbench workbench = PlatformUI.getWorkbench();
					if (workbench != null) {
						IWorkbenchWindow[] windows = workbench
								.getWorkbenchWindows();
						if (windows != null && windows.length > 0) {
							IWorkbenchPage workbenchPage = windows[0]
									.getActivePage();
							IEditorPart editorPart = workbenchPage
									.getActiveEditor();
							if (editorPart instanceof DcaseDiagramEditor) {
								Diagram diagram = ((DcaseDiagramEditor) editorPart)
										.getDiagram();
								TransactionalEditingDomain domain = GMFEditingDomainFactory.INSTANCE
										.getEditingDomain(diagram.eResource()
												.getResourceSet());
								domain.getCommandStack().execute(
										new RecordingCommand(domain) {
											public void doExecute() {
												Display.getDefault().asyncExec(
														new Runnable() {
															public void run() {
																List<ModuleModel> mlist = getModuleItems(
																		file,
																		DELETED);
																viewer.setInput(mlist);
																viewer.refresh();
															}
														});
											}
										});
							}
						}
					}
				}
			}
		}

		private void commonMarkerHandler(IMarker marker) {
			// do nothing...
		}
	}

	/**
	 * Page Selection Listener class for detecting current diagram.
	 */
	private class ModuleSelectionListener implements ISelectionListener {
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (!(part instanceof DcaseDiagramEditor)) {
				return;
			}
			if (selection == null || selection.isEmpty()) {
				return;
			}
			if (!(selection instanceof IStructuredSelection)) {
				return;
			}

			Object eobj = ((IStructuredSelection) selection).toArray()[0];
			if (!(eobj instanceof ArgumentEditPart)) {
				return;
			}
			Object aobj = DcaseEditorUtil.getElement((ArgumentEditPart) eobj);
			if (aobj instanceof Argument) {
				if (((Argument) aobj).eResource() == null) {
					return;
				}
				URI uri = ((Argument) aobj).eResource().getURI();
				IFile file = ResourcesPlugin.getWorkspace().getRoot()
						.getFile(new Path(uri.toPlatformString(false)));
				viewer.setInput(getModuleItems(file, CHANGED));
				viewer.refresh();
			}
		}
	}
}
