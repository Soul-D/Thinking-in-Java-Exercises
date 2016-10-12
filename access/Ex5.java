package access;

public class Ex5 {
    public static void main(String[] args) {
        Ex5Helper h = new Ex5Helper();
        int i = h.i;
        i = h.j;
        //i = h.k;
        i = h.l;
        h.printPublic();
        h.printProtected();
        //h.printPrivate();
        h.printDefault();
    }
}

class Ex5Helper {
    public int i;
    protected int j;
    private int k;
    int l;
    public void printPublic(){
        System.out.println("public");
    }
    protected void printProtected(){
        System.out.println("protected");
    }
    private void printPrivate(){
        System.out.println("private");
    }
    void printDefault(){
        System.out.println("Default");
    }
}
