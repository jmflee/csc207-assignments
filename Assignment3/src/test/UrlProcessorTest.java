// **********************************************************
// Assignment3: 1001175346
// UTORID user_name: leejos14
//
// Author: Joseph Lee
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************

package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;

import driver.*;
import exceptions.InvalidScholarHTMLException;

import org.junit.Test;

/**
 * Class Name: UrlProcessorTest tests the class UrlProcessor
 */
public class UrlProcessorTest {

  UrlProcessor p;
  String expected = "";
  String actual = "";
  String input = "";

  @Before
  public void setUp() throws Exception {
    p = new UrlProcessor();
    PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
    writer.write("this is not a scholar file"); // File contents
    writer.close(); // Closes the writer
  } // End method

  @Test(expected = InvalidScholarHTMLException.class)
  public void notWebScholar() throws InvalidScholarHTMLException, Exception {
    // Gets the html of a non-scholar file from the internet
    expected = "Error: https://www.google.ca: Invalid scholar file\n";
    p.getHTML("https://www.google.ca");
  } // End method

  @Test(expected = InvalidScholarHTMLException.class)
  public void notLocalScholar() throws InvalidScholarHTMLException, Exception {
    // Gets the html of a local non-scholar file
    expected = "Error: test.txt: Invalid scholar file\n";
    p.getHTML("test.txt");
  } // End method

  @Test(expected = Exception.class)
  public void fileNotExistScholar() throws Exception {
    // Gets the contents of a file that does not exist
    p.getHTML("nonExistantFile");
  } // End method

  @Test(expected = Exception.class)
  public void invalidUrlScholar()
      throws InvalidScholarHTMLException, Exception {
    // Gets the contents of a url that does not exist
    p.getHTML("https://www.fakewebsite.fake");
  } // End method

  @Test
  public void localScholar() throws Exception {
    // Tests the contents of a local scholar
    actual = p.getHTML("sample1.html");
    expected = p.getHTML("sample1.html");
    assertEquals(expected, actual);
  } // End method

  @Test
  public void webScholar() throws Exception {
    // Gets the contents of an online scholar
    input = "https://mcs.utm.utoronto.ca/~abbas/a1.html";
    actual = p.getHTML(input);
    expected = p.getHTML(input);
    assertEquals(expected, actual);
  } // End method

  @After
  public void close() {
    new File("test.txt").delete(); // Deletes test file
  } // End method
} // End class
