package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Ex18TextFile extends ArrayList<String> {

    public static String read(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } finally {
            in.close();
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            out.print(text);
        } finally {
            out.close();
        }
    }

    public Ex18TextFile(String fileName, String splitter) throws IOException {
        super(Arrays.asList(read(fileName).split(splitter)));
        if (get(0).equals("")) remove(0);
    }

    public Ex18TextFile(String fileName) throws IOException {
        this(fileName, "\n");
    }

    public void write(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            for (String item : this)
                out.println(item);
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String file = read("./src/main/java/io/Ex18TextFile.java");
        write("./src/main/java/io/Ex18test.txt", file);
        Ex18TextFile text = new Ex18TextFile("./src/main/java/io/Ex18test.txt");
        text.write("./src/main/java/io/Ex18test2.txt");
        TreeSet<String> words = new TreeSet<String>(new Ex18TextFile("./src/main/java/io/Ex18TextFile.java", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
