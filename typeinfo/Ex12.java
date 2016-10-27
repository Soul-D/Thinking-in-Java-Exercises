package typeinfo;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.TypeCounter;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class Ex12 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Coffee.class);
        for(Coffee coffee : new CoffeeGenerator(20)) {
            printnb(coffee.getClass().getSimpleName() + " ");
            counter.count(coffee);
        }
        print();
        print(counter);
    }
}
