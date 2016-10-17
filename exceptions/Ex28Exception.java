package exceptions;

public class Ex28Exception extends RuntimeException {
    private String msg;

    public Ex28Exception(String msg) {
        this.msg = msg;
    }

    public void printMsg(){
        System.out.println(msg);
    }
}

class Ex28Test{
    public static void main(String[] args) {
        throw new Ex28Exception("Hi");
    }
}

