package operators;

public class Ex13 {
    public static void main(String[] args) {
        charToBinary('\n');
        charToBinary('A');
        charToBinary('\f');
    }

    static void charToBinary(char c)
    {
        System.out.println(Integer.toBinaryString(c));
    }
}
