package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.*;

public class Ex30SortPerfomance {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();

    static {
        tests.add(new Test<List<Integer>>("sort") {
            int test(List<Integer> list, TestParam tp) {
                Collections.sort(list);
                return 1;
            }
        });
    }

    static class SortTester extends Tester<List<Integer>>{
        public SortTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(CollectionData.list(new RandomGenerator.Integer(),size));
            return container;
        }

        public static void run(List<Integer> container, List<Test<List<Integer>>> tests){
            new SortTester(container,tests).timedTest();
        }
    }

    public static void main(String[] args) {
        Tester.defaultParams = TestParam.array(1000,1);
        Tester<List<Integer>> sortArrayListTest = new SortTester(new ArrayList<Integer>(),Ex30SortPerfomance.tests);
        sortArrayListTest.setHeadline("sortArrayListTest");
        sortArrayListTest.timedTest();
        Tester<List<Integer>> sortLinkedListTest = new SortTester(new LinkedList<Integer>(),Ex30SortPerfomance.tests);
        sortArrayListTest.setHeadline("sortLinkedListTest");
        sortArrayListTest.timedTest();
    }
}
