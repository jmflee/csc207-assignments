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

import exceptions.WrongArgumentsException;

/**
 * Class Name: InputParser Parses the arguments sent to the main method
 */
public class ArgumentParser {

  private static boolean validArgument = false;

  /**
   * Checks the input arguments and returns true if it is valid
   */
  public static boolean checkInput(String args[])
      throws WrongArgumentsException {
    boolean validArgument = (args.length == 1 || args.length == 2);
    if (!validArgument) {
      throw new WrongArgumentsException(args.length);
    }
    return validArgument;
  }
}
