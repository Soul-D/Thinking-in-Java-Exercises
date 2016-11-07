package generics;

public interface Ex20Interface {
    void f();
    void g();
}

class Ex20Impl implements Ex20Interface{
    public void f() {
        System.out.println("f()");
    }

    public void g() {
        System.out.println("g()");
    }

    public void h() {
        System.out.println("h()");
    }
}

class Ex20Test{
    static <T extends Ex20Interface> void callEx20(T obj){
        obj.f();
        obj.g();
    }

    public static void main(String[] args) {
        callEx20(new Ex20Impl());
    }
}
