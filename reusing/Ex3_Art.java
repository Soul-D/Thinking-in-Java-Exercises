package reusing;

import static net.mindview.util.Print.*;

public class Ex3_Art {
    Ex3_Art() { print("Art constructor"); }
}

class Ex3_Drawing extends Ex3_Art {
    Ex3_Drawing() { print("Drawing constructor"); }
}

class Ex3_Cartoon extends Ex3_Drawing {
    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
