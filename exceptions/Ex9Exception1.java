package exceptions;

import java.util.Random;

public class Ex9Exception1 extends Exception {}
class Ex9Exception2 extends Exception {}
class Ex9Exception3 extends Exception {}
class Ex9Test{
    static void throwAll() throws Ex9Exception1, Ex9Exception2, Ex9Exception3 {
        Random random = new Random();
        int i = random.nextInt(3);
        if (i == 0)
            throw new Ex9Exception1();
        else if (i == 1)
            throw new Ex9Exception2();
        else
            throw new Ex9Exception3();
    }
    public static void main(String[] args) {
        try {
            throwAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}