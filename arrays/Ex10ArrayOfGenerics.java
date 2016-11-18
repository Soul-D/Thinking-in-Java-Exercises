//: arrays/ArrayOfGenerics.java
package arrays; /* Added by Eclipse.py */
// It is possible to create arrays of generics.

import java.util.ArrayList;
import java.util.List;

public class Ex10ArrayOfGenerics {

  public static void main(String[] args) {
    List<List<BerylliumSphere>> spheres =
      new ArrayList<List<BerylliumSphere>>(10);
    for(int i = 0; i < spheres.size(); i++)
      spheres.set(i, new ArrayList<BerylliumSphere>());
  }
} ///:~
