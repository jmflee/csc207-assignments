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
 * Class Name: WrongArgumentsExceptionException is an error when an incorrect
 * amount of arguments are inputted
 */
public class WrongArgumentsException extends Exception {

  /** The error. */
  private String error = "";

  /**
   * Creates an error output
   * 
   * @param input error content
   */
  public WrongArgumentsException(int input) {
    this.error = "Error: " + input
        + " arguments entered: Invalid amount of argument\n";
  }

  /**
   * Gets the error.
   *
   * @return this.error is the amount of errors
   */
  public String getError() {
    return this.error;
  }
}
