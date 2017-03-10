package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 */

/**
 * @author Olivier Liechti
 * @modify Thibaud Besseau
 */
public class DFSFileExplorer implements IFileExplorer
{

	@Override
	public void explore(File rootDirectory, IFileVisitor vistor)
	{
		//visit the file rootDirectory
		vistor.visit(rootDirectory);

		if (rootDirectory.isDirectory())
		{
			// Create an array with all files and directories who are in rootDirectory
			File[] fileArray = rootDirectory.listFiles();
			// Create a arrayList to store all directories contain in rootDirectory
			ArrayList<File> listSubDirectories = new ArrayList<>();

			// Sort the files list to fix a bug
			Arrays.sort(fileArray);

			// Visit all files
			for (File file : fileArray)
			{
				if (file.isFile())
				{
					vistor.visit(file);
				}
				else if (file.isDirectory())
				{
					//add all subDirectories in list
					listSubDirectories.add(file);
				}
			}
			//visit all subdirectories contain in the list
			for (File directory : listSubDirectories)
			{
				explore(directory, vistor);
			}
		}
	}
}
