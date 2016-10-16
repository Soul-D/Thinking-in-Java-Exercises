package holding;

import java.util.*;

public class Ex19 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        set.add(5);
        set.add(4);
        set.add(1);
        set.add(7);
        set.add(3);
        System.out.println(set);
        Integer[] keys = set.toArray(new Integer[0]);
        Arrays.sort(keys);
        Set<Integer> lset = new LinkedHashSet<Integer>();
        for(int key : keys){
            lset.add(key);
        }
        System.out.println(lset);
        lset.clear();
        for (int i = keys.length-1;i>=0;i--){
            lset.add(keys[i]);
        }
        System.out.println(lset);
    }
}
