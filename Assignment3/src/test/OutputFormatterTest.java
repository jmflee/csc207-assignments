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

import org.junit.Before;
import org.junit.Test;

import driver.*;
import exceptions.InvalidScholarHTMLException;

/**
 * Class Name: OutputFormatterTest tests the OutputFormatter
 */
public class OutputFormatterTest {

  UrlProcessor url;
  InfoScraper scraper;
  OutputFormatter out;
  String expected = "";
  String actual = "";
  String input = "";

  @Before
  public void setUp() {
    url = new UrlProcessor();
    scraper = new InfoScraper(null, false);
    out = new OutputFormatter();
  } // End method

  @Test
  public void allInfo() throws Exception {
    // Tests the method output message from getOutput
    expected = "---------------------------------------------------------"
        + "--------------\n1. Name of Author:\n\trahul sawhney\n2. Number of"
        + " All Citations:\n\t33\n3. Number of i10-index after 2009:\n\t1\n4."
        + " Title of the first 3 publications:\n\t1-   On fast exploration"
        + " in 2D and 3D terrains with multiple robots\n\t2-   Sonic Grid:"
        + " an auditory interface for the visually impaired to navigate"
        + " GUI-based environments\n\t3-   Multi robotic exploration with"
        + " communication requirement to a fixed base station\n5. Total"
        + " paper citation (first 5 papers):\n\t33\n6. Total Co-Authors:"
        + "\n\t0\n\n------------------------------------------------------"
        + "-----------------\n7. Co-Author list sorted (Total: 0):\n";
    input = "rs.html";
    scraper = new InfoScraper(url.getHTML(input), false);
    out.setInfo(scraper.getInfo()); // Populate relevant info
    out.setAuthors(scraper.getCoAuthorNames()); // Populate co-authors
    actual = out.getOutput();
    assertEquals(expected, actual);
  } // End method

  @Test
  public void setError() throws Exception {
    // Gets the output with an error message
    expected = "---------------------------------------------------------"
        + "--------------\nError\n\n-------------------------------------"
        + "-----------------"
        + "-----------------\n7. Co-Author list sorted (Total: 0):\n";
    input = "rs.html";
    scraper = new InfoScraper(url.getHTML(input), false);
    out.setError("Error\n"); // Populate error
    out.setAuthors(scraper.getCoAuthorNames()); // Populate co-authors
    actual = out.getOutput();
    assertEquals(expected, actual);
  } // End method
} // End class
