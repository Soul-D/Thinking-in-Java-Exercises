package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class Ex23 {
    private static final int BSIZE = 1024;

    static String getPrintableChars(CharBuffer charBuffer){
        char[] arr = charBuffer.toString().toCharArray();
        int i;
        for (i = arr.length-1; i > 0; i--){
            if (arr[i] > 31 && arr[i] < 127)
                break;
        }
        return charBuffer.toString().substring(0,i+1);
    }

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileInputStream("./src/main/java/io/data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        System.out.println(getPrintableChars(buff.asCharBuffer()));
    }
}
