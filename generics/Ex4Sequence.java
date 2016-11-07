//: innerclasses/Sequence.java
package generics; /* Added by Eclipse.py */
// Holds a sequence of Objects.

import java.util.ArrayList;
import java.util.List;

interface Ex4Selector<T> {
    boolean end();

    T current();

    void next();
}

public class Ex4Sequence<T> {
    private List<T> items;
    private int next = 0;
    private final int size;

    public Ex4Sequence(int size) {
        items = new ArrayList<T>();
        this.size = size;
    }

    public void add(T x) {
        if (next < size) {
            items.add(x);
            next++;
        }
    }

    private class SequenceEx4Selector implements Ex4Selector<T> {
        private int i = 0;

        public boolean end() {
            return i == size;
        }

        public T current() {
            return items.get(i);
        }

        public void next() {
            if (i < size) i++;
        }

        public Ex4Sequence sequence() {
            return Ex4Sequence.this;
        }
    }

    public Ex4Selector selector() {
        return new SequenceEx4Selector();
    }

    public static void main(String[] args) {
        Ex4Sequence<String> sequence = new Ex4Sequence<String>(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Ex4Selector ex4Selector = sequence.selector();
        while (!ex4Selector.end()) {
            System.out.print(ex4Selector.current() + " ");
            ex4Selector.next();
        }
        System.out.println();
        Ex4Sequence<Integer> sequence2 = new Ex4Sequence<Integer>(10);
        for (int i = 0; i < 10; i++)
            sequence2.add(i);
        Ex4Selector ex4Selector2 = sequence2.selector();
        while (!ex4Selector2.end()) {
            System.out.print(ex4Selector2.current() + " ");
            ex4Selector2.next();
        }
    }
}