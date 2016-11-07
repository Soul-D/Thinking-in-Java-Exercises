package generics;

public class Ex6 {
    public static void main(String[] args) {
        RandomList<Integer> rs = new RandomList<Integer>();
        for(int i = 0; i < 11; i++)
            rs.add(i);
        for(int i = 0; i < 11; i++)
            System.out.print(rs.select() + " ");
        System.out.println();
        RandomList<Float> rs2 = new RandomList<Float>();
        for(int i = 0; i < 11; i++)
            rs2.add(1.1f * i);
        for(int i = 0; i < 11; i++)
            System.out.printf("%.1f ",rs2.select());
    }
}
