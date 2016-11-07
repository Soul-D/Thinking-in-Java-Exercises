//: generics/GenericCast.java
package generics; /* Added by Eclipse.py */

import java.util.ArrayList;
import java.util.List;

class Ex33FixedSizeStack<T> {
    private int size;
    private List<T> storage;

    public Ex33FixedSizeStack(int size) {
        storage = new ArrayList<T>();
        this.size = size;
    }

    public void push(T item) throws FixedSizeStackIsFull {
        if (storage.size() < size)
            storage.add(item);
        else
            throw new FixedSizeStackIsFull();
    }

    public T pop() throws FixedSizeStackIsEmpty {
        if (storage.size() > 0)
            return storage.get(0);
        else
            throw new FixedSizeStackIsEmpty();
    }
}

class FixedSizeStackIsFull extends Exception {
}

class FixedSizeStackIsEmpty extends Exception {
}

class Ex33GenericCast {
    public static final int SIZE = 10;

    public static void main(String[] args) throws FixedSizeStackIsFull, FixedSizeStackIsEmpty {
        Ex33FixedSizeStack<String> strings = new Ex33FixedSizeStack<String>(SIZE);
        for (String s : "A B C D E F G H I J K".split(" "))
            strings.push(s);
        for (int i = 0; i < SIZE + 1; i++) {
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}



