package generics;

import net.mindview.util.New;

import java.util.Map;

public class Ex11 {
    public static void main(String[] args) {
        Map<Ex11,Ex10GenericMethods> map = New.map();
        map.put(new Ex11(),new Ex10GenericMethods());
    }
}
