package generics;

public class Ex38BasicCoffee {
    private String name;
    public void set(String val) { name = val; }
    public String get() { return name; }
    public void drink() {
        System.out.println("Drinking coffee");
    }
}

class Ex38Decorator extends Ex38BasicCoffee{
    protected Ex38BasicCoffee coffee;

    public Ex38Decorator(Ex38BasicCoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void set(String val) {
        coffee.set(val);
    }

    @Override
    public String get() {
        return coffee.get();
    }

    @Override
    public void drink() {
        coffee.drink();
    }
}

class Ex38WithSteamedMilk extends Ex38Decorator{
    public void drinkMilk(){
        System.out.println("Delicious streamed milk");
    }

    public Ex38WithSteamedMilk(Ex38BasicCoffee coffee) {
        super(coffee);
    }

    @Override
    public void drink() {
        drinkMilk();
        coffee.drink();
    }
}

class Ex38WithFoam extends Ex38Decorator{
    public void eatFoam(){
        System.out.println("Mmm... foam...");
    }

    public Ex38WithFoam(Ex38BasicCoffee coffee) {
        super(coffee);
    }

    @Override
    public void drink() {
        eatFoam();
        coffee.drink();
    }
}

class Ex38WithChocolate extends Ex38Decorator{
    public void eatChocolate(){
        System.out.println("Mmm... chocolate...");
    }

    public Ex38WithChocolate(Ex38BasicCoffee coffee) {
        super(coffee);
    }

    @Override
    public void drink() {
        eatChocolate();
        coffee.drink();
    }
}

class Ex38WithCaramel extends Ex38Decorator{
    public void eatCaramel(){
        System.out.println("Mmm... caramel...");
    }

    public Ex38WithCaramel(Ex38BasicCoffee coffee) {
        super(coffee);
    }

    @Override
    public void drink() {
        eatCaramel();
        coffee.drink();
    }
}

class Ex38WithWhippedCream extends Ex38Decorator{
    public void eatWhippedCream(){
        System.out.println("Mmm... whipped cream...");
    }

    public Ex38WithWhippedCream(Ex38BasicCoffee coffee) {
        super(coffee);
    }

    @Override
    public void drink() {
        eatWhippedCream();
        coffee.drink();
    }
}

class Ex38Test{
    public static void main(String[] args) {
        Ex38WithWhippedCream coffee = new Ex38WithWhippedCream(new Ex38WithCaramel
                (new Ex38WithChocolate(new Ex38WithFoam(new Ex38WithSteamedMilk(new Ex38BasicCoffee())))));
        coffee.drink();
        Ex38WithWhippedCream coffee2 = new Ex38WithWhippedCream(new Ex38WithSteamedMilk(new Ex38BasicCoffee()));
        coffee2.drink();
    }
}
