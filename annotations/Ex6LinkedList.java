package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;
import java.util.LinkedList;

public class Ex6LinkedList {
    LinkedList<String> testObject = new LinkedList<>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }

    @Test
    void _contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove() {
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\Ex6LinkedList";
        AtUnit.main(arr);
    }
}
