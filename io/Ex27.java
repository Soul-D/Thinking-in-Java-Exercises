package io;

import java.io.*;

public class Ex27 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Ex27S1 ref = new Ex27S1(13,42);
        System.out.println(ref);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/main/java/io/Ex27dat"));
        out.writeObject(ref);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/main/java/io/Ex27dat"));
        System.out.println((Ex27S1)in.readObject());
    }
}

class Ex27S1 implements Serializable {
    int i;
    Ex27S2 field;

    public Ex27S1(int i, int j) {
        this.i = i;
        this.field = new Ex27S2(j);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex27S1{");
        sb.append("i=").append(i);
        sb.append(", field=").append(field);
        sb.append('}');
        return sb.toString();
    }
}

class Ex27S2 implements Serializable {
    int i;

    public Ex27S2(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex27S2{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }
}


