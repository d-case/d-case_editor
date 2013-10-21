/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.parameter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst;
import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeParseHandler;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifier;
import net.dependableos.dcase.diagram.editor.verifier.DataTypeVerifierFactory;
import net.dependableos.dcase.diagram.editor.verifier.DoubleVerifier;
import net.dependableos.dcase.diagram.editor.verifier.EnumVerifier;
import net.dependableos.dcase.diagram.editor.verifier.IntegerVerifier;
import net.dependableos.dcase.diagram.editor.verifier.StringVerifier;

/**
 * The base bean class of parameter data.
 */
public abstract class ParameterDataItem {
    
    /**
     * the root preference key.
     */
    private static final String PREFERENCE_KEY_ROOT = "net.dependableos.dcase.parameters"; //$NON-NLS-1$
    /**
     * the param names preference key.
     */
    private static final String NAMES_KEY = "ParameterItems"; //$NON-NLS-1$
    
    /**
     * A empty string.
     */
    private static final String BLANK = ""; //$NON-NLS-1$
    
    /**
     * the name.
     */
    private String name;
    
    /**
     * the minimum.
     */
    private Integer min;
    
    /**
     * the maximum.
     */
    private Integer max;
    
    /**
     * Returns the verifier.
     * @return the verifier
     */
    public abstract DataTypeVerifier getVerifier();
    
    /**
     * Returns the xml element.
     * @param doc the document
     * @return the parameter element
     */
    public abstract Element getElement(Document doc);
    
    /**
     * Returns the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the string expression of min.
     * @return the min
     */
    public String getMinString() {
        if (getMin() != null) {
            return getMin().toString();
        } else {
            return ""; //$NON-NLS-1$
        }
    }
    /**
     * Returns the min.
     * @return the min
     */
    public Integer getMin() {
        return min;
    }
    /**
     * Sets the min.
     * @param min the min to set
     */
    public void setMin(Integer min) {
        this.min = min;
    }
    /**
     * Returns the string expression of max.
     * @return the max
     */
    public String getMaxString() {
        if (getMax() != null) {
            return getMax().toString();
        } else {
            return ""; //$NON-NLS-1$
        }
    }
    /**
     * Returns the max.
     * @return the max
     */
    public Integer getMax() {
        return max;
    }
    /**
     * Sets the max.
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.max = max;
    }
    
    /**
     * Returns the deep copy of this object.
     * @return the copy of this object. 
     * @throws CloneNotSupportedException Failed to create the copy of this object.
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        
        Object dest;
        try {
            dest = this.getClass().newInstance();
            
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            
            for (PropertyDescriptor descriptor : descriptors) {
                Method readMethod = descriptor.getReadMethod();
                Method writeMethod = descriptor.getWriteMethod();
                if (readMethod != null && writeMethod != null) {
                    Object ret = readMethod.invoke(this, new Object[]{});
                    writeMethod.invoke(dest, ret);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (IntrospectionException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new CloneNotSupportedException(e.getMessage());
        } catch (InstantiationException e) {
            throw new CloneNotSupportedException(e.getMessage());
        }  
        return dest;
    }
    
    /**
     * Returns the list of parameter data type from the given Parameter Data Type xml file.
     * 
     * @param inputStream the import file.
     * @return the list of parameter data type.
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    public static List<ParameterDataItem> getParamDatatypeListFromXml(InputStream inputStream)
            throws
                ParserConfigurationException,
                SAXException,
                IOException {
        
        Map<String, DataTypeVerifier> vf;
        
        vf = parseDatatypeStream(inputStream);
        
        return getParameterDataItemListFromVerifierMap(vf);
    }

    /**
     * Returns the list of parameter data from the Preferences.
     * @return the list of parameter data
     */
    public static List<ParameterDataItem> getParamDatatypeListFromPreferences() {
       
        Map<String, DataTypeVerifier> vf = getDataTypeVerifierMapFromPreferences();
        return getParameterDataItemListFromVerifierMap(vf);
    }
    
