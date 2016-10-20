package strings;

public class Ex6 {
    int i;
    long l;
    float f;
    double d;

    public String toString() {
        return String.format("i = %d, long = %d, float = %.2f, double = %.2f",i,l,f,d);
    }
}

class Ex6Test{
    public static void main(String[] args) {
        System.out.println(new Ex6());
    }
}
