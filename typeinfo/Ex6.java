//: typeinfo/Shapes.java
package typeinfo; /* Added by Eclipse.py */

import java.util.Arrays;
import java.util.List;

enum PossibleShapes{
  CIRCLE, SQUARE, TRIANGLE
}

public class Ex6 {
  static void highlight(List<Shape> shapeList, PossibleShapes ps){
    for (Shape shape : shapeList){
      if (shape.getClass().getSimpleName().equalsIgnoreCase(ps.toString())){
        shape.highlighted = true;
      }
      else {
        shape.highlighted = false;
      }
    }
  }

  static void highlight2(List<Shape> shapeList, Class clazz){
    for (Shape shape : shapeList){
      if (clazz.isInstance(shape)){
        shape.highlighted = true;
      }
      else {
        shape.highlighted = false;
      }
    }
  }

  public static void main(String[] args) {
    List<Shape> shapeList = Arrays.asList(
      new Circle(), new Square(), new Triangle()
    );
    highlight(shapeList, PossibleShapes.SQUARE);
    for(Shape shape : shapeList)
      shape.draw();
    highlight2(shapeList, Circle.class);
    for(Shape shape : shapeList)
      shape.draw();
  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
