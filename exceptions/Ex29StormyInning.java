//: exceptions/StormyInning.java
package exceptions; /* Added by Eclipse.py */

class Ex29BaseballException extends RuntimeException {
}

class Ex29Foul extends Ex29BaseballException {
}

class Ex29Strike extends Ex29BaseballException {
}

abstract class Ex29Inning {
    public Ex29Inning() throws Ex29BaseballException {
    }

    public void event() throws Ex29BaseballException {

    }

    public abstract void atBat() throws Ex29Strike, Ex29Foul;

    public void walk() {
    }
}

class Ex29StormException extends RuntimeException {
}

class Ex29RainedOut extends Ex29StormException {
}

class Ex29PopFoul extends Ex29Foul {
}

interface Ex29Storm {
    public void event();

    public void rainHard();
}

public class Ex29StormyInning extends Ex29Inning implements Ex29Storm {

    public Ex29StormyInning() {
    }

    public Ex29StormyInning(String s) {
    }


    public void walk() {
    }

    public void event() {
    }


    public void rainHard() {
    }


    public void atBat() {
    }

    public static void main(String[] args) {

        Ex29StormyInning si = new Ex29StormyInning();
        si.atBat();


        Ex29Inning i = new Ex29StormyInning();
        i.atBat();


    }
}
