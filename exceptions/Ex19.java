//: exceptions/LostMessage.java
package exceptions; /* Added by Eclipse.py */
// How an exception can be lost.

public class Ex19 {
  void f() throws VeryImportantException {
    throw new VeryImportantException();
  }
  void dispose() throws HoHumException {
    throw new HoHumException();
  }
  public static void main(String[] args) {
    try {
      Ex19 lm = new Ex19();
      try {
        lm.f();
      } finally {
        try {
          lm.dispose();
        }
        catch (HoHumException e){
          e.printStackTrace();
        }
      }
    } catch(Exception e) {
      System.out.println(e);
    }
  }
} /* Output:
A trivial exception
*///:~
