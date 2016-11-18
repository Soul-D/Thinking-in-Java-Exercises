package arrays;

import java.util.Arrays;

public class Ex7 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(getArray(4, 5, 6)));
    }

    static BerylliumSphere[][][] getArray(int xsize, int ysize, int zsize){
        BerylliumSphere[][][] arr = new BerylliumSphere[xsize][ysize][zsize];
        for (int i = 0; i < xsize; i++){
            for (int j = 0; j < ysize; j++){
                for (int k = 0; k < zsize; k++) {
                    arr[i][j][k] = new BerylliumSphere();
                }
            }
        }
        return arr;
    }
}
