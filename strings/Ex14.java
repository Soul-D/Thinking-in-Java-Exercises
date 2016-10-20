//: strings/SplitDemo.java
package strings; /* Added by Eclipse.py */

import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

public class Ex14 {
  public static void main(String[] args) {
    String input =
      "This!!unusual use!!of exclamation!!points";
    print(Arrays.toString(input.split("!!")));
    print(Arrays.toString(input.split("!!",3)));
  }
} /* Output:
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation!!points]
*///:~
