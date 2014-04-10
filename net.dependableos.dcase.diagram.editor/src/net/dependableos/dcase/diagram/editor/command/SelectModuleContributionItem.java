/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static org.eclipse.swt.SWT.PUSH;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.Goal;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.Userdef001;
import net.dependableos.dcase.Userdef005;
import net.dependableos.dcase.diagram.common.command.ChangeBasicNodePropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.command.ChangeBasicLinkPropertyTransactionCommand;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.model.AttributeType;
import net.dependableos.dcase.diagram.common.util.FileUtil;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.DcaseLink003Userdef001DescUserdef00EditPart;
import net.dependableos.dcase.diagram.edit.parts.GoalEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseLinkEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;
import net.dependableos.dcase.diagram.editor.ui.NewModuleInputDialog;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditor;
import net.dependableos.dcase.diagram.part.DcaseDiagramEditorUtil;
import net.dependableos.dcase.diagram.part.PatternUtil;
import net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector;
import net.dependableos.dcase.impl.ArgumentImpl;
import net.dependableos.dcase.impl.ParameterItem;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class SelectModuleContributionItem extends ContributionItem implements
		IAttachmentSelector {

	/**
	 * the Labels for executing commands.
	 */
	private static final String SET_ATTACHMENT_CMD_LABEL = "command for setting Attachment"; //$NON-NLS-1$
	private static final String SET_USERDEF005_CMD_LABEL = "command for setting Userdef005"; //$NON-NLS-1$
	private static final String SET_USERDEF011_CMD_LABEL = "command for setting Userdef011"; //$NON-NLS-1$
	private static final String SET_PUBLICFLAG_CMD_LABEL = "command for setting Flags to Public"; //$NON-NLS-1$

	/**
	 * the current Argument edit part.
	 */
	private ArgumentEditPart argumentEditPart;

	/**
	 * the selected edit part.
	 */
	private DcaseNodeEditPart selectedEditPart;
	private DcaseLinkEditPart selectedLinkEditPart;

	/**
	 * the attachment selector name.
	 */
	private String name;

	/**
	 * the menu name for New...
	 */
	private String newName = "New..."; //$NON-NLS-1$

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem.
	 */
	public SelectModuleContributionItem() {
	}

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem and
	 * initialize it.
	 * 
	 * @param id
	 *            the contribution item identifier, or null.
	 */
	public SelectModuleContributionItem(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Menu menu, int index) {
		fill(menu, index, false);
	}

	public void fill(Menu menu, int index, boolean isCopy) {
		argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart();
		selectedEditPart = DcaseEditorUtil.getFirstCurrentSelectedPart();
		boolean isGoalNode = (selectedEditPart instanceof GoalEditPart);
		// get current module name
		IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
		String moduleName = ModuleUtil.getModuleName(modelFile);

		// get list
		Map<String, String> map = null;
		if (selectedEditPart == null) {
			selectedLinkEditPart = DcaseEditorUtil
					.getFirstCurrentSelectedLink();
		}
		try {
			map = ModuleUtil.getModulesAndNodes(argumentEditPart, isGoalNode,
					isGoalNode ? null : moduleName);
		} catch (CoreException e) {
			MessageWriter.writeMessageToConsole(
					Messages.SelectModuleContributionItem_0,
					MessageTypeImpl.SELECT_MODULE_FAILED);
			return;
		}

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String name = entry.getKey();
			if (moduleName.equals(ModuleUtil.getModuleName(name))) {
				continue;
			}
			if (isGoalNode && !ModuleUtil.isPublicNodeName(name)) {
				continue;
			}
			MenuItem item = new MenuItem(menu, PUSH);
			item.setText(name);
			SelectModuleSelectionAdapter adapter = new SelectModuleSelectionAdapter(
					isCopy);
			adapter.setEntry(name);
			item.addSelectionListener(adapter);
		}

		// add New...
		if (!isGoalNode && !isCopy) {
			boolean newLoop = true;
			while (newLoop) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String name = entry.getKey();
					if (moduleName.equals(ModuleUtil.getModuleName(name))) {
						continue;
					}
					if (ModuleUtil.isPublicNodeName(name)) {
						continue;
					}
					if (name.equals(newName)) {
						newName += "."; //$NON-NLS-1$
						break;
					}
				}
				newLoop = false; // duplicate check done.
			}
			MenuItem item = new MenuItem(menu, PUSH);
			item.setText(newName);
			SelectModuleSelectionAdapter adapter = new SelectModuleSelectionAdapter(
					isCopy);
			adapter.setEntry(newName);
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

	/**
	 * Adapter class for Selecting from Modules.
	 */
	private class SelectModuleSelectionAdapter extends SelectionAdapter {
		/**
		 * the Labels for executing commands.
		 */
		private final String ADD_MODULE_CMD_LABEL = "command for adding module"; //$NON-NLS-1$
		private final String ADD_SUBTREE_CMD_LABEL = "command for adding sub-tree"; //$NON-NLS-1$
		private final String SELECT_SUBTREE_CMD_LABEL = "command for selecting sub-tree"; //$NON-NLS-1$
		private final String ARRANGE_SUBTREE_CMD_LABEL = "command for arranging sub-tree"; //$NON-NLS-1$
		private final String PERSIST_SUBTREE_CMD_LABEL = "command for persisting sub-tree"; //$NON-NLS-1$
		private final String MOVE_CMD_LABEL = "command for moving module, etc..."; //$NON-NLS-1$

		/**
		 * The referenced module or public node.
		 */
		private String entryName;

		/**
		 * Whether copy or reference.
		 */
		private boolean isCopy;

		private static final String CREATE_DIAGRAM_CMD_LABEL = "command for creating Diagram"; //$NON-NLS-1$

		public SelectModuleSelectionAdapter(boolean isCopy) {
			this.isCopy = isCopy;
		}

		/**
		 * Sets the selected module of public node.
		 * 
		 * @param entry
		 */
		public void setEntry(String entryName) {
			this.entryName = entryName;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {
			Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
			TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
					.getEditingDomain(currentDiagram.eResource()
							.getResourceSet());
			Resource diagramResource = currentDiagram.eResource();
			final boolean newFlag = entryName.equals(newName);
			boolean isModuleNode = false;

			// get selected node or link
			BasicNode selectedNode = null;
			BasicLink selectedLink = null;
			EObject eobj = DcaseEditorUtil.getElement(selectedEditPart);
			if (eobj == null) {
				eobj = DcaseEditorUtil.getElement(selectedLinkEditPart);
			}
			if (eobj instanceof BasicNode) {
				selectedNode = (BasicNode) eobj;
				isModuleNode = (selectedNode instanceof Userdef005 || selectedNode instanceof Userdef001);
			} else if (eobj instanceof BasicLink) {
				selectedLink = (BasicLink) eobj;
			} else {
				DcaseDiagramEditor editor = DcaseEditorUtil.getCurrentDcaseEditor();
				Object obj = editor.getSite().getPage().getSelection();
				if (obj instanceof StructuredSelection) {
					Object sobj = ((StructuredSelection) obj).getFirstElement();
					if (sobj instanceof DcaseLink003Userdef001DescUserdef00EditPart) {
						DcaseLink003Userdef001DescUserdef00EditPart dobj =
								(DcaseLink003Userdef001DescUserdef00EditPart)sobj;
						selectedLinkEditPart = (DcaseLinkEditPart)dobj.getParent();
						eobj = DcaseEditorUtil.getElement(selectedLinkEditPart);
						selectedLink = (BasicLink) eobj;
					}
				}
				if (selectedLink == null) {
					MessageWriter.writeMessageToConsole(
							Messages.SelectModuleContributionItem_1,
							MessageTypeImpl.SELECT_MODULE_FAILED);
					return;
				}
			}

			// get responsibility
			String respName = newFlag ? "":null; //$NON-NLS-1$
			if (!newFlag && selectedNode != null) {
				respName = getRespName(entryName);
			}
			String respAddr = newFlag ? "":null; //$NON-NLS-1$
			if (!newFlag && selectedNode != null) {
				respAddr = getRespAddress(entryName);
			}
			String respIcon = newFlag ? "":null; //$NON-NLS-1$
			if (!newFlag && selectedNode != null) {
				respIcon = getRespIcon(entryName);
			}
			String respTime = newFlag ? "":null; //$NON-NLS-1$
			if (!newFlag && selectedNode != null) {
				respTime = getRespTime(entryName);
			}

			// *** Copy Subtree
			if (!isModuleNode && isCopy) {
				ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
						.getCurrentArgumentEditPart();
				Argument currentArgument = (Argument) DcaseEditorUtil
						.getElement(currentArgumentEditPart);
				Point currentPoint = selectedEditPart.getLocation();
				Dimension dimension = selectedEditPart.getSize();
				currentPoint.x += dimension.width / 2;
				currentPoint.y += dimension.height * 2;
				CreateModuleHandler subtreeHandler = new CreateModuleHandler();
				subtreeHandler.initDatas();
				String moduleName = ModuleUtil.getModuleName(entryName);
				ModuleUtil.openModuleEditor(moduleName);
				ArgumentEditPart moduleArgumentEditPart = DcaseEditorUtil
						.getCurrentArgumentEditPart();
				openDiagram(diagramResource);
				GraphicalEditPart nodeEditPart = getNodeEditPart(
						moduleArgumentEditPart, entryName, true);
				if (subtreeHandler.selectSubtree(nodeEditPart, nodeEditPart)) {
					Set<GraphicalEditPart> nodeEditPartSet = subtreeHandler
							.getSelectedNodeEditParts();
					Set<ConnectionNodeEditPart> linkEditPartSet = subtreeHandler
							.getSelectedLinkEditParts();
					ArgumentImpl addedArgument = (ArgumentImpl) DcaseFactory.eINSTANCE
							.createArgument();
					Set<String> idSet = DcaseEditorUtil
							.getChildUUIDs(currentArgumentEditPart);
					// add links to argument
					for (Iterator<ConnectionNodeEditPart> it = linkEditPartSet
							.iterator(); it.hasNext();) {
						ConnectionNodeEditPart link = it.next();
						Object modelObj = DcaseEditorUtil.getElement(link);
						if (modelObj instanceof BasicLink) {
							EObject cpLink = EcoreUtil.copy((EObject) modelObj);
							addedArgument.getRootBasicLink().add(
									(BasicLink) cpLink);
						}
					}
					// add nodes to argument
					HashMap<GraphicalEditPart, BasicNode> copyNodeMap = new HashMap<GraphicalEditPart, BasicNode>();
					for (Iterator<GraphicalEditPart> it = nodeEditPartSet
							.iterator(); it.hasNext();) {
						GraphicalEditPart node = it.next();
						Object modelObj = DcaseEditorUtil.getElement(node);
						// rewrite link source and target
						if (modelObj instanceof BasicNode) {
							EObject cpModel = EcoreUtil
									.copy((EObject) modelObj);
							((BasicNode) cpModel)
									.setName(((BasicNode) modelObj).getName());
							addedArgument.getRootBasicNode().add(
									(BasicNode) cpModel);
							copyNodeMap.put(node, (BasicNode) cpModel);
							for (BasicLink addLink : addedArgument
									.getRootBasicLink()) {
								if (addLink.getSource() == (BasicNode) modelObj) {
									addLink.setSource((BasicNode) cpModel);
								}
								if (addLink.getTarget() == (BasicNode) modelObj) {
									addLink.setTarget((BasicNode) cpModel);
								}
							}
						}
					}
					// add link
					BasicNode topNode = ModuleUtil
							.getRootElement(addedArgument);
					BasicLink link = DcaseFactory.eINSTANCE
							.createDcaseLink001();
					link.setSource((BasicNode) DcaseEditorUtil
							.getElement(selectedEditPart));
					link.setTarget(topNode);
					addedArgument.getRootBasicLink().add(link);
					// execute commands
					CompoundCommand addCmd = new CompoundCommand(
							ADD_MODULE_CMD_LABEL);
					ICommand additionCommand = new ModelAdditionCommand(
							currentDomain, ADD_SUBTREE_CMD_LABEL, null,
							addedArgument, currentArgument, false);
					addCmd.add(new ICommandProxy(additionCommand));
					// select, arrange and persistence command
					ICommand selectCommand = new SelectExcludesCommand(
							SELECT_SUBTREE_CMD_LABEL, currentArgumentEditPart,
							idSet);
					addCmd.add(new ICommandProxy(selectCommand));
					// move
					MoveSelectedCommand moveCommand = new MoveSelectedCommand(
							currentDomain, MOVE_CMD_LABEL, currentPoint);
					addCmd.add(new ICommandProxy(moveCommand));
					ICommand arrangeCommand = new ArrangeExcludesCommand(
							currentDomain, ARRANGE_SUBTREE_CMD_LABEL, null,
							currentArgumentEditPart, idSet);
					addCmd.add(new ICommandProxy(arrangeCommand));
					ICommand persistanceCommand = new NotationPersistanceCommand(
							currentDomain, PERSIST_SUBTREE_CMD_LABEL, null,
							currentArgumentEditPart);
					addCmd.add(new ICommandProxy(persistanceCommand));
					// execute
					currentArgumentEditPart.getDiagramEditDomain()
							.getDiagramCommandStack().execute(addCmd);
					// replace unknown parameters
					replaceUnknownParameters(currentArgumentEditPart,
							copyNodeMap);
					return;
				}
			}

			// *** Create or Copy Module
			if (newFlag || isCopy) {
				// get module name
				IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
				IFile newDiagramFile = null;
				IFile newModelFile = null;
				String moduleName = null;
				String modulePath = null;
				do {
					NewModuleInputDialog dialog = new NewModuleInputDialog(
							DcaseEditorUtil.getActiveWindowShell(),
							(moduleName == null) ? net.dependableos.dcase.diagram.editor.message.Messages.CreateModuleDialog_1
									: net.dependableos.dcase.diagram.editor.message.Messages.CreateModuleDialog_2);
					if (dialog.open() != Dialog.OK) {
						return;
					}
					moduleName = dialog.getInputedFilename();
					IPath iPath = modelFile.getFullPath().removeFileExtension().addTrailingSeparator().append(moduleName);
					modulePath = PatternUtil.getModuleName(iPath);
					newDiagramFile = ResourcesPlugin.getWorkspace().getRoot()
							.getFile(ModuleUtil.getDiagramPath(modulePath));
					newModelFile = ResourcesPlugin.getWorkspace().getRoot()
							.getFile(ModuleUtil.getModelPath(modulePath));
				} while (newDiagramFile.exists() || newModelFile.exists());

				final IPath newDiagramPath = newDiagramFile.getFullPath();
				final IFile newModelFileFixed = newModelFile;
				// create diagram
				if (newFlag) {
					AbstractTransactionalCommand createCmd = new AbstractTransactionalCommand(
							currentDomain, CREATE_DIAGRAM_CMD_LABEL,
							Collections.EMPTY_LIST) {
						protected CommandResult doExecuteWithResult(
								IProgressMonitor monitor, IAdaptable info)
								throws ExecutionException {
							// create child's folder
							IFolder newFolder = (IFolder)newModelFileFixed.getParent();
							if (! newFolder.exists()) {
								try {
									newFolder.create(true, true, null);
								} catch (CoreException e) {
									return CommandResult.newErrorCommandResult(e.getMessage());
								}
							}
							// create or get diagram
							URI diagramURI = URI.createPlatformResourceURI(
									newDiagramPath.toOSString(), false);
							URI modelURI = URI.createPlatformResourceURI(
									newModelFileFixed.getFullPath().toOSString(), false);
							Resource diagram = DcaseDiagramEditorUtil
									.createDiagram(diagramURI, modelURI,
											monitor);
							// open diagram
							try {
								DcaseDiagramEditorUtil.openDiagram(diagram);
							} catch (Exception e) {
								MessageWriter
										.writeMessageToConsole(
												Messages.CreateModuleHandler_2,
												MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
								throw new DcaseRuntimeException(
										Messages.CreateModuleHandler_2,
										null,
										null,
										0,
										MessageTypeImpl.MODULE_FILE_CREATE_FAILED);
							}
							return CommandResult.newOKCommandResult();
						}
					};
					try {
						OperationHistoryFactory.getOperationHistory().execute(
								createCmd, new NullProgressMonitor(), null);
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				} else {
					// copy files
					IFile oldDiagramFile = ResourcesPlugin.getWorkspace()
							.getRoot()
							.getFile(ModuleUtil.getDiagramPath(entryName));
					IFile oldModelFile = ResourcesPlugin.getWorkspace()
							.getRoot()
							.getFile(ModuleUtil.getModelPath(entryName));
					FileUtil.copyFileTo(oldDiagramFile, newDiagramPath);
					FileUtil.copyFileTo(oldModelFile, newModelFileFixed.getFullPath());
					ModelUtil.updateModelFileReference(oldDiagramFile, newModelFile, newDiagramFile, false);
				}
				entryName = modulePath;
			}

			// set reference to Attachment
			String oldName;
			if (selectedNode != null) {
				oldName = selectedNode.getAttachment();
			} else {
				oldName = selectedLink.getAttachment();
				entryName += ModuleUtil.getContractIconString(
						selectedLink.getSource(), selectedLink.getTarget());
			}
			Map<AttributeType, Object> attrNewMap = new HashMap<AttributeType, Object>();
			attrNewMap.put(AttributeType.ATTACHMENT, entryName);
			ICommand setAttachmentCommand;
			if (selectedNode != null) {
				attrNewMap.put(AttributeType.RESPNAME, respName);
				attrNewMap.put(AttributeType.RESPADDRESS, respAddr);
				attrNewMap.put(AttributeType.RESPICON, respIcon);
				attrNewMap.put(AttributeType.RESPTIME, respTime);
				setAttachmentCommand = new ChangeBasicNodePropertyTransactionCommand(
						currentDomain, SET_ATTACHMENT_CMD_LABEL, null,
						selectedNode, attrNewMap);
			} else {
				setAttachmentCommand = new ChangeBasicLinkPropertyTransactionCommand(
						currentDomain, SET_ATTACHMENT_CMD_LABEL, null,
						selectedLink, attrNewMap);
			}
			argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(new ICommandProxy(setAttachmentCommand));

			// set reference to RefSource
			if (selectedNode != null) {
				if (oldName != null && oldName.length() > 0
						&& !ModuleUtil.isWorkspaceReference(oldName)
						&& !ModuleUtil.isUrl(oldName)) {
					setRefSource(argumentEditPart, selectedEditPart, oldName,
							false);
				}
				setRefSourceAndName(argumentEditPart, selectedEditPart,
						entryName, true, isCopy);
			}

			if (!newFlag) {
				openDiagram(diagramResource);
			} else {
				setPublicFlag(DcaseEditorUtil.getCurrentArgumentEditPart());
				IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorPart editorPart = (IEditorPart) DcaseEditorUtil
						.getCurrentDcaseEditor();
				workbenchPage.saveEditor(editorPart, false);
			}
		}
	}

	/**
	 * Open diagram.
	 * 
	 * @param diagramResource
	 *            the diagram resource.
	 */
	private void openDiagram(Resource diagramResource) {
		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (PartInitException e) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_3,
					MessageTypeImpl.SELECT_MODULE_FAILED);
			throw new DcaseRuntimeException(Messages.CreateModuleHandler_3,
					null, null, 0, MessageTypeImpl.SELECT_MODULE_FAILED);
		}
	}

	/**
	 * Sets RefSource to target node.
	 * 
	 * @param editPart
	 *            the attaching edit part.
	 * @param isAppend
	 *            whether append or remove.
	 */
	private static void setRefSource(ArgumentEditPart currentArgumentEditPart,
			DcaseNodeEditPart editPart, String attachment, boolean isAppend) {
		setRefSourceAndName(currentArgumentEditPart, editPart, attachment,
				isAppend, false);
	}

	/**
	 * Sets RefSource to target node.
	 * 
	 * @param editPart
	 *            the attaching edit part.
	 * @param isAppend
	 *            whether append or remove.
	 * @param isCopy
	 *            whether copy or not.
	 */
	private static void setRefSourceAndName(
			ArgumentEditPart currentArgumentEditPart,
			DcaseNodeEditPart editPart, String attachment, boolean isAppend,
			boolean isCopy) {
		String myName = ModuleUtil.getAttributeValue(editPart,
				AttributeType.NAME);
		String moduleName = ModuleUtil.getModuleName(attachment);
		ModuleUtil.openModuleEditor(moduleName);
		ArgumentEditPart moduleArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		GraphicalEditPart nodeEditPart = getNodeEditPart(
				moduleArgumentEditPart, attachment, true);
		BasicNode node = null;
		EObject nobj = DcaseEditorUtil.getElement(nodeEditPart);
		if (nobj instanceof BasicNode) {
			node = (BasicNode) nobj;
		} else {
			MessageWriter.writeMessageToConsole(
					Messages.SelectModuleContributionItem_1,
					MessageTypeImpl.SELECT_MODULE_FAILED);
		}
		Diagram moduleDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain moduleDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(moduleDiagram.eResource().getResourceSet());
		IFile modelFile = DcaseEditorUtil.getModelFile(currentArgumentEditPart);
		String refStr = ModuleUtil.createNodeReference(modelFile, myName);
		String newAttrStr = isCopy ? refStr : (isAppend ? ModuleUtil
				.appendModuleReference(nodeEditPart, refStr) : ModuleUtil
				.removeModuleReference(nodeEditPart, refStr));
		Map<AttributeType, Object> attrAddMap = new HashMap<AttributeType, Object>();
		attrAddMap.put(AttributeType.REFSOURCE, newAttrStr);
		if (isCopy) {
			attrAddMap.put(AttributeType.NAME, PatternUtil.getModuleBaseName(moduleName));
		}
		ICommand setUserdef011Command = new ChangeBasicNodePropertyTransactionCommand(
				moduleDomain, SET_USERDEF011_CMD_LABEL, null, node, attrAddMap);
		nodeEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(new ICommandProxy(setUserdef011Command));
		ModuleUtil.saveModuleEditor(moduleName);
	}

	/**
	 * Sets Flag to argument.
	 * 
	 * @param argumentEditPart
	 *            the argument EditPart.
	 */
	private static void setPublicFlag(ArgumentEditPart argumentEditPart) {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(currentDiagram.eResource().getResourceSet());
		EObject aobj = DcaseEditorUtil.getElement(argumentEditPart);
		Map<AttributeType, Object> attrAddMap = new HashMap<AttributeType, Object>();
		attrAddMap.put(AttributeType.FLAG,
				ModuleUtil.getPublicFlagString());
		ICommand command = new ChangeBasicNodePropertyTransactionCommand(
				currentDomain, SET_PUBLICFLAG_CMD_LABEL, null, (Argument) aobj,
				attrAddMap);
		argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
				.execute(new ICommandProxy(command));
	}

	/**
	 * Returns the node edit part.
	 * 
	 * @param editPart
	 *            the Argument edit part.
	 * @param name
	 *            the node name.
	 * @return the node edit part.
	 */
	private static GraphicalEditPart getNodeEditPart(ArgumentEditPart editPart,
			String name, boolean isAttachment) {
		// if name is not a public node name
		if (isAttachment && !ModuleUtil.isPublicNodeName(name)) {
			return editPart;
		}
		String nodeName = ModuleUtil.getNodeName(name);
		if (nodeName == null) {
			return null;
		}
		for (Object obj : editPart.getChildren()) {
			if (obj instanceof DcaseNodeEditPart) {
				DcaseNodeEditPart nodeEditPart = (DcaseNodeEditPart) obj;
				if (nodeName.equals(ModuleUtil.getAttributeValue(nodeEditPart,
						AttributeType.NAME))) {
					return nodeEditPart;
				}
			}
		}
		return null;
	}

	/**
	 * Replace unknown parameters with values.
	 * 
	 * @param argumentEditPart
	 *            the argument edit part.
	 * @param nodeMap
	 *            the nodes of subtree.
	 */
	private void replaceUnknownParameters(ArgumentEditPart argumentEditPart,
			Map<GraphicalEditPart, BasicNode> nodeMap) {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(currentDiagram.eResource().getResourceSet());

		for (Map.Entry<GraphicalEditPart, BasicNode> entry : nodeMap.entrySet()) {
			GraphicalEditPart orgEditPart = entry.getKey();
			BasicNode newNode = entry.getValue();
			GraphicalEditPart newEditPart = getNodeEditPart(argumentEditPart,
					newNode.getName(), false);
			if (newEditPart == null) {
				continue;
			}
			if (!(orgEditPart instanceof DcaseNodeEditPart)
					|| !(newEditPart instanceof DcaseNodeEditPart)) {
				continue;
			}
			String formatter = newNode.getParameterizedDesc();
			if (formatter == null || formatter.length() == 0) {
				continue;
			}
			String orgParameters = ((DcaseNodeEditPart) orgEditPart)
					.getParameters(null);
			String newParameters = ((DcaseNodeEditPart) newEditPart)
					.getParameters(null);
			String unknownParameters = selectUnknownParameters(orgParameters,
					newParameters);
			String newFormattedDesc = ParameterItem.getFormattedDesc(
					unknownParameters, formatter);
			Map<AttributeType, Object> attrAddMap = new HashMap<AttributeType, Object>();
			attrAddMap.put(AttributeType.PARAMETERIZEDDESC, newFormattedDesc);
			ICommand command = new ChangeBasicNodePropertyTransactionCommand(
					currentDomain, SET_USERDEF005_CMD_LABEL, null, newNode,
					attrAddMap);
			newEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(new ICommandProxy(command));
		}
	}

	/**
	 * Returns the unknown parameters.
	 * 
	 * @param orgParameters
	 *            the original parameters.
	 * @param newParameters
	 *            the new parameters.
	 * @return the unknown parameters.
	 */
	private String selectUnknownParameters(String orgParameters,
			String newParameters) {
		List<ParameterItem> orgList = ParameterItem
				.getPatameterList(orgParameters);
		List<ParameterItem> newList = ParameterItem
				.getPatameterList(newParameters);
		List<ParameterItem> cpyList = new ArrayList<ParameterItem>(orgList);
		for (ParameterItem orgItem : orgList) {
			String orgId = orgItem.getParameterId();
			for (ParameterItem newItem : newList) {
				if (orgId.equals(newItem.getParameterId())) {
					cpyList.remove(orgItem);
				}
			}
		}
		return ParameterItem.getSavedString(cpyList);
	}

	/**
	 * Selects the attachment.
	 * 
	 * @param parent
	 *            the parent shell.
	 * @param currentAttachment
	 *            the original attachment.
	 * @param basicNode
	 *            the node.
	 * @return the selected attachment.
	 * 
	 * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#selectAttachment(java.lang.String)
	 */
	public String selectAttachment(Shell parent, String currentAttachment,
			BasicNode basicNode) {
		if (basicNode instanceof Goal || basicNode instanceof Userdef005 ||
				basicNode instanceof Userdef001) {
			ListDialog dialog = new ListDialog(parent);
			dialog.setTitle("Select from Modules..."); //$NON-NLS-1$
			dialog.setMessage("Select from the following Modules..."); //$NON-NLS-1$
			dialog.setContentProvider(new ArrayContentProvider());
			dialog.setLabelProvider(new LabelProvider());

			// get data
			argumentEditPart = DcaseEditorUtil.getCurrentArgumentEditPart();
			boolean isGoalNode = (basicNode instanceof Goal);
			ArrayList<String> data = new ArrayList<String>();
			Map<String, String> map = null;
			try {
				map = ModuleUtil.getModulesAndNodes(argumentEditPart,
						isGoalNode);
			} catch (CoreException e) {
				MessageWriter.writeMessageToConsole(
						Messages.SelectModuleContributionItem_0,
						MessageTypeImpl.SELECT_MODULE_FAILED);
				return null;
			}
			IFile modelFile = DcaseEditorUtil.getModelFile(argumentEditPart);
			String moduleName = ModuleUtil.getModuleName(modelFile);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String name = entry.getKey();
				if (moduleName.equals(ModuleUtil.getModuleName(name))) {
					continue;
				}
				if (isGoalNode && !ModuleUtil.isPublicNodeName(name)) {
					continue;
				}
				data.add(name);
			}
			dialog.setInput(data.toArray());

			if (dialog.open() == ListDialog.OK) {
				Object[] result = dialog.getResult();
				if (result.length == 1) {
					return (String) result[0];
				}
			}
		}

		return null;
	}

	/**
	 * Returns the attachment selector name.
	 * 
	 * @return the attachment selector name.
	 * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the attachment selector name.
	 * 
	 * @param name
	 *            the attachment selector name.
	 * @see net.dependableos.dcase.diagram.ui.AttributeDialog.IAttachmentSelector#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Processes after care.
	 * 
	 * @param node
	 *            the node.
	 */
	public void postProcess(BasicNode node, String attachment) {
		doPostProcess(node, attachment);
	}

	public static void doPostProcess(BasicNode node, String attachment) {
		Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
		Resource diagramResource = currentDiagram.eResource();
		ArgumentEditPart currentArgumentEditPart = DcaseEditorUtil
				.getCurrentArgumentEditPart();
		IFile myFile = DcaseEditorUtil.getModelFile(currentArgumentEditPart);
		String myName = node.getName();
		String myNodeName = ModuleUtil.createNodeReference(myFile, myName);
		DcaseNodeEditPart currentEditPart = (DcaseNodeEditPart) getNodeEditPart(
				currentArgumentEditPart, myNodeName, true);

		String oldAttachment = node.getAttachment();
		if (oldAttachment != null && oldAttachment.length() > 0
				&& !ModuleUtil.isWorkspaceReference(oldAttachment)
				&& !ModuleUtil.isUrl(oldAttachment)) {
			setRefSource(currentArgumentEditPart, currentEditPart,
					oldAttachment, false);
		}
		if (attachment != null && attachment.length() > 0
				&& !ModuleUtil.isWorkspaceReference(attachment)
				&& !ModuleUtil.isUrl(oldAttachment)) {
			setRefSource(currentArgumentEditPart, currentEditPart, attachment,
					true);
		}

		try {
			DcaseDiagramEditorUtil.openDiagram(diagramResource);
		} catch (PartInitException e) {
			MessageWriter.writeMessageToConsole(Messages.CreateModuleHandler_3,
					MessageTypeImpl.SELECT_MODULE_FAILED);
			throw new DcaseRuntimeException(Messages.CreateModuleHandler_3,
					null, null, 0, MessageTypeImpl.SELECT_MODULE_FAILED);
		}

	}
	
	/**
	 * Returns the node including responsibilities.
	 * @param attachment  the attachment string.
	 * @return the node.
	 */
	private BasicNode getRespNode(String attachment) {
		String moduleName = ModuleUtil.getModuleName(attachment);
		String nodeName = attachment.equals(moduleName) ? null : ModuleUtil
				.getNodeName(attachment);
		IPath path = ModuleUtil.getModelPath(moduleName);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		EObject mobj = ModelUtil.getModel(file, true);
		if (mobj instanceof Argument) {
			// from Argument
			if (nodeName == null || nodeName.length() == 0) {
				return (Argument) mobj;
			} else {
				// from Node
				EList<BasicNode> nodeList = ((Argument) mobj)
						.getRootBasicNode();
				for (BasicNode node : nodeList) {
					if (node.getName().equals(nodeName)) {
						return node;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Returns the respName.
	 * 
	 * @param attachment
	 *            the attachment string.
	 * @return the respName.
	 */
	public String getRespName(String attachment) {
		BasicNode node = getRespNode(attachment);
		return (node != null) ? node.getRespName() : ""; //$NON-NLS-1$
	}

	/**
	 * Returns the respAddress.
	 * 
	 * @param attachment
	 *            the attachment string.
	 * @return the respAddress.
	 */
	public String getRespAddress(String attachment) {
		BasicNode node = getRespNode(attachment);
		return (node != null) ? node.getRespAddress() : ""; //$NON-NLS-1$
	}

	/**
	 * Returns the respIcon.
	 * 
	 * @param attachment
	 *            the attachment string.
	 * @return the respIcon.
	 */
	public String getRespIcon(String attachment) {
		BasicNode node = getRespNode(attachment);
		return (node != null) ? node.getRespIcon() : ""; //$NON-NLS-1$
	}

	/**
	 * Returns the respTime.
	 * 
	 * @param attachment
	 *            the attachment string.
	 * @return the respTime.
	 */
	public String getRespTime(String attachment) {
		BasicNode node = getRespNode(attachment);
		return (node != null) ? node.getRespTime() : ""; //$NON-NLS-1$
	}

}
