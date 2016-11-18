package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Ex23 {

    static Integer[] fill(Integer[] arr, int bound){
        Random random = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[13];
        System.out.println(Arrays.toString(fill(arr,50)));
        Arrays.sort(arr, /*new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }*/Collections.<Integer>reverseOrder());
        System.out.println(Arrays.toString(arr));
    }
}
