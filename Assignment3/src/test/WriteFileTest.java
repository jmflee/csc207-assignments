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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import write.WriteFile;

/**
 * Class Name: WriteFileTest tests the WriteFile class
 */
public class WriteFileTest {

  WriteFile write;
  String input;
  String fileName;
  String actual;
  String expected;
  String line;
  BufferedReader br;
  StringBuilder sb;
  File file;

  @Before
  public void setUp() {
    write = new WriteFile();
    sb = new StringBuilder();
  } // End method

  @Test
  public void newFile() throws Exception {
    // Tests the creation of a new file
    input = "hello \n\t\nwor\nld";
    expected = input + "\n";
    fileName = "test.txt"; // File does not exist
    write.outputWrite(input, fileName); // Create new file
    br = new BufferedReader(new FileReader(fileName)); // Enter new file
    String line = br.readLine();
    while (line != null) {
      sb.append(line);
      sb.append(System.lineSeparator());
      line = br.readLine();
    } // End loop
    actual = sb.toString(); // Get file contents
    assertEquals(expected, actual);

  } // End test case

  @Test
  public void existingFile() throws Exception {
    // Tests rewriting an existing file
    input = "rewriting he\n\n\n\t\t\tllo world with this thing\n\n\n\n\n\n\n";
    expected = input;
    fileName = "test.txt";
    write.outputWrite(input, fileName);
    br = new BufferedReader(new FileReader(fileName));
    line = br.readLine();
    while (line != null) {
      sb.append(line);
      sb.append(System.lineSeparator());
      line = br.readLine();
    } // End loop
    actual = sb.toString();
    assertEquals(expected, actual);
  } // End test case

  @Test
  public void newLineFileName() throws Exception {
    // Tests rwhere the fileName has a \n in it
    input = "rewriting he\n\n\n\t\t\tllo world with this thing\n\n\n\n\n\n\n";
    expected = input;
    fileName = "tes\nt.txt";
    write.outputWrite(input, fileName);
    br = new BufferedReader(new FileReader(fileName));
    line = br.readLine();
    while (line != null) {
      sb.append(line);
      sb.append(System.lineSeparator());
      line = br.readLine();
    } // End loop
    actual = sb.toString();
    assertEquals(expected, actual);
  } // End test case

  @After
  public void close() throws IOException {
    new File("test.txt").delete(); // Deletes test file
    new File("tes\nt.txt").delete(); // Deletes test file
    br.close(); // Close buffered reader
  } // End method
} // End class
