package generics;

import java.util.ArrayList;

public class Ex19CargoShip extends ArrayList<CargoHold> {
    public Ex19CargoShip(int nCargoHolds, int nContainers,
                         int nProducts) {
        for (int i = 0; i < nCargoHolds; i++)
            add(new CargoHold(nContainers, nProducts));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (CargoHold ch : this)
            for (Container c : ch)
                for (Product p : c) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }
}

class Container extends ArrayList<Product> {
    public Container(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

class CargoHold extends ArrayList<Container> {
    public CargoHold(int nContainers, int nProducts) {
        for (int i = 0; i < nContainers; i++)
            add(new Container(nProducts));
    }
}

class E19Test {
    public static void main(String[] args) {
        System.out.println(new Ex19CargoShip(14, 5, 10));
    }
}


