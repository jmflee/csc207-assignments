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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import exceptions.InvalidScholarHTMLException;

/**
 * Class Name: UrlProcessor processes the html file/url and gets the contents of
 * the file
 */
public class UrlProcessor {

  private StringBuilder html = new StringBuilder();
  private boolean fromWeb;

  /**
   * Processes a local file or website url and gets it's contents
   * 
   * @param urlString is the name of the local file or the url
   * @return html.toString() is the contents of the file
   * @throws IOException 
   */
  public String getHTML(String urlString)
      throws InvalidScholarHTMLException, Exception {
    // create object to store html source text as it is being collected
    StringBuilder html = new StringBuilder();
    // open connection to given url or file
    URL url = (urlString.contains("https://")) ? new URL(urlString)
        : new File(urlString).toURI().toURL();
    this.fromWeb = // Checks if the input is from
        (urlString.contains("https://")) ? true : false;
    // create BufferedReader to buffer the given url's HTML source
    BufferedReader htmlbr =
        new BufferedReader(new InputStreamReader(url.openStream()));
    String line;
    // read each line of HTML code and store in StringBuilder
    while ((line = htmlbr.readLine()) != null) {
      html.append(line);
    } // End loop
    htmlbr.close();
    String content = html.toString();
    // Throws an error if the file isn't from a Google Scholar file
    if (!content.contains("Google Scholar")) {
      throw new InvalidScholarHTMLException(urlString);
    }
    // convert StringBuilder into a String and return it
    return html.toString();
  } // End method

  /**
   * Returns whether the input was a url or a local file
   * 
   * @return this.fromWeb is a boolean of whether the link was url or a file
   */
  public boolean getWeb() {
    return this.fromWeb;
  } // End method
} // End class
