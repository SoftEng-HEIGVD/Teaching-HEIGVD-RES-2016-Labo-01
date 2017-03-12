package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        // start by visiting:
        visitor.visit(rootDirectory);


        //Need to explor the file before the folder : need to filter

        File [] folder = rootDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        File [] file = rootDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });

        //visit the file if there is one
        if(file!=null)
        for(File f : file)
            visitor.visit(f);

        //visit the folder and be sure there is one.
        if(folder==null) return;
        for(File d : folder)
            explore(d,visitor);


    }

}
