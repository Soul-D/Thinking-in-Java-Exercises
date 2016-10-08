package operators;

public class Ex10 {
    public static void main(String[] args) {
        int i1 = 0xAAAA;
        int i2 = 0x5555;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i2));
        System.out.println(Integer.toBinaryString(i1&i2));
        System.out.println(Integer.toBinaryString(i1|i2));
        System.out.println(Integer.toBinaryString(i1^i2));
        System.out.println(Integer.toBinaryString(~i1));
        System.out.println(Integer.toBinaryString(~i2));
    }
}
