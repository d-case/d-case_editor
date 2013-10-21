/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dependableos.dcase.BasicLink;
import net.dependableos.dcase.BasicNode;
import net.dependableos.dcase.diagram.common.util.NumberUtil;
import net.dependableos.dcase.diagram.common.util.StringUtil;
import net.dependableos.dcase.diagram.editor.common.util.DcaseEditorUtil;

import org.eclipse.draw2d.graph.Edge;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;

/**
 * A class that compares the sibling order of the edges.
 */
public class DcaseEditPartLayoutComparator implements Comparator<Edge>,
        Serializable {

    /**
     * serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * the value of non ordered.
     */
    private static final int NON_NUMBER = Integer.MAX_VALUE;

    /**
     * Compares the edges.
     * 
     * @param object1 the first edge.
     * @param object2 the second edge.
     * @return -1 if and only if the first edge is lower than second edge<br>
     *         0 if and only if the first edge equals second edge<br>
     *         1 if and only if the first edge is higher than second edge
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Edge object1, Edge object2) {

        // gets the sibling order of the first edge.
        int first001Value = getValueUserdef001(object1);

        // gets the sibling order of the second edge.
        int second001Value = getValueUserdef001(object2);

        int n = compareValue(first001Value, second001Value);
        if (n == 0) {
            String firstNameValue = getNameValue(object1);
            String secondNameValue = getNameValue(object2);
            // name order
            n = compareNameValue(firstNameValue, secondNameValue);
            if (n == 0) {
                // graphic location order
                // compares the coordinates if the sibling order of first edge equals the sibling order of second edge
                first001Value = object1.target.x;
                second001Value = object2.target.x;
                if (compareValue(first001Value, second001Value) != 0) {
                    return compareValue(first001Value, second001Value);
                } else {
                    // compares the Y axis.
                    first001Value = object1.target.y;
                    second001Value = object2.target.y;
                    if (compareValue(first001Value, second001Value) != 0) {
                        return compareValue(first001Value, second001Value);
                    }
                }
            }
        }
        return n;
    }

    /**
     * Compares the sibling orders.
     * 
     * @param value1 the first value.
     * @param value2 the second value.
     * @return -1 if and only if the first value is lower than second value<br>
     *         0 if and only if the first value equals second value<br>
     *         1 if and only if the first value is higher than second value
     */
    private int compareValue(int value1, int value2) {
        if (value1 > value2) {
            return 1;
        } else if (value1 < value2) {
            return -1;
        } else {
            return 0;
        }

    }

    /**
     * 
     * @param firstNameValue the first name value.
     * @param secondNameValue the second name value.
     * @return -1 if and only if the first value is lower than second value<br>
     *         0 if and only if the first value equals second value<br>
     *         1 if and only if the first value is higher than second value
     */
    private int compareNameValue(String firstNameValue, String secondNameValue) {

        final String patternNotNumber = "\\D+"; //$NON-NLS-1$
        String frstNotNumber = extractMatchString(patternNotNumber, firstNameValue);
        String secondNotNumber = extractMatchString(patternNotNumber, secondNameValue);
        int n = frstNotNumber.compareTo(secondNotNumber);
        if (n == 0) {
            Pattern pattern1 = Pattern.compile(patternNotNumber);
            Pattern pattern2 = Pattern.compile(patternNotNumber);
            String[] matcher1 = null;
            String[] matcher2 = null;
            if (firstNameValue != null && !firstNameValue.isEmpty()) {
                matcher1 = pattern1.split(firstNameValue);
            }
            if (secondNameValue != null && !secondNameValue.isEmpty()) {
                matcher2 = pattern2.split(secondNameValue);
            }

            if (matcher1 != null && matcher2 != null) {
                int i = 0;
                while (i < matcher1.length && i < matcher2.length) {
                    String s1 = matcher1[i];
                    String s2 = matcher2[i];
                    int firstValue = NumberUtil.parseIntWithDefault(
                            s1, NON_NUMBER);
                    int secondValue = NumberUtil.parseIntWithDefault(
                            s2, NON_NUMBER);
                    n = compareValue(firstValue, secondValue);
                    if (n != 0) {
                        return n;
                    }
                    i++;
                }
            }
            int firstValue = NON_NUMBER;
            int secondValue = NON_NUMBER;
            if (matcher1 != null) {
                firstValue = matcher1.length;
            }
            if (matcher2 != null) {
                secondValue = matcher2.length;
            }
            n = compareValue(firstValue, secondValue);
        }
        return n;
    }

    /**
     * Returns the value of the sibling order.
     * 
     * @param edge the edge.
     * @return the value of the sibling order.
     */
    private int getValueUserdef001(Edge edge) {
        int udef001Value = 0;
        ConnectionEditPart linkEditPart = (ConnectionEditPart) edge.data;
        ConnectorImpl connectLink = (ConnectorImpl) linkEditPart.getModel();

        String userdef001Value = null;
        if (connectLink.getElement() != null) {
            userdef001Value = ((BasicLink) connectLink.getElement())
                    .getUserdef001();
        }
        if (userdef001Value == null) {
            udef001Value = NON_NUMBER;
        } else {
            udef001Value = NumberUtil.parseIntWithDefault(userdef001Value,
                    NON_NUMBER);
        }
        return udef001Value;
    }

    /**
     * Returns the value of the name property.
     * 
     * @param edge the edge.
     * @return the value of the name property.
     */
    private String getNameValue(Edge edge) {
        ConnectionEditPart connection = (ConnectionEditPart) edge.data;
        GraphicalEditPart gEditPart = (GraphicalEditPart) connection.getTarget();
        if (gEditPart != null) {
            BasicNode node = (BasicNode) DcaseEditorUtil.
                    getElement(gEditPart);
            if (node == null) {
                return StringUtil.EMPTY;
            }
            return node.getName();
        }
        return StringUtil.EMPTY;
    }

    /**
     * Returns the matches string.
     * @param regex the regex pattern text.
     * @param target target string.
     * @return matches string or empty string.
     */
    private String extractMatchString(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        if (target == null || target.isEmpty()) {
            return StringUtil.EMPTY;
        }
        Matcher matcher = pattern.matcher(target);
        if (matcher != null && matcher.find()) {
            return matcher.group(0);
        }
        
        return StringUtil.EMPTY;
    }
}