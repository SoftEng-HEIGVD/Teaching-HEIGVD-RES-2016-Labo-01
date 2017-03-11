package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

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

        vistor.visit(rootDirectory);
        if (rootDirectory.isDirectory()) {
            File[] dirContent = rootDirectory.listFiles();
            ArrayList<File> files = new ArrayList<>();
            ArrayList<File> directories = new ArrayList<>();

            for (int i = 0; i < dirContent.length; i++) {
                if (dirContent[i].isFile()) {
                    files.add(dirContent[i]);
                } else if (dirContent[i].isDirectory()) {
                    directories.add(dirContent[i]);
                }
            }

            Comparator fileComparator = new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };

            files.sort(fileComparator);

            for (File f : files) {
                vistor.visit(f);
            }

            directories.sort(fileComparator);

            for (File d : directories) {
                explore(d, vistor);
            }
        }
    }

}
