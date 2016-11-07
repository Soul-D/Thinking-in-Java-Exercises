package generics;

interface Ex25Interface1 {
    void g();
}

interface Ex25Interface2 {
    void f();
}

class Ex25Impl implements Ex25Interface1, Ex25Interface2 {
    public void g() {
        System.out.println("g()");
    }

    public void f() {
        System.out.println("f()");
    }
}

class Ex25Test{
    static <T extends Ex25Interface1> void int1(T arg){
        arg.g();
    }

    static <T extends Ex25Interface2> void int2(T arg){
        arg.f();
    }

    public static void main(String[] args) {
        Ex25Impl obj = new Ex25Impl();
        int1(obj);
        int2(obj);
    }
}
