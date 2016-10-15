package innerclasses;

public class Ex8 {
    class Inner {
        private int i;
        void print(){
            System.out.println(i);
        }
    }

    void changePrivateInnerField(){
        Inner inner = new Inner();
        inner.i = 5;
        inner.print();
    }
}

class Ex8Test {
    public static void main(String[] args) {
        new Ex8().changePrivateInnerField();
    }
}
