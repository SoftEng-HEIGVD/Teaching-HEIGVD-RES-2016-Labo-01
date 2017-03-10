package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @modify Thibaud Besseau
 */
public class FileNumberingFilterWriter extends FilterWriter
{

	private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
	private Integer lineNumber = 1;
	private boolean WasCarriageReturn;

	public FileNumberingFilterWriter(Writer out)
	{
		super(out);
	}

	@Override
	public void write(String str, int off, int len) throws IOException
	{
		write(str.toCharArray(), off, len);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException
	{
		for (int i = off; i < (off + len); i++)
		{
			write((int) cbuf[i]);
		}
	}

	@Override
	public void write(int c) throws IOException
	{
		if (lineNumber == 1 || WasCarriageReturn && c != '\n')
		{
			out.write(Integer.toString(lineNumber++) + '\t');
		}

		out.write(c);

		//test if CarriageReturn
		if (c == '\r')
		{
			WasCarriageReturn = true;
		}
		else
		{
			WasCarriageReturn = false;
		}

		//if return line
		if (c == '\n')
		{
			out.write(Integer.toString(lineNumber++) + '\t');
		}
	}

}