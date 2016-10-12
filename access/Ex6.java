package access;

public class Ex6 {
    protected int i = 5;
}

class Ex6Helper {
    public static void main(String[] args) {
        Ex6 e = new Ex6();
        System.out.println(e.i);
        e.i = 6;
        System.out.println(e.i);
    }
}