    /**
     * Returns the data type verifier map from the preferences.
     * @return the data type verifier map
     */
    public static Map<String, DataTypeVerifier> getDataTypeVerifierMapFromPreferences() {
        
        Map<String, DataTypeVerifier> ret = new LinkedHashMap<String, DataTypeVerifier>();
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_KEY_ROOT);
        String namesString = store.get(NAMES_KEY, BLANK);
        
        if (namesString == null || namesString.length() == 0) {
            return ret;
        }
        
        for (String name : namesString.split(",")) { //$NON-NLS-1$
            String attributeKey = name + "Attributes"; //$NON-NLS-1$
            String attributesString = store.get(attributeKey, BLANK);
            ret.put(name, getVerifier(attributesString));
        }
        return ret;
    }
    
    /**
     * Returns the list of parameter data from the String.
     * @return the list of parameter data
     */
    public static List<ParameterDataItem> getParamDatatypeListFromString(String defString) {
       
        Map<String, DataTypeVerifier> vf = getDataTypeVerifierMapFromString(defString);
        return getParameterDataItemListFromVerifierMap(vf);
    }
    
    /**
     * Returns the data type verifier map from the string.
     * @return the data type verifier map
     */
    public static Map<String, DataTypeVerifier> getDataTypeVerifierMapFromString(String defString) {
        Map<String, DataTypeVerifier> ret = new LinkedHashMap<String, DataTypeVerifier>();
        
        if (defString == null || defString.length() == 0) {
            return ret;
        }
        
        String[] sepString = defString.split(";"); //$NON-NLS-1$
        if (sepString.length < 2) {
        	return ret;
        }
        String[] namesString = sepString[0].split(","); //$NON-NLS-1$
        if (namesString.length != sepString.length - 1) {
        	return ret;
        }
        for (int index=0; index<namesString.length; index++) {
            ret.put(namesString[index], getVerifier(sepString[index+1]));
        }
        return ret;
    }
    
    /**
     * Returns the verifier which has attribute value identical with the given attribute preference string.
     * 
     * @param attributesString the attribute preference string
     * @return the verifier
     */
    private static DataTypeVerifier getVerifier(String attributesString) {
        
        DataTypeVerifier vf = null;
        Map<String, Object> attributeMap = getAttributeMap(attributesString);
        
        String paramType = (String) attributeMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_TYPE);
        
        if (SystemDefinitionConst.DATA_TYPE_STRING.equals(paramType)) {
            vf = new StringVerifier();
        } else if (SystemDefinitionConst.DATA_TYPE_INT.equals(paramType)) {
            vf = new IntegerVerifier();
        } else if (SystemDefinitionConst.DATA_TYPE_DOUBLE.equals(paramType)) {
            vf = new DoubleVerifier();
        } else if (SystemDefinitionConst.DATA_TYPE_ENUM.equals(paramType)) {
            vf = new EnumVerifier();
        } else if (SystemDefinitionConst.DATA_TYPE_RAW.equals(paramType)) {
            vf = new StringVerifier();
        } else {
            throw new DcaseSystemException("", null, MessageTypeImpl.UNDEFINED); //$NON-NLS-1$
        }
        
        vf.setDataType((String) attributeMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_TYPE));
        vf.setParamName((String) attributeMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_NAME));
        
        for (Entry<String, Object> entry : attributeMap.entrySet()) {
            
            if ("items".equals(entry.getKey())) { //$NON-NLS-1$
                @SuppressWarnings("unchecked")
                List<String> items = (List<String>) entry.getValue();
                for (String item : items) {
                    vf.addItem(item);
                }
            } else {
                vf.addAttribute(entry.getKey(), (String) entry.getValue());
            }
        }
        vf.init();
        return vf;
    }
    
    /**
     * Returns the map of attribute.
     * @param attributesString the attribute preference string 
     * @return the attribute map, whose key is the attibute name, whose value is
     *         String object(if type is not enum) or List object(if type is enum).
     */
    private static Map<String, Object> getAttributeMap(String attributesString) {
        
        Map<String, Object> ret = new LinkedHashMap<String, Object>();
        
        int preInd = 0;
        int ind = 0;
        
        List<String> attributList = new ArrayList<String>();
        do {
            preInd = ind;
            
            int nextLBranc = attributesString.indexOf("{", preInd + 1); //$NON-NLS-1$
            int nextComma = attributesString.indexOf(",", preInd + 1); //$NON-NLS-1$
            
            if (nextComma > 0 && nextLBranc > 0 && nextLBranc < nextComma) {
                int nextRBranc = attributesString.indexOf("}", nextLBranc + 1); //$NON-NLS-1$
                nextComma = attributesString.indexOf(",", nextRBranc + 1); //$NON-NLS-1$
            }
            ind = nextComma;

            if (preInd > 0) {
                preInd++;
            }
            if (ind > 0) {
                attributList.add(attributesString.substring(preInd, ind));
            } else {
                attributList.add(attributesString.substring(preInd));
            }
        } while (ind > 0 && ind < attributesString.length() - 1);
        
        for (String attributeString : attributList) {
            int equalInd = attributeString.indexOf("="); //$NON-NLS-1$
            String[] pair = new String[] {
                    attributeString.substring(0, equalInd),
                    attributeString.substring(equalInd + 1),
            };
            if ("items".equals(pair[0])) { //$NON-NLS-1$
                String items = pair[1];
                String[] itemsArray = items.substring(1, items.length() - 1).split(","); //$NON-NLS-1$
                ret.put(pair[0], Arrays.asList(itemsArray));
            } else {
                ret.put(pair[0], pair[1]);
            }
        }
        return ret;
    }

    
    /**
     * Saves the given list of parameter data into the Preferences.
     * @param paramTypeList the given list of parameter data
     */
    public static void savePreferences(List<ParameterDataItem> paramTypeList) {
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_KEY_ROOT);

        try {
            store.clear();
        } catch (BackingStoreException e) {
            MessageWriter
                    .showErrorMessageBox(
                            net.dependableos.dcase.diagram.editor.message.Messages.ErrorPreferenceStoreClear);
        }
        
        Map<String, String> newPreferenceMap = getPreferenceMap(paramTypeList);
        StringBuilder sb = new StringBuilder();
        for (Iterator<Entry<String, String>> ite = newPreferenceMap.entrySet().iterator(); ite.hasNext();) {
            Entry<String, String> entry = ite.next();
            String newName = entry.getKey();
            sb.append(newName);
            if (ite.hasNext()) {
                sb.append(","); //$NON-NLS-1$
            }
            store.put(
                    newName + "Attributes", entry.getValue()); //$NON-NLS-1$ //$NON-NLS-2$
        }
        store.put(NAMES_KEY, sb.toString()); //$NON-NLS-1$
        try {
            store.flush();
        } catch (BackingStoreException e) {
            MessageWriter
                    .showErrorMessageBox(
                            net.dependableos.dcase.diagram.editor.message.Messages.ErrorPreferenceStoreFlush);
        }
        // refreshes the verifier factory.
        DataTypeVerifierFactory.getInstance().refresh();
    }
    
    /**
     * Returns the map of the param data preferences.
     * @param paramTypeList the list of the param data preferences
     * @return the map of the param data preferences
     */
    private static Map<String, String> getPreferenceMap(List<ParameterDataItem> paramTypeList) {
        
        Map<String, String> ret = new LinkedHashMap<String, String>();
        
        for (ParameterDataItem paramData : paramTypeList) {
            ret.put(paramData.getName(), getAttributeString(paramData));
        }
        
        return ret;
    }
    
    /**
     * Returns the attribute preference string of the given param data. 
     * @param paramData the param data
     * @return the attribute preference string of the given param data
     */
    public static String getAttributeString(ParameterDataItem paramData) {
        
        StringBuilder sb = new StringBuilder();
        
        Map<String, String> attMap = paramData.getVerifier().getAttributeMap();
        for (Iterator<Entry<String, String>> ite = attMap.entrySet().iterator(); ite.hasNext();) {
            Entry<String, String> entry = ite.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue()); //$NON-NLS-1$
            if (ite.hasNext()) {
                sb.append(","); //$NON-NLS-1$
            }
        }
        List<String> items = paramData.getVerifier().getItems();
        if (items != null && items.size() > 0) {
            sb.append(","); //$NON-NLS-1$
            StringBuilder itemSb = new StringBuilder();
            itemSb.append("{"); //$NON-NLS-1$
            for (Iterator<String> ite = items.iterator(); ite.hasNext();) {
                itemSb.append(ite.next());
                if (ite.hasNext()) {
                    itemSb.append(","); //$NON-NLS-1$
                }
            }
            itemSb.append("}"); //$NON-NLS-1$
            sb.append("items=").append(itemSb); //$NON-NLS-1$
        }
        
        return sb.toString();
    }
    
    /**
     * Creates the param data list from data type verifier map.
     * @param vf the data type verifier map
     * @return the param data list.
     */
    private static List<ParameterDataItem> getParameterDataItemListFromVerifierMap(
            Map<String, DataTypeVerifier> vf) {
        
        List<ParameterDataItem> ret = new ArrayList<ParameterDataItem>();
        for (Entry<String, DataTypeVerifier>  entry : vf.entrySet()) {
           DataTypeVerifier verifire =  entry.getValue();
           String type = verifire.getDataType();
           // creates string data type bean.
           if (SystemDefinitionConst.DATA_TYPE_STRING.equals(type)) {
               
               StringParameterType param = new StringParameterType();
               param.setName(verifire.getParamName());
               Map<String, String> attMap = verifire.getAttributeMap();
               String min = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MIN);
               if (min != null && min.length() > 0) {
                   param.setMin(Integer.parseInt(min));
               }
               String max = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MAX);
               if (max != null && max.length() > 0) {
                   param.setMax(Integer.parseInt(max));
               }
               ret.add(param);
           // creates int data type bean.
           } else if (SystemDefinitionConst.DATA_TYPE_INT.equals(type)) {
               
               IntegerParameterType param = new IntegerParameterType();
               param.setName(verifire.getParamName());
               Map<String, String> attMap = verifire.getAttributeMap();
               String min = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MIN);
               if (min != null && min.length() > 0) {
                   param.setMin(Integer.parseInt(min));
               }
               String max = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MAX);
               if (max != null && max.length() > 0) {
                   param.setMax(Integer.parseInt(max));
               }
               ret.add(param);
           // creates double data type bean.
           } else if (SystemDefinitionConst.DATA_TYPE_DOUBLE.equals(type)) {
               
               DoubleParameterType param = new DoubleParameterType();
               param.setName(verifire.getParamName());
               Map<String, String> attMap = verifire.getAttributeMap();
               String min = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MIN);
               if (min != null && min.length() > 0) {
                   param.setDoubleMin(Double.parseDouble(min));
               }
               String max = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MAX);
               if (max != null && max.length() > 0) {
                   param.setDoubleMax(Double.parseDouble(max));
               }
               String digit = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_DIGIT);
               if (digit != null && digit.length() > 0) {
                   param.setDigit(Integer.parseInt(digit));
               }
               String incremental = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_INC);
               if (incremental != null && incremental.length() > 0) {
                   param.setIncremental(Double.parseDouble(incremental));
               }
               ret.add(param);
           // creates enumlation data type bean.
           } else if (SystemDefinitionConst.DATA_TYPE_ENUM.equals(type)) {
               
               EnumParameterType param = new EnumParameterType();
               param.setName(verifire.getParamName());
               param.setEnumList(verifire.getAttributeList());
               ret.add(param);
               
           // creates raw data type bean.
           } else if (SystemDefinitionConst.DATA_TYPE_RAW.equals(type)) {
                   
               RawParameterType param = new RawParameterType();
               param.setName(verifire.getParamName());
               Map<String, String> attMap = verifire.getAttributeMap();
               String min = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MIN);
               if (min != null && min.length() > 0) {
                   param.setMin(Integer.parseInt(min));
               }
               String max = attMap.get(SystemDefinitionConst.DATA_ATTRIBUTE_MAX);
               if (max != null && max.length() > 0) {
                   param.setMax(Integer.parseInt(max));
               }
               ret.add(param);
                   
           } else {
               throw new DcaseSystemException(
                       net.dependableos.dcase.diagram.editor.message.Messages
                           .ParameterDataItem_InvalidDatsaTypeErrorMessage,
                       null, MessageTypeImpl.DATA_STRUCTURE_ERROR);
           }
        }
        return ret;
    }
    
    /**
     * Loads the parameter definition file and parses it.
     * 
     * @param is the data source.
     * @return the list of parameter data.
     * @throws ParserConfigurationException 
     * @throws SAXException 
     * @throws IOException 
     */
    private static Map<String, DataTypeVerifier> parseDatatypeStream(InputStream is)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory saxParseFactory = SAXParserFactory.newInstance();
        saxParseFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParseFactory.newSAXParser();
        DataTypeParseHandler handle = new DataTypeParseHandler();
        saxParser.parse(is, handle);
        return handle.getVerifierMap();
    }
    
    /**
     * Returns the xml string from the list of parameter data types.
     * @param paramTypeList the list of parameter data types.
     * @return the xml string.
     * @throws ParserConfigurationException 
     * @throws TransformerException 
     */
    public static String getXmlString(List<ParameterDataItem> paramTypeList)
        throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // gets an instance of builder.
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        // creates an instance of DOM
        Document doc = db.newDocument();
        Element rootElement = doc.createElement(SystemDefinitionConst.DATA_TYPE_TAG_DATATYPE);
        rootElement.setAttribute(
                "xmlns",  //$NON-NLS-1$
                SystemDefinitionConst.NS_PARAMETER_DATA_TYPE);
        doc.appendChild(rootElement);
        
        for (ParameterDataItem item : paramTypeList) {
            Element parameterElement = item.getElement(doc);
            rootElement.appendChild(parameterElement);
        }
        
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        
        // configures the indent
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
        transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4"); //$NON-NLS-1$ //$NON-NLS-2$
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(outputStream));
        
        return new String(outputStream.toByteArray());
    }
    
    /**
     * Returns the list of parameter data type from Paramaeter Data Type xml file.
     */
    public static void refresh() {
        DataTypeVerifierFactory vf = DataTypeVerifierFactory.getInstance();
        vf.refresh();
    }
    
    /**
     * The bean class for string data type.
     */
    public static class StringParameterType extends ParameterDataItem {

        /**
         * {@inheritDoc}
         */
        @Override
        public Element getElement(Document doc) {
            // creates the parameter element
            Element parameterElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER);
            // sets the name attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            // sets the type attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_STRING);
            // creates the length element
            Element lengthElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_LENGTH);
            if (getMinString().trim().length() > 0) {
                // sets the min attribute
                lengthElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                        getMinString());
            }
            if (getMaxString().trim().length() > 0) {
                // sets the max attribute
                lengthElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                        getMaxString());
            }
            parameterElement.appendChild(lengthElement);
            return parameterElement;
        }

        /**
         * {@inheritDoc}.
         */
        @Override
        public DataTypeVerifier getVerifier() {
            StringVerifier vf = new StringVerifier();
            vf.setDataType(SystemDefinitionConst.DATA_TYPE_STRING);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_STRING);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                    getMinString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                    getMaxString());
            return vf;
        }

        /**
         * @see net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem#validate()
         */
        @Override
        public void validate() {
            
            StringVerifier vf = (StringVerifier) getVerifier();
            vf.init(false);
        }
    }

    /**
     * The bean class for int data type.
     */
    public static class IntegerParameterType extends ParameterDataItem {

        /**
         * {@inheritDoc}
         */
        @Override
        public Element getElement(Document doc) {
            // creates the parameter element
            Element parameterElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER);
            // sets the name attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            // sets the type attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_INT);
            // creates the length element
            Element rangeElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_RANGE);
            if (getMinString().trim().length() > 0) {
                // sets the min attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                        getMinString());
            }
            if (getMaxString().trim().length() > 0) {
                // sets the max attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                        getMaxString());
            }
            parameterElement.appendChild(rangeElement);
            return parameterElement;
        }
        
        /* (non-Javadoc)
         * @see net.dependableos.dcase.diagram.editor.ui.ParameterDataItem#getVerifier()
         */
        @Override
        public DataTypeVerifier getVerifier() {
            IntegerVerifier vf = new IntegerVerifier();
            vf.setDataType(SystemDefinitionConst.DATA_TYPE_INT);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_INT);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                    getMinString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                    getMaxString());
            return vf;
        }

        /**
         * @see net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem#validate()
         */
        @Override
        public void validate() {
            IntegerVerifier vf = (IntegerVerifier) getVerifier();
            vf.init(false);
        }
    }
    
    /**
     * Validates the parameter data.
     * when this object is invalid, throw {@link DcaseSystemException}.
     */
    public abstract void validate();
    
    /**
     * The bean class for double data type.
     */
    public static class DoubleParameterType extends ParameterDataItem {

        /**
         * the double type minimum.
         */
        private Double doubleMin;
        
        /**
         * the double type maximum.
         */
        private Double doubleMax;
        
        /**
         * the digit value.
         */
        private Integer digit;
        
        /**
         * the incremental value.
         */
        private Double incremental;
        
        /**
         * Returns the string expression of doubleMin.
         * @return the string expression of doubleMin.
         */
        public String getMinString() {
            if (getDoubleMin() != null) {
                return getDoubleMin().toString();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        /**
         * Returns the double min.
         * @return the double min
         */
        public Double getDoubleMin() {
            return doubleMin;
        }
        /**
         * Sets the double min.
         * @param min the doubleMin to set
         */
        public void setDoubleMin(Double min) {
            this.doubleMin = min;
        }
        /**
         * Returns the string expression of double max.
         * @return the string expression of double max
         */
        public String getMaxString() {
            if (getDoubleMax() != null) {
                return getDoubleMax().toString();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        /**
         * Returns the double max.
         * @return the double max
         */
        public Double getDoubleMax() {
            return doubleMax;
        }
        /**
         * Return the double min.
         * @param max the doubleMax to set
         */
        public void setDoubleMax(Double max) {
            this.doubleMax = max;
        }
        /**
         * Returns the string expression of digit.
         * @return the digit
         */
        public String getDigitString() {
            if (getDigit() != null) {
                return getDigit().toString();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        /**
         * Returns the digit.
         * @return the digit
         */
        public Integer getDigit() {
            return digit;
        }
        /**
         * Sets the digit.
         * @param digit the digit to set
         */
        public void setDigit(Integer digit) {
            this.digit = digit;
        }
        /**
         * Returns the string expression of incremental.
         * @return the string expression of incremental
         */
        public String getIncrementalString() {
            if (getIncremental() != null) {
                return getIncremental().toString();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        /**
         * Returns the incremental.
         * @return the incremental
         */
        public Double getIncremental() {
            return incremental;
        }
        /**
         * Sets the incremental.
         * @param incremental the incremental to set
         */
        public void setIncremental(Double incremental) {
            this.incremental = incremental;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Element getElement(Document doc) {
            // creates the parameter element
            Element parameterElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER);
            // sets the name attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            // sets the type attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_DOUBLE);
            // creates the length element
            Element rangeElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_RANGE);
            if (getMinString().trim().length() > 0) {
                // sets the min attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                        getMinString());
            }
            if (getMaxString().trim().length() > 0) {
                // sets the max attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                        getMaxString());
            }
            if (getDigitString().trim().length() > 0) {
                // sets the digit attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_DIGIT,
                        getDigitString());
            }
            if (getIncrementalString().trim().length() > 0) {
                // sets the inc attribute
                rangeElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_INC,
                        getIncrementalString());
            }
            parameterElement.appendChild(rangeElement);
            return parameterElement;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public DataTypeVerifier getVerifier() {
            DoubleVerifier vf = new DoubleVerifier();
            vf.setDataType(SystemDefinitionConst.DATA_TYPE_DOUBLE);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_DOUBLE);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                    getMinString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                    getMaxString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_DIGIT,
                    getDigitString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_INC,
                    getIncrementalString());
            return vf;
        }
        
        /**
         * @see net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem#validate()
         */
        @Override
        public void validate() {
            DoubleVerifier vf = (DoubleVerifier) getVerifier();
            vf.init(false);
        }
    }
    
    /**
     * The bean class for the enum data type.
     */
    public static class EnumParameterType extends ParameterDataItem {

        /**
         * the list of enum items.
         */
        private List<String> enumList = new ArrayList<String>();

        /**
         * Returns the copy of the enum list.
         * @return copy of the enumList
         */
        public List<String> getEnumList() {
            return new ArrayList<String>(enumList);
        }
        
        /**
         * Sets the enumList.
         * @param enumList the enum item list
         */
        public void setEnumList(List<String> enumList) {
            this.enumList = enumList;
        }

        /**
         * Adds a element to the enumList.
         * @param value a new element to add.
         */
        public void addEnumList(String value) {
            this.enumList.add(value);
        }

        /**
         * Removes the element from the enum list.
         * @param ind the index of the element to remove.
         */
        public void removeEnumList(int ind) {
            this.enumList.remove(ind);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Element getElement(Document doc) {
            // creates the parameter element
            Element parameterElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER);
            // sets the name attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            // sets the type attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_ENUM);
            // creates the items element
            Element itemsElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_ITEMS);
            for (String item : getEnumList()) {
                // creates the item element
                Element itemElement = doc.createElement(
                        SystemDefinitionConst.DATA_TYPE_TAG_ITEM);
                // sets the value attribute
                itemElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_VALUE,
                        item);
                itemsElement.appendChild(itemElement);
            }
            
            parameterElement.appendChild(itemsElement);
            return parameterElement;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public DataTypeVerifier getVerifier() {
            EnumVerifier vf = new EnumVerifier();
            vf.setDataType(SystemDefinitionConst.DATA_TYPE_ENUM);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_ENUM);
            for (String item : getEnumList()) {
                vf.addItem(item);
            }
            return vf;
        }

        /**
         * @see net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem#validate()
         */
        @Override
        public void validate() {
            EnumVerifier vf = (EnumVerifier) getVerifier();
            vf.init(false);
        }
    }
    
    /**
     * The bean class for the raw data type. 
     */
    public static class RawParameterType extends ParameterDataItem {

        /**
         * {@inheritDoc}
         */
        @Override
        public Element getElement(Document doc) {
            // creates the parameter element
            Element parameterElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_PARAMETER);
            // sets the name attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            // sets the type attribute
            parameterElement.setAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_RAW);
            // creates the length element
            Element lengthElement = doc.createElement(
                    SystemDefinitionConst.DATA_TYPE_TAG_LENGTH);
            if (getMinString().trim().length() > 0) {
                // sets the min attribute
                lengthElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                        getMinString());
            }
            if (getMaxString().trim().length() > 0) {
                // sets the max attribute
                lengthElement.setAttribute(
                        SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                        getMaxString());
            }
            parameterElement.appendChild(lengthElement);
            return parameterElement;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public DataTypeVerifier getVerifier() {
            StringVerifier vf = new StringVerifier();
            vf.setDataType(SystemDefinitionConst.DATA_TYPE_RAW);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_NAME,
                    getName());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_TYPE,
                    SystemDefinitionConst.DATA_TYPE_RAW);
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MIN,
                    getMinString());
            vf.addAttribute(
                    SystemDefinitionConst.DATA_ATTRIBUTE_MAX,
                    getMaxString());
            return vf;
        }
        
        /**
         * @see net.dependableos.dcase.diagram.editor.parameter.ParameterDataItem#validate()
         */
        @Override
        public void validate() {
            StringVerifier vf = (StringVerifier) getVerifier();
            vf.init(false);
        }
    }
    
    /**
     * Returns the string that represents the parameter datas.
     * @param paramTypeList the given list of parameter data.
     * @return the string that represents the parameter datas.
     */
    public static String getSavedString(List<ParameterDataItem> paramTypeList) {
    	if (paramTypeList.size() == 0) {
    		return BLANK;
    	}
        Map<String, String> newPreferenceMap = getPreferenceMap(paramTypeList);
        StringBuffer keyBuffer = new StringBuffer();
        StringBuffer valBuffer = new StringBuffer();
        for (Iterator<Entry<String, String>> ite = newPreferenceMap.entrySet().iterator();
        		ite.hasNext();) {
        	Entry<String, String> entry = ite.next();
            keyBuffer.append(entry.getKey());
            valBuffer.append(entry.getValue());
            if (ite.hasNext()) {
                keyBuffer.append(","); //$NON-NLS-1$
                valBuffer.append(";"); //$NON-NLS-1$
            }
        }
        return keyBuffer.append(";").append(valBuffer).toString();
    }
}
