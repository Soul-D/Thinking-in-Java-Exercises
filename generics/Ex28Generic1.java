package generics;

import typeinfo.pets.Mouse;
import typeinfo.pets.Pet;
import typeinfo.pets.Rodent;

public class Ex28Generic1<T> {
    void m1(T arg){}
}

class Ex28Generic2<T> {
    T m1(){return null;}
}

class Ex28Test{
    static <T> void method1(Ex28Generic1<T> arg, T param){
        arg.m1(param);
    }

    static <T> T method2(Ex28Generic2<? extends T> arg){
        return arg.m1();
    }

    public static void main(String[] args) {
        Ex28Generic1<Rodent> rodent = new Ex28Generic1<Rodent>();
        method1(rodent,new Mouse());
        method1(rodent,new Rodent());
        //method1(rodent,new Cat());
        Ex28Generic2<Pet> pet = new Ex28Generic2<Pet>();
        Pet pet1 = pet.m1();
        //Cat cat = pet.m1();
    }
}
