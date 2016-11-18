package arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.Generated;
import net.mindview.util.Generator;

import java.util.Arrays;

import static net.mindview.util.Print.print;

class SkipGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private int increment;
        boolean last;

        public Boolean(int increment) {
            this.increment = increment;
        }

        public java.lang.Boolean next() {
            int i = increment;
            if (i % 2 != 0)
                last = !last;
            return last;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private int increment;
        private byte value = 0;

        public Byte(int increment) {
            this.increment = increment;
            value = (byte) -increment;
        }

        public java.lang.Byte next() {
            value += increment;
            return value;
        }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Character implements Generator<java.lang.Character> {
        private int increment;
        int index;

        public Character(int increment) {
            this.increment = increment;
            index = -increment;
        }

        public java.lang.Character next() {
            index = (index + increment) % chars.length;
            return chars[index];
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg;

        public String(int increment) {
            this.cg = new Character(increment);
        }

        public String(int length, int increment) {
            this.length = length;
            this.cg = new Character(increment);
        }

        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private int increment;
        private short value;

        public Short(int increment) {
            this.increment = increment;
            this.value = (short) -increment;
        }

        public java.lang.Short next() {
            value += increment;
            return value;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int increment;
        private int value;

        public Integer(int increment) {
            this.increment = increment;
            this.value = -increment;
        }

        public java.lang.Integer next() {
            value += increment;
            return value;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private int increment;
        private long value;

        public Long(int increment) {
            this.increment = increment;
            value = -increment;
        }

        public java.lang.Long next() {
            value += increment;
            return value;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float increment;
        private float value;

        public Float(float increment) {
            this.increment = increment;
            value = -increment;
        }

        public java.lang.Float next() {
            value += increment;
            return value;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double increment;
        private double value;

        public Double(double increment) {
            this.increment = increment;
            value = -increment;
        }

        public java.lang.Double next() {
            value += increment;
            return value;
        }
    }

}

public class Ex16 {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(Boolean.class, new SkipGenerator.Boolean(2), size));
        print("a1 = " + Arrays.toString(a1));
        byte[] a2 = ConvertTo.primitive(Generated.array(Byte.class, new SkipGenerator.Byte(2), size));
        print("a2 = " + Arrays.toString(a2));
        char[] a3 = ConvertTo.primitive(Generated.array(Character.class, new SkipGenerator.Character(2), size));
        print("a3 = " + Arrays.toString(a3));
        short[] a4 = ConvertTo.primitive(Generated.array(Short.class, new SkipGenerator.Short(2), size));
        print("a4 = " + Arrays.toString(a4));
        int[] a5 = ConvertTo.primitive(Generated.array(Integer.class, new SkipGenerator.Integer(2), size));
        print("a5 = " + Arrays.toString(a5));
        long[] a6 = ConvertTo.primitive(Generated.array(Long.class, new SkipGenerator.Long(2), size));
        print("a6 = " + Arrays.toString(a6));
        float[] a7 = ConvertTo.primitive(Generated.array(Float.class, new SkipGenerator.Float(2.5f), size));
        print("a7 = " + Arrays.toString(a7));
        double[] a8 = ConvertTo.primitive(Generated.array(Double.class, new SkipGenerator.Double(2.5), size));
        print("a8 = " + Arrays.toString(a8));
    }
}
