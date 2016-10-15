package innerclasses;

public class Ex15 {
    Ex15(int i){
        System.out.println(i);
    }
}

class Ex15Second {
    Ex15 getEx15(int i){
        return new Ex15(i){};
    }
}

class Ex15Test {
    public static void main(String[] args) {
        Ex15 ex15 = new Ex15Second().getEx15(13);
    }
}
