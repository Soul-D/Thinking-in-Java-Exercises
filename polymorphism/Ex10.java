package polymorphism;

public class Ex10 {
    void first(){
        System.out.println("first");
        second();
    }
    void second(){
        System.out.println("second");
    }
}

class Ex10_Second extends Ex10{
    @Override
    void second() {
        System.out.println("derived second");
    }
}

class Ex10_Test {
    public static void main(String[] args) {
        Ex10 e = new Ex10_Second();
        e.first();
    }
}
