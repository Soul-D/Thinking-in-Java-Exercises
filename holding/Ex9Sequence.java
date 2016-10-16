//: innerclasses/Sequence.java
package holding; /* Added by Eclipse.py */
// Holds a sequence of Objects.

import java.util.Iterator;

public class Ex9Sequence {
  private Object[] items;
  private int next = 0;
  public Ex9Sequence(int size) { items = new Object[size]; }
  public void add(Object x) {
    if(next < items.length)
      items[next++] = x;
  }
  public Iterator<Object> iterator(){
    return new Iterator<Object>() {
      int current = 0;
      public boolean hasNext() {
        return current != items.length;
      }

      public Object next() {
        return items[current++];
      }

      public void remove() {

      }
    };
  }


  public static void main(String[] args) {
    Ex9Sequence ex9Sequence = new Ex9Sequence(10);
    for(int i = 0; i < 10; i++)
      ex9Sequence.add(Integer.toString(i));
    Iterator iterator = ex9Sequence.iterator();
    while(iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
