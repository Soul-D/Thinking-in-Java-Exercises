package exceptions;

public class Ex1 {
    public static void main(String[] args) {
        try{
            throw new Exception("Yo!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("I'm here");
        }
    }
}
