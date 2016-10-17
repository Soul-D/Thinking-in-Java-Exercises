//: exceptions/MainException.java
package exceptions; /* Added by Eclipse.py */

import java.io.FileInputStream;

public class Ex26 {
  // Pass all exceptions to the console:
  public static void main(String[] args) throws Exception {
    // Open the file:
    FileInputStream file =
      new FileInputStream("Hollow.java");
    // Use the file ...
    // Close the file:
    file.close();
  }
} ///:~
