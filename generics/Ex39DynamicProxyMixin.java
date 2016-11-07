package generics;

import java.util.Random;

import static net.mindview.util.Tuple.tuple;

interface Ex39Colored {
    String getColor();
}

class Ex39ColoredImpl implements Ex39Colored{
    public String getColor() {
        return "Color is " + new Random().nextInt(32);
    }
}

public class Ex39DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basic.class),
                tuple(new TimeStampedImp(), TimeStamped.class),
                tuple(new SerialNumberedImp(), SerialNumbered.class),
                tuple(new Ex39ColoredImpl(), Ex39Colored.class));
        Basic b = (Basic) mixin;
        TimeStamped t = (TimeStamped) mixin;
        SerialNumbered s = (SerialNumbered) mixin;
        Ex39Colored c = (Ex39Colored) mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
        System.out.println(c.getColor());
    }
}
