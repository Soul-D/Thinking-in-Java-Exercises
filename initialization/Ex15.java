package initialization;

public class Ex15 {
    String s1;
    {
        s1 = "s1";
    }

    public static void main(String[] args) {
        System.out.println(new Ex15().s1);
    }
}
