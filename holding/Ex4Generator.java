package holding;

import java.util.*;

import static holding.Ex4Generator.next;

public class Ex4Generator {
    private static int i = 0;
    private static List<String> list = Arrays.asList("Gial Ackbar", "Padmé Amidala", "Wedge Antilles", "Appo");

    static String next(){
        if (i == list.size())
            i = 0;
        return list.get(i++);
    }
}

class Ex4Test {
    static Collection fill(Collection collection, int k){
        for (int i = 0; i < k; i++){
            collection.add(next());
        }
        return collection;
    }

    public static void main(String[] args) {
        System.out.println(fill(new ArrayList(),10));
        System.out.println(fill(new LinkedList(),10));
        System.out.println(fill(new HashSet(),10));
        System.out.println(fill(new LinkedHashSet(),10));
        System.out.println(fill(new TreeSet(),10));
    }
}
