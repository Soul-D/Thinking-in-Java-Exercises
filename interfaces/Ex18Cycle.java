package interfaces;

public interface Ex18Cycle {
    void ride();
}

class Unicycle implements Ex18Cycle {
    public void ride() {
        System.out.println("Unicycle");
    }
}
class Bicycle implements Ex18Cycle {
    public void ride() {
        System.out.println("Bicycle");
    }
}
class Tricycle implements Ex18Cycle {
    public void ride() {
        System.out.println("Tricycle");
    }
}

interface CycleFactory {
    Ex18Cycle getCycle();
}

class UnicycleFactory implements CycleFactory{
    public Unicycle getCycle() {
        return new Unicycle();
    }
}

class BicycleFactory implements CycleFactory{
    public Bicycle getCycle() {
        return new Bicycle();
    }
}

class TricycleFactory implements CycleFactory{
    public Tricycle getCycle() {
        return new Tricycle();
    }
}

class Rides {
    public static void rideTheTrack(CycleFactory cf){
        cf.getCycle().ride();
    }
}

class Ex18Test{
    public static void main(String[] args) {
        Rides.rideTheTrack(new UnicycleFactory());
        Rides.rideTheTrack(new BicycleFactory());
        Rides.rideTheTrack(new TricycleFactory());
    }
}
