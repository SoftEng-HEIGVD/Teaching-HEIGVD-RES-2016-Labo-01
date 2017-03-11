package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Note : my intention is to only use the "basic" write(int) method below for the actual conversion and writing,
 * having the code written only once and calling this method in the other write()
 *
 * @author Olivier Liechti
 * @author Camilo Pineda Serna
 */
public class UpperCaseFilterWriter extends FilterWriter
{

    public UpperCaseFilterWriter(Writer wrappedWriter)
    {
        super(wrappedWriter);
    }


    /**
     * Converts a character (as an int) to upper case and writes it with FilterWriter.
     *
     * @param c the Unicode code point of the character to convert
     * @throws IOException
     */
    @Override
    public void write(int c) throws IOException
    {
        // This FilterWrite's decorator is only responsible for "upper casing" the char.
        // The writing part is done by the super-class.
        super.write(Character.toUpperCase(c));
    }

    /**
     * Uses this.write(int) to convert an array of char (between \u0000 and \uffff) to upper case
     * and writes it with FilterWriter.
     * The "restriction" is not that strong, because the whole range of the basic char can be converted
     * see : http://stackoverflow.com/a/2006544
     *
     * @param cbuf the array of char to convert to upper case
     * @param off  the offset of the first char in cbuf to convert
     * @param len  the number of char to convert
     * @throws IOException
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        for (int currentCharPos = off; currentCharPos < off + len; ++currentCharPos)
        {
            this.write((int) cbuf[currentCharPos]);
        }
    }


    /**
     * Uses this.write(int) to convert a String to upper case and writes it with FilterWriter.
     *
     * @param str the String to convert to upper case
     * @param off the offsef of the first char to convert
     * @param len the number of char to convert
     * @throws IOException
     */
    @Override
    public void write(String str, int off, int len) throws IOException
    {
        for (int currentCharPos = off; currentCharPos < off + len; ++currentCharPos)
        {
            this.write(str.codePointAt(currentCharPos));
        }
    }


}
