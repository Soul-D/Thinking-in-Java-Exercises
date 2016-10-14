package interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ex16 {
    int counter = 10;
    private static Random rand = new Random();
    public char[] next(){
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (char)rand.nextInt(65536);
        }
        return arr;
    }
}

class AdaptedEx16 extends Ex16 implements Readable {
    int counter;

    public AdaptedEx16(int counter) {
        this.counter = counter;
    }

    public int read(CharBuffer cb) throws IOException {
        if (counter-- == 0)
            return -1;
        char[] arr = next();
        cb.put(arr);
        cb.append(" ");
        return arr.length;
    }
}

class Ex16_Test{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new AdaptedEx16(10));
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
}
