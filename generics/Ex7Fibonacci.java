package generics;

import java.util.Iterator;

import static net.mindview.util.Print.printnb;

public class Ex7Fibonacci implements Iterable<Integer>{
    private int count;

    public Ex7Fibonacci(int count) {
        this.count = count;
    }

    private Fibonacci fibonacci = new Fibonacci();

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() {
                return count>0;
            }

            public Integer next() {
                count--;
                return fibonacci.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class Ex7Test{
    public static void main(String[] args) {
        for(int i : new Ex7Fibonacci(18))
            printnb(i + " ");
    }
}