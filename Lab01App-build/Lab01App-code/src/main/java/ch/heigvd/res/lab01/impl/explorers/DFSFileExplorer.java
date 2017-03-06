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
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

   @Override
   public void explore(File rootDirectory, IFileVisitor visitor) {
	  if (rootDirectory == null) return;

	  // Visit the file
	  visitor.visit(rootDirectory);

	  // If it's a directory, we need to explore it
	  if (rootDirectory.isDirectory()) {

		 File[] files = rootDirectory.listFiles();
		 Arrays.sort(files, (a, b) -> a.getPath().compareTo(b.getPath()));
		 // First we visit the files
		 for (File f : files) {
			if (!f.isDirectory()) visitor.visit(f);
		 }

		 // Then we visit the directories
		 for (File f : files) {
			if (f.isDirectory()) explore(f, visitor);
		 }
	  }
   }
}
