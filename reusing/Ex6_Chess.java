package reusing;

import static net.mindview.util.Print.print;

public class Ex6_Chess extends BoardGame{
    Ex6_Chess() {
        //print("Chess constructor");
        super(11);
    }

    public static void main(String[] args) {
        Ex6_Chess e = new Ex6_Chess();
    }
}
