package holding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Ex10_Rodent {
    Mark m1 = new Mark(1);
    Mark m2 = new Mark(2);
    Mark sharedM;

    public void setSharedM(Mark sharedM) {
        this.sharedM = sharedM;
        sharedM.addRef();
    }

    public void dispose(){
        sharedM.dispose();
    }

    Ex10_Rodent(){
        System.out.println("Ex1_Rodent");
    }
    void crunch(){
    }
}

class  Ex10Mouse extends Ex10_Rodent {
    Mark m2 = new Mark(2);
    Mark m1 = new Mark(1);
    Ex10Mouse(){
        System.out.println("Mouse");
    }
    @Override
    void crunch() {
        System.out.println("Mouse.crunch()");
    }
}

class Ex10Gerbil extends Ex10_Rodent {
    Mark m1 = new Mark(1);
    Mark m3 = new Mark(3);
    Ex10Gerbil(){
        System.out.println("Gerbil");
    }
    @Override
    void crunch() {
        System.out.println("Gerbil.crunch()");
    }
}

class Ex10Hamster extends Ex10_Rodent {
    Mark m3 = new Mark(3);
    Mark m1 = new Mark(1);

    Ex10Hamster(){
        System.out.println("Hamster");
    }

    @Override
    void crunch() {
        System.out.println("Hamster.crunch()");
    }
}

class Ex9_Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Random random = new Random(13);
        List<Ex10_Rodent> list = new ArrayList<Ex10_Rodent>();
        Class<?> rodentArr[] = {
                Ex10Mouse.class,
                Ex10Gerbil.class,
                Ex10Hamster.class
        };
        Mark sharedM = new Mark(13);
        for (int i = 0; i < 5; i++){
            list.add((Ex10_Rodent) rodentArr[random.nextInt(rodentArr.length)].newInstance());
            list.get(i).setSharedM(sharedM);
        }
        Iterator<Ex10_Rodent> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next().crunch();
        }
    }
}

class Mark {
    private int count;

    Mark(int i){
        System.out.println("Mark " + i);
    }
    void addRef(){
        count++;
    }
    void dispose(){
        if (--count == 0){
            System.out.println("Disposing Mark");
        } else {
            System.out.println(count);
        }
    }
}
