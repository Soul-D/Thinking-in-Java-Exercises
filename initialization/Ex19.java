package initialization;

public class Ex19 {
    public static void main(String[] args) {
        doNothing("1","2","3");
        String[] arr = {"1","2","42"};
        doNothing(arr);
    }

    static void doNothing(String... s){
        for (String str : s){
            System.out.println(str);
        }
    }
}
