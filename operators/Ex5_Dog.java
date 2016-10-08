package operators;

public class Ex5_Dog {
    String name;
    String says;

    public Ex5_Dog(String name, String says) {
        this.name = name;
        this.says = says;
    }

    public static void main(String[] args) {
        Ex5_Dog spot = new Ex5_Dog("spot", "Ruff!");
        Ex5_Dog scruffy = new Ex5_Dog("scruffy", "Wurf!");
        getSayDog(spot);
        getSayDog(scruffy);
    }

    private static void getSayDog(Ex5_Dog dog) {
        System.out.println(dog.name + " says " + dog.says);
    }
}
