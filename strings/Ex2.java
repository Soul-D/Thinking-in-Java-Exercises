//: strings/InfiniteRecursion.java
package strings; /* Added by Eclipse.py */
// Accidental recursion.
// {RunByHand}

import java.util.ArrayList;
import java.util.List;

public class Ex2 {
  public String toString() {
    return " InfiniteRecursion address: " + super.toString() + "\n";
  }
  public static void main(String[] args) {
    List<Ex2> v =
      new ArrayList<Ex2>();
    for(int i = 0; i < 10; i++)
      v.add(new Ex2());
    System.out.println(v);
  }
} ///:~
