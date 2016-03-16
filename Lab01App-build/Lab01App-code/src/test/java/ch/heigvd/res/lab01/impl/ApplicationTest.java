/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.lab01.impl;

import ch.heigvd.res.lab01.quotes.Quote;
import java.io.Writer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Norah
 */
public class ApplicationTest {
    
    public ApplicationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Application.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Application.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAndStoreQuotes method, of class Application.
     */
    @Test
    public void testFetchAndStoreQuotes() throws Exception {
        System.out.println("fetchAndStoreQuotes");
        int numberOfQuotes = 0;
        Application instance = new Application();
        instance.fetchAndStoreQuotes(numberOfQuotes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearOutputDirectory method, of class Application.
     */
    @Test
    public void testClearOutputDirectory() throws Exception {
        System.out.println("clearOutputDirectory");
        Application instance = new Application();
        instance.clearOutputDirectory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeQuote method, of class Application.
     */
    @Test
    public void testStoreQuote() throws Exception {
        System.out.println("storeQuote");
        Quote quote = null;
        String filename = "";
        Application instance = new Application();
        instance.storeQuote(quote, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printFileNames method, of class Application.
     */
    @Test
    public void testPrintFileNames() {
        System.out.println("printFileNames");
        Writer writer = null;
        Application instance = new Application();
        instance.printFileNames(writer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthorEmail method, of class Application.
     */
    @Test
    public void testGetAuthorEmail() {
        System.out.println("getAuthorEmail");
        Application instance = new Application();
        String expResult = "";
        String result = instance.getAuthorEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processQuoteFiles method, of class Application.
     */
    @Test
    public void testProcessQuoteFiles() throws Exception {
        System.out.println("processQuoteFiles");
        Application instance = new Application();
        instance.processQuoteFiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
