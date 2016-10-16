package holding;

import java.util.*;

import static net.mindview.util.Print.print;

public class Ex6ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
        List<String> strings = new ArrayList<String>(Arrays.<String>asList("1","2","3","11","22","12","123"));
        print("1: " + strings);
        String h = "13";
        strings.add(h); // Automatically resizes
        print("2: " + strings);
        print("3: " + strings.contains(h));
        strings.remove(h); // Remove by object
        String p = strings.get(2);
        print("4: " +  p + " " + strings.indexOf(p));
        String cymric = "3";
        print("5: " + strings.indexOf(cymric));
        print("6: " + strings.remove(cymric));
        // Must be the exact object:
        print("7: " + strings.remove(p));
        print("8: " + strings);
        strings.add(3, "666"); // Insert at an index
        print("9: " + strings);
        List<String> sub = strings.subList(1, 4);
        print("subList: " + sub);
        print("10: " + strings.containsAll(sub));
        Collections.sort(sub); // In-place sort
        print("sorted subList: " + sub);
        // Order is not important in containsAll():
        print("11: " + strings.containsAll(sub));
        Collections.shuffle(sub, rand); // Mix it up
        print("shuffled subList: " + sub);
        print("12: " + strings.containsAll(sub));
        List<String> copy = new ArrayList<String>(strings);
        sub = Arrays.asList(strings.get(1), strings.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);
        copy = new ArrayList<String>(strings); // Get a fresh copy
        copy.remove(2); // Remove by index
        print("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        print("15: " + copy);
        copy.set(1, "11"); // Replace an element
        print("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        print("17: " + copy);
        print("18: " + strings.isEmpty());
        strings.clear(); // Remove all elements
        print("19: " + strings);
        print("20: " + strings.isEmpty());
        strings.addAll(Arrays.asList("9","8","7","6","5"));
        print("21: " + strings);
        Object[] o = strings.toArray();
        print("22: " + o[3]);
        String[] pa = strings.toArray(new String[0]);
        print("23: " + pa[3]);
    }
}
