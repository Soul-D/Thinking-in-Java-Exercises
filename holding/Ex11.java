package holding;

import java.util.*;

public class Ex11 {
    static void print(Collection collection){
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}

class Ex11Test{
    public static void main(String[] args) {
        List list = Arrays.asList("13","12","17","1","5");
        Collection c1 = new ArrayList(list);
        Collection c2 = new LinkedList(list);
        Collection c3 = new HashSet(list);
        Collection c4 = new LinkedHashSet(list);
        Collection c5 = new TreeSet(list);
        Ex11.print(c1);
        Ex11.print(c2);
        Ex11.print(c3);
        Ex11.print(c4);
        Ex11.print(c5);
    }
}
