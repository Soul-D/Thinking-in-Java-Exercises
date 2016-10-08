package object;

public class Ex5_DataOnly {
    int i;
    double d;
    boolean b;

    public static void main(String[] args) {
        Ex5_DataOnly data = new Ex5_DataOnly();
        data.i = 47;
        data.d = 1.1;
        data.b = false;
        System.out.println("i = " + data.i + " d = " + data.d + " b = " + data.b);
    }
}
