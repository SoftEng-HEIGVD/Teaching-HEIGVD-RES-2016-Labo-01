package ch.heigvd.res.lab01.impl.explorers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

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
        visitor.visit(rootDirectory);
        File[] files;
        ArrayList<File> directories = new ArrayList<>();

        if(rootDirectory.isDirectory()) {
            files = rootDirectory.listFiles();
            Arrays.sort(files); //ApplicationTest requires this to be sorted because why not

            //First all files
            for(File file : files) {
                if(file.isFile())
                    explore(file, visitor);
                else
                    directories.add(file);
            }

            //Then the rest
            for(File dir : directories) {
                explore(dir, visitor);
            }
        }
    }

}
