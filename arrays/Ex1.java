package arrays;

import java.util.Arrays;

public class Ex1 {
    static void method(BerylliumSphere[] spheres){
        System.out.println(Arrays.toString(spheres));
    }

    public static void main(String[] args) {
        method(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()});
        //method(BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()});
        BerylliumSphere[] spheres = new BerylliumSphere[] {new BerylliumSphere(), new BerylliumSphere()};
        BerylliumSphere[] spheres2 = {new BerylliumSphere(), new BerylliumSphere()};
        //spheres = {new BerylliumSphere(), new BerylliumSphere()};
    }
}
