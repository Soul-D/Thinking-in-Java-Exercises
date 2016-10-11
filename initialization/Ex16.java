package initialization;

public class Ex16 {
    public static void main(String[] args) {
        String[] arr = new String[13];
        for (int i = 0; i < arr.length; i++){
            arr[i] = new String("" + i);
        }
        for (String s : arr){
            System.out.println(s);
        }
    }
}
