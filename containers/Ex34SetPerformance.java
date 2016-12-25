package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

import java.util.*;

public class Ex34SetPerformance {
    static List<Test<Set<String>>> tests =
            new ArrayList<Test<Set<String>>>();

    static {
        tests.add(new Test<Set<String>>("add") {
            int test(Set<String> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    Generator<String> gen = new CountingGenerator.String();
                    for (int j = 0; j < size; j++)
                        set.add(gen.next());
                }
                return loops * size;
            }
        });
        tests.add(new Test<Set<String>>("contains") {
            int test(Set<String> set, TestParam tp) {
                Generator<String> gen = new CountingGenerator.String();
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(gen.next());
                return loops * span;
            }
        });
        tests.add(new Test<Set<String>>("iterate") {
            int test(Set<String> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<String> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }

    public static void main(String[] args) {
        Tester.fieldWidth = 10;
        Ex34ListTester.run(new TreeSet<String>(), tests);
        Ex34ListTester.run(new HashSet<String>(), tests);
        Ex34ListTester.run(new LinkedHashSet<String>(), tests);
    }
}

class Ex34ListTester extends Tester<Set<String>>{
    public Ex34ListTester(Set<String> container, List<Test<Set<String>>> tests) {
        super(container, tests);
    }

    @Override
    protected Set<String> initialize(int size) {
        container.clear();
        container.addAll(CollectionData.list(new CountingGenerator.String(), size));
        return container;
    }

    public static void run(Set<String> container, List<Test<Set<String>>> tests){
        new Ex34ListTester(container, tests).timedTest();
    }
}