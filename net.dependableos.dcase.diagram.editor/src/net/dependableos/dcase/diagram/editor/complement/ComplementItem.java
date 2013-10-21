/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.complement;

/**
 * A class that represents an item to complement.
 */
public class ComplementItem {
    
    /**
     * the path to the model.
     */
    private String path;
    
    /**
     * the subject.
     */
    private String subject;

    /**
     * Creates the item and initializes it.
     * 
     * @param path the path to the model.
     * @param subject the subject.
     */
    public ComplementItem(String path, String subject) {
        super();
        this.path = path;
        this.subject = subject;
    }

    /**
     * Returns the path to the model.
     * 
     * @return the path to the model.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the subject.
     * 
     * @return the subject.
     */
    public String getSubject() {
        return subject;
    }

}
