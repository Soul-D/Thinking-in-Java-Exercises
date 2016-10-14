package polymorphism;

public class Ex1_Cycle {
    void ride(){
        System.out.println("Ex1_Cycle " + wheels());
    }
    int wheels(){
        return 0;
    }
}

class Unicycle extends Ex1_Cycle {
    void ride(){
        System.out.println("Unicycle " + wheels());
    }

    int wheels() {
        return 1;
    }

    void balance(){
        System.err.println("Unicycle balance");
    }
}

class Bicycle extends Ex1_Cycle {
    void ride(){
        System.out.println("Bicycle " + wheels());
    }

    int wheels() {
        return 2;
    }

    void balance(){
        System.err.println("Bicycle balance");
    }
}

class Tricycle extends Ex1_Cycle {
    void ride(){
        System.out.println("Tricycle " + wheels());
    }

    int wheels() {
        return 3;
    }
}

class Ex1_Test {
    static void ride(Ex1_Cycle e){
        e.ride();
    }

    public static void main(String[] args) {
        ride(new Ex1_Cycle());
        ride(new Unicycle());
        ride(new Bicycle());
        ride(new Tricycle());
        Ex1_Cycle arr[] = {
                new Unicycle(),
                new Bicycle(),
                new Tricycle()
        };
        /*for (Ex1_Cycle c : arr){
            ((Unicycle)c).balance();
        }*/
    }
}
