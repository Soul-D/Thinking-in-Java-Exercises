package control;

import java.util.Random;

public class Ex2 {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 25; i++){
            int i1 = random.nextInt();
            int i2 = random.nextInt();
            if (i1 > i2){
                System.out.println(i1 + " > " + i2);
            }
            else if (i1 == i2){
                System.out.println(i1 + " = " + i2);
            }
            else{
                System.out.println(i1 + " < " + i2);
            }
        }
    }
}
