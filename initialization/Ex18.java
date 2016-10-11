package initialization;

public class Ex18 {
    Ex18(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        Ex18[] arr = new Ex18[13];
        for (int i = 0; i < arr.length; i++){
            arr[i] = new Ex18("" + i);
        }
    }
}
