package io;

import java.io.*;

public class Ex14BasicFileOutput {
    static String file = "./src/main/java/io/Ex14BasicFileOutput.out";
    static String fileSrc = "./src/main/java/io/Ex14BasicFileOutput.java";

    static long test(PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(fileSrc)));
        String s;
        long startTime = System.nanoTime();
        while ((s = in.readLine()) != null)
            for (int i = 0; i < 10000; i++)
                out.println(s);
        out.close();
        in.close();
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        PrintWriter out2 = new PrintWriter(new FileWriter(file));
        System.out.println(test(out));
        System.out.println(test(out2));
    }
}