package generics;

import net.mindview.util.New;

import java.util.Map;

public class Ex12 {
    public static void main(String[] args) {
        Map<Ex12,Ex10GenericMethods> map = New.<Ex12,Ex10GenericMethods>map();
        map.put(new Ex12(),new Ex10GenericMethods());
    }
}
