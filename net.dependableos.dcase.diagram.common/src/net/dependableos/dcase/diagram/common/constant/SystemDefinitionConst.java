/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.constant;

/**
 * System constant values.
 */
public final class SystemDefinitionConst {

    /**
     * The initial size of a collection.
     */
    public static final int COLLECTION_INITIAL_CAPACITY = 128;

    /**
     * The marker ID of calculating the score.
     */
    public static final String CALCULATE_SCORE_MARKER_ID 
        = "net.dependableos.dcase.diagram.editor.dcaseproblem.calculatescore";

    /**
     * HTTP protocol.
     */
    public static final String CONST_HTTP_PROTOCOL_STRING = "http"; //$NON-NLS-1$

    /**
     * HTTPS protocol.
     */
    public static final String CONST_HTTPS_PROTOCOL_STRING = "https"; //$NON-NLS-1$

    /**
     * FILE protocol.
     */
    public static final String CONST_FILE_PROTOCOL_STRING = "file"; //$NON-NLS-1$

    /**
     * The line dash style of a link.
     */
    public static final int[] LINK_LINE_DASH = {4, 5};

    /**
     * The string that represents the type name of the Goal node.
     */
    public static final String GOAL_NAME = "Goal"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Strategy node.
     */
    public static final String STRATEGY_NAME = "Strategy"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Evidence node.
     */
    public static final String EVIDENCE_NAME = "Evidence"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Undeveloped node.
     */
    public static final String UNDEVELOPED_NAME = "Undeveloped"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Context node.
     */
    public static final String CONTEXT_NAME = "Context"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Monitor node.
     */
    public static final String MONITOR_NAME = "Monitor"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Justification node.
     */
    public static final String JUSTIFICATION_NAME = "Justification"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Pattern node.
     */
    public static final String SYSTEM_NAME = "Pattern"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Action node.
     */
    public static final String POLICY_NAME = "Action"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the External node.
     */
    public static final String USERDEF001_NAME = "External"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Userdef002 node.
     */
    public static final String USERDEF002_NAME = "Userdef002"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Userdef003 node.
     */
    public static final String USERDEF003_NAME = "Userdef003"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Assumption node.
     */
    public static final String USERDEF004_NAME = "Assumption"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Module node.
     */
    public static final String USERDEF005_NAME = "Module"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Contract node.
     */
    public static final String USERDEF006_NAME = "Contract"; //$NON-NLS-1$

    /**
     * The string that represents the type name of the Argument.
     */
    public static final String ARGUMENT_NAME = "Argument";  //$NON-NLS-1$

    /**
     * The string that represents the type name of the Basic link.
     */
    public static final String BASIC_LINK_NAME = "BasicLink";   //$NON-NLS-1$

    /**
     * The value of the top margin of the canvas.
     */
    public static final int NODE_MARGIN_TOP = 8;

    /**
     * The value of the left margin of the canvas.
     */
    public static final int NODE_MARGIN_LEFT = 9;

    /**
     * The value of the bottom margin of the canvas.
     */
    public static final int NODE_MARGIN_BOTTOM = 9;

    /**
     * The value of the right margin of the canvas.
     */
    public static final int NODE_MARGIN_RIGHT = 9;

    /**
     * The value of the left and right margin of a Parallelogram shape.
     */
    public static final int NODE_MARGIN_PARALLEL = 12;
    
    /**
     * The value of the top and bottom margin of a node.
     */    
    public static final int NODE_MARGIN_VERTICAL = 4;
    
    /**
     * The path to the file to translate to D-Case model from GMF model.
     */
    public static final String XSL_TO_DCASE_PATH = "/resource/gmf_to_dcase.xsl";   //$NON-NLS-1$

    /**
     * The path to the file to translate to GMF model from D-Case model.
     */
    public static final String XSL_TO_GMF_PATH = "/resource/dcase_to_gmf.xsl";   //$NON-NLS-1$

    /**
     * The path to the file to translate to New GMF model from Old GMF model.
     */
    public static final String XSL_TO_NEWGMF_PATH = "/resource/old_to_new.xsl";   //$NON-NLS-1$

    /**
     * The path to the file to translate to ARM from GMF model.
     */
    public static final String XSL_TO_ARM_PATH = "/resource/gmf_to_arm.xsl";   //$NON-NLS-1$

    /**
     * The path to the file to translate to SACM from GMF model.
     */
    public static final String XSL_TO_SACM_PATH = "/resource/gmf_to_sacm.xsl";   //$NON-NLS-1$

    /**
     * The file extension of EPS. 
     */
    public static final String EPS_FILE_EXTENSION = ".eps"; //$NON-NLS-1$
    
    /**
     * The workspace folder of D-Case.
     */
    public static final String DCASE_TOOL_FOLDER = "dcase"; //$NON-NLS-1$
    
    /**
     * The name of the file to convert to EPS from PDF.
     */
    public static final String EPS_CONVERT_TOOL_NAME = "convertPdfToEps.bat"; //$NON-NLS-1$
    
    /**
     * The file extension of PDF. 
     */
    public static final String PDF_FILE_EXTENSION = ".pdf"; //$NON-NLS-1$

    /**
     * The namespace of parameter data type xml.
     */
    public static final String NS_PARAMETER_DATA_TYPE =
        "http://www.dependable-os.net/2011/06/dcase/Parameter/DataType"; //$NON-NLS-1$
    
    /**
     * The name of the dataType element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_DATATYPE = "dataType"; //$NON-NLS-1$
    
    /**
     * The name of the parameter element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_PARAMETER = "parameter"; //$NON-NLS-1$
    
    /**
     * The name of the length element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_LENGTH = "length"; //$NON-NLS-1$
    
    /**
     * The name of the range element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_RANGE = "range"; //$NON-NLS-1$
    
    /**
     * The name of the items element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_ITEMS = "items"; //$NON-NLS-1$
    
    /**
     * The name of the item element of the data type xml.
     */
    public static final String DATA_TYPE_TAG_ITEM = "item"; //$NON-NLS-1$
    
    /**
     * The name of the value attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_VALUE = "value"; //$NON-NLS-1$
    
     /**
     * The name of the name attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_NAME = "name"; //$NON-NLS-1$

    /**
     * The name of the type attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_TYPE = "type"; //$NON-NLS-1$
    
    /**
     * The name of the min attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_MIN = "min"; //$NON-NLS-1$

    /**
     * The name of the max attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_MAX = "max"; //$NON-NLS-1$
    
    /**
     * The name of the digit attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_DIGIT = "digit"; //$NON-NLS-1$
    
    /**
     * The name of the inc attribute of the data type xml.
     */
    public static final String DATA_ATTRIBUTE_INC = "inc"; //$NON-NLS-1$    

    /**
     * The string that represents integer type.
     */
    public static final String DATA_TYPE_INT = "int"; //$NON-NLS-1$

    /**
     * The string that represents string type.
     */
    public static final String DATA_TYPE_STRING = "string"; //$NON-NLS-1$

    /**
     * The string that represents double type.
     */
    public static final String DATA_TYPE_DOUBLE = "double"; //$NON-NLS-1$

    /**
     * The string that represents enumeration type.
     */
    public static final String DATA_TYPE_ENUM = "enum"; //$NON-NLS-1$
    
    /**
     * The string that represents raw type.
     */
    public static final String DATA_TYPE_RAW = "raw"; //$NON-NLS-1$
    
    /**
     * The constructor.
     */
    private SystemDefinitionConst() {
    }

}
