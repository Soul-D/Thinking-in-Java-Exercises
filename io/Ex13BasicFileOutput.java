package io;

import java.io.*;

public class Ex13BasicFileOutput {
    static String file = "./src/main/java/io/Ex13BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new StringReader(BufferedInputFile.read("./src/main/java/io/Ex13BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String s;
        while ((s = lnr.readLine()) != null)
            out.println(lnr.getLineNumber() + ": " + s);
        out.close();
        lnr.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
