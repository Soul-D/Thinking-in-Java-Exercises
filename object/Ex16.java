//: initialization/Overloading.java
package object; /* Added by Eclipse.py */
// Demonstration of both constructor
// and ordinary method overloading.

import static net.mindview.util.Print.*;

/** Just tree*/
class Tree {
    /** Tree's height*/
  int height;
    /** Seeding a tree with 0 height*/
  Tree() {
    print("Planting a seedling");
    height = 0;
  }
    /** Seeding a tree with custom height
     * @param initialHeight Desired height*/
  Tree(int initialHeight) {
    height = initialHeight;
    print("Creating new Tree that is " +
            height + " feet tall");
  }
    /** Printing tree's height*/
  void info() {
    print("Tree is " + height + " feet tall");
  }
    /** Printing custom string + tree's height*/
  void info(String s) {
    print(s + ": Tree is " + height + " feet tall");
  }
}

/** Class starting main*/
public class Ex16 {
    /** Nothing to say
     * @param args No use*/
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++) {
      Tree t = new Tree(i);
      t.info();
      t.info("overloaded method");
    }
    // Overloaded constructor:
    new Tree();
  }
} /* Output:
Creating new Tree that is 0 feet tall
Tree is 0 feet tall
overloaded method: Tree is 0 feet tall
Creating new Tree that is 1 feet tall
Tree is 1 feet tall
overloaded method: Tree is 1 feet tall
Creating new Tree that is 2 feet tall
Tree is 2 feet tall
overloaded method: Tree is 2 feet tall
Creating new Tree that is 3 feet tall
Tree is 3 feet tall
overloaded method: Tree is 3 feet tall
Creating new Tree that is 4 feet tall
Tree is 4 feet tall
overloaded method: Tree is 4 feet tall
Planting a seedling
*///:~



