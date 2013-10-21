/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import static net.dependableos.dcase.diagram.common.constant.SystemDefinitionConst.COLLECTION_INITIAL_CAPACITY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dependableos.dcase.Argument;
import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.exception.DcaseRuntimeException;
import net.dependableos.dcase.diagram.common.model.LinkType;
import net.dependableos.dcase.diagram.common.model.NodeType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * Initializes the name of a node or a link.
 */
public class NamePropertyAutoCreator {

    /**
     * the maximum value of sequence number.
     */
    private static final int MAX_NUMBER = Integer.MAX_VALUE;

    /**
     * the minimum value of sequence number.
     */
    private static final int MIN_NUMBER = 1;

    /**
     * the map of the prefixes for the nodes.
     */
    private static final Map<NodeType, String> NODE_PREFIX_MAP;
    
    /**
     * the map of the prefixes for the links.
     */
    private static final Map<LinkType, String> LINK_PREFIX_MAP;

    /**
     * the regular expression pattern.
     */
    private static final Pattern NAME_PROPERTY_PATTERN;

    static {
        // initializes the prefixes for the nodes.
        NODE_PREFIX_MAP = new HashMap<NodeType, String>();
        NODE_PREFIX_MAP.put(NodeType.GOAL, TermsMessages.NamePropertyAutoCreator_0);
        NODE_PREFIX_MAP.put(NodeType.STRATEGY, TermsMessages.NamePropertyAutoCreator_1);
        NODE_PREFIX_MAP.put(NodeType.EVIDENCE, TermsMessages.NamePropertyAutoCreator_2);
        NODE_PREFIX_MAP.put(NodeType.UNDEVELOPED, TermsMessages.NamePropertyAutoCreator_3);
        NODE_PREFIX_MAP.put(NodeType.CONTEXT, TermsMessages.NamePropertyAutoCreator_4);
        NODE_PREFIX_MAP.put(NodeType.MONITOR, TermsMessages.NamePropertyAutoCreator_5);
        NODE_PREFIX_MAP.put(NodeType.JUSTIFICATION, TermsMessages.NamePropertyAutoCreator_6);
        NODE_PREFIX_MAP.put(NodeType.SYSTEM, TermsMessages.NamePropertyAutoCreator_7);
        NODE_PREFIX_MAP.put(NodeType.POLICY, TermsMessages.NamePropertyAutoCreator_8);
        NODE_PREFIX_MAP.put(NodeType.USERDEF001, TermsMessages.NamePropertyAutoCreator_9);
        NODE_PREFIX_MAP.put(NodeType.USERDEF002, TermsMessages.NamePropertyAutoCreator_10);
        NODE_PREFIX_MAP.put(NodeType.USERDEF003, TermsMessages.NamePropertyAutoCreator_11);
        NODE_PREFIX_MAP.put(NodeType.USERDEF004, TermsMessages.NamePropertyAutoCreator_12);
        NODE_PREFIX_MAP.put(NodeType.USERDEF005, TermsMessages.NamePropertyAutoCreator_13);
        NODE_PREFIX_MAP.put(NodeType.USERDEF006, TermsMessages.NamePropertyAutoCreator_14);

        // initializes the prefixes for the links.
        LINK_PREFIX_MAP = new HashMap<LinkType, String>();
        LINK_PREFIX_MAP.put(LinkType.BASIC_LINK, TermsMessages.NamePropertyAutoCreator_15);

        // initializes the regular expression pattern.
        NAME_PROPERTY_PATTERN = Pattern.compile("(" //$NON-NLS-1$
                + StringUtil.join(NODE_PREFIX_MAP.values(), "|") + "|" //$NON-NLS-1$ //$NON-NLS-2$
                + StringUtil.join(LINK_PREFIX_MAP.values(), "|") + ")(\\d+)", //$NON-NLS-1$ //$NON-NLS-2$
                Pattern.CASE_INSENSITIVE);
    }

    /**
     * the map that manages current sequence number.
     */
    private Map<String, Integer> currentMaxNumberMap;

    /**
     * the map that manages all sequence numbers.
     */
    private Map<String, SortedSet<Integer>> allNumbersMap;

    /**
     * the map that manages sequence numbers those are created by this object.
     */
    private Map<String, List<Integer>> usedNumbersMap;

    /**
     * A constructor.
     */    
    public NamePropertyAutoCreator() {  
    }

    /**
     * Loads the diagram.
     * 
     * @param argument the argument of the diagram.
     */
    public void loadDiagram(Argument argument) {

        // initializes the maps.
        currentMaxNumberMap = new HashMap<String, Integer>();
        allNumbersMap = null;
        usedNumbersMap = new HashMap<String, List<Integer>>();

        // gets the current sequence number and sequence numbers those are created by this object.
        EList<BasicNode> nodeList = argument.getRootBasicNode();
        for (BasicNode node : nodeList) {
            saveNumbers(node.getName());
        }
        EList<BasicLink> linkList = argument.getRootBasicLink();
        for (BasicLink link : linkList) {
            saveNumbers(link.getName());
        }
    }

