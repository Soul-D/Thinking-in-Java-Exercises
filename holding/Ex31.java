//: polymorphism/shape/RandomShapeGenerator.java
// A "factory" that randomly creates shapes.
package holding;

import polymorphism.Ex4_Rectangle;
import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

import java.util.Iterator;
import java.util.Random;

public class Ex31 implements Iterable<Shape>{
  private int iteratorNumber;
  private Random rand = new Random(47);

  public Ex31(int iteratorNumber) {
    this.iteratorNumber = iteratorNumber;
  }

  public Shape next() {
    switch(rand.nextInt(4)) {
      default:
      case 0: return new Circle();
      case 1: return new Square();
      case 2: return new Triangle();
      case 3: return new Ex4_Rectangle();
    }
  }

  public Iterator<Shape> iterator() {
    return new Iterator<Shape>() {
      int count = 0;
      public boolean hasNext() {
        return count<iteratorNumber;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public Shape next() {
        count++;
        return next();
      }
    };
  }
} ///:~
