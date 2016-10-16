package holding;

import java.util.*;
import static net.mindview.util.Print.print;

public class Ex5ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
        List<Integer> integers = new ArrayList<Integer>(Arrays.<Integer>asList(1,2,3,4,5,4,3,2,1));
        print("1: " + integers);
        Integer h = 13;
        integers.add(h); // Automatically resizes
        print("2: " + integers);
        print("3: " + integers.contains(h));
        integers.remove(h); // Remove by object
        Integer p = integers.get(2);
        print("4: " +  p + " " + integers.indexOf(p));
        Integer cymric = 3;
        print("5: " + integers.indexOf(cymric));
        print("6: " + integers.remove(cymric));
        // Must be the exact object:
        print("7: " + integers.remove(p));
        print("8: " + integers);
        integers.add(3, 666); // Insert at an index
        print("9: " + integers);
        List<Integer> sub = integers.subList(1, 4);
        print("subList: " + sub);
        print("10: " + integers.containsAll(sub));
        Collections.sort(sub); // In-place sort
        print("sorted subList: " + sub);
        // Order is not important in containsAll():
        print("11: " + integers.containsAll(sub));
        Collections.shuffle(sub, rand); // Mix it up
        print("shuffled subList: " + sub);
        print("12: " + integers.containsAll(sub));
        List<Integer> copy = new ArrayList<Integer>(integers);
        sub = Arrays.asList(integers.get(1), integers.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);
        copy = new ArrayList<Integer>(integers); // Get a fresh copy
        copy.remove(2); // Remove by index
        print("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        print("15: " + copy);
        copy.set(1, 11); // Replace an element
        print("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        print("17: " + copy);
        print("18: " + integers.isEmpty());
        integers.clear(); // Remove all elements
        print("19: " + integers);
        print("20: " + integers.isEmpty());
        integers.addAll(Arrays.asList(9,8,7,6,5));
        print("21: " + integers);
        Object[] o = integers.toArray();
        print("22: " + o[3]);
        Integer[] pa = integers.toArray(new Integer[0]);
        print("23: " + pa[3]);
    }
}
