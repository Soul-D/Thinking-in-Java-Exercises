package generics;

public interface Ex37Colored {
    Ex37Color getColor();

    void setColor(Ex37Color color);
}

class Ex37ColoredImpl implements Ex37Colored {
    private Ex37Color color;

    public Ex37Color getColor() {
        return color;
    }

    public void setColor(Ex37Color color) {
        this.color = color;
    }
}

enum Ex37Color {
    BLACK, WHITE, RED
}

class Ex37Mixin extends BasicImp implements TimeStamped, SerialNumbered, Ex37Colored {
    private TimeStamped timeStamp = new TimeStampedImp();
    private SerialNumbered serialNumber = new SerialNumberedImp();
    private Ex37Colored colored = new Ex37ColoredImpl();

    public Ex37Color getColor() {
        return colored.getColor();
    }

    public void setColor(Ex37Color color) {
        colored.setColor(color);
    }

    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }

    public long getStamp() {
        return timeStamp.getStamp();
    }
}

class Ex37Test{
    public static void main(String[] args) {
        Ex37Mixin mixin = new Ex37Mixin();
        System.out.println(mixin.getSerialNumber());
        System.out.println(mixin.getStamp());
        System.out.println(mixin.getColor());
        mixin.setColor(Ex37Color.RED);
        System.out.println(mixin.getColor());
    }
}
