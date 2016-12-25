package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.CountingGenerator;
import net.mindview.util.CountingIntegerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex32ResizableInt<T> extends ArrayList<Integer>{
    private Integer[] arr = new Integer[10];
    private int index = 0;

    public boolean add(Integer s){
        if (index > arr.length - 1){
            Integer[] newArr = new Integer[arr.length*2];
            System.arraycopy(arr,0,newArr,0,arr.length);
            arr = newArr;
        }
        arr[index++] = s;
        return true;
    }

    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer set(int index, Integer element) {
        int result = arr[index];
        arr[index] = element;
        return result;
    }
}

class Ex32ListTester extends Tester<List<Integer>>{
    public Ex32ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
        super(container, tests);
    }

    static Random random = new Random();

    static List<Test<List<Integer>>> tests =
            new ArrayList<Test<List<Integer>>>();

    static {
        tests.add(new Test<List<Integer>>("add") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                for (int i = 0; i < tp.loops; i++){
                    container.add(i);
                }
                return tp.loops;
            }
        });
        tests.add(new Test<List<Integer>>("get") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                for (int i = 0; i < tp.loops; i++){
                    container.get(random.nextInt(container.size()));
                }
                return tp.loops;
            }
        });
        tests.add(new Test<List<Integer>>("increment") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                for (int i = 0; i < tp.loops; i++){
                    for (int j = 0; j < tp.size; j++) {
                        container.set(j, container.get(j) + 1);
                    }
                }
                return tp.loops*tp.size;
            }
        });
    }

    @Override
    protected List<Integer> initialize(int size) {
        container.clear();
        container.addAll(new CountingIntegerList(size));
        return container;
    }

    public static void main(String[] args) {
        new Ex32ListTester(new ArrayList<Integer>(),Ex32ListTester.tests).timedTest();
        new Ex32ListTester(new Ex32ResizableInt<Integer>(),Ex32ListTester.tests).timedTest();
    }
}

