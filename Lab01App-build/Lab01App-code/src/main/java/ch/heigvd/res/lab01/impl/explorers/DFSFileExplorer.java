package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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
		if (rootDirectory == null) return;

		vistor.visit(rootDirectory);

		if (rootDirectory.isDirectory()) {
			/*
				The recursion should be as simple as:
				for (File f : rootDirectory.listFiles()) explore(f, vistor);

				However the test theApplicationShouldBeAbleToGenerateTheListOfFileNames
				assumes that files are visited before subdirectories.

				I had no choice!
			 */

			ArrayList<File> files = new ArrayList<>();
			Collections.addAll(files, rootDirectory.listFiles());

			files.sort((a, b) -> {
				// Files come first
				if (a.isFile() != b.isFile()) {
					return a.isFile() ? -1 : 1;
				}

				// Lexicographical ordering
				return a.getName().compareTo(b.getName());
			});

			for (File child : files) {
				explore(child, vistor);
			}
		}
	}
}
