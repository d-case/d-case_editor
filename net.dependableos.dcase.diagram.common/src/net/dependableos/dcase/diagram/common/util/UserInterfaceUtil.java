/*
 * Copyright (C) Yutaka Matsuno 2010-2012 All rights reserved.
 */
package net.dependableos.dcase.diagram.common.util;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A utility class that handles UI.
 */
public final class UserInterfaceUtil {

    /**
     * A constructor.
     */
    private UserInterfaceUtil() {
    }

    /**
     * Returns the tree path that represents the specified file path.
     * 
     * @param filePath the file path.
     * @param root the workspace root.
     * @return the tree path.
     */
    public static TreePath convertFilePathToTreePath(String filePath,
            IWorkspaceRoot root) {
        TreePath result = null;
        // returns null if the file doesn't exist.
        if (filePath != null && filePath.trim().length() != 0) {
            if (isFileExist(filePath, root)) {
                result = new TreePath((new Path(filePath)).segments());
            }
        }
        return result;
    }

    /**
     * Tests whether the file exists.
     * 
     * @param filePath the file path.
     * @param root the workspace root.
     * @return true if and only if the file exists; false otherwise.
     */
    private static boolean isFileExist(String filePath, IWorkspaceRoot root) {
        boolean result = false;
        try {
            IPath path = root.getFile(new Path(filePath)).getLocation();
            if (path != null) {
                File file = path.toFile();
                if (file.exists()) {
                    result = true;
                }
            }
        } catch (IllegalArgumentException e) {
            result = false;
        }
        return result;
    }
    
    /**
     * Tests whether the file is opened with D-Case Editor.
     * 
     * @param file the file.
     * @return true if and only if the file is opened with D-Case Editor; false otherwise.
     */
    public static boolean isFileOpenedWithEditor(IFile file) {
        boolean result = false;
        IEditorReference[] editorReferences = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .getEditorReferences();
        for (IEditorReference reference : editorReferences) {
            try {
                IEditorInput editorInput = reference.getEditorInput();
                if (editorInput instanceof FileEditorInput) {
                    IFile editfile = ((FileEditorInput) editorInput).getFile();
                    if (file.equals(editfile)) {
                        result = true;
                        break;
                    }
                }
            } catch (PartInitException e) {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }
}
