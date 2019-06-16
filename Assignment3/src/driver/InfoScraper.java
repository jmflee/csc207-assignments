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
package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Name: InfoScraper Scrapes relevant info from a google scholar html file
 */
public class InfoScraper {
  private String url = "";
  private String output = "";
  private int counter = 0;
  private List<String> coAuthorNames = new ArrayList();
  private final String spacer =
      "-----------------------------------------------------------------------";
  private String contents = "";
  private boolean fromWeb = false;
  private Pattern patternObject;
  private Matcher matcherObject;
  private String reForAuthorExtraction;

  /**
   * Initializes the html file to be processed
   * 
   * @param inputs is the html document
   */
  public InfoScraper(String inputs, boolean web) {
    this.contents = inputs;
    this.fromWeb = web;
  }

  /**
   * Extracts an authors name from an html page with regex
   * 
   * @return this.output is the author's name
   */
  private String extractAuthorsName() {
    this.reForAuthorExtraction = // regex pattern
        (this.fromWeb) ? "<div id=\"gsc_prf_in\">(.*?)</div>"
            : "<span id=\"cit-name-display\" "
                + "class=\"cit-in-place-nohover\">(.*?)</span>";
    this.patternObject = Pattern.compile(reForAuthorExtraction);
    this.matcherObject = this.patternObject.matcher(this.contents);
    while (matcherObject.find()) { // Finds author's name with pattern
      this.output = matcherObject.group(1);
    } // End loop
    return this.output; // Returns author name
  } // End method


  /**
   * Extracts the total citations and i10 publications after 2009 from an html
   * page with regex
   * 
   * @return citePubs is an array of size 2 containing total citations and i10
   *         publications after 2009
   */
  private String[] extractCitationsAndTwoKNinePubs() {
    String[] citePubs = new String[2]; // Will hold all citaitons and i10 index
    this.counter = 0; // Reset counter to zero
    this.reForAuthorExtraction = // Regex pattern
        (this.fromWeb) ? "<td class=\"gsc_rsb_std\">(\\d+)</td>"
            : "<td class=\"cit-borderleft cit-data\">(\\d+)</td>";
    this.patternObject = Pattern.compile(reForAuthorExtraction);
    this.matcherObject = this.patternObject.matcher(this.contents);
    while (matcherObject.find()) {
      this.counter++;
      if (this.counter == 1) { // First find is always the total citations
        citePubs[0] = matcherObject.group(1);
      } else if (counter == 6) { // Sixth entry is the i10 after 2009
        citePubs[1] = matcherObject.group(1);
      } // End if
    } // End loop
    return citePubs;
  } // End method

  /**
   * Extracts the first three publications from an html file
   * 
   * @return this.output is the output for the first three publications
   */
  private String extractFirstThreePubs() {
    this.output = "";
    this.counter = 0;
    this.reForAuthorExtraction = // Regex pattern
        (this.fromWeb) ? "<a href=\".*?\" class=\"gsc_a_at\">(.*?)</a>"
            : "<a href=\".*?\" class=\"cit-dark-large-link\">(.*?)</a>";
    this.patternObject = Pattern.compile(reForAuthorExtraction);
    this.matcherObject = this.patternObject.matcher(this.contents);
    while (matcherObject.find()) {
      this.counter++;
      if (this.counter <= 3) {
        this.output += this.counter + "-   " + matcherObject.group(1) + "\n\t";
      }
    } // End loop
    return this.output.substring(0, this.output.length() - 2);
  } // End method

  /**
   * Extracts the total citations from the first five papers
   * 
   * @return cites is the amount of citations from the first five papers
   */
  private String extractTotalPaperCites() {
    int cites = 0; // Amount of initial citations before processing
    this.counter = 0;
    this.reForAuthorExtraction = // Regex pattern
        (this.fromWeb) ? "<a href=\".*?\" class=\"gsc_a_ac\">(\\d+)</a>"
            : "<a class=\"cit-dark-link\" href=\".*?\">(\\d+)</a>";
    this.patternObject = Pattern.compile(reForAuthorExtraction);
    this.matcherObject = this.patternObject.matcher(this.contents);
    while (matcherObject.find()) {
      this.counter++; // Counter adds
      if (this.counter <= 5) { // Captures only the first 5 citations
        cites += Integer.parseInt(matcherObject.group(1)); // Adds to total
      } // End if
    } // End loop
    return cites + "";
  } // End method

  /**
   * Extracts the # of co authors and their names from an html file
   * 
   * @return cites is the amount of co-authors in the html document
   */
  private String extractCoAuthors() {
    this.counter = 0; // Resets counter to zero
    this.reForAuthorExtraction = // Regex pattern
        (this.fromWeb) ? "<a class=\"gsc_rsb_aa\" href=\".*?\">(.*?)</a>"
            : "<a class=\"cit-dark-link\" href=\".*?\" title=\".*?\">(.*?)</a>";
    this.patternObject = Pattern.compile(reForAuthorExtraction);
    this.matcherObject = this.patternObject.matcher(this.contents);
    while (matcherObject.find()) {
      if (!matcherObject.group(1).equals("Citations")) {
        // Doesn't add duplicates
        if (!this.coAuthorNames.contains(matcherObject.group(1))) {
          this.counter++; // Only increase authors when it isn't a dud input
          this.coAuthorNames.add(matcherObject.group(1)); // Add authors
        }
      } // End if
    } // End loop
    return this.counter + "";
  } // End method

  /**
   * Returns a list of unsorted co-authors
   * 
   * @return this.coAuthorNames is a list of all the co-authors
   */
  public List<String> getCoAuthorNames() {
    return this.coAuthorNames; // Returns list
  } // End method

  /**
   * Returns all relevant information aside from co-authors
   * 
   * @return info[] is an array of all the relevant information from the html
   *         file with the exception of co-author names
   */
  public String[] getInfo() {
    String citations = extractCitationsAndTwoKNinePubs()[0];
    String iTen = extractCitationsAndTwoKNinePubs()[1];
    // Fills array with relevant info
    String[] info = {extractAuthorsName(), citations, iTen,
        extractFirstThreePubs(), extractTotalPaperCites(), extractCoAuthors()};
    return info; // Returns relevant info
  } // End method
} // End class
