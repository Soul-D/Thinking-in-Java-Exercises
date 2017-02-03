package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

public class Ex5HashSetTest extends HashSet<String> {

    @Test
    void initialization() {
        assert isEmpty();
    }

    @Test
    void _contains() {
        add("one");
        assert contains("one");
    }

    @Test
    void _remove() {
        add("one");
        remove("one");
        assert isEmpty();
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\Ex5HashSetTest";
        AtUnit.main(arr);
    }
}