package arrays;

import net.mindview.util.Generated;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Ex18 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        BerylliumSphere[] berylliumSpheres = Generated.array(BerylliumSphere.class,new BerylliumGenerator(),13);
        System.out.println(Arrays.toString(berylliumSpheres));
        BerylliumSphere[] berylliumSpheres1 = new BerylliumSphere[5];
        System.arraycopy(berylliumSpheres,3,berylliumSpheres1,0,5);
        System.out.println(Arrays.toString(berylliumSpheres1));
        Field field = berylliumSpheres.getClass().getComponentType().getDeclaredField("id");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.setLong(berylliumSpheres[4],666);
        System.out.println(Arrays.toString(berylliumSpheres));
        System.out.println(Arrays.toString(berylliumSpheres1));
    }
}
