//: polymorphism/PolyConstructors.java
package polymorphism; /* Added by Eclipse.py */
// Constructors and polymorphism
// don't produce what you might expect.
import static net.mindview.util.Print.*;

class Glyph {
  void draw() { print("Glyph.draw()"); }
  Glyph() {
    print("Glyph() before draw()");
    draw();
    print("Glyph() after draw()");
  }
}	

class RoundGlyph extends Glyph {
  private int radius = 1;
  RoundGlyph(int r) {
    radius = r;
    print("RoundGlyph.RoundGlyph(), radius = " + radius);
  }
  void draw() {
    print("RoundGlyph.draw(), radius = " + radius);
  }
}

class RectangularGlyph extends Glyph {
  private int width = 1;
  private int height = 1;

  public RectangularGlyph(int width, int height) {
    this.width = width;
    this.height = height;
    print("RectangularGlyph.RectangularGlyph(), width = " + width + " height = " + height);
  }

  void draw(){
    print("RectangularGlyph.draw(), width = " + width + " height = " + height);
  }
}

public class PolyConstructors {
  public static void main(String[] args) {
    new RoundGlyph(5);
    new RectangularGlyph(10,10);
  }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*///:~
