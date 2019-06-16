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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import driver.UrlProcessor;
import driver.InfoScraper;
import exceptions.InvalidScholarHTMLException;

/**
 * Class Name: InfoScraperTest tests the class InfoScraper
 */
public class InfoScraperTest {

  UrlProcessor url;
  String[] actual;
  String[] expected;
  List<String> actualAuthors;
  List<String> expectedAuthors;
  InfoScraper scraper;
  String input = "";
  String content = "";

  @Before
  public void setUp() {
    url = new UrlProcessor();
    scraper = new InfoScraper(null, false);
    actualAuthors = new ArrayList();
    expectedAuthors = new ArrayList();
  } // End method

  @Test
  public void scrapeNoCoauthors() throws Exception {
    // Scrapes info from an html with no co authors from a local file
    input = "rs.html";
    expected = new String[] {"rahul sawhney", "33", "1",
        "1-   On fast exploration in 2D and 3D terrains with multiple robots"
            + "\n\t2-   Sonic Grid: an auditory interface for the visually"
            + " impaired to navigate GUI-based environments\n\t3-   Multi"
            + " robotic exploration with communication"
            + " requirement to a fixed base station",
        "33", "0"};
    // Scrapes local info
    scraper = new InfoScraper(url.getHTML(input), false);
    actual = scraper.getInfo(); // Gets relevant info
    actualAuthors = scraper.getCoAuthorNames(); // Gets co-authors (none)
    for (int info = 0; info < actual.length; info++) {
      assertEquals(expected[info], actual[info]); // Compares info
    } // End loop
    for (int info = 0; info < actualAuthors.size(); info++) {
      // Compares co-authors
      assertEquals(expectedAuthors.get(info), actualAuthors.get(info));
    } // End loop
  } // End method


  @Test
  public void scrapeCoauthors() throws Exception {
    // Scrapes info from an html with co authors from a online file
    expectedAuthors.add("Deepak Jagdish");
    expectedAuthors.add("Michael Massimi");
    input = "https://mcs.utm.utoronto.ca/~abbas/a1.html";
    expected = new String[] {"Abbas Attarwala", "17", "0",
        "1-   Real time collaborative video annotation using Google App"
            + " Engine and XMPP protocol\n\t2-   Reading together as a"
            + " Leisure Activity: Implications for E-reading\n\t3-   An"
            + " accessible, large-print, listening and talking"
            + " e-book to support families reading together",
        "16", "2"};
    scraper = new InfoScraper(url.getHTML(input), true);
    actual = scraper.getInfo(); // Gets relevant info
    actualAuthors = scraper.getCoAuthorNames(); // Gets co-author names
    for (int info = 0; info < actual.length; info++) {
      // Compares info
      assertEquals(expected[info], actual[info]);
    } // End loop
    for (int info = 0; info < expectedAuthors.size(); info++) {
      // Compares co-authors
      assertEquals(expectedAuthors.get(info), actualAuthors.get(info));
    } // End loop
  } // End method
} // End class
