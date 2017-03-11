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
 * @author Aurelie Levy
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {

        //check if rootDirectory is ok to explore/visit
        if (rootDirectory == null) {
            throw new UnsupportedOperationException("rootDirectory not find");
        }
        
        ArrayList<File> arrayDirectories = new ArrayList<>();//to store subdirectories
        
        //all the content of rootDirectory (files AND subdirectories)
        File[] filesAndDirectories = rootDirectory.listFiles();

        vistor.visit(rootDirectory);

        //if rootDirectory is a directory, we have to find its files/subdirectories
        if (rootDirectory.isDirectory()) {

            //we sort the content of rootDirectory to explore/visit in the right order
            Arrays.sort(filesAndDirectories);

            if (filesAndDirectories != null) {//if there is smth in rootDirectory, we have to explore/visit

                for (File contentOfRoot : filesAndDirectories) {
                    if (contentOfRoot.isDirectory()) {//for the subdirectories
                        arrayDirectories.add(contentOfRoot);//we keep them because we have to do the files first
                    } else {
                        explore(contentOfRoot, vistor); //explore the files first (by recursion)
                    }
                }
                for (File subDirectories : arrayDirectories) {//explore the subdirectories then (by recursion)
                    explore(subDirectories, vistor);
                }
            }
        }   //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

}
