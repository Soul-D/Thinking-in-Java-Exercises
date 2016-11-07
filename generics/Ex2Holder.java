package generics;

public class Ex2Holder<T> {
    T a;
    T b;
    T c;

    public Ex2Holder(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    public T getC() {
        return c;
    }

    public void setC(T c) {
        this.c = c;
    }
}

class Ex2Test{
    public static void main(String[] args) {
        Ex2Holder<String> holder = new Ex2Holder<String>("A","B","C");
        System.out.println(holder.getA());
        System.out.println(holder.getB());
        System.out.println(holder.getC());
        holder.setA("AA");
        System.out.println(holder.getA());
    }
}
