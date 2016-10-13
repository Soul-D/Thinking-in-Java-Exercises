package reusing;

public class Ex4 {
    Ex4() {
        System.out.println("Ex4 is alive");
    }
}

class Ex4_Derived extends Ex4 {
    Ex4_Derived() {
        System.out.println("Whatever");
    }

    public static void main(String[] args) {
        new Ex4_Derived();
    }
}
