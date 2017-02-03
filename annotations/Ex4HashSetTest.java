package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;

import java.util.*;

public class Ex4HashSetTest {
    private static final Object obj = new Object();
    private static Map<HashSet<String>, Object> hashMap = new IdentityHashMap<>();
    HashSet<String> testObject = new HashSet<String>();

    @Test
    void initialization() {
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(testObject) != null);
        hashMap.put(testObject, obj);
        System.out.println(hashMap.get(testObject) != null);
        assert testObject.isEmpty();
    }

    @Test
    void _contains() {
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(testObject) != null);
        hashMap.put(testObject, obj);
        System.out.println(hashMap.get(testObject) != null);
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove() {
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(testObject) != null);
        hashMap.put(testObject, obj);
        System.out.println(hashMap.get(testObject) != null);
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\Ex4HashSetTest";
        AtUnit.main(arr);
    }
}