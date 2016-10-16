package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Ex12 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(7,3,5,9,1,60,34));
        List<Integer> list2 = new ArrayList<Integer>(list.size());
        ListIterator<Integer> li = list.listIterator(list.size());
        while(li.hasPrevious()){
            list2.add(li.previous());
        }
        System.out.println(list2);
    }
}
