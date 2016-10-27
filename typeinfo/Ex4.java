//: typeinfo/Shapes.java
package typeinfo; /* Added by Eclipse.py */

public class Ex4 {
  public static void main(String[] args) {
    Shape shape = new Rhomboid();
    Rhomboid rhomboid = (Rhomboid)shape;
    Circle circle = null;
    if (shape instanceof Circle){
      circle = (Circle)shape;
    }
    System.out.println(circle);
  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
