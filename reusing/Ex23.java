package reusing;

public class Ex23 {
    static int i = 8;
    static {
        System.out.println("Loading");
    }
    Ex23(){
        System.out.println("Constructing");
    }
}

class Ex23_Second {
    public static void main(String[] args) {
        //Ex23 e = new Ex23();
        System.out.println(Ex23.i);
        Ex23 e = new Ex23();
    }
}
