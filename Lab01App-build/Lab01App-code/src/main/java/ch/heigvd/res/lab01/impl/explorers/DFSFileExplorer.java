package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import com.sun.istack.internal.NotNull;

import java.io.File;

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
	  if(rootDirectory == null) return;

	  visitor.visit(rootDirectory); // Visit the file
	  if(rootDirectory.isDirectory()){ // If it's a directory, visit all the files in the directory
		 for(File f : rootDirectory.listFiles()){
			explore(f, visitor);
		 }
	  }
   }
}
