package arrays;

import net.mindview.util.Generated;
import net.mindview.util.Generator;

import java.util.Arrays;

class BerylliumGenerator implements Generator<BerylliumSphere>{
    public BerylliumSphere next() {
        return new BerylliumSphere();
    }
}

public class Ex15 {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = Generated.array(BerylliumSphere.class,new BerylliumGenerator(),13);
        System.out.println(Arrays.toString(spheres));
    }
}
