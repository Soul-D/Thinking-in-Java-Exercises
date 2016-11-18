package arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

public class Ex14 {
    public static void main(String[] args) {
        int[] iarr = new int[7];
        long[] larr = new long[7];
        float[] farr = new float[7];
        double[] darr = new double[7];
        boolean[] barr = new boolean[7];
        char[] carr = new char[7];
        short[] sarr = new short[7];
        iarr = ConvertTo.primitive(Generated.array(Integer.class,new CountingGenerator.Integer(),7));
        larr = ConvertTo.primitive(Generated.array(Long.class,new CountingGenerator.Long(),7));
        farr = ConvertTo.primitive(Generated.array(Float.class,new CountingGenerator.Float(),7));
        darr = ConvertTo.primitive(Generated.array(Double.class,new CountingGenerator.Double(),7));
        barr = ConvertTo.primitive(Generated.array(Boolean.class,new CountingGenerator.Boolean(),7));
        carr = ConvertTo.primitive(Generated.array(Character.class,new CountingGenerator.Character(),7));
        sarr = ConvertTo.primitive(Generated.array(Short.class,new CountingGenerator.Short(),7));
        System.out.println(Arrays.toString(iarr));
        System.out.println(Arrays.toString(larr));
        System.out.println(Arrays.toString(farr));
        System.out.println(Arrays.toString(darr));
        System.out.println(Arrays.toString(barr));
        System.out.println(Arrays.toString(carr));
        System.out.println(Arrays.toString(sarr));
    }
}
