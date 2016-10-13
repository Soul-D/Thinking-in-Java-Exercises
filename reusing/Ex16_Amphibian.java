package reusing;

public class Ex16_Amphibian {
    void swim() {
        System.out.println("Swimming");
    }
}

class Frog extends Ex16_Amphibian{
    void swim() {
        System.out.println("Frog is swimming");
    }

    public static void main(String[] args) {
        Ex16_Amphibian f = new Frog();
        f.swim();
    }
}
