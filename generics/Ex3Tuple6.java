package generics;

import net.mindview.util.FiveTuple;

public class Ex3Tuple6 <A,B,C,D,E,F> extends FiveTuple <A,B,C,D,E>{
    public final F sixth;
    public Ex3Tuple6(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex3Tuple6{");
        sb.append("a=").append(first).append(" ");
        sb.append("b=").append(second).append(" ");
        sb.append("c=").append(third).append(" ");
        sb.append("d=").append(fourth).append(" ");
        sb.append("e=").append(fifth).append(" ");
        sb.append("f==").append(sixth).append(" ");
        sb.append('}');
        return sb.toString();
    }
}

class ExTest{
    public static void main(String[] args) {
        Ex3Tuple6<Integer, Double, String, Object, Vehicle, Amphibian> tuple6 =
                new Ex3Tuple6<Integer, Double, String, Object, Vehicle, Amphibian>(3,4.4,"Hi",new Object(),new Vehicle(), new Amphibian());
        System.out.println(tuple6);
    }
}
