package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

public class Ex10 {
    public static void main(String[] args) throws IOException {
        LinkedList<String> ll = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        Set<String> words = new HashSet<String>();
        for (int i = 1; i < args.length; i++)
            words.add(args[i]);
        String buf;
        while ((buf = br.readLine()) != null)
            ll.add(buf);
        br.close();
        ListIterator<String> li = ll.listIterator(ll.size());
        label:
        while (li.hasPrevious()) {
            String prev = li.previous();
            for (String str : prev.split("\\W")) {
                if (words.contains(str)) {
                    System.out.println(prev);
                    continue label;
                }
            }
        }
    }
}
