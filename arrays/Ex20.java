package arrays;

import java.util.Arrays;

public class Ex20 {

    static void fill(int[][] arr, int data){
        for (int i = 0; i < arr.length; i++){
            Arrays.fill(arr[i],13);
        }
    }

    public static void main(String[] args) {
        int[][] arr1 = new int[7][7];
        int[][] arr2 = new int[7][7];
        int[][] arr3 = new int[7][7];
        fill(arr1,13);
        fill(arr2,13);
        fill(arr3,13);
        arr3[6][6] = 12;
        System.out.println(Arrays.deepToString(arr1));
        System.out.println(Arrays.deepToString(arr2));
        System.out.println(Arrays.deepToString(arr3));
        System.out.println(Arrays.deepEquals(arr1,arr2));
        System.out.println(Arrays.deepEquals(arr1,arr3));
    }
}
