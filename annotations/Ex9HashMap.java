package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;

import java.util.HashMap;

public class Ex9HashMap extends HashMap<Integer, Integer> {
    @Test
    void _isEmpty() {
        assert isEmpty();
    }

    @Test
    void _put() {
        put(1,13);
        assert get(1).equals(13);
    }

    @Test
    void _size(){
        put(1,13);
        put(2,13);
        assert size() == 2;
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\Ex9HashMap";
        AtUnit.main(arr);
    }
}
