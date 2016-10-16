package holding;

import java.util.LinkedList;
import java.util.ListIterator;

public class Ex14 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < 3; i++) {
            ListIterator<Integer> iterator = list.listIterator(list.size()/2);
            iterator.add(i);
        }
        System.out.println(list);
    }
}
