package innerclasses;

public class Ex7 {
    private int i;
    private void printI(){
        System.out.println(i);
    }

    void workWithInner(){
        Inner inner = new Inner();
        inner.setAndPrint(13);
        System.out.println(i);
    }

    class Inner {
        void setAndPrint(int integer){
            i = integer;
            printI();
        }
    }
}

class Ex7Test{
    public static void main(String[] args) {
        new Ex7().workWithInner();
    }
}
