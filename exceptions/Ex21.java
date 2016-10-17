package exceptions;

public class Ex21 {
    public Ex21() throws Exception {
        throw new Exception();
    }
}

class Ex21Derived extends Ex21{
    public Ex21Derived() throws Exception {
        /*try {
            super();
        }
        catch (Exception e){

        }*/
    }
}
