package arrays;

import net.mindview.util.Generated;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class Ex21 {
    public static void main(String[] args) {
        BerylliumSphere[] berylliumSpheres = Generated.array(BerylliumSphere.class, new BerylliumGenerator(), 13);
        System.out.println(Arrays.toString(berylliumSpheres));
        Arrays.sort(berylliumSpheres, new Comparator<BerylliumSphere>() {
            public int compare(BerylliumSphere o1, BerylliumSphere o2) {
                try {
                    Field field = o1.getClass().getDeclaredField("id");
                    field.setAccessible(true);
                    long id1 = (Long) field.get(o1);
                    long id2 = (Long) field.get(o2);
                    return id1 > id2 ? -1 : id1 == id2 ? 0 : 1;
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });
        System.out.println(Arrays.toString(berylliumSpheres));
    }
}
