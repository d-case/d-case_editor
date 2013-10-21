/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.layout;

/**
 * 
 * The arrange angle class.
 *
 */
public class ArrangeAngle {
    /**
     * The Angle direction. 
     */
    public enum Direction {
        /**
         * The Vertical.
         */
        Vertical,
        /**
         * The Horizontal.
         */
        Horizontal
    };
    /**
     * The instance object.
     */
    private static ArrangeAngle instance = null;
    /**
     * The arrange direction.
     */
    private Direction angle = Direction.Vertical;
    /**
     * 
     */
    ArrangeAngle() {
        
    }
    /**
     * Create the class instance object.
     * 
     * @return A class instance object.
     */
    public static ArrangeAngle createInstance() {
        if (ArrangeAngle.instance == null) {
            ArrangeAngle.instance = new ArrangeAngle();
        }
        return instance;
        
    }
    /**
     * Get the angle direction.
     * 
     * @return The direction.
     */
    public Direction getAngle() {
        return angle;
    }
    /**
     * Set the angle direction.
     * 
     * @param angle The direction.
     */
    public void setAngle(Direction angle) {
        this.angle = angle;
    }
}
