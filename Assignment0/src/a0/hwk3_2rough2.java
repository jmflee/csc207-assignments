package a0;

public class hwk3_2rough2 {
  public static void main(String[] args) {
    String input = args[0];
    subStrings(input);
  }

  public static void subStrings(String input) {
    for (int pos = 0; pos < input.length(); pos++) {
      String out[] = input.split("" + input.charAt(pos));
      if (input.length() == 1) {
        return;
      } else if (out.length != 1) {
        if (!out[0].equals("")) {
          System.out.println("{" + out[0] + ", " + out[1] + "}");
        }
        subStrings(out[0] + out[1]);
      }
    }
  }
}
