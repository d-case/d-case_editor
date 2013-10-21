/*
 * Copyright (C) 2013  Nagoya University All rights reserved.
 */
package net.dependableos.dcase.diagram.edit.parts;

import net.dependableos.dcase.diagram.edit.parts.custom.DcaseNodeResponsibilityEditPart;
import net.dependableos.dcase.diagram.part.DcaseVisualIDRegistry;
import net.dependableos.dcase.diagram.providers.DcaseElementTypes;
import net.dependableos.dcase.diagram.providers.DcaseParserProvider;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Userdef001ResponsibilityEditPart extends
		DcaseNodeResponsibilityEditPart {

	public static final int VISUAL_ID = 4078; // set manually...

	public Userdef001ResponsibilityEditPart(View view) {
		super(view);
	}

	public IParser getParser() {
		if (parser == null) {
			parser = DcaseParserProvider
					.getParser(
							DcaseElementTypes.Userdef001_1010,
							getParserElement(),
							DcaseVisualIDRegistry
									.getType(net.dependableos.dcase.diagram.edit.parts.Userdef001ResponsibilityEditPart.VISUAL_ID));
		}
		return parser;
	}

}
