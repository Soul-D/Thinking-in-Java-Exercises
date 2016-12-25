package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.CountingGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex31ResizableString<T> extends ArrayList<String>{
    private String[] arr = new String[10];
    private int index = 0;

    public boolean add(String s){
        if (index > arr.length - 1){
            String[] newArr = new String[arr.length*2];
            System.arraycopy(arr,0,newArr,0,arr.length);
            arr = newArr;
        }
        arr[index++] = s;
        return true;
    }

    public String get(int index){
        return arr[index];
    }
}

class Ex31ListTester extends Tester<List<String>>{
    public Ex31ListTester(List<String> container, List<Test<List<String>>> tests) {
        super(container, tests);
    }

    static Random random = new Random();

    static List<Test<List<String>>> tests =
            new ArrayList<Test<List<String>>>();

    static {
        tests.add(new Test<List<String>>("add") {
            @Override
            int test(List<String> container, TestParam tp) {
                for (int i = 0; i < tp.loops; i++){
                    container.add(Integer.toString(i));
                }
                return tp.loops;
            }
        });
        tests.add(new Test<List<String>>("get") {
            @Override
            int test(List<String> container, TestParam tp) {
                for (int i = 0; i < tp.loops; i++){
                    container.get(random.nextInt(container.size()));
                }
                return tp.loops;
            }
        });
    }

    @Override
    protected List<String> initialize(int size) {
        container.clear();
        container.addAll(CollectionData.list(new CountingGenerator.String(), size));
        return container;
    }

    public static void main(String[] args) {
        new Ex31ListTester(new ArrayList<String>(),Ex31ListTester.tests).timedTest();
        new Ex31ListTester(new Ex31ResizableString<String>(),Ex31ListTester.tests).timedTest();
    }
}
