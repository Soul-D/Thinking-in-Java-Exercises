package exceptions;

public class Ex4Exception extends Exception {
    private String msg;

    public Ex4Exception(String msg) {
        this.msg = msg;
    }

    public void printMsg(){
        System.out.println(msg);
    }
}

class Ex4Test{
    public static void main(String[] args) {
        try{
            throw new Ex4Exception("Hi");
        }
        catch (Ex4Exception e){
            e.printMsg();
        }
    }
}
