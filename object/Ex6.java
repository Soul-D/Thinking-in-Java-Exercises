package object;

public class Ex6 {
    int storage(String s) {
        return s.length() * 2;
    }

    public static void main(String[] args) {
        Ex6 ex6 = new Ex6();
        System.out.println(ex6.storage("Hello"));
    }
}
