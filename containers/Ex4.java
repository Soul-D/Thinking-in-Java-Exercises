package containers;

import net.mindview.util.TextFile;

import java.util.*;

public class Ex4 {
    static Collection<String> getFromFile(String filename){
        List<String> list = new ArrayList<String>();
        String[] file = TextFile.read(filename).split("\\s+");
        for (String s : file)
            list.add(s);
        return list;
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.addAll(getFromFile("src/main/java/containers/Ex4.java"));
        System.out.println(set);
    }
}
