package exceptions;

public class Ex10 {
    void f() throws Ex10Exception2 {
        try {
            g();
        } catch (Ex10Exception e) {
            throw new Ex10Exception2(e);
        }
    }
    void g() throws Ex10Exception {throw new Ex10Exception();}
}

class Ex10Exception extends Exception{}
class Ex10Exception2 extends Exception{
    public Ex10Exception2(Throwable cause) {
        super(cause);
    }
}

class Ex10Test{
    public static void main(String[] args) {
        try {
            new Ex10().f();
        } catch (Ex10Exception2 ex10Exception2) {
            ex10Exception2.printStackTrace();
        }
    }
}