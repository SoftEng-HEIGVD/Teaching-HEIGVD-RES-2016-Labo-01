package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti, Camilo Pineda Serna
 */
public class DFSFileExplorer implements IFileExplorer
{
    /**
     * explores (DFS pre-order) the file system from a specific node
     * and invokes the visitor.visit() methode to each node (even if it doesn't exist)
     * @param rootDirectory the directory where to start the traversal
     * @param vistor defines the operation to be performed on each file
     */
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor)
    {
        // there is a test for this :
        // if current node does not exist
        // visit anyway and stops iteration
        if (!rootDirectory.exists())
        {
            vistor.visit(rootDirectory);
            return;
        }

        // visit current node
        vistor.visit(rootDirectory);

        // recursion
        // 1) if a file was reached, halt recursion
        if (rootDirectory.isFile())
        {
            return;
        }
        else // 2) else, continue recursion
        {
            File[] currentFiles = rootDirectory.listFiles();
            // we must go deeper
            for (File currentFile : currentFiles)
            {
                explore(currentFile, vistor);
            }
        }
    }
}
