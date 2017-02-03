package annotations;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;
import net.mindview.atunit.TestObjectCreate;
import net.mindview.atunit.TestProperty;

import java.util.Random;

public class Ex8 {
    private void letsPlayAGame() {
        Random random = new Random();
        if (random.nextBoolean())
            throw new RuntimeException();
    }

    @TestProperty
    public void letsPlayAGamePub() {
        letsPlayAGame();
    }
}

class Ex8Test extends Ex8 {
    @Test
    void _letsPlayAGame() {
        letsPlayAGamePub();
    }

    @TestObjectCreate
    static Ex8Test create() {
        return new Ex8Test();
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\Ex8Test";
        AtUnit.main(arr);
    }
}
