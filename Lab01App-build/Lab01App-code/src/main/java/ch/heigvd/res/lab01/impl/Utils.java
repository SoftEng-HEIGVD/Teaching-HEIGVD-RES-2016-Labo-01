package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

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
  public static String[] getNextLine(String lines) {
	  String[] lignes = new String[2];
	  String[] separators = {"\n","\r","\r\n"};
	  int pos = Integer.MAX_VALUE;
	  for (String separator : separators) 
	  {
		int tmp = lines.indexOf(separator);
		if(tmp != -1 && tmp < pos){
			pos = tmp + separator.length();
		}
	  }
	  if(pos != Integer.MAX_VALUE)
	  {
		  System.out.println("pos = " + pos);
		  lignes[0] = lines.substring(0,pos);
		  lignes[1] = lines.substring(pos);
	  }
	  else
	  {
		  lignes[0] = "";
		  lignes[1] = lines;
	  }
	  System.out.println(lines + " separated in ");
	  System.out.println(lignes[0]);
	  System.out.println(lignes[1]);
	  
	  return lignes;
  }

}
