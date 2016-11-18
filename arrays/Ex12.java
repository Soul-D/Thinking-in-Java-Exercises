package arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

public class Ex12 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(ConvertTo.primitive(Generated.array(Double.class, new CountingGenerator.Double(), 10))));
    }
}
