package innerclasses;

public class Ex2StringHolder {
    private String str;

    public Ex2StringHolder(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}

class Ex2Test {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++){
            sequence.add(new Ex2StringHolder(Integer.toString(i)));
        }
        Selector selector = sequence.selector();
        while (!selector.end()){
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
