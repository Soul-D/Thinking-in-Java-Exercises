package innerclasses;

public interface Ex23U {
    void m1();
    void m2();
    void m3();
}

class Ex23A{
    Ex23U getEx23U(){
        return new Ex23U() {
            public void m1() {
                System.out.println("m1");
            }

            public void m2() {
                System.out.println("m2");
            }

            public void m3() {
                System.out.println("m3");
            }
        };
    }
}

class Ex23B{
    int i;
    Ex23U[] arr;

    public Ex23B(int i) {
        arr = new Ex23U[i];
    }

    void add(Ex23U u){
        if (i < arr.length)
            arr[i++] = u;
    }

    void setNull(int index){
        if (index >= 0 && index < arr.length)
            arr[index] = null;
    }

    void callMethods(){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null) {
                arr[i].m1();
                arr[i].m2();
                arr[i].m3();
            }
        }
    }
}

class Ex23TEst {
    public static void main(String[] args) {
        Ex23A a1 = new Ex23A();
        Ex23A a2 = new Ex23A();
        Ex23A a3 = new Ex23A();
        Ex23B b = new Ex23B(2);
        for (int i = 0; i < 2; i++){
            b.add(a1.getEx23U());
        }
        b.callMethods();
        b.setNull(1);
        System.out.println();
        b.callMethods();
    }
}
