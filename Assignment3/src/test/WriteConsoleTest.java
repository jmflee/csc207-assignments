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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import write.WriteConsole;

/**
 * Class Name: WriteConsoleTest tests the class WriteConsole for sysout
 */
public class WriteConsoleTest {

  WriteConsole print;
  String input;
  String actual;
  String expected;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    print = new WriteConsole();
    System.setOut(new PrintStream(outContent));
  }
  
  @Test
  public void print() {
    // Tests a standard print message 
    expected = "hello world";
    input = expected;
    print.outputPrint(input);
    assertEquals(expected + "\n", outContent.toString());
  }

  @Test
  public void printNewline() {
    // Tests a standard print message with newlines
    expected = "hello\nworld \n\t";
    input = expected;
    print.outputPrint(input);
    assertEquals(expected + "\n", outContent.toString());
  }

  @After
  public void cleanUpStreams() {
      System.setOut(null);
  }

}
