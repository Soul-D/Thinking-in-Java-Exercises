//: typeinfo/Shapes.java
package typeinfo; /* Added by Eclipse.py */

import java.util.Arrays;
import java.util.List;

public class Ex5 {

  static void rotateShape(Shape shape){
    if (!(shape instanceof Circle)){
      System.out.println("Rotate " + shape.getClass().getSimpleName());
    }
  }

  public static void main(String[] args) {
    List<Shape> shapeList = Arrays.asList(
      new Circle(), new Square(), new Triangle()
    );
    for(Shape shape : shapeList)
      rotateShape(shape);
  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
