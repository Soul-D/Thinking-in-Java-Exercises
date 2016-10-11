package initialization;

import static net.mindview.util.Print.print;

class Ex13_Cup {
    Ex13_Cup(int marker) {
        print("Cup(" + marker + ")");
    }
    void f(int marker) {
        print("f(" + marker + ")");
    }
}

class Ex13s {
    static Ex13_Cup cup1;
    static Ex13_Cup cup2;
    static {
        cup1 = new Ex13_Cup(1);
        cup2 = new Ex13_Cup(2);
    }
    Ex13s() {
        print("Cups()");
    }
}

public class Ex13 {
    public static void main(String[] args) {
        print("Inside main()");
        Ex13s.cup1.f(99);  // (1)
    }
     static Ex13s cups1 = new Ex13s();  // (2)
     static Ex13s cups2 = new Ex13s();  // (2)
} /* Output:
Inside main()
Cup(1)
Cup(2)
f(99)
*///:~


