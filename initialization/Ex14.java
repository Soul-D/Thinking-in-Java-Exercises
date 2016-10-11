package initialization;

public class Ex14 {
    static String s1 = "s1";
    static String s2;
    static {
        s2 = "s2";
    }
    static void printFields(){
        System.out.println(s1 + " " + s2);
    }

    public static void main(String[] args) {
        printFields();
    }
}
