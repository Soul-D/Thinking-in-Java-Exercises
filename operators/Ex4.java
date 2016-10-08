package operators;

public class Ex4 {
    public static void main(String[] args) {
        double distance = 5;
        double time = 2;
        System.out.println(getVelocity(distance, time));
    }

    private static double getVelocity(double distance, double time) {
        return distance/time;
    }
}
