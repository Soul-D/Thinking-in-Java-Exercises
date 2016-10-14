package interfaces;

import java.util.Random;

public interface Ex7_Rodent {
    Mark m1 = new Mark(1);
    Mark m2 = new Mark(2);
    void crunch();
}

class Ex7_Mouse implements Ex7_Rodent {
    Mark m2 = new Mark(2);
    Mark m1 = new Mark(1);
    Ex7_Mouse(){
        System.out.println("Mouse");
    }
    public void crunch() {
        System.out.println("Mouse.crunch()");
    }
}

class Ex7_Gerbil implements Ex7_Rodent {
    Mark m1 = new Mark(1);
    Mark m3 = new Mark(3);
    Ex7_Gerbil(){
        System.out.println("Gerbil");
    }
    public void crunch() {
        System.out.println("Gerbil.crunch()");
    }
}

class Ex7_Hamster implements Ex7_Rodent {
    Mark m3 = new Mark(3);
    Mark m1 = new Mark(1);
    Ex7_Hamster(){
        System.out.println("Hamster");
    }
    public void crunch() {
        System.out.println("Hamster.crunch()");
    }
}

class Ex7_Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Random random = new Random(13);
        Ex7_Rodent arr[] = {
                new Ex7_Gerbil(),
                new Ex7_Hamster(),
                new Ex7_Mouse()
        };
        for (Ex7_Rodent rodent : arr){
            rodent.crunch();
        }
    }
}
