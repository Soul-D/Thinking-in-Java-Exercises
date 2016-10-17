package exceptions;

class Ex11Test {
    public static void main(String[] args) {
        try {
            new Ex10().f();
        } catch (Ex10Exception2 e) {
            throw new RuntimeException(e);
        }
    }
}