// **********************************************************
// Assignment1:

// Student1: Joseph Lee
// UTOR user_name: leejos14
// UT Student #: 1001175346
// Author: Joseph Lee
//
// Student2: Taras Zelenenkyy
// UTOR user_name: zelenenk
// UT Student #: 1001400307
// Author: Taras Zelenenkyy
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************

package a1;

import java.io.*;

public class JShell {

  public static void main(String[] args) throws IOException {

    BufferedReader objReader =
        new BufferedReader(new InputStreamReader(System.in));
    String input = "";// first input
    // array of all accepted commands
    String inputs[] =
        {"mkdir", "cd", "ls", "pwd", "mv", "cp", "cat", "get", "echo"};
    // number of arguments for each corresponding commands
    int arguments[] = {1, 1, 0, 0, 2, 2, 1, 1, 3};

    
    while (0<1) {// read forever
      boolean valid = false;
      String tail = "";
      System.out.print("/# ");// path
      input = objReader.readLine();// input
      String commands[] = input.split("\\s+");// splits up commands
      if (commands[0].equals("exit")
            && commands.length == 1){
        break;
      }
      for (int user = 0; user < inputs.length; user++) {
        if (commands[0].equals(inputs[user])
            && arguments[user] == commands.length - 1) {// command search
          System.out.println(inputs[user]);// command
          valid = true;
          for (int arg = 0; arg < arguments[user]; arg++) {
            tail = tail + commands[arg + 1] + " "; // putting arguments together
          }
          if (arguments[user] > 0) {
            System.out.print(tail + "\n");// printing arguments
          }
        }
      }
      if (!valid && !input.replace(" ", "").equals("")) { // ignores "return as
                                                          // an invalid command
        System.out.println("Invalid command, please try again");
      }
    }

  }
}
