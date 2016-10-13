package reusing;

public class Ex8 {
    Ex8(int i){}
}

class Ex8_Derived extends Ex8 {

    public Ex8_Derived() {
        super(13);
    }

    public Ex8_Derived(int i) {
        super(i);
    }
}
