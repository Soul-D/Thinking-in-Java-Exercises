package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Ex7 {
    public static void main(String[] args) throws IOException {
        LinkedList<String> ll = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader("./src/main/java/io/Ex7.java"));
        String buf;
        while ((buf = br.readLine()) != null)
            ll.add(buf);
        br.close();
        ListIterator<String> li = ll.listIterator(ll.size());
        while (li.hasPrevious()){
            System.out.println(li.previous());
        }
    }
}
