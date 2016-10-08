package operators;

public class Ex6_Dog {
    String name;
    String says;

    public Ex6_Dog(String name, String says) {
        this.name = name;
        this.says = says;
    }

    public static void main(String[] args) {
        Ex6_Dog spot = new Ex6_Dog("spot", "Ruff!");
        Ex6_Dog scruffy = new Ex6_Dog("scruffy", "Wurf!");
        Ex6_Dog dog = spot;
        System.out.println(spot == scruffy);
        System.out.println(spot == dog);
        System.out.println(scruffy == dog);
        System.out.println(spot.equals(scruffy));
        System.out.println(spot.equals(dog));
        System.out.println(scruffy.equals(dog));
    }
}
