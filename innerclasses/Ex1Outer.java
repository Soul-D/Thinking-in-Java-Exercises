package innerclasses;

public class Ex1Outer {

    Inner getInner(){
        return new Inner();
    }

    class Inner {}
}

class Ex1Test {
    public static void main(String[] args) {
        Ex1Outer.Inner inner = new Ex1Outer().getInner();
    }
}
