/*
 * Copyright (C) 2012  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.logic.xmlconv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.Argument;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.common.util.Messages;
import net.dependableos.dcase.diagram.common.util.ModelUtil;
import net.dependableos.dcase.provider.DcaseEditPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

/**
 * Converts the GMF model file to the Text file.
 */
public class XmlConversionToTextLogic {

	/**
	 * The input file.
	 */
	private IFile inputFile;

	/**
	 * The output file.
	 */
	private IFile outputFile;

	/**
	 * The flag of overwrite save for output file.
	 */
	private boolean overwriteOption;

	/**
	 * Allocates a XmlConversionToTextLogic object and initializes it to
	 * represent the input file, the output file and the overwrite option.
	 * 
	 * @param inputFile
	 *            The input file.
	 * @param outputFile
	 *            The output file.
	 * @param overwriteOption
	 *            The flag of overwrite save for output file.
	 */
	public XmlConversionToTextLogic(IFile inputFile, IFile outputFile,
			boolean overwriteOption) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.overwriteOption = overwriteOption;
	}

	/**
	 * Converts the GMF model file to the Text file.
	 */
	public void convert() {

		// Checks the input file.
		if (inputFile == null) {
			throw new DcaseRuntimeException(
					Messages.XmlConversionToTextLogic_0, null, null, 0,
					MessageTypeImpl.CONVERT_TO_TEXT_FAILED);
		}
		// Checks the output file.
		if (outputFile == null) {
			throw new DcaseRuntimeException(
					Messages.XmlConversionToTextLogic_1, null, null, 0,
					MessageTypeImpl.CONVERT_TO_TEXT_FAILED);
		}

		File target = new File(outputFile.getLocation().toOSString());

		// Check the existence of the file and check an overwrite option more.
		if (target.exists() && !overwriteOption) {
			throw new DcaseRuntimeException(
					Messages.XmlConversionToTextLogic_2, null, null, 0,
					MessageTypeImpl.CONVERT_TO_TEXT_FAILED);
		}

		// Sort nodes.
		HashMap<String, ArrayList<BasicNode>> map = new HashMap<String, ArrayList<BasicNode>>();
		EObject obj = ModelUtil.getModel(inputFile);
		if (obj instanceof Argument) {
			Argument argument = (Argument) obj;
			EList<BasicNode> nodes = argument.getRootBasicNode();
			for (BasicNode node : nodes) {
				String key;
				Class<?> ifaces[] = node.getClass().getInterfaces();
				if (ifaces.length >= 1) {
					key = ifaces[0].getSimpleName();
				} else {
					key = node.getClass().getSimpleName();
				}
				ArrayList<BasicNode> list = map.get(key);
				if (list == null) {
					list = new ArrayList<BasicNode>();
				}
				list.add(node);
				map.put(key, list);
			}
		}

		// Converts to the Text.
		try {
			FileWriter writer = new FileWriter(target);
			String retStr = System.getProperty("line.separator"); //$NON-NLS-1$

			Object[] keyArray = map.keySet().toArray();
			Arrays.sort(keyArray, new NodeTypeComparator());
			for (Object ko : keyArray) {
				// uum...
				String key = (String) ko;
				String name = getNodeName(key);
				writer.write("[" + name + "]" + retStr); //$NON-NLS-1$ //$NON-NLS-2$
				for (BasicNode node : map.get(key)) {
					String descStr = node.getDesc();
					if (descStr == null) {
						descStr = ""; //$NON-NLS-1$
					}
					String attaStr = node.getAttachment();
					if (attaStr == null) {
						attaStr = ""; //$NON-NLS-1$
					}
					writer.write("\"" + node.getName() + "\",\"" + //$NON-NLS-1$ //$NON-NLS-2$
							descStr + "\",\"" + attaStr + "\"" + retStr); //$NON-NLS-3$ //$NON-NLS-4$
				}
				writer.write(retStr);
			}
			writer.close();
		} catch (IOException e) {
			throw new DcaseSystemException(NLS.bind(
					Messages.XmlConversionToTextLogic_3, outputFile
							.getFullPath().toString()), e,
					MessageTypeImpl.UNDEFINED);
		}

		try {
			outputFile.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			throw new DcaseSystemException(NLS.bind(
					Messages.XmlConversionToTextLogic_3, outputFile
							.getFullPath().toString()), e,
					MessageTypeImpl.UNDEFINED);
		}

	}

	/**
	 * Conversions the node type name.
	 * 
	 * @param key
	 *            the original name.
	 * @return the node type name.
	 */
	private String getNodeName(String key) {
		return DcaseEditPlugin.getPlugin().getString("_UI_" + key + "_type"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Comparator class for Node Type Name.
	 * 
	 */
	private class NodeTypeComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			if (!(o1 instanceof String) || !(o2 instanceof String)) {
				return 0;
			}
			String s1 = getNodeName((String) o1);
			String s2 = getNodeName((String) o2);
			byte[] b1 = s1.getBytes();
			byte[] b2 = s2.getBytes();
			for (int i = 0; i < b1.length && i < b2.length; i++) {
				if (b1[i] == b2[i]) {
					continue;
				}
				return b1[i] - b2[i];
			}
			return 0;
		}
	}

}
