package arrays;

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;

public class Ex22 {
    public static void main(String[] args) {
        Integer[] arr = Generated.array(new Integer[20],new RandomGenerator.Integer());
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr,arr[5]));
        System.out.println(Arrays.binarySearch(arr,arr[19]));
    }
}
