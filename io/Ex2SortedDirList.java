package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Ex2SortedDirList {
    final File file;

    public Ex2SortedDirList(File file) {
        this.file = file;
    }

    public String[] list(){
        String[] result = file.list();
        Arrays.sort(result,String.CASE_INSENSITIVE_ORDER);
        return result;
    }

    public String[] list(String regex){
        final Pattern pattern = Pattern.compile(regex);
        String[] result = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(result,String.CASE_INSENSITIVE_ORDER);
        return result;
    }
}

class Ex2Test{
    public static void main(String[] args) {
        Ex2SortedDirList sdl = new Ex2SortedDirList(new File("./src/main/java/io"));
        System.out.println(Arrays.toString(sdl.list()));
        System.out.println(Arrays.toString(sdl.list(".*Dir.*")));
    }
}
