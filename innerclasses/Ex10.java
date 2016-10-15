package innerclasses;

public class Ex10 {
    Ex9 getEx9(boolean flag){
        if (flag) {
            class Ex9Implementation implements Ex9 {
                public void run() {
                    System.out.println("run");
                }
            }
            return new Ex9Implementation();
        } else {
            class Ex9Implementation implements Ex9 {
                public void run() {
                    System.out.println("die");
                }
            }
            return new Ex9Implementation();
        }
    }
}

class Ex10Test{
    public static void main(String[] args) {
        Ex9 ex9 = new Ex10().getEx9(true);
        ex9.run();
        ex9 = new Ex10().getEx9(false);
        ex9.run();
    }
}
