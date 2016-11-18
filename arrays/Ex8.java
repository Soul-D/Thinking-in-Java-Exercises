package arrays;

import java.util.Arrays;

public class Ex8 {
    public static void main(String[] args) {
        ArrayOfGenericType<String> t = new ArrayOfGenericType<String>(5);
        //t.array[1] = 1;
        //t.array[0] = new Object();
        t.array[2] = new String("gsdgsd");
        t.array[0] = "0";
        t.array[1] = "00";
        Object[] objects = t.array;
        objects[0] = 1;
        System.out.println(Arrays.toString(t.array));
    }
}
