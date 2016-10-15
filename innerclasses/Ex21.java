package innerclasses;

public interface Ex21 {
    void run();
    void hide();
    class Nested{
        static void call(Ex21 e){
            e.run();
            e.hide();
        }
    }
}

class Ex21Impl implements Ex21{
    public void run() {
        System.out.println("run");
    }

    public void hide() {
        System.out.println("hide");
    }
}

class Ex21Test{
    public static void main(String[] args) {
        Ex21.Nested.call(new Ex21Impl());
    }
}
