package control;

import java.util.Random;

public class Ex3 {
    public static void main(String[] args) {
        Random random = new Random();
        while(true){
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
