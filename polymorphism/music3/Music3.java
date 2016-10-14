//: polymorphism/music3/Music3.java
// An extensible program.
package polymorphism.music3;

import polymorphism.Ex4_Rectangle;
import polymorphism.music.Note;
import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

import java.util.Random;

import static net.mindview.util.Print.*;

class RandomInstrumentGenerator {
    private Random rand = new Random(47);

    public Instrument next() {
        switch (rand.nextInt(6)) {
            default:
            case 0:
                return new Wind();
            case 1:
                return new Percussion();
            case 2:
                return new Stringed();
            case 3:
                return new Brass();
            case 4:
                return new Woodwind();
            case 5:
                return new NewInstrument();
        }
    }
}

class Instrument {
    void play(Note n) {
        print("Instrument.play() " + n);
    }

    public String toString() {
        return "Instrument";
    }

    void adjust() {
        print("Adjusting Instrument");
    }
}

class Wind extends Instrument {
    void play(Note n) {
        print("Wind.play() " + n);
    }

    public String toString() {
        return "Wind";
    }

    void adjust() {
        print("Adjusting Wind");
    }
}

class Percussion extends Instrument {
    void play(Note n) {
        print("Percussion.play() " + n);
    }

    public String toString() {
        return "Percussion";
    }

    void adjust() {
        print("Adjusting Percussion");
    }
}

class Stringed extends Instrument {
    void play(Note n) {
        print("Stringed.play() " + n);
    }

    public String toString() {
        return "Stringed";
    }

    void adjust() {
        print("Adjusting Stringed");
    }
}

class Brass extends Wind {
    void play(Note n) {
        print("Brass.play() " + n);
    }

    void adjust() {
        print("Adjusting Brass");
    }
}

class Woodwind extends Wind {
    void play(Note n) {
        print("Woodwind.play() " + n);
    }

    public String toString() {
        return "Woodwind";
    }
}

class NewInstrument extends Instrument {
    @Override
    void play(Note n) {
        print("NewInstrument.play() " + n);
    }

    @Override
    public String toString() {
        return "NewInstrument";
    }

    @Override
    void adjust() {
        print("Adjusting NewInstrument");
    }
}

public class Music3 {
    // Doesn't care about type, so new types
    // added to the system still work right:
    public static void tune(Instrument i) {
        // ...
        i.play(Note.MIDDLE_C);
    }

    public static void tuneAll(Instrument[] e) {
        for (Instrument i : e)
            tune(i);
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        /*Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind(),
                new NewInstrument()
        };*/
        Instrument[] orchestra = new Instrument[13];
        RandomInstrumentGenerator rig = new RandomInstrumentGenerator();
        for (int i = 0; i < orchestra.length; i++){
            orchestra[i] = rig.next();
        }
        tuneAll(orchestra);
        for (Instrument instr : orchestra) {
            System.out.println(instr);
        }
    }
} /* Output:
Wind.play() MIDDLE_C
Percussion.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
Woodwind.play() MIDDLE_C
*///:~
