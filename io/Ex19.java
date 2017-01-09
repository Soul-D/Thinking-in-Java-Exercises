package io;

import net.mindview.util.BinaryFile;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Ex19 {
    static Map<Byte,Integer> countBytes(String filename) throws IOException {
        Map<Byte,Integer> result = new TreeMap<Byte, Integer>();
        byte[] arr = BinaryFile.read(filename);
        for (byte b : arr) {
            result.put(b,result.get(b) == null ? 1 : result.get(b) + 1);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(countBytes("./src/main/java/io/Ex19.java"));
    }
}
