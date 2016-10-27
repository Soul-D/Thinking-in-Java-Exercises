//: typeinfo/RegisteredFactories.java
package typeinfo; /* Added by Eclipse.py */
// Registering Class Factories in the base class.

import typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Ex14 {
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Class<? extends Part>> partFactories =
            new ArrayList<Class<? extends Part>>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(FuelFilter.class);
        partFactories.add(AirFilter.class);
        partFactories.add(CabinAirFilter.class);
        partFactories.add(OilFilter.class);
        partFactories.add(FanBelt.class);
        partFactories.add(PowerSteeringBelt.class);
        partFactories.add(GeneratorBelt.class);
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        try {
            return partFactories.get(n).newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}

class Ex14Test{
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++)
            System.out.println(Part.createRandom());
    }
}

