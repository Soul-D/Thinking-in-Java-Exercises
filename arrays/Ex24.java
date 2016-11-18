package arrays;

import java.util.Arrays;
import java.util.Comparator;

public class Ex24 {
    public static void main(String[] args) {
        IntClass[] arr = {new IntClass(1),new IntClass(3),new IntClass(7),new IntClass(10),new IntClass(12)};
        System.out.println(Arrays.binarySearch(arr, new IntClass(12), new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                IntClass i1 = (IntClass)o1;
                IntClass i2 = (IntClass)o2;
                return i1.field-i2.field;
            }
        }));
    }
}
