package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

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
     * and invokes the visitor.visit() method to each node (even if it doesn't exist)
     * first on the files, then on the directories
     *
     * @param rootDirectory the directory where to start the traversal
     * @param vistor        defines the operation to be performed on each file
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

        // visit the node
        vistor.visit(rootDirectory);

        // listing of all the nodes inside of the current rootFile
        File[] nodesInsideRoot = rootDirectory.listFiles();
        Arrays.sort(nodesInsideRoot); // because listFiles() doesn't sort nor guarantee a specific order

        // first the files (not the directories) are visited)
        for (File currentNode : nodesInsideRoot)
        {
            if (currentNode.isFile())
            {
                vistor.visit(currentNode);
            }

        }

        // then the directories  are explored. they will be visited by the recursive explore() call
        for (File currentNode : nodesInsideRoot)
        {
            if (currentNode.isDirectory())
            {
                // we must go deeper
                explore(currentNode, vistor);
            }

        }
    }
}
