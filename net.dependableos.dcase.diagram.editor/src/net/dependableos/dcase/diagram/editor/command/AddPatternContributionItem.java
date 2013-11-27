/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.command;

import static net.dependableos.dcase.diagram.common.constant.SystemPropertyKeyConst.TEMPLATE_RESOURCE_PROJECT_NAME;
import static org.eclipse.swt.SWT.PUSH;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.DcaseFactory;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.model.NodeType;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.diagram.common.util.PropertyUtil;
import net.dependableos.dcase.diagram.common.validator.NodeMultiplicity;
import net.dependableos.dcase.diagram.edit.parts.ArgumentEditPart;
import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeEditPart;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.common.util.ModuleUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * A contribution item that has sub menus represent converter names.
 */
public class AddPatternContributionItem extends ContributionItem {

	/**
	 * the Labels for executing commands.
	 */
	private static final String MOVE_CMD_LABEL = "command for moving module, etc..."; //$NON-NLS-1$
	private static final String NOTIFY_CMD_LABEL = "command for notify parameters"; //$NON-NLS-1$

	/**
	 * the Labels for creating template. (copy from
	 * command.TemplateModelAdditionAction.java)
	 */
	private static final String TEMPLATE_CMD_LABEL = "command for creating template"; //$NON-NLS-1$
	private static final String TEMPLATE_ADD_MODEL_CMD_LABEL = "template model addition command"; //$NON-NLS-1$
	private static final String TEMPLATE_SELECT_CMD_LABEL = "select command for template addition"; //$NON-NLS-1$
	private static final String TEMPLATE_ARRANGE_CMD_LABEL = "arrange command for template addition"; //$NON-NLS-1$
	private static final String TEMPLATE_PERSISTANCE_CMD_LABEL = "persistance command for template addition"; //$NON-NLS-1$

	/**
	 * the name of the template project.
	 */
	private static final String CONST_TEMPLATE_RESOURCE_PROJECT_NAME = PropertyUtil
			.getSystemProperty(TEMPLATE_RESOURCE_PROJECT_NAME);

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem.
	 */
	public AddPatternContributionItem() {
		super();
	}

