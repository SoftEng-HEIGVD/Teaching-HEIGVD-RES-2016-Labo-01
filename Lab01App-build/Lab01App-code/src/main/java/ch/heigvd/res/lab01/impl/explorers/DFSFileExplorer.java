package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {

        // mark the current directory as visited
        vistor.visit(rootDirectory);

        /*
         * I test if the current directory is empty on return;
         */
        if (rootDirectory.listFiles() == null) {
            return;
        }

        /*
         *  he purpose of the dfs is browse a file directory for each aborescence
         * there we must move all files in the directory and each time we find a 
         * directory is carried no treatment if there is a file called the visit
         * method parameter passing the filename
         */
        for (File file : rootDirectory.listFiles()) {
            if (!file.isDirectory()) {
                vistor.visit(file);
            }
        }

        /*
         * the purpose of the dfs is browse a file directory for each aborescence
         * therefore we must move all files in the directory and each time it 
         * finds a file is performed no treatment if there is a directory called
         *the method explore
         */
        for (File file1 : rootDirectory.listFiles()) {
            if (file1.isDirectory()) {
                explore(file1, vistor);
            }

        }
    }
}
