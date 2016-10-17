package exceptions;

class Ex25Exception1 extends Exception{}
class Ex25Exception2 extends Ex25Exception1{}
class Ex25Exception3 extends Ex25Exception2{}

public class Ex25A {
    void throwException() throws Ex25Exception1 {
        throw new Ex25Exception1();
    }
}

class Ex25B extends Ex25A{
    void throwException() throws Ex25Exception2 {
        throw new Ex25Exception2();
    }
}

class Ex25C extends Ex25B{
    void throwException() throws Ex25Exception3 {
        throw new Ex25Exception3();
    }
}

class Ex25Test{
    public static void main(String[] args) throws Ex25Exception1 {
        Ex25A a = new Ex25C();
        a.throwException();
    }
}