package control;

public class Ex9 {
    public static void main(String[] args) {
        fibonacci(10);
    }

    public static void fibonacci(int number){
        int[] arr = new int[number];
        for (int i = 0; i < arr.length; i++){
            if ((i == 0) || (i == 1)) {
                arr[i] = 1;
            }
            else {
                arr[i] = arr[i-2]+arr[i-1];
            }
        }
        for (int x : arr){
            System.out.println(x);
        }
    }
}
