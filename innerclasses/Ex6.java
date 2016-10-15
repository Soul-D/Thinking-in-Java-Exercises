package innerclasses;

import innerclasses.mypackage.Ex6Interface;
import innerclasses.mypackage2.Ex6Class;

public class Ex6 extends Ex6Class {
    Ex6Interface getInterface(){
        return new Inner();
    }
}

class Ex6Test {
    public static void main(String[] args) {
        new Ex6().getInterface().run();
    }
}
