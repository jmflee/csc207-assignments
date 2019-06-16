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

import java.util.Collections;
import java.util.List;

/**
 * Class Name: OutputFormatter processes the output for the relevant info
 * obtained from the html file
 */
public class OutputFormatter {
  private final String spacer = // Divider
      "-----------------------------------------------------------------------";
  private String relevantInfo = "";
  private String allAuthors = "";
  private final String[] prefix =
      {"1. Name of Author:\n\t", "2. Number of All Citations:\n\t",
          "3. Number of i10-index after 2009:\n\t",
          "4. Title of the first 3 publications:\n\t",
          "5. Total paper citation (first 5 papers):\n\t",
          "6. Total Co-Authors:\n\t"};

  /**
   * Compiles all relevant info aside from co-author names to the output string
   * 
   * @param data is an array of relevant info aside from co-author names
   */
  public void setInfo(String[] data) {
    this.relevantInfo += spacer + "\n"; // Adds spacer
    for (int info = 0; info < prefix.length; info++) { // Compiles relevant info
      this.relevantInfo += prefix[info] + data[info] + "\n";
    } // End loop
  } // End method

  /**
   * Compiles all the co-authors to the output string
   * 
   * @param allAuthors is a list of the co-author names
   */
  public void setAuthors(List<String> allAuthors) {
    int authors = 0;
    Collections.sort(allAuthors);
    for (int author = 0; author < allAuthors.size(); author++) {
      if (!this.allAuthors.contains(allAuthors.get(author))) {
        // Doesn't add duplicates
        this.allAuthors += allAuthors.get(author) + "\n"; // Add authors
        authors++;
      }
    } // End loop
    this.allAuthors = "\n" + this.spacer + "\n7. Co-Author list sorted (Total: "
        + authors + "):" + "\n" + this.allAuthors;
  } // End method

  /**
   * Compiles error messages to the output string
   * 
   * @param error is the error message
   */
  public void setError(String error) {
    this.relevantInfo += spacer + "\n" + error;
  } // End method

  /**
   * Returns the the final output string
   * 
   * @return this.output is the final output string
   */
  public String getOutput() {
    return this.relevantInfo + this.allAuthors;
  } // End method
} // End class
