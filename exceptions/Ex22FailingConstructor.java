package exceptions;

public class Ex22FailingConstructor {
    public Ex22FailingConstructor() {
        System.out.println("start");
        if (true)
            throw new RuntimeException();
        System.out.println("end");
    }
}

class Ex22Test {
    public static void main(String[] args) {
        try {
            new Ex22FailingConstructor();
            try {
            } finally {

            }
        } catch (Exception e) {

        }
    }
}
