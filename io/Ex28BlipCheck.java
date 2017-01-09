package io;

import java.io.*;

import static net.mindview.util.Print.print;

public class Ex28BlipCheck implements Externalizable {
    /*Ex28BlipCheck() {
        print("Ex28BlipCheck Constructor");
    }*/

    public void writeExternal(ObjectOutput out) throws IOException {
        print("Ex28BlipCheck.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Ex28BlipCheck.readExternal");
    }
}

class Ex28Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip1 b1 = new Blip1();
        Ex28BlipCheck b2 = new Ex28BlipCheck();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("./src/main/java/io/Blips.out"));
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/main/java/io/Blips.out"));
        print("Recovering b1:");
        b1 = (Blip1) in.readObject();
        print("Recovering b2:");
        b2 = (Ex28BlipCheck) in.readObject();
    }
}

