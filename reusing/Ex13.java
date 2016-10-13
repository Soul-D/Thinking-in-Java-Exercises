package reusing;

public class Ex13 {
    void fus(){
        System.out.println("fus");
    }
    void fus(int i){
        System.out.println("fus " + i);
    }
    void fus(String s){
        System.out.println("fus " + s);
    }
}

class Ex13_Second extends Ex13 {
    void fud (int i, String s) {
        System.out.println("fus " + i + " " + s);
    }

    public static void main(String[] args) {
        Ex13_Second e = new Ex13_Second();
        e.fus();
        e.fus(13);
        e.fus("Hi");
        e.fud(13,"Hi");
    }
}
