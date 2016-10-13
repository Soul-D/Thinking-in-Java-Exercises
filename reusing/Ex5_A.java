package reusing;

public class Ex5_A {
    Ex5_A(int i){
        System.out.println("Ex5_A");
    }
}

class Ex5_B {
    Ex5_B(int i){
        System.out.println("Ex5_B");
    }
}

class Ex5_C extends Ex5_A {
    Ex5_B b;

    public Ex5_C() {
        super(1);
        b = new Ex5_B(1);
    }

    public static void main(String[] args) {
        Ex5_C c = new Ex5_C();
    }
}