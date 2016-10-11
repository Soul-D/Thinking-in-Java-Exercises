package initialization;

public class Ex10 {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize is running");
        super.finalize();
    }

    public static void main(String[] args) {
        new Ex10();
    }
}
