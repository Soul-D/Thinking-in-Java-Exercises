//: generics/coffee/Breve.java
package typeinfo.coffee;

public class Breve extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Breve> {
        public Breve create() {
            return new Breve();
        }
    }
} ///:~
