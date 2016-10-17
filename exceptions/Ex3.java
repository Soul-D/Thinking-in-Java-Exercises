package exceptions;

public class Ex3 {
    public static void main(String[] args) {
        int[] arr = new int[0];
        try{
            arr[1] = 5;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e);
        }
    }
}
