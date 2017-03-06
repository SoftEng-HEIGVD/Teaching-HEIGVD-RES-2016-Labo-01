/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : 1
 File         : NoOpFileTransformer.java
 Author       : Antoine Drabble
 Date         : 10.03.2016
 Goal         : FileTransformer class which applies no filters to the writer
 -----------------------------------------------------------------------------------
*/
package ch.heigvd.res.lab01.impl.transformers;

import java.io.Writer;

/**
 * This class returns a writer without any decorator. When an instance of
 * this class is passed to a file system explorer, it will simply duplicate
 * the content of the input file into the output file.
 * 
 * @author Olivier Liechti
 */
public class NoOpFileTransformer extends FileTransformer {

    /**
     * Doesn't decorate the writer
     * 
     * @param writer
     * @return 
     */
    @Override
    public Writer decorateWithFilters(Writer writer) {
        /*
         * The NoOpFileTransformer does not apply any transformation of the character stream
         * (no uppercase, no line number, etc.). So, we don't need to decorate the writer connected to
         * the output file at all. Just uncomment the following line and get rid of the UnsupportedOperationException and
         * you will be all set.
         */
        return writer;
    }

}
