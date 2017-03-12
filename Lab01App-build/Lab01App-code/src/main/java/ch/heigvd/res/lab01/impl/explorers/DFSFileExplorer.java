package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Baeriswyl Julien (julien.baeriswyl@heig-vd.ch) [MODIFIED BY, on 2017-03-10]
 */
public class DFSFileExplorer implements IFileExplorer
{
    @Override
    public void explore (File rootDirectory, IFileVisitor visitor) // JBL: renaming vistor -> visitor
    {
        // JBL: visit root node
        visitor.visit(rootDirectory);

        File[] subfiles = rootDirectory.listFiles();
        if (subfiles == null)
        {
            return;
        }

        // JBL: ensure filenames list is sorted
        Arrays.sort(subfiles, new Comparator<File>()
            {
                @Override
                public int compare (File o1, File o2)
                {
                    return o1.getPath().compareTo(o2.getPath());
                }
            }
        );
        
        // JBL: iterate through files (normal, hidden, ...)
        for (File file : subfiles)
        {
            if (!file.isDirectory())
            {
                visitor.visit(file);
            }
        }
        
        // JBL: iterate through directories
        for (File file : subfiles)
        {
            if (file.isDirectory())
            {
                explore(file, visitor); // JBL: recursion
            }
        }
    }
}
