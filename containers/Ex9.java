package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.SortedSet;
import java.util.TreeSet;

public class Ex9 {
    public static void main(String[] args) {
        SortedSet<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(CollectionData.list(new RandomGenerator.String(),10));
        System.out.println(set);
    }
}
