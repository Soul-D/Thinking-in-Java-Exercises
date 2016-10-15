package innerclasses;

public class Ex12 {
    private int i;
    private void printI(){
        System.out.println(i);
    }
    void workWithInner(){
        System.out.println(Ex12.this.i);
        new Object(){
            void changeOuter(){
                i = 10;
                printI();
            }
        }.changeOuter();
    }
}

class Ex12Test{
    public static void main(String[] args) {
        new Ex12().workWithInner();
    }
}
