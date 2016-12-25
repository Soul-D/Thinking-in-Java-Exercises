package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

public class Ex26CountedString {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private char ch;
    private int id = 0;

    public Ex26CountedString(String str, char ch) {
        s = str;
        this. ch = ch;
        created.add(s);
        for (String s2 : created)
            if (s2.equals(s))
                id++;
    }

    public String toString() {
        return "String: " + s + " char: " + ch + " id: " + id + " hashCode(): " + hashCode();
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + (int)ch;
        result = 37 * result + id;
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof Ex26CountedString &&
                s.equals(((Ex26CountedString) o).s) &&
                ch == ((Ex26CountedString) o).ch &&
                id == ((Ex26CountedString) o).id;
    }

    public static void main(String[] args) {
        Map<Ex26CountedString, Integer> map = new HashMap<Ex26CountedString, Integer>();
        Ex26CountedString[] cs = new Ex26CountedString[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new Ex26CountedString("hi",'q');
            map.put(cs[i], i);
        }
        print(map);
        for (Ex26CountedString cstring : cs) {
            print("Looking up " + cstring);
            print(map.get(cstring));
        }
    }
}
