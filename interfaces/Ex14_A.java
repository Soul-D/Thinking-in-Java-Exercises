package interfaces;

interface Ex14_A {
    void f1();

    void g1();
}

interface Ex14_B {
    void f2();

    void g2();
}

interface Ex14_C {
    void f3();

    void g3();
}

interface Ex14_D extends Ex14_A, Ex14_B, Ex14_C {
    void f4();
}

abstract class Ex14_E {
    public void f5() {
    }
}

class Ex14_F extends Ex14_E implements Ex14_D {
    public void f4() {

    }

    public void f1() {

    }

    public void g1() {

    }

    public void f2() {

    }

    public void g2() {

    }

    public void f3() {

    }

    public void g3() {

    }
}

class Ex14_Test{
    static void doEx14_A(Ex14_A a){
        a.f1();
        a.g1();
    }
    static void doEx14_B(Ex14_B a){
        a.f2();
        a.g2();
    }
    static void doEx14_C(Ex14_C a){
        a.f3();
        a.g3();
    }
    static void doEx14_D(Ex14_D a){
        a.f1();
        a.g1();
        a.f2();
        a.g2();
        a.f3();
        a.g3();
        a.f4();
    }

    public static void main(String[] args) {
        Ex14_F e = new Ex14_F();
        doEx14_A(e);
        doEx14_B(e);
        doEx14_C(e);
        doEx14_D(e);
    }
}
