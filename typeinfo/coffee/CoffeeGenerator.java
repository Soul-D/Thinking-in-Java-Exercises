package typeinfo.coffee;

import typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoffeeGenerator {
    static List<Factory<? extends Coffee>> coffees = new ArrayList<Factory<? extends Coffee>>();

    static {
        coffees.add(new Americano.Factory());
        coffees.add(new Breve.Factory());
        coffees.add(new Cappuccino.Factory());
        coffees.add(new Latte.Factory());
        coffees.add(new Mocha.Factory());
    }

    static Random random = new Random();

    static Coffee randomCoffee(){
        int i = random.nextInt(coffees.size());
        return coffees.get(i).create();
    }

    static List<Coffee> getCoffees(int n){
        List<Coffee> list = new ArrayList<Coffee>();
        for (int i = 0; i < n; i++){
            list.add(randomCoffee());
        }
        return list;
    }
}

class Ex16Test{
    public static void main(String[] args) {
        for (Coffee coffee : CoffeeGenerator.getCoffees(20)){
            System.out.println(coffee);
        }
    }
}
