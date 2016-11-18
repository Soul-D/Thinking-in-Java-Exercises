package arrays;

import java.util.Arrays;

public class Ex2 {
    static BerylliumSphere[] fill(int n){
        BerylliumSphere[] spheres = new BerylliumSphere[n];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new BerylliumSphere();
        return spheres;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fill(15)));
    }
}
