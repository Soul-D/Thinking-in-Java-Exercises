package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;

import java.util.LinkedList;

public class Ex7LinkedList extends LinkedList<String> {

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
        arr[0] = "target\\classes\\annotations\\Ex7LinkedList";
        AtUnit.main(arr);
    }
}
