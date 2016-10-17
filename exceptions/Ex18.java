//: exceptions/LostMessage.java
package exceptions; /* Added by Eclipse.py */
// How an exception can be lost.

public class Ex18 {
  void f() throws VeryImportantException {
    throw new VeryImportantException();
  }
  void dispose() throws HoHumException {
    throw new HoHumException();
  }
  public static void main(String[] args) {
    try {
      Ex18 lm = new Ex18();
      try {
        lm.f();
      } finally {
        try {
          lm.dispose();
        }
        finally {
          throw new Exception();
        }
      }
    } catch(Exception e) {
      System.out.println(e);
    }
  }
} /* Output:
A trivial exception
*///:~
