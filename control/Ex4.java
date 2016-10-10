package control;

public class Ex4 {
    public static void main(String[] args) {
        printPrimeNumbers(50);
    }

    public static void printPrimeNumbers(int limit){
        for (int i = 1; i <= limit; i++){
            boolean prime = true;
            for (int j = 2; j < i; j++){
                if (i % j == 0){
                    prime = false;
                }
            }
            if (prime){
                System.out.println(i);
            }
        }
    }
}
