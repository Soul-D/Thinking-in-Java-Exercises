//: holding/Statistics.java
package holding; /* Added by Eclipse.py */
// Simple demonstration of HashMap.

import java.util.*;

class Occurence implements Comparable<Occurence>{
  int number;
  int count;

  public Occurence(int number) {
    this.number = number;
    count=1;
  }

  public int compareTo(Occurence o) {
    return -new Integer(count).compareTo(o.count);
  }

  @Override
  public String toString() {
    return  "number=" + number +
            ", count=" + count +
            '\n';
  }
}

public class Ex23 {
  public static void main(String[] args) {
    Random rand = new Random(47);
    Set<Occurence> occurences = new HashSet<Occurence>();
    label:
    for(int i = 0; i < 10000000; i++) {
      // Produce a number between 0 and 20:
      int r = rand.nextInt(20);
      for (Occurence occurence : occurences){
        if (occurence.number == r) {
          occurence.count += 1;
          continue label;
        }
      }
      occurences.add(new Occurence(r));
    }
    System.out.println(new TreeSet<Occurence>(occurences));
  }
} /* Output:
{15=497, 4=481, 19=464, 8=468, 11=531, 16=533, 18=478, 3=508, 7=471, 12=521, 17=509, 2=489, 13=506, 9=549, 6=519, 1=502, 14=477, 10=513, 5=503, 0=481}
*///:~
