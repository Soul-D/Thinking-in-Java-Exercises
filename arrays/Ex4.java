package arrays;

public class Ex4 {
    public static void main(String[] args) {
        printArray(getArray(5,3,3,3.2,5.5));
        System.out.println();
        printArray(getArray(7,2,3,3.2,5.5));
        System.out.println();
        printArray(getArray(8,1,5,3.2,5.5));
    }

    static double[][][] getArray(int xsize, int ysize, int zsize, double from, double to){
        double[][][] result = new double[xsize][ysize][zsize];
        double step = (to - from) / (xsize * ysize * zsize - 1);
        int t = 0;
        for (int i = 0; i < xsize; i++){
            for (int j = 0; j < ysize; j++){
                for (int k = 0; k < zsize; k++) {
                    result[i][j][k] = from + step * t++;
                }
            }
        }
        return result;
    }

    static void printArray(double[][][] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print("{");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print("{");
                for (int k = 0; k < arr[0][0].length; k++) {
                    System.out.printf("%.2f ", arr[i][j][k]);
                }
                System.out.print("}");
            }
            System.out.print("}");
            System.out.println();
        }
    }
}
