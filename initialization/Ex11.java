package initialization;

public class Ex11 {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize is running");
        super.finalize();
    }

    public static void main(String[] args) {
        new Ex11();
        System.gc();
        System.runFinalization();
    }
}
