package exceptions;

class Ex13Test {
    public static void main(String[] args) {
        try {
            throw new NullPointerException();
            //Ex9Test.throwAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("I'm here");
        }
    }
}