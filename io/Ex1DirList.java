package io;

import net.mindview.util.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ex1DirList {
    public static void main(final String[] args) {
        final File path = new File("./src/main/java/io");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    File file = new File(dir, name);
                    if (file.isFile()) {
                        Set<String> words = new HashSet<String>(new TextFile(file.getAbsolutePath(),"\\W+"));
                        for (String arg : args) {
                            if (words.contains(arg))
                                return true;
                        }
                    }
                    return false;
                }
            });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}
