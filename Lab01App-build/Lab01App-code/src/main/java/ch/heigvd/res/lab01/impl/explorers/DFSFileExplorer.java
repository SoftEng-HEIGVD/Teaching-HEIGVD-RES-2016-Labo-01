package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author David Truan
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        visitor.visit(rootDirectory);

        // we check if we are in a directory
        if(rootDirectory.isDirectory()) {
            
            List<File> files = new ArrayList<>();
            List<File> directories = new ArrayList<>();

            // check if it is not empty
            if (rootDirectory.listFiles() == null)
                return;

            // for each file or directory we put it in the corresponding list
            for (File f : rootDirectory.listFiles()) {
                if (f.isDirectory()) {
                    directories.add(f);

                } else {
                    files.add(f);
                }
            }

            // we sort the two lists to be comptaible with all OS
            // then we visit the files and explore the directories
            Collections.sort(files);
            Collections.sort(directories);
            
            for(File f : files) {
                visitor.visit(f);
            }
            for(File d : directories) {
                explore(d, visitor);
            }
        }
    }

}
