package arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

public class Ex13 {
    public static void main(String[] args) {
        String s = new CountingGenerator.String(15).next();
        System.out.println(s);
        s = new String(ConvertTo.primitive(Generated.array(Character.class,new CountingGenerator.Character(),20)));
        System.out.println(s);
    }
}
