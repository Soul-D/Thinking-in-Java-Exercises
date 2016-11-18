package arrays;

import java.util.Arrays;

public class Ex25PythonLists {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(arr.getClass().getSimpleName());
        System.out.println(Arrays.toString(arr));
        System.out.println(arr[4]);
        int[] arr1 = new int[arr.length+1];
        System.arraycopy(arr,0,arr1,0,arr.length);
        arr1[arr1.length-1] = 6;
        int[] arr2 = new int[arr1.length+2];
        System.arraycopy(arr1,0,arr2,0,arr1.length);
        int[] newArr = {7,8};
        System.arraycopy(newArr,0,arr2,arr2.length-2,newArr.length);
        System.out.println(Arrays.toString(arr2));
        int[] aSlice = new int[2];
        System.arraycopy(arr2,2,aSlice,0,2);
        System.out.println(Arrays.toString(aSlice));
        System.out.println(new MyArr(arr2).getReversed());
    }
}

class MyArr{
    int[] arr;

    public MyArr(int[] arr) {
        this.arr = arr;
    }

    public MyArr getReversed(){
        int[] tmp = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            tmp[i] = arr[arr.length-i-1];
        }
        arr = tmp;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
