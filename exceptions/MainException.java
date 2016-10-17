//: exceptions/MainException.java
package exceptions; /* Added by Eclipse.py */
import java.io.*;

public class MainException {
  // Pass all exceptions to the console:
  public static void main(String[] args) throws Exception {
    // Open the file:
    FileInputStream file =
      new FileInputStream("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\exceptions\\MainException.java");
    // Use the file ...
    // Close the file:
    file.close();
  }
} ///:~
