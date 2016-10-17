package exceptions;

public class Ex27 {
    public static void main(String[] args) {
        int[] arr = new int[0];
        try{
            arr[1] = 5;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new RuntimeException(e);
        }
    }
}
