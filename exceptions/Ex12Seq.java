//: innerclasses/Sequence.java
package exceptions; /* Added by Eclipse.py */
// Holds a sequence of Objects.

class SequenceFullException extends RuntimeException {
}

public class Ex12Seq {
    private Object[] items;
    private int next = 0;

    public Ex12Seq(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
        else
            throw new SequenceFullException();
    }

    private class SequenceEx12Selector implements Ex12Selector {
        private int i = 0;

        public boolean end() {
            return i == items.length;
        }

        public Object current() {
            return items[i];
        }

        public void next() {
            if (i < items.length) i++;
        }
    }

    public Ex12Selector selector() {
        return new SequenceEx12Selector();
    }

    public static void main(String[] args) {
        Ex12Seq sequence = new Ex12Seq(10);
        for (int i = 0; i < 11; i++)
            sequence.add(Integer.toString(i));
        Ex12Selector ex12Selector = sequence.selector();
        while (!ex12Selector.end()) {
            System.out.print(ex12Selector.current() + " ");
            ex12Selector.next();
        }
        System.out.println();
    }
}

interface Ex12Selector {
    boolean end();

    Object current();

    void next();
}