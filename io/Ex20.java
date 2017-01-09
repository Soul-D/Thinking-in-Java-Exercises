package io;

import net.mindview.util.BinaryFile;
import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;

public class Ex20 {
    public static void main(String[] args) throws IOException {
        System.out.println(verifyClassFiles("./target/classes/io"));
    }

    static boolean verifyClassFiles(String directoryName) throws IOException {
        for (File file : Directory.walk(directoryName,".*\\.class")){
            byte[] bytes = BinaryFile.read(file);
            if (!bytesToHex(bytes).startsWith("CAFEBABE"))
                return false;
        }
        return true;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}


