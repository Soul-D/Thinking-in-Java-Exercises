package generics;

import java.util.ArrayList;
import java.util.List;

public class Ex27CovariantLists {
    public static void main(String[] args) {
        //List<Number> numbers = new ArrayList<Integer>();
        List<? extends Number> numbers = new ArrayList<Integer>();
        //numbers.add(13);
        Number n = numbers.get(0);
    }
}
