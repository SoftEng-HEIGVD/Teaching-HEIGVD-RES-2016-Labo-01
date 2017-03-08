package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
        
        if (rootDirectory == null) {
            throw new UnsupportedOperationException("rootDirectory not existing");

        }
        vistor.visit(rootDirectory);
        if (rootDirectory.isDirectory()) {
            ArrayList<File> arrayDirectories = new ArrayList<>();
            File[] filesAndDirectories = rootDirectory.listFiles();
            Arrays.sort(filesAndDirectories);
            
            if (filesAndDirectories != null) {

                for (File f : filesAndDirectories) {//subdirectories
                    //vistor.visit(f);
                    if (f.isDirectory()) {
                        arrayDirectories.add(f);
                        //explore(f, vistor);
                    } else {
                        explore(f, vistor);
                    }
                }
                for (File af : arrayDirectories) {
                    explore(af, vistor);
                }
            }

        }   //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

}