    /**
     * Saves the sequence number of the name to the map.
     * 
     * @param name the name
     */
    private void saveNumbers(String name) {
        if (name == null) {
            return;
        }

        Matcher matcher = NAME_PROPERTY_PATTERN.matcher(name);
        if (matcher.matches()) {

            String prefix = matcher.group(1);

            // gets the sequence number
            int number = 0;
            try {
                number = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException nfe) {
                return;
            }
            if (number < MIN_NUMBER) {
                return;
            }

            // saves the number.
            saveUsedNumber(prefix, number);

            // tests whether the number is larger than the maximum.
            Integer currentMaxNumberObj = currentMaxNumberMap.get(prefix);
            if (currentMaxNumberObj == null
                    || number > currentMaxNumberObj.intValue()) {
                currentMaxNumberMap.put(prefix, Integer.valueOf(number));
            }
        }
    }

    /**
     * Returns the initial name.
     * 
     * @param eObject a node or a link.
     * @return the name.
     */
    public String getInitialName(EObject eObject) {

        // gets the prefix.
        String prefix = null;
        if (eObject instanceof BasicNode) {
            NodeType nodeType = NodeType.getNodeType((BasicNode) eObject);
            prefix = NODE_PREFIX_MAP.get(nodeType);
        } else if (eObject instanceof BasicLink) {
            prefix = LINK_PREFIX_MAP.get(LinkType.BASIC_LINK);
        }

        if (prefix == null) {
            return null;
        }

        // gets and updates the current number.
        int nextNumber = 0;
        try {
            nextNumber = getNextNumber(prefix);
        } catch (DcaseRuntimeException dre) {
            // failed to get or update the current number.
            return null;
        }

        // returns the initial name.
        return prefix + String.valueOf(nextNumber);
    }

    /**
     * Updates and Returns the current number.
     * 
     * @param prefix the prefix.
     * @return the current number
     */
    private int getNextNumber(String prefix) {

        // the current number is not found.
        Integer currentMaxNumberObj = currentMaxNumberMap.get(prefix);
        if (currentMaxNumberObj == null) {
            currentMaxNumberMap.put(prefix, Integer.valueOf(MIN_NUMBER));
            saveUsedNumber(prefix, MIN_NUMBER);
            return MIN_NUMBER;
        }

        int currentMaxNumber = currentMaxNumberObj.intValue();
        int nextNumber = 0;

        // the number is the maximum.
        if (currentMaxNumber == MAX_NUMBER) {

            // creates the map that manages all numbers.
            if (allNumbersMap == null) {
                allNumbersMap = new HashMap<String, SortedSet<Integer>>();
            }
            SortedSet<Integer> sortedAllNumbers = allNumbersMap.get(prefix);
            if (sortedAllNumbers == null) {
                sortedAllNumbers = createSortedNumbers(prefix);
            }

            nextNumber = MIN_NUMBER;
            for (Integer sortedNumber : sortedAllNumbers) {
                if (nextNumber != sortedNumber.intValue()) {
                    break;
                } else if (nextNumber == MAX_NUMBER) {
                    throw new DcaseRuntimeException(null, null, null, 0,
                            MessageTypeImpl.UNDEFINED);
                }
                nextNumber++;
            }

            sortedAllNumbers.add(Integer.valueOf(nextNumber));
            allNumbersMap.put(prefix, sortedAllNumbers);

        } else {
            // updates the number.
            nextNumber = ++currentMaxNumber;
            currentMaxNumberMap.put(prefix, Integer.valueOf(currentMaxNumber));
            saveUsedNumber(prefix, nextNumber);
        }

        return nextNumber;
    }

    /**
     * Save the number.
     * 
     * @param prefix the prefix.
     * @param number the number.
     */
    private void saveUsedNumber(String prefix, int number) {

        List<Integer> usedNumberSet = usedNumbersMap.get(prefix);
        if (usedNumberSet == null) {
            usedNumberSet = new ArrayList<Integer>(COLLECTION_INITIAL_CAPACITY);
        }
        usedNumberSet.add(Integer.valueOf(number));
        usedNumbersMap.put(prefix, usedNumberSet);
    }

    /**
     * Creates the sorted numbers.
     * 
     * @param prefix the prefix.
     * @return the sorted numbers.
     */
    private SortedSet<Integer> createSortedNumbers(String prefix) {

        SortedSet<Integer> sortedAllNumbers = new TreeSet<Integer>();

        List<Integer> usedNumbers = usedNumbersMap.get(prefix);
        sortedAllNumbers.addAll(usedNumbers);

        return sortedAllNumbers;
    }
}
