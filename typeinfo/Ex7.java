//: typeinfo/SweetShop.java
package typeinfo; /* Added by Eclipse.py */
// Examination of the way the class loader works.

import java.util.ArrayList;
import java.util.List;

public class Ex7 {
  public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    List<Object> list = new ArrayList<Object>();
    for (String s : args) {
      list.add(Class.forName("typeinfo." + s).newInstance());
    }
  }
} /* Output:
inside main
Loading Candy
After creating Candy
Loading Gum
After Class.forName("Gum")
Loading Cookie
After creating Cookie
*///:~
