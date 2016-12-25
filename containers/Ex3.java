package containers;

import net.mindview.util.Countries;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Ex3 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        System.out.println(set);
        set = new LinkedHashSet<String>();
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        System.out.println(set);
        set = new TreeSet<String>();
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        System.out.println(set);
    }
}
