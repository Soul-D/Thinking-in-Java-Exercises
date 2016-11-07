//: net/mindview/util/Sets.java
package generics;

import generics.watercolors.Watercolors;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static generics.watercolors.Watercolors.*;
import static net.mindview.util.Print.print;

public class Ex17Sets {
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result;
        if (a instanceof EnumSet)
            result = ((EnumSet) a).clone();
        else
            result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    public static <T>
    Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result;
        if (a instanceof EnumSet)
            result = ((EnumSet) a).clone();
        else
            result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    // Subtract subset from superset:
    public static <T> Set<T>
    difference(Set<T> superset, Set<T> subset) {
        Set<T> result;
        if (superset instanceof EnumSet)
            result = ((EnumSet) superset).clone();
        else
            result = new HashSet<T>(superset);
        result.removeAll(subset);
        return result;
    }

    // Reflexive--everything not in the intersection:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
} ///:~

class Ex17Test {
    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print(Ex17Sets.union(set1,set2));
        print(Ex17Sets.intersection(set1,set2));
        print(Ex17Sets.difference(set1,set2));
    }
}