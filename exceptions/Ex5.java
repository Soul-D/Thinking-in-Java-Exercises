package exceptions;

public class Ex5 {
    public static void main(String[] args) {
        boolean flag = true;
        int a = 1;
        int b = 0;
        while(flag){
            try {
                int c = a/b;
                flag = false;
            }
            catch (Exception e){
                b = 1;
            }
        }
        System.out.println("works");
    }
}
