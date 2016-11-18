package arrays;

public class Ex3 {
    public static void main(String[] args) {
        printArray(getArray(5,3,3.2,5.5));
        System.out.println();
        printArray(getArray(7,2,3.2,5.5));
        System.out.println();
        printArray(getArray(8,1,3.2,5.5));
    }

    static double[][] getArray(int xsize, int ysize, double from, double to){
        double[][] result = new double[xsize][ysize];
        double step = (to - from) / (xsize * ysize - 1);
        int k = 0;
        for (int i = 0; i < xsize; i++){
            for (int j = 0; j < ysize; j++){
                result[i][j] = from + step*k++;
            }
        }
        return result;
    }

    static void printArray(double[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%.2f ",arr[i][j]);
            }
            System.out.println();
        }
    }
}
