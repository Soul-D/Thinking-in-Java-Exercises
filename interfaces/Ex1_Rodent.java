package interfaces;

import java.util.Random;

public abstract class Ex1_Rodent {
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

    Ex1_Rodent(){
        System.out.println("Ex1_Rodent");
    }
    abstract void crunch();
}

class Mouse extends Ex1_Rodent {
    Mark m2 = new Mark(2);
    Mark m1 = new Mark(1);
    Mouse(){
        System.out.println("Mouse");
    }
    @Override
    void crunch() {
        System.out.println("Mouse.crunch()");
    }
}

class Gerbil extends Ex1_Rodent {
    Mark m1 = new Mark(1);
    Mark m3 = new Mark(3);
    Gerbil(){
        System.out.println("Gerbil");
    }
    @Override
    void crunch() {
        System.out.println("Gerbil.crunch()");
    }
}

class Hamster extends Ex1_Rodent {
    Mark m3 = new Mark(3);
    Mark m1 = new Mark(1);

    Hamster(){
        System.out.println("Hamster");
    }

    @Override
    void crunch() {
        System.out.println("Hamster.crunch()");
    }
}

class Ex1_Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Random random = new Random(13);
        Ex1_Rodent arr[] = new Ex1_Rodent[random.nextInt(13)];
        Class<?> rodentArr[] = {
                Mouse.class,
                Gerbil.class,
                Hamster.class
        };
        Mark sharedM = new Mark(13);
        for (int i = 0; i < arr.length; i++){
            arr[i] = (Ex1_Rodent) rodentArr[random.nextInt(rodentArr.length)].newInstance();
            arr[i].setSharedM(sharedM);
        }
        for (Ex1_Rodent rodent : arr){
            rodent.crunch();
        }
        for (Ex1_Rodent rodent : arr){
            rodent.dispose();
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
