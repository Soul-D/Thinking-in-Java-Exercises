package strings;

import java.util.Scanner;

public class Ex20 {
    int i;
    long l;
    float f;
    double d;
    String s;

    Ex20(String arg){
        Scanner scanner = new Scanner(arg);
        i = scanner.nextInt();
        l = scanner.nextLong();
        f = scanner.nextFloat();
        d = scanner.nextDouble();
        s = scanner.next();
    }

    @Override
    public String toString() {
        return "Ex20{" +
                "i=" + i +
                ", l=" + l +
                ", f=" + f +
                ", d=" + d +
                ", s='" + s + '\'' +
                '}';
    }
}

class Ex20Test{
    public static void main(String[] args) {
        String arg = "13 65554 14,56 864,123 Ohaio";
        System.out.println(new Ex20(arg));
    }
}
