package initialization;

public class Ex4 {
    Ex4(){
        System.out.println("I'm here");
    }
    Ex4(String s){
        System.out.println(s + " I'm here");
    }

    public static void main(String[] args) {
        new Ex4();
        new Ex4("Really");
    }
}
