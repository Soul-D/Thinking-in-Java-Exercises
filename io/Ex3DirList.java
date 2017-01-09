package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Ex3DirList {
    public static void main(final String[] args) {
        File path = new File("./src/main/java/io");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);

                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        long size = 0;
        for (String dirItem : list) {
            long fsize = new File(path,dirItem).length();
            System.out.println(dirItem + ", szie = " + fsize);
            size += fsize;
        }
        System.out.println("Size of selected " + list.length + " files equals " + size);
    }
}
