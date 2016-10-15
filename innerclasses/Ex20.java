package innerclasses;

public interface Ex20 {
    class Nested{}
}

class Ex20Impl implements Ex20{
}

class Ex20Test{
    public static void main(String[] args) {
        Ex20.Nested nested = new Ex20.Nested();
    }
}
