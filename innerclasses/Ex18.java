package innerclasses;

public class Ex18 {
    static class Nested{}
}

class Ex18Test {
    public static void main(String[] args) {
        Ex18.Nested nested = new Ex18.Nested();
    }
}
