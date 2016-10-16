//: innerclasses/Sequence.java
package holding; /* Added by Eclipse.py */
// Holds a sequence of Objects.

import java.util.ArrayList;
import java.util.List;

interface Ex3Selector {
  boolean end();
  Object current();
  void next();
}

public class Ex3Sequence {
  private List items;
  public Ex3Sequence() { items = new ArrayList(); }
  public void add(Object x) {
    items.add(x);
  }
  private class SequenceSelector implements Ex3Selector {
    private int i = 0;
    public boolean end() { return i == items.size(); }
    public Object current() { return items.get(i); }
    public void next() { if(i < items.size()) i++; }
    public Ex3Sequence sequence() {
      return Ex3Sequence.this;
    }
  }
  public Ex3Selector selector() {
    return new SequenceSelector();
  }
  public Ex3Selector reverseSelector() {return new Ex3Selector() {
    private int i = items.size();
    public boolean end() {
      return i == 0;
    }

    public Object current() {
      return items.get(i-1);
    }

    public void next() {
      if (i > 0) i--;
    }
  };}
  public static void main(String[] args) {
    Ex3Sequence ex3Sequence = new Ex3Sequence();
    for(int i = 0; i < 100; i++)
      ex3Sequence.add(Integer.toString(i));
    Ex3Selector ex3Selector = ex3Sequence.selector();
    while(!ex3Selector.end()) {
      System.out.print(ex3Selector.current() + " ");
      ex3Selector.next();
    }
    System.out.println();
    Ex3Selector ex3Selector2 = ex3Sequence.reverseSelector();
    while(!ex3Selector2.end()) {
      System.out.print(ex3Selector2.current() + " ");
      ex3Selector2.next();
    }
    System.out.println();
    SequenceSelector ss = ex3Sequence.new SequenceSelector();
    Ex3Sequence s = ss.sequence();
    System.out.println(ex3Sequence == s);
  }
} /* Output:
0 1 2 3 4 5 6 7 8 9
*///:~
