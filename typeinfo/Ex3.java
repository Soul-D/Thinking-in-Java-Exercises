//: typeinfo/Shapes.java
package typeinfo; /* Added by Eclipse.py */

class Rhomboid extends Shape {
  public String toString() { return "Rhomboid"; }
}

public class Ex3 {
  public static void main(String[] args) {
    Shape shape = new Rhomboid();
    Rhomboid rhomboid = (Rhomboid)shape;
    //circle = (Circle)shape;
  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
