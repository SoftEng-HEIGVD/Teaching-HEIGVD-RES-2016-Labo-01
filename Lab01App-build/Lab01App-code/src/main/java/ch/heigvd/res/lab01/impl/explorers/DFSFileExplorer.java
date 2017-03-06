package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
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
  public void explore(File rootDirectory, IFileVisitor visitor) {
  	// visit the file
    visitor.visit(rootDirectory);

    // look for other files
    File[] files = rootDirectory.listFiles();
    if(files == null)
    	return;

    // the test indicates that we must visit files before sub-folders,
    // therefore we sort the list to put files before
    Arrays.sort(files, new Comparator<File>() {
    	public int compare(File a, File b) {
    		return (b.isFile() ? 1 : 0) - (a.isFile() ? 1 : 0);
    	}
    });

    // recursive call for sub-folders/files
    for(File file : files)
    	explore(file, visitor);
  }

}
