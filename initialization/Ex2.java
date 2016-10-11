package initialization;

public class Ex2 {
    String s = "Out of constructor";

    public static void main(String[] args) {
        System.out.println(new Ex2().s);
        System.out.println(new Ex2_cons("Inside of constructor").s);
    }
}

class Ex2_cons{
    String s = "Out of constructor";

    Ex2_cons(String s){
        this.s = s;
    }
}