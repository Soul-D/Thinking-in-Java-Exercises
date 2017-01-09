package io;

import net.mindview.util.Directory;

import java.io.File;

public class Ex4 {
    static double sumSizesOfFilesRegex(String regex){
        double result = 0;
        for (File file : Directory.walk(".",regex)){
            result +=file.length();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sumSizesOfFilesRegex(".*\\.java"));
    }
}
