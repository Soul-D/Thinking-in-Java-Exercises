package typeinfo.pets;

public class Gerbil extends Rodent {
    public Gerbil(String name) {
        super(name);
    }

    public Gerbil() {
        super();
    }
    public static class Factory implements typeinfo.factory.Factory<Gerbil> {
        public Gerbil create() {
            return new Gerbil();
        }
    }
}
