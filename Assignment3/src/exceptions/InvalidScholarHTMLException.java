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

package exceptions;

/**
 * Class Name: InvalidScholarHTMLException is an error when the url is
 * not a Google Scholar file
 */
public class InvalidScholarHTMLException extends Exception {
  
  /** The error. */
  private String error = "";

  /**
   * ICreates an error output
   * 
   * @param input error content
   */
  public InvalidScholarHTMLException (String input) {
    this.error =  "Error: " + input + ": Invalid scholar file\n";
  }

  /**
   * Gets the error.
   *
   * @return this.error is the error message
   */
  public String getError() {
    return this.error;
  }
}
