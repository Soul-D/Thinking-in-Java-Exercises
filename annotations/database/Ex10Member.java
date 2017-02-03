package annotations.database;

import net.mindview.atunit.AtUnit;
import net.mindview.atunit.Test;
import net.mindview.atunit.TestProperty;

public class Ex10Member {
    @TestProperty
    static Member member = new Member();

    @Test
    void getFirstName() {
        member.firstName = "Nicky";
        assert member.getFirstName().equals("Nicky");
    }

    public static void main(String[] args) throws Exception {
        String[] arr = new String[1];
        arr[0] = "target\\classes\\annotations\\database\\Ex10Member";
        AtUnit.main(arr);
    }
}
