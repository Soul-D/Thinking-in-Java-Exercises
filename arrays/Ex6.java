package arrays;

import java.util.Arrays;

public class Ex6 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(getArray(4, 5)));
    }

    static BerylliumSphere[][] getArray(int xsize, int ysize){
        BerylliumSphere[][] arr = new BerylliumSphere[xsize][ysize];
        for (int i = 0; i < xsize; i++){
            for (int j = 0; j < ysize; j++){
                arr[i][j] = new BerylliumSphere();
            }
        }
        return arr;
    }
}
