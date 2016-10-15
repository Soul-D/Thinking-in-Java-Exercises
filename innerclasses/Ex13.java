package innerclasses;

public class Ex13 {
    Ex9 getEx9(){
        return new Ex9(){
            public void run() {
                System.out.println("run");
            }
        };
    }
}

class Ex13Test{
    public static void main(String[] args) {
        Ex9 ex9 = new Ex13().getEx9();
        ex9.run();
    }
}
