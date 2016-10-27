package typeinfo;

public class Ex10 {
    public static void main(String[] args) {
        char[] chars = {'q','w','e'};
        System.out.println(chars.getClass().getSuperclass().getName());
    }
}
