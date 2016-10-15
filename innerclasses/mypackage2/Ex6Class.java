package innerclasses.mypackage2;

import innerclasses.mypackage.Ex6Interface;

public class Ex6Class {
    protected class Inner implements Ex6Interface {
        public Inner(){}
        public void run() {
            System.out.println("run");
        }
    }
}
