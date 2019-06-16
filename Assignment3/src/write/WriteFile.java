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
package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Class Name: WriteFile creates a file with the output of the parser
 */
public class WriteFile {

  /**
   * Prints the output to the console
   * 
   * @param output is the output to be saved into the file
   * @param fileName is the name of the file
   * @throws UnsupportedEncodingException
   */
  public static void outputWrite(String output, String fileName)
      throws Exception {
    // Initialize the print writer with the file name
    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
    writer.write(output); // Input the output into the file
    writer.close(); // Closes the writer
  }
}
