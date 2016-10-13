package reusing;

import static net.mindview.util.Print.print;

public class Ex11 {
    Cleanser cleanser = new Cleanser();

    public static void main(String[] args) {
        Ex11 x = new Ex11();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        print(x);
    }

    @Override
    public String toString() {
        return "Ex11 " + cleanser.toString();
    }

    public void scrub() {
        cleanser.append(" Detergent.scrub()");
        cleanser.scrub();
    }

    public void apply() {
        cleanser.apply();
    }

    public void dilute() {
        cleanser.dilute();
    }

    public void append(String a) {
        cleanser.append(a);
    }

    public void foam() {
        cleanser.append(" foam()");
    }
}
