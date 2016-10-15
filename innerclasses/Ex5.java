package innerclasses;

public class Ex5 {
    class Inner {}
}

class Ex5Test {
    public static void main(String[] args) {
        Ex5.Inner inner = new Ex5().new Inner();
    }
}
