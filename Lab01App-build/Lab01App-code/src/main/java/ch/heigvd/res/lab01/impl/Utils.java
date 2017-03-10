package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 * @modify Thibaud Besseau
 */
public class Utils
{

	private static final Logger LOG = Logger.getLogger(Utils.class.getName());

	/**
	 * This method looks for the next new line separators (\r, \n, \r\n) to extract
	 * the next line in the string passed in arguments.
	 *
	 * @param lines a string that may contain 0, 1 or more lines
	 * @return an array with 2 elements; the first element is the next line with
	 * the line separator, the second element is the remaining text. If the argument does not
	 * contain any line separator, then the first element is an empty string.
	 */
	public static String[] getNextLine(String lines)
	{
		int indexEndLine;
		String[] data = new String[2];

		//find if \n exist
		indexEndLine = lines.indexOf("\n"); // for \n, \r\n
		//if \n doesn't exist find \r
		if (indexEndLine == -1)
		{
			indexEndLine = lines.indexOf("\r"); // for \r
		}
		//if \n and \r doesn't exist return data
		if (indexEndLine == -1)
		{
			data[0] = "";
			data[1] = lines;
			return data;
		}
		else
		{
			data[0] = lines.substring(0, indexEndLine + 1);
			if (lines.length() < indexEndLine + 1)
			{
				data[1] = "";
			}
			else
			{
				data[1] = lines.substring(indexEndLine + 1);
			}
		}
		return data;
	}

}

