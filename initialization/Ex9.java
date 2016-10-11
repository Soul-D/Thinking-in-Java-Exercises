package initialization;

public class Ex9 {
    Ex9(int i){
        System.out.println(i);
    }
    Ex9(){
        this(1);
    }

    public static void main(String[] args) {
        new Ex9();
    }
}
