package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

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
    public void explore(File rootDirectory, IFileVisitor vistor) {

        vistor.visit(rootDirectory);                //visit the root folder
        File[] paths = rootDirectory.listFiles();   //List of Files (files + folder in the folder)

    /* Nothing in the folder : End of the recurtion */
        if (paths == null)
            return;

        Arrays.sort(paths); //Sort the list

    /* 1st treat files */
        for (File f : paths) {
            if (f.isFile())
                vistor.visit(f);
        }

    /*2n treat Direcoties */
        for (File f : paths) {
            if (f.isDirectory()) {
                explore(f, vistor);
            }
        }

    }

}
