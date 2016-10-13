package reusing;

public class Ex21 {
    final void doNothing(){
        System.out.println("Nothing");
    }
}

class Ex21_Second extends Ex21 {
    //void doNothing(){}
}
