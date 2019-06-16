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
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.*;
import write.WriteConsole;
import write.WriteFile;


/**
 * Class Name: MyParser Parses a google scholar url/html file and finds the
 * scholars name, total citations, i10 index after 2009, the first three
 * publication titles, sum of first 5 citations, # of co-authors and all the
 * co-author's names
 */
public class MyParser {

  private static List<String> allCoAuthors = new ArrayList(); // Contains all
                                                              // co-authors
  private static OutputFormatter o = new OutputFormatter(); // Formats output
  private static UrlProcessor process = new UrlProcessor(); // Gets a html's
                                                            // contents
  private String output = "";
  private static boolean validInput = false;

  public static void main(String[] args) {
    manager(args); // Sents arguments to manger
  } // End method

  /**
   * Manages the inputs in such a way that the proper output is created
   * @param inputs are the arguments sent form the user
   */
  private static void manager(String[] inputs) {
    try {
      validInput = ArgumentParser.checkInput(inputs);
      String inputFiles[] = inputs[0].split(","); // Split up html pages
      for (String inputFile : inputFiles) { // Cycles through html documents
        try {
          InfoScraper i = // Initialize InfoScraper
              new InfoScraper(process.getHTML(inputFile), process.getWeb());
          o.setInfo(i.getInfo()); // Concatenate relevant info to output
          allCoAuthors.addAll(i.getCoAuthorNames()); // Concatenate co-authors
        } catch (InvalidScholarHTMLException e1) { // Is not a scholar
          o.setError(e1.getError());
        } catch (Exception e2) { // Error message
          o.setError("Error: " + inputFile + ": Invalid file entered \n");
        } // End try
      } // End loop
      o.setAuthors(allCoAuthors); // Concatenate co-authors to output
    } catch (WrongArgumentsException e3) {
      o.setError(e3.getError());
    } finally {
      try {
        if (inputs.length == 1 || !validInput) {// Prints the contents
          WriteConsole.outputPrint(o.getOutput());
        } else if (inputs.length == 2) { // Creates a file with the contents
          WriteFile.outputWrite(o.getOutput(), inputs[1].trim());
        } // End if
      } catch (Exception e4) { // File names with invalid charcters error
        System.out.println("Error: Invalid characters in file name");
      } // End try
    } // End try
  } // End method
} // End class
