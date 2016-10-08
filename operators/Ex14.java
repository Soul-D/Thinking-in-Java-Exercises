package operators;

public class Ex14 {
    public static void main(String[] args) {
        compareStrings("Hello", "Hello");
        compareStrings("Hello", "Hello1");
        compareStrings("Hello2", "Hello1");
        String hello = new String("Hello");
        compareStrings("Hello", hello);
    }

    static void compareStrings(String first, String second)
    {
        System.out.println(first == second);
        System.out.println(first != second);
        System.out.println(first.equals(second));
    }
}
