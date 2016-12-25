package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

public class Ex27CountedString {
  private static List<String> created =    new ArrayList<String>();
  private String s;
  private int id = 0;
  public Ex27CountedString(String str) {
    s = str;
    created.add(s);
    for(String s2 : created)
      if(s2.equals(s))
        id++;
  }
  public String toString() {
    return "String: " + s + " id: " + id +
      " hashCode(): " + hashCode();
  }
  public int hashCode() {
    int result = 17;
    result = 37 * result + s.hashCode();
    return result;
  }
  public boolean equals(Object o) {
    return o instanceof Ex27CountedString &&
      s.equals(((Ex27CountedString)o).s) &&
      id == ((Ex27CountedString)o).id;
  }
  public static void main(String[] args) {
    Map<Ex27CountedString,Integer> map =
      new HashMap<Ex27CountedString,Integer>();
    Ex27CountedString[] cs = new Ex27CountedString[5];
    for(int i = 0; i < cs.length; i++) {
      cs[i] = new Ex27CountedString("hi");
      map.put(cs[i], i); // Autobox int -> Integer
    }
    print(map);
    for(Ex27CountedString cstring : cs) {
      print("Looking up " + cstring);
      print(map.get(cstring));
    }
  }
}
