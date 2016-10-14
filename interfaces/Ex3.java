package interfaces;

public abstract class Ex3 {
    Ex3(){
        print();
    }
    abstract void print();
}

class Ex3_Derived extends Ex3{
    int i = 7;
    @Override
    void print() {
        System.out.println(i);
    }
}

class Ex3_Test{
    public static void main(String[] args) {
        new Ex3_Derived().print();
    }
}
