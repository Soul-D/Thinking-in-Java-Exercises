package initialization;

public class Ex8 {
    void m1(){
        System.out.println("m1");
    }
    void m2(){
        this.m1();
        m1();
    }

    public static void main(String[] args) {
        new Ex8().m2();
    }
}
