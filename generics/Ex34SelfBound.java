package generics;

public abstract class Ex34SelfBound <T extends Ex34SelfBound<T>> {
    abstract T method(T arg);

    T method2(T arg){
        return method(arg);
    }
}

class Ex34Derived extends Ex34SelfBound<Ex34Derived>{
    Ex34Derived method(Ex34Derived arg) {
        return this;
    }
}
class Ex34Test{
    public static void main(String[] args) {
        Ex34Derived d = new Ex34Derived();
        d.method(d);
        d.method2(d);
    }
}
