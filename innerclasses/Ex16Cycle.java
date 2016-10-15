package innerclasses;

public interface Ex16Cycle {
    void ride();
}

class Unicycle implements Ex16Cycle {
    public void ride() {
        System.out.println("Unicycle");
    }
    public static CycleFactory factory = new CycleFactory() {
        public Ex16Cycle getCycle() {
            return new Unicycle();
        }
    };
}

class Bicycle implements Ex16Cycle {
    public void ride() {
        System.out.println("Bicycle");
    }
    public static CycleFactory factory = new CycleFactory() {
        public Ex16Cycle getCycle() {
            return new Bicycle();
        }
    };
}

class Tricycle implements Ex16Cycle {
    public void ride() {
        System.out.println("Tricycle");
    }
    public static CycleFactory factory = new CycleFactory() {
        public Ex16Cycle getCycle() {
            return new Tricycle();
        }
    };
}

interface CycleFactory {
    Ex16Cycle getCycle();
}

class Rides {
    public static void rideTheTrack(CycleFactory cf){
        cf.getCycle().ride();
    }
}

class Ex16Test {
    public static void main(String[] args) {
        Rides.rideTheTrack(Unicycle.factory);
        Rides.rideTheTrack(Bicycle.factory);
        Rides.rideTheTrack(Tricycle.factory);
    }
}
