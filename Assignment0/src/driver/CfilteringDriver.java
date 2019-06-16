// **********************************************************
// Assignment0:
// UTOR user_name: leejos14
// UT Student #: 1001175346
// Author: Joseph Lee
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.Integer;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // Opens file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      /*
       * Creates a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // This is a blankline being read
      br.readLine();

      // Reads each line of movie ratings and populates the userMovieMatrix
      String row = "";
      // currentUser determines which user is being populated
      int currentUser = 0;
      while ((row = br.readLine()) != null) {
        // currentMovie determines which particular movie rating is populated in
        // the array
        int currentMovie = 0;
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        for (String singleRating : allRatings) {
          // Make the String number into an integer
          // Populates userMovieMatrix by individually filling each element in
          cfObject.populateUserMovieMatrix(currentUser, currentMovie++,
              Integer.parseInt(singleRating));
        }
        //
        currentUser++;
      }

      // Close the file
      fStream.close();
      // Calculates the similarity score
      cfObject.calculateSimilarityScore();
      // Prints out the userUserMatrix
      cfObject.printUserUserMatrix();
      // Prints similar users
      cfObject.findAndprintMostSimilarPairOfUsers();
      // Prints dissimilar users
      cfObject.findAndprintMostDissimilarPairOfUsers();

    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
