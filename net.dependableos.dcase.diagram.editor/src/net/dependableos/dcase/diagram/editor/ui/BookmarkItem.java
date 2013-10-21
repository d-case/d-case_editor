/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.editor.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import net.dependableos.dcase.diagram.common.exception.DcaseSystemException;
import net.dependableos.dcase.diagram.common.util.MessageTypeImpl;
import net.dependableos.dcase.diagram.editor.common.util.MessageWriter;
import net.dependableos.dcase.diagram.editor.message.Messages;

/**
 * A class that represents a bookmark item.
 */
public class BookmarkItem {
    
    /**
     * preference key for bookmarks.
     */
    private static final String PREFERENCE_ROOT = "net.dependableos.dcase.bookmarks"; //$NON-NLS-1$

    /**
     * A preference items key.
     */
    private static final String BOOKMARK_ITEMS_KEY = "BookmarkItems"; //$NON-NLS-1$

    /**
     * A empty string.
     */
    private static final String BLANK = ""; //$NON-NLS-1$
    
    /**
     * regular expression pattern to extract bookmarks from preference string.
     */
    private static final Pattern BOOKMARK_PATTERN = Pattern.compile("^\\[(.*)\\](.*)$"); //$NON-NLS-1$
    
    /**
     * the delimiter to separate bookmarks.
     */
    private static final String BOOKMARK_DELIMITER = "|"; //$NON-NLS-1$
    
    /**
     * the bookmark name.
     */
    private String name;
    
    /**
     * the URL.
     */
    private String url;
    
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
     * Returns the url.
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * Sets the url.
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Returns the list of bookmarks from Preferrence.
     * @return list of bookmarks.
     */
    public static List<BookmarkItem> getListFromPreferrence() {
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_ROOT);
        String storedString = store.get(BOOKMARK_ITEMS_KEY, BLANK);
        
        List<BookmarkItem> ret = new ArrayList<BookmarkItem>();
        
        if (storedString != null && storedString.length() > 0) {
            String[] bookmarkLines = storedString.split("\\" + BOOKMARK_DELIMITER); //$NON-NLS-1$
            for (String bookmarkLine : bookmarkLines) {
                
                Matcher matcher = BOOKMARK_PATTERN.matcher(bookmarkLine);
                if (matcher.matches()) {
                    BookmarkItem item = new BookmarkItem();
                    item.setName(matcher.group(1));
                    item.setUrl(matcher.group(2));
                    ret.add(item);
                } else {
                    throw new DcaseSystemException(
                            Messages.BookmarkItem_BookmarkInvalidErrorMessage,
                            null,
                            MessageTypeImpl.OPEN_BOOKMARK_INVALID_FAILED);
                }
            }
        }
        return ret;
    }
    
    /**
     * Saves the list of bookmarks to the preference.
     * @param bookmarkList list of bookmarks. 
     */
    public static void saveToPreferrence(List<BookmarkItem> bookmarkList) {
        
        StringBuilder sb = new StringBuilder(BLANK);
        
        for (BookmarkItem bookmark : bookmarkList) {
            sb.append(bookmark.toString());
            if (!bookmark.equals(bookmarkList.get(bookmarkList.size() - 1))) {
                sb.append(BOOKMARK_DELIMITER);
            }
        }
        
        IEclipsePreferences store = InstanceScope.INSTANCE
                .getNode(PREFERENCE_ROOT);
        try {
            store.put(BOOKMARK_ITEMS_KEY, sb.toString());
            store.flush();
        } catch (BackingStoreException e) {
            MessageWriter.showErrorMessageBox(
                    Messages.ErrorPreferenceStoreFlush);
        }
        
    }
    
    /**
     * Returns the string for saving into the preference.
     * @return the string for saving into the preference.
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(BLANK);
        if (getName() != null) {
            sb.append("[").append(getName()).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            sb.append("[").append(BLANK).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (getUrl() != null) {
            sb.append(getUrl());
        } else {
            sb.append(BLANK);
        }
        return sb.toString();
    }
}
