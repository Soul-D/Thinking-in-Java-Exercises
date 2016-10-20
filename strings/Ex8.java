package strings;

public class Ex8 {
    public static void main(String[] args) {
        for (String s : Splitting.knights.split("the|you")){
            System.out.println(s);
        }
    }
}
