package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.mindview.util.Print.print;

public class Ex42HolderA {
    private int i;

    public Ex42HolderA(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex42HolderA{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }
}

class Ex42HolderB {
    private List list = new ArrayList();

    public Ex42HolderB(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex42HolderB{");
        sb.append("list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}

class Ex42Functional{
    static class HolderAAdder implements Combiner<Ex42HolderA>{
        public Ex42HolderA combine(Ex42HolderA x, Ex42HolderA y) {
            Ex42HolderA result = new Ex42HolderA(0);
            result.setI(x.getI()+y.getI());
            return result;
        }
    }

    static class HolderBAdder implements Combiner<Ex42HolderB>{
        public Ex42HolderB combine(Ex42HolderB x, Ex42HolderB y) {
            Ex42HolderB result = new Ex42HolderB(new ArrayList());
            result.getList().addAll(x.getList());
            result.getList().addAll(y.getList());
            return result;
        }
    }

    static class HolderAMultiplier implements Collector<Ex42HolderA>{
        public Ex42HolderA result() {
            return null;
        }

        public Ex42HolderA function(Ex42HolderA x) {
            x.setI(x.getI()+1);
            return x;
        }
    }

    static class HolderBMultiplier implements Collector<Ex42HolderB>{
        public Ex42HolderB result() {
            return null;
        }

        public Ex42HolderB function(Ex42HolderB x) {
            x.getList().addAll(x.getList());
            return x;
        }
    }

    static class HolderAToInteger implements UnaryFunction<Integer,Ex42HolderA>{
        public Integer function(Ex42HolderA x) {
            return x.getI();
        }
    }

    static class HolderBToList implements UnaryFunction<List,Ex42HolderB>{
        public List function(Ex42HolderB x) {
            return x.getList();
        }
    }

    static class HolderAGreaterThen implements UnaryPredicate<Ex42HolderA>{
        private Ex42HolderA holderA;

        public HolderAGreaterThen(Ex42HolderA holderA) {
            this.holderA = holderA;
        }

        public boolean test(Ex42HolderA x) {
            return x.getI()>holderA.getI();
        }
    }

    static class HolderBGreaterThen implements UnaryPredicate<Ex42HolderB>{
        private Ex42HolderB holderB;

        public HolderBGreaterThen(Ex42HolderB holderB) {
            this.holderB = holderB;
        }

        public boolean test(Ex42HolderB x) {
            return x.getList().size() > holderB.getList().size();
        }
    }
}

class Ex42Test{
    public static void main(String[] args) {
        List<Ex42HolderA> listA = new ArrayList<Ex42HolderA>();
        listA.add(new Ex42HolderA(5));
        listA.add(new Ex42HolderA(10));
        listA.add(new Ex42HolderA(7));
        listA.add(new Ex42HolderA(3));
        print(listA);
        print(Functional.filter(listA,new Ex42Functional.HolderAGreaterThen(new Ex42HolderA(6))));
        Functional.forEach(listA,new Ex42Functional.HolderAMultiplier());
        print(listA);
        print(Functional.reduce(listA,new Ex42Functional.HolderAAdder()));
        print(Functional.transform(listA,new Ex42Functional.HolderAToInteger()));
        List<Ex42HolderB> listB = new ArrayList<Ex42HolderB>();
        listB.add(new Ex42HolderB(new ArrayList<Integer>(Arrays.asList(1,3,5,7))));
        listB.add(new Ex42HolderB(new ArrayList<Integer>(Arrays.asList(2,4,6))));
        print(listB);
        print(Functional.filter(listB,new Ex42Functional.HolderBGreaterThen(new Ex42HolderB(Arrays.asList(1,3, 4)))));
        Functional.forEach(listB,new Ex42Functional.HolderBMultiplier());
        print(listB);
        print(Functional.reduce(listB,new Ex42Functional.HolderBAdder()));
        print(Functional.transform(listB,new Ex42Functional.HolderBToList()));
    }
}
