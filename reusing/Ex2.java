package reusing;

public class Ex2 extends Detergent {
    @Override
    public void scrub() {
        append(" That's the real scrub going on there!");
        super.scrub();
    }

    public void sterilize() {
        append(" You're not sterilize!");
    }

    public static void main(String[] args) {
        Ex2 e = new Ex2();
        e.scrub();
        e.sterilize();
        e.apply();
        e.dilute();
        e.foam();
        System.out.println(e);
    }
}
