package reusing;

public class Ex1 {
}

class Ex1_Second {
    Ex1 e;

    Ex1 getEx1() {
        if (e == null) {
            e = new Ex1();
        }
        return e;
    }
}
