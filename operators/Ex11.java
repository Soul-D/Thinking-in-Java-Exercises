package operators;

public class Ex11 {
    public static void main(String[] args) {
        int i = 0x80000000;
        System.out.println(Integer.toBinaryString(i));
        for (int j = 0; j < 31; j++) {
            System.out.println(Integer.toBinaryString(i >>= 1));
        }

    }
}
