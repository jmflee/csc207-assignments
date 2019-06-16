// **********************************************************
// Assignment0:
// UTOR user_name: leejos14
// UT Student #: 101175346
// Author: Joseph Lee
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package a0;

import java.lang.Math;
import java.text.DecimalFormat;

public class Cfiltering {
  // hold an integer of the number of users
  private int numberOfUsers;
  // holds an integer of the number of movies
  private int numberOfMovies;
  // used to round numbers for output values
  private DecimalFormat four = new DecimalFormat("0.0000");
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private double userUserMatrix[][];

  // Default Constructor
  public Cfiltering() {
    // the amount of users by default is 1
    numberOfUsers = 1;
    // the amount of movies by default is 1
    numberOfMovies = 1;
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new double[1][1];
  }

  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    // constructs numberOfUsers
    this.numberOfUsers = numberOfUsers;
    // construct numberOfMovies
    this.numberOfMovies = numberOfMovies;
    // 2d matrix of size; numberOfUsers and numberOfMovies
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    // 2d matrix of size; numberOfUsers and numberofUsers
    userUserMatrix = new double[numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix.
   */

  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   */

  public void calculateSimilarityScore() {
    // Compiles all the columns for each user's similarity score
    for (int currentUser = 0; currentUser < numberOfUsers; currentUser++) {
      // Sends currentUser as an argument to be compared by other user scores
      userUserMatrix[currentUser] = getSimilarityColumn(currentUser);
    }
  }

  /**
   * Determines the similarity score for two users and returns the scores as a
   * single array. Similarity scores are stored in the variable named sum
   * 
   * @param currentUser The user in userUserMatrix being compared with.
   * @return column The column of similarity scores after the currentUser has
   *         been compared with all other users.
   */

  private double[] getSimilarityColumn(int currentUser) {
    // The size of the matrix column will always be the same size as the
    // numberOfUsers
    double column[] = new double[numberOfUsers];
    // Loops through all users in userUserMatrix
    for (int compareUser = 0; compareUser < numberOfUsers; compareUser++) {
      // Loops through all the movie ratings for the original user and the users
      // being compared with and calculates Euclidian distance
      for (int score = 0; score < numberOfMovies; score++) {
        // The Euclidian distance will be zero if the current user is being
        // compared with itself
        // ex. currentUser = compareUser = 0, therefore they are the same
        // If the users are not the same then the Euclidian will be calculated
        column[compareUser] = (currentUser == compareUser) ? 0
            : column[compareUser]
                + Math.pow((userMovieMatrix[currentUser][score]
                    - userMovieMatrix[compareUser][score]), 2);
      }
      // Calculates the similarity score
      column[compareUser] = Math.pow((1 + Math.sqrt(column[compareUser])), -1);
    }
    // Returns column of similarity scores for currentUser
    return column;
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */

  public void printUserUserMatrix() {
    System.out.println("\n\nuserUserMatrix is:");
    for (int user = 0; user < userUserMatrix.length; user++) {
      System.out.print("[");
      for (int score = 0; score < userUserMatrix[user].length; score++) {
        if (score == userUserMatrix[user].length - 1) {
          System.out.print(four.format(userUserMatrix[user][score]) + "]\n");
        } else {
          System.out.print(four.format(userUserMatrix[user][score]) + ", ");
        }
      }
    }
  }

  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   */

  public void findAndprintMostSimilarPairOfUsers() {
    // Prints out the similar users and score
    System.out.println(getSimilarity(true));
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostDissimilarPairOfUsers() {
    // Prints out the dissimilar users and score
    System.out.println(getSimilarity(false));
  }

  /**
   * This function creates the output string for similar or dissimilar users in
   * the userUserMatrix
   * 
   * @param similarityEnabled The status that determines if the user wants to
   *        find dissimilar users or similar users
   * @return similarUsers The output string for similar or dissimilar users
   */

  private String getSimilarity(boolean similarityEnabled) {
    // Sets the output prefix when the user wants to get similar or dissimilar
    // users
    String prefix = (similarityEnabled) ? "similar" : "dissimilar";
    // similarUsers is used for outputting the final results
    String similarUsers = "";
    // similarScore stores the similarity score of the (dis)similar users
    double similarScore = (similarityEnabled) ? 0 : 1.1;
    // Loops through the elements in userUserMatrix but only the elements
    // contained in from the top left to the bottom right
    // ex. For this given userUserMatrix only the digits with / in front will
    // be cycled through
    // [1.0000, /0.1380, /0.2171, /0.1285]
    // [0.1380, 1.0000, /0.1827, /0.1614]
    // [0.2171, 0.1827, 1.0000, /0.1250]
    // [0.1285, 0.1614, 0.1250, 1.0000]
    for (int currentUser = 0; currentUser < numberOfUsers; currentUser++) {
      for (int column = (1 + currentUser); column < numberOfUsers; column++) {
        if (similarityEnabled) {
          // This block is for calculating similar users
          if (userUserMatrix[currentUser][column] > similarScore) {
            // Rewrites the previous similarity score iff the new score is
            // higher
            similarScore = userUserMatrix[currentUser][column];
            similarUsers =
                ("User" + (currentUser + 1) + " and User" + (column + 1));
          } else if (userUserMatrix[currentUser][column] == similarScore) {
            // Adds additional users that have the same similarity score
            similarUsers = (similarUsers + ", \nUser" + (currentUser + 1)
                + " and User" + (column + 1));
          }
        }
        // This block is for calculating dissimilar users
        else {
          if (userUserMatrix[currentUser][column] < similarScore) {
            // Rewrites the previous similarity score iff the new score is
            // lower
            similarScore = userUserMatrix[currentUser][column];
            similarUsers =
                ("User" + (currentUser + 1) + " and User" + (column + 1));
          } else if (userUserMatrix[currentUser][column] == similarScore) {
            // Adds additional users that have the same similarity score
            similarUsers = (similarUsers + ", \nUser" + (currentUser + 1)
                + " and User" + (column + 1));
          }
        }
      }
    }
    similarUsers = ("\n\nThe most " + prefix
        + " pairs of users from above userUserMatrix are: \n" + similarUsers
        + " \nwith similarity score of " + four.format(similarScore));
    return similarUsers;
  }
}
