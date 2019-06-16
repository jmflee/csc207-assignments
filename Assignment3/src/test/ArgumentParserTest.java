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

import org.junit.Before;
import org.junit.Test;

import driver.ArgumentParser;
import exceptions.WrongArgumentsException;

/**
 * Class Name: ArgumentParserTest tests the class ArgumentParser
 */
public class ArgumentParserTest {

  ArgumentParser a;
  String expectedError;
  String actualError;
  boolean expected;
  boolean actual;
  String[] input;

  @Before
  public void setUp() {
    a = new ArgumentParser();
    expected = false;
    actual = false;
  } // End method

  @Test
  public void zeroArguments() {
    // Tests for an error when an array of size zero is entered
    input = new String[0];
    expectedError = "Error: 0 arguments entered: Invalid amount of argument\n";
    try {
      a.checkInput(input);
    } catch (WrongArgumentsException e1) {
      actualError = e1.getError();
      assertEquals(expectedError, actualError);
    } // End try
  } // End method
  
  @Test
  public void oneArguments() {
    // Tests when an array of size one is entered
    input = new String[1];
    expected = true;
    try {
      actual = a.checkInput(input);
      assertEquals(expected, actual);
    } catch (WrongArgumentsException e1) {
      System.out.println("This expected is true");
    } // End try
  } // End method
  
  @Test
  public void twoArguments() {
    // Tests when an array of size two is entered
    input = new String[2];
    expected = true;
    try {
      actual = a.checkInput(input);
      assertEquals(expected, actual);
    } catch (WrongArgumentsException e1) {
      System.out.println("This expected is true");
    } // End try
  } // End method
  
  @Test
  public void greaterTwoArguments() {
    // Tests for an error when an array larger then 2 is entered
    input = new String[3];
    expectedError = "Error: 3 arguments entered: Invalid amount of argument\n";
    try {
      a.checkInput(input);
    } catch (WrongArgumentsException e1) {
      actualError = e1.getError();
      assertEquals(expectedError, actualError);
    } // End try
  } // End method
} // End class
