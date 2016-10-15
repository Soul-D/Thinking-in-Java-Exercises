package innerclasses;

public interface Ex9 {
    void run();
}

class Ex9Example{
    Ex9 getEx9(){
        class Ex9Implementation implements Ex9{
            public void run() {
                System.out.println("run");
            }
        }
        return new Ex9Implementation();
    }
}

class Ex9Test{
    public static void main(String[] args) {
        Ex9 ex9 = new Ex9Example().getEx9();
        ex9.run();
    }
}
