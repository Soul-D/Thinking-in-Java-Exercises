package innerclasses;

public class Ex3Outer {
    private String s;

    public Ex3Outer(String s) {
        this.s = s;
    }

    Inner getInner(){
        return new Inner();
    }

    class Inner {
        public String toString() {
            return s;
        }
    }
}

class Ex3Test{
    public static void main(String[] args) {
        Ex3Outer outer = new Ex3Outer("Private");
        Ex3Outer.Inner inner = outer.getInner();
        System.out.println(inner.toString());
    }
}
