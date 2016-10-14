package interfaces;

public abstract class Ex4 {
    abstract void run();
}

class Ex4_Derived extends Ex4{
    void run(){
        System.out.println("run");
    }
}

class Ex4_Test{
    static void run(Ex4 e){
        //((Ex4_Derived) e).run();
        e.run();
    }

    public static void main(String[] args) {
        run(new Ex4_Derived());
    }
}
