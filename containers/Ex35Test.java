package containers;

import java.util.*;

import static containers.MapPerformance.tests;

public class Ex35Test {
    public static void main(String[] args) {
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
        Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
        Tester.run(new Hashtable<Integer, Integer>(), tests);
        Tester.run(new Ex17SlowMap<Integer, Integer>(), tests);
    }
}
