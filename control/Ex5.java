package control;

public class Ex5 {
    public static void main(String[] args) {
        int i1 = 0xAAAA;
        int i2 = 0x5555;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(myToBinaryString(i1));
        System.out.println(Integer.toBinaryString(i2));
        System.out.println(myToBinaryString(i2));
        System.out.println(Integer.toBinaryString(i1&i2));
        System.out.println(myToBinaryString(i1&i2));
        System.out.println(Integer.toBinaryString(i1|i2));
        System.out.println(myToBinaryString(i1|i2));
        System.out.println(Integer.toBinaryString(i1^i2));
        System.out.println(myToBinaryString(i1^i2));
        System.out.println(Integer.toBinaryString(~i1));
        System.out.println(myToBinaryString(~i1));
        System.out.println(Integer.toBinaryString(~i2));
        System.out.println(myToBinaryString(~i2));
    }

    public static String myToBinaryString(int i){
        int j = i;
        String s = "";
        while (i/2 != 0){
            s += (i % 2 == 0) ? "0" : "1";
            i >>= 1;
        }
        s += (i == 0) ? "0" : "1";
        if (j < 0) {
            while (s.length() < 32) {
                s += 1;
            }
        }
        return new StringBuilder(s).reverse().toString();
    }
}
