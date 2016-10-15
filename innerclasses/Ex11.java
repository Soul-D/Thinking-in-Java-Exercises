package innerclasses;

public class Ex11 {
    private class Ex9Implementation implements Ex9 {
        public void run() {
            System.out.println("run");
        }
    }

    Ex9 getEx9(){
        return new Ex9Implementation();
    }
}

class Ex11Test {
    public static void main(String[] args) {
        Ex9 ex9 = new Ex11().getEx9();
        //Ex11.Ex9Implementation imp = (Ex11.Ex9Implementation)ex9;
    }
}