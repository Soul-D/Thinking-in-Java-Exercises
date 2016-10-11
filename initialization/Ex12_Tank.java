package initialization;

public class Ex12_Tank {
    boolean isFull = true;

    @Override
    protected void finalize() throws Throwable {
        if (isFull){
            System.out.println("Tank is full error");
        }
        super.finalize();
    }

    public static void main(String[] args) {
        new Ex12_Tank().isFull = false;
        new Ex12_Tank();
        System.gc();
        System.runFinalization();
    }
}
