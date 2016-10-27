//: generics/coffee/Mocha.java
package typeinfo.coffee;

public class Mocha extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Mocha> {
        public Mocha create() {
            return new Mocha();
        }
    }
} ///:~
