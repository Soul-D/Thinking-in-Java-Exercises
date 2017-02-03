package annotations;

@ExtractInterface("IDivisor")
public class Ex2Divisor {
    public int divide(int x, int y) {
        int res = 0;
        while (x >= y) {
            x = sub(x, y);
            res++;
        }
        return res;
    }

    private int sub(int x, int y) {
        return x - y;
    }

    public static void main(String[] args) {
        Ex2Divisor d = new Ex2Divisor();
        System.out.println(d.divide(180,11));
    }
}
