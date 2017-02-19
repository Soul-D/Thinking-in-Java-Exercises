package concurrency;

@Active
public class ProjectPassiveObject {

    public void print() {
        System.out.println("Print method");
    }

    public void doIt() { System.out.println("Do it method"); }
}
