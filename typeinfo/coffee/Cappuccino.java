//: generics/coffee/Cappuccino.java
package typeinfo.coffee;

public class Cappuccino extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Cappuccino> {
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
} ///:~