	/**
	 * Constructor for the class. Creates a SelectModuleContributionItem and
	 * initialize it.
	 * 
	 * @param id
	 *            the contribution item identifier, or null.
	 */
	public AddPatternContributionItem(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Menu menu, int index) {
		IProject templateProject;
		try {
			templateProject = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(CONST_TEMPLATE_RESOURCE_PROJECT_NAME);

			// tests whether the project is accessible.
			if (!templateProject.isAccessible()) {
				MessageWriter.writeMessageToConsole(
						Messages.AddPatternContributionItem_1,
						MessageTypeImpl.CREATE_PATTERN_FAILED);
				return;
			}
		} catch (DcaseSystemException dse) {
			dse.printStackTrace();
			return;
		}

		// get templates.
		ArrayList<IFile> list = new ArrayList<IFile>();
		try {
			getTemplateFiles(templateProject, list);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		// get selected node.
		DcaseNodeEditPart selectedEditPart = DcaseEditorUtil
				.getFirstCurrentSelectedPart();
		BasicNode selectedNode = null;
		if (selectedEditPart != null) {
			Object sobj = DcaseEditorUtil.getElement(selectedEditPart);
			if (sobj instanceof BasicNode) {
				selectedNode = (BasicNode) sobj;
			}
		}

		// add to menus.
		for (IFile file : list) {
			BasicNode rootNode = ModuleUtil.getRootNode(file);
			if (rootNode == null) {
				continue;
			}
			// validate between selected node and root node.
			if (selectedNode != null) {
				NodeType selectedType = NodeType.getNodeType(selectedNode);
				NodeType rootType = NodeType.getNodeType(rootNode);
				Map<NodeType, NodeMultiplicity> map = selectedType
						.getNodeValidatorRule().getChildMultiplicity();
				NodeMultiplicity multiplicity = map.get(rootType);
				if (multiplicity == null
						|| multiplicity == NodeMultiplicity.ZERO) {
					continue;
				}
			}
			MenuItem item = new MenuItem(menu, PUSH);
			String fname = file.toString();
			item.setText(fname.substring(fname.indexOf(
					"/", fname.indexOf("/") + 1) + 1)); //$NON-NLS-1$
			AddPatternSelectionAdapter adapter = new AddPatternSelectionAdapter();
			adapter.setEntry(file);
			item.addSelectionListener(adapter);
		}
	}

	/**
	 * Returns the pattern file list
	 * 
	 * @param topResource
	 * @param list
	 * @throws CoreException
	 */
	private void getTemplateFiles(IResource topResource, ArrayList<IFile> list)
			throws CoreException {
		IResource[] resources;
		if (topResource.getType() == IResource.PROJECT) {
			resources = ((IProject) topResource).members();
		} else if (topResource.getType() == IResource.FOLDER) {
			resources = ((IFolder) topResource).members();
		} else {
			return;
		}
		for (IResource res : resources) {
			switch (res.getType()) {
			case IResource.FILE:
				if (res.getFileExtension().equals(
						ModuleUtil.getModelFileExtension())) {
					list.add((IFile) res);
				}
				break;
			case IResource.FOLDER:
				getTemplateFiles((IFolder) res, list);
				break;
			}
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
	private class AddPatternSelectionAdapter extends SelectionAdapter {
		/**
		 * The template file.
		 */
		private IFile entry;

		/**
		 * Sets the selected template of public node.
		 * 
		 * @param entry
		 */
		public void setEntry(IFile entry) {
			this.entry = entry;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {
			DcaseNodeEditPart parentEditPart = DcaseEditorUtil
					.getFirstCurrentSelectedPart();
			Diagram currentDiagram = DcaseEditorUtil.getCurrentDiagram();
			ArgumentEditPart argumentEditPart = DcaseEditorUtil
					.getCurrentArgumentEditPart();
			Point currentPoint;
			if (parentEditPart != null) {
				currentPoint = parentEditPart.getLocation();
				Dimension dimension = parentEditPart.getSize();
				currentPoint.x += dimension.width / 2;
				currentPoint.y += dimension.height * 2;
			} else {
				currentPoint = ModuleUtil.getCurrentLocation(argumentEditPart);
			}

			// gets the model of the selected template.
			EObject templateModel = ModelUtil.getModel(entry, true);
			if (templateModel == null) {
				MessageWriter.writeMessageToConsole(
						Messages.TemplateModelAdditionAction_2,
						MessageTypeImpl.CREATE_PATTERN_FAILED);
				return;
			}
			// tests whether a node exists in the selected template.
			EList<BasicNode> nodeList = ((Argument) templateModel)
					.getRootBasicNode();
			if (nodeList == null || nodeList.size() == 0) {
				MessageWriter.writeMessageToConsole(
						Messages.TemplateModelAdditionAction_3,
						MessageTypeImpl.CREATE_PATTERN_FAILED);
				return;
			}
			// copy the model of the selected template.
			EObject copyModel = (EObject) EcoreUtil.copy(templateModel);
			Argument copyArgument = (Argument) copyModel;
			if (! ModuleUtil.processPatterns(copyArgument)) {
				return;
			}
			// add link
			if (parentEditPart != null) {
				BasicNode topNode = ModuleUtil.getRootElement(copyArgument);
				BasicLink link = DcaseFactory.eINSTANCE.createDcaseLink001();
				link.setSource((BasicNode) DcaseEditorUtil
						.getElement(parentEditPart));
				link.setTarget(topNode);
				copyArgument.getRootBasicLink().add(link);
			}

			// add pattern to current argument.
			Set<String> currentIdSet = DcaseEditorUtil
					.getChildUUIDs(argumentEditPart);
			CompoundCommand patternCmd = createTemplateAdditionCommand(
					currentDiagram, copyModel, argumentEditPart, currentPoint,
					currentIdSet);
			// execute
			argumentEditPart.getDiagramEditDomain().getDiagramCommandStack()
					.execute(patternCmd);
		}
	}

	/**
	 * Creates the command to add the template. (copy from
	 * command.TemplateModelAdditionAction.java)
	 * 
	 * @param currentDiagram
	 *            the current diagram.
	 * @param templateModel
	 *            the EObject from the template.
	 * @param currentArgumentEditPart
	 *            the current argument edit part.
	 * @param excludeIdSet
	 *            the set of node IDs those will be excluded to select and
	 *            arrange layout.
	 * @return the command to add the template.
	 */
	private CompoundCommand createTemplateAdditionCommand(
			Diagram currentDiagram, EObject templateModel,
			ArgumentEditPart currentArgumentEditPart, Point currentPoint,
			Set<String> excludeIdSet) {

		TransactionalEditingDomain currentDomain = GMFEditingDomainFactory.INSTANCE
				.getEditingDomain(currentDiagram.eResource().getResourceSet());

		// creates the compound command.
		CompoundCommand cc = new CompoundCommand(TEMPLATE_CMD_LABEL);

		// creates the command to add the model.
		Argument currentArgument = (Argument) currentDiagram.getElement();
		Argument templateArgument = (Argument) templateModel;
		ICommand additionCommand = new ModelAdditionCommand(currentDomain,
				TEMPLATE_ADD_MODEL_CMD_LABEL, null, templateArgument,
				currentArgument);
		cc.add(new ICommandProxy(additionCommand));

		// creates the command to select nodes those will be add from the
		// template.
		ICommand selectCommand = new SelectExcludesCommand(
				TEMPLATE_SELECT_CMD_LABEL, currentArgumentEditPart,
				excludeIdSet);
		cc.add(new ICommandProxy(selectCommand));

		// creates the command to move to the current point.
		MoveSelectedCommand moveCommand = new MoveSelectedCommand(
				currentDomain, MOVE_CMD_LABEL, currentPoint);
		cc.add(new ICommandProxy(moveCommand));

		// creates the command to arrange layout nodes those will be add from
		// the template.
		if (templateArgument.getRootBasicNode().size() > 1) {
			ICommand arrangeCommand = new ArrangeExcludesCommand(currentDomain,
					TEMPLATE_ARRANGE_CMD_LABEL, null, currentArgumentEditPart,
					excludeIdSet);
			cc.add(new ICommandProxy(arrangeCommand));

			// creates the command to notify all nodes.
			NotifyParametersCommand notifyCommand = new NotifyParametersCommand(
					currentDomain, NOTIFY_CMD_LABEL, null,
					currentArgumentEditPart, excludeIdSet);
			cc.add(new ICommandProxy(notifyCommand));
		}

		// creates the command to persist the edit parts.
		ICommand persistanceCommand = new NotationPersistanceCommand(
				currentDomain, TEMPLATE_PERSISTANCE_CMD_LABEL, null,
				currentArgumentEditPart);
		cc.add(new ICommandProxy(persistanceCommand));

		return cc;
	}

}
