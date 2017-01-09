package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Ex12 {
    public static void main(String[] args) throws IOException {
        LinkedList<String> ll = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        PrintWriter pw = new PrintWriter("./src/main/java/io/Ex12.out");
        int lineNumber = 1;
        String buf;
        while ((buf = br.readLine()) != null)
            ll.add(lineNumber++ + ": " + buf);
        br.close();
        ListIterator<String> li = ll.listIterator(ll.size());
        while (li.hasPrevious()){
            pw.println(li.previous());
        }
        pw.close();
        System.out.println(BufferedInputFile.read("./src/main/java/io/Ex12.out"));
    }
}
