package io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ex16UsingRandomAccessFile {
    static String file = "./src/main/java/io/Ex16rtest.dat";

    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        System.out.println(rf.read());
        System.out.println(rf.readChar());
        System.out.println(rf.readChar());
        System.out.println(rf.read(new byte[3]));
        System.out.println(rf.readChar());
        System.out.println(rf.readBoolean());
        System.out.println(rf.readByte());
        System.out.println(rf.readByte());
        System.out.println(rf.readDouble());
        System.out.println(rf.readFloat());
        System.out.println(rf.readInt());
        System.out.println(rf.readLong());
        System.out.println(rf.readShort());
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        rf.write(13);
        rf.writeChars("St");
        rf.write(new byte[]{1,2,3});
        rf.writeChar('v');
        rf.writeBoolean(true);
        rf.writeByte(13);
        rf.writeBytes("1");
        rf.writeDouble(13.5);
        rf.writeFloat(3.3f);
        rf.writeInt(42);
        rf.writeLong(2141512552);
        rf.writeShort(244);
        rf.writeUTF("Hello");
        rf.close();
        display();
    }
}