package operators;

public class Ex12 {
    public static void main(String[] args) {
        int i = 0xFFFFFFFF;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i <<= 1));
        for (int j = 0; j < 31; j++) {
            System.out.println(Integer.toBinaryString(i >>>= 1));
        }

    }
}
