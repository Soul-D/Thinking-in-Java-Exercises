//: interfaces/music5/Music5.java
// Interfaces.
package interfaces.music5;

import polymorphism.music.Note;

import static net.mindview.util.Print.*;

interface Playable {
    void play(Note n);
}

abstract class Instrument implements Playable {
    // Compile-time constant:
    int VALUE = 5; // static & final

    // Cannot have method definitions:
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public void adjust() {
        print(this + ".adjust()");
    }
}

class Wind extends Instrument {

    public String toString() {
        return "Wind";
    }

}

class Percussion extends Instrument {

    public String toString() {
        return "Percussion";
    }

}

class Stringed extends Instrument {

    public String toString() {
        return "Stringed";
    }

}

class Brass extends Wind {
    public String toString() {
        return "Brass";
    }
}

class Woodwind extends Wind {
    public String toString() {
        return "Woodwind";
    }
}

public class Music5 {
    // Doesn't care about type, so new types
    // added to the system still work right:
    static void tune(Playable i) {
        // ...
        i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Playable[] e) {
        for (Playable i : e)
            tune(i);
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
} /* Output:
Wind.play() MIDDLE_C
Percussion.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
Woodwind.play() MIDDLE_C
*///:~
