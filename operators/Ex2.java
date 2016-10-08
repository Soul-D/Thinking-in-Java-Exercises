package operators;

public class Ex2 {
    float f;

    public static void main(String[] args) {
        Ex2 first = new Ex2();
        Ex2 second = new Ex2();
        first.f = 1.f;
        second.f = 2.f;
        printBothObjectsField(first, second);
        first = second;
        printBothObjectsField(first,second);
        first.f = 5.f;
        printBothObjectsField(first, second);
    }

    private static void printBothObjectsField(Ex2 first, Ex2 second) {
        System.out.println("first.f = " + first.f + ", second.f = " + second.f);
    }
}
