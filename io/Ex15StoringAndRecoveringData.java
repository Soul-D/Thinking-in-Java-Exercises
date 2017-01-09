
package io;

import java.io.*;

public class Ex15StoringAndRecoveringData {
    public static void main(String[] args)
            throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./src/main/java/io/Ex15Data.txt")));
        out.write(13);
        out.writeChars("St");
        out.write(new byte[]{1,2,3});
        out.writeChar('v');
        out.writeBoolean(true);
        out.writeByte(13);
        out.writeBytes("1");
        out.writeDouble(13.5);
        out.writeFloat(3.3f);
        out.writeInt(42);
        out.writeLong(2141512552);
        out.writeShort(244);
        out.writeUTF("Hello");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("./src/main/java/io/Ex15Data.txt")));
        System.out.println(in.read());
        System.out.println(in.readChar());
        System.out.println(in.readChar());
        System.out.println(in.read(new byte[3]));
        System.out.println(in.readChar());
        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        System.out.println(in.readByte());
        System.out.println(in.readDouble());
        System.out.println(in.readFloat());
        System.out.println(in.readInt());
        System.out.println(in.readLong());
        System.out.println(in.readShort());
        System.out.println(in.readUTF());
        in.close();
    }
}
