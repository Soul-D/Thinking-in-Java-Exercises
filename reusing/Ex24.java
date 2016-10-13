package reusing;

import static net.mindview.util.Print.print;

public class Ex24 extends Beetle{
    private int k = printInit("Ex24.k initialized");
    public Ex24() {
        print("k = " + k);
        print("j = " + j);
    }
    private static int x2 =
            printInit("static Ex24.x2 initialized");
    public static void main(String[] args) {
        print("Ex24 constructor");
        Ex24 e = new Ex24();
    }
}
