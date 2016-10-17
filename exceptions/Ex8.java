package exceptions;

public class Ex8 {
    void throwException() throws Ex4Exception {
        throw new Ex4Exception("Rise!");
    }
}

class Ex8Test{
    public static void main(String[] args) {
        try {
            new Ex8().throwException();
        } catch (Ex4Exception e) {
            e.printStackTrace();
        }
    }
}
